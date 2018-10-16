<template>
  <div class="goods-action border-1px-top">
    <div class="action-left">
      <div class="left-item">
        <i class="iconfont">&#xe654;</i>
        <span>客服</span>
      </div>
      <div class="left-item">
        <i class="iconfont">&#xe611;</i>
        <span>收藏</span>
      </div>
      <div class="left-item">
        <i class="iconfont">&#xe602;</i>
        <span>购物车</span>
      </div>
    </div>
    <div class="action-right">
      <div class="right-item right-item-buy">
        <span>立即购买</span>
      </div>
      <div class="right-item right-item-cart">
        <span>加入购物车</span>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .goods-action{
    display: flex;
    height: 115px;
    padding:0 28px;
    background: white;
    .action-left{
      display: flex;
      justify-content: space-around;
      flex:1;
      .left-item{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        color:#666;
      }
    }
    .action-right{
      display: flex;
      align-items: center;
      flex-grow: 0;
      .right-item{
        display: inline-block;
        width:222px;
        height: 68px;
        text-align: center;
        line-height: 68px;
        border-radius: 34px;
        color:white;
        &-buy{
          background: #b60a0a;
        }
        &-cart{
          background: #aa8a59;
        }
        &:nth-child(2){
          margin-left: 10px;
        }
      }
    }
  }
</style>
