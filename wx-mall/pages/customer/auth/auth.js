// pages/customer/addwh/addwh.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    items:{
      username:'',
      mobile:'',
      idCard:''
    },
    id:0,
    is_service:0
  },
  cancelAddress: function () {
    const item = this.data.items;
    item.username = '';
    item.mobile = '';
    item.idCard = '';
    this.setData({
      items: item
    });
  },
  bindinputName(e) {
    let items = this.data.items;
    items.username = e.detail.value;
    this.setData({
      items: items
    });
  },
  bindinputMobile(e) { 
    let items = this.data.items;
    items.mobile = e.detail.value;
    this.setData({
      items: items
    });
  },
  bindinputCard(e) { 
    let items = this.data.items;
    items.idCard = e.detail.value;
    this.setData({
      items: items
    });
  }, 
  saveCustomer() {
    let that = this; 
    if (that.data.items.username == '') {
      util.showErrorToast('请输入用户名');
      return false;
    }
    if (that.data.items.mobile == '') {
      util.showErrorToast('请输入手机号码');
      return false;
    }
    if (!util.validatePhone(that.data.items.mobile)) {
      util.showErrorToast('请输入正确手机号码');
      return false;
    }
    if (that.data.items.idCard == '') {
      util.showErrorToast('请输入身份证号');
      return false;
    } 
    if (!(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(that.data.items.idCard))) {
      util.showErrorToast('请输入正确身份证号');
        return false;
    }
    util.request(api.IsRealValidate, { username: that.data.items.username, mobile: that.data.items.mobile, idCard: that.data.items.idCard }, 'POST').then(function (res) {
      if (res.errno === 0) { 
        wx.showToast({
          title: '已实名成功'
        });
        setTimeout(function(){
          wx.navigateBack({
            url: '/pages/goods/goods?id=' + that.data.id + '&is_service=' + that.data.is_service
          })
        },3000) 
      }else{
        util.showErrorToast(res.errmsg);
      }
    });

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { 
    this.setData({
      id: parseInt(options.id),
      is_service: parseInt(options.is_service)
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})