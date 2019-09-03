var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();
Page({
  data: {
    customer: {
      id: 0,
      province_id: 0,
      city_id: 0,
      district_id: 0,
      provinceName:'',
      cityName:'',
      countyName:'', 
      detailInfo: '',
      full_region: '',
      uname: '',
      mobile: '',
      gender: 1,
      customerState:1,
      birthday: null,
      job:'',
      addressUserId:0,
      remarks:'',
      upKeepState:''
    },
    isv: 0,
    customerId: 0,
    openSelectRegion: false,
    selectRegionList: [
      { id: 0, name: '省份', parent_id: 1, type: 1 },
      { id: 0, name: '城市', parent_id: 1, type: 2 },
      { id: 0, name: '区县', parent_id: 1, type: 3 }
    ], 
    regionType: 1,
    regionList: [],
    selectRegionDone: false
  }, 
  cancelAddress:function(){
    const item = this.data.customer;
    item.full_region=''; 
    item.detailInfo = '';
    item.birthday = '';
    item.customerState = 1;
    item.gender = 1;
    item.uname = '';
    item.mobile = '';
    item.job = '';
    item.remarks = '';
    this.setData({
      customer: item,
      selectRegionList: [
        { id: 0, name: '省份', parent_id: 1, type: 1 },
        { id: 0, name: '城市', parent_id: 1, type: 2 },
        { id: 0, name: '区县', parent_id: 1, type: 3 }
      ]
    });
  },
  zhCustomer:function(){
    this.setData({
      isv: 1
    });
    let customer = this.data.customer;
    customer.upKeepState = 1;
    this.setData({
      customer: customer
    });
    this.saveCustomer();
  },
  bindDateChange: function (event) {
    let customer = this.data.customer;
    customer.birthday = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindJobChange: function (event) {
    let customer = this.data.customer;
    customer.job = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  radioChange: function (event) {
    let customer = this.data.customer;
    customer.gender = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  kradioChange: function (event) {
    let customer = this.data.customer;
    customer.customerState = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindinputRemarks(event){
    let customer = this.data.customer;
    customer.remarks = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindJobName(event) {
    let customer = this.data.customer;
    customer.job = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindinputMobile(event) {
    let customer = this.data.customer;
    customer.mobile = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindinputName(event) {
    let customer = this.data.customer;
    customer.uname = event.detail.value;
    this.setData({
      customer: customer
    });
  },
  bindinputCustomer(event) {
    let customer = this.data.customer;
    customer.detailInfo = event.detail.value;
    this.setData({
      customer: customer
    });
  }, 
  getCustomerDetail() {
    let that = this;
    util.request(api.CustomerObject, { id: that.data.customerId },'POST').then(function (res) {
      if (res.errno === 0) {
        if (res.data) {
          var params={};
          params = res.data; 
          params.full_region = res.data.addressVo.full_region;
          params.detailInfo = res.data.addressVo.detailInfo;  
          that.setData({
            customer: params
          });
        }
        wx.hideLoading();
      }
    });
  },
  setRegionDoneStatus() {
    let that = this;
    let doneStatus = that.data.selectRegionList.every(item => {
      return item.id != 0;
    });

    that.setData({
      selectRegionDone: doneStatus
    })

  },
  chooseRegion() {
    let that = this;
    this.setData({
      openSelectRegion: !this.data.openSelectRegion
    });

    //设置区域选择数据
    let customer = this.data.customer;
    if (customer.province_id > 0 && customer.city_id > 0 && customer.district_id > 0) {
      let selectRegionList = this.data.selectRegionList;
      selectRegionList[0].id = customer.province_id;
      selectRegionList[0].name = customer.provinceName;
      selectRegionList[0].parent_id = 1;

      selectRegionList[1].id = customer.city_id;
      selectRegionList[1].name = customer.cityName;
      selectRegionList[1].parent_id = customer.province_id;

      selectRegionList[2].id = customer.district_id;
      selectRegionList[2].name = customer.countyName;
      selectRegionList[2].parent_id = customer.city_id;
      console.log('-----0000011-------', selectRegionList)
      this.setData({
        selectRegionList: selectRegionList,
        regionType: 3
      });

      this.getRegionList(customer.city_id);
    } else {
      this.setData({
        selectRegionList: [
          { id: 0, name: '省份', parent_id: 1, type: 1 },
          { id: 0, name: '城市', parent_id: 1, type: 2 },
          { id: 0, name: '区县', parent_id: 1, type: 3 }
        ],
        regionType: 1
      })
      this.getRegionList(1);
    }

    this.setRegionDoneStatus();

  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数 
    if (options.v){
      if (options.v==2){
        wx.setNavigationBarTitle({
          title: '新增准客户'
        })
      }
      this.setData({
        isv: options.v
      });
    }
    if (options.id) {
      if (options.v == 2) {
        wx.setNavigationBarTitle({
          title: '编辑准客户'
        })
      } else if (options.v == 1){
        wx.setNavigationBarTitle({
          title: '编辑客户'
        })
      }
      this.setData({
        customerId: options.id
      });
      this.getCustomerDetail();
    } 
    this.getRegionList(1);

  },
  onReady: function () { 
  },
  selectRegionType(event) {
    let that = this;
    let regionTypeIndex = event.target.dataset.regionTypeIndex;
    let selectRegionList = that.data.selectRegionList;

    //判断是否可点击
    if (regionTypeIndex + 1 == this.data.regionType || (regionTypeIndex - 1 >= 0 && selectRegionList[regionTypeIndex - 1].id <= 0)) {
      return false;
    }

    this.setData({
      regionType: regionTypeIndex + 1
    })

    let selectRegionItem = selectRegionList[regionTypeIndex];

    this.getRegionList(selectRegionItem.parent_id);

    this.setRegionDoneStatus();

  },
  selectRegion(event) {
    let that = this;
    let regionIndex = event.target.dataset.regionIndex;
    let regionItem = this.data.regionList[regionIndex];
    let regionType = regionItem.type;
    let selectRegionList = this.data.selectRegionList;
    selectRegionList[regionType - 1] = regionItem;


    if (regionType != 3) {
      this.setData({
        selectRegionList: selectRegionList,
        regionType: regionType + 1
      })
      this.getRegionList(regionItem.id);
    } else {
      this.setData({
        selectRegionList: selectRegionList
      })
    }

    //重置下级区域为空
    selectRegionList.map((item, index) => {
      if (index > regionType - 1) {
        item.id = 0;
        item.name = index == 1 ? '城市' : '区县';
        item.parent_id = 0;
      }
      return item;
    });

    this.setData({
      selectRegionList: selectRegionList
    })


    that.setData({
      regionList: that.data.regionList.map(item => {

        //标记已选择的
        if (that.data.regionType == item.type && that.data.selectRegionList[that.data.regionType - 1].id == item.id) {
          item.selected = true;
        } else {
          item.selected = false;
        }

        return item;
      })
    });

    this.setRegionDoneStatus();

  },
  doneSelectRegion() {
    if (this.data.selectRegionDone === false) {
      return false;
    } 
    let customer = this.data.customer;
    let selectRegionList = this.data.selectRegionList;
    console.log('********', selectRegionList)
    customer.province_id = selectRegionList[0].id;
    customer.city_id = selectRegionList[1].id;
    customer.district_id = selectRegionList[2].id;
    customer.provinceName = selectRegionList[0].name;
    customer.cityName = selectRegionList[1].name;
    customer.countyName = selectRegionList[2].name;
    customer.full_region = selectRegionList.map(item => {
      return item.name;
    }).join('');

    this.setData({
      customer: customer,
      openSelectRegion: false
    });

  },
  cancelSelectRegion() {
    this.setData({
      openSelectRegion: false,
      regionType: this.data.regionDoneStatus ? 3 : 1
    });

  },
  getRegionList(regionId) {
    let that = this;
    let regionType = that.data.regionType;
    util.request(api.RegionList, { parentId: regionId }).then(function (res) {
      if (res.errno === 0) {
        that.setData({
          regionList: res.data.map(item => {

            //标记已选择的
            if (regionType == item.type && that.data.selectRegionList[regionType - 1].id == item.id) {
              item.selected = true;
            } else {
              item.selected = false;
            }

            return item;
          })
        });
      }
    });
  },
  cancelCustomer() {
    wx.navigateBack({
      url: '/pages/ucenter/address/address',
    })
  },
  saveCustomer() {
    console.log(this.data.customer)
    let customer = this.data.customer;

    if (customer.uname == '') {
      util.showErrorToast('请输入姓名');  
      return false;
    }

    if (customer.mobile == '') {
      util.showErrorToast('请输入手机号码');
      return false;
    }

    if (!util.validatePhone(customer.mobile)) {
      util.showErrorToast('请输入正确手机号码');
      return false;
    }

    if (customer.district_id == 0 & this.data.isv == 1) {
      util.showErrorToast('请输入省市区');
      return false;
    }

    if (customer.detailInfo == '' & this.data.isv == 1) {
      util.showErrorToast('请输入详细地址');
      return false;
    }
    if (customer.birthday == '' & this.data.isv == 1) {
      util.showErrorToast('请选择客户生日');
      return false;
    }

    let that = this;
    var addressVo={};
    const url=that.data.customerId == 0 ? api.CustomerSave :api.CustomerEdit;
    if (that.data.customerId == 0){
      addressVo={
        provinceName: customer.provinceName ? customer.provinceName : '',
        cityName: customer.cityName ? customer.cityName : '',
        countyName: customer.countyName ? customer.countyName : '',
        detailInfo: customer.detailInfo ? customer.detailInfo : ''
      }
    }else{
      addressVo = {
        provinceName: customer.provinceName ? customer.provinceName : customer.addressVo.provinceName,
        cityName: customer.cityName ? customer.cityName : customer.addressVo.cityName,
        countyName: customer.countyName ? customer.countyName : customer.addressVo.countyName,
        detailInfo: customer.detailInfo ? customer.detailInfo : customer.addressVo.detailInfo
      }
    }
    util.request(url, {
      id: customer.id,
      uname: customer.uname,
      mobile: customer.mobile,
      province_id: customer.province_id,
      city_id: customer.city_id,
      district_id: customer.district_id,
      gender: customer.gender, 
      birthday: customer.birthday,
      customerState: that.data.isv,
      addressVo: addressVo,
      job: customer.job,
      remarks: customer.remarks,
      addressUserId: customer.addressUserId,
      upkeepState: customer.upKeepState
    }, 'POST').then(function (res) {
      if (res.errno === 0) {
        if (that.data.customerId == 0){
          that.cancelAddress();
          wx.showToast({
            title: '添加完成'
          });
        }else{
          wx.navigateBack({
            url: '/pages/customer/cuslist/cuslist',
          })
        } 
      }else{
        util.showErrorToast(res.errmsg);
      }
    });

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