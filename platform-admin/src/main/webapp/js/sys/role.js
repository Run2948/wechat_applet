$(function () {
    $("#jqGrid").Grid({
        url: '../sys/role/list',
        colModel: [
            {label: '角色ID', name: 'roleId', index: "role_id", key: true, hidden: true},
            {label: '角色名称', name: 'roleName', index: "role_name", width: 75},
            {label: '所属部门', name: 'deptName', width: 75},
            {label: '备注', name: 'remark', width: 100},
            {
                label: '创建时间', name: 'createTime', index: "create_time", width: 80, formatter: function (value) {
                    return transDate(value);
                }
            }
        ]
    });
});

//菜单树
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};

//部门结构树
var dept_ztree;
var dept_setting = {
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

//数据树
var data_ztree;
var data_setting = {
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
    },
    check: {
        enable: true,
        nocheckInherit: true,
        chkboxType: {"Y": "", "N": ""}
    }
};


var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            roleName: null
        },
        showList: true,
        title: null,
        role: {deptId: '', deptName: ''},
        ruleValidate: {
            roleName: [
                {required: true, message: '角色名称不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.role = {deptId: '', deptName: ''};
            vm.getMenuTree(null);
            vm.getDept();
            vm.getDataTree();
        },
        update: function () {
            var roleId = getSelectedRow("#jqGrid");
            if (roleId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.getDataTree();
            vm.getMenuTree(roleId);
        },
        del: function (event) {
            var roleIds = getSelectedRows("#jqGrid");
            if (roleIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../sys/role/delete",
                    params: JSON.stringify(roleIds),
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
        getRole: function (roleId) {
            Ajax.request({
                url: "../sys/role/info/" + roleId,
                async: true,
                successCallback: function (r) {
                    vm.role = r.role;
                    vm.getDept();

                    //勾选角色所拥有的菜单
                    var menuIds = vm.role.menuIdList;
                    for (var i = 0; i < menuIds.length; i++) {
                        var node = menu_ztree.getNodeByParam("menuId", menuIds[i]);
                        menu_ztree.checkNode(node, true, false);
                    }

                    //勾选角色所拥有的部门数据权限
                    var deptIds = vm.role.deptIdList;
                    for (var i = 0; i < deptIds.length; i++) {
                        var node = data_ztree.getNodeByParam("deptId", deptIds[i]);
                        data_ztree.checkNode(node, true, false);
                    }
                }
            });
        },
        saveOrUpdate: function (event) {
            //获取选择的菜单
            var nodes = menu_ztree.getCheckedNodes(true);
            var menuIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            vm.role.menuIdList = menuIdList;

            //获取选择的数据
            var nodes = data_ztree.getCheckedNodes(true);
            var deptIdList = new Array();
            for (var i = 0; i < nodes.length; i++) {
                deptIdList.push(nodes[i].deptId);
            }
            vm.role.deptIdList = deptIdList;

            var url = vm.role.roleId == null ? "../sys/role/save" : "../sys/role/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.role),
                contentType: "application/json",
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        getMenuTree: function (roleId) {
            //加载菜单树
            Ajax.request({
                url: "../sys/menu/perms",
                async: true,
                successCallback: function (r) {
                    menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r.menuList);
                    //展开所有节点
                    menu_ztree.expandAll(true);

                    if (roleId != null) {
                        vm.getRole(roleId);
                    }
                }
            });
        },
        getDataTree: function (roleId) {
            //加载菜单树
            Ajax.request({
                url: "../sys/dept/list",
                async: true,
                successCallback: function (r) {
                    data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, r.list);
                    //展开所有节点
                    data_ztree.expandAll(true);
                }
            });
        },
        getDept: function () {
            //加载部门树
            Ajax.request({
                url: "../sys/dept/list",
                async: true,
                successCallback: function (r) {
                    dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r.list);
                    var node = dept_ztree.getNodeByParam("deptId", vm.role.deptId);
                    if (node != null) {
                        dept_ztree.selectNode(node);
                        vm.role.deptName = node.name;
                    }
                }
            });
        },
        deptTree: function () {
            openWindow({
                title: "选择部门",
                area: ['300px', '450px'],
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = dept_ztree.getSelectedNodes();
                    //选择上级部门
                    vm.role.deptId = node[0].deptId;
                    vm.role.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'roleName': vm.q.roleName},
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