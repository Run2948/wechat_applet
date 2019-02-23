$(function () {
    let userId = getQueryString("userId");
    let url = '../customer/list';
    if (userId) {
        url += '?uid=' + userId;
    }
    $("#jqGrid").Grid({
        url: '../customer/list'+'?uid=' + userId,
        colModel: [{
            label: 'id', name: 'id', index: 'id', key: true, hidden: true
        }, {
            label: '客户姓名', name: 'uname', index: 'uname', width: 80
        }, {
            label: '性别', name: 'gender', index: 'gender', width: 40, formatter: function (value) {
                return transGender(value);
            }
        }, {
            label: '出生日期', name: 'birthday', index: 'birthday', width: 80, formatter: function (value) {
                return transDate(value,"yyyy-MM-dd");
            }
        } , {
            label: '手机号码', name: 'mobile', index: 'mobile', width: 80
        }, {
            label: '职务', name: 'job', index: 'job', width: 40
        },
            {
            label: '维护状态', name: 'upkeepState', index: 'upkeepState',width: 50,formatter: function (value) {
                return transUpkeepState(value);
            }
        }, {
            label: '客户属性', name: 'customerState', index: 'customerState', width: 80,formatter: function (value) {
                return transCustomerState(value);
            }

        },
            {
                label: '地址', name: 'addressEntity', index: 'addressEntity',formatter: function (value) {
                    return value.provinceName+value.cityName+value.countyName+value.detailInfo;
                }
                },
            {
            label: '备注', name: 'remarks', index: 'remarks', width: 80
        }]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title:null,
        user: {
            gender: 1,
            addressEntity:{
            	proId:0,
                provinceName:'',
                cityName:'',
                countyName:''
            }
        },
        ruleValidate: {
            uname: [
                {required: true, message: '会员名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            uname: ''
          //  telNumber: ''
        },
        provinceNames:[],
        selProId:0,
        selCityProId:0,
        cityNames:[],
        selCityId:0,
        countyNames:[],
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'uname': vm.q.uname

                },
                page: page
            }).trigger("reloadGrid");
        },
        upkeepList: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title: '客户信息',
                type: 2,
                content: '../shop/upkeep.html?customerId=' + id
            })
        },
        //修改客户信息
        updateCustomer: function () {
            var id = getSelectedRow("#jqGrid");
                if (id == null) {
                    return;
                }
                vm.showList = false;
                vm.title = "修改客户信息";
                vm.getInfo(id);
        },
        saveOrUpdate: function (event) {
            //var url = vm.user.id == null ? "../customer/save" : "../customer/update";
            var url = "../customer/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.user),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../customer/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                }
            });
            vm.selProId = vm.user.addressEntity.provinceName;
            vm.selCityProId = vm.user.addressEntity.cityName;
            console.log('vm.selProId-------',vm.selProId);
            console.log('vm.selCityProId-------',vm.selCityProId);
            console.log('addressEntity-------',vm.user.addressEntity);
            this.getProvinceNames();
            this.getCityNames();
            this.getCountyNames();
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../customer/delete",
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
        /**
         * 获取省列表
         */
        getProvinceNames: function () {
            Ajax.request({
                url: "../sys/region/getAllProvice?areaId="+1,
                async: true,
                successCallback: function (r) {
                    vm.provinceNames = r.list;
                }
            });
        },
        /**
         * 选择省操作调用方法
         */
        proNameChange: function(val){
        	console.log('-------val',val);
        	if(val == null || val == ""){
        		console.log('proNameChange-------val',val);
        		return;
        	}
            vm.selProId = val;
            vm.getCityNames();
        },
        /**
         * 获取市列表
         */
        getCityNames: function () {
        	if(vm.selProId == null || vm.selProId == ""){
        		console.log('getCityNames-------vm.selProId',vm.selProId);
        		return;
        	}
            Ajax.request({
                url: "../sys/region/getAllCityByName?areaName="+vm.selProId,
                async: true,
                successCallback: function (r) {
                    vm.cityNames = r.list;
                }
            });
        },
        /**
         * 选择市操作调用方法
         */
        proNameCityChange: function(val){
        	console.log('-------val2',val);
        	if(val == null || val == ""){
        		console.log('proNameCityChange-------val',val);
        		return;
        	}
            vm.selCityProId = val;
            vm.getCountyNames();
        },
        /**
         * 获取区列表
         */
        getCountyNames: function () {
        	if(vm.selCityProId == null || vm.selCityProId == ""){
        		console.log('getCountyNames-------vm.selCityProId',vm.selCityProId);
        		return;
        	}
            Ajax.request({
                url: "../sys/region/getChildrenDistrictByName?areaName="+vm.selCityProId,
                async: true,
                successCallback: function (r) {
                    vm.countyNames = r.list;
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
