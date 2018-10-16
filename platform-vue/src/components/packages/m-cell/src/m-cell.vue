<template>
  <div class="m-cell border-1px-top">
    <div class="cell-left">
      <span>{{title}}</span>
    </div>
    <div class="cell-right">
      <slot></slot>
      <span>{{value}}</span>
      <i class="iconfont" v-if="isLink">&#xe632;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  name: 'm-cell',
  props: ['title', 'value', 'isLink']
}
</script>

<style lang="less" scoped>
  .m-cell{
    display: flex;
    justify-content: space-between;
    align-items: center;
    min-height: 133px;
    .cell-left{
      display: flex;
      align-items: center;
      span{
        font-size: 30px;
        color:#333;
      }
    }
    .cell-right{
      display: flex;
      align-items: center;
      font-size: 30px;
      color:#999;
    }
  }
</style>
