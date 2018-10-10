/**
 * 用户相关服务
 */

const util = require('../utils/util.js');
const api = require('../config/api.js');


/**
 * 调用微信登录
 */
function loginByWeixin(userInfo) {
  console.log("-----1----")
  console.log(userInfo.userInfo) 
  let code = null;
  return new Promise(function (resolve, reject) {
    return util.login().then((res) => {
      code = res.code; 
      return userInfo;
    }).then((userInfo) => {
      //登录远程服务器
      let params={};
      params.code=code;
      params.avatarUrl = userInfo.userInfo.avatarUrl;
      params.city = userInfo.userInfo.city;
      params.country = userInfo.userInfo.country;
      params.gender = userInfo.userInfo.gender;
      params.language = userInfo.userInfo.language;
      params.nickName = userInfo.userInfo.nickName;
      params.province = userInfo.userInfo.province;
      util.request(api.AuthLoginByWeixin, params, 'POST').then(res => {
        if (res.errno === 0) {
          console.log("------3------")
          //存储用户信息
          wx.setStorageSync('userInfo', userInfo);
          wx.setStorageSync('token', res.data.openid);
          console.log(res.data.openid)

          resolve(res);
        } else {
          util.showErrorToast(res.errmsg)
          reject(res);
          console.log("------2------")
        }
      }).catch((err) => {
        reject(err);
      });
    }).catch((err) => {
      reject(err);
    })
  });
}

/**
 * 判断用户是否登录
 */
function checkLogin() {
  return new Promise(function (resolve, reject) {
    if (wx.getStorageSync('userInfo') && wx.getStorageSync('token')) {

      util.checkSession().then(() => {
        resolve(true);
      }).catch(() => {
        reject(false);
      });

    } else {
      reject(false);
    }
  });
}


module.exports = {
  loginByWeixin,
  checkLogin,
};











