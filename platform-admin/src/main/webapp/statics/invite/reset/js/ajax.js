/**
      ajax的工具文件
 */

var ajax = {
    /**
     *  ajax请求，请求之后的处理
     * @param param
     * @constructor
     */
    Ajax: function(param) {
        $.ajax({
            url: param.url + "?v=" + $.now(),
            type: typeof(param.type) == "undefined" ? "GET" : "POST",
            data: param.param,
            contentType: "application/x-www-form-urlencoded",
            dateType: "json",
            async: param.async === false ? false : true,
            success: function(data) {
                err.closeloading();
                if (data == null) {
                    data = {};
                }
                if (typeof data == "string") {
                    data = JSON.parse(data);
                }
                if (typeof(param.success) == 'function') {
                    if (data.errno == 0) {
                        param.success(data, param.param);
                    } else {
                        // utils.btnDisabled('btncomplet_new', 1);
                        err.errordiv(data.errmsg);
                    }

                } else {
                    // utils.btnDisabled('btncomplet_new', 1);
                    err.errordiv(data.errmsg);
                }
            },
            error: function(data) {
                err.closeloading();
                // utils.btnDisabled('btncomplet_new', 1);
                err.errordiv(data.errmsg);
            }
        });
    },
    Ajax_N: function(param) {
        $.ajax({
            url: param.url + "?v=" + $.now(),
            type: typeof(param.type) == "undefined" ? "GET" : "POST",
            data: param.param,
            contentType: "application/x-www-form-urlencoded",
            dateType: "json",
            async: param.async === false ? false : true,
            beforeSend: function() {
                //异步请求时spinner出现
                err.loading();
            },
            success: function(data) {
                err.closeloading();
                if (data == null) {
                    data = {};
                }
                if (typeof data == "string") {
                    data = JSON.parse(data);
                }
                if (typeof(param.success) == 'function') {
                    if (data.errorCode == "USER_000009") {
                        window.location.href = "http://file.nbcip.com/404page.html";
                    }
                    param.success(data, param.param);
                } else {
                    // utils.btnDisabled('btncomplet_new', 1);
                    err.errordiv(data.message);
                }
            },
            error: function(data) {
                err.closeloading();
                // utils.btnDisabled('btncomplet_new', 1);
                err.errordiv(data.message);
            }
        });
    },
    Ajax_NB: function(param) {
        $.ajax({
            url: param.url + "?v=" + $.now(),
            type: typeof(param.type) == "undefined" ? "GET" : "POST",
            data: param.param,
            contentType: "application/x-www-form-urlencoded",
            dateType: "json",
            async: param.async === false ? false : true,
            beforeSend: function() {
                param.beforeSend();
            },
            success: function(data) {
                if (data == null) {
                    data = {};
                }
                if (typeof data == "string") {
                    data = JSON.parse(data);
                }
                if (typeof(param.success) == 'function') {
                    if (data.errorCode == "USER_000009") {
                        window.location.href = "http://file.nbcip.com/404page.html";
                    }
                    param.success(data, param.param);
                } else {
                    utils.btnDisabled('btncomplet_new', 1);
                    err.errordiv(data.message);
                }
            },
            error: function(data) {
                utils.btnDisabled('btncomplet_new', 1);
                err.errordiv(data.message);
            }
        });
    }
}
