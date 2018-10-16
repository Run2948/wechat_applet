$(function () {
    $("#jqGrid").Grid({
        url: '../keywords/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '关键词', name: 'keyword', index: 'keyword', width: 80},
            {label: '热销', name: 'isHot', index: 'is_hot', width: 80},
            {label: '默认', name: 'isDefault', index: 'is_default', width: 80},
            {label: '显示', name: 'isShow', index: 'is_show', width: 80},
            {label: '排序', name: 'sortOrder', index: 'sort_order', width: 80},
            {label: '跳转链接', name: 'schemeUrl', index: 'scheme_url', width: 80},
            {label: '类型', name: 'type', index: 'type', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        keywords: {isHot: 0, isDefault: 0, isShow: 1, type: 0, keyword:'', schemeUrl:'', sortOrder:1},
        ruleValidate: {
            keyword: [
                {required: true, message: '关键词不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: '', keyword:''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.keywords = {isHot: 0, isDefault: 0, isShow: 1, type: 0, keyword:'', schemeUrl:'', sortOrder:1};
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
            var url = vm.keywords.id == null ? "../keywords/save" : "../keywords/update";

            //confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: url,
                    contentType: "application/json",
                    params: JSON.stringify(vm.keywords),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });

            //});
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../keywords/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../keywords/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.keywords = r.keywords;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'keyword': vm.q.keyword},
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