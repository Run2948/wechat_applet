<template>
  <div class="goods-details" id="goods-details">
    <div class="details-tab">
      <div class="tab-item tab-item-active">
        <span>功能详情</span>
      </div>
      <div class="tab-item">
        <span>常见问题</span>
      </div>
    </div>
    <div class="details-content">
      <div class="content-item" :key="key" v-for="(item, key) in detailsSet.summary">
        <img :src="item.img" alt="">
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['detailsSet']
}
</script>

<style lang="less" scoped>
.goods-details{
  background: white;
  .details-tab{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 83px;
    .tab-item{
      span{
        color:#666;
      }
      &:not(:first-child){
        margin-left:88px;
      }
      &-active{
        span{
          font-weight: 700;
          color:#aa8a59;
        }
      }
    }
  }
  .details-content{
    .content-item{
      img{
        display: block;
        width: 100%;
      }
    }
  }
}
</style>
