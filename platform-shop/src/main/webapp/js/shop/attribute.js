$(function () {
    $("#jqGrid").Grid({
        url: '../attribute/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '所属种类', name: 'categoryName', index: 'attribute_category_id', width: 80},
            {label: '名称', name: 'name', index: 'name', width: 80},
            // {label: '类型', name: 'inputType', index: 'input_type', width: 80},
            // {label: '值', name: 'value', index: 'value', width: 80},
            {label: '排序', name: 'sortOrder', index: 'sort_order', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        attribute: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: '',
            categoryName: ''
        },
        categories: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.attribute = {};
            vm.categories = [];
            this.getCategories();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            this.getCategories();
        },
        saveOrUpdate: function (event) {
            var url = vm.attribute.id == null ? "../attribute/save" : "../attribute/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.attribute),
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
                    type: "POST",
                    url: "../attribute/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
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
                url: "../attribute/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.attribute = r.attribute;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name, 'categoryName': vm.q.categoryName},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        getCategories: function () {
            Ajax.request({
                url: "../attributecategory/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.categories = r.list;
                }
            });
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