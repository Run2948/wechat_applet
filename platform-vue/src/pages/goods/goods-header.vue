<template>
  <div class="goods-header" :style="{background: `RGBA(255, 255, 255, ${opacity})`}">
    <div class="header-left" @click="$back">
      <i class="iconfont" :style="{background: opacity === 1 ? 'white' : '', color: opacity === 1 ? '#333' : ''}">&#xe68c;</i>
    </div>
    <div class="header-middle">
      <div class="middle-item" :class="{'middle-item-active': activeTab === 0}" @click="clickTab(0)">
        <span>商品</span>
      </div>
      <div class="middle-item" :class="{'middle-item-active': activeTab === 1}" @click="clickTab(1)">
        <span>详情</span>
      </div>
      <div class="middle-item" :class="{'middle-item-active': activeTab === 2}" @click="clickTab(2)">
        <span>评价</span>
      </div>
    </div>
    <div class="header-right">
      <i class="iconfont" :style="{background: opacity === 1 ? 'white' : '', color: opacity === 1 ? '#333' : ''}">&#xe622;</i>
      <i class="iconfont" :style="{background: opacity === 1 ? 'white' : '', color: opacity === 1 ? '#333' : ''}">&#xe604;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      activeTab: 0
    }
  },
  props: {
    scrollY: {
      default () {
        return 0
      }
    }
  },
  computed: {
    opacity () {
      if (this.scrollY < 0) {
        return this.scrollY < -170 ? 1 : Math.abs(this.scrollY) / 170
      }
    }
  },
  methods: {
    clickTab (n) {
      this.activeTab = n
      this.$emit('clickTab', n)
    }
  }
}
</script>

<style lang="less" scoped>
  .goods-header{
    position: relative;
    display: flex;
    justify-content: center;
    height: 88px;
    .header-left {
      position: absolute;
      top: 50%;
      left: 27px;
      transform: translateY(-50%);
      i{
        display: inline-block;
        width:60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border-radius: 30px;
        background: RGBA(0, 0, 0, .1);
        color:white;
      }
    }
    .header-middle{
      display: flex;
      .middle-item{
        position: relative;
        display: flex;
        align-items: center;
        color:#666;
        &:not(:first-child){
          margin-left: 34px;
        }
        &-active{
          span{
            color:#aa8a59;
          }
          &::after {
            position: absolute;
            content: '';
            bottom: 0;
            left: 0;
            width: 100%;
            height: 0;
            border-bottom: 3px solid #aa8a59;
          }
        }
      }
    }
    .header-right {
      position: absolute;
      top: 50%;
      right: 27px;
      transform: translateY(-50%);
      i{
        display: inline-block;
        width:60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border-radius: 30px;
        background: RGBA(0, 0, 0, .1);
        color:white;
        &:first-child{
          margin-right:30px;
        }
      }
    }
  }
</style>
