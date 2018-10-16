import axios from '@/libs/api.request'
export const cart = {
  getUserCartInfo: (params) => {
    return axios.request({
      url: 'cart/getUserCartInfo',
      data: params,
      method: 'post'
    })
  },
  updateUserCartGoods: (params) => {
    return axios.request({
      url: 'cart/updateUserCartGoods',
      data: params,
      method: 'post'
    })
  },
  deleteUserCartGoods: (params) => {
    return axios.request({
      url: 'cart/deleteUserCartGoods',
      data: params,
      method: 'post'
    })
  },
  checkout: (params) => {
    return axios.request({
      url: 'cart/checkout',
      data: params,
      method: 'post'
    })
  },
  getCheckoutInfo: (params) => {
    return axios.request({
      url: 'cart/getCheckoutInfo',
      data: params,
      method: 'post'
    })
  },
  getTransactionInfo: (params) => {
    return axios.request({
      url: 'cart/getTransactionInfo',
      data: params,
      method: 'post'
    })
  }
}
