$(function () {
    $("#jqGrid").Grid({
        url: '../sys/config/list',
        colModel: [
            {label: 'ID', name: 'id', key: true, hidden: true},
            {label: '参数名', name: 'key', index: 'key', width: 60},
            {label: '参数值', name: 'value', index: 'value', width: 100},
            {label: '备注', name: 'remark', index: 'remark', width: 80}
        ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            key: null
        },
        showList: true,
        title: null,
        config: {},
        ruleValidate: {
            key: [
                {required: true, message: '参数名不能为空', trigger: 'blur'}
            ],
            value: [
                {required: true, message: '参数值不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.config = {};
        },
        update: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }

            Ajax.request({
                url: "../sys/config/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.showList = false;
                    vm.title = "修改";
                    vm.config = r.config;
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/config/delete",
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
            var url = vm.config.id == null ? "../sys/config/save" : "../sys/config/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.config),
                contentType: "application/json",
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key},
                page: page
            }).trigger("reloadGrid");
        },
        reloadSearch: function () {
            vm.q = {
                confKey: ''
            }
            vm.reload();
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