<template>
  <div class="topic-channels">
    <cube-scroll ref="scroll" direction="horizontal">
      <div class="channels-wrap">
        <div class="wrap-item">
          <span>共235篇文章</span>
          <img src="//yanxuan.nosdn.127.net/254e04f823354c67d765d68c4012b64e.png?imageView&quality=75" alt="">
          <h4>严选推荐</h4>
        </div>
      </div>
    </cube-scroll>
  </div>
</template>

<style lang="less">
  .topic-channels {
    padding:20px 0 28px 0;
    margin-bottom: 20px;
    background: white;
    .cube-scroll-content {
      display: inline-block;
      .channels-wrap {
        white-space: nowrap;
        .wrap-item {
          position: relative;
          display: inline-block;
          width: 2.2133rem;
          margin-left: .32rem;
          span{
            position: absolute;
            top:0;
            left:0;
            width:100%;
            height: 0.42667rem;
            line-height: 0.42667rem;
            text-align: right;
            background: linear-gradient(left, RGBA(255, 255, 255, .5), RGBA(0, 0, 0, .5));
            color:white;
          }
          img{
            width:100%;
            height: 2.2133rem;
          }
          h4{
            text-align: center;
            margin-top: .29333rem;
            color:#333;
          }
          &:last-child{
            margin-right:.32rem;
          }
        }
      }
    }
  }
</style>
