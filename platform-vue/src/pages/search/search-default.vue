<template>
  <div class="search-default">
    <search-section title="热门搜索">
      <div class="default-hot">
        <span
            :key="key"
            v-for="(item, key) in hot"
            @click="selectHot(item)"
            :style="{background: $randomColor(0.1)}">{{item.name}}</span>
      </div>
    </search-section>
    <search-section title="历史纪录">
      <i class="iconfont" slot="right" @click="clearHistory">&#xe62a;</i>
      <div class="default-history">
        <span
            :key="key"
            @click="selectHistory(item)"
            v-for="(item, key) in history">{{item}}</span>
      </div>
    </search-section>
  </div>
</template>

<script type="text/ecmascript-6">
import searchSection from './common/search-section'

export default {
  props: ['hot', 'history'],
  components: {
    searchSection
  },
  methods: {
    selectHot (o) {
      this.$emit('select-hot', o)
    },
    selectHistory (o) {
      this.$emit('select-history', o)
    },
    clearHistory () {
      this.$emit('clear-history')
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';
  .default-hot {
    display: flex;
    flex-wrap: wrap;
    span {
      flex-grow: 0;
      height: 50px;
      line-height: 50px;
      padding: 0 22px;
      margin: 0 20px 20px 0;
      border-radius: 5px;
      color: #666;
    }
  }

  .default-history {
    display: flex;
    flex-wrap: wrap;
    span {
      flex-grow: 0;
      height: 50px;
      line-height: 50px;
      padding: 0 22px;
      margin: 0 20px 20px 0;
      box-sizing: border-box;
      border-radius: 5px;
      border:1px solid @border-color;
      color: #666;
    }
  }
</style>
