var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    typeId: 0,
    collectList: []
  },
  getCollectList() {
    let that = this;
    util.request(api.GetShareGoods, {}).then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          collectList: res.data
        });
      }
    });
  },
  onLoad: function (options) {

  },
  onReady: function () {

  },
  onShow: function () {
    this.getCollectList();
  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  openGoods(event) {

    let that = this;
    let goodsId = this.data.collectList[event.currentTarget.dataset.index].goodsId;
    console.log(goodsId); 
    wx.navigateTo({
      url: '/pages/goods/goods?id=' + goodsId,
    });
  },
  //按下事件开始  
  touchStart: function (e) {
    let that = this;
    that.setData({
      touch_start: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-start')
  },
  //按下事件结束  
  touchEnd: function (e) {
    let that = this;
    that.setData({
      touch_end: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-end')
  },
})