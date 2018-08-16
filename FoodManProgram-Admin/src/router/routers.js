import Main from '@/view/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面不会缓存
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: '小程序管理后台 - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },
  // {
  //   path: '',
  //   name: 'doc',
  //   meta: {
  //     title: '文档',
  //     href: 'https://lison16.github.io/iview-admin-doc/#/',
  //     icon: 'ios-book'
  //   }
  // },
  {
    path: '/app',
    name: 'app',
    component: Main,
    children: [
      {
        path: 'app_page',
        name: 'app_page',
        meta: {
          icon: 'md-cube',
          title: '小程序'
        },
        component: () => import('@/view/app/app-page.vue')
      }
    ]
  },
  {
    path: '/shop',
    name: 'shop',
    meta: {
      icon: 'ios-star',
      title: '商铺管理'
    },
    component: Main,
    children: [
      {
        path: 'store_page',
        name: 'store_page',
        meta: {
          icon: 'md-trending-up',
          title: '商铺信息管理'
        },
        component: () => import('@/view/shop/store/store-page.vue')
      },
      {
        path: 'product_page',
        name: 'product_page',
        meta: {
          icon: 'ios-shirt-outline',
          title: '商品信息管理'
        },
        component: () => import('@/view/shop/product/product-page.vue')
      }
    ]
  }
]
