<template>
  <div class="goods-cell">
    <div class="cell-left">
      <span>{{title}}</span>
    </div>
    <div class="cell-mid">
      <slot></slot>
    </div>
    <div class="cell-right">
      <i class="iconfont">&#xe632;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['title']
}
</script>

<style lang="less" scoped>
  .goods-cell{
    display: flex;
    min-height: 80px;
    padding:0 28px;
    .cell-left{
      display: flex;
      align-items: center;
      flex-basis: 93px;
      span{
        color:#666;
      }
    }
    .cell-mid{
      display: flex;
      align-items: center;
      flex:1;
    }
    .cell-right{
      display: flex;
      justify-content: flex-end;
      align-items: center;
      flex-basis: 40px;
      i{
        font-size: 24px;
        color:#666;
      }
    }
  }
</style>
