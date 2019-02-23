$(function () {
    $("#jqGrid").Grid({
        url: '../ad/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '广告位置', name: 'adPositionName', index: 'ad_Position_id', width: 80},
            {label: '形式', name: 'mediaType', index: 'media_type', width: 80},
            {label: '广告名称', name: 'name', index: 'name', width: 80},
            {label: '链接', name: 'link', index: 'link', width: 80},
            {
                label: '图片', name: 'imageUrl', index: 'image_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {label: '内容', name: 'content', index: 'content', width: 80},
            {
                label: '结束时间', name: 'endTime', index: 'end_time', width: 80, formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '状态', name: 'enabled', index: 'enabled', width: 80, formatter: function (value) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        ad: {enabled: 1, imageUrl: '', mediaType: 0},
        ruleValidate: {
            name: [
                {required: true, message: '广告名称不能为空', trigger: 'blur'}
            ],
            imageUrl: [
                {required: true, message: '图片不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        adPositions: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.ad = {enabled: 1, imageUrl: '', mediaType: 0};
            vm.adPosition = [];
            this.getAdPositions();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            this.getAdPositions();
        },
        saveOrUpdate: function (event) {
            var url = vm.ad.id == null ? "../ad/save" : "../ad/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.ad),
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
                    url: "../ad/delete",
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
                url: "../ad/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.ad = r.ad;
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
        handleSuccess: function (res, file) {
            vm.ad.imageUrl = file.response.url;
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
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        eyeImage: function () {
            var url = vm.ad.imageUrl;
            eyeImage(url);
        },
        /**
         * 获取会员级别
         */
        getAdPositions: function () {
            Ajax.request({
                url: "../adposition/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.adPositions = r.list;
                }
            });
        }
    }
});