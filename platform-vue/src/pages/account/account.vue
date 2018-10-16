<template>
  <page page-background="white" :better-scroll="true">
    <m-cell-group title="基础资料">
      <m-cell
          title="头像"
          :is-link="true"
          @click.native="updateAvatar">
        <div class="account-avatar">
          <img class="ignore" :src="profile.avatar | avatar">
        </div>
      </m-cell>
      <m-cell
          title="账号"
          value="13680065830"></m-cell>
      <m-cell
          title="昵称"
          :value="profile.nickName"
          :is-link="true"
          @click.native="$go('/account/name')"></m-cell>
    </m-cell-group>
    <m-cell-group
        title="保密资料">
      <m-cell
          title="手机号"
          value="13680065830"
          :is-link="true"></m-cell>
      <m-cell title="密码"
              :is-link="true"></m-cell>
    </m-cell-group>
    <m-button
        color="#b4272d"
        styles="margin:0.3733rem 0.3733rem 0 0.3733rem;"
        @click.native="logout">
      <span>退出登录</span>
    </m-button>
  </page>
</template>

<script type="text/ecmascript-6">
import {mapState, mapActions} from 'vuex'

export default {
  computed: {
    ...mapState('user', ['profile'])
  },
  methods: {
    ...mapActions('user', ['logout']),
    updateAvatar () {
      const title = '选择照片'
      const data = [
        {
          id: 1,
          content: '拍照'
        },
        {
          id: 2,
          content: '本地上传'
        }
      ]
      this.$sheet(title, data, (item, index) => {
        // @TODO
      })
    }
  }
}
</script>

<style lang="less" scoped>
  .account-avatar {
    display: inline-block;
    height: 104px;
    width: 104px;
    margin-right: 54px;
    .ignore {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }
</style>
