import * as types from './mutation-types'
import {UserValidate} from '@/validate'
import {api} from '@/service/api'
import {toast} from '@/libs/cube-ui'
import session from '@/libs/session'

export const actions = {
  async login ({dispatch}, o) {
    const form = {
      account: o.mobile,
      password: o.password
    }
    let validate = new UserValidate('login').check(form)
    if (validate.success) {
      let res = await api.user.login(form)
      if (res.success) {
        session.setToken(res.data.token)
        let res1 = await dispatch('getUserInfo')
        toast('登录成功', 'correct')
        return res1
      } else {
        toast(res.msg, 'warn')
      }
    } else {
      toast(validate.errorMsg, 'warn')
    }
  },
  async wechatLogin ({dispatch}, s) {
    const form = {
      code: s
    }
    let res = await api.user.wechatLogin(form)
    if (res.success) {
      session.setToken(res.data.token)
      let res1 = await dispatch('getUserInfo')
      return res1
    } else {
      toast(res.msg, 'warn')
    }
  },
  async getUserInfo ({commit}) {
    if (session.isLogin()) {
      let res = await api.user.getUserInfo()
      if (res.success) {
        commit(types.INIT_USER_INFO, res.data)
        commit(types.SET_LOGIN_STATE, true)
        return res.data
      } else {
        toast(res.msg, 'warn')
      }
    }
  },
  async updateUserInfo ({dispatch}, o) {
    let res = await api.user.updateUserInfo()
    if (res.success) {
      let res1 = await dispatch('getUserInfo')
      if (res1) {
        toast('修改昵称成功', 'correct')
        return true
      }
    } else {
      toast(res.msg, 'warn')
    }
  },
  async getUserOrder ({commit}, o) {
    let res = await api.user.getUserOrder()
    if (res.success) {
      // @TODO
    } else {
      toast(res.msg, 'warn')
    }
  },
  async logout ({commit}) {
    session.clearToken()
    toast('退出成功', 'correct')
    window.location.href = '/'
  }
}
