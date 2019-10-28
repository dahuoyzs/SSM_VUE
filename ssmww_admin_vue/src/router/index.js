import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    //mode: 'history',//去掉路径中的#号
    routes: [
        {//这个对象里面没有component所以没啥用，但是有一个redirect重定向的功能
            path: '/',
            redirect: '/dashboard'
        },
        {//根目录
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            //上方代码可以写成这样
            /*
                import Home from '****'
                component:Home,//这是普通方式， 而上边的这种形式是  懒加载方式

                懒加载
                const routes = [
                    { path: '/index', component: resolve => require(['./index.vue'], resolve) },
                    { path: '/hello', component: resolve => require(['./hello.vue'], resolve) },
                ]
            */
            meta: { title: '自述文件' },//这个属性还不知什么意思，
            children:[//二级路由
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/goodslist',
                    component: resolve => require(['../components/mypage/GoodsList.vue'], resolve),
                    meta: { title: '商品列表' }
                },
                {
                    path: '/goodsmanger',
                    component: resolve => require(['../components/mypage/GoodsManger.vue'], resolve),
                    meta: { title: '商品管理' }
                },
                {
                    path: '/addgoods',
                    component: resolve => require(['../components/mypage/AddGoods.vue'], resolve),
                    meta: { title: '添加商品' }
                },
                {
                    // 订单列表
                    path: '/orderlist',
                    component: resolve => require(['../components/mypage/OrderList.vue'], resolve),
                    meta: { title: '所有订单' }
                },
                {
                    // 订单列表
                    path: '/addresslist',
                    component: resolve => require(['../components/mypage/AddressList.vue'], resolve),
                    meta: { title: '常用地址' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: 'markdown编辑器' }    
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: { title: '文件上传' }   
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                }
            ]
        },
        {
            path: '/print',
            name:'print',
            component: resolve => require(['../components/mypage/Print.vue'], resolve)
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '*',              //匹配所有 没有找到的网页直接进入404界面
            redirect: '/404'
        }
    ]
})
