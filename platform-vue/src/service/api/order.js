import axios from '@/libs/api.request'
export const order = {
  getOrderList: (params) => {
    return axios.request({
      url: 'order/getOrderList',
      data: params,
      method: 'post'
    })
  }
}
