<template>
  <div class="m-goods" @click="$go('/goods/1')">
    <div class="goods-pic" data-ratio="1:1">
      <img :src="info.img" alt="">
    </div>
    <div class="goods-name">
      <h4>{{info.name}}</h4>
    </div>
    <div class="goods-desc">
      <p>{{info.desc}}</p>
    </div>
    <div class="goods-price">
      <span>￥{{info.price}}</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  name: 'm-goods',
  props: ['info']
}
</script>

<style lang="less" scoped>
  .m-goods{
    .goods-pic {
      position: relative;
      height: 0;
      width:100%;
      margin-bottom: 14px;
      background: #f4f4f4;
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
    .goods-name{
      h4{
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        font-size: 32px;
        color:#333;
      }
    }
    .goods-desc{
      p{
        padding:14px 0;
        white-space: nowrap;
        overflow-x: hidden;
        text-overflow: ellipsis;
        font-size: 24px;
        color:#666;
      }
    }
    .goods-price{
      span{
        color:#a21c20;
      }
    }
  }
</style>
