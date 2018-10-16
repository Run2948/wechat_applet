<template>
  <div class="checkout-goods">
    <div class="goods-pic">
      <img class="ignore" src="https://static.home.mi.com/app/shop/img?id=shop_f4e9e198a07c9888cd3a4295a0f54b0b.jpeg&w=240&h=240" alt="">
    </div>
    <div class="goods-info">
      <div class="info-name">
        <h3>米家机器人扫地边刷</h3>
      </div>
    </div>
    <div class="goods-price-num">
      <span class="single-price">￥39</span>
      <span class="num">x1</span>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .checkout-goods{
    display: flex;
    .goods-pic{
      flex-basis: 166px;
      height: 166px;
      .ignore{
        width:100%;
        height:100%;
      }
    }
    .goods-info{
      flex:1;
      .info-name{
        padding-top:20px;
        h3{
          font-weight: 600;
          color:#333;
        }
      }
    }
    .goods-price-num{
      display: flex;
      flex-direction: column;
      align-items: flex-end;
      flex-basis: 70px;
      .single-price{
        padding-top:20px;
        color:#666;
      }
      .num{
        padding-top:20px;
        color:#666;
      }
    }
  }
</style>
