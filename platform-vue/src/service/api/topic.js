import axios from '@/libs/api.request'
export const topic = {
  getTopicInfo: (params) => {
    return axios.request({
      url: 'topic/getTopicInfo',
      data: params,
      method: 'post'
    })
  }
}
