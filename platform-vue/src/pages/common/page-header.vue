<template>
  <div class="page-header border-1px-bottom">
    <div class="header-left" @click="$back">
      <i class="iconfont">&#xe68c;</i>
    </div>
    <h3>{{$route.meta.title}}</h3>
  </div>
</template>

<style lang="less" scoped>
  .page-header{
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
