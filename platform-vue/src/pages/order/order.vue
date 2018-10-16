<template>
  <page :better-scroll="true">
    <template slot="extra-top">
      <account-warm></account-warm>
      <order-type
        :type="type"
        @current-type="currentType"></order-type>
    </template>
    <order-status-all v-if="type === 0"></order-status-all>
    <order-status-uncleared v-if="type === 1"></order-status-uncleared>
    <order-status-dispatched v-if="type === 2"></order-status-dispatched>
    <order-status-refund v-if="type === 3"></order-status-refund>
    <order-status-received v-if="type === 4"></order-status-received>
  </page>
</template>

<script type="text/ecmascript-6">
import accountWarm from '../common/account-warm'
import orderType from './order-type'
import orderStatusAll from './order-status-all'
import orderStatusUncleared from './order-status-uncleared'
import orderStatusDispatched from './order-status-dispatched'
import orderStatusRefund from './order-status-refund'
import orderStatusReceived from './order-status-received'
export default{
  data () {
    return {
      type: parseInt(this.$route.query.from)
    }
  },
  components: {
    accountWarm,
    orderType,
    orderStatusAll,
    orderStatusUncleared,
    orderStatusDispatched,
    orderStatusRefund,
    orderStatusReceived
  },
  methods: {
    currentType (n) {
      this.type = n
    }
  }
}
</script>
