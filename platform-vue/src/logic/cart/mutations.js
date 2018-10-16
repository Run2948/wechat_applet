import * as types from './mutation-types'

export const mutations = {
  [types.INIT_CART_INFO] (state, a) {
    // @TODO
  },
  [types.TOGGLE_MODEL] (state) {
    state.cart.model = !state.cart.model
  },
  [types.SELECT_GOODS] (state, o) {
    for (let i = 0; i < state.cart.select.length; i++) {
      if (state.cart.select[i].goodsId === o.goodsId) {
        state.cart.select.splice(i, 1)
        return
      }
    }
    state.cart.select.push(o)
  },
  [types.SELECT_GROUP] (state, o) {
    let gid = state.cart.list.filter(i => {
      return i.shopId === o.shopId
    }).map(j => {
      return j.goodsId
    })
    let sid = state.cart.select.map(k => {
      return k.goodsId
    })
    let isAll = true
    for (let l = 0; l < gid.length; l++) {
      if (!sid.includes(gid[l])) {
        isAll = false
      }
    }
    state.cart.select = state.cart.select.filter(m => {
      return m.shopId !== o.shopId
    })
    if (!isAll) {
      for (let n = 0; n < state.cart.list.length; n++) {
        if (state.cart.list[n].shopId === o.shopId) {
          state.cart.select.push(state.cart.list[n])
        }
      }
    }
  },
  [types.SELECT_ALL] (state) {
    if (state.cart.list.length > state.cart.select.length) {
      state.cart.select = state.cart.list.filter(i => {
        return true
      })
    } else {
      state.cart.select = []
    }
  },
  [types.UPDATE_CART_GOODS_BY_ID] (state, o) {
    for (let i = 0; i < state.cart.list.length; i++) {
      if (state.cart.list[i].goodsId === o.goodsId) {
        state.cart.list[i].number = o.number
        break
      }
    }
    for (let i = 0; i < state.cart.select.length; i++) {
      if (state.cart.select[i].goodsId === o.goodsId) {
        state.cart.select[i].number = o.number
        return
      }
    }
  },
  [types.DELETE_CART_GOODS_BY_IDS] (state, a) {
    state.cart.list = state.cart.list.filter(i => {
      return !a.includes(i.goodsId)
    })
    state.cart.select = state.cart.select.filter(i => {
      return !a.includes(i.goodsId)
    })
  },
  [types.CLEAR_CART] (state, a) {
    state.cart.list = []
    state.cart.select = []
  }
}
