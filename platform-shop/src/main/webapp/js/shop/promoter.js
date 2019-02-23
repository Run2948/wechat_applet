$(function () {
    let promoterId = getQueryString("promoterId");
    let show_div = getQueryString("show_div");
    let url = '../user/promoterList';
    if (promoterId) {
         url += '?customer=' + promoterId;
    }
    // if(show_div=='1'){
    //     alert(show_div);
    //     vm.showList = true;
    // }
    // if(show_div=='2'){
    //     alert(show_div);
    //     vm.showList = false;
    // }

    $("#jqGrid").Grid({
        url: '../user/promoterList'+'?promoterId=' + promoterId,
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: '会员名称', name: 'username', index: 'username', width: 80
        },  {
            label: '性别', name: 'gender', index: 'gender', width: 40, formatter: function (value) {
                return transGender(value);
            }
        }, {
            label: '出生日期', name: 'birthday', index: 'birthday', width: 80, formatter: function (value) {
                return transDate(value);
            }
        }, {
            label: '注册时间', name: 'registerTime', index: 'register_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
        }, {
            label: '手机号码', name: 'mobile', index: 'mobile', width: 80
        }, {
            label: '购买金额', name: 'firstBuyMoney', index: 'firstBuyMoney', width: 80, formatter: function (value) {
                return value==null?'0.00':value;
            }
        }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            uname: ''
          //  telNumber: ''
        },
        user: {
            mobile:''
        },
        title: null,
        ruleValidate: {
            mobile: [
                {required: true, message: '电话号码不能为空', trigger: 'blur'}
            ]
        },
    },
    reload: function (event) {
        var show_div = getQueryString("show_div");
        if(show_div=='1'){
            vm.showList = true;
        }
        if(show_div=='2'){
            vm.showList = false;
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
                postData: {},
                page: page
            }).trigger("reloadGrid");
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.updatePromoter()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },

        updatePromoter: function (event) {
            confirm('确定要替换推广人？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../user/updatePromoter",
                    contentType: "application/json",
                    params: JSON.stringify(vm.user),
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
