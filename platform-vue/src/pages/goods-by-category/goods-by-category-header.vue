<template>
  <div class="goods-by-category-header">
    <nav-header :title="subCategory.name"></nav-header>
    <div class="header-category">
      <cube-scroll ref="scroll" direction="horizontal">
        <div class="category-wrap">
          <div class="wrap-item"
              :key="key"
               :class="{'wrap-item-active': parseInt(item.id) === parseInt(currentSid)}"
               @click="changeSubCategory(item)"
              v-for="(item, key) in subCategory.sub">
              <span>{{item.name}}</span>
          </div>
        </div>
      </cube-scroll>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import navHeader from '../common/nav-header'
export default {
  data () {
    return {
      currentSid: this.sid
    }
  },
  props: ['subCategory', 'sid'],
  components: {
    navHeader
  },
  methods: {
    changeSubCategory (o) {
      this.$emit('change-sub-category', o)
      this.currentSid = o.id
    }
  }
}
</script>

<style lang="less">
  .goods-by-category-header {
    background: white;
    .header-category {
      .cube-scroll-content {
        display: inline-block;
        .category-wrap {
          white-space: nowrap;
          .wrap-item {
            display: inline-block;
            height: 1rem;
            padding:0 0.2133rem;
            margin-left: .373rem;
            text-align: center;
            span{
              line-height: 1rem;
              color:#666;
            }
            &:last-child{
              margin-right:.373rem;
            }
            &-active{
              position: relative;
              span{
                color:rgb(132, 95, 63);
              }
              &::after {
                position: absolute;
                content: '';
                bottom: 0;
                left: 0;
                width: 100%;
                height: 0;
                border-bottom: 0.08333rem solid rgb(132, 95, 63);
                transform: scaleY(0.5);
              }
            }
          }
        }
      }
    }
  }
</style>
