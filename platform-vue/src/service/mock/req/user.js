export default {
  login: req => {
    return {
      success: true,
      code: 200,
      data: {token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzIxNTA4NTksImV4cCI6MTUzNDc0Mjg1OSwiZGF0YSI6eyJ1c2VyX2lkIjo1fX0.fd3dzT90WBKH4CchwsLIYBiym9KPQWBlkDWQQ8Vj5jM'},
      msg: '登陆成功'
    }
  },
  wechatLogin: req => {
    return {
      success: true,
      code: 200,
      data: {token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MzIxNTA4NTksImV4cCI6MTUzNDc0Mjg1OSwiZGF0YSI6eyJ1c2VyX2lkIjo1fX0.fd3dzT90WBKH4CchwsLIYBiym9KPQWBlkDWQQ8Vj5jM'},
      msg: '登陆成功'
    }
  },
  getUserInfo: req => {
    return {
      success: true,
      code: 200,
      data: {
        nick_name: 'Neho',
        avatar: '//api.neho.top/static/images/201809051504249715.jpeg',
        age: 24
      },
      msg: '获取用户信息成功'
    }
  },
  updateUserInfo: req => {
    return {
      success: true,
      code: 200,
      data: null,
      msg: '更新用户信息成功'
    }
  }
}
