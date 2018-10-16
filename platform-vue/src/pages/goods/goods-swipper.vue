<template>
  <div class="goods-swipper">
    <cube-slide :data="swipperList">
      <cube-slide-item v-for="(item, index) in swipperList" :key="index">
        <div class="swipper-image">
          <img :src="item.image" alt="">
        </div>
      </cube-slide-item>
    </cube-slide>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['swipperList']
}
</script>

<style lang="less" scoped>
  .goods-swipper{
    .swipper-image{
      height: 10rem;
      img{
        width:100%;
        height: 100%;
      }
    }
  }
</style>
