/**
      ajax的工具文件
 */

var index = { 
    wait: 60,
    boltrue: true, //后台报错标示
    inviteCode:0,
    init:function(){
        index.inviteCode=index.getLocationParam('mlsUserId');
        //index.inviteCode=1;
    },
    getCode: function() {
        var mobile=$(".mobile").val()
        ajax.Ajax({
            url: "../../../api/sendRegisterCode",
            type: 'POST',
            param: { mobile: mobile},
            success: function(data, param) {
                console.log("---------")
            },
            error: function() {}
        });
    },
    resetSubmit:function(){
        const param={}
        param.mobile = $(".mobile").val();
        param.captcha = $(".code").val();
        param.inviteCode = index.inviteCode;
        if(!param.mobile.match(/^1[3-9][0-9]\d{8}$/)){
            err.errordiv("请输入有效的手机号");
            return false;
        }
        if(param.captcha==""){
            err.errordiv("请输入验证码");
            return false;
        }
        ajax.Ajax({
            url: "../../../api/inviteReg",
            type: 'POST',
            param: param,
            success: function(data, param) {
                err.errordiv("您已注册成功，请前往下载中心下载");
                setTimeout(function(){
                 window.location.href="https://www.pgyer.com/lalage166"
                },1000)
            },
            error: function() {}
        });
    },
    dtime: function(o, str) {
        var mobile=$(".mobile").val()
        if(!mobile.match(/^1[3-9][0-9]\d{8}$/)){
            err.errordiv("请输入有效的手机号");
            return false;
        }
        if (index.wait == 60) {
            $(o).css('background', '#969696');
           if (str == 1) {
                $('.mobile').attr('readonly', 'readonly');
                index.getCode();
            }

        }
        if (index.wait == 0) {
            index.boltrue = true;
            $(o).css('background', '#E96900');
            o.removeAttribute("disabled");
            o.value = "获取验证码";
            index.wait = 60;
            $('.mobile').removeAttr('readonly');
        } else if (index.boltrue) {
            o.setAttribute("disabled", true);
            o.value = "重新发送(" + index.wait + ")";
            index.wait--;
            setTimeout(function() {
                    index.dtime(o);
                },
                1000);
        } else if (!index.boltrue) {
            index.boltrue = true;
            $(o).css('background', '#E96900');
            o.removeAttribute("disabled");
            o.value = "获取验证码";
            index.wait = 60;
        }
    },
    codeLeave: function(str) {
        var validDate = $(str).val();
        str.value = str.value.replace(/[^\d]/g, '');
    },
    //获取页面参数
    getLocationParam: function(strParame) {
        var args = new Object();
        var query = location.search.substring(1);
        var pairs = query.split("&"); // Break at ampersand
        for (var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }
        return args[strParame];
    }
}
$(function() {
    index.init();
});
