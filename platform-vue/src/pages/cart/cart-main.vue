<template>
  <div class="cart-main">
    <cart-main-list
        :list="list"
        @goods-number="goodsNumber"
        @select="select"
        @select-group="selectGroup"></cart-main-list>
    <cart-main-recommend></cart-main-recommend>
  </div>
</template>

<script type="text/ecmascript-6">
import cartMainList from './cart-main-list'
import cartMainRecommend from './cart-main-recommend'
export default {
  props: ['list'],
  components: {
    cartMainList,
    cartMainRecommend
  },
  methods: {
    goodsNumber (o) {
      this.$emit('goods-number', o)
    },
    select (o) {
      this.$emit('select', o)
    },
    selectGroup (o) {
      this.$emit('select-group', o)
    }
  }
}
</script>
