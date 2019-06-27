$(function () {
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        visible: false,
        switch1: false,
        data1: '',
        loading: false,
        loading2: false,
        formValidate: {
            name: '',
            mail: '',
            city: '',
            gender: '',
            interest: [],
            date: '',
            time: '',
            desc: ''
        },
        ruleValidate: {
            name: [
                {required: true, message: '姓名不能为空', trigger: 'blur'}
            ],
            mail: [
                {required: true, message: '邮箱不能为空', trigger: 'blur'},
                {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
            ],
            city: [
                {required: true, message: '请选择城市', trigger: 'change'}
            ],
            gender: [
                {required: true, message: '请选择性别', trigger: 'change'}
            ],
            interest: [
                {required: true, type: 'array', min: 1, message: '至少选择一个爱好', trigger: 'change'},
                {type: 'array', max: 2, message: '最多选择两个爱好', trigger: 'change'}
            ],
            date: [
                {required: true, type: 'date', message: '请选择日期', trigger: 'change'}
            ],
            time: [
                {required: true, type: 'date', message: '请选择时间', trigger: 'change'}
            ],
            desc: [
                {required: true, message: '请输入个人介绍', trigger: 'blur'},
                {type: 'string', min: 20, message: '介绍不能少于20字', trigger: 'blur'}
            ]
        },
        value1: 25,
        value2: [20, 50],
        value3: [20, 50],
        value4: 30,
        value5: [20, 50],
        value6: 30,
        value7: [20, 50],
        value8: 25,
        value9: 25,
        value10: 25,
        value11: ['beijing', 'gugong'],
        data: [{
            value: 'beijing',
            label: '北京',
            children: [
                {
                    value: 'gugong',
                    label: '故宫'
                },
                {
                    value: 'tiantan',
                    label: '天坛'
                },
                {
                    value: 'wangfujing',
                    label: '王府井'
                }
            ]
        }, {
            value: 'jiangsu',
            label: '江苏',
            children: [
                {
                    value: 'nanjing',
                    label: '南京',
                    children: [
                        {
                            value: 'fuzimiao',
                            label: '夫子庙',
                        }
                    ]
                },
                {
                    value: 'suzhou',
                    label: '苏州',
                    children: [
                        {
                            value: 'zhuozhengyuan',
                            label: '拙政园',
                        },
                        {
                            value: 'shizilin',
                            label: '狮子林',
                        }
                    ]
                }
            ],
        }],
        cityList: [
            {
                value: 'beijing',
                label: '北京市'
            },
            {
                value: 'shanghai',
                label: '上海市'
            },
            {
                value: 'shenzhen',
                label: '深圳市'
            },
            {
                value: 'hangzhou',
                label: '杭州市'
            },
            {
                value: 'nanjing',
                label: '南京市'
            },
            {
                value: 'chongqing',
                label: '重庆市'
            }
        ],
        model2: '',
        model3: '',
        model4: ''
    },
    methods: {
        show: function () {
            this.visible = true;
        },
        change: function (status) {
            iview.Message.info('开关状态：' + status);
        },
        toLoading: function () {
            this.loading = true;
        },
        toLoading2: function () {
            this.loading = true;
        },
        handleSubmit: function (name) {
            this.$refs[name].validate(function (valid) {
                if (valid) {
                    iview.Message.success('提交成功!');
                } else {
                    iview.Message.error('表单验证失败!');
                }
            })
        },
        handleReset: function (name) {
            this.$refs[name].resetFields();
        },
        format: function (val) {
            return '进度' + val + '%';
        },
        hideFormat: function () {
            return null;
        },
        render1: function (item) {
            return item.label;
        },
        handleChange1: function (newTargetKeys, direction, moveKeys) {
            console.log(newTargetKeys);
            console.log(direction);
            console.log(moveKeys);
            this.targetKeys1 = newTargetKeys;
        },
        api: function () {
            openWindow({
                type: 2,
                title: "iView Api",
                area: ['80%', '500px'],
                content: ['https://www.iviewui.com/docs/guide/install']
            });
        }
    }
});