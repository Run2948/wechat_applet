<template>
  <div class="goods-spec-panel">
    <div class="panel-goods">
      <div class="goods-img">
        <img src="https://shop.io.mi-img.com/app/shop/img?id=shop_5f926ad08c9604219568908eafc90a8e.jpeg" alt="">
      </div>
      <div class="goods-info">
        <div class="info-name">
          <p>路易丝漫男式纯羊毛抗皱三防西装套装</p>
        </div>
        <div class="info-price">
          <span>￥199.00</span>
        </div>
      </div>
    </div>
    <div class="panel-section">
      <div class="section-title">
        <span>容量</span>
      </div>
      <div class="section-content">
        <span>16G</span>
        <span>32G</span>
      </div>
    </div>
    <div class="panel-section">
      <div class="section-title">
        <span>颜色</span>
      </div>
      <div class="section-content">
        <span>米兰白</span>
        <span>太空灰</span>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  @import '../../static/less/index';
  .goods-spec-panel{
    min-height: 700px;
    padding:0 27px;
    background: white;
    .panel-goods{
      display: flex;
      align-items: center;
      padding:27px 0;
      .goods-img{
        flex-basis: 127px;
        height: 127px;
        img{
          height: 100%;
          width: 100%;
        }
      }
      .goods-info{
        display: flex;
        flex-direction: column;
        justify-content: center;
        padding-left:20px;
        .info-name{
          p{
            font-size: 28px;
          }
        }
        .info-price{
          span{
            font-size: 34px;
            font-weight: 600;
            color:#b6090b;
          }
        }
      }
    }
    .panel-section{
      .section-title{
        display: flex;
        align-items: center;
        height: 62px;
        span{
          font-size: 28px;
          color:#666;
        }
      }
      .section-content{
        display: flex;
        padding:20px 0;
        span{
          flex-grow: 0;
          height: 50px;
          line-height: 50px;
          padding: 0 22px;
          margin: 0 20px 20px 0;
          box-sizing: border-box;
          border-radius: 5px;
          border:1px solid @border-color;
          font-size: 28px;
          color: #666;
        }
      }
    }
  }
</style>
