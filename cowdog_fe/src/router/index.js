import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Test from '../components/Test.vue'
import Admin from '../views/notice/components/Admin.vue'
import Notice from '../views/notice/components/notice.vue'
import UserReport from '../views/notice/components/user-report.vue'
import WritingReport from '../views/notice/components/writing-report.vue'
import AppealList from '../views/appeal/components/AppealList.vue'
import AppealDetail from '../views/appeal/components/AppealDetail.vue'
import AppealCreate from '../views/appeal/components/AppealCreate.vue'
import LoginPage from '../views/login/LoginPage.vue'
import RegisterPage from '../views/register/RegisterPage.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path:'/login',
    name:'Login',
    component:LoginPage
  },
  {
    path:'/register',
    name:'Register',
    component:RegisterPage
  },

  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    children: [
        {
          path: '/admin/notice',
          name: 'Notice',
          component: Notice
        },
        {
          path: '/admin/user-report',
          name: 'UserReport',
          component: UserReport
        },
        {
          path: '/admin/writing-report',
          name: 'WritingReport',
          component: WritingReport
        },
        {
          path: '/appeal',
          name: 'AppealList',
          component: AppealList, 
          // 중첩 라우팅..
          // children: [],
          // props를 true로 설정하면, $route.params가 컴포넌트 props로 설정된다.
          props: true,
        },
        {
          path: '/appeal/detail',
          name: 'AppealDetail',
          component: AppealDetail,
        },
        {
          path: '/appeal/create',
          name: 'AppealCreate',
          component: AppealCreate,
        },  
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
