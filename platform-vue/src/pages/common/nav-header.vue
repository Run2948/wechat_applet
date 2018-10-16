<template>
  <div class="m-nav-header">
    <div class="header-left">
      <i class="iconfont" @click="$go('/home')">&#xe622;</i>
    </div>
    <h3>{{title ? title : '网易严选'}}</h3>
    <div class="header-right">
      <i class="iconfont" @click="$go('/search')">&#xe62b;</i>
      <i class="iconfont" @click="$go('/cart')">&#xe602;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['title']
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';

  .m-nav-header {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    background: white;
    .header-left {
      position: absolute;
      top: 50%;
      left: 27px;
      transform: translateY(-50%);
      i {
        font-size: 55px;
        color: #333;
      }
    }
    h3 {
      font-size: 34px;
      font-size: @title-color;
    }
    .header-right {
      position: absolute;
      top: 50%;
      right: 27px;
      transform: translateY(-50%);
      i {
        font-size: 55px;
        color: #333;
        &:not(:last-child) {
          padding-right: 27px;
        }
      }
    }
  }
</style>
