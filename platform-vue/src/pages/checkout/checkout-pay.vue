<template>
  <div class="checkout-pay">
    <div class="pay-info">
      <span class="info-text">合计</span>
      <span class="info-price">￥1236.00</span>
    </div>
    <div class="pay-action" @click="pay">
      <span>去支付</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  methods: {
    pay () {
      this.$emit('pay')
    }
  }
}
</script>

<style lang="less" scoped>
  .checkout-pay{
    position: relative;
    display: flex;
    height: 110px;
    background: white;
    .pay-info{
      display: flex;
      align-items: center;
      flex:1;
      padding-left:28px;
      .info-price{
        font-size: 28px;
        color:#b60a0a;
      }
    }
    .pay-action{
      display: flex;
      align-items: center;
      justify-content: center;
      flex-basis: 210px;
      background: #b60a0a;
      span{
        color:white;
      }
    }
  }
</style>
