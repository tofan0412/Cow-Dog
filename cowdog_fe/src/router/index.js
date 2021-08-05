import { createRouter, createWebHistory } from 'vue-router'
import Test from '../components/Test.vue'
import Admin from '../views/notice/Admin.vue'
import Notice from '../views/notice/components/notice.vue'
import NoticeCreate from '../views/notice/components/notice-create.vue'
import NoticeUpdate from '../views/notice/components/notice-update.vue'
import UserReport from '../views/notice/components/user-report.vue'
import ArticleReport from '../views/notice/components/article-report.vue'
import LoginPage from '../views/login/LoginPage.vue'
import RegisterPage from '../views/register/RegisterPage.vue'
import Mypage from '../views/mypage/Mypage.vue'
import MyInfo from '../views/mypage/components/MyInfo.vue'
import Eachother from '../views/mypage/components/Eachother.vue'
import Follow from '../views/mypage/components/Follow.vue'
import Following from '../views/mypage/components/Following.vue'
import Setting from '../views/mypage/components/Setting.vue'
import Update from '../views/mypage/components/Update.vue'
import FindingPassword from '../views/findpassword/FindingPassword.vue'
import AppealCreate from '../views/appeal/components/AppealCreate'
import AppealDetail from '../views/appeal/components/AppealDetail'
import AppealList from '../views/appeal/components/AppealList'
import AppealUpdate from '../views/appeal/components/AppealUpdate'

const routes = [
  
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
    path:'/findingpassword',
    name:'FindingPassword',
    component:FindingPassword
  },
  {
    path:'/mypage',
    name: 'Mypage',
    component:Mypage,
    children:[
      {
        path:'/mypage/myinfo',
        name:'MyInfo',
        component:MyInfo
      },
      {
        path:'/mypage/eachother',
        name:'Eachother',
        component:Eachother
      },
      {
        path:'/mypage/follow',
        name:'Follow',
        component:Follow
      },
      {
        path:'/mypage/following',
        name:'Following',
        component:Following
      },
      {
        path:'/mypage/setting',
        name:'Setting',
        component:Setting
      },
      {
        path:'/mypage/update',
        name:'update',
        component:Update
      },

    ]
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
          path: '/admin/noticeCreate',
          name: 'NoticeCreate',
          component: NoticeCreate
        },
        {
          path: '/admin/noticeDetailUpdate',
          name: 'UpdateNotice',
          component: NoticeUpdate,
        },
        {
          path: '/admin/user-report',
          name: 'UserReport',
          component: UserReport
        },
        {
          path: '/admin/article-report',
          name: 'ArticleReport',
          component: ArticleReport
        },
        {
          path: '/appeal/',
          name: 'AppealList',
          component: AppealList
        },
        {
          path: '/appeal/',
          name: 'AppealList',
          component: AppealCreate
        },
        {
          path: '/appeal/',
          name: 'AppealList',
          component: AppealDetail
        },
        {
          path: '/appeal/',
          name: 'AppealList',
          component: AppealUpdate
        },
    ]
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
