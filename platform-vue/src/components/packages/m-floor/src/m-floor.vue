<template>
  <div class="m-floor">
    <div class="floor-header">
      <span>{{title}}</span>
      <i class="iconfont" v-show="isLink">&#xe63a;</i>
    </div>
    <div class="floor-view" :style="styles">
      <slot></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'm-floor',
  props: ['title', 'isLink', 'styles']
}
</script>

<style lang="less" scoped>
  @import '../../../../static/less/index';
  .m-floor{
    background: white;
    .floor-header{
      display: flex;
      align-items: center;
      justify-content: center;
      height: 105px;
      span{
        font-size: 32px;
        color:#333;
      }
      i{
        color:#666;
        padding-left:10px;
      }
    }
    .floor-view{
      padding:0 14px;
    }
  }
</style>
