<template>
  <div class="goods-by-category-list-goods" @click="$go('/goods/1')">
    <div class="goods-pic" data-ratio="1:1">
      <img :src="info.pic" alt="">
    </div>
    <div class="goods-desc">
      <p>{{info.desc}}</p>
    </div>
    <div class="goods-name">
      <h4>{{info.name}}</h4>
    </div>
    <div class="goods-price">
      <span>￥{{info.price}}</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['info']
}
</script>

<style lang="less" scoped>
  .goods-by-category-list-goods{
    .goods-pic {
      position: relative;
      height: 0;
      width:100%;
      &[data-ratio="1:1"] {
        padding-top:calc(100% * 1 / 1);
      }
      *{
        position: absolute;
        left:0;
        top:0;
        width:100%;
        height: 100%;
      }
    }
    .goods-desc{
      background: #f6f2f1;
      p{
        padding:14px 0;
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        font-size: 28px;
        color:#845f3f;
      }
    }
    .goods-name{
      padding-top:15px;
      h4{
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        font-size: 32px;
        color:#333;
      }
    }
    .goods-price{
      padding:15px 0;
      span{
        color:#a21c20;
      }
    }
  }
</style>
