// pages/customer/addwh/addwh.js
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fs_array: ['请选择维护状态', '无意向待跟进', '有意向待跟进'],
    fs_index:0,
    // ly_array: ['请选择来源', '用户购买商品,邮寄给客户', '用户买了 服务性商品后，系统插入','用户自行维护'],
    // ly_index:0,
    // gift:'',
    ctype:'',
    giftPrice:'',
    place:'',
    utime:'',
    customerId:0,
    id:0,
    state:0
  },
  // bindinputName1(e){
  //   this.setData({
  //     gift: e.detail.value
  //   })
  // },
  bindinputName2(e) {
    this.setData({
      ctype: e.detail.value
    })
  },
  bindinputName3(e) {
    this.setData({
      giftPrice: e.detail.value
    })
  },
  bindinputName4(e) {
    this.setData({
      place: e.detail.value
    })
  },
  bindDateChange(e) {
    this.setData({
      utime: e.detail.value
    })
  },
  bindFSChange(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      fs_index: e.detail.value
    })
  },
  // bindLYChange(e) {
  //   console.log('picker发送选择改变，携带值为', e.detail.value)
  //   this.setData({
  //     ly_index: e.detail.value
  //   })
  // },
  saveCustomer() { 
    let that = this;  
    if (that.data.fs_index == 0 & that.data.state==2) {
      util.showErrorToast('请选择维护状态');
      return false;
    } 
    console.log(that.data.id)
    var url = that.data.id == 0 ? api.WhSave:api.UpkeepUpdate;
    console.log('----000---', url)
    util.request(url, {
      id: that.data.id,
      customerId: that.data.customerId, 
      ctype: that.data.ctype,
      place: that.data.place,
      giftPrice: that.data.giftPrice,
      utime: that.data.utime,
      status: that.data.fs_index
    }, 'POST').then(function (res) {
      if (res.errno === 0) {
        wx.navigateBack({
          url: '/pages/customer/whlist/whlist',
        })
      }
    });

  },
  getQueryObject() {
    let that = this; 
    util.request(api.QueryObject, { id: that.data.id }, 'POST').then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({  
          ctype: res.data.ctype,
          place: res.data.place,
          giftPrice: res.data.giftPrice,
          utime: res.data.utime, 
          fs_index: res.data.status,
          customerId: res.data.customerId 
        });
        wx.hideLoading();
      }
    });
  },
  cancelAddress: function () {
    this.setData({
      fs_index: 0, 
      ctype: '',
      place: '',
      giftPrice: '',
      utime: ''
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('-------8888-----', options.state)
    this.setData({
      customerId: options.id ? options.id:0,
      id: options.nid ? options.nid:0,
      state: options.state
    }); 
    if (options.nid){ 
      wx.setNavigationBarTitle({
        title: '编辑维护历史'
      })
      wx.showLoading({
        title: '加载中...',
        success: function () { }
      });
      this.getQueryObject();
    } 
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
  // onPullDownRefresh: function () {

  // },

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