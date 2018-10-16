<template>
  <div class="search-goods" @click="$go('/goods/1')">
    <div class="goods-top">
      <div class="top-pic" data-ratio="1:1">
        <img :src="info.pic" alt="">
      </div>
      <div class="top-desc">
        <p>{{info.desc}}</p>
      </div>
    </div>
    <div class="goods-bottom">
      <div class="bottom-name">
        <h4>{{info.name}}</h4>
      </div>
      <div class="bottom-price">
        <span>￥{{info.price}}</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['info']
}
</script>

<style lang="less" scoped>
  .search-goods{
    .goods-top{
      padding:0 28px;
      background: white;
      .top-pic {
        position: relative;
        height: 0;
        width:100%;
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
      .top-desc{
        p{
          padding:14px 0;
          white-space: nowrap;
          overflow-x: hidden;
          text-overflow: ellipsis;
          font-size: 28px;
          color:#845f3f;
        }
      }
    }
    .goods-bottom{
      padding:0 28px;
      background: #f7f7f7;
      .bottom-name{
        padding-top:15px;
        h4{
          white-space: nowrap;
          overflow-x: hidden;
          text-overflow: ellipsis;
          font-size: 32px;
          color:#333;
        }
      }
      .bottom-price{
        padding:15px 0;
        span{
          color:#a21c20;
        }
      }
    }
  }
</style>
