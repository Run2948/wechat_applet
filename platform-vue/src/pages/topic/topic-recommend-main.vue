<template>
  <div class="top-recommend-main" :style="styles">
    <div class="main-pic">
      <span>严选推荐</span>
      <img class="ignore" src="//yanxuan.nosdn.127.net/3ca3fd809d7c2a42035934a3b82e0e6e.jpg?imageView&quality=75" alt="">
    </div>
    <div class="main-info">
      <div class="info-title-price">
        <span class="info-title">海边度假必看攻略</span>
        <span class="info-price">19.9元起</span>
      </div>
      <div class="info-desc">
        <p>不去海边浪一浪，夏天算白过了</p>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'm-floor',
  props: ['styles']
}
</script>

<style lang="less" scoped>
  .top-recommend-main {
    border: 1px solid #d9d9d9;
    border-radius: 7px;
    .main-pic{
      position: relative;
      width:100%;
      height: 539px;
      .ignore{
        width:100%;
        height: 100%;
      }
      span{
        position: absolute;
        top:28px;
        left:28px;
        display: inline-block;
        height:36px;
        line-height: 36px;
        padding:0 20px;
        border-radius: 18px;
        background: white;
        color:#333;
      }
    }
    .main-info{
      padding:24px 24px 68px 24px;
      .info-title-price{
        display: flex;
        justify-content: space-between;
        font-size: 36px;
        color:#333;
      }
      .info-desc{
        margin-top:20px;
        p{
          font-size: 24px;
          color:#666;
        }
      }
    }
  }
</style>
