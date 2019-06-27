$(function () {
    $("#jqGrid").Grid({
        url: '../brand/list',
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: '品牌名称', name: 'name', index: 'name', width: 80
        },  {
            label: '描述', name: 'simpleDesc', index: 'simple_desc', width: 80
        },  {
            label: '排序', name: 'sortOrder', index: 'sort_order', width: 80
        }, {
            label: '显示', name: 'isShow', index: 'is_show', width: 80, formatter: function (value) {
                return transIsNot(value)
            }
        }, {
            label: '展示价格', name: 'floorPrice', index: 'floor_Price', width: 80
        }, {
            label: '主图片', name: 'appListPicUrl', index: 'app_list_pic_url', width: 80, formatter: function (value) {
                return transImg(value);
            }
        }, {
            label: '新品牌', name: 'isNew', index: 'is_new', width: 80, formatter: function (value) {
                return transIsNot(value)
            }
        }, {
            label: '新品牌图片', name: 'newPicUrl', index: 'new_pic_url', width: 80, formatter: function (value) {
                return transImg(value);
            }
        }, {
            label: '新品牌排序', name: 'newSortOrder', index: 'new_sort_order', width: 80
        }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        brand: {listPicUrl: '', picUrl: '', appListPicUrl: '', newPicUrl: '', isShow: 1, isNew: 0, newSortOrder:1, sortOrder:1, floorPrice:''},
        ruleValidate: {
            name: [
                {required: true, message: '品牌名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.brand = {listPicUrl: '', picUrl: '', appListPicUrl: '', newPicUrl: '', isShow: 1, isNew: 0, newSortOrder:1, sortOrder:1, floorPrice:''};
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.brand.id == null ? "../brand/save" : "../brand/update";
            console.log(JSON.stringify(vm.brand));
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.brand),
                successCallback: function () {
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
                    url: "../brand/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../brand/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.brand = r.brand;
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
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.brand.listPicUrl = file.response.url;
        },
        handleSuccessPicUrl: function (res, file) {
            vm.brand.picUrl = file.response.url;
        },
        handleSuccessAppListPicUrl: function (res, file) {
            vm.brand.appListPicUrl = file.response.url;
        },
        handleSuccessNewPicUrl: function (res, file) {
            vm.brand.newPicUrl = file.response.url;
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
        eyeImageListPicUrl: function () {
            var url = vm.brand.listPicUrl;
            eyeImage(url);
        },
        eyeImagePicUrl: function () {
            var url = vm.brand.picUrl;
            eyeImage(url);
        },
        eyeImageAppListPicUrl: function () {
            var url = vm.brand.appListPicUrl;
            eyeImage(url);
        },
        eyeImageNewPicUrl: function () {
            var url = vm.brand.newPicUrl;
            eyeImage(url);
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