import {rules} from '../rules'

export class Validate {
  constructor (scene) {
    this._currentSecene = scene
    this.success = ''
    this.errorMsg = ''
  }

  _getItems () {
    return this._scene[this._currentSecene]
  }

  _getTypes (a) {
    return this._rule[a].split('|')
  }

  _getErrorMsg (b) {
    return this._message[b]
  }

  _callRule (e, g) {
    const fn = rules[e]
    return fn(g)
  }

  check (f) {
    const arrItems = this._getItems()
    for (let c = 0; c < arrItems.length; c++) {
      let item = arrItems[c]
      let arrTypes = this._getTypes(item)
      for (let d = 0; d < arrTypes.length; d++) {
        let type = arrTypes[d]
        let result = this._callRule(type, f[item])
        if (!result) {
          this.success = false
          this.errorMsg = this._getErrorMsg(`${item}.${type}`)
          return this
        }
      }
    }
    this.success = true
    return this
  }
}
