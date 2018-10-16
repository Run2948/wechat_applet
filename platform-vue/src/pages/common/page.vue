<template>
  <div class="page" :style="{background: pageBackground}">
    <div :class="topClasses">
      <page-header v-if="!this.$slots.top"></page-header>
      <slot name="top"></slot>
      <slot name="extra-top"></slot>
    </div>
    <div class="page-middle" :class="{'page-better-scroll': betterScroll}">
      <template v-if="!betterScroll">
        <router-view></router-view>
        <slot></slot>
      </template>
      <cube-scroll
        ref="scroll"
        :scrollEvents="scrollEvents"
        @before-scroll-start="beforeScrollStart"
        @scroll="scroll"
        v-if="betterScroll">
        <router-view></router-view>
        <slot></slot>
      </cube-scroll>
    </div>
    <div class="page-bottom">
      <slot name="bottom"></slot>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import pageHeader from './page-header'
export default {
  name: 'page',
  data () {
    return {
      options: {
        pullDownRefresh: {
          threshold: 60,
          stopTime: 1000,
          txt: '更新成功'
        }
      },
      scrollEvents: ['scroll', 'before-scroll-start']
    }
  },
  props: ['betterScroll', 'pageBackground', 'headerImmerse'],
  computed: {
    topClasses () {
      return this.headerImmerse ? 'page-top-immerse' : 'page-top'
    }
  },
  components: {
    pageHeader
  },
  methods: {
    beforeScrollStart () {
      this.$refs.scroll.refresh()
    },
    scroll (e) {
      this.$emit('scroll', e)
    },
    scrollTo (val, options) {
      if (typeof val === 'string') {
        const el = document.querySelector(val)
        const topEl = this.$refs.scroll.$el.querySelector('.cube-scroll-content')
        try {
          this.$refs.scroll.scrollTo(0, -(el.getBoundingClientRect().top - topEl.getBoundingClientRect().top), 800)
        } catch (e) {
          // @TODO
        }
      }
      if (Array.isArray(val)) {
        this.$refs.scroll.scrollTo(...val)
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .page{
    position: relative;
    display: flex;
    flex-direction: column;
    width:100%;
    height: 100%;
    .page-top{
      flex-grow: 0;
      &-immerse{
        position: absolute;
        top:0;
        left:0;
        width:100%;
        z-index: 11;
      }
    }
    .page-middle{
      position: relative;
      flex:1;
      overflow-y: auto;
    }
    .page-better-scroll{
      overflow-y: hidden;
    }
    .page-bottom{
      flex-grow: 0;
    }
  }
</style>
