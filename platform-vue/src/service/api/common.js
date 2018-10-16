import axios from '@/libs/api.request'
export const common = {
  getIdentifyingCode: (params) => {
    return axios.request({
      url: 'common/getIdentifyingCode',
      data: params,
      method: 'post'
    })
  }
}
