$(function () {
    $("#jqGrid").Grid({
        url: '../sys/region/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '上级区域', name: 'parentName', index: 'parent_id', width: 80},
            {label: '区域', name: 'name', index: 'name', width: 80},
            {
                label: '类型', name: 'type', index: 'type', width: 80,
                formatter: function (value) {
                    if (value === '0' || value === 0) {
                        return '国家';
                    }
                    if (value == '1') {
                        return '省份';
                    }
                    if (value == '2') {
                        return '地市';
                    }
                    if (value == '3') {
                        return '区县';
                    }
                }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        region: {type: '1'},
        ruleValidate: {
            name: [
                {required: true, message: '区域名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            parentName: '',
            name: '',
            type: ''
        },
        regionList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.region = {type: '1'};
            vm.changeType(1);
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.region.id == null ? "../sys/region/save" : "../sys/region/update";
            Ajax.request({
                type: 'POST',
                url: url,
                params: JSON.stringify(vm.region),
                contentType: "application/json",
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
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
                    url: "../sys/region/delete",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../sys/region/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.region = r.region;
                    vm.changeType(vm.region.type);
                }
            });
        },
        changeType: function (type) {
            Ajax.request({
                url: "../sys/region/getAreaByType?type=" + type,
                async: true,
                successCallback: function (r) {
                    vm.regionList = r.list;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'name': vm.q.name,
                    'parentName': vm.q.parentName,
                    'type': vm.q.type
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
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