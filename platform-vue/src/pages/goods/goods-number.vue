<template>
  <div class="goods-number">
    <goods-cell title="数量：" @click.native="updateGoodsNumber">
      <span>1</span>
    </goods-cell>
  </div>
</template>

<script type="text/ecmascript-6">
import goodsCell from './common/goods-cell'
import goodsNumberPanel from './goods-number-panel'

export default {
  components: {
    goodsCell,
    goodsNumberPanel
  },
  methods: {
    updateGoodsNumber () {
      this.$mPopup({
        mask: true
      }, (h) => {
        return h(goodsNumberPanel, {
          on: {
            change: this.changeHandler
          }
        })
      })
    },
    changeHandler (o) {
      this.$emit('change', o)
    }
  }
}
</script>

<style lang="less" scoped>
  .goods-number {
    margin-bottom: 10px;
    background: white;
  }
</style>
