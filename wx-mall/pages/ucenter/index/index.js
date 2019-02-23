var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();

Page({
    data: {
        userInfo: {},
        hasMobile: '',
        // isReal:0
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
        console.log('----dd----',app.globalData)
    },
    onReady: function () {

    },
    onShow: function () {

        let userInfo = wx.getStorageSync('userInfo');
        let token = wx.getStorageSync('token');  
        // 页面显示
        if (token) {
          app.globalData.userInfo = userInfo.userInfo;
          app.globalData.token = token;
        }else{
          wx.removeStorageSync('userInfo');
        } 
        this.setData({
            userInfo: app.globalData.userInfo
        });
      // this.initDataInfo();

    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },
    onShareAppMessage: function () {
      return {
        title: '分享获取优惠卷',
        imageUrl: 'https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/bff2e49136fcef1fd829f5036e07f116.jpg',
        path: 'pages/index/index?id=' + wx.getStorageSync('uId')
      }
    },
  // initDataInfo: function () {
  //   let that = this;
  //   var data = new Object();
  //   const userId = wx.getStorageSync('uId') ? wx.getStorageSync('uId') : 0;
  //   util.request(api.UserInfoById, { userId: userId }, 'POST').then(function (res) {
  //     if (res.errno === 0) {
  //       that.setData({
  //         isReal: res.data.isReal
  //       });

  //     }
  //   });
  // },
    bindGetUserInfo(e) { 
      let token = wx.getStorageSync('token');
      if (token) {
        return false;
      }
        if (e.detail.userInfo){
            //用户按了允许授权按钮
            user.loginByWeixin(e.detail).then(res => {
                let userInfo = wx.getStorageSync('userInfo');
                this.setData({
                  userInfo: userInfo.userInfo
                });
                app.globalData.userInfo = userInfo.userInfo;
                app.globalData.token = res.data.openid;
            }).catch((err) => {
                console.log(err)
            }); 
        } else {
            //用户按了拒绝按钮
            wx.showModal({
                title: '警告通知',
                content: '您点击了拒绝授权,将无法正常显示个人信息,点击确定重新获取授权。',
                success: function (res) {
                    if (res.confirm) {
                        wx.openSetting({
                            success: (res) => {
                                if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
                                    user.loginByWeixin(e.detail).then(res => {
                                      let userInfo = wx.getStorageSync('userInfo');
                                      this.setData({
                                        userInfo: userInfo.userInfo
                                      });
                                      app.globalData.userInfo = userInfo.userInfo;
                                      app.globalData.token = res.data.openid;
                                    }).catch((err) => {
                                        console.log(err)
                                    });
                                }
                            }
                        })
                    }
                }
            });
        }
    },
    exitLogin: function () {
        wx.showModal({
            title: '',
            confirmColor: '#b4282d',
            content: '退出登录？',
            success: function (res) {
                if (res.confirm) {
                    wx.removeStorageSync('token');
                    wx.removeStorageSync('userInfo');
                    console.log("---------123123---------")
                    wx.switchTab({
                        url: '/pages/index/index'
                    });
                }
            }
        })

    }
})