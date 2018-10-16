<template>
  <div class="center-order">
    <div class="order-header">
      <div class="header-left">
        <h4>我的订单</h4>
      </div>
      <div class="header-right" @click="$go('/order?from=0')">
        <span>全部订单</span>
        <i class="iconfont">&#xe632;</i>
      </div>
    </div>
    <div class="order-content border-1px-top">
      <div class="content-item" @click="$go('/order?from=1')">
        <i class="iconfont">&#xe636;</i>
        <span>待付款</span>
      </div>
      <div class="content-item" @click="$go('/order?from=2')">
        <i class="iconfont">&#xe61a;</i>
        <span>待收货</span>
      </div>
      <div class="content-item" @click="$go('/order?from=3')">
        <i class="iconfont">&#xe6e6;</i>
        <span>待评价</span>
      </div>
      <div class="content-item" @click="$go('/order?from=4')">
        <i class="iconfont">&#xe662;</i>
        <span>退款/售后</span>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .center-order{
    padding:0 28px;
    background: white;
    .order-header{
      position: relative;
      height: 104px;
      .header-left {
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
        display: flex;
        align-items: center;
        h4{
          font-size: 28px;
          font-weight: 600;
          color:#333;
        }
      }
      .header-right {
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        display: flex;
        align-items: center;
        color:#666;
        i{
          font-size: 28px;
        }
      }
    }
    .order-content{
      display: flex;
      .content-item{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        flex-basis: calc(25% - 150px / 4);
        height: 166px;
        i{
          font-size: 60px;
          color:#b4946d;
        }
        span{
          padding-top:10px;
          color:#666;
        }
        &:not(:first-child){
          margin-left:50px;
        }
      }
    }
  }
</style>
