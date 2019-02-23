$(function () {
   var  promoterId = getQueryString("promoterId");

});


var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            uname: ''
          //  telNumber: ''
        },
        user: {
            mobile:'',
            id:0
        },
        title: null,
        ruleValidate: {
            mobile: [
                {required: true, message: '电话号码不能为空', trigger: 'blur'}
            ]
        },
    },
    created:function() {
        console.log('---------eeee--');
        var promoterId = getQueryString("promoterId");
        console.log('--------'+promoterId);
        this.user.id=promoterId;
        console.log('--------'+this.user.id);
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            var  promoterId = getQueryString("promoterId");
            vm.showList = true;

        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.updatePromoter()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        updatePromoter: function (event) {
            confirm('确定要替换推广人？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../user/updatePromoter",
                    contentType: "application/json",
                    params: JSON.stringify(vm.user),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        }
    }
});
