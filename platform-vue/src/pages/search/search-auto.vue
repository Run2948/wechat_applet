<template>
  <div class="search-auto">
    <search-section title="大家都在看" styles="padding:0.37333rem;" v-if="recommend">
      <div class="auto-recommend">
        <div class="recommend-pic">
          <img class="ignore" src="http://img.youpin.mi-img.com/800_pic/c3c572d31db2e1531e4ad245b33885f1.png@base@tag=imgScale&h=350&w=350&et=1&eth=480&etw=480&etc=FFFFFF" alt="">
        </div>
        <div class="recommend-info">
          <h4>{{recommend.name}}</h4>
          <span>￥2499.00</span>
        </div>
        <div class="recommend-buy">
          <span>选购</span>
          <i class="iconfont">&#xe632;</i>
        </div>
      </div>
    </search-section>
    <search-section title="热门商品" styles="padding:0.37333rem;">
      <div class="auto-hot">
        <div class="hot-item border-1px-bottom"
          :key="key"
          v-for="(item, key) in searchList">
          <div class="item-left">
            <span>{{item.name}}</span>
          </div>
          <div class="item-right">
            <span>￥{{item.price}}</span>
          </div>
        </div>
      </div>
    </search-section>
  </div>
</template>

<script type="text/ecmascript-6">
import searchSection from './common/search-section'

export default {
  props: ['recommend', 'searchList'],
  components: {
    searchSection
  }
}
</script>

<style lang="less" scoped>
  .auto-recommend{
    display: flex;
    height: 110px;
    .recommend-pic{
      flex-basis: 110px;
      .ignore{
        width:100%;
        height: 100%;
      }
    }
    .recommend-info{
      display: flex;
      flex-direction: column;
      justify-content: center;
      flex:1;
      h4{
        font-size: 28px;
        color:#333;
      }
      span{
        padding-top:10px;
        color:#666;
      }
    }
    .recommend-buy{
      display: flex;
      align-items: center;
      flex-grow: 0;
      color:#b4282d;
    }
  }
  .auto-hot{
    .hot-item{
      display: flex;
      justify-content: space-between;
      height: 88px;
      .item-left{
        display: flex;
        align-items: center;
        span{
          font-size: 28px;
          color:#333;
        }
      }
      .item-right{
        display: flex;
        align-items: center;
        span{
          color:#666;
        }
      }
    }
  }
</style>
