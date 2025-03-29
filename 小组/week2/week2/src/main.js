import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import LoginComponent from './components/Login.vue'; // 修改导入名称
import RegisterComponent from './components/Register.vue'; // 修改导入名称
import LoginRegisterWrapper from './components/LoginRegisterWrapper.vue'
Vue.component('LoginComponent', LoginComponent)
Vue.component('RegisterComponent', RegisterComponent)
Vue.component('LoginRegisterWrapper', LoginRegisterWrapper)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
