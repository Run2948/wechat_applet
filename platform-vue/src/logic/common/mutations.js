import * as types from './mutation-types'

export const mutations = {
  [types.INIT_IDENTIFY_TIMER] (state, o) {
    state.identifyingCode.timer = o
  },
  [types.CLEAR_IDENTIFY_TIMER] (state) {
    clearInterval(state.identifyingCode.timer)
    state.identifyingCode.timer = null
    state.identifyingCode.time = 60
  },
  [types.UPDATE_IDENTIFY_TIMER] (state, n) {
    state.identifyingCode.time = n
  },
  [types.ADD_POPUP] (state, o) {
    state.popup.push(o)
  },
  [types.CLEAR_POPUP] (state) {
    if (state.popup.length) {
      state.popup[0].close()
      state.popup.splice(0, 1)
    }
  }
}
