<template>
  <div class="cart-checkout">
    <div class="checkout-info">
      <div class="info-left" @click="selectAll">
        <i class="iconfont" v-html="cartInfo.isAll ? '&#xe65f;' : '&#xe660;'" :style="{color: !cartInfo.isAll ? '#666' : ''}"></i>
        <span>全选</span>
      </div>
      <div class="info-right" v-if="!cartInfo.model">
        <span class="right-text">合计</span>
        <span class="right-price">￥{{cartInfo.price}}.00</span>
      </div>
    </div>
    <div class="checkout-action" @click="cartInfo.model ? deleteGoods() : checkout()">
      <span v-if="!cartInfo.model">去结算({{cartInfo.quantity}})</span>
      <span v-if="cartInfo.model">删除</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['cartInfo'],
  methods: {
    selectAll () {
      this.$emit('select-all')
    },
    deleteGoods () {
      this.$emit('delete-goods', this.cartInfo.select)
    },
    checkout () {
      this.$emit('checkout', this.cartInfo.select)
    }
  }
}
</script>

<style lang="less" scoped>
  .cart-checkout{
    position: relative;
    display: flex;
    height: 110px;
    background: white;
    .checkout-info{
      position: relative;
      flex:1;
      .info-left{
        position: absolute;
        top:50%;
        left:30px;
        transform: translateY(-50%);
        display: flex;
        align-items: center;
        .iconfont{
          color:#b60a0a;
        }
      }
      .info-right{
        position: absolute;
        top:50%;
        right:30px;
        transform: translateY(-50%);
        display: flex;
        align-items: center;
        .right-price{
          color:#b60a0a;
        }
      }
    }
    .checkout-action{
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
