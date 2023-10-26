import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import Cookie from 'js-cookie';
Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
//添加全局前置导航守卫
// router.beforeEach((to, from, next) => {
//   //判断token存不存在
//   const token = Cookie.get('token')
//   //token不存在
//   // if (!token && to.name !== 'salerlogin') {
//   //   next({ name: 'salerlogin' })
//   // } else if (token && to.name === 'salerlogin') {
//   //   next({ name: 'salerindex'})
//   // }else{
//   //   next()
//   // }
//   console.log(token)
//   if(token==null){
//     next({ name: 'salerlogin' })
//   } else{
//     next()
//   }
// })

