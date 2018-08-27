<style lang="less">
@import './login.less';
</style>

<template>
<div class="login">
  <div class="login-con">
    <Card icon="log-in" title="欢迎登录" :bordered="false">
      <div class="form-con">
        <login-form @on-success-valid="handleSubmit"></login-form>
        <p class="login-tip">输入任意用户名和密码即可</p>
      </div>
    </Card>
  </div>
</div>
</template>

<script>
import LoginForm from '_c/login-form'
// import {
//   mapActions
// } from 'vuex'
import Cookies from 'js-cookie'
export default {
  components: {
    LoginForm
  },
  methods: {
    // ...mapActions([
    //   'handleLogin',
    //   'getUserInfo'
    // ]),
    handleSubmit ({
      userName,
      password
    }) {
      Cookies.set('user', userName)
      Cookies.set('password', password)
      this.$store.commit('userAvator', 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png')
      Cookies.set('access', 'super_admin')
      Cookies.set('token', 'super_admin')
      this.$router.push({
        name: 'home'
      })
      // this.handleLogin({ userName, password }).then(res => {
      //   this.getUserInfo().then(res => {
      //     this.$router.push({
      //       name: 'home'
      //     })
      //   })
      // })
    }
  }
}
</script>

<style>

</style>
