$(function () {
    $("#jqGrid").Grid({
        url: '../feedback/list',
        colModel: [
            {label: 'msgId', name: 'msgId', index: 'msg_id', key: true, hidden: true},
            {label: '会员名称', name: 'userName', index: 'user_name', width: 80},
            {label: '手机', name: 'mobile', index: 'mobile', width: 80},
            {
                label: '反馈类型', name: 'feedType', index: 'feed_Type', width: 80, formatter: function (value) {
                    if (value == 1) {
                        return '商品相关';
                    } else if (value == 2) {
                        return '物流状况';
                    } else if (value == 3) {
                        return '客户服务';
                    } else if (value == 4) {
                        return '优惠活动';
                    } else if (value == 5) {
                        return '功能异常';
                    } else if (value == 6) {
                        return '产品建议';
                    } else if (value == 7) {
                        return '其他';
                    }
                    return '';
                }
            },
            {label: '详细内容', name: 'content', index: 'content', width: 80},
            {
                label: '反馈时间', name: 'addTime', index: 'add_time', width: 80, formatter: function (value) {
                    return transDate(value);
                }
            }]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        feedback: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            userName: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.feedback = {};
        },
        update: function (event) {
            let msgId = getSelectedRow("#jqGrid");
            if (msgId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(msgId)
        },
        saveOrUpdate: function (event) {
            let url = vm.feedback.msgId == null ? "../feedback/save" : "../feedback/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.feedback),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            let msgIds = getSelectedRows("#jqGrid");
            if (msgIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {

                Ajax.request({
                    type: "POST",
                    url: "../feedback/delete",
                    contentType: "application/json",
                    params: JSON.stringify(msgIds),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (msgId) {
            Ajax.request({
                url: "../feedback/info/" + msgId,
                async: true,
                successCallback: function (r) {
                    vm.feedback = r.feedback;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.userName},
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