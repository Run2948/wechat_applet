<template>
  <page :better-scroll="true">
    <account-warm slot="extra-top"></account-warm>
    <template slot="bottom">
      <checkout-address-tip></checkout-address-tip>
      <checkout-pay @pay="payHandler"></checkout-pay>
    </template>
    <checkout-address
        :address="checkoutInfo.address"
        @click.native="$go('/checkout/address')"></checkout-address>
    <checkout-pay-way></checkout-pay-way>
    <checkout-goods-details></checkout-goods-details>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapActions} from 'vuex'
import accountWarm from '../common/account-warm'
import checkoutPay from './checkout-pay'
import checkoutAddressTip from './checkout-address-tip'
import checkoutAddress from './checkout-address'
import checkoutPayWay from './checkout-pay-way'
import checkoutGoodsDetails from './checkout-goods-details'
export default{
  data () {
    return {
      checkoutId: this.$route.params.id,
      checkoutInfo: {
        address: {}
      }
    }
  },
  components: {
    accountWarm,
    checkoutPay,
    checkoutAddressTip,
    checkoutAddress,
    checkoutPayWay,
    checkoutGoodsDetails
  },
  methods: {
    ...mapActions('cart', ['getCheckoutInfo', 'pay']),
    showDialog (fn) {
      this.$createDialog({
        type: 'confirm',
        content: '便宜不等人，请三思而行~',
        confirmBtn: {
          text: '继续支付',
          active: true,
          disabled: false,
          href: 'javascript:;'
        },
        cancelBtn: {
          text: '取消',
          active: false,
          disabled: false,
          href: 'javascript:;'
        },
        onConfirm: () => {
          // @TODO
        },
        onCancel: () => {
          fn()
        }
      }).show()
    },
    payHandler () {
      this.pay(this.checkoutId).then(() => {
        this.$go('/transaction/25875', false)
      })
    }
  },
  created () {
    this.getCheckoutInfo(this.checkoutId).then(res => {
      this.checkoutInfo.address = res.address
    })
  },
  beforeRouteLeave (to, from, next) {
    if (to.path === '/cart') {
      this.showDialog(() => {
        next()
      })
    } else {
      next()
    }
  }
}
</script>
