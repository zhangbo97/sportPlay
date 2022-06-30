import Vue from 'vue'
import VueRouter from 'vue-router'
//引入login组件
import Login from '../components/Login.vue'

import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import UserList from '../components/admin/UserList.vue'

//import { component } from 'vue/types/umd'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    redirect:"/login"
  },
  {
    path:"/login",
    component:Login
  },  
  {
    path:"/home",
    component:Home,
    redirect:"Welcome",
    children:[  //重定向的是子组件
      {path:"/Welcome",component:Welcome,},
      {path:"/user",component:UserList,},
      
    ]
  }
]

const router = new VueRouter({
  routes
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == '/login') return next();// 访问路径为登录
  // 获取flag
  const userFlag = window.sessionStorage.getItem("user");// session取值
  if (!userFlag) return next('/login');// 没登录去登录
  next();
})

export default router// 暴露出去

