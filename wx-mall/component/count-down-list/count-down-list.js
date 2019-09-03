// component/count-down-list/count-down-list.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    endTime:{
      type: Number,
      value: 0,
      observer: function(){
        this.countDown()
      }
    },
    ntype: {
      type: Number,
      value: 0
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    model:{
      day:'00',
      hou: '00',
      min: '00',
      sec: '00'
    },
    timedown:null
  },
  ready(){
    // this.countDown()
  },
  /**
   * 组件的方法列表
   */
  methods: {
    timeFormat(param) {//小于10的格式化函数
      return param < 10 ? '0' + param : param;
    },
    countDown() {//倒计时函数
      // 获取当前时间，同时得到活动结束时间数组
      let newTime = new Date().getTime();
      // 对结束时间进行处理渲染到页面
      let endTime = this.properties.endTime;
      let obj = null;
      // 如果活动未结束，对时间进行处理
      if (endTime - newTime > 0) {
        let time =(endTime - newTime) / 1000;
        // 获取天、时、分、秒
        let day = parseInt(time / (60 * 60 * 24));
        let hou = parseInt(time % (60 * 60 * 24) / 3600);
        let min = parseInt(time % (60 * 60 * 24) % 3600 / 60);
        let sec = parseInt(time % (60 * 60 * 24) % 3600 % 60);
        obj = {
          day: this.timeFormat(day),
          hou: this.timeFormat(hou),
          min: this.timeFormat(min),
          sec: this.timeFormat(sec)
        }
      } else {//活动已结束，全部设置为'00'
        obj = {
          day: '00',
          hou: '00',
          min: '00',
          sec: '00'
        }
        clearTimeout(this.data.timedown)
        var e = {
          success: true
        }
        this.triggerEvent('downEnd', e, '');
        return false;
      }
      // 渲染，然后每隔一秒执行一次倒计时函数
      this.setData({ model: obj })
      this.data.timedown = setTimeout(this.countDown.bind(this), 1000);
    }
  }
})
