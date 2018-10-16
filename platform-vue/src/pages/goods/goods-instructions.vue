<template>
  <div class="goods-instructions">
    <goods-cell title="说明：">
      <div class="instructions">
        <div class="instructions-item">
          <i class="iconfont">&#xe619;</i>
          <p>小米有品甄选精品</p>
        </div>
        <div class="instructions-item">
          <i class="iconfont">&#xe619;</i>
          <p>由 小米 发货并提供售后</p>
        </div>
        <div class="instructions-item">
          <i class="iconfont">&#xe619;</i>
          <p>支持7天无理由退货（请参考商品详情-常见问题）</p>
        </div>
      </div>
    </goods-cell>
  </div>
</template>

<script type="text/ecmascript-6">
import goodsCell from './common/goods-cell'
export default {
  components: {
    goodsCell
  }
}
</script>

<style lang="less" scoped>
  .goods-instructions{
    margin-bottom:10px;
    background: white;
    .instructions{
      padding:20px 0;
      .instructions-item{
        display: flex;
        i{
          font-size: 24px;
          color:#ab8959;
        }
        p{
          padding-left:5px;
          color:#333;
        }
        &:not(:first-child){
          padding-top:20px;
        }
      }
    }
  }
</style>
