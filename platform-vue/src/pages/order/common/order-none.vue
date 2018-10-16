<template>
  <div class="order-none">
    <img class="ignore" src="../../../static/images/order/order_none.png" alt="">
    <p>{{text}}</p>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['text']
}
</script>

<style lang="less" scoped>
  .order-none{
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top:220px;
    .ignore{
      width:2.3055rem;
      height: 2.3055rem;
    }
    p{
      padding-top:34px;
      color:#999;
    }
  }
</style>
