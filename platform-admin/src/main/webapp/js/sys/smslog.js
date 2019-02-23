$(function () {
    $("#jqGrid").Grid({
        url: '../sys/smslog/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '操作人', name: 'userName', index: 'user_id', width: 80},
            {label: '发送编号', name: 'sendId', index: 'send_id', width: 80},
            {label: '发送内容', name: 'content', index: 'content', width: 80},
            {
                label: '发送时间', name: 'stime', index: 'stime', width: 100, formatter: function (value) {
                    return transDate(value, 'yyyy-MM-dd hh:mm:ss');
                }
            },
            {label: '用户签名', name: 'sign', index: 'sign', width: 80},
            {
                label: '发送状态', name: 'sendStatus', index: 'send_status', width: 80, formatter: function (value) {
                    if (value === 0) {
                        return '<span class="label label-success">成功</span>';
                    }
                    return '<span class="label label-danger">失败</span>';
                }
            },
            {label: '无效号码数', name: 'invalidNum', index: 'invalid_num', width: 80},
            {label: '成功提交数', name: 'successNum', index: 'success_num', width: 80},
            {label: '黑名单数', name: 'blackNum', index: 'black_num', width: 80},
            {label: '返回消息', name: 'returnMsg', index: 'return_msg', width: 100}]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        config: {},
        ruleValidate: {
            domain: [
                {required: true, message: '发送域名不能为空', trigger: 'blur'}
            ],
            name: [
                {required: true, message: '用户名不能为空', trigger: 'blur'}
            ],
            pwd: [
                {required: true, message: '接口密钥不能为空', trigger: 'blur'}
            ],
            sign: [
                {required: true, message: '签名不能为空', trigger: 'blur'}
            ]
        },
        q: {
            sendId: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        addConfig: function (event) {
            vm.showList = false;
            vm.title = "短信配置";
            vm.getConfig();
        },
        updateConfig: function (event) {
            let url = "../sys/smslog/saveConfig";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.config),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        getConfig: function () {
            Ajax.request({
                url: "../sys/smslog/config",
                async: true,
                successCallback: function (r) {
                    vm.config = r.config;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'sendId': vm.q.sendId},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                sendId: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.updateConfig()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        sendSms: function () {
            openWindow({
                type: 2,
                title: '发送短信',
                area: ['1030px', '500px'],
                content: ['sendsms.html'],
                cancel: function (index) {
                    vm.reload();
                }
            });
        }
    }
});