// pages/customer/addwh/addwh.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    items:{
      name:'',
      amount:'',
    },
  },
  cancelAddress: function () {
    const item = this.data.items;
    item.name = '';
    item.amount = '';
    this.setData({
      items: item
    });
  },
  bindinputName(e) {
    let items = this.data.items;
    items.name = e.detail.value;
    this.setData({
      items: items
    });
  },
  bindinputAmount(e) { 
    let items = this.data.items;
    items.amount = e.detail.value;
    this.setData({
      items: items
    });
  },  
  saveCustomer() {
    let that = this; 
    if (that.data.items.name == '') {
      util.showErrorToast('请输入真实姓名');
      return false;
    }
    if (that.data.items.amount == '') {
      util.showErrorToast('请输入提现金额');
      return false;
    }
    util.request(api.WithdrawCashes, { name: that.data.items.name, amount: that.data.items.amount }, 'POST', 'application/x-www-form-urlencoded').then(function (res) {
      if (res.errno === 0) { 
        wx.showToast({
          title: '提现成功'
        });
      }else{
        util.showErrorToast(res.errmsg);
      }
    });

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { 
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