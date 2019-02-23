$(function () {
    $("#jqGrid").Grid({
        url: '../sys/user/list',
        colModel: [
            {label: '用户ID', name: 'userId', index: "user_id", key: true, hidden: true},
            {label: '用户名', name: 'username', width: 75},
            {label: '所属部门', name: 'deptName', width: 75},
            {label: '邮箱', name: 'email', width: 90},
            {label: '手机号', name: 'mobile', width: 100},
            {
                label: '状态', name: 'status', width: 80, formatter: function (value) {
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
            {
                label: '创建时间', name: 'createTime', index: "create_time", width: 80, formatter: function (value) {
                    return transDate(value);
                }
            }]
    });
});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null
        },
        showList: true,
        title: null,
        roleList: {},
        user: {
            status: 1,
            deptName: '',
            roleIdList: []
        },
        ruleValidate: {
            username: [
                {required: true, message: '姓名不能为空', trigger: 'blur'}
            ],
            email: [
                {required: true, message: '邮箱不能为空', trigger: 'blur'},
                {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
            ],
            mobile: [
                {required: true, message: '手机号不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增(默认密码：888888)";
            vm.roleList = {};
            vm.user = {status: 1, roleIdList: [], deptId: '', deptName: ''};

            //获取角色信息
            this.getRoleList();
            vm.getDept();
        },
        getDept: function () {
            //加载部门树
            Ajax.request({
                url: '../sys/dept/list',
                async: true,
                successCallback: function (r) {
                    ztree = $.fn.zTree.init($("#deptTree"), setting, r.list);
                    var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                    if (node != null) {
                        ztree.selectNode(node);

                        vm.user.deptName = node.name;
                    }
                }
            });
        },
        update: function () {
            var userId = getSelectedRow("#jqGrid");
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";

            Ajax.request({
                url: "../sys/user/info/" + userId,
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                    //获取角色信息
                    vm.getRoleList();
                    vm.getDept();
                }
            });

        },
        del: function () {
            var userIds = getSelectedRows("#jqGrid");
            if (userIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/user/delete",
                    params: JSON.stringify(userIds),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
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
        getRoleList: function () {
            Ajax.request({
                url: '../sys/role/select',
                async: true,
                successCallback: function (r) {
                    vm.roleList = r.list;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'username': vm.q.username},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        deptTree: function () {
            openWindow({
                title: "选择部门",
                area: ['300px', '450px'],
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
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