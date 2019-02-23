$(function () {
    $("#jqGrid").Grid({
        url: '../sys/log/list',
        colModel: [
            {label: 'id', name: 'id', key: true, hidden: true},
            {label: '用户名', name: 'username', width: 50},
            {label: '用户操作', name: 'operation', width: 70},
            {label: '请求方法', name: 'method', width: 150},
            {label: '请求参数', name: 'params', width: 80},
            {label: 'IP地址', name: 'ip', width: 150},
            {
                label: '创建时间', name: 'createDate', width: 90, formatter: function (value) {
                return transDate(value);
            }
            }
        ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            key: null
        },
        isLogin: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');

            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'key': vm.q.key, 'operation': vm.isLogin.toLocaleString()},
                page: page
            }).trigger("reloadGrid");
        }
    }
});