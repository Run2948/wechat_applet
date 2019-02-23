$(function () {
    $("#jqGrid").Grid({
        url: '../user/list',
        colModel: [{
            label: '会员id', name: 'id', index: 'id', width: 30,key: true
        }, {
            label: '会员名称', name: 'username', index: 'username', width: 80
        }, {
            label: '会员密码', name: 'password', index: 'password', hidden: true
        }, {
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
            label: '是否实名认证', name: 'isReal', index: 'isReal', width: 60, formatter: function (value) {
                return value==2?"已认证":"未认证";
            }
        }, {
            label: '最后登录Ip', name: 'lastLoginIp', index: 'last_login_ip', hidden: true
        },
            {
                label: '真实姓名', name: 'realName', index: 'realName', width: 50
            }, {
            label: '手机号码', name: 'mobile', index: 'mobile', width: 80
        },{
            label: '推广人', name: 'promoterName', index: 'promoterName', width: 80
        },{
            label: '微信名', name: 'nickname', index: 'nickname', width: 80
        }, {
            label: '注册Ip', name: 'registerIp', index: 'register_ip', hidden: true
        }, {
            label: '头像', name: 'avatar', index: 'avatar', width: 80, formatter: function (value) {
                return transImg(value);
            }
        }, {
            label: '微信Id', name: 'weixinOpenid', index: 'weixin_openid', width: 80, hidden: true
        },
            {
                label: 'qrCode', name: 'qrCode', index: 'qrCode', width: 80, hidden: true
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        user: {
            gender: 1
        },
        ruleValidate: {
            username: [
                {required: true, message: '会员名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            username: ''
        },
        userLevels: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.user = {gender: '1'};
            vm.userLevels = [];

            this.getUserLevels();
        },
        download:function(){
            var id = getSelectedRow("#jqGrid");
            console.log('---------id--'+id);
            if (id == null) {
                return;
            }
            var curWwwPath=window.document.location.href;
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            //获取主机地址，如： http://localhost:8083
            var localhostPaht=curWwwPath.substring(0,pos);
            localhostPah= localhostPaht+"/statics/qrdir/";
            console.log('---------eeee--'+localhostPah);
            var x=new XMLHttpRequest();
            x.open("GET", localhostPah+id+".png", true);
            x.responseType = 'blob';
            x.onload=function(e){
                download(x.response, id+".png", "image/png" );
            };
            x.send();
           },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
            this.getUserLevels();
        },
        saveOrUpdate: function (event) {
            var url = vm.user.id == null ? "../user/save" : "../user/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.user),
                successCallback: function (r) {
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
                    url: "../user/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });

            });
        },
        exportUser: function () {
            exportFile('#rrapp', '../user/export', {'username': vm.q.username});
        },
        coupon: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '优惠券',
                type: 2,
                content: '../shop/usercoupon.html?userId=' + id
            })
        },
        address: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '收获地址',
                type: 2,
                content: '../shop/address.html?userId=' + id
            })
        },
        customer: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '客户信息',
                type: 2,
                content: '../shop/customer.html?userId=' + id
            })
        },
        //推广信息
        promoterList: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '推广信息',
                type: 2,
                content: '../shop/promoter.html?promoterId=' + id
            })
        },
        //推广人变更
        updatePromoter: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '推广人变更',
                type: 2,
                content: '../shop/promoterUpdate.html?promoterId=' + id
            })
        },
        shopCart: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '购物车',
                type: 2,
                content: '../shop/cart.html?userId=' + id
            })
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../user/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                }
            });
        },
        /**
         * 获取会员级别
         */
        getUserLevels: function () {
            Ajax.request({
                url: "../userlevel/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.userLevels = r.list;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'username': vm.q.username},
                page: page,
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