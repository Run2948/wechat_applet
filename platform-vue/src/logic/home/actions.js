import * as types from './mutation-types'
import {api} from '@/service/api'

export const actions = {
  async getHomeInfo ({commit}) {
    let res = await api.home.getHomeInfo()
    if (res.success) {
      commit(types.INIT_HOME, res.data)
    }
  }
}
