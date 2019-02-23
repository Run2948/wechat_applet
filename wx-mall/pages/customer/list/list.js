// pages/customer/whlist/whlist.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data: {
    whList: [],
    page: 1,
    size: 13,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1,
    customerId:0
  },
  onLoad: function (options) {
    this.setData({
      customerId: options.id
    });
    wx.showLoading({
      title: '加载中...',
      success: function () { }
    });
    // this.getWhList();
  }, 
  /**
   * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () {
    console.log("下一页")
    this.getWhList();
  }, 
  getWhList() {
    let that = this;
    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }
    util.request(api.QueryList, { currentPage: that.data.page, numsPerPage: that.data.size, customerId: that.data.customerId }, 'POST').then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          whList: that.data.whList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        }); 
        wx.hideLoading();
      }
    });
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function (options) {
    console.log('-------fff', this.data.customerId)
    this.setData({
      whList: [],
      page:1, 
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