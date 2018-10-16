<template>
  <div class="goods-shop">
    <div class="shop-avatar">
      <img class="ignore" src="http://static.home.mi.com/app/shop/img?id=shop_72bacf474dfad998341d995c6fcb9db1.jpg&t=jpeg" alt="">
    </div>
    <div class="shop-info">
      <h3>小米自营产品</h3>
      <p>为发烧而生</p>
    </div>
    <div class="shop-link">
      <span>进入店铺</span>
      <i class="iconfont">&#xe632;</i>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .goods-shop{
    display: flex;
    align-items: center;
    height: 120px;
    padding:0 28px;
    margin-bottom: 10px;
    background: white;
    .shop-avatar{
      flex-grow: 0;
      .ignore{
        width:1.0666rem;
        height:1.0666rem;
      }
    }
    .shop-info{
      flex:1;
      padding-left:20px;
      h3{
        font-size: 28px;
        font-weight: 600;
        color:#333;
      }
      p{
        padding-top:20px;
        color:#666;
      }
    }
    .shop-link{
      flex-grow: 0;
      color:#aa8a59;
      i{
        font-size: 24px;
      }
    }
  }
</style>
