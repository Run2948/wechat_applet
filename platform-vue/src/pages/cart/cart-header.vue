<template>
  <div class="cart-header">
    <h3>购物车</h3>
    <div
      class="header-right"
      @click="changeModel"
      v-if="headerRight">
      <span>{{cartInfo.model ? '完成' : '编辑'}}</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['cartInfo', 'headerRight'],
  methods: {
    changeModel () {
      this.$emit('change-model')
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';
  .cart-header{
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    background: white;
    h3{
      font-size: 30px;
      color: #333;
    }
    .header-right{
      position: absolute;
      top:50%;
      right:30px;
      transform: translateY(-50%);
      span{
        color:#b60a0a;
      }
    }
  }
</style>
