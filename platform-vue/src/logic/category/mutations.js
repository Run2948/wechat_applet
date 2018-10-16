import * as types from './mutation-types'

export const mutations = {
  [types.INIT_CATEGORY] (state, a) {
    state.category = a
  },
  [types.INIT_SUB_CATEGORY] (state, o) {
    state.subCategory = o
  },
  [types.INIT_GOODS_BY_CATEGORY] (state, a) {
    state.goodsByCategory = a
  }
}
