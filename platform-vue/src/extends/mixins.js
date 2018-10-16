import Vue from 'vue'
import logic from '@/logic'

Vue.mixin({
  beforeRouteLeave (to, from, next) {
    if (logic.state.common.popup.length) {
      logic.commit('common/CLEAR_POPUP')
    } else {
      next()
    }
  }
})
