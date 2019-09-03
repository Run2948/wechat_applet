var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');



var app = getApp();

Page({
  data: {
    couponList: null,
    buyType: ''
  },
  onLoad: function (options) {
    this.data.buyType = options.buyType
    // this.loadListData()
    var merchantId = options.merid;
    var goodsTotalPrice = options.price
    this.validCouponList(merchantId, goodsTotalPrice)
  },

  // loadListData: function () {
  //   let that = this;
  //   util.request(api.GoodsCouponList, { type: this.data.buyType }).then(function (res) {
  //     if (res.errno === 0) {
  //       that.setData({
  //         couponList: res.data
  //       });
  //     }
  //   });
  // }, 
  validCouponList: function (merchantId, goodsTotalPrice) {
    let that = this;
    util.request(api.ValidCouponList, { merchantId: merchantId, goodsTotalPrice: goodsTotalPrice }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          couponList: res.data
        });
      }
    });
  },

  /**
   * 点击不使用优惠券
   * 返回上一页
   */
  noUseCoupon: function () {
    app.globalData.userCoupon = 'NO_USE_COUPON'
    wx.navigateBack({
    })
  },

  tapCoupon: function (event) {
    let item = event.currentTarget.dataset.item
    if (item.coupon_status!=1) {
      return
    }
    app.globalData.userCoupon = 'USE_COUPON'
    app.globalData.courseCouponCode = item
    wx.navigateBack({
    })
  }
})