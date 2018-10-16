import Vue from 'vue'

Vue.filter('avatar', function (value) {
  if (value) return value
  return require('../static/images/common/default_avatar.png')
})
