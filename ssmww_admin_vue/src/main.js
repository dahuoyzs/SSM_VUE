import Vue from 'vue';              //引用vue
import App from './App';            //引用App模板
import router from './router/index.js';      //引用路由
import store from './store/store.js';      //引用路由
import axios from 'axios';          //引用网络请求组件
import ElementUI from 'element-ui'; //引用ElementUI
//import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
import '../static/css/theme-green/index.css';       // 浅绿色主题
//import '../static/css/theme-green/color-green.css';
import "babel-polyfill";


import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI, { size: 'small' });
Vue.prototype.$axios = axios;           //方便使用 直接给在Vue添加个属性  全局方便使用

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const role = localStorage.getItem('ms_username');  //可以当做是从全局的session中获取一个值
    // 在HTML5中，新加入了一个localStorage特性，这个特性主要是用来作为本地存储来使用的，
    // 解决了cookie存储空间不足的问题(cookie中每条cookie的存储空间为4k)，
    // localStorage中一般浏览器支持的是5M大小，这个在不同的浏览器中localStorage会有所不同

    if(!role && to.path !== '/login'){//判断是否登录
        next('/login');
    }else if(to.meta.permission){
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin' ? next() : next('/403');
    }else{
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if(navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor'){
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        }else{
            next();//满足了所有的条件 1.登录  2. 管理员权限 3.浏览器也兼容
        }
    }
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');