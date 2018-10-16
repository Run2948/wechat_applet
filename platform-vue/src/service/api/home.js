import axios from '@/libs/api.request'
export const home = {
  getHomeInfo: (params) => {
    return axios.request({
      url: 'home/getHomeInfo',
      data: params,
      method: 'post'
    })
  }
}
