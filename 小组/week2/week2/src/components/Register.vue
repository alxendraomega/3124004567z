<template>
    <div class="register-container">
      <h2>注册</h2>
      <form @submit.prevent="handleRegister">
        <label for="email">邮箱:</label>
        <input type="email" id="email" v-model="email" required placeholder="请输入邮箱">
        <span v-if="!email" class="error-msg">邮箱为必填项</span>
        <br>
        <label for="phone">手机号:</label>
        <input type="number" id="phone" v-model="phone" required placeholder="请输入手机号">
        <span v-if="!phone" class="error-msg">手机号为必填项</span>
        <span v-if="phone && phone.toString().length!== 11" class="error-msg">手机号必须为11位数字</span>
        <br>
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required placeholder="请输入密码">
        <span v-if="!password" class="error-msg">密码为必填项</span>
        <br>
        <label for="confirmPassword">确认密码:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required placeholder="请再次输入密码">
        <span v-if="!confirmPassword" class="error-msg">确认密码为必填项</span>
        <span v-if="password && confirmPassword && password!== confirmPassword" class="error-msg">两次密码不一致</span>
        <br>
        <button type="submit">注册</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name:"RegisterComponent",
    data() {
      return {
        email: '',
        phone: '',
        password: '',
        confirmPassword: ''
      };
    },
    methods: {
      handleRegister() {
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailRegex.test(this.email)) {
          alert('请输入正确的邮箱格式');
          return;
        }
        if (this.phone.toString().length!== 11 || isNaN(this.phone)) {
          alert('请输入正确的11位手机号');
          return;
        }
        if (this.password!== this.confirmPassword) {
          alert('两次输入的密码不一致');
          return;
        }
        alert('注册成功');
      }
    }
  };
  </script>
  
  <style scoped>
  .register-container {
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