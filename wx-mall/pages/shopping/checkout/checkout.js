var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
const pay = require('../../../services/pay.js');

var app = getApp();

Page({
  data: {
    checkedGoodsList: [],
    checkedAddress: {},
    checkedCoupon: [],
    couponList: [],
    goodsTotalPrice: 0.00, //商品总价
    freightPrice: 0.00,    //快递费
    couponPrice: 0.00,     //优惠券的价格
    orderTotalPrice: 0.00,  //订单总价
    actualPrice: 0.00,     //实际需要支付的总价
    addressId: 0,
    couponId: 0,
    isBuy: false,
    couponDesc: '',
    couponCode: '',
    buyType: '',
    couponIdArr:[],
    item:{},
    type:null,
    groupBuyingId:'',
    activityType:1
  },
  onLoad: function (options) { 
    // 页面初始化 options为页面跳转所带来的参数
    if (options.isBuy) {
      this.setData({
        isBuy: options.isBuy
      })
    }
    if (options.addressId){
      this.setData({
        addressId: options.addressId
      })
    }
    if (options.type){
      this.setData({
        type: options.type
      })
    }
    if (options.groupBuyingId){
      this.setData({
        groupBuyingId: options.groupBuyingId
      })
    }
    if (options.activityType){
      this.setData({
        activityType: options.activityType
      })
    }
    this.data.buyType = this.data.isBuy ?'detailBuy':'cart'
    //每次重新加载界面，清空数据
    app.globalData.userCoupon = 'NO_USE_COUPON'
    app.globalData.courseCouponCode = {}
    
  },

  switch1Change(e) {
    console.log('switch1 发生 change 事件，携带值为', e.detail.value)
  },
  
  getCheckoutInfo: function () {
    let that = this;
    var url = api.CartCheckout
    let buyType = this.data.isBuy ? 'detailBuy' : 'cart'
    var sumPrice=0
    util.request(url, { addressId: that.data.addressId, couponId: that.data.couponId, type: buyType, activityType: that.data.activityType }).then(function (res) {
      if (res.errno === 0) {
        for (var i = 0; i < res.data.checkedGoodsList.length;i++){
          var param = res.data.checkedGoodsList[i];
          for (var j = 0; j < that.data.couponIdArr.length;j++){
            if (param.merchantId == that.data.couponIdArr[j].merchantId) {
              param.couponName = that.data.couponIdArr[j].name;
              param.couponPrice = that.data.couponIdArr[j].type_money;
              sumPrice += that.data.couponIdArr[j].type_money
            }
          }
        }
        that.setData({
          checkedGoodsList: res.data.checkedGoodsList,
          checkedAddress: res.data.checkedAddress,
          actualPrice: util.accSub(res.data.actualPrice,sumPrice),
          couponPrice: res.data.couponPrice,
          freightPrice: res.data.freightPrice,
          goodsTotalPrice: res.data.goodsTotalPrice,
          orderTotalPrice: res.data.orderTotalPrice
        });
        //设置默认收获地址
        if (that.data.checkedAddress){
            let addressId = that.data.checkedAddress.id;
            if (addressId) {
                that.setData({ addressId: addressId });
            }
        }else{
            wx.showModal({
                title: '',
                content: '请添加默认收货地址!',
                success: function (res) {
                    if (res.confirm) {
                        that.selectAddress();
                        console.log('用户点击确定')
                    }
                }
            })
        }
      }
      wx.hideLoading();
    });
  },
  selectAddress() {
    wx.navigateTo({
      url: '/pages/shopping/address/address',
    })
  },
  addAddress() {
    wx.navigateTo({
      url: '/pages/shopping/addressAdd/addressAdd',
    })
  },
  onReady: function () {
    // 页面渲染完成

  },
  onShow: function () {
    // 页面显示
    this.getCouponData()
    wx.showLoading({
      title: '加载中...',
    }) 
    this.getCheckoutInfo();
  },

  /**
   * 获取优惠券
   */
  getCouponData: function () {
    if (app.globalData.userCoupon == 'USE_COUPON') {
      if (this.data.couponIdArr.length>0){
        for (var i = 0; i < this.data.couponIdArr.length;i++){
          if (this.data.couponIdArr[i].merchantId == app.globalData.courseCouponCode.merchantId){
              this.data.couponIdArr.splice(i,1)
            }
        }
      }
      this.setData({
        couponIdArr: this.data.couponIdArr.concat(app.globalData.courseCouponCode)
      })
    } else if (app.globalData.userCoupon == 'NO_USE_COUPON') {
      this.setData({
        couponDesc: "不使用优惠券",
        couponId: 0,
      })
    }
  },

  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },

  /**
   * 选择可用优惠券
   */
  tapCoupon: function (e) { 
    var merid = e.currentTarget.dataset.merid
    var price = e.currentTarget.dataset.price
    let that = this
      wx.navigateTo({
        url: '../selCoupon/selCoupon?buyType=' + that.data.buyType + '&merid=' + merid + '&price=' + price
      })
  },

  submitOrder: function () {
    let that=this
    if (that.data.addressId <= 0) {
      util.showErrorToast('请选择收货地址');
      return false;
    }
    wx.showLoading({
      title: '提交中'
    })
    if (that.data.couponIdArr.length>0){
      this.setData({
        couponId: '',
      })
      for (var i = 0; i < that.data.couponIdArr.length; i++) {
        that.data.couponId += that.data.couponIdArr[i].user_coupon_id+','
      }
    }
    if (that.data.couponId != "" & that.data.couponId != null & that.data.couponId!=undefined){
      if (that.data.couponId.indexOf(',') > -1) {
        that.data.couponId = that.data.couponId.substr(0, that.data.couponId.length - 1)
      }
    }
    var param={}
    param.addressId = that.data.addressId
    param.promoterId = wx.getStorageSync('userId') || 0
    param.couponId = that.data.couponId == 0 ? null : that.data.couponId,
    param.type = that.data.buyType
    param.payType = that.data.type
    param.groupBuyingId = that.data.groupBuyingId
    console.log("{{}}}}}+++++======:", JSON.stringify(param))
    util.request(api.OrderSubmit, param, 'POST').then(res => {
      wx.hideLoading()
      if (res.errno === 0) {
        const orderId = res.data.orderInfo.id;
        pay.payOrder(parseInt(orderId)).then(res => {
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=1&orderId=' + orderId
          });
        }).catch(res => {
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=0&orderId=' + orderId
          });
        });
      } else {
        util.showErrorToast('下单失败');
      }
    });
  }
})