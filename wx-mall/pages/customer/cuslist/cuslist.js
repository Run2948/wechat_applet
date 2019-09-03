var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
  data: { 
    orderList: [],
    array: ['请选择类型','客户', '准客户'],
    index: 0,
    page: 1,
    size: 6,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1,
    q: {
      uname: '',
      customerState: 0,
      upkeepState:0
    }, 
    customerCount:0,
    customerYcount:0,
    customerWcount:0 
  },
  onLoad: function (options) { 
    // wx.showLoading({
    //   title: '加载中...',
    //   success: function () {}
    // });
    // this.getOrderList();
  },
  /**
   * 页面上拉触底事件的处理函数
  */
  selKhId:function(){
    let q = this.data.q;
    q.customerState = 1;
    q.upkeepState = 0;
    this.setData({
      q: q
    });
    this.onKeywordConfirm();
  },
  selWhId: function () {
    let q = this.data.q;
    q.upkeepState = 1;
    q.customerState = 0;
    this.setData({
      q: q
    });
    this.onKeywordConfirm();
  },
  selWhId1: function () {
    let q = this.data.q;
    q.upkeepState = 2;
    q.customerState = 0;
    this.setData({
      q: q
    });
    this.onKeywordConfirm();
  },
  onReachBottom: function () {
    console.log("下一页")
    this.getOrderList()
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    wx.showNavigationBarLoading();
    var self = this;
    self.setData({
      orderList: [],
      page: 1,
      totalPages: 1
    });
    self.getOrderList();
  },
  inputChange(event) {
    let q = this.data.q;
    q.uname = event.detail.value;
    this.setData({
      q: q
    });
  }, 
  clearKeyword(){
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
      orderList: [],
      page:1,
      totalPages:1
    })
    this.getOrderList();
  },
  getCount(){
    const that=this;
    util.request(api.GetCount, { customerState:1}, 'POST').then(function (res) {
      if (res.errno === 0) { 
        that.setData({
          customerCount: res.data.customerCount,
          customerWcount: res.data.customerWcount,
          customerYcount: res.data.customerYcount
        });
      } 
    });
  },
  getOrderList() {
    let that = this; 
    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }
    util.request(api.CustomerList, { currentPage: that.data.page, numsPerPage: that.data.size, uname: that.data.q.uname, customerState:1, upkeepState: that.data.q.upkeepState },'POST').then(function (res) {
      if (res.errno === 0) {  
        for (var i = 0; i < res.data.data.length;i++){ 
          if (res.data.data[i].addressVo.detailInfo.length>8){
            res.data.data[i].addressVo.detailInfo = res.data.data[i].addressVo.detailInfo.substring(0,8)+"...";
          } 
        }
        that.setData({
          orderList: that.data.orderList.concat(res.data.data),
          page: res.data.currentPage+1,
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
      orderList:[], 
      page:1
    });
    wx.showLoading({
      title: '加载中...',
      success: function () { }
    });
    this.getOrderList();
    this.getCount();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})