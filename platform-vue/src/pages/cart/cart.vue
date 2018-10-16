<template>
  <page :better-scroll="betterScroll">
    <cart-header
        slot="top"
        :headerRight="headerRight"
        :cartInfo="cartInfo"
        @change-model="changeModel"></cart-header>
    <cart-checkout
      slot="bottom"
      :cartInfo="cartInfo"
      @checkout="checkoutHandler"
      @delete-goods="deleteGoods"
      @select-all="selectAll"
      v-if="checkoutVisible"></cart-checkout>
    <cart-default v-if="defaultVisible"></cart-default>
    <cart-main
        v-if="mianVisible"
        :list="cartGroup"
         @goods-number="goodsNumber"
        @select="select"
        @select-group="selectGroup"></cart-main>
  </page>
</template>

<script type="text/ecmascript-6">
import cartHeader from './cart-header'
import cartCheckout from './cart-checkout'
import cartDefault from './cart-default'
import cartMain from './cart-main'
import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'

export default {
  computed: {
    ...mapState('user', ['isLogin']),
    ...mapState('cart', ['cart']),
    ...mapGetters('cart', ['cartGroup', 'cartInfo']),
    betterScroll () {
      return this.isLogin && this.cartGroup.length > 0
    },
    headerRight () {
      return this.isLogin && this.cartGroup.length > 0
    },
    defaultVisible () {
      return !this.isLogin || this.cartGroup.length === 0
    },
    mianVisible () {
      return this.isLogin
    },
    checkoutVisible () {
      return this.isLogin && this.cartGroup.length > 0
    }
  },
  components: {
    cartHeader,
    cartCheckout,
    cartDefault,
    cartMain
  },
  methods: {
    ...mapMutations('cart', ['TOGGLE_MODEL', 'SELECT_GOODS', 'SELECT_GROUP', 'SELECT_ALL']),
    ...mapActions('cart', ['updateUserCartGoods', 'deleteUserCartGoods', 'checkout']),
    changeModel () {
      this.TOGGLE_MODEL()
    },
    checkoutHandler (a) {
      if (a.length) {
        this.checkout(a).then(res => {
          this.$go(`/checkout?id=${res}`)
        })
      } else {
        this.$toast('商品数量不能少于一件', 'warn')
      }
    },
    deleteGoods (o) {
      this.deleteUserCartGoods(o)
    },
    goodsNumber (o) {
      if (o.type === 'sub' && o.number === 1) {
        this.$toast('商品数量不能少于一件', 'warn')
        return
      }
      this.updateUserCartGoods(o)
    },
    select (o) {
      this.SELECT_GOODS(o)
    },
    selectGroup (o) {
      this.SELECT_GROUP(o)
    },
    selectAll () {
      this.SELECT_ALL()
    }
  }
}
</script>
