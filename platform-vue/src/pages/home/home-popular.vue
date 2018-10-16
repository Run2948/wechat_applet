<template>
  <div class="home-popular">
    <m-banner-floor title="人气推荐 · 好物精选" :banner="banner" color="#B4A078" background="#F4E9CB">
      <cube-scroll ref="scroll" direction="horizontal">
        <div class="home-popular-wrap">
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
      banner: '//yanxuan-static.nosdn.127.net/hxm/yanxuan-wap/p/20161201/style/img/icon-normal/bitmap2-d626e0b52a.png'
    }
  },
  props: ['list']
}
</script>

<style lang="less">
  .home-popular {
    margin-bottom: 20px;
    .cube-scroll-content {
      display: inline-block;
      .home-popular-wrap {
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
