<template>
  <div class="order-status-uncleared">
    <order-none text="目前没有待付款订单哦~"></order-none>
  </div>
</template>

<script type="text/ecmascript-6">
import orderNone from './common/order-none'
export default {
  components: {
    orderNone
  }
}
</script>
