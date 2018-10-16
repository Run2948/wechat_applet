import Vue from 'vue'
import Vuex from 'vuex'
import commonModule from './common/index'
import userModule from './user/index'
import cartModule from './cart/index'
import categoryModule from './category/index'
import homeModule from './home/index'
import searchModule from './search/index'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    common: commonModule,
    user: userModule,
    cart: cartModule,
    category: categoryModule,
    home: homeModule,
    search: searchModule
  }
})
