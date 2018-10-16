<template>
  <div class="topic-banner">
    <cube-slide  :auto-play="false" :show-dots="false" :data="bannerList">
      <cube-slide-item v-for="(item, index) in bannerList" :key="index">
        <div class="banner-item">
          <img :src="item.image" alt="">
          <div class="item-content">
            <span>严选推荐</span>
            <h4>8月新品穿搭画册</h4>
            <p>服饰鞋包上新，季末折扣6折起</p>
          </div>
        </div>
      </cube-slide-item>
    </cube-slide>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['bannerList']
}
</script>

<style lang="less" scoped>
  .topic-banner {
    padding:28px 0;
    background: white;
    .banner-item{
      position: relative;
      height: 5.14667rem;
      img{
        width:100%;
        height: 100%;
      }
      .item-content{
        position: absolute;
        top:50%;
        left:50%;
        width:6.222rem;
        height: 2.63889rem;
        transform: translate(-50%, -50%);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        background: white;
        span{
          position: relative;
          display: inline-block;
          padding:0 5px;
          color:#666;
          &::before{
            position: absolute;
            content: '';
            top:50%;
            left:-0.32rem;
            transform: translateY(-50%);
            width:0.32rem;
            height: 1px;
            background: #666;
          }
          &::after{
            position: absolute;
            content: '';
            top:50%;
            right:-0.32rem;
            transform: translateY(-50%);
            width:0.32rem;
            height: 1px;
            background: #666;
          }
        }
        h4{
          margin-top:.21333rem;
          line-height: .64rem;
          font-size: .42667rem;
          font-weight: 600;
          color:#333;
        }
        p{
          margin-top: .0533rem;
        }
      }
    }
  }
</style>
