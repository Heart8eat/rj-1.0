import Vue from 'vue'
import VueRouter from 'vue-router'
import salerlogin from '../views/salerlogin.vue'
import salerindex from '../views/salerindex.vue'
import listgoods from '../views/listgoods.vue'
import myorders from '../views/myorders.vue'
import historicalgoods from '../views/historicalgoods.vue'
import Cookie from 'js-cookie';
Vue.use(VueRouter)
// 1. 定义 (路由) 组件。
// 可以从其他文件 import 进来
//const Foo = { template: '<div>foo</div>' }
//const Bar = { template: '<div>bar</div>' }

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
  { path: '/salerlogin', name: 'salerlogin', component: salerlogin },
  { path: '/salerindex', name: 'salerindex',component: salerindex },
  { path: '/listgoods', name: 'listgoods',component: listgoods },
  { path: '/myorders', name: 'myorders',component: myorders },
  { path: '/historicalgoods', name: 'historicalgoods',component: historicalgoods },
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
  mode:'history',
  routes // (缩写) 相当于 routes: routes
})

router.beforeEach((to, from, next) => {
  //判断token存不存在
  const token = Cookie.get('token')
  //console.log(token)
  //token不存在
  if (!token && to.name !== 'salerlogin') {
    next({ name: 'salerlogin' })
  } else if (token && to.name === 'salerlogin') {
    next({ name: 'salerindex'})
  }else{
    next()
  }
})


// 4.暴露实例
export default router



