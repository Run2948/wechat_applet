<template>
  <div class="address-item border-1px-bottom">
    <div class="item-select">
      <i class="iconfont">&#xe65f;</i>
    </div>
    <div class="item-main">
      <div class="main-top">
        <span>邓海标</span>
        <span>13680065830</span>
      </div>
      <div class="main-bottom">
        <span>默认</span>
        <p>中国 广东 佛山 顺德 北滘镇莘村中学（528300）</p>
      </div>
    </div>
    <div class="item-update" @click="update">
      <i class="iconfont">&#xe62c;</i>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  methods: {
    update () {
      this.$emit('update')
    }
  }
}
</script>

<style lang="less" scoped>
  .address-item{
    display: flex;
    height: 210px;
    padding:0 27px;
    background: white;
    .item-select{
      display: flex;
      align-items: center;
      flex-grow: 0;
      i{
        color:#d74945;
      }
    }
    .item-main{
      display: flex;
      flex-direction: column;
      justify-content: center;
      flex:1;
      padding:0 24px 0 40px;
      .main-top{
        display: flex;
        justify-content: space-between;
        span{
          font-weight: 600;
          font-size: 28px;
          color:#333;
        }
      }
      .main-bottom{
        display: flex;
        padding-top:27px;
        span{
          display: inline-block;
          height: 36px;
          box-sizing: border-box;
          padding:0 10px;
          margin-right:10px;
          line-height: 34px;
          border:1px solid #d74945;
          white-space: nowrap;
          color:#d74945;
        }
        p{
          color:#666;
        }
      }
    }
    .item-update{
      display: flex;
      align-items: center;
      flex-grow: 0;
      i{
        color:#666;
      }
    }
  }
</style>
