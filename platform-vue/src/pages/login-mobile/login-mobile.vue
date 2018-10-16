<template>
  <page>
    <div class="login-logo">
      <div class="logo-wrap">
        <img class="ignore" src="../../static/images/login/logo.png" alt="">
      </div>
    </div>
    <div class="login-form">
      <m-input type="text" placeholder="手机号" v-model="form.mobile"></m-input>
      <m-input type="password" placeholder="验证码" v-model="form.password">
        <div class="input-verify" @click="getIdentifyingCode">
          <span>{{identifyingCode.time === 60 ? '获取验证码' : `${identifyingCode.time}秒后重新获取`}}</span>
        </div>
      </m-input>
      <m-button color="#b4272d" styles="margin-top:28px" @click.native="loginHandler">
        <span>登录</span>
      </m-button>
    </div>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'
export default {
  data () {
    return {
      form: {
        mobile: 13680065830,
        password: ''
      }
    }
  },
  computed: {
    ...mapState('common', ['identifyingCode'])
  },
  methods: {
    ...mapActions('common', ['getIdentifyingCode']),
    ...mapActions('user', ['login']),
    loginHandler () {
      this.login(this.form).then(res => {
        res && this.$go('/home')
      })
    }
  }
}
</script>

<style lang="less" scoped>
  @import '../../static/less/index';

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
  .login-form{
    padding:0 27px;
    .input-verify{
      span{
        color:#999;
      }
    }
  }
</style>
