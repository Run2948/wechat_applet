<template>
  <div class="category-content-list">
    <cube-scroll>
      <div class="list-item"
           :class="{'list-item-active': activeList.id === item.id}"
           :key="key"
           v-for="(item, key) in list"
           @click="selectList(item)">
        <span>{{item.name}}</span>
      </div>
    </cube-scroll>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  data () {
    return {
      currentList: null
    }
  },
  props: ['list'],
  computed: {
    activeList () {
      if (this.currentList) {
        return this.currentList
      } else {
        return this.list[0]
      }
    }
  },
  methods: {
    selectList (o) {
      this.currentList = o
      this.$emit('selectList', o)
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';
  .category-content-list{
    position: absolute;
    top:0;
    bottom:0;
    width:167px;
    background: #f4f4f4;
    .list-item{
      display: flex;
      align-items: center;
      height: 103px;
      span{
        position: relative;
        display: inline-block;
        height: 50px;
        line-height: 50px;
        text-align: center;
        flex:1;
        color:#666;
      }
      &-active{
        background: white;
        span{
          font-size: 28px;
          color:#ab2b2b;
          &::before{
            position: absolute;
            content: '';
            top: 0;
            left: 0;
            height: 100%;
            width: 0;
            border-left: 5px solid #ab2b2b;
          }
        }
      }
    }
  }
</style>
