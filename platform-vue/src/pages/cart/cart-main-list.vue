<template>
  <div class="cart-main-list">
    <cart-main-list-group
      :key="key"
      :group="item"
      v-for="(item, key) in list"
      @goods-number="goodsNumber"
      @select="select"
      @select-group="selectGroup"></cart-main-list-group>
  </div>
</template>

<script type="text/ecmascript-6">
import cartMainListGroup from './cart-main-list-group'

export default {
  props: ['list'],
  components: {
    cartMainListGroup
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
