$(function () {
    $("#jqGrid").Grid({
        url: '../coupon/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '优惠券名称', name: 'name', index: 'name', width: 120},
            {label: '金额', name: 'typeMoney', index: 'type_money', width: 80},
            {
                label: '发放方式', name: 'sendType', index: 'send_type', width: 80, formatter: function (value) {
                    if (value == 0) {
                        return '按订单发放';
                    } else if (value == 1) {
                        return '按用户发放';
                    } else if (value == 2) {
                        return '商品转发送券';
                    } else if (value == 3) {
                        return '按商品发放';
                    } else if (value == 4) {
                        return '新用户注册';
                    } else if (value == 5) {
                        return '线下发放';
                    } else if (value == 7) {
                        return '包邮优惠';
                    }
                    else if (value == 8) {
                        return '店铺满减';
                    }
                    return '-';
                }
            },
            {label: '最小金额', name: 'minAmount', index: 'min_amount', width: 80},
            {label: '最大金额', name: 'maxAmount', index: 'max_amount', width: 80},
            {
                label: '发放开始时间',
                name: 'sendStartDate',
                index: 'send_start_date',
                width: 120,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '发放结束时间', name: 'sendEndDate', index: 'send_end_date', width: 120, formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '使用开始时间',
                name: 'useStartDate',
                index: 'use_start_date',
                width: 120,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '使用结束时间', name: 'useEndDate', index: 'use_end_date', width: 120, formatter: function (value) {
                    return transDate(value);
                }
            },
            {label: '最小商品金额', name: 'minGoodsAmount', index: 'min_goods_amount', width: 80},
            {label: '发放数量', name: 'totalCount', index: 'totalCount', width: 80},
            {
                label: '操作', width: 70, sortable: false, formatter: function (value, col, row) {
                    if (row.sendType == 1 || row.sendType == 3) {
                        return '<button class="ivu-btn ivu-btn-primary ivu-btn-circle ivu-btn-small" onclick="vm.publish(' + row.id + ',' + row.sendType + ')"><i class="ivu-icon ivu-icon-android-send"></i>发放</button>';
                    }
                    return '';
                }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        showCard: false,
        showGoods: false,
        title: null,
        coupon: {sendType: 0},
        ruleValidate: {
            name: [
                {required: true, message: '优惠券名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        goods: [],
        goodss: [],
        user: [],
        users: [],
        selectData: {},
        sendSms: ''//是否发送短信
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.showCard = true;
            vm.showGoods = false;
            vm.title = "新增";
            vm.coupon = {sendType: 0};
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.showCard = true;
            vm.showGoods = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.coupon.id == null ? "../coupon/save" : "../coupon/update";
            if(vm.coupon.sendStartDate>=vm.coupon.sendEndDate){
                alert("发放开始时间不能大于等于发放结束时间")
                return false;
            }
            if(vm.coupon.useStartDate>=vm.coupon.useEndDate){
                alert("使用开始时间不能大于等于使用结束时间")
                return false;
            }
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.coupon),
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
                    url: "../coupon/delete",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../coupon/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.coupon = r.coupon;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.showCard = false;
            vm.showGoods = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
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
        },
        publish: function (id, sendType) {
            vm.showGoods = true;
            vm.goods = [];
            vm.user = [];
            vm.getGoodss();
            vm.getUsers();
            vm.selectData = {id: id, sendType: sendType};
            vm.sendSms = false;
            openWindow({
                title: "发放",
                area: ['600px', '350px'],
                content: jQuery("#sendDiv")
            })
        },
        getUsers: function () {
            Ajax.request({
                url: "../user/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.users = r.list;
                }
            });
        },
        publishSubmit: function () {

            var sendType = vm.selectData.sendType;
            if (sendType == 1 && vm.user.length == 0) {
                vm.$Message.error('请选择下发会员');
                return;
            }
            if (sendType == 3 && vm.goods.length == 0) {
                vm.$Message.error('请选择下发商品');
                return;
            }
            confirm('确定下发优惠券？', function () {
                Ajax.request({
                    type: "POST",
                    dataType: 'json',
                    url: "../coupon/publish",
                    contentType: "application/json",
                    params: JSON.stringify({
                        sendType: vm.selectData.sendType,
                        couponId: vm.selectData.id,
                        goodsIds: vm.goods.toString(),
                        userIds: vm.user.toString(),
                        sendSms: vm.sendSms
                    }),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                            vm.showGoods = false;
                            vm.showList = true;
                        });
                    }
                });
            });
        },
        getGoodss: function () {
            Ajax.request({
                url: "../goods/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.goodss = r.list;
                }
            });
        }
    }
});