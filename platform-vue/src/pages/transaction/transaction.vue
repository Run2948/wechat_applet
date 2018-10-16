<template>
  <page :better-scroll="true">
    <transaction-header slot="top"></transaction-header>
    <transaction-result></transaction-result>
    <transaction-info :transactionInfo="transactionInfo"></transaction-info>
    <transaction-action
      @check="check"
      slot="bottom"></transaction-action>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapActions} from 'vuex'
import transactionHeader from './transaction-header'
import transactionResult from './transaction-result'
import transactionInfo from './transaction-info'
import transactionAction from './transaction-action'
export default {
  data () {
    return {
      transactionId: this.$route.query.id,
      transactionInfo: {}
    }
  },
  components: {
    transactionHeader,
    transactionResult,
    transactionInfo,
    transactionAction
  },
  methods: {
    ...mapActions('cart', ['getTransactionInfo']),
    check () {
      this.$go('/order?from=2', false)
    }
  },
  created () {
    this.getTransactionInfo(this.transactionId).then(res => {
      this.transactionInfo = res
    })
  }
}
</script>
