<template>
  <div data-tag="recommend" class="home-banner">
    <cube-slide :data="list">
      <cube-slide-item v-for="(item, index) in list" :key="index">
        <div style="height: 5.3333rem">
          <img :src="item.image" alt="">
        </div>
      </cube-slide-item>
    </cube-slide>
    <div class="banner-policy">
      <div class="policy-item">
        <i class="iconfont">&#xe619;</i>
        <span>网易自营品牌</span>
      </div>
      <div class="policy-item">
        <i class="iconfont">&#xe619;</i>
        <span>30天无忧退货</span>
      </div>
      <div class="policy-item">
        <i class="iconfont">&#xe619;</i>
        <span>24小时快速退款</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['list']
}
</script>

<style lang="less" scoped>
  .home-banner{
    margin-bottom:20px;
    background: white;
    .banner-policy{
      display: flex;
      align-items: center;
      height: 74px;
      padding:0 27px;
      .policy-item{
        display: flex;
        align-items: center;
        flex:1;
        i{
          color:#b4282d;
        }
        span{
          padding-left:10px;
          color:#333;
        }
      }
    }
  }
</style>
