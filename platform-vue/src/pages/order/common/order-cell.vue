<template>
  <div class="order-cell">
    <div class="cell-time-status">
      <div class="cell-time">
        <span>2018-08-06 16:57</span>
      </div>
      <div class="cell-status">
        <span>已支付支付</span>
      </div>
    </div>
    <div class="cell-goods border-1px-top">
      <div class="goods-item">
        <div class="item-pic">
          <img class="ignore" src="https://static.home.mi.com/app/shop/img?id=shop_f4e9e198a07c9888cd3a4295a0f54b0b.jpeg&w=240&h=240" alt="">
        </div>
        <div class="item-info">
          <div class="info-name">
            <h3>米家机器人扫地边刷</h3>
          </div>
        </div>
        <div class="item-price-num">
          <span class="single-price">￥39</span>
          <span class="num">x1</span>
        </div>
      </div>
      <div class="goods-item">
        <div class="item-pic">
          <img class="ignore" src="https://static.home.mi.com/app/shop/img?id=shop_4fc6ea862ee2da95bb2926c635ccb85f.jpeg&w=240&h=240" alt="">
        </div>
        <div class="item-info">
          <div class="info-name">
            <h3>米家扫地机器人主刷罩</h3>
          </div>
        </div>
        <div class="item-price-num">
          <span class="single-price">￥29</span>
          <span class="num">x1</span>
        </div>
      </div>
    </div>
    <div class="cell-price">
      <div class="price-total">
        <p>共2件商品，总金额<span>￥78</span></p>
      </div>
      <div class="price-transfer">
        <span>（含运费￥10）</span>
      </div>
    </div>
    <div class="cell-action border-1px-top">
      <div class="expire-time">
        <span>24小时候订单将关闭</span>
      </div>
      <div class="action-btns">
        <span>取消</span>
        <span>去支付</span>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .order-cell{
    padding:0 28px;
    background: white;
    .cell-time-status{
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 90px;
      .cell-time{
        span{
          color:#666;
        }
      }
      .cell-status{
        span{
          color:#845f3f;
        }
      }
    }
    .cell-goods{
      .goods-item{
        display: flex;
        padding-top: 20px;
        .item-pic{
          flex-basis: 166px;
          height: 166px;
          .ignore{
            width:100%;
            height:100%;
          }
        }
        .item-info{
          flex:1;
          .info-name{
            padding-top:20px;
            h3{
              font-weight: 600;
              color:#333;
            }
          }
        }
        .item-price-num{
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
        &:last-child{
          padding-bottom: 20px;
        }
      }
    }
    .cell-price{
      .price-total{
        padding-top:35px;
        p{
          text-align: right;
          color:#333;
          span{
            font-size: 28px;
            font-weight: 600;
            color:rgb(182, 9, 9);
          }
        }
      }
      .price-transfer{
        padding:20px 0;
        text-align: right;
        color:#333;
      }
    }
    .cell-action{
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 113px;
      .expire-time{
        color:#666;
      }
      .action-btns{
        display: flex;
        align-items: center;
        span{
          display: inline-block;
          width:170px;
          height: 60px;
          box-sizing: border-box;
          line-height: 60px;
          text-align: center;
          border-radius: 5px;
          border:1px solid rgb(204, 204, 204);
          color:#666;
          &:not(:first-child){
            margin-left:20px;
            color:rgb(159, 128, 82);
            border-color: rgb(159, 128, 82);
          }
        }
      }
    }
  }
</style>
