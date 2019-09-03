var util = require('./utils/util.js');
var api = require('./config/api.js');
var user = require('./services/user.js');

App({
  onLaunch: function (options) { 
    // if (options.scene!="1008"){
    //   wx.removeStorageSync('userId');
    // }
    //获取用户的登录信息
    user.checkLogin().then(res => {
      console.log('app login')
      this.globalData.userInfo = wx.getStorageSync('userInfo');
      this.globalData.token = wx.getStorageSync('token');
    }).catch(() => {
       wx.removeStorageSync('userInfo');
       wx.removeStorageSync('token');
    }); 
    console.log("全局onLaunch options==" + JSON.stringify(options))
    let q = decodeURIComponent(options.query.q)
    if (q) {
      this.globalData.goodId = util.getQueryString(q, 'id');
      this.globalData.userId = util.getQueryString(q, 'userId');
      console.log("全局onLaunch onload goodId=" + this.globalData.goodId)
      console.log("全局onLaunch onload userId=" + this.globalData.userId)
    }
   
  }, 
  
  globalData: {
    userInfo: {
      nickName: 'Hi,游客',
      userName: '点击登录',
      avatarUrl: 'https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/150547696d798c.png'
    },
    goodId:0,
    userId:0,
    token: '',
    userCoupon: 'NO_USE_COUPON',//默认不适用优惠券
    courseCouponCode: {},//购买课程的时候优惠券信息
  }
})