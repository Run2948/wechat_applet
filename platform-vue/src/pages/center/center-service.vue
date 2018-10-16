<template>
  <div class="center-service">
    <div class="service-header">
      <div class="header-left">
        <h4>我的服务</h4>
      </div>
    </div>
    <div class="service-content">
      <div class="content-line border-1px-top">
        <div class="line-item">
          <i class="iconfont">&#xe600;</i>
          <span>拼团订单</span>
        </div>
        <div class="line-item">
          <i class="iconfont">&#xe672;</i>
          <span>优惠券</span>
        </div>
        <div class="line-item">
          <i class="iconfont">&#xe610;</i>
          <span>新人邀请</span>
          <label>立享红包</label>
        </div>
        <div class="line-item">
          <i class="iconfont">&#xe8bc;</i>
          <span>分享app</span>
        </div>
      </div>
      <div class="content-line border-1px-top">
        <div class="line-item">
          <i class="iconfont">&#xe68a;</i>
          <span>我的收藏</span>
        </div>
        <div class="line-item">
          <i class="iconfont">&#xe6e1;</i>
          <span>地址管理</span>
        </div>
        <div class="line-item">
          <i class="iconfont">&#xe633;</i>
          <span>意见反馈</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .center-service{
    padding:0 28px;
    margin-bottom: 28px;
    background: white;
    .service-header{
      position: relative;
      height: 104px;
      .header-left {
        position: absolute;
        top: 50%;
        left: 0;
        transform: translateY(-50%);
        display: flex;
        align-items: center;
        h4{
          font-size: 28px;
          font-weight: 600;
          color:#333;
        }
      }
    }
    .service-content{
      .content-line{
        display: flex;
        .line-item{
          display: flex;
          flex-direction: column;
          align-items: center;
          flex-basis: calc(25% - 150px / 4);
          height: 207px;
          i{
            padding-top:35px;
            font-size: 60px;
            color:#b4946d;
          }
          span{
            padding-top:15px;
            color:#666;
          }
          label{
            padding-top:10px;
            font-size: 16px;
            color:#999;
          }
          &:not(:first-child){
            margin-left:50px;
          }
        }
      }
    }
  }
</style>
