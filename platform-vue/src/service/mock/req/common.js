export default {
  getIdentifyingCode: req => {
    return {
      success: true,
      code: 200,
      data: null,
      msg: '发送验证码成功'
    }
  }
}
