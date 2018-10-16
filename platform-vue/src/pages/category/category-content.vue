<template>
  <div class="category-content">
    <category-content-list
        :list="list"
        @selectList="selectList"></category-content-list>
    <category-content-sub-list
        :sub-list="subList"
        @selectSubList="selectSubList"></category-content-sub-list>
  </div>
</template>

<script type="text/ecmascript-6">
import categoryContentList from './category-content-list'
import categoryContentSubList from './category-content-sub-list'
export default{
  data () {
    return {
      currentList: null
    }
  },
  props: ['list'],
  computed: {
    subList () {
      if (this.currentList) {
        return this.currentList
      } else {
        return this.list.length ? this.list[0] : {}
      }
    }
  },
  components: {
    categoryContentList,
    categoryContentSubList
  },
  methods: {
    selectList (o) {
      this.currentList = o
    },
    selectSubList (o) {
      this.$emit('selectSubList', o)
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';
  .category-content{
    position: absolute;
    top:0;
    bottom:0;
    width:100%;
    background: white;
  }
</style>
