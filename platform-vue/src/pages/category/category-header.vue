<template>
  <div class="category-header border-1px-bottom">
    <div class="header-search" @click="$go('/search')">
      <i class="iconfont">&#xe62b;</i>
      <span>润米超级品牌日</span>
    </div>
  </div>
</template>

<style lang="less" scoped>
  @import '../../static/less/index';
  .category-header{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    padding:0 28px;
    background: white;
    .header-search{
      display: flex;
      align-items: center;
      flex:1;
      height: 59px;
      padding-left:28px;
      border-radius: 7px;
      background: #f4f4f4;
      i{
        color:#666;
      }
      span{
        padding-left:20px;
        color:#999;
      }
    }
  }
</style>
