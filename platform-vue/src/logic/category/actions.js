import * as types from './mutation-types'
import {api} from '@/service/api'

export const actions = {
  async getCategory ({commit}) {
    let res = await api.category.getCategory()
    if (res.success) {
      commit(types.INIT_CATEGORY, res.data)
    }
  },
  async getSubCategoryByPid ({commit}) {
    let res = await api.category.getSubCategoryByPid()
    if (res.success) {
      commit(types.INIT_SUB_CATEGORY, res.data)
      return res.data
    }
  },
  async getGoodsByCategory ({commit}) {
    let res = await api.category.getGoodsByCategory()
    if (res.success) {
      commit(types.INIT_GOODS_BY_CATEGORY, res.data)
    }
  }
}
