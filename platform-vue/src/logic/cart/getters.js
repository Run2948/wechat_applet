export const getters = {
  cartGroup: state => {
    let map = {}
    let dest = []
    let arr = state.cart.list
    let sid = state.cart.select.map(k => {
      return k.goodsId
    })
    for (let i = 0; i < arr.length; i++) {
      let ai = arr[i]
      if (sid.includes(ai.goodsId)) {
        ai.select = true
      } else {
        ai.select = false
      }
      if (!map[ai.shopId]) {
        dest.push({
          shopId: ai.shopId,
          shopName: ai.shopName,
          list: [ai]
        })
        map[ai.shopId] = ai
      } else {
        for (let j = 0; j < dest.length; j++) {
          let dj = dest[j]
          if (dj.shopId === ai.shopId) {
            dj.list.push(ai)
            break
          }
        }
      }
    }
    for (let k = 0; k < dest.length; k++) {
      let isAll = true
      for (let l = 0; l < dest[k].list.length; l++) {
        if (!sid.includes(dest[k].list[l].goodsId)) {
          isAll = false
        }
      }
      if (isAll) {
        dest[k].select = true
      } else {
        dest[k].select = false
      }
    }
    return dest
  },
  cartInfo: state => {
    let isAll = false
    let price = 0
    let quantity = 0
    let select = state.cart.select
    let model = state.cart.model
    if (state.cart.list.length === state.cart.select.length) {
      isAll = true
    }
    for (let i = 0; i < state.cart.select.length; i++) {
      quantity += 1
      price += state.cart.select[i].price * state.cart.select[i].number
    }
    return {
      isAll,
      price,
      quantity,
      select,
      model
    }
  }
}
