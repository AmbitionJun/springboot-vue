import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import axios from 'axios';
import VueAxios from 'vue-axios'
import router from './router/index';
// import { get, post } from "service";



Vue.use(ElementUI);
Vue.prototype.$axios = axios
// axios.defaults.baseURL = '/api'  //关键代码
Vue.config.productionTip = false
// Vue.prototype.$get = get
// Vue.prototype.$post = post
Vue.use(VueAxios, axios)
new Vue({
  router,
  el: '#app',
  render: h => h(App)
});
