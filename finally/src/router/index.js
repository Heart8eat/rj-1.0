import Vue from 'vue'
import VueRouter from 'vue-router'
import zhuye from '../views1/zhuye.vue'
import goods from '../views1/goods.vue'
import books from '../views1/books.vue'
import loginMain from '../views1/loginMain.vue'
import regist from '../views1/regist.vue'
import salerlogin from '../views/salerlogin.vue'
import salerindex from '../views/salerindex.vue'
import listgoods from '../views/listgoods.vue'
import myorders from '../views/myorders.vue'
import historicalgoods from '../views/historicalgoods.vue'
import creategoods from '../views/creategoods.vue'
import pricemanage from '../views/pricemanage.vue'
import Cookie from 'js-cookie';

Vue.use(VueRouter)

const routes = [
    { path: '/zhuye',name: 'zhuye', component: zhuye },
    {
      path: '/goods/:productId',
      name: 'goods',
      component: goods
    },
    {
      path: '/books/:productId',
      name: 'books',
      component: books
    },
    { path: '/loginMain',name: 'loginMain', component: loginMain },
    { path: '/regist',name: 'regist', component: regist },
    { path: '/salerlogin', name: 'salerlogin', component: salerlogin },
    { path: '/salerindex', name: 'salerindex', component: salerindex },
    { path: '/listgoods', name: 'listgoods', component: listgoods },
    { path: '/myorders', name: 'myorders', component: myorders },
    { path: '/historicalgoods', name: 'historicalgoods', component: historicalgoods },
    { path: '/creategoods', name: 'creategoods', component: creategoods },
    { path: '/pricemanage', name: 'pricemanage', component: pricemanage },
]

const router = new VueRouter({
    mode: 'history',
    routes
})

router.beforeEach((to, from, next) => {
  const token = Cookie.get('token')
  const routesRequiringToken = ['salerindex', 'listgoods', 'myorders', 'historicalgoods'];

  if (routesRequiringToken.includes(to.name) && token === null) {
    // 如果访问需要验证 token 的路由且没有令牌，重定向到登录页面
    next({ name: 'salerlogin' });
  } else {
    next();
  }
})

export default router

