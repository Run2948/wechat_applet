<template>
  <div class="evaluate-card">
    <div class="card-user">
      <div class="user-avatar">
        <img src="https://s1.mi-img.com/mfsv2/avatar/s008/p01amyF87qXf/793p8cAufflQSe.jpg" alt="">
      </div>
      <div class="user-name">
        <span>T*****rstruck</span>
      </div>
      <div class="user-star">
        <img src="https://static.home.mi.com/youpin/static/m/res/images/evaluation_btn_level.a_sel_light.png" alt="">
        <img src="https://static.home.mi.com/youpin/static/m/res/images/evaluation_btn_level.a_sel_light.png" alt="">
        <img src="https://static.home.mi.com/youpin/static/m/res/images/evaluation_btn_level.a_sel_light.png" alt="">
        <img src="https://static.home.mi.com/youpin/static/m/res/images/evaluation_btn_level.a_sel_light.png" alt="">
      </div>
    </div>
    <div class="card-text">
      <p>手机性价比很高。优点有很多，缺点也很多，总体OK。</p>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .evaluate-card{
    height: 100%;
    box-sizing: border-box;
    padding:28px;
    background: #f8f8f8;
    .card-user{
      display: flex;
      align-items: center;
      .user-avatar{
        display: inline-block;
        height: 70px;
        width:70px;
        img{
          width:100%;
          height: 100%;
          border-radius: 50%;
        }
      }
      .user-name{
        padding:0 15px;
      }
      .user-star{
        display: flex;
        align-items: center;
        img{
          width:30px;
          height: 30px;
        }
      }
    }
    .card-text{
      padding-top:28px;
      p{
        white-space:normal;
        color:#333;
      }
    }
  }
</style>
