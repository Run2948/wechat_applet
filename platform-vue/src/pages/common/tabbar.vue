<template>
  <div class="tabbar">
    <div class="tabbar-item" :class="{'tabbar-item-active': $route.name === item.path}"
         :key="key"
          v-for="(item, key) in items"
          @click="$go(item.path)">
      <i class="iconfont" v-html="item.icon"></i>
      <span>{{item.text}}</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['items']
}
</script>

<style lang="less" scoped>
  .tabbar{
    display: flex;
    height: 100px;
    background: white;
    .tabbar-item{
      position: relative;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      flex:1;
      color:RGB(127,127,127);
      .iconfont{
        font-size: 45px;
      }
      span{
        font-size: 20px;
        font-weight: 400;
      }
      &-active{
        color:RGB(132,95,63)
      }
    }
  }
</style>
