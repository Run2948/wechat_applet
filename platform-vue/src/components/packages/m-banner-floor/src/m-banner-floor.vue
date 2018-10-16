<template>
  <div class="m-banner-floor">
    <div class="floor-header" :style="{backgroundImage: `url(${banner})`, color: color}">
      <h3>{{title}}</h3>
      <span :style="{background: background}">查看更多</span>
    </div>
    <div class="floor-view">
      <slot></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'm-banner-floor',
  props: ['title', 'banner', 'color', 'background']
}
</script>

<style lang="less" scoped>
  @import '../../../../static/less/index';
  .m-banner-floor{
    background: white;
    .floor-header{
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: 260px;
      background: center no-repeat;
      background-size: 100% 100%;
      h3{
        font-size: 38px;
      }
      span{
        display: inline-block;
        height: 56px;
        width:242px;
        margin-top:20px;
        line-height: 56px;
        text-align: center;
      }
    }
    .floor-view{
      padding:28px 0;
      overflow-x: hidden;
    }
  }
</style>
