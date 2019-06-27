$(function () {
    $("#jqGrid").Grid({
        url: '../mlsuser/list',
        colModel: [
            {label: '用户ID', name: 'mlsUserId', index: "MLS_USER_ID", key: true, hidden: true},
            {label: '手机号', name: 'userTel', width: 75},
            {label: '一级比例%', name: 'fx1', width: 75},
            {label: '二级比例%', name: 'fx2', width: 75}
           ]
    });
});



var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null
        },
        showList: true,
        title: null,     
        user: {
//            status: 1,
//            deptName: '',
//            roleIdList: []
        },
        ruleValidate: {
        	fx1: [
                {required: true, message: '一级比例不能为空', trigger: 'blur'}
            ],
            fx2: [
                {required: true, message: '二级比例不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        update: function () {
            var userId = getSelectedRow("#jqGrid");
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            Ajax.request({
                url: "../mlsuser/info/" + userId,
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                    //获取角色信息
                    vm.getRoleList();
                    vm.getDept();
                }
            });

        },
        
        saveOrUpdate: function (event) {
            var url = vm.user.mlsUserId == null ? "../mlsuser/save" : "../mlsuser/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.user),
                contentType: "application/json",
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'userTel': vm.q.userTel},
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
        }
    }
});