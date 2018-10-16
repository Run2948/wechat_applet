<template>
  <div class="center-header">
    <div class="header-right">
      <i class="iconfont">&#xe685;</i>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .center-header{
    position: relative;
    height: 88px;
    .header-right {
      position: absolute;
      top: 50%;
      right: 27px;
      transform: translateY(-50%);
      i {
        font-size: 50px;
        color: white;
        &:not(:last-child) {
          padding-right: 27px;
        }
      }
    }
  }
</style>
