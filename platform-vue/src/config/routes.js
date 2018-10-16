export default [
  {
    path: '/',
    name: '/',
    redirect: '/home'
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/pages/login/login')
  },
  {
    path: '/login-mobile',
    name: 'loginMobile',
    meta: {
      title: '手机号登录'
    },
    component: () => import('@/pages/login-mobile/login-mobile')
  },
  {
    path: '/home',
    name: 'home',
    meta: {
      keepAlive: true
    },
    component: () => import('@/pages/home/home')
  },
  {
    path: '/category',
    name: 'category',
    meta: {
      keepAlive: true
    },
    component: () => import('@/pages/category/category')
  },
  {
    path: '/topic',
    name: 'topic',
    meta: {
      keepAlive: true
    },
    component: () => import('@/pages/topic/topic')
  },
  {
    path: '/cart',
    name: 'cart',
    meta: {
      keepAlive: true
    },
    component: () => import('@/pages/cart/cart')
  },
  {
    path: '/center',
    name: 'center',
    meta: {
      keepAlive: true
    },
    component: () => import('@/pages/center/center')
  },
  {
    path: '/account',
    name: 'account',
    meta: {
      title: '个人资料',
      requiresAuth: true
    },
    component: () => import('@/pages/account/account')
  },
  {
    path: '/address/update',
    name: 'address-update',
    meta: {
      requiresAuth: true
    },
    component: () => import('@/pages/address-update/address-update')
  },
  {
    path: '/account/name',
    name: 'account-name',
    meta: {
      title: '修改昵称',
      requiresAuth: true
    },
    component: () => import('@/pages/account-name/account-name')
  },
  {
    path: '/search',
    name: 'search',
    component: () => import('@/pages/search/search')
  },
  {
    path: '/goods-by-category',
    name: 'goods-by-category',
    component: () => import('@/pages/goods-by-category/goods-by-category')
  },
  {
    path: '/order',
    name: 'order',
    meta: {
      title: '我的订单',
      requiresAuth: true
    },
    component: () => import('@/pages/order/order')
  },
  {
    path: '/checkout',
    name: 'checkout',
    meta: {
      title: '确认信息',
      requiresAuth: true
    },
    component: () => import('@/pages/checkout/checkout')
  },
  {
    path: '/checkout/address',
    name: 'checkout-address',
    meta: {
      title: '选择地址',
      requiresAuth: true
    },
    component: () => import('@/pages/checkout-address/checkout-address')
  },
  {
    path: '/transaction/:id',
    name: 'transaction',
    meta: {
      requiresAuth: true
    },
    component: () => import('@/pages/transaction/transaction')
  },
  {
    path: '/goods/:id',
    name: 'goods',
    component: () => import('@/pages/goods/goods')
  },
  {
    path: '/evaluate',
    name: 'evaluate',
    meta: {
      title: '评价详情'
    },
    component: () => import('@/pages/evaluate/evaluate')
  }
]
