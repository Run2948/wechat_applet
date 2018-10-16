<template>
  <div class="transaction-result">
    <i class="iconfont">&#xe646;</i>
    <h4>交易成功</h4>
  </div>
</template>

<style lang="less" scoped>
  .transaction-result{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 355px;
    margin-bottom: 10px;
    background: white;
    i{
      display: inline-block;
      width:175px;
      height: 175px;
      line-height: 175px;
      text-align: center;
      font-size: 175px;
      color:#00c800;
    }
    h4{
      font-size: 34px;
      color:#666;
    }
  }
</style>
