<template>
  <div class="goods-spec">
    <goods-cell title="规格：" @click.native="updateGoodsSpec">
      <span>4G 米兰白</span>
    </goods-cell>
  </div>
</template>

<script type="text/ecmascript-6">
import goodsCell from './common/goods-cell'
import goodsSpecPanel from './goods-spec-panel'
export default {
  components: {
    goodsCell,
    goodsSpecPanel
  },
  methods: {
    updateGoodsSpec () {
      this.$mPopup({
        mask: true
      }, (h) => {
        return h(goodsSpecPanel)
      })
    }
  }
}
</script>

<style lang="less" scoped>
  .goods-spec{
    margin-bottom:10px;
    background: white;
  }
</style>
