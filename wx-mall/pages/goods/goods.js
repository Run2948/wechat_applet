var app = getApp();
var WxParse = require('../../lib/wxParse/wxParse.js');
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var user = require('../../services/user.js');

Page({
  data: {
    winHeight: "",
    id: 0,
    userId:0,
    goods: {},
    gallery: [],
    attribute: [],
    issueList: [],
    comment: [],
    brand: {},
    specificationList: [],
    productList: [],
    relatedGoods: [],
    groupBuyLis:[],
    newBuyLis: [],
    newGroup: {},
    cartGoodsCount: 0,
    userHasCollect: 0,
    number: 1,
    checkedSpecText: '请选择规格数量',
    checkedSpecPrice: 0,
    yprice:0,
    proId: 0,
    proImg:'',
    openAttr: false,
    openCoupon:false,
    openGroup:false,
    openPart: false,
    cimPart:true,
    noCollectImage: "/static/images/icon_collect.png",
    hasCollectImage: "/static/images/icon_collect_checked.png",
    collectBackImage: "/static/images/icon_collect.png",
    nowtime:0,
    type:0,
    ntype:'',
    groupprice:0,
    groupNum:0,
    groupBuyingId:''
  }, 
  onShareAppMessage: function() { 
    this.addShareGoods()
    const share_obj= {
      title: this.data.goods.name,
      imageUrl: this.data.goods.list_pic_url,
      path: 'pages/goods/goods?id=' + this.data.id + '&userId=' + wx.getStorageSync('uId')
    }
    return share_obj
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数

    var that = this;
    that.setData({
      nowtime: new Date().getTime() + 20000
    });
    if (options.id) {
      that.setData({
        id: options.id,
      });
    }
    if (options.type) {
      that.setData({
        type: options.type,
      });
    }
    if (options.userId) {
      wx.setStorageSync('userId', options.userId)
    }
    if (options.q) {
      const q = decodeURIComponent(options.q)
      that.setData({
        id: util.getQueryString(q, 'id')
      });
      wx.setStorageSync('userId', util.getQueryString(q, 'userId'))
      that.newLogin()
    }
    that.getGoodsInfo();
    var that = this
    //  高度自适应
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 100;
        that.setData({
          winHeight: calc
        });
      }
    });
  },
  onShow: function () {
    let token = wx.getStorageSync('token');
    if (!token) {
      wx.redirectTo({
        url: '../customer/zcuslist/zcuslist?id=' + this.data.id + '&type=' + this.data.type
      })
      return false;
    }
    this.cartGoodsCount();
    this.getCouponList();
    this.getGroupBuyList();
  },
  backfun:function(e){
     console.log("---===---=:",e)
     if(e.success){
       this.getGroupBuyList()
     }
  },
  openCoupon:function(){
    this.setData({
      openCoupon: true
    });
    this.getCouponList();
  },
  colseCoupon:function(){
    this.setData({
      openCoupon: false
    });
  },
  openGroup: function () {
    this.setData({
      openGroup: true
    });
    this.getCouponList();
  }, 
  colseGroup: function () {
    this.setData({
      openGroup: false
    });
  },
  cimPart:function(){
    if (this.data.openAttr == false) {
      //打开规格选择窗口
      this.setData({
        openAttr: !this.data.openAttr,
        collectBackImage: "/static/images/detail_back.png"
      });
    }
    this.setData({
      cimPart: false
    });
  },
  openPart: function (e) {  
    var model = e.target.dataset.items;
    this.setData({
      newGroup:model,
      groupBuyingId: model.groupBuyingDetailedList[0].groupBuyingId,
      openPart: true
    });
  },
  colsePart: function () {
    this.setData({
      openPart: false
    });
  },
  getCouponList: function () {
    let that = this;
    util.request(api.CouponListByMer, {
      merchantId: that.data.goods.merchantId
    }, "POST").then(function (res) {
      if (res.errno === 0) {
        that.setData({
          merCoupon: res.data,
        });
      }
    });
  },
  getGroupBuyList: function () {
    let that = this;
    util.request(api.GroupBuyList, {
      goodsId: that.data.id
    }, "POST").then(function (res) {
      if (res.errno === 0) { 
        var items = res.data.groupBuyingEntityList;
        var arr = [];
        var num = Math.ceil(items.length / 2)
        console.log('--------,------:', num)
        for (var j = 0; j < num; j++) {
          var str = [];
          for (var i = 0; i < items.length; i++) {
            if (str.length < 2) {
              if (items[i + j * 2]) {
                str.push(items[i + j * 2]);
              }
            }
          }
          arr.push(str);
        }
        console.log('----------www---------:', arr)
        that.setData({
          groupBuyList: res.data.groupBuyingEntityList,
          groupNum: res.data.groupNum,
          newBuyLis: arr
        });
      }
    });
  },
  addShareGoods: function () {
    let that = this;
    const param={};
    param.goodsId = that.data.goods.id
    param.name = that.data.goods.name
    param.goodsBrief = that.data.goods.goods_brief || ''
    param.retailPrice = that.data.goods.retail_price
    param.marketPrice = that.data.goods.market_price
    param.primaryPicUrl = that.data.goods.primary_pic_url 
    console.log("------ffff:", JSON.stringify(param))
    util.request(api.InsShareGoods, param, "POST", 'application/x-www-form-urlencoded').then(function (res) { 
      console.log("------rrrrr:", res)
      if (res.errno === 0) { 
        console.log("------chenggong")
      }
    });
  },
  takeCoupon: function (e) {
    let that = this;
    util.request(api.TakeMerCoupon, {
      id: e.target.dataset.couponid
    }, "POST").then(function (res) {
      if (res.errno === 0) {
        wx.showToast({
          title: '领取成功',
          icon: 'none',
          duration: 2000
        })
      } else {
        wx.showToast({
          title: res.errmsg,
          icon: 'none',
          duration: 2000
        })
      }
    });
  },
  getGoodsInfo: function() {
    let that = this;
    util.request(api.GoodsDetail, {
      id: that.data.id
    }).then(function(res) {
      if (res.errno === 0) {
        that.setData({
          goods: res.data.info,
          gallery: res.data.gallery,
          attribute: res.data.attribute,
          issueList: res.data.issue,
          comment: res.data.comment,
          brand: res.data.brand,
          specificationList: res.data.specificationList,
          productList: res.data.productList,
          userHasCollect: res.data.userHasCollect
        });
        //设置默认值
        that.setDefSpecInfo(that.data.specificationList);
        if (res.data.userHasCollect == 1) {
          that.setData({
            'collectBackImage': that.data.hasCollectImage
          });
        } else {
          that.setData({
            'collectBackImage': that.data.noCollectImage
          });
        }

        WxParse.wxParse('goodsDetail', 'html', res.data.info.goods_desc, that);

        that.getGoodsRelated();
      }
    });

  },
  getGoodsRelated: function() {
    let that = this;
    util.request(api.GoodsRelated, {
      id: that.data.id
    }).then(function(res) {
      if (res.errno === 0) {
        that.setData({
          relatedGoods: res.data.goodsList,
        });
      }
    });

  },
  clickSkuValue: function (event) {
    let that = this;
    let specNameId = event.currentTarget.dataset.nameId;
    let specValueId = event.currentTarget.dataset.valueId + "";
    let state = event.currentTarget.dataset.state;
    const proImg = event.currentTarget.dataset.picurl;
    // 禁用则结束
    if (state) {
      return;
    }
    //判断是否可以点击

    //TODO 性能优化，可在wx:for中添加index，可以直接获取点击的属性名和属性值，不用循环
    let _specificationList = this.data.specificationList;
    for (let i = 0; i < _specificationList.length; i++) {
      if (_specificationList[i].specification_id == specNameId) {
        for (let j = 0; j < _specificationList[i].valueList.length; j++) {
          if (_specificationList[i].valueList[j].id == specValueId) {
            //如果已经选中，则反选
            if (_specificationList[i].valueList[j].checked) {
              _specificationList[i].valueList[j].checked = false;
              // }
              if (_specificationList.length > 1) {
                that.unSelectValue()
              }
            } else {
              _specificationList[i].valueList[j].checked = true;
              if (_specificationList.length > 1) {
                that.selectValue(specValueId, specNameId)
              }
            }
          } else {
            _specificationList[i].valueList[j].checked = false;
          }
        }
      }
    }
    this.setData({
      'proImg': proImg,
      'specificationList': _specificationList
    });
    // this.selectValue(specValueId, specNameId)
    //重新计算spec改变后的信息
    this.changeSpecInfo();
    // 新加
    var key = that.getCheckedSpecKey();
    for (var i = 0; i < that.data.productList.length; i++) {
      if (that.data.productList[i].goods_specification_ids == key) {
        that.setData({
          checkedSpecPrice: that.data.type == '1' ? that.data.productList[i].group_price : that.data.productList[i].retail_price,
          yprice: that.data.productList[i].retail_price,
          proId: that.data.productList[i].id
        });
      }
    }
  },
  //选中
  selectValue: function (id, specNameId) {
    let that = this
    var newAttrIds = []
    for (var i = 0; i < that.data.productList.length; i++) {
      var selArr = [];
      if (that.data.productList[i].goods_specification_ids.indexOf('_') > -1) {
        selArr = that.data.productList[i].goods_specification_ids.split('_')
        if (selArr.indexOf(id) > -1) {
          for (var j = 0; j < selArr.length; j++) {
            if (selArr[j] != id) {
              if (newAttrIds.indexOf(selArr[j]) <= -1) {
                newAttrIds.push(selArr[j])
              }
            }
          }
        }
      }
    }
    for (var z = 0; z < that.data.specificationList.length; z++) {
      for (var y = 0; y < that.data.specificationList[z].valueList.length; y++) {
        if (that.data.specificationList[z].specification_id != specNameId) {
          var nid = that.data.specificationList[z].valueList[y].id + ""
          if (newAttrIds.indexOf(nid) > -1) {
            that.data.specificationList[z].valueList[y].state = false
          } else {
            that.data.specificationList[z].valueList[y].state = true
          }
        }
      }
    }
    that.setData({
      'specificationList': that.data.specificationList
    });
  },
  //取消选择
  unSelectValue: function () {
    let that = this;
    var n = 0;
    for (var z = 0; z < that.data.specificationList.length; z++) {
      for (var y = 0; y < that.data.specificationList[z].valueList.length; y++) {
        if (that.data.specificationList[z].valueList[y].checked) {
          n += 1;
          that.selectValue(that.data.specificationList[z].valueList[y].id + "", that.data.specificationList[z].valueList[y].specification_id + "")
          break;
        }
      }
    }
    if (n == 0) {
      for (var m = 0; m < that.data.specificationList.length; m++) {
        for (var n = 0; n < that.data.specificationList[m].valueList.length; n++) {
          that.data.specificationList[m].valueList[n].state = false
        }
      }
      that.setData({
        'specificationList': that.data.specificationList
      });
    }
  },
  //获取选中的规格信息
  getCheckedSpecValue: function () {
    let checkedValues = [];
    let _specificationList = this.data.specificationList;
    for (let i = 0; i < _specificationList.length; i++) {
      let _checkedObj = {
        nameId: _specificationList[i].specification_id,
        valueId: 0,
        valueText: ''
      };
      for (let j = 0; j < _specificationList[i].valueList.length; j++) {
        if (_specificationList[i].valueList[j].checked) {
          _checkedObj.valueId = _specificationList[i].valueList[j].id;
          _checkedObj.valueText = _specificationList[i].valueList[j].value;
        }
      }
      checkedValues.push(_checkedObj);
    }

    return checkedValues;

  },
  //根据已选的值，计算其它值的状态
  setSpecValueStatus: function () {

  },
  //判断规格是否选择完整
  isCheckedAllSpec: function () {
    return !this.getCheckedSpecValue().some(function (v) {
      if (v.valueId == 0) {
        return true;
      }
    });
  },
  getCheckedSpecKey: function () {
    let checkedValue = this.getCheckedSpecValue().map(function (v) {
      return v.valueId;
    });
    return checkedValue.join('_');
  },
  changeSpecInfo: function () {
    let that = this;
    let checkedNameValue = that.getCheckedSpecValue();
    let checkedValue = checkedNameValue.filter(function (v) {
      if (v.valueId != 0) {
        return true;
      } else {
        return false;
      }
    }).map(function (v) {
      return v.valueText;
    });
    if (checkedValue.length > 0) {
      this.setData({
        'checkedSpecText': checkedValue.join('　')
      });
    } else {
      this.setData({
        'checkedSpecText': '请选择规格数量'
      });
    }

  },
  getCheckedProductItem: function (key) {
    console.log('---------00--------00----:', this.data.productList)
    return this.data.productList.filter(function (v) {
      if (v.goods_specification_ids = key) {
        return true;
      } else {
        return false;
      }
    });
  }, 
  cartGoodsCount:function(){
    let that=this
    util.request(api.CartGoodsCount).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          cartGoodsCount: res.data.cartTotal.goodsCount
        });
      }
    });
  },
  onReady: function() {
    // 页面渲染完成

  }, 
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  },
  switchAttrPop: function() { 
    if (this.data.openAttr == false) {
      this.setData({
        openAttr: !this.data.openAttr,
        collectBackImage: "/static/images/detail_back.png"
      });
    }
  },
  goUrl: function() {
    wx.switchTab({
      url: '/pages/index/index',
    })
  },
  closeAttrOrCollect: function() {
    let that = this;
    if (this.data.openAttr) {
      this.setData({
        openAttr: false,
      });
      if (that.data.userHasCollect == 1) {
        that.setData({
          'collectBackImage': that.data.hasCollectImage
        });
      } else {
        that.setData({
          'collectBackImage': that.data.noCollectImage
        });
      }
    } else {
      //添加或是取消收藏
      util.request(api.CollectAddOrDelete, {
          typeId: 0,
          valueId: this.data.id
        }, "POST")
        .then(function(res) {
          let _res = res;
          if (_res.errno == 0) {
            if (_res.data.type == 'add') {
              that.setData({
                'collectBackImage': that.data.hasCollectImage
              });
            } else {
              that.setData({
                'collectBackImage': that.data.noCollectImage
              });
            }

          } else {
            wx.showToast({
              image: '/static/images/icon_error.png',
              title: _res.errmsg,
              mask: true
            });
          }

        });
    }

  },
  openCartPage: function() {
    wx.switchTab({
      url: '/pages/cart/cart',
    });
  },

  /**
   * 直接购买
   */
  buyGoods: function(e) {
    var that = this; 
    var ntype = e.target.dataset.ntype || ''
    var activityType = e.target.dataset.activitytype || '';
    var groupBuyingId = e.target.dataset.groupbuyingid || '';
    that.setData({
      groupBuyingId: groupBuyingId
    });
    if (that.data.openAttr == false) {
      //打开规格选择窗口
      that.setData({
        openAttr: !that.data.openAttr,
        collectBackImage: "/static/images/detail_back.png"
      });
    } else {
      wx.showLoading({
        title: '提交中',
      })
      wx.setStorageSync('isYJ', that.data.isYJ);
      //提示选择完整规格
      if (!that.isCheckedAllSpec()) {
        wx.showToast({
          title: '请选择完整规格'
        });
        return false;
      }

      //根据选中的规格，判断是否有对应的sku信息
      let checkedProduct = that.getCheckedProductItem(that.getCheckedSpecKey());
      if (that.getCheckedSpecKey() != "") {
        if (!checkedProduct || checkedProduct.length <= 0) {
          //找不到对应的product信息，提示没有库存
          wx.showToast({
            title: '库存不足'
          });
          return false;
        }
      } else {
        if (that.data.goods.goods_number < that.data.number) {
          wx.showToast({
            title: '库存不足'
          });
          return false;
        }
      }  
      // 直接购买商品
      util.request(api.BuyAdd, {
        goodsId: that.data.goods.id,
        number: that.data.number,
        productId: that.data.proId ? that.data.proId : that.data.productList[0].id}, "POST")
        .then(function(res) {
          wx.hideLoading();
          let _res = res;
          if (_res.errno == 0) {
            that.setData({
              openAttr: !that.data.openAttr
            });
            wx.navigateTo({
              url: '/pages/shopping/checkout/checkout?isBuy=true&type=' + ntype + '&groupBuyingId=' + groupBuyingId + '&activityType=' + activityType
            })
          } else {
            wx.showToast({
              image: '/static/images/icon_error.png',
              title: _res.errmsg,
              mask: true
            });
          }

        });

    }
  },

  /**
   * 添加到购物车
   */
  addToCart: function() {
    var that = this; 
    if (that.data.openAttr == false) {
      //打开规格选择窗口
      that.setData({
        openAttr: !this.data.openAttr,
        collectBackImage: "/static/images/detail_back.png"
      });
    } else {
      wx.showLoading({
        title: '提交中',
      })
      wx.setStorageSync('isYJ', that.data.isYJ);
      //提示选择完整规格
      if (!this.isCheckedAllSpec()) {
        wx.showToast({
          title: '请选择完整规格'
        });
        return false;
      }

      //根据选中的规格，判断是否有对应的sku信息
      let checkedProduct = that.getCheckedProductItem(that.getCheckedSpecKey());
      if (that.getCheckedSpecKey() != "") {
        if (!checkedProduct || checkedProduct.length <= 0) {
          //找不到对应的product信息，提示没有库存
          wx.showToast({
            title: '库存不足'
          });
          return false;
        }
      } else {
        if (that.data.goods.goods_number < that.data.number) {
          wx.showToast({
            title: '库存不足'
          });
          return false;
        }
      } 

      //添加到购物车
      util.request(api.CartAdd, {
        goodsId: that.data.goods.id,
        number: that.data.number,
        productId: that.data.proId ? that.data.proId : that.data.productList[0].id}, "POST")
        .then(function(res) {
          wx.hideLoading();
          let _res = res;
          if (_res.errno == 0) {
            wx.showToast({
              title: '添加成功'
            });
            that.setData({
              openAttr: !that.data.openAttr,
              cartGoodsCount: _res.data.cartTotal.goodsCount,
            });
            if (that.data.userHasCollect == 1) {
              that.setData({
                'collectBackImage': that.data.hasCollectImage
              });
            } else {
              that.setData({
                'collectBackImage': that.data.noCollectImage
              });
            }
          } else {
            wx.showToast({
              image: '/static/images/icon_error.png',
              title: _res.errmsg,
              mask: true
            });
          }

        });
    }

  },
  cutNumber: function() {
    this.setData({
      number: (this.data.number - 1 > 1) ? this.data.number - 1 : 1
    });
  },
  addNumber: function() {
    this.setData({
      number: this.data.number + 1
    });
  },
  setDefSpecInfo: function(specificationList) {
    //未考虑规格联动情况
    let that = this;
    if (!specificationList) return;
    for (let i = 0; i < specificationList.length; i++) {
      let specification = specificationList[i];
      let specNameId = specification.specification_id;
      //规格只有一个时自动选择规格
      if (specification.valueList && specification.valueList.length == 1) {
        let specValueId = specification.valueList[0].id;
        that.clickSkuValue({
          currentTarget: {
            dataset: {
              "nameId": specNameId,
              "valueId": specValueId
            }
          }
        });
      }
    }
    specificationList.map(function(item) {

    });

  },
  newLogin:function(){
    let that=this;
    //重新登陆
    const token = wx.getStorageSync('token');
    if (!token) {
      wx.getSetting({
        success(res) {
          if (res.authSetting['scope.userInfo']) {
            wx.getUserInfo({
              success: function (res) {
                //用户按了允许授权按钮
                user.loginByWeixin(res).then(res => {
                  let userInfo = wx.getStorageSync('userInfo');
                  app.globalData.userInfo = userInfo.userInfo;
                  app.globalData.token = res.data.openid;
                  that.cartGoodsCount();
                }).catch((err) => {
                  console.log(err)
                });
              }
            })
          } else {
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
                          that.cartGoodsCount();
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
        }
      })
    }
  }
})