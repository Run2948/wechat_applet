<template>
  <div class="checkout-cell">
    <div class="cell-left">
      <span>{{title}}</span>
    </div>
    <div class="cell-right">
      <span>{{value}}</span>
      <i class="iconfont" v-if="isLink">&#xe632;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['title', 'value', 'isLink']
}
</script>

<style lang="less" scoped>
  @import "../../../static/less/index";
  .checkout-cell{
    position: relative;
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 88px;
    .cell-left{
      font-size: 28px;
      font-weight: 600;
      color:#333;
    }
    .cell-right{
      display: flex;
      align-items: center;
      color:#666;
    }
    &:not(:first-child){
      &::before {
        position: absolute;
        content: '';
        top: 0;
        left: 0;
        width: 100%;
        height: 0;
        border-top: 1px solid @border-color;
        transform: scaleY(0.5);
      }
    }
  }
</style>
