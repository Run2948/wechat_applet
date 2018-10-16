<template>
  <div class="home-flash">
    <div class="flash-left">
      <div class="left-title">
        <h3>严选限时购</h3>
      </div>
      <div class="left-countdown">
        <span>01</span>
        <span>24</span>
        <span>55</span>
      </div>
      <div class="left-next">下一场 18：00 开始</div>
    </div>
    <div class="flash-right">
      <img class="ignore" src="http://yanxuan.nosdn.127.net/36b3fa8eec9efb2cbdea65b3f95f2fe8.png?imageView&quality=85&thumbnail=330x330" alt="">
    </div>
  </div>
</template>

<style lang="less" scoped>
  .home-flash {
    display: flex;
    padding:30px 40px 30px 56px;
    margin-bottom: 20px;
    background: white;
    .flash-left{
      display: flex;
      flex-direction: column;
      justify-content: center;
      flex:1;
      .left-title{
        h3{
          letter-spacing: 12px;
          font-size: 36px;
          font-weight: 600;
          color:#333;
        }
      }
      .left-countdown{
        display: flex;
        padding:28px 0;
        span{
          position: relative;
          display: inline-block;
          width:70px;
          height: 50px;
          line-height: 50px;
          text-align: center;
          font-size: 34px;
          font-weight: 600;
          border-radius: 3px;
          background: #444444;
          color:white;
          &:not(:first-child){
            margin-left:14px;
            &:before{
              position: absolute;
              top:50%;
              left:-10px;
              transform: translateY(-50%);
              content: ':';
              color:#444444;
            }
          }
        }
      }
    }
    .flash-right{
      flex:1;
      .ignore{
        width:100%;
      }
    }
  }
</style>
