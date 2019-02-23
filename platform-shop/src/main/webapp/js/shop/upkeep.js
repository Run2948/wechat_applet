$(function () {
    let customerId = getQueryString("customerId");
    let url = '../upkeep/list';
    if (customerId) {
        url += '?customerId=' + customerId;
    }
    $("#jqGrid").Grid({
        url: '../upkeep/list'+'?customerId=' + customerId,
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: '维护时间', name: 'utime', index: 'utime', width: 80, formatter: function (value) {
        return transDate(value);
    }

        }, {
            label: '维护方式', name: 'ctype', index: 'ctype', width: 80
        }, {
            label: '客户状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                return value=='1'?'有意向':'无意向';
            }
        },{
                label: '地点', name: 'place', index: 'place', width: 80
            },{
            label: '礼品金额', name: 'giftPrice', giftPrice: 'place', width: 80,formatter: function (value) {
                return value==null?'0.00':value;
            }
        } , {
            label: '维护数据来源', name: 'dataFrom', index: 'dataFrom', width: 80, formatter: function (value) {
                return transDataFrom(value);
            }

        },]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            uname: ''
          //  telNumber: ''
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
                    url: "../customer/delete",
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
