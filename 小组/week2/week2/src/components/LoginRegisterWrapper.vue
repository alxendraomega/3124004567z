<template>
    <div class="wrapper">
      <button @click="showLogin =!showLogin">{{ showLogin? '切换到注册' : '切换到登录' }}</button>
      <LoginComponent v-if="showLogin" />
      <RegisterComponent v-else />
    </div>
  </template>
  
  <script>
  import LoginComponent from './Login.vue';
  import RegisterComponent from './Register.vue';
  
  export default {
    components: {
      LoginComponent,
      RegisterComponent
    },
    data() {
      return {
        showLogin: true
      };
    }
  };
  </script>
  
  <style scoped>
  .wrapper {
    text-align: center;
  }
  </style>