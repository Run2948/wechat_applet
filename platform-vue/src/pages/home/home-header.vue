<template>
  <div class="home-header">
    <div class="header-top">
      <div class="top-logo">
        <img src="@/static/images/home/navi_title_v4.png" alt="">
      </div>
      <div class="top-search" @click="$go('/search')">
        <div class="search-left">
          <i class="iconfont">&#xe62b;</i>
          <span>搜索好品，共有16523件商品</span>
        </div>
      </div>
    </div>
    <div class="header-bottom">
      <cube-scroll ref="scroll" direction="horizontal">
        <div class="bottom-tabs">
          <div class="tabs-item"
               :key="key"
               :class="{'tabs-item-active': item.id === navId}"
               @click="changeNav(item)"
               v-for="(item, key) in navList">
            <span>{{item.name}}</span>
          </div>
        </div>
      </cube-scroll>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      navId: this.currentNav
    }
  },
  props: ['currentNav', 'navList'],
  methods: {
    changeNav (o) {
      this.navId = o.id
      this.$emit('change-nav', o)
    }
  }
}
</script>

<style lang="less">
  .home-header {
    .header-top {
      display: flex;
      align-items: center;
      height: 88px;
      padding: 0 27px;
      background: white;
      .top-logo {
        flex-basis: 160px;
        box-sizing: border-box;
        padding-right: 20px;
        img {
          width: 100%;
        }
      }
      .top-search {
        position: relative;
        flex: 1;
        height: 58px;
        border-radius: 5px;
        background: #f4f4f4;
        .search-left {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          display: flex;
          align-items: center;
          i {
            color: #909090;
          }
          span {
            white-space: nowrap;
            color: #c8c8c8;
          }
        }
      }
    }
    .header-bottom {
      width: 100%;
      overflow-x: hidden;
      background: white;
      .cube-scroll-content {
        display: inline-block;
        .bottom-tabs {
          white-space: nowrap;
          padding: 0 .4rem;
          .tabs-item {
            display: inline-block;
            height: .77778rem;
            padding: 0 .2223rem;
            text-align: center;
            line-height: .77778rem;
            span {
              font-size: .372rem;
              font-weight: 400;
            }
            &-active {
              position: relative;
              color: #b4282d;
              &::after {
                position: absolute;
                content: '';
                bottom: 0;
                left: 0;
                width: 100%;
                height: 0;
                border-bottom: 2px solid #b4282d;
              }
            }
            &:not(:first-child) {
              margin-left: .833rem;
            }
          }
        }
      }
    }
  }
</style>
