<template>
  <page>
    <div class="page-header border-1px-bottom" slot="top">
      <div class="header-left" @click="$back">
        <i class="iconfont">&#xe68c;</i>
      </div>
      <h3>地址编辑</h3>
      <div class="header-right">
        <span>保存</span>
      </div>
    </div>
    <div class="address-update">
      <div class="update-item">
        <div class="item-title">
          <span>收货人姓名</span>
        </div>
        <div class="item-text">
          <input type="text" placeholder="收货人" v-model="form.name">
        </div>
      </div>
      <div class="update-item">
        <div class="item-title">
          <span>手机号码</span>
        </div>
        <div class="item-text">
          <input type="text" placeholder="手机号码" v-model="form.mobile">
        </div>
      </div>
      <div class="update-item">
        <div class="item-title">
          <span>所在地区</span>
        </div>
        <div class="item-text">
          <p>广东 佛山 顺德 北滘</p>
        </div>
      </div>
      <div class="update-item">
        <div class="item-title">
          <span>街道地址</span>
        </div>
        <div class="item-text">
          <input type="text" placeholder="街道地址" v-model="form.address">
        </div>
      </div>
      <div class="update-item">
        <div class="item-title">
          <span>邮政编码</span>
        </div>
        <div class="item-text">
          <input type="text" placeholder="邮政编码" v-model="form.code">
        </div>
      </div>
    </div>
  </page>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      form: {
        name: '邓海标',
        mobile: 13680065830,
        address: '陈家北二巷',
        code: '528300'
      }
    }
  }
}
</script>
<style lang="less" scoped>
  @import '../../static/less/index';
  .page-header{
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    background: white;
    .header-left{
      position: absolute;
      top:50%;
      left:30px;
      transform: translateY(-50%);
    }
    h3{
      font-size: 30px;
      font-weight: 600;
      color:#333;
    }
    .header-right{
      position: absolute;
      top:50%;
      right:30px;
      transform: translateY(-50%);
      span{
        color:#666;
      }
    }
  }
  .address-update{
    padding:0 43px;
    background: white;
    .update-item{
      position: relative;
      display: flex;
      height: 107px;
      color:#333;
      .item-title{
        display: flex;
        align-items: center;
        flex-basis: 172px;
      }
      .item-text{
        display: flex;
        align-items: center;
        flex:1;
        input{
          border:none;
          outline: none;
        }
      }
      &:not(:last-child) {
        &::after {
          position: absolute;
          content: '';
          bottom: 0;
          left: 0;
          width: 100%;
          height: 0;
          border-bottom: 1px solid @border-color;
          transform: scaleY(0.5);
        }
      }
    }
  }
</style>
