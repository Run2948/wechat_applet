$(function () {
    let goodsId = getQueryString("goodsId");
    let url = '../product/list';
    if (goodsId) {
        url += '?goodsId=' + goodsId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商品', name: 'goodsName', index: 'goods_id', width: 120},
            {
                label: '商品规格',
                name: 'specificationValue',
                index: 'goods_specification_ids',
                width: 100,
                formatter: function (value, options, row) {
                    return value.replace(row.goodsName + " ", '');
                }
            },
            {label: '商品序列号', name: 'goodsSn', index: 'goods_sn', width: 80},
            {label: '商品库存', name: 'goodsNumber', index: 'goods_number', width: 80},
            {label: '零售价格(元)', name: 'retailPrice', index: 'retail_price', width: 80},
            {label: '市场价格(元)', name: 'marketPrice', index: 'market_price', width: 80}]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        product: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            goodsName: ''
        },
        goodss: [],
        attribute: [],
        color: [], guige: [], weight: [],
        colors: [],
        guiges: [],
        weights: [],
        type: ''
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.product = {};
            vm.getGoodss();
            vm.type = 'add';
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.type = 'update';

            vm.getInfo(id)
        },
        changeGoods: function (opt) {
            let goodsId = opt.value;
            if(!goodsId)return;

            Ajax.request({
                url: "../goods/info/" + goodsId,
                async: true,
                successCallback: function (r) {
                    if (vm.type == 'add') {
                        vm.product.goodsSn = r.goods.goodsSn;
                        vm.product.goodsNumber = r.goods.goodsNumber;
                        vm.product.retailPrice = r.goods.retailPrice;
                        vm.product.marketPrice = r.goods.marketPrice;
                    }
                    Ajax.request({
                        url: "../goodsspecification/queryAll?goodsId=" + goodsId + "&specificationId=1",
                        async: true,
                        successCallback: function (r) {
                            vm.colors = r.list;
                        }
                    });
                    Ajax.request({
                        url: "../goodsspecification/queryAll?goodsId=" + goodsId + "&specificationId=2",
                        async: true,
                        successCallback: function (r) {
                            vm.guiges = r.list;
                        }
                    });
                    Ajax.request({
                        url: "../goodsspecification/queryAll?goodsId=" + goodsId + "&specificationId=4",
                        async: true,
                        successCallback: function (r) {
                            vm.weights = r.list;
                        }
                    });
                }
            });
        },
        saveOrUpdate: function (event) {
            let url = vm.product.id == null ? "../product/save" : "../product/update";

            if(vm.attribute.indexOf(1) == -1)vm.color = [];
            if(vm.attribute.indexOf(2) == -1)vm.guige = [];
            if(vm.attribute.indexOf(4) == -1)vm.weight = [];
            vm.product.goodsSpecificationIds = vm.color + '_' + vm.guige + '_' + vm.weight;

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.product),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });


        },
        del: function (event) {
            let ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../product/delete",
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
            vm.attribute = [];
            Ajax.request({
                url: "../product/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.product = r.product;
                    let goodsSpecificationIds = vm.product.goodsSpecificationIds.split("_");
                    goodsSpecificationIds.forEach((goodsSpecificationId, index) => {
                        let specificationIds = goodsSpecificationId.split(",").filter(id => !!id).map(id => Number(id));

                        if (index == 0) {
                            vm.color = specificationIds;
                            if (specificationIds.length > 0) {
                                vm.attribute.push(1);
                            }
                        } else if (index == 1) {
                            vm.guige = specificationIds;
                            if (specificationIds.length > 0) {
                                vm.attribute.push(2);
                            }
                        } else if (index == 2) {
                            vm.weight = specificationIds;
                            if (specificationIds.length > 0) {
                                vm.attribute.push(4);
                            }
                        }
                    });

                    vm.getGoodss();
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'goodsName': vm.q.goodsName},
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