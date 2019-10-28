
import Home from './components/Home'
import GoodsList from './components/GoodsList'
import GoodsOrders from './components/GoodsOrders'
import AddGoods from './components/AddGoods'
import About from './components/about/About'
import Login from './components/Login'
import Register from './components/Register'
import Print from './components/Print'
// 二级路由
import AboutOne from './components/about/AboutOne'
import AboutTwo from './components/about/AboutTwo'
import AboutThree from './components/about/AboutThree'
import AboutFour from './components/about/AboutFour'


const routes=[
	{path:'/',name:"homelink",component:Home},
	{path:'/goodslist',name:"goodslistlink",component:GoodsList},
	{path:'/goodsorders',name:"goodsorderslink",component:GoodsOrders},
	{path:'/addgoods',name:"addgoodslink",component:AddGoods},
	{path:'/about',name:"aboutlink",component:About,
		children:[
			{path:'/aboutone',name:"aboutone",component:AboutOne},
			{path:'/abouttwo',name:"abouttwo",component:AboutTwo},
			{path:'/aboutthree',name:"aboutthree",component:AboutThree},
			{path:'/aboutfour',name:"aboutfour",component:AboutFour}
		]
		//可以实现单个的路由守卫    这个是单个的enter事件函数
		// ,beforeEnter:function(to,from,next){
		// 	// console.log(to);
		// }
		//下面这个写法是不存在的，因为人家不支持
		// ,afterEnter:function(to,from){
		// 	console.log(to);
		// }
	},
	{path:'/login',name:"loginlink",component:Login},
	{path:'/register',name:"registerlink",component:Register},
	{path:'/print',name:"print",component:Print},
	{path:'*',redirect:'homelink'}
];


export default routes