<template>
  <div class="evaluate-outline">
    <div class="outline-header border-1px-bottom">
      <div class="header-left">
        <span>用户评价（125783+）</span>
      </div>
      <div class="header-left">
        <label>96%</label>
        <span>满意</span>
      </div>
    </div>
    <div class="outline-content">
      <span>全部</span>
      <span class="light">最新</span>
    </div>
  </div>
</template>

<style lang="less" scoped>
  @import '../../static/less/index';
  .evaluate-outline{
    padding-left:34px;
    margin-bottom: 10px;
    background: white;
    .outline-header{
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 77px;
      padding-right:34px;
      color:#666;
      .header-left{
        label{
          color:#ff6b01;
        }
      }
    }
    .outline-content{
      display: flex;
      flex-wrap: wrap;
      padding:26px 26px 26px 0;
      span{
        display: inline-block;
        min-width: 133px;
        height: 55px;
        box-sizing: border-box;
        padding:0 24px;
        line-height: 55px;
        text-align: center;
        border-radius: 5px;
        background: #ff6b01;
        color:white;
        &:not(:first-child){
          margin-left: 20px;
        }
      }
      .light{
        background: #ffebec;
        color:#666;
      }
    }
  }
</style>
