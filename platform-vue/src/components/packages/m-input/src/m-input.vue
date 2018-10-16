<template>
  <div class="m-input">
    <div class="input-left">
      <input
          :type="type"
          :placeholder="placeholder"
          :value="value"
          @input="$emit('input', $event.target.value)">
    </div>
    <div class="input-right">
      <slot></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default{
  name: 'm-input',
  props: ['type', 'placeholder', 'value']
}
</script>

<style lang="less" scoped>
  .m-input{
    position: relative;
    display: flex;
    align-items: center;
    min-height: 85px;
    .input-left{
      flex:1;
      input{
        display: inline-block;
        width:100%;
        font-size: 28px;
        background: none;
        outline: none;
        color:#666;
      }
    }
    .input-right{
      display: flex;
      align-items: center;
      flex-grow: 0;
    }
    &::after {
      position: absolute;
      content: '';
      bottom: 0;
      left: 0;
      width: 100%;
      height: 0;
      border-bottom: 1px solid #845f3f;
      transform: scaleY(0.5);
    }
  }
</style>
