var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();

Page({
  data: {
    addressList: [],
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    this.getAddressList();
  },
  getAddressList (){
    let that = this;
    util.request(api.AddressList).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          addressList: res.data
        });
      }
    });
  },
  addressAddOrUpdate (event) {
    console.log(event)
    wx.navigateTo({
      url: '/pages/shopping/addressAdd/addressAdd?id=' + event.currentTarget.dataset.addressId
    });
  },
  selectAddress(event) {
    var addressId = event.currentTarget.dataset.addressId;
    //选择该收货地址
    wx.navigateTo({
      url: '/pages/shopping/checkout/checkout?addressId=' + addressId + '&isBuy=buy'
    });
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})