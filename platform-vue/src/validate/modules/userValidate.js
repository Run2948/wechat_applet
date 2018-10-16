import {Validate} from './validate.js'

export class UserValidate extends Validate {
  _rule = {
    account: 'require|phone',
    password: 'require'
  }
  _message = {
    'account.require': '手机号必填',
    'account.phone': '手机号码格式不正确',
    'password.require': '密码必填'
  }
  _scene = {
    login: ['account', 'password']
  }
}
