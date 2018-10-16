<template>
  <div class="m-number">
    <div class="number-wrap">
      <div class="wrap-sub" @click="sub">
        <i class="iconfont">&#xe6ba;</i>
      </div>
      <div class="wrap-text">
        <span>{{value}}</span>
      </div>
      <div class="wrap-add" @click="add">
        <i class="iconfont">&#xe658;</i>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  name: 'm-number',
  props: ['value', 'params'],
  methods: {
    sub () {
      this.$emit('change', {type: 'sub', number: this.value, goods: this.params})
    },
    add () {
      this.$emit('change', {type: 'add', number: this.value, goods: this.params})
    }
  }
}
</script>

<style lang="less" scoped>
  .m-number{
    display: inline-block;
    .number-wrap{
      display: flex;
      height: 50px;
      width:208px;
      box-sizing: border-box;
      border:1px solid #cfcfcf;
      .wrap-sub{
        display: flex;
        align-items: center;
        justify-content: center;
        flex-basis: 50px;
        box-sizing: border-box;
        border-right:1px solid #cfcfcf;
        background: #fafafa;
        color:#999;
      }
      .wrap-text{
        display: flex;
        align-items: center;
        justify-content: center;
        flex:1;
        span{
          font-size: 28px;
          color:#333;
        }
      }
      .wrap-add{
        display: flex;
        align-items: center;
        justify-content: center;
        flex-basis: 50px;
        box-sizing: border-box;
        border-left:1px solid #cfcfcf;
        background: #f4f4f4;
        color:#666;
      }
    }
  }
</style>
