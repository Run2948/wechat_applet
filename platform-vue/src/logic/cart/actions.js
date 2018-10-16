import * as types from './mutation-types'
import { api } from '@/service/api'

export const actions = {
  async getUserCartInfo ({commit}) {
    let res = await api.cart.getUserCartInfo()
    if (res.success) {
      commit(types.INIT_CART_INFO, res)
    }
  },
  async updateUserCartGoods ({commit}, o) {
    const updateInfo = {
      goodsId: o.goods.goodsId,
      number: o.type === 'sub' ? (parseInt(o.number) - 1) : (parseInt(o.number) + 1)
    }
    let res = await api.cart.updateUserCartGoods()
    if (res.success) {
      commit(types.UPDATE_CART_GOODS_BY_ID, updateInfo)
    }
  },
  async deleteUserCartGoods ({commit}, a) {
    const deleteGoodsIds = a.map(i => {
      return i.goodsId
    })
    let res = await api.cart.deleteUserCartGoods()
    if (res.success) {
      commit(types.DELETE_CART_GOODS_BY_IDS, deleteGoodsIds)
    }
  },
  async checkout ({commit}, a) {
    let res = await api.cart.checkout()
    if (res.success) {
      return res.data.checkoutId
    }
  },
  async getCheckoutInfo ({commit}, n) {
    let res = await api.cart.getCheckoutInfo()
    if (res.success) {
      return res.data
    }
  },
  async pay ({dispatch, commit}, n) {
    // 具体的支付逻辑，包括微信，支付宝支付，业务服务器的逻辑
    // 清空购物车数据， 重新获取购物车信息
    commit(types.CLEAR_CART)
    dispatch('getUserCartInfo')
  },
  async getTransactionInfo ({commit}, n) {
    let res = await api.cart.getTransactionInfo()
    if (res.success) {
      return res.data
    }
  }
}
