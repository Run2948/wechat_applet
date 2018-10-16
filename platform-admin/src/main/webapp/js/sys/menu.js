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
    var table = new TreeTable(TreeGrid.id, '../sys/menu/queryAll', colunms);
    table.setExpandColumn(2);
    table.setIdField("menuId");
    table.setCodeField("menuId");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}

var TreeGrid = {
    id: "jqGrid",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'menuId', visitable: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', width: '180px'},
        {title: '上级菜单', field: 'parentName', align: 'center', valign: 'middle', width: '100px'},
        {
            title: '图标',
            field: 'icon',
            align: 'center',
            valign: 'middle',
            width: '50px',
            formatter: function (item, index) {
                return item.icon == null ? '' : '<i class="' + item.icon + ' fa-lg"></i>';
            }
        },
        {
            title: '类型',
            field: 'type',
            align: 'center',
            valign: 'middle',
            width: '60px',
            formatter: function (item) {
                if (item.type === 0) {
                    return '<span class="label label-primary">目录</span>';
                }
                if (item.type === 1) {
                    return '<span class="label label-success">菜单</span>';
                }
                if (item.type === 2) {
                    return '<span class="label label-warning">按钮</span>';
                }
            }
        },
        {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', width: '50px'},
        {title: '菜单URL', field: 'url', align: 'center', valign: 'middle', width: '200px'},
        {title: '授权标识', field: 'perms', align: 'center', valign: 'middle'},
        {
            title: '状态', field: 'status', align: 'center', valign: 'middle', width: '80px',
            formatter: function (item) {
                if (item.status === 1) {
                    return '<span class="label label-danger">无效</span>';
                }
                return '<span class="label label-success">有效</span>';
            }
        }];
    return columns;
};

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
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
        menu: {
            parentName: null,
            parentId: 0,
            type: 1,
            orderNum: 0,
            status: 0
        },
        q: {
            menuName: '',
            parentName: ''
        },
        ruleValidate: {
            name: [
                {required: true, message: '菜单名称不能为空', trigger: 'blur'}
            ],
            url: [
                {required: true, message: '菜单url不能为空', trigger: 'blur'}
            ],
            parentName: [
                {required: true, message: '上级菜单不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        selectIcon: function () {
            openWindow({
                type: 2,
                title: '选取图标',
                area: ['1030px', '500px'],
                content: ['icon.html'],
                btn: false
            });
        },
        getMenu: function (menuId) {
            //加载菜单树
            Ajax.request({
                url: "../sys/menu/select",
                async: true,
                successCallback: function (r) {
                    ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                    var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
                    if (node) {
                        ztree.selectNode(node);
                        vm.menu.parentName = node.name;
                    } else {
                        node = ztree.getNodeByParam("menuId", 0);
                        ztree.selectNode(node);
                        vm.menu.parentName = node.name;
                    }
                }
            });
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            var menuId = TreeGrid.table.getSelectedRow();
            var parentId = 0;
            if (menuId.length != 0) {
                parentId = menuId[0].id;
            }
            vm.menu = {parentName: null, parentId: parentId, type: 1, orderNum: 0, status: 0};
            vm.getMenu();
        },
        update: function (event) {
            var menuId = TreeGrid.table.getSelectedRow();
            if (menuId.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }

            Ajax.request({
                url: "../sys/menu/info/" + menuId[0].id,
                async: true,
                successCallback: function (r) {
                    vm.showList = false;
                    vm.title = "修改";
                    vm.menu = r.menu;

                    vm.getMenu();
                }
            });
        },
        del: function (event) {
            var menuIds = TreeGrid.table.getSelectedRow(), ids = [];
            if (menuIds.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.each(menuIds, function (idx, item) {
                    ids[idx] = item.id;
                });
                Ajax.request({
                    url: "../sys/menu/delete",
                    params: JSON.stringify(ids),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.menu.menuId == null ? "../sys/menu/save" : "../sys/menu/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.menu),
                contentType: "application/json",
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        menuTree: function () {
            openWindow({
                title: "选择菜单",
                area: ['300px', '450px'],
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.menu.parentId = node[0].menuId;
                    vm.menu.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        query: function () {
            vm.reload();
        },
        reload: function (event) {
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