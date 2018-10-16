<template>
  <div class="evaluate-cell">
    <div class="cell-user">
      <div class="user-left">
        <img class="ignore" src="https://s1.mi-img.com/mfsv2/avatar/fdsc3/p013qAccs3i9/WT2sfu9guuY496.jpg" alt="">
        <span>芯儿拽天下</span>
      </div>
    </div>
    <div class="cell-text">
      <p>超级喜欢，本来想买128G版本可惜当时还没出，现在才有，目前已刷MIUI10开发版，附赠一张自拍照</p>
    </div>
    <div class="cell-pic">
      <div class="pic-item">
        <div class="item-wrap" data-ratio="1:1">
          <div class="wrap-image"></div>
        </div>
      </div>
      <div class="pic-item">
        <div class="item-wrap" data-ratio="1:1">
          <div class="wrap-image"></div>
        </div>
      </div>
    </div>
    <div class="cell-action">
      <div class="action-time">
        <span>2018-08-12</span>
      </div>
      <div class="action-type">
        <div class="type-praise">
          <i class="iconfont">&#xe641;</i>
          <span>578</span>
        </div>
        <div class="type-message">
          <i class="iconfont">&#xe661;</i>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
  .evaluate-cell {
    padding: 0 34px 34px 34px;
    background: white;
    .cell-user {
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 137px;
      .user-left {
        display: flex;
        align-items: center;
        .ignore {
          display: inline-block;
          width: 1.094rem;
          height: 1.094rem;
          border-radius: 0.547rem;
        }
        span {
          padding-left: 34px;
          font-size: 28px;
          color: #333;
        }
      }
    }
    .cell-pic {
      display: flex;
      flex-wrap: wrap;
      padding-top: 30px;
      .pic-item {
        position: relative;
        flex-basis: calc(25% - 30px / 3);
        .item-wrap {
          &[data-ratio="1:1"] {
            padding-top: calc(100% * 1 / 1);
          }
          * {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-image: url(http://i1.mifile.cn/a2/1534312684_9824811_s1008_567wh.jpg);
            background-position: center center;
            background-repeat: no-repeat;
            background-size: cover;
          }
        }
        &:not(:nth-child(4n + 1)) {
          margin-left: 10px;
        }
      }
    }
    .cell-action {
      display: flex;
      justify-content: space-between;
      padding-top: 30px;
      color: #999;
      .action-type {
        display: flex;
        .type-praise {
          display: flex;
          align-items: center;
          padding-right: 20px;
          i {
            padding-right: 10px;
          }
        }
      }
    }
  }
</style>
