<template>
  <div class="home-release">
    <m-banner-floor title="新品首发" :banner="banner" color="#8BA0B6" background="#D8E5F1">
      <cube-scroll ref="scroll" direction="horizontal">
        <div class="home-release-wrap">
          <div class="wrap-item" :key="key" v-for="(item, key) in list">
            <m-goods :info="item"></m-goods>
          </div>
        </div>
      </cube-scroll>
    </m-banner-floor>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      banner: '//yanxuan-static.nosdn.127.net/hxm/yanxuan-wap/p/20161201/style/img/icon-normal/bitmap-d4f7b37e32.png'
    }
  },
  props: ['list']
}
</script>

<style lang="less">
  .home-release {
    margin-bottom: 20px;
    .cube-scroll-content {
      display: inline-block;
      .home-release-wrap {
        white-space: nowrap;
        .wrap-item {
          display: inline-block;
          width: 3.773rem;
          height: 200px;
          margin-left: .373rem;
          &:last-child{
            margin-right:.373rem;
          }
        }
      }
    }
  }
</style>
