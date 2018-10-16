import * as types from './mutation-types'

export const mutations = {
  [types.INIT_USER_INFO] (state, o) {
    state.profile.nickName = o.nick_name
    state.profile.avatar = o.avatar
    state.profile.age = o.age
  },
  [types.SET_LOGIN_STATE] (state, b) {
    state.isLogin = b
  }
}
