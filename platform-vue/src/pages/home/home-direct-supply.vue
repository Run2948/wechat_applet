<template>
  <div class="home-direct-supply">
    <m-floor title="品牌制造商直供" :is-link="true">
      <div class="direct-supply-wrap">
        <div class="wrap-item" :key="key" v-for="(item, key) in list">
          <div class="item-desc">
            <h4>{{item.name}}</h4>
            <span>{{item.price}}元起</span>
          </div>
          <img class="ignore" :src="item.img" alt="">
        </div>
      </div>
    </m-floor>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['list']
}
</script>

<style lang="less" scoped>
  .home-direct-supply{
    margin-bottom: 20px;
    .direct-supply-wrap{
      display: flex;
      flex-wrap: wrap;
      .wrap-item{
        position: relative;
        flex-basis: calc(50% - 3.5px);
        height: 242px;
        background: #f4f4f4;
        color:#333;
        .item-desc{
          position: absolute;
          top:20px;
          left:20px;
          display: flex;
          flex-direction: column;
          align-items: flex-start;
          h4{
            height: 30px;
            line-height: 30px;
          }
          span{
            height: 30px;
            line-height: 30px;
          }
          label{
            display: inline-block;
            height: 30px;
            line-height: 30px;
            padding:0 10px;
            border:1px solid #d1c9b3;
            color:#d1c9b3;
          }
        }
        .ignore{
          width:100%;
          height: 100%;
        }
        &:nth-child(2n + 1){
          margin:0 7px 7px 0;
        }
        &:nth-child(2n){
          margin-bottom:7px;
        }
      }
    }
  }
</style>
