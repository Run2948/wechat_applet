// pages/customer/whlist/whlist.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data: {
    whList: [],
  },
  onLoad: function (options) {
  }, 
  /**
   * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () {
    console.log("下一页")
    this.getWhList();
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    wx.showNavigationBarLoading();
    var self = this;
    self.getWhList();
  },
  getWhList() {
    let that = this; 
    util.request(api.FansList, {}, 'POST').then(function (res) {
      if (res.errno === 0) {
        that.setData({
          whList: res.data,
        });
        wx.hideLoading();
      }
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    });
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    wx.showLoading({
      title: '加载中...',
      success: function () { }
    });
    this.getWhList();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})