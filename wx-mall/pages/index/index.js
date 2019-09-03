const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');

//获取应用实例
const app = getApp()
Page({
  data: {
    newGoods: [],
    hotGoods: [],
    topics1:{},
    topics2:{},
    topics3:{},
    skill: [],
    group:[],
    brands: [],
    floorGoods: [],
    banner: [],
    channel: [],
    goodsCount: 0
  },
  onShareAppMessage: function () {
    return {
      title: '51Shop',
      desc: '51商城',
      path: '/pages/index/index'
    }
  },
  onPullDownRefresh() {
    // 增加下拉刷新数据的功能
    var self = this;
    self.getIndexData();
  },
  getIndexData: function () {
    let that = this;
    var data = new Object();
    util.request(api.IndexUrlNewGoods).then(function (res) {
      if (res.errno === 0) {
        data.newGoods = res.data.newGoodsList
        that.setData(data);
      }
    });
    util.request(api.IndexUrlHotGoods).then(function (res) {
      if (res.errno === 0) {
        data.hotGoods = res.data.hotGoodsList
        that.setData(data);
      }
    });
    util.request(api.IndexUrlTopic).then(function (res) {
      if (res.errno === 0) {
        data.topics1 = res.data.topicList[0]
        data.topics2 = res.data.topicList[1]
        data.topics3 = res.data.topicList[2]
        that.setData(data);
      }
    });
    //秒杀产品
    util.request(api.KillList,{page: 1,size:3}).then(function (res) {
      if (res.errno === 0) {
        data.skill = res.data.data
        that.setData(data); 
      }
    });
    //团购产品
    util.request(api.GroupList,{ page: 1, size: 3 }).then(function (res) {
      if (res.errno === 0) {
        data.group = res.data.data
        that.setData(data);
      }
    });
    util.request(api.IndexUrlCategory).then(function (res) {
      if (res.errno === 0) {
        data.floorGoods = res.data.categoryList
        that.setData(data);
      }
    });
    util.request(api.IndexUrlBanner).then(function (res) {

      if (res.errno === 0) {
        data.banner = res.data.banner
        that.setData(data);
      }
    });
    util.request(api.IndexUrlChannel).then(function (res) {
      if (res.errno === 0) {
        data.channel = res.data.channel
        that.setData(data);
      }
    });
    util.request(api.GoodsCount).then(function (res) {
      that.setData({
        goodsCount: res.data.goodsCount
      });
    });

  },
  onLoad: function (options) {
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
  }
})
