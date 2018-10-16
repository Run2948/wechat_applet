import Vue from 'vue'
import {router} from '@/router'

Vue.prototype.$go = function (path, cache = true) {
  if (cache) {
    router.push(path)
  } else {
    router.replace(path)
  }
}
Vue.prototype.$back = function () {
  router.go(-1)
}
Vue.prototype.$randomColor = function (a) {
  let r = Math.floor(Math.random() * 255)
  let g = Math.floor(Math.random() * 255)
  let b = Math.floor(Math.random() * 255)
  return 'rgba(' + r + ',' + g + ',' + b + ',' + a + ')'
}
