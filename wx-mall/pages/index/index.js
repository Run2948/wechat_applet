const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');

//获取应用实例
const app = getApp()
Page({
  data: {
    page: 1,
    size: 6,
    loadmoreText: '正在加载更多数据',
    nomoreText: '全部加载完成',
    nomore: false,
    totalPages: 1,
    newGoods: [],
    hotGoods: [],
    topics: [],
    brands: [],
    floorGoods: [],
    banner: [],
    channel: [],
    holiday:{
      holiday_name:'',
      holiday:''
    },
    birthday:'',
    user:{
      uname:'',
      gender:0,
      birthday:'',
      mobile:'',
      job:''
    },
    isUserDiv:false,  
  },
  onShareAppMessage: function () {
    return {
      title: '51商城',
      desc: '51商城',
      path: '/pages/index/index'
    }
  },
  /**
   * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () { 
    this.getIndexData(); 
  }, 
  tzCusPage: function (event){ 
    const gid=event.currentTarget.dataset.gid
    let token = wx.getStorageSync('token'); 
    if (token){
      if (gid==1){
        wx.navigateTo({
          url: '../customer/cuslist/cuslist',
        })
      } else if (gid == 2){
        wx.navigateTo({
          url: '../customer/zcuslist/zcuslist',
        })
      } else if (gid == 3) { 
        this.initUserInfo();
        this.initUserJR();
      } else if (gid == 4) {
        wx.navigateTo({
          url: '../customer/whlist/whlist',
        })
      }
      
    } else{
      wx.showModal({
        title: '',
        content: '请先登录',
        success: function (res) {
          if (res.confirm) {
            wx.removeStorageSync("userInfo");
            wx.removeStorageSync("token"); 
            wx.switchTab({
              url: '/pages/ucenter/index/index'
            });
          }
        }
      });
    }
  },
  onPullDownRefresh(){
	  	// 增加下拉刷新数据的功能
      wx.showNavigationBarLoading();
	    var self = this;
      self.setData({ 
        hotGoods:[],
        page: 1,
        totalPages: 1
      });
      self.getIndexData();
  },
  getIndexData: function () {
    let that = this;
    var data = new Object(); 
    if (that.data.totalPages <= that.data.page - 1) {
      that.setData({
        nomore: true
      })
      return;
    }
    util.request(api.ServiceGoods, { page: that.data.page, size: that.data.size}).then(function (res) {
      if (res.errno === 0) { 
        that.setData({
          hotGoods: that.data.hotGoods.concat(res.data.data),
          page: res.data.currentPage + 1,
          totalPages: res.data.totalPages
        });
        wx.hideLoading();
      } 
      wx.hideNavigationBarLoading() //完成停止加载
      wx.stopPullDownRefresh() //停止下拉刷新
    });
  }, 
  getBannerList:function(){
    let that = this;
    var data = new Object();
    util.request(api.IndexUrlBanner).then(function (res) {
      if (res.errno === 0) {
        data.banner = res.data.banner
        that.setData(data);
      }
    });
  },
  getTodsyList:function(){
    let that = this;
    var data = new Object();
    util.request(api.IndexUrlNewGoods).then(function (res) {
      if (res.errno === 0) {
        data.newGoods = res.data.newGoodsList
        that.setData(data);
      }
    });
  },
  getBandList:function(){
    let that = this;
    var data = new Object();
    util.request(api.IndexUrlBrand).then(function (res) {
      if (res.errno === 0) {
        data.brands = res.data.brandList
        that.setData(data);
      }
    });
  },
  onLoad: function (options) {   
    if (options.id){
      wx.setStorageSync('userId', options.id); 
    } 
    this.getBannerList();
    this.getTodsyList();
    this.getBandList();
    this.getIndexData();
  },
  onReady: function () {
    // 页面渲染完成 
  },
  onShow: function () {
    // 页面显示  
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
})
