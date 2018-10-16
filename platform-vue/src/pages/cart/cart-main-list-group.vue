<template>
  <div class="cart-main-list-group">
    <div class="group-header">
      <div class="header-left" @click="selectGroup(group)">
        <i class="iconfont" v-html="group.select ? '&#xe65f;' : '&#xe660;'" :style="{color: !group.select ? '#666' : ''}"></i>
        <span>{{group.shopName}}</span>
      </div>
      <div class="header-right">
        <i class="iconfont">&#xe60c;</i>
        <span>满150.00免运费</span>
      </div>
    </div>
    <div class="group-content">
      <div class="content-item border-1px-top"
         :key="key"
         :group="item"
         v-for="(item, key) in group.list">
        <div class="item-select" @click="select(item)">
          <i class="iconfont" v-html="item.select ? '&#xe65f;' : '&#xe660;'" :style="{color: !item.select ? '#666' : ''}"></i>
        </div>
        <div class="item-pic">
          <img class="ignore" :src="item.pic" alt="">
        </div>
        <div class="item-info">
          <div class="info-name">
            <p>{{item.goodsName}}</p>
          </div>
          <div class="info-price-num">
            <div class="price">
              <span>￥{{item.price}}.00</span>
            </div>
            <div class="num">
              <m-number
                  :value="item.number"
                  :params="item"
                  @change="change"></m-number>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  props: ['group'],
  methods: {
    change (o) {
      this.$emit('goods-number', o)
    },
    select (o) {
      this.$emit('select', o)
    },
    selectGroup (o) {
      this.$emit('select-group', o)
    }
  }
}
</script>

<style lang="less" scoped>
  .cart-main-list-group {
    background: white;
    .group-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 88px;
      padding: 0 28px;
      .header-left {
        display: flex;
        align-items: center;
        i {
          color: #b60a0a;
        }
        span {
          padding-left: 10px;
          font-size: 28px;
          font-weight: 600;
          color: #333;
        }
      }
      .header-right {
        display: flex;
        align-items: center;
        color: #666;
        span {
          padding-left: 10px;
        }
      }
    }
    .group-content {
      padding: 0 28px;
      .content-item {
        display: flex;
        height: 223px;
        box-sizing: border-box;
        padding: 28px 0;
        .item-select {
          display: flex;
          align-items: center;
          flex-basis: 64px;
          i {
            color: #b60a0a;
          }
        }
        .item-pic {
          flex-basis: 164px;
          .ignore {
            height: 100%;
            width: 100%;
          }
        }
        .item-info {
          display: flex;
          flex-direction: column;
          justify-content: space-between;
          flex: 1;
          padding: 0 10px;
          .info-name {
            p {
              font-size: 28px;
              font-weight: 600;
              color: #333;
            }
          }
          .info-price-num {
            display: flex;
            justify-content: space-between;
            .price {
              display: flex;
              flex-direction: column;
              justify-content: flex-end;
              span {
                color: #b60a0a;
              }
            }
          }
        }
      }
    }
    &:not(:first-child){
      margin-top: 28px;
    }
  }
</style>
