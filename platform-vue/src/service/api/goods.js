import axios from '@/libs/api.request'
export const goods = {
  getGoodsInfo: (params) => {
    return axios.request({
      url: 'goods/getGoodsInfo',
      data: params,
      method: 'post'
    })
  }
}
