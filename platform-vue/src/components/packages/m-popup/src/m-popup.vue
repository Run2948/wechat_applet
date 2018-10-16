<template>
  <div class="m-popup" :class="{'m-popup-mask': mask}" @click.self="close()">
    <div class="popup-bottom animated fadeInUpBig">
      <render :render="expand"></render>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import render from '../../../common/render'
export default{
  name: 'm-popup',
  props: {
    mask: {
      type: Boolean
    },
    close: {
      type: Function
    },
    expand: {
      type: Function,
      default () {
        return ''
      }
    }
  },
  components: {
    render
  },
  methods: {
    init () {
      // @TODO
    }
  }
}
</script>

<style lang="less" scoped>
  .m-popup{
    position: fixed;
    top:0;
    bottom:0;
    width:100%;
    z-index: 1000;
    .popup-bottom{
      position: absolute;
      bottom:0;
      left:0;
      width:100%;
    }
    &-mask{
      background: rgba(0,0,0,0.2)
    }
  }
</style>
