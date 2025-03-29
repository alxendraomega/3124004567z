<template>
    <div class="login-container">
      <h2>登录</h2>
      <form @submit.prevent="handleLogin">
        <label for="email">邮箱:</label>
        <input type="email" id="email" v-model="email" required placeholder="请输入邮箱">
        <span v-if="!email" class="error-msg">邮箱为必填项</span>
        <span v-if="email &&!isValidEmail(email)" class="error-msg">请输入正确的邮箱格式</span>
        <br>
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required placeholder="请输入密码">
        <span v-if="!password" class="error-msg">密码为必填项</span>
        <br>
        <button type="submit">登录</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
     name:"LoginComponent",
    data() {
       
      return {
        email: '',
        password: ''
      };
    },
    methods: {
      handleLogin() {
        if (!this.isValidEmail(this.email)) {
          alert('请输入正确的邮箱格式');
          return;
        }
        if (!this.password) {
          alert('请输入密码');
          return;
        }
        alert('登录成功');
      },
      isValidEmail(email) {
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegex.test(email);
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
  }
  .error-msg {
    color: red;
    font-size: 12px;
  }
  </style>