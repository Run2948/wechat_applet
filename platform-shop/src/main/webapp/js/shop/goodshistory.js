$(function () {
    $("#jqGrid").Grid({
        url: '../goods/historyList',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商品类型', name: 'categoryName', index: 'category_id', width: 80},
            {label: '商品序列号', name: 'goodsSn', index: 'goods_sn', width: 80, hidden: true},
            {label: '名称', name: 'name', index: 'name', width: 160},
            {label: '品牌', name: 'brandName', index: 'brand_id', width: 120},
            {label: '关键字', name: 'keywords', index: 'keywords', width: 80, hidden: true},
            {label: '简明介绍', name: 'goodsBrief', index: 'goods_brief', width: 80, hidden: true},
            {label: '商品描述', name: 'goodsDesc', index: 'goods_desc', width: 80, hidden: true},
            {
                label: '上架', name: 'isOnSale', index: 'is_on_sale', width: 50,
                formatter: function (value) {
                    return transIsNot(value);
                }
            },
            {
                label: '添加时间', name: 'addTime', index: 'add_time', width: 80, formatter: function (value) {
                    return transDate(value);
                }
            },
            {label: '删除状态', name: 'isDelete', index: 'is_delete', width: 80, hidden: true},
            {label: '属性类别', name: 'attributeCategoryName', index: 'attribute_category', width: 80},
            {label: '是否新商品', name: 'isNew', index: 'is_new', width: 80, hidden: true},
            {label: '商品单位', name: 'goodsUnit', index: 'goods_unit', width: 80},
            {label: '商品主图', name: 'primaryPicUrl', index: 'primary_pic_url', width: 80, hidden: true},
            {label: '商品列表图', name: 'listPicUrl', index: 'list_pic_url', width: 80, hidden: true},
            {label: '零售价格', name: 'retailPrice', index: 'retail_price', width: 80},
            {label: '商品库存', name: 'goodsNumber', index: 'goods_number', width: 80},
            {label: '销售量', name: 'sellVolume', index: 'sell_volume', width: 80},
            {label: '主product_id', name: 'primaryProductId', index: 'primary_product_id', width: 80, hidden: true},
            {label: '单价', name: 'unitPrice', index: 'unit_price', width: 80},
            {label: '推广描述', name: 'promotionDesc', index: 'promotion_desc', width: 80, hidden: true},
            {label: '推广标签', name: 'promotionTag', index: 'promotion_tag', width: 80, hidden: true},
            {
                label: '限购', name: 'isLimited', index: 'is_limited', width: 80, formatter: function (value) {
                    return transIsNot(value);
                }
            },
            {
                label: '热销', name: 'isHot', index: 'is_hot', width: 80, formatter: function (value) {
                    return transIsNot(value);
                }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        back: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要恢复选中的记录？', function () {

                Ajax.request({
                    type: "POST",
                    url: "../goods/back",
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
                url: "../goods/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.goods = r.goods;
                    $('#goodsDesc').editable('setHTML', vm.goods.goodsDesc);
                    vm.getCategory();
                    vm.getAttributes(vm.goods.attributeCategory);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});