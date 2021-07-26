import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Test from '../components/Test.vue'
import Admin from '../views/notice/components/Admin.vue'
import Notice from '../views/notice/components/notice.vue'
import UserReport from '../views/notice/components/user-report.vue'
import WritingReport from '../views/notice/components/writing-report.vue'
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
        }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
