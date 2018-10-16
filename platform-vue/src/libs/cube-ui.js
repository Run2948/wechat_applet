import Vue from 'vue'
import {
  /* eslint-disable no-unused-vars */
  Style,
  Button,
  Scroll,
  Input,
  Toast,
  ActionSheet,
  Slide,
  Dialog
} from 'cube-ui'

Vue.use(Button)
Vue.use(Scroll)
Vue.use(Input)
Vue.use(Toast)
Vue.use(ActionSheet)
Vue.use(Slide)
Vue.use(Dialog)

export const toast = (text, type) => {
  Toast.$create({
    $props: {
      time: 1000,
      txt: text,
      type: type || 'correct'
    }
  }).show()
}

export const sheet = (title, data, fn) => {
  ActionSheet.$create({
    $props: {
      title: title,
      data: data,
      onSelect: (item, index) => {
        fn(item, index)
      }
    }
  }).show()
}

Vue.prototype.$toast = toast
Vue.prototype.$sheet = sheet
