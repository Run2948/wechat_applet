$(function () {
    let userId = getQueryString("userId");
    let url = '../address/list';
    if (userId) {
        url += '?userId=' + userId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员', name: 'shopUserName', index: 'user_id', width: 90},
            {label: '收货人姓名', name: 'userName', index: 'user_name', width: 80},
            {label: '手机', name: 'telNumber', index: 'tel_number', width: 80},
            {label: '收货地址国家码', name: 'nationalCode', index: 'national_Code', width: 80},
            {label: '省', name: 'provinceName', index: 'province_Name', width: 80},
            {label: '市', name: 'cityName', index: 'city_Name', width: 80},
            {label: '区', name: 'countyName', index: 'county_Name', width: 80},
            {label: '详细收货地址信息', name: 'detailInfo', index: 'detail_Info', width: 150},
            {label: '邮编', name: 'postalCode', index: 'postal_Code', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            userName: '',
            telNumber: ''
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
                postData: {
                    'userName': vm.q.userName,
                    'telNumber': vm.q.telNumber
                },
                page: page
            }).trigger("reloadGrid");
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../address/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        }
    }
});