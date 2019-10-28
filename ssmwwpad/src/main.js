import Vue from 'vue'
import Router from "vue-router"
import App from './App.vue'
import routes from './routes.js'
import axios from 'axios'
import ElementUI from 'element-ui' //引用ElementUI


Vue.use(ElementUI, { size: 'small' })
Vue.use(Router)

Vue.prototype.$axios=axios;
const router=new Router({
	routes,
	//mode:'history'
})
// ES6写法   路由前置函数
router.beforeEach((to,from,next) =>{
  let salename=localStorage.getItem('salename');
  //console.log("路由守卫查看到的salename:"+salename);
  if(salename==null||salename=="")
  {
    if (to.path=='/Login'||to.path=='/Register') {
      next();
    }else{
      //this.$message({message: '不好意思，请先登录',type: 'success'});
     
      alert('请先登录');
    }
  }else{
    next();
  }

})

// ES5写法
// router.beforeEach(function(to,from,next){
// 	console.log(to);
// })
// ES5写法   路由后置函数
// router.afterEach(function(to,from){
// 	console.log(to);
// })
new Vue({
  el: '#app',
  router,
  render: h => h(App)
  //上面的这句的另一种写法
  // render: function (createElement) {
  //   return createElement(
  //     'h' + this.level,   // tag name 标签名称
  //     this.$slots.default // 子组件中的阵列
  //   )
  // }
})
















