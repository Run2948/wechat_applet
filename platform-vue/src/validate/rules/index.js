export const rules = {
  require (val) {
    return !!val
  },
  phone (val) {
    const phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/
    return phoneReg.test(val)
  },
  isNumber (val) {
    return /^[0-9]+.?[0-9]*/.test(val)
  }
}
