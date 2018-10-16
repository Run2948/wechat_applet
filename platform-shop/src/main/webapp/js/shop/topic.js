$(function () {
    $("#jqGrid").Grid({
        url: '../topic/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '活动主题', name: 'title', index: 'title', width: 80},
            {label: '活动内容', name: 'content', index: 'content', width: 80, hidden: true},
            {
                label: '图像', name: 'avatar', index: 'avatar', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {
                label: '活动条例图片', name: 'itemPicUrl', index: 'item_pic_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {label: '子标题', name: 'subtitle', index: 'subtitle', width: 80},
            {label: '活动类别', name: 'topicCategoryId', index: 'topic_category_id', width: 80},
            {label: '活动价格', name: 'priceInfo', index: 'price_info', width: 80},
            {label: 'readCount', name: 'readCount', index: 'read_count', width: 80},
            {
                label: '场景图片', name: 'scenePicUrl', index: 'scene_pic_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {label: '活动模板Id', name: 'topicTemplateId', index: 'topic_template_id', width: 80},
            {label: '活动标签Id', name: 'topicTagId', index: 'topic_tag_id', width: 80}]
    });
    $('#content').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: 'auto', //高度
        language: "zh_cn",
        minHeight: '200px',
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',//上传到本地服务器
        imageUploadParams: {id: "edit"},
        // imageManagerDeleteURL: '../sys/oss/delete',//删除图片(有问题)
        imagesLoadURL: '../sys/oss/queryAll'//管理图片
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        topic: {avatar: '', itemPicUrl: '', scenePicUrl: ''},
        ruleValidate: {
            title: [
                {required: true, message: '活动主题不能为空', trigger: 'blur'}
            ]
        },
        q: {
            title: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.topic = {avatar: '', itemPicUrl: '', scenePicUrl: ''};
            $('#content').editable('setHTML', '');
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
            var url = vm.topic.id == null ? "../topic/save" : "../topic/update";

            //编辑器内容
            vm.topic.content = $('#content').editable('getHTML');
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.topic),
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
                    url: "../topic/delete",
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
                url: "../topic/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.topic = r.topic;
                    $('#content').editable('setHTML', vm.topic.content);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'title': vm.q.title},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        handleSuccessAvatar: function (res, file) {
            vm.topic.avatar = file.response.url;
        },
        handleSuccessItemPicUrl: function (res, file) {
            vm.topic.itemPicUrl = file.response.url;
        },
        handleSuccessScenePicUrl: function (res, file) {
            vm.topic.scenePicUrl = file.response.url;
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
        eyeImageAvatar: function () {
            var url = vm.topic.avatar;
            eyeImage(url);
        },
        eyeImageItemPicUrl: function () {
            var url = vm.topic.itemPicUrl;
            eyeImage(url);
        },
        eyeImageScenePicUrl: function () {
            var url = vm.topic.scenePicUrl;
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