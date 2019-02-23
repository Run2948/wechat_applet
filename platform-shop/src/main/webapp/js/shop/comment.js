$(function () {
    let status = getQueryString("status");
    let url = '../comment/list';
    if (status) {
        url += '?status=' + status;
    }
    debugger
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '类型', name: 'typeId', index: 'type_id', width: 80},
            {label: '商品', name: 'valueName', index: 'value_id', width: 80},
            {label: '评价', name: 'content', index: 'content', width: 80},
            {
                label: '评论时间', name: 'addTime', index: 'add_time', width: 80, formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value) {
                    if (value === 0) {
                        return '<span class="label label-success">显示</span>';
                    }
                    return '<span class="label label-danger">隐藏</span>';
                }
            },
            {label: '会员', name: 'userName', index: 'user_id', width: 80}]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        comment: {id: ''},
        q: {
            userName: '',
            valueName: '',
            picUrl: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        toggleStatus: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.comment.id = id;

            confirm('确定要切换状态？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../comment/toggleStatus",
                    contentType: "application/json",
                    params: JSON.stringify(vm.comment),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
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
                    url: "../comment/delete",
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
        seePic: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            Ajax.request({
                url: "../commentpicture/queryAll?commentId=" + id,
                async: true,
                successCallback: function (r) {
                    var data = [];
                    for (var i = 0; i < r.list.length; i++) {
                        var picUrl = r.list[i].picUrl;
                        data.push({"src": picUrl});
                    }
                    eyeImages(data);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'userName': vm.q.userName, 'valueName': vm.q.valueName, 'picUrl': vm.q.picUrl},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        }
    }
});