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
            {label: '市场价格(元)', name: 'marketPrice', index: 'market_price', width: 80},
            {label: '团购价格(元)', name: 'groupPrice', index: 'group_price', width: 80}
            ]
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
        specifications: [],
        type: '',
        goodsId:0,
        ggArr:[],
        isSecKill:1,
        params:[
            { param : [] , ggArr:[]},
            { param : [] , ggArr:[]},
            { param : [] , ggArr:[]}
        ]
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.product = {groupPrice:0};
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
            let model= opt.value
            vm.goodsId = model.id;
            if (vm.type == 'add') {
                vm.product.goodsSn = model.goodsSn;
                vm.product.goodsNumber = model.goodsNumber;
                vm.product.retailPrice = model.retailPrice;
                vm.product.marketPrice = model.marketPrice;
                vm.product.id=null;
            }
            if(!vm.goodsId)return;

            Ajax.request({
                url: "../specification/queryListByGoodsId?goodsId="+vm.goodsId,
                async: true,
                successCallback: function (r) {
                    vm.specifications= r.list;
                }
            });
            vm.isSecKill=model.isSecKill;
        },
        saveOrUpdate: function (event) {
            if(vm.attribute.length>2){
                alert('属性最多选择两项');
                return false;
            }
            let url = vm.product.id == null ? "../product/save" : "../product/update";
            if(vm.attribute.length<=1){
                vm.product.goodsSpecificationIds = vm.params[0].param+'_';
            }else if(vm.attribute.length<=2){
                vm.product.goodsSpecificationIds = vm.params[0].param + '_' + vm.params[1].param ;
            }else if(vm.attribute.length<=3){
                vm.product.goodsSpecificationIds = vm.params[0].param + '_' + vm.params[1].param + '_' + vm.params[2].param;
            }else{
                vm.product.goodsSpecificationIds='';
            }
            vm.product.goodsId=vm.goodsId;
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.product),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                        vm.product.goodsId=0;
                        vm.product.goodsSn = '';
                        vm.product.goodsNumber = 1;
                        vm.product.retailPrice = 1;
                        vm.product.marketPrice = 1;
                        vm.product.goodsSpecificationIds='';
                        vm.params=[
                            { param : [] , ggArr:[]},
                            { param : [] , ggArr:[]},
                            { param : [] , ggArr:[]}
                        ]
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
                    vm.getGoodss();
                    vm.product = r.product;
                    vm.goodsId=r.product.goodsId;
                    if(vm.product.goodsSpecificationIds.indexOf('_')>0){
                        let goodsSpecificationIds = vm.product.goodsSpecificationIds.split("_");
                        goodsSpecificationIds.forEach((goodsSpecificationId, index) => {
                            if(goodsSpecificationId.indexOf(',')>0){
                                let specificationIds = goodsSpecificationId.split(",").filter(id => !!id).map(id => Number(id));
                                if (index == 0) {
                                    vm.params[index].param = specificationIds;
                                    if (specificationIds.length > 0) {
                                        vm.attribute.push(1);
                                    }
                                } else if (index == 1) {
                                    vm.params[index].param = specificationIds;
                                    if (specificationIds.length > 0) {
                                        vm.attribute.push(2);
                                    }
                                } else if (index == 2) {
                                    vm.params[index].param = specificationIds;
                                    if (specificationIds.length > 0) {
                                        vm.attribute.push(4);
                                    }
                                }
                            }else {
                               vm.params[index].param=vm.product.goodsSpecificationId
                            }
                      });
                    } else{
                        vm.params[0].param=vm.product.goodsSpecificationIds
                    }
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
            
           
        },
        changeAttributes: function(){
            Ajax.request({
                url: "../goodsspecification/queryAll?goodsId=" + vm.goodsId + "&specificationId=" + vm.attribute[vm.attribute.length-1].id,
                async: true,
                successCallback: function (r) {
                    vm.params[vm.attribute.length-1].ggArr = r.list;
                }
            });
        }
    }
});