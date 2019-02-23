(function ($) {

    /*********************************对jQuery类型原型扩展********************************************/
    jQuery.extend($.prototype, {
        Grid: function (options) {
            //分页Id
            var pager = this.attr('id') + 'Pager';
            this.after('<div id="' + pager + '"></div>');

            this.defaults = {
                width: 1000,
                styleUI: 'Bootstrap',
                datatype: "json",
                viewrecords: true,
                height: 385,
                rowNum: 10,
                rowList: [10, 30, 50],
                rownumbers: true,
                rownumWidth: 25,
                autowidth: true,
                multiselect: true,
                jsonReader: {
                    root: "page.list",
                    page: "page.currPage",
                    total: "page.totalPage",
                    records: "page.totalCount"
                },
                prmNames: {
                    page: "page",
                    rows: "limit",
                    order: "order"
                },
                pager: "#" + pager
            };
            var param = $.extend(this.defaults, options);
            this.jqGrid(param);
        }
    });

    /*********************************对Date类型原型扩展********************************************/
    jQuery.extend(Date.prototype, {
        /**
         * 格式化日期 默认格式：'yyyy-MM-dd hh:mm:ss'
         * @param fmt
         * @returns {*}
         */
        dateFormat: function (fmt) {
            if (!fmt) {
                fmt = 'yyyy-MM-dd hh:mm:ss';
            }
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    });

    /*********************************对String类型原型扩展********************************************/
    /**
     * 功能描述:对String 类型原型扩展
     *
     * @author 李鹏军
     */
    jQuery.extend(String.prototype, {

        /**
         * String转Date(兼容IE)
         *
         * eg:"2017-3-24 08:30:30".toDate();
         *
         * @returns {Date}
         */
        toDate: function () {
            return new Date(this.replace("-", "/").replace("-", "/"));
        },
        /**
         * 在字符串末尾追加字符串
         *
         * @param {String} str 要追加的字符串
         *
         * @returns {string}
         */
        append: function (str) {
            return this.concat(str);
        },

        /**
         * Truncate a string and add an ellipsis ('...') to the end if it
         * exceeds the specified length.
         *
         * eg:'这是一个测试案例'.ellipsis(7); return "这是一个..."
         *
         * @param {Number} length The maximum length to allow before
         *            truncating.
         * @param {Boolean} [word=false] 'true' to try to find a common word
         *            break.
         * @return {String} The converted text.
         */
        ellipsis: function (length, word) {
            var value = this;
            if (value && value.length > length) {
                if (word) {
                    var vs = value.substr(0, length - 2),
                        index = Math.max(vs.lastIndexOf(' '), vs.lastIndexOf('.'), vs.lastIndexOf('!'), vs.lastIndexOf('?'));
                    if (index !== -1 && index >= (length - 15)) {
                        return vs.substr(0, index) + "...";
                    }
                }
                return value.substr(0, length - 3) + "...";
            }
            return value;
        },

        /**
         * 判断字符串是否以指定内容结尾
         *
         * @returns {boolean}
         */
        endsWith: function () {
            return this.substr(this.length - arguments[0].length) == arguments[0];
        },

        /**
         * 比较两个字符串是否相等，不区分大小写
         *
         * @param {String} str
         *
         * @returns {boolean}
         */
        equalIgnoreCase: function (str) {
            var temp1 = this.toLowerCase();
            var temp2 = str.toLowerCase();
            return temp1.equal(temp2);
        },

        /**
         * 插入字符串，索引无效将直接追加到字符串的末尾
         *
         * @param {String} value 插入的值
         * @param {Number} index 插入的索引
         *
         * @returns {String}
         */
        insert: function (value, index) {
            var s = this;
            if (!s) {
                return value;
            }

            if (!value) {
                return s;
            }

            var len = s.length;

            if (!index && index !== 0) {
                index = len;
            }

            if (index < 0) {
                index *= -1;
                if (index >= len) {
                    // negative overflow, insert at start
                    index = 0;
                } else {
                    index = len - index;
                }
            }

            if (index === 0) {
                s = value + s;
            } else if (index >= s.length) {
                s += value;
            } else {
                s = s.substr(0, index) + value + s.substr(index);
            }
            return s;
        },

        /**
         * 判断字符串是否为Null或者为空字符串。
         *
         * @returns {boolean}
         */
        isNullOrEmpty: function () {
            return this === undefined || this === null || this == "" || this == "null";
        },

        /**
         * 判断字符串是否为Null或者为空字符串或者全是空格。
         *
         * @returns {boolean}
         */
        isEmpty: function () {
            return this.isNullOrEmpty() || this.trim().isNullOrEmpty();
        },

        /**
         * 判断字符串是否包含指定的内容。
         *
         * @param {String} val 指定内容
         *
         * @returns {boolean}
         */
        isContains: function (val) {
            return this.indexOf(val) > -1;
        },

        /**
         * 从左截取指定长度的字串
         *
         * @param {Number} n
         *
         * @returns {String}
         */
        left: function (n) {
            return this.slice(0, n);
        },

        /**
         * 左填充 eg:"12".leftPad(5, '0'); return "00012"
         *
         * @param {Number} size 整个字符串长度
         * @param {String} character 要填充的字符串
         *
         * @returns {string}
         */
        leftPad: function (size, character) {
            var result = String(this);
            character = character || " ";
            while (result.length < size) {
                result = character + result;
            }
            return result;
        },

        /**
         * 去除字符串左边的空格。
         *
         * @returns {*|XML|string|void}
         */
        ltrim: function () {
            return this.replace(/(^\s*)/g, "");
        },

        /**
         * Returns a string with a specified number of repetitions a given
         * string pattern. The pattern be separated by a different string.
         *
         * '---'.repeat(4); // = '------------' '--'.repeat(3, '/'); // =
         * '--/--/--'
         *
         * @param {Number} count The number of times to repeat the pattern
         *            (may be 0).
         * @param {String} sep An option string to separate each pattern.
         */
        repeat: function (count, sep) {
            var pattern = this;
            if (count < 1) {
                count = 0;
            }
            for (var buf = [], i = count; i--;) {
                buf.push(pattern);
            }
            return buf.join(sep || '');
        },

        /**
         * 逆序
         *
         * @returns {string}
         */
        reverse: function () {
            return this.split("").reverse().join("");
        },

        /**
         * 从右截取指定长度的字串
         *
         * @param {Number} n
         *
         * @returns {String}
         */
        right: function (n) {
            return this.slice(this.length - n);
        },

        /**
         * 去除字符串右边的空格。
         *
         * @returns {*|XML|string|void}
         */
        rtrim: function () {
            return this.replace(/(\s*$)/g, "");
        },

        /**
         * 判断字符串是否以指定内容开头
         *
         * @returns {boolean}
         */
        startsWith: function () {
            return this.substr(0, arguments[0].length) == arguments[0];
        },

        /**
         * Utility function that allows you to easily switch a string
         * between two alternating values. The passed value is compared to
         * the current string, and if they are equal, the other value that
         * was passed in is returned. If they are already different, the
         * first value passed in is returned. Note that this method returns
         * the new value but does not change the current string. //
         * alternate sort directions sort.toggle('ASC', 'DESC'); // instead
         * of conditional logic: sort = (sort === 'ASC' ? 'DESC' : 'ASC');
         *
         * @param {String} string The current string.
         * @param {String} value The value to compare to the current string.
         * @param {String} other The new value to use if the string already
         *            equals the first value passed in.
         * @return {String} The new value.
         */
        toggle: function (value, other) {
            return this.toString() === value ? other : value;
        },

        /**
         * 除去两边空白
         *
         * @returns {*|XML|string|void}
         */
        trim: function () {
            return this.replace(/(^\s*)|(\s*$)/g, "");
        },

        /**
         * 计算字符串长度(英文占1个字符，中文汉字占2个字符)
         *
         * @return {int} 字符串所占字符数。如："你abc" 返回结果就为 5
         */
        toChar: function () {
            var len = 0;
            for (var i = 0; i < this.length; i++) {
                if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
                    len += 2;
                } else {
                    len++;
                }
            }
            return len;
        }
    });

    /**
     * 功能描述:对Array 类型原型扩展
     *
     * @author 李鹏军
     */
    jQuery.extend(Array.prototype, {

        /**
         * 插入一条记录
         *
         * @param item
         */
        add: function (item) {
            this.push(item);
        },

        /**
         * 插入数组
         *
         * @param items
         */
        addRange: function (items) {
            var length = items.length;

            if (length != 0) {
                for (var index = 0; index < length; index++) {
                    this.push(items[index]);
                }
            }
        },

        /**
         * 消空数组元素.
         */
        clear: function () {
            if (this.length > 0) {
                this.splice(0, this.length);
            }
        },

        /**
         * 删除无效的元素(null/"")
         */
        removeVoidElement: function () {
            for (var i = 0; i < this.length; i++) {
                if ("" == this[i] || null == this[i] || "null" == this[i]) {
                    this.remove(this[i]);
                    i--;// 移除一个对象，数组长度减1
                }
            }
        },
        /**
         * 判断是否为空
         *
         * @returns {boolean}
         */
        isEmpty: function () {
            if (this.length == 0) {
                return true;
            } else {
                return false;
            }
        },

        /**
         * 克隆数组对象
         *
         * @returns {Array}
         */
        clone: function () {
            var clonedArray = [];
            var length = this.length;

            for (var index = 0; index < length; index++) {
                clonedArray[index] = this[index];
            }

            return clonedArray;
        },

        /**
         * 搜索指定的Object,并返回第一个匹配项从零开始的索引
         *
         * @param item 要查找的Object对象
         *
         * @returns {number} 找到返回该元素在数组中的索引,否则返回-1
         */
        indexOf: function (item) {
            var length = this.length;

            if (length != 0) {
                for (var index = 0; index < length; index++) {
                    if (this[index] == item) {
                        return index;
                    }
                }
            }

            return -1;
        },

        /**
         * 插入数组元素
         *
         * @param index 插入元素的下标.
         * @param item
         */
        insert: function (index, item) {
            this.splice(index, 0, item);
        },

        /**
         * 确定某个元素是否在数组中.
         *
         * @param item 要查找的Object对象
         *
         * @returns {boolean} 找到返回true,否则返回false;
         */
        contains: function (item) {
            var index = this.indexOf(item);
            return (index >= 0);
        },

        /**
         * 删除数组元素.
         *
         * @param {Object} item 要删除的对象
         */
        remove: function (item) {
            var index = this.indexOf(item);

            if (index >= 0) {
                this.splice(index, 1);
            }
        },

        /**
         * 替换数组中的reg为rpby 注意本方法不改变给定的数组本身。
         *
         * @param reg 要替换的元素
         * @param rpby 替换后的元素
         *
         * @returns {string[]}
         */
        replace: function (reg, rpby) {
            var ta = this.slice(0), d = '\0';
            var str = ta.join(d);
            str = str.replace(reg, rpby);
            return str.split(d);
        },

        /**
         * 删除数组元素.
         *
         * @param {Number} index 删除元素的下标.
         */
        removeAt: function (index) {
            this.splice(index, 1);
        },

        /**
         * 数字数组由小到大排序
         *
         * @returns {jQuery}
         */
        min2Max: function () {
            var oValue;
            for (var i = 0; i < this.length; i++) {
                for (var j = 0; j <= i; j++) {
                    if (this[i] < this[j]) {
                        oValue = this[i];
                        this[i] = this[j];
                        this[j] = oValue;
                    }
                }
            }
            return this;
        },

        /**
         * 数字数组由大到小排序
         *
         * @returns {jQuery}
         */
        max2Min: function () {
            var oValue;
            for (var i = 0; i < this.length; i++) {
                for (var j = 0; j <= i; j++) {
                    if (this[i] > this[j]) {
                        oValue = this[i];
                        this[i] = this[j];
                        this[j] = oValue;
                    }
                }
            }
            return this;
        },

        /**
         * 获得数字数组中最大项
         *
         * @returns {number}
         */
        getMax: function () {
            var oValue = 0;

            if (this.length > 0) {
                oValue = this[0];
            }

            for (var i = 0; i < this.length; i++) {
                if (this[i] > oValue) {
                    oValue = this[i];
                }
            }
            return oValue;
        },

        /**
         * 获得数字数组中最小项
         *
         * @returns {number}
         */
        getMin: function () {
            var oValue = 0;

            if (this.length > 0) {
                oValue = this[0];
            }

            for (var i = 0; i < this.length; i++) {
                if (this[i] < oValue) {
                    oValue = this[i];
                }
            }
            return oValue;
        }
    });
})(jQuery);