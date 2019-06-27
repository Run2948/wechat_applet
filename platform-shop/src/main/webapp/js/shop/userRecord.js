$(function () {
    let url = '../userRecord/list';
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'reportId', index: 'reportId', key: true, hidden: true},
            {label: '分销用户ID', name: 'mlsUserId', index: 'mlsUserId', width: 80},
            {label: '分销用户名', name: 'userName', index: 'userName', width: 80},
            {label: '记录内容', name: 'typesStr', index: 'typesStr', width: 80},
            {label: '金额', name: 'price', index: 'price', width: 80},
            {label: '备注', name: 'remarks', index: 'remarks', width: 80},
            {
                label: '交易时间', name: 'ctime', index: 'ctime', width: 80, formatter: function (value) {
                    return transDate(value);
                }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        userRecord: {},
        q: {
            mlsUserId: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'mlsUserId': vm.q.mlsUserId},
                page: page
            }).trigger("reloadGrid");
        }
    }
});