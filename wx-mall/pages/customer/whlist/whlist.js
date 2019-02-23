// pages/customer/whlist/whlist.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data: {
    whList: [],
    array: ['选择类型','客户','准客户'],
    index:0,
    page: 1,
    size: 8,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1,
    q:{
      uname:'',
      customerState:0
    }
  },
  onLoad: function (options) {
    // wx.showLoading({
    //   title: '加载中...',
    //   success: function () { }
    // });
    // this.getWhList();
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
    self.setData({
      whList:[],
      page: 1,
      totalPages: 1
    });
    self.getWhList();
  },
  selKhId: function () {
    console.log(11111)
    let q = this.data.q;
    q.customerState = 1; 
    this.setData({
      q: q
    }); 
    this.onKeywordConfirm();
  },
  selKhId1: function () {
    let q = this.data.q;
    q.customerState = 2;
    this.setData({
      q: q
    });
    this.onKeywordConfirm();
  },
  inputChange(event) {
    let q = this.data.q;
    q.uname = event.detail.value;
    this.setData({
      q: q
    });
  },
  clearKeyword() {
    let q = this.data.q;
    q.uname = '';
    this.setData({
      q: q
    });
  },
  bindPickerChange(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  onKeywordConfirm() {
    this.setData({
      whList: [],
      page: 1,
      totalPages: 1
    }) 
    this.getWhList();
  },
  tzPage(e){ 
    wx.navigateTo({
      url: '../addwh/addwh?id=' + e.currentTarget.dataset.khid + '&state=' + e.currentTarget.dataset.state
    })
  },
  getWhList() {
    let that = this;  
    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }
    console.log(33333)
    util.request(api.UpkeepList, { currentPage: that.data.page, numsPerPage: that.data.size, uname: that.data.q.uname, customerState: that.data.q.customerState }, 'POST').then(function (res) {
      console.log(44444)
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          whList: that.data.whList.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
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
    this.setData({
      whList: [],
      page: 1
    });
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