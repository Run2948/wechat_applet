$(function () {
    let types = getQueryString("types");
    let url = '../order/groupList';
    if (types) {
        url += '?types=' + types;
    }
   
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商品Id', name: 'goodsId', index: 'goods_id', width: 100},
            {label: '商品名称', name: 'goodsName', index: 'goods_name', width: 80},
            {label: '购买人数', name: 'groupNum', index: 'group_num', width: 80},            
            {label: '成团需要人数', name: 'successNum', index: 'success_num', width: 80},            
            {
                label: '是否成功团', name: 'types', index: 'types', width: 80, formatter: function (value) {
                    if (value == '1') {
                        return '已成团';
                    } else if (value == '0') {
                        return '未成团';
                    } 
                    return '-';
                }
            }
        ]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        detail: false,
        q: {
        	goodsName: '',
        	types: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            vm.detail = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'goodsName': vm.q.goodsName,
                    'types': vm.q.types
                },
                page: page
            }).trigger("reloadGrid");
        }
    
    }
});