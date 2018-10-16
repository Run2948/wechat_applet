<template>
  <div class="transaction-header border-1px-bottom">
    <div class="header-left" @click="$go('/cart', false)">
      <i class="iconfont">&#xe642;</i>
    </div>
    <h3>交易结果</h3>
  </div>
</template>

<style lang="less" scoped>
  .transaction-header{
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    background: white;
    .header-left{
      position: absolute;
      top:50%;
      left:30px;
      transform: translateY(-50%);
    }
    h3{
      font-size: 30px;
      font-weight: 600;
      color:#333;
    }
  }
</style>
