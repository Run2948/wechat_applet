export default {
  getUserCartInfo: req => {
    return {
      success: true,
      code: 200,
      data: {token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzIxNTA4NTksImV4cCI6MTUzNDc0Mjg1OSwiZGF0YSI6eyJ1c2VyX2lkIjo1fX0.fd3dzT90WBKH4CchwsLIYBiym9KPQWBlkDWQQ8Vj5jM'},
      msg: '获取购物车信息成功'
    }
  },
  updateUserCartGoods: req => {
    return {
      success: true,
      code: 200,
      data: null,
      msg: '更新购物车信息成功'
    }
  },
  deleteUserCartGoods: req => {
    return {
      success: true,
      code: 200,
      data: null,
      msg: '更新购物车信息成功'
    }
  },
  checkout: req => {
    return {
      success: true,
      code: 200,
      data: {
        checkoutId: 12576
      },
      msg: '获取购物信息成功'
    }
  },
  getCheckoutInfo: req => {
    return {
      success: true,
      code: 200,
      data: {
        address: {
          id: 1,
          name: '邓海标',
          mobile: '13680065830'
        },
        pay: {
          wechat: {
            able: true,
            default: true
          },
          alipay: {
            able: true,
            default: false
          }
        },
        goods: {},
        deliver: {},
        price: {}
      },
      msg: '获取购物信息成功'
    }
  },
  getTransactionInfo: req => {
    return {
      success: true,
      code: 200,
      data: {
        transactionId: 5277,
        transactionNumber: '8736293828302832'
      },
      msg: '获取交易信息成功'
    }
  }
}
