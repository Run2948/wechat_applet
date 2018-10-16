<template>
  <page :better-scroll="true" page-background="white">
    <search-header
      slot="top"
      :step="step"
      :searchKey="searchKey"
      @search="search"
      @key-change="change"></search-header>
    <search-default
        :hot="hot"
        :history="history"
        @select-hot="selectHot"
        @select-history="selectHistory"
        @clear-history="clearHistory"
        v-if="step === 1"></search-default>
    <search-auto
        :recommend="recommend"
        :searchList="searchList"
        v-if="step === 2"></search-auto>
    <search-list
      :list="searchList"
      v-if="step === 3"></search-list>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapActions} from 'vuex'
import session from '@/libs/session'
import searchHeader from './search-header'
import searchDefault from './search-default'
import searchAuto from './search-auto'
import searchList from './search-list'
export default {
  data () {
    return {
      step: 1,
      searchKey: '小米MIX',
      hot: [],
      history: session.getHistory(),
      recommend: null,
      searchList: [
        {
          pic: 'https://img.youpin.mi-img.com/800_pic/7887b091dafa47cb751ab9728b9a42f6.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '多亲AI功能电话',
          desc: '内置小爱同学，语音红外遥控器，立体环绕，双卡双待',
          price: '299'
        },
        {
          pic: 'https://img.youpin.mi-img.com/800_pic/b7328e3dda65010f5ee5ff86f9c2d021.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '小米8 透明探索版',
          desc: '3D结构光技术，更安全的面部解锁 / 压感屏幕指纹 / 双频GPS / 骁龙845处理器 / AI变焦双摄 / 三星 AMOLED 屏',
          price: '3699'
        },
        {
          pic: 'http://img.youpin.mi-img.com/800_pic/6874c0c9ab598a3fbaf7bbae5fe34ede.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF',
          name: '红米6A',
          desc: '12nm高性能处理器 / 5.45" 小巧全面屏 / 1300万高清相机 / “小杨柳腰”机身',
          price: '599'
        }
      ]
    }
  },
  components: {
    searchHeader,
    searchDefault,
    searchAuto,
    searchList
  },
  methods: {
    ...mapActions('search', ['getSearchHot', 'getSearchListBykeyword']),
    change (key) {
      this.getSearchListBykeyword(key).then(res => {
        this.recommend = res.recommend
        this.searchList = res.hot
        this.step = 2
      })
    },
    search (val) {
      if (val) {
        this.step = 3
        session.addHistory(val)
      } else {
        this.$toast('搜索关键字不能为空', 'warn')
      }
    },
    selectHot (o) {
      // @TODO
    },
    selectHistory (s) {
      this.getSearchListBykeyword(s).then(res => {
        this.recommend = res.recommend
        this.searchList = res.hot
        this.searchKey = s
        this.step = 3
      })
    },
    clearHistory () {
      session.clearHistory()
      this.history = []
    },
    init () {
      this.getSearchHot().then(res => {
        this.hot = res
      })
    }
  },
  created () {
    this.init()
  }
}
</script>
