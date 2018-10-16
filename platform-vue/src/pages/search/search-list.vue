<template>
  <div class="search-list">
    <div class="list-item border-1px-bottom" :key="key" v-for="(item, key) in list">
      <search-goods :info="item"></search-goods>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import searchGoods from './common/search-goods'
export default {
  props: ['list'],
  components: {
    searchGoods
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';
  .search-list{
    display: flex;
    flex-wrap: wrap;
    .list-item{
      position: relative;
      flex-basis: 50%;
      width: 0;
      &:nth-child(2n + 1) {
        &::before {
          position: absolute;
          content: '';
          top: 0;
          right: 0;
          height: 100%;
          width: 0;
          border-right: 1px solid @border-color;
          transform: scaleX(0.5);
        }
      }
    }
  }
</style>
