import Vue from 'vue'

import mDemo from './packages/m-demo'
import mButton from './packages/m-button'
import mFloor from './packages/m-floor'
import mBannerFloor from './packages/m-banner-floor'
import mGoods from './packages/m-goods'
import mNumber from './packages/m-number'
import mCell from './packages/m-cell'
import mCellGroup from './packages/m-cell-group'
import mInput from './packages/m-input'
import mPopup from './packages/m-popup'

const version = '1.0.0'

const install = function (Vue, config = {}) {
  if (install.installed) return

  Vue.component(mDemo.name, mDemo)
  Vue.component(mButton.name, mButton)
  Vue.component(mFloor.name, mFloor)
  Vue.component(mBannerFloor.name, mBannerFloor)
  Vue.component(mGoods.name, mGoods)
  Vue.component(mNumber.name, mNumber)
  Vue.component(mCell.name, mCell)
  Vue.component(mCellGroup.name, mCellGroup)
  Vue.component(mInput.name, mInput)
  Vue.component(mPopup.name, mPopup)
}

Vue.use({ version, install })

Vue.$mPopup = Vue.prototype.$mPopup = mPopup
