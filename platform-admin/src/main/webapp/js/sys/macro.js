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
    var table = new TreeTable(TreeGrid.id, '../sys/macro/queryAll', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
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
        {title: 'id', field: 'id', align: 'center', valign: 'middle', width: '50px'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', width: '50px'},
        {title: '值', field: 'value', align: 'center', valign: 'middle', width: '50px'},
        {
            title: '状态',
            field: 'status',
            align: 'center',
            valign: 'middle',
            width: '50px',
            formatter: function (item) {
                if (item.status == 1) {
                    return '显示';
                } else {
                    return '隐藏';
                }
            }
        },
        {
            title: '类型', field: 'type', align: 'center', valign: 'middle', width: '50px',
            formatter: function (item, index) {
                if (item.type === 1) {
                    return '<span class="label label-success">参数配置</span>';
                }
                return '<span class="label label-primary">目录</span>';
            }
        },
        {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', width: '50px'},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', width: '50px'},
        {
            title: '创建时间',
            field: 'gmtCreate',
            align: 'center',
            valign: 'middle',
            width: '80px',
            formatter: function (item) {
                return transDate(item.gmtCreate);
            }
        },
        {
            title: '修改时间', field: 'gmtModified', align: 'center', valign: 'middle', width: '80px',
            formatter: function (item) {
                return transDate(item.gmtCreate);
            }
        }
    ];
    return columns;
};


var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        macro: {type: 0, status: 1},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        macros: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getMacros: function () {
            Ajax.request({
                url: "../sys/macro/queryAll?type=0",
                async: true,
                successCallback: function (r) {
                    vm.macros = r.list;
                }
            });
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.macro = {type: 0, status: 1};
            vm.macros = [];
            vm.getMacros();
        },
        update: function (event) {
            var id = TreeGrid.table.getSelectedRow();
            if (id.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            Ajax.request({
                url: "../sys/macro/info/" + id[0].id,
                async: true,
                successCallback: function (r) {
                    vm.macro = r.macro;
                }
            });
            vm.getMacros();
        },
        saveOrUpdate: function (event) {
            var url = vm.macro.id == null ? "../sys/macro/save" : "../sys/macro/update";
            Ajax.request({
                type: 'POST',
                url: url,
                params: JSON.stringify(vm.macro),
                contentType: "application/json",
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var id = TreeGrid.table.getSelectedRow(), ids = [];
            if (id.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.each(id, function (idx, item) {
                    ids[idx] = item.id;
                });
                Ajax.request({
                    url: "../sys/macro/delete",
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