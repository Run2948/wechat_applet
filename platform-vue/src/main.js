// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

// css
import 'normalize.css'
import 'animate.css'
import '@/static/less/index.less'

// libs
import '@/libs/cube-ui'
import '@/components'
import 'amfe-flexible'

// routes
import {router} from '@/router'

// extends
import '@/extends'

// mock
import '@/service/mock'

// logic
import store from '@/logic'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
