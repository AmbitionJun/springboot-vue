import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "../after-server/Layout/Layout.vue"
Vue.use(VueRouter);

const login = () => import("../after-server/Login.vue")
const routes = [

    {
        path: '/admin/login',
        name: 'Login',
        component:login
    },

    //公共布局下的路由
    {
        path: '/admin/Layout',
        name : 'Layout',
        component: Layout,
        children: [
            {
                path: '/admin/Layout/filmlist',
                name: 'filmlist',
                component: () => import("@/after-server/film/List")
            },
            {
                path: '/admin/Layout/filmarrange',
                name: 'filmarrange',
                component: () => import("@/after-server/film/Arrange")
            },
            {
                path: '/admin/Layout/filmadd',
                name: 'filmadd',
                component: () => import("@/after-server/film/Add")
            },
            {
                path: '/admin/Layout/filmposter',
                name: 'filmposter',
                component: () => import("@/after-server/film/Poster")
            },
            {
                path: '/admin/Layout/Active',
                name: 'Active',
                component: () => import("@/after-server/film/Active")
              },
            {
                path: '/admin/Layout/userlist',
                name: 'userlist',
                component: () => import("@/after-server/user/List")
            },
            {
              path: '/admin/Layout/adminlist',
              name: 'adminlist',
              component: () => import("@/after-server/adminList")
          },

            {
                path: '/admin/Layout/orderlist',
                name: 'orderlist',
                component: () => import("@/after-server/order/List")
            },
           
            {
                path: '/403',
                component: () => import("@/after-server/error/403")
            },

            {
                path: '/404',
                component: () => import("@/after-server/error/404")
            },
            {
                path: "*",
                redirect: "/admin/Layout/filmlist"
            },
        ]
    },
    {
      path: '/userlogin',
          name: 'userlogin',
          component: () => import("@/before-app/user/Login.vue"),
    },
    {
        path: '/userRegister',
            name: 'userRegister',
            component: () => import("@/before-app/user/Register.vue"),
      },
    
    {
      path: '/index',
      name: 'index',
      component: () => import("@/before-app/Layout/Layout.vue"),
      children: [
        {
            path: '/home',
            component: () => import("@/before-app/Home")
        },
        {
            path: '/film',
            component: () => import("@/before-app/film/Index"),
        },
        {
            path: '/films',
            component: () => import("@/before-app/film/Films")
        },
        {
            path: '/active',
            component: () => import("@/before-app/Active")
        },
        
        //购票
        {
            path: '/seat',
            component: () => import("@/before-app/user/Seat")
        },
        {
            path: '/order',
            component: () => import("@/before-app/user/Order.vue")
        },
    
    ]
  },
    {
        path: "*",
        redirect: '/home'
    }


];

const router = new VueRouter({
    mode: 'history',
    routes: routes
});


router.beforeEach((to, from, next) => {
    let isAuthenticated = sessionStorage.getItem("token") !== null
    // if (to.name !== 'Login' && !isAuthenticated) next({name: 'Login'})
    // else next()
    if (to.name == 'filmlist' ||to.name == 'Active' || to.name == 'filmarrange' || to.name == 'filmadd' || to.name == 'filmposter' || to.name == 'userlist' || to.name == 'adminlist' || to.name == 'orderlist') {
        if (!isAuthenticated) {
          next({ name: 'Login' })
        } else {
          next()
        }
      }
      else next()
})

export default router


