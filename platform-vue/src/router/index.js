import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from '@/config/routes'
import {routerInterceptor} from './interceptor'

Vue.use(VueRouter)

export const router = new VueRouter({
  mode: 'history',
  routes
})

routerInterceptor(router)
