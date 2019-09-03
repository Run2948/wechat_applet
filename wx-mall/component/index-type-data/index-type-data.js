// component/index-type-data/index-type-data.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    name: String,
    type:Number,
    data: Array,
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {

    /**
      * 团转到首页
      */
    gotoDetail: function (e) {
      var id = e.currentTarget.dataset.id
      wx.navigateTo({
        url: '../../pages/goods/goods?id=' + id + '&type=' + this.properties.type
      })
    }

  }
})
