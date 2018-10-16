<template>
  <div class="goods-evaluate">
    <div class="evaluate-header">
      <div class="header-left">
        <span>用户评价（1763）</span>
      </div>
      <div class="header-right">
        <span>98%满意</span>
        <i class="iconfont">&#xe632;</i>
      </div>
    </div>
    <div class="evaluate-content">
      <cube-scroll ref="scroll" direction="horizontal">
        <div class="content-wrap">
          <div class="wrap-item">
            <evaluate-card></evaluate-card>
          </div>
          <div class="wrap-item">
            <evaluate-card></evaluate-card>
          </div>
        </div>
      </cube-scroll>
    </div>
    <div class="evaluate-footer">
      <span>查看更多</span>
      <i class="iconfont">&#xe632;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import evaluateCard from './common/evaluate-card'
export default {
  components: {
    evaluateCard
  }
}
</script>

<style lang="less">
  .goods-evaluate{
    margin-bottom: 10px;
    .evaluate-header{
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 99px;
      padding:0 28px;
      background: white;
      .header-left{
        display: flex;
        align-items: center;
        span{
          font-size: 28px;
          font-weight: 600;
          color:#333;
        }
      }
      .header-right{
        display: flex;
        align-items: center;
        color:#aa8a59;
        i{
          font-size: 24px;
        }
      }
    }
    .evaluate-content{
      background: white;
      .cube-scroll-content {
        display: inline-block;
        .content-wrap {
          white-space: nowrap;
          .wrap-item {
            display: inline-block;
            width: 8.2533rem;
            height: 4.6933rem;
            margin-left: .373rem;
            &:last-child{
              margin-right:.373rem;
            }
          }
        }
      }
    }
    .evaluate-footer{
      display: flex;
      justify-content: center;
      align-items: center;
      height: 99px;
      background: white;
      color:#aa8a59;
      i{
        font-size: 24px;
      }
    }
  }
</style>
