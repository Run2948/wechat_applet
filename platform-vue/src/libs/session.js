import Cookies from 'js-cookie'
import Lockr from 'lockr'
import {TOKEN_KEY, HISTORY_KEY} from '@/config/app'

export default {
  setToken: function (token) {
    Cookies.set(TOKEN_KEY, token)
  },
  clearToken: function () {
    Cookies.remove(TOKEN_KEY)
  },
  isLogin: function () {
    return Cookies.get(TOKEN_KEY)
  },
  getHistory: function () {
    let arrHistory = Lockr.get(HISTORY_KEY)
    if (!arrHistory) {
      arrHistory = []
    }
    return arrHistory
  },
  addHistory: function (history) {
    let arrHistory = this.getHistory()
    arrHistory.push(history)
    Lockr.set(HISTORY_KEY, arrHistory)
  },
  clearHistory: function () {
    Lockr.rm(HISTORY_KEY)
  }
}
