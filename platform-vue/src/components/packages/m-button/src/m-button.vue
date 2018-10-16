<template>
  <div :class="`m-button m-button-${type}`" :style="calcStyles">
    <slot></slot>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'm-button',
  props: {
    type: {
      type: String,
      default () {
        return 'default'
      }
    },
    color: {
      type: String,
      default () {
        return '#666'
      }
    },
    styles: {
      type: String
    }
  },
  computed: {
    calcStyles () {
      return `border-color: ${this.color};color:${this.color === '#666' ? '#666' : 'white'};background: ${this.color === '#666' ? null : this.color};${this.styles}`
    }
  }
}
</script>

<style lang="less" scoped>
  .m-button {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 95px;
    border: 1px solid;
    font-size: 30px;
    border-radius: 5px;
  }
</style>
