$(function () {
    $("#jqGrid").Grid({
        url: '../holiday/list',
        colModel: [
            {label: '节日ID', name: 'id', index: "id", key: true, hidden: true},
            {label: '节日名称', name: 'holidayName',  index: "holiday_Name"  ,width: 75},         
            {
                label: '时间', name: 'holiday', width: 80, formatter: function (value) {
                    return transDate(value,"yyyy-MM-dd");
                }
            }]
    });
});


var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
        	holidayName: ''
        },
        showList: true,
        title: null,
        roleList: {},
        holiday: {        
        	holidayName: '',
        	holiday: ''
        },
        ruleValidate: {
        	holidayName: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            holiday: [
            	{required: true, message: '日期不能为空', trigger: 'change',type: 'date' }
           ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增节日";
            vm.holiday = { holidayName: '', holiday: ''};           
        },
        update: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            Ajax.request({
                url: "../holiday/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.holiday = r.holiday;
                }
            });

        },
        del: function () {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../holiday/delete",
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
            var url = vm.holiday.id == null ? "../holiday/save" : "../holiday/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.holiday),
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
                postData: {'holidayName': vm.q.holidayName},
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