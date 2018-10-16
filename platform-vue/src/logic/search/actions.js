import {api} from '@/service/api'

export const actions = {
  async getSearchHot () {
    let res = await api.search.getSearchHot()
    if (res.success) {
      return res.data
    }
  },
  async getSearchListBykeyword () {
    let res = await api.search.getSearchListBykeyword()
    if (res.success) {
      return res.data
    }
  }
}
