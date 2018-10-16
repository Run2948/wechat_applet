<template>
  <div class="m-divider">
    <span class="divider-left"></span>
    <span class="divider-text">{{title}}</span>
    <span class="divider-right"></span>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'm-divider',
  props: ['title']
}
</script>

<style lang="less" scoped>
  .m-divider {
    display: flex;
    .divider-left {
      position: relative;
      flex: 1;
      &::before {
        position: absolute;
        content: '';
        top: 50%;
        left: 0;
        width: 100%;
        transform: translateY(-50%) scaleY(0.5);
        border-bottom: 1px solid  #c1a0b0;
      }
    }
    .divider-text {
      flex-grow: 0;
      padding:0 20px;
      color: #c1a0b0;
    }
    .divider-right {
      position: relative;
      flex: 1;
      &::before {
        position: absolute;
        content: '';
        top: 50%;
        left: 0;
        width: 100%;
        transform: translateY(-50%) scaleY(0.5);
        border-bottom: 1px solid #c1a0b0;
      }
    }
  }
</style>
