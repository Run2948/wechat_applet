<template>
  <div class="search-header">
    <div class="header-top">
      <div class="top-left" @click="$back">
        <i class="iconfont">&#xe68c;</i>
      </div>
      <div class="top-search">
        <i class="iconfont">&#xe62b;</i>
        <input type="search" placeholder="小米超级品牌日" v-model="keyword">
        <i class="iconfont clear" @click="clear" v-if="searchBtnVisible">&#xe61b;</i>
      </div>
      <div class="top-right" @click="search" v-if="searchBtnVisible">
        <span>搜索</span>
      </div>
    </div>
    <div class="header-bottom" v-if="bottomVisible">
      <div class="bottom-item" :class="{'bottom-item-active': sort === 0}" @click="changeSort(0)">
        <span>综合</span>
      </div>
      <div class="bottom-item" :class="{'bottom-item-active': sort === 1}" @click="changeSort(1)">
        <span>价格</span>
      </div>
      <div class="bottom-item" :class="{'bottom-item-active': sort === 2}" @click="changeSort(2)">
        <span>品牌</span>
      </div>
      <div class="bottom-item" :class="{'bottom-item-active': sort === 3}" @click="changeSort(3)">
        <span>分类</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      keyword: this.searchKey,
      sort: 0
    }
  },
  props: ['step', 'searchKey'],
  computed: {
    searchBtnVisible () {
      return this.step !== 3
    },
    bottomVisible () {
      return this.step === 3
    }
  },
  methods: {
    search () {
      this.$emit('search', this.keyword)
    },
    clear () {
      this.keyword = ''
      this.$emit('clear')
    },
    changeSort (n) {
      this.sort = n
      this.$emit('change-sort', n)
    }
  },
  watch: {
    'keyword': function (val) {
      this.$emit('key-change', val)
    },
    'searchKey': function (val) {
      this.keyword = val
    }
  }
}
</script>

<style lang="less" scoped>
  .search-header {
    padding: 0 28px;
    background: white;
    .header-top {
      display: flex;
      align-items: center;
      height: 88px;
      .top-left {
        flex-basis: 50px;
      }
      .top-search {
        display: flex;
        align-items: center;
        flex: 1;
        height: 60px;
        border-radius: 8px;
        background: #f4f4f4;
        i {
          flex-basis: 65px;
          text-align: center;
          line-height: 60px;
          color: #999;
        }
        input {
          flex: 1;
          border: none;
          outline: none;
          background: #f4f4f4;
          &::-webkit-search-cancel-button {
            display: none;
          }
        }
        .clear {
          flex-basis: 65px;
          text-align: center;
          line-height: 60px;
          color: #999;
        }
      }
      .top-right {
        display: flex;
        justify-content: flex-end;
        flex-basis: 85px;
        span {
          color: #666;
        }
      }
    }
    .header-bottom {
      display: flex;
      align-items: center;
      height: 88px;
      .bottom-item {
        display: flex;
        justify-content: center;
        flex: 1;
        color: #666;
        span {
          font-size: 28px;
        }
        &-active {
          color: #9e0000;
        }
      }
    }
  }
</style>
