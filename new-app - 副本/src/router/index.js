import Vue from 'vue'
import VueRouter from 'vue-router'
import zhuye from '../views/zhuye.vue'
import goods from '../views/goods.vue'
import books from '../views/books.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/zhuye', component: zhuye },
    { path: '/goods', component: goods },
    { path: '/books', component: books }

]
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})

export default router
