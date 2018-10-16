import axios from '@/libs/api.request'
export const category = {
  getCategory: (params) => {
    return axios.request({
      url: 'category/getCategory',
      data: params,
      method: 'post'
    })
  },
  getSubCategoryByPid: (params) => {
    return axios.request({
      url: 'category/getSubCategoryByPid',
      data: params,
      method: 'post'
    })
  },
  getGoodsByCategory: (params) => {
    return axios.request({
      url: 'category/getGoodsByCategory',
      data: params,
      method: 'post'
    })
  }
}
