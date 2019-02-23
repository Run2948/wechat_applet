$(function () {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function () {
        TreeGrid.table.resetHeight({height: $(window).height() - 100});
    });
}

function getGrid() {
    var colunms = TreeGrid.initColumn();
    var table = new TreeTable(TreeGrid.id, '../category/queryAll', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}

var TreeGrid = {
    id: "jqGrid",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', align: 'id', width: '50px'},
        {title: '分类名称', field: 'name', align: 'center', valign: 'middle', width: '100px'},
        {title: '描述', field: 'frontDesc', align: 'center', valign: 'middle', width: '150px'},
        {title: '首页展示', field: 'showIndex', align: 'center', valign: 'middle', width: '50px'},
        {
            title: '显示',
            field: 'isShow',
            align: 'center',
            valign: 'middle',
            width: '50px',
            formatter: function (item, index) {
                return transIsNot(item.show)
            }
        },
        // {title: '类型', field: 'type', align: 'center', valign: 'middle', width: '50px'},
        {title: '级别', field: 'level', align: 'center', valign: 'middle', width: '50px'}]
    return columns;
};

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        category: {isShow: 1, type: 0, level: 'L1', bannerUrl: '', iconUrl: '', imgUrl: '', wapBannerUrl: ''},
        ruleValidate: {
            name: [
                {required: true, message: '分类名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        categoryList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.category = {isShow: 1, type: 0, level: 'L1', bannerUrl: '', iconUrl: '', imgUrl: '', wapBannerUrl: ''};
            this.getParentCategory();
        },
        update: function (event) {
            var id = TreeGrid.table.getSelectedRow();
            if (id.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id[0].id);
            this.getParentCategory();
        },
        getParentCategory: function () {
            Ajax.request({
                url: "../category/getCategorySelect",
                async: true,
                successCallback: function (r) {
                    vm.categoryList = r.list;
                }
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.category.id == null ? "../category/save" : "../category/update";
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.category),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var id = TreeGrid.table.getSelectedRow(), ids = [];
            if (id.length == 0) {
                iview.Message.error("请选择一条记录");
                return;
            }
            $.each(id, function (idx, item) {
                ids[idx] = item.id;
            });

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../category/delete",
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
                url: "../category/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.category = r.category;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            TreeGrid.table.refresh();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccessBannerUrl: function (res, file) {
            vm.category.bannerUrl = file.response.url;
        },
        eyeImageBannerUrl: function () {
            var url = vm.category.bannerUrl;
            eyeImage(url);
        },
        handleSuccessIconUrl: function (res, file) {
            vm.category.iconUrl = file.response.url;
        },
        eyeImageIconUrl: function () {
            var url = vm.category.iconUrl;
            eyeImage(url);
        },
        handleSuccessImgUrl: function (res, file) {
            vm.category.imgUrl = file.response.url;
        },
        eyeImageImgUrl: function () {
            var url = vm.category.imgUrl;
            eyeImage(url);
        },
        handleSuccessWapBannerUrl: function (res, file) {
            vm.category.wapBannerUrl = file.response.url;
        },
        eyeImageWapBannerUrl: function () {
            var url = vm.category.wapBannerUrl;
            eyeImage(url);
        }
    }
});