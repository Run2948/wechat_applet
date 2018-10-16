<template>
  <div class="m-nav-footer">
    <div class="footer-version">
      <span>下载APP</span>
      <span>电脑版</span>
    </div>
    <div class="footer-copyright">
      <p>网易公司版权所有 © 1997-2018</p>
      <p>食品经营许可证：JY13301080111719</p>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .m-nav-footer{
    padding:54px 20px 28px 20px;
    background: #414141;
    .footer-version{
      display: flex;
      justify-content: center;
      color:white;
      span{
        display: inline-block;
        height: 60px;
        width: 166px;
        text-align: center;
        line-height: 60px;
        box-sizing: border-box;
        border:1px solid white;
        &:last-child{
          margin-left:50px;
        }
      }
    }
    .footer-copyright{
      margin-top:36px;
      color:#999;
      p{
        line-height: 40px;
        text-align: center;
      }
    }
  }
</style>
