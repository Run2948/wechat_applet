$(function () {
    $("#jqGrid").Grid({
        url: '../user/list',
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
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
            label: '最后登录时间', name: 'lastLoginTime', index: 'last_login_time', width: 80, formatter: function (value) {
                return transDate(value);
            }
        }, {
            label: '最后登录Ip', name: 'lastLoginIp', index: 'last_login_ip', hidden: true
        }, {
            label: '会员等级', name: 'levelName', width: 40
        }, {
            label: '微信名', name: 'nickname', index: 'nickname', width: 80
        }, {
            label: '手机号码', name: 'mobile', index: 'mobile', width: 120
        }, {
            label: '注册Ip', name: 'registerIp', index: 'register_ip', hidden: true
        }, {
            label: '头像', name: 'avatar', index: 'avatar', width: 80, formatter: function (value) {
                return transImg(value);
            }
        }, {
            label: '微信Id', name: 'weixinOpenid', index: 'weixin_openid', width: 80, hidden: true
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