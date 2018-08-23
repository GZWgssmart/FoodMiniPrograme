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
      title: 'Login - 登录',
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
  {
    path: '/app',
    name: 'app',
    component: Main,
    children: [
      {
        path: 'app_page',
        name: 'app_page',
        meta: {
          icon: '_qq',
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
      icon: 'logo-buffer',
      title: '商铺管理'
    },
    component: Main,
    children: [
      {
        path: '/shop/store_page',
        name: '/shop/store_page',
        meta: {
          icon: 'md-trending-up',
          title: '店铺管理'
        },
        component: () => import('@/view/shop/store/store-page.vue')
      },
      {
        path: '/shop/product_page',
        name: '/shop/product_page',
        meta: {
          icon: 'md-grid',
          title: '商品管理'
        },
        component: () => import('@/view/shop/product/product-page.vue')
      }
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  }
]
