<template>
  <div class="transaction-info">
    <div class="info-item">
      <div class="item-title">
        <span>订单金额</span>
      </div>
      <div class="item-text">
        <p>78元</p>
      </div>
    </div>
    <div class="info-item">
      <div class="item-title">
        <span>订单编号</span>
      </div>
      <div class="item-text">
        <p>{{transactionInfo.transactionNumber}}</p>
      </div>
    </div>
    <div class="info-item">
      <div class="item-title">
        <span>收货地址</span>
      </div>
      <div class="item-text">
        <p>中国 广东 佛山 顺德 北滘镇莘村中学（528300） 邓海标 13680065830</p>
      </div>
    </div>
    <div class="info-item">
      <div class="item-title">
        <span>送货时间</span>
      </div>
      <div class="item-text">
        <p>不限送货时间</p>
      </div>
    </div>
    <div class="info-item">
      <div class="item-title">
        <span>支付方式</span>
      </div>
      <div class="item-text">
        <p>微信支付</p>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['transactionInfo']
}
</script>

<style lang="less" scoped>
  .transaction-info{
    padding:43px;
    background: white;
    .info-item{
      display: flex;
      .item-title{
        flex-basis: 144px;
        span{
          color:#666;
        }
      }
      .item-text{
        flex:1;
        p{
          color:#333;
        }
      }
      &:not(:first-child){
        padding-top:20px;
      }
    }
  }
</style>
