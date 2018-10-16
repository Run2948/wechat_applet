import axios from '@/libs/api.request'
export const search = {
  getSearchHot: (params) => {
    return axios.request({
      url: 'search/getSearchHot',
      data: params,
      method: 'post'
    })
  },
  getSearchListBykeyword: (params) => {
    return axios.request({
      url: 'search/getSearchListBykeyword',
      data: params,
      method: 'post'
    })
  }
}
