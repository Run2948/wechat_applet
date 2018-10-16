<template>
  <div id="app">
    <div class="app-page" :class="{'app-page-with-tabbar': tabbarVisible}">
      <keep-alive>
        <router-view v-if="$route.meta.keepAlive"></router-view>
      </keep-alive>
      <router-view v-if="!$route.meta.keepAlive"></router-view>
    </div>
    <div class="app-tabbar" v-if="tabbarVisible">
      <tabbar :items="items"></tabbar>
    </div>
  </div>
</template>

<script>
import {tabbarConfig} from './config/tabbar'
import {mapActions} from 'vuex'
import tabbar from './pages/common/tabbar'
export default {
  name: 'App',
  data () {
    return {
      items: tabbarConfig
    }
  },
  computed: {
    tabbarVisible () {
      const routes = this.items.map(value => {
        return value.path
      })
      return routes.includes(this.$route.name)
    }
  },
  components: {
    tabbar
  },
  methods: {
    ...mapActions('user', ['getUserInfo'])
  },
  created () {
    this.getUserInfo()
  }
}
</script>

<style lang="less" scoped>
#app {
  position: absolute;
  top:0;
  bottom:0;
  width:100%;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 24px;
  background: #f3f5f4;
  .app-page{
    position: absolute;
    top:0;
    bottom:0;
    width:100%;
    &-with-tabbar{
      bottom:100px;
    }
  }
  .app-tabbar{
    position: absolute;
    bottom:0;
    width:100%;
  }
}
</style>
