import axios from '@/libs/api.request'
export const user = {
  login: (params) => {
    return axios.request({
      url: 'user/login',
      data: params,
      method: 'post'
    })
  },
  wechatLogin: (params) => {
    return axios.request({
      url: 'user/wechatLogin',
      data: params,
      method: 'post'
    })
  },
  getUserInfo: (params) => {
    return axios.request({
      url: 'user/getUserInfo',
      data: params,
      method: 'post'
    })
  },
  updateUserInfo: (params) => {
    return axios.request({
      url: 'user/updateUserInfo',
      data: params,
      method: 'post'
    })
  }
}
