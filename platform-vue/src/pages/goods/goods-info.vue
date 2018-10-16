<template>
  <div class="goods-info">
    <div class="info-name">
      <h4>小米8 SE</h4>
    </div>
    <div class="info-desc">
      <p>骁龙710处理器 / AI 超感光双摄 / 5.88" 全面屏 / 前置2000万柔光自拍 / 三星 AMOLED 屏幕 / 3120mAh 长续航</p>
    </div>
    <div class="info-price">
      <div class="price-pay">
        <span>￥</span>
        <span>1799</span>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .goods-info{
    padding:28px;
    margin-bottom: 10px;
    background: white;
    .info-name{
      h4{
        font-size: 34px;
        font-weight: 600;
        color:#333;
      }
    }
    .info-desc{
      padding:20px 0;
      p{
        color:#666;
      }
    }
    .info-price{
      .price-pay{
        display: flex;
        align-items: flex-end;
        span{
          &:nth-child(1){
            color:#b60a0a;
          }
          &:nth-child(2){
            font-size: 40px;
            font-weight: 600;
            color:#b60a0a;
          }
        }
      }
    }
  }
</style>
