<template>
  <div class="checkout-goods-details">
    <div class="details-header">
      <h4>商品详情</h4>
    </div>
    <div class="details-content">
      <div class="content-goods border-1px-top border-1px-bottom">
        <checkout-goods></checkout-goods>
      </div>
      <div class="content-cells">
        <checkout-cell title="优惠券/优惠码" :is-link="true"></checkout-cell>
        <checkout-cell title="配送方式" value="快递配送" :is-link="true"></checkout-cell>
        <checkout-cell title="送货时间" value="不限时间" :is-link="true"></checkout-cell>
        <checkout-cell title="发票类型" value="个人电子发票" :is-link="true"></checkout-cell>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import checkoutGoods from './common/checkout-goods'
import checkoutCell from './common/checkout-cell'
export default {
  components: {
    checkoutGoods,
    checkoutCell
  }
}
</script>

<style lang="less" scoped>
  .checkout-goods-details{
    background: white;
    .details-header{
      display: flex;
      align-items: center;
      height: 88px;
      padding:0 38px;
      h4{
        font-size: 32px;
        font-weight: 600;
        color:#666;
      }
    }
    .details-content{
      .content-goods{
        padding:0 28px;
      }
      .content-cells{
        padding:0 28px;
      }
    }
  }
</style>
