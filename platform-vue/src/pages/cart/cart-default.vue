<template>
  <div class="cart-default">
    <div class="default-policy">
      <div class="policy-item">
        <i class="iconfont">&#xe643;</i>
        <span>30天无忧退货</span>
      </div>
      <div class="policy-item">
        <i class="iconfont">&#xe643;</i>
        <span>24小时快速退款</span>
      </div>
      <div class="policy-item">
        <i class="iconfont">&#xe643;</i>
        <span>满88元免邮费</span>
      </div>
    </div>
    <div class="default-center">
      <div class="center-no">
        <img class="ignore" src="//yanxuan-static.nosdn.127.net/hxm/yanxuan-wap/p/20161201/style/img/icon-normal/noCart-a8fe3f12e5.png" alt="">
        <p>去添加点什么吧</p>
      </div>
      <div class="center-login"></div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .cart-default{
    position: relative;
    height: 100%;
    .default-policy{
      display: flex;
      height: 67px;
      align-items: center;
      padding:0 28px;
      .policy-item{
        display: flex;
        justify-content: center;
        align-items: center;
        flex:1;
        color:#666;
      }
    }
    .default-center{
      position: absolute;
      top:50%;
      width:100%;
      transform: translateY(-50%);
      .center-no{
        display: flex;
        flex-direction: column;
        align-items: center;
        .ignore{
          width:4rem;
          height: 4rem;
        }
        p{
          font-size: 28px;
          color:#666;
        }
      }
    }
  }
</style>
