<template>
  <page>
    <m-nav-header slot="top"></m-nav-header>
    <div class="login-logo">
      <div class="logo-wrap">
        <img class="ignore" src="../../static/images/login/logo.png" alt="">
      </div>
    </div>
    <div class="login-btns">
      <m-button color="#b4272d" styles="margin-bottom:16px" @click.native="getOauthCode">
        <i class="iconfont" style="padding-right:5px">&#xe6db;</i>
        <span>微信授权登陆</span>
      </m-button>
      <m-button @click.native="$go('/login-mobile')">
        <i class="iconfont" style="padding-right:5px">&#xe631;</i>
        <span>手机号码登陆</span>
      </m-button>
    </div>
  </page>
</template>

<script type="text/ecmascript-6">
import mNavHeader from '../common/nav-header'
import {wechat} from '@/service/wechat'
import {mapActions} from 'vuex'
export default {
  components: {
    mNavHeader
  },
  methods: {
    ...mapActions('user', ['wechatLogin']),
    getOauthCode: wechat.oauth.getOauthCode,
    wechatLoginHandler () {
      this.wechatLogin().then(res => {
        this.$go('/home')
      })
    }
  },
  created () {
    if (this.$route.query.code) {
      this.wechatLoginHandler()
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';

  .login-header {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 88px;
    background: white;
    .header-left {
      position: absolute;
      top: 50%;
      left: 27px;
      transform: translateY(-50%);
      i {
        font-size: 55px;
        color: #333;
      }
    }
    h3 {
      font-size: 34px;
      font-size: @title-color;
    }
    .header-right {
      position: absolute;
      top: 50%;
      right: 27px;
      transform: translateY(-50%);
      i {
        font-size: 55px;
        color: #333;
        &:not(:last-child) {
          padding-right: 27px;
        }
      }
    }
  }

  .login-logo {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 410px;
    .logo-wrap {
      display: inline-block;
      width: 300px;
      height: 100px;
      .ignore {
        width: 100%;
        height: 100%;
      }
    }
  }

  .login-btns {
    padding: 0 40px;
  }
</style>
