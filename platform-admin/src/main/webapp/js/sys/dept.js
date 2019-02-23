$(function () {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function () {
        TreeGrid.table.resetHeight({height: $(window).height() - 100});
    });
}

function getGrid() {
    var colunms = TreeGrid.initColumn();
    var table = new TreeTable(TreeGrid.id, '../sys/dept/list', colunms);
    table.setExpandColumn(2);
    table.setIdField("deptId");
    table.setCodeField("deptId");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}

var TreeGrid = {
    id: "deptTable",
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '部门ID', field: 'deptId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '部门名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级部门', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        dept: {
            parentName: null,
            parentId: 0,
            orderNum: 0
        },
        ruleValidate: {
            name: [
                {required: true, message: '部门名称不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        getDept: function () {
            //加载部门树
            Ajax.request({
                url: "../sys/dept/select",
                async: true,
                successCallback: function (r) {
                    ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                    var node = ztree.getNodeByParam("deptId", vm.dept.parentId);
                    if (node) {
                        ztree.selectNode(node);
                        vm.dept.parentName = node.name;
                    } else {
                        node = ztree.getNodeByParam("deptId", 0);
                        ztree.selectNode(node);
                        vm.dept.parentName = node.name;
                    }
                }
            });
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            var deptId = TreeGrid.table.getSelectedRow();
            var parentId = 0;
            if (deptId.length != 0) {
                parentId = deptId[0].id;
            }
            vm.dept = {parentName: null, parentId: parentId, orderNum: 0};
            vm.getDept();
        },
        update: function () {
            var deptId = getDeptId();
            if (deptId == null) {
                return;
            }
            Ajax.request({
                url: "../sys/dept/info/" + deptId,
                async: true,
                successCallback: function (r) {
                    vm.showList = false;
                    vm.title = "修改";
                    vm.dept = r.dept;

                    vm.getDept();
                }
            });
        },
        del: function () {
            var deptId = getDeptId();
            if (!deptId) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../sys/dept/delete",
                    params: {"deptId": deptId},
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.dept.deptId == null ? "../sys/dept/save" : "../sys/dept/update";
            Ajax.request({
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.dept),
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        deptTree: function () {
            openWindow({
                title: "选择部门",
                area: ['300px', '450px'],
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.dept.parentId = node[0].deptId;
                    vm.dept.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            TreeGrid.table.refresh();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
    }
});

function getDeptId() {
    var selected = $('#deptTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}