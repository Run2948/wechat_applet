<template>
  <div class="m-cell-group">
    <div class="group-header">
      <div class="header-left">
        <span>{{title}}</span>
      </div>
    </div>
    <div class="group-content">
      <slot></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  name: 'm-cell-group',
  props: ['title']
}
</script>

<style lang="less" scoped>
  .m-cell-group{
    padding:0 28px;
    .group-header{
      display: flex;
      justify-content: space-between;
      height: 100px;
      .header-left{
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        span{
          padding-bottom: 20px;
          color:#666;
        }
      }
    }
    &:not(:first-child){
      .group-header{
        position: relative;
        &::before {
          position: absolute;
          content: '';
          top: 0;
          left: 0;
          width: 100%;
          height: 0;
          border-top: 1px solid #ededed;
          transform: scaleY(0.5);
        }
      }
    }
  }
</style>
