import Vue from 'vue'
import logic from '@/logic'

const PopupConstructor = Vue.extend(require('./m-popup.vue').default)

const getAnInstance = () => {
  return new PopupConstructor({
    el: document.createElement('div')
  })
}

const instance = getAnInstance()

instance.close = () => {
  if (instance.$el.parentNode) {
    instance.$el.parentNode.removeChild(instance.$el)
    logic.commit('common/CLEAR_POPUP')
  }
}

let mPopup = (options, render) => {
  instance.mask = options ? options.mask : false
  if (render) {
    instance.expand = render
  }
  document.body.appendChild(instance.$el)
  Vue.nextTick(function () {
    logic.commit('common/ADD_POPUP', instance)
    instance.init()
  })
  return instance
}

export default mPopup
