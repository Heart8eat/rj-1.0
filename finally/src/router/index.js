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
import listbooks from '../views1/listbooks.vue'
import usercenter from '../views1/usercenter.vue'
import Cookie from 'js-cookie';
import goodsmanage from '../views/goodsmanage.vue'
import test from '../views1/test.vue'
Vue.use(VueRouter)

const routes = [
  { path: '/zhuye', name: 'zhuye', component: zhuye },
  { path: '/test', name: 'test', component: test },
  { path: '/listbooks', name: 'listbooks', component: listbooks },
  { path: '/usercenter', name: 'usercenter', component: usercenter },
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
  { path: '/loginMain', name: 'loginMain', component: loginMain },
  { path: '/regist', name: 'regist', component: regist },
  { path: '/salerlogin', name: 'salerlogin', component: salerlogin },
  { path: '/salerindex', name: 'salerindex', component: salerindex },
  { path: '/listgoods', name: 'listgoods', component: listgoods },
  { path: '/myorders', name: 'myorders', component: myorders },
  { path: '/historicalgoods', name: 'historicalgoods', component: historicalgoods },
  { path: '/creategoods', name: 'creategoods', component: creategoods },
  { path: '/pricemanage', name: 'pricemanage', component: pricemanage },
  { path: '/goodsmanage', name: 'goodsmanage', component: goodsmanage },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

router.beforeEach((to, from, next) => {
  const token = Cookie.get('token')
  if (!token && to.name === 'regist') {
    next()
  } else if (token && to.name === 'loginMain') {
    next({ name: 'zhuye' })
  } else if (!token && to.name !== 'loginMain') {
    next({ name: 'loginMain' })
  } else {
    next()
  }
})

export default router

