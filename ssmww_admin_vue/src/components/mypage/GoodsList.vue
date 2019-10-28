<template>
	<div>
		<!-- row12站一行  -->
		<div class="row">
			<div class="center offset-md-4">
				<form  class="input-group" @submit.prevent="btnsearch">
					<input type="text" class="form-control" placeholder="请输入要搜索的内容" v-model="searchText" ></input>
					<input type="submit" class="btn btn-outline-success" style="margin-left:1px"  value="搜索" ></input>
				</form >
			</div>
		</div>
		<div class="row" >
			<div class="col-lg-2">
				<!-- 分类 -->
				<div class="list-group">
				  <button 
				  	class="list-group-item list-group-item-action"
					@click="getGoodsAll()"
				  	>
				    全部
				    <span class="badge badge-pill badge-success">{{ goodsALLcount }}</span>
				  </button>
				  
				  <button
				  	class="list-group-item list-group-item-action"
					v-for="(value,key) in goodsClassJO" 
					@click="getGoodsByClass(key)"
				  	>
					  {{ key }}<span class="badge badge-pill badge-success">{{ value }}</span>
				  </button>

				</div>
				<br>
			</div>
			<!-- 商品列表 -->
			<div class="col-lg-6" style="background-color:#fff">
				<table class="table" >
					<thead class="thead-default">
						<tr>
							<!-- <th>序号</th> -->
							<th>商品名称</th>
							<th>商品编号</th>
							<th>价格</th>
							<th>加入</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(goods,index) in resultgoods">
							<!-- <td>{{ index+1 }}</td> -->
							<td>{{ goods.goodsname }}</td>
							<td><small><i>{{ goods.goodsid }}</i></small></td>
							<td>{{ goods.price }}</td>
							<td><button 
								@click="addToBasket(goods.goodsid,goods.goodsname,goods.price,index)"
								class="btn btn-sm btn-outline-success">+</button></td>
						</tr>
					</tbody>
				</table>
				
				<!-- 分页 -->
				<div class="center offset-md-2">
					<el-pagination
					small
					@size-change="handleSizeChange"
					@current-change="handleCurrentChange"
					:current-page="pagenow"
					:page-sizes="pagesizes"
					:page-size="pagelimit"
					layout="total, sizes, prev, pager, next, jumper"
					:total="goodscount">
					</el-pagination>
				</div>
				
			</div>
			<!-- 购物车 -->
			<div class="col-lg-4" >
				<div class="goodsbasket" v-if="selectgoods.length > 0" style="background-color:#fff">
					<table class="table">
					<thead class="thead-default">
						<tr>
							<th>数量</th>
							<th>商品名称</th>
							<th>价格</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="sgoods in selectgoods">
							<td>
								<button @click="reduce(sgoods)" class="btn btn-sm btn-light"
								>-</button>
								<span>{{ sgoods.number }}</span>
								<button @click="add(sgoods)" class="btn btn-sm btn-light"
								>+</button>
							</td>
							<td>{{ sgoods.goodsname }}</td>
							<td>{{ sgoods.price }}</td>
							<td>
								<button @click="removeSelectGoods(sgoods)" class="btn btn-sm btn-outline-danger"
								>x</button>
							</td>
						</tr>
					</tbody>
				</table>
				<p>总价:{{ countMoney }} RMB</p>
				<button class="btn btn-success btn-block" @click="editAddress">选择地址确认订单>></button>
				</div>
				<div class="basketText" v-else>
					购物车暂时没有商品
				</div>
			</div>

			<!--  编辑弹窗   -->
			<el-dialog title="选择地址" :visible.sync="dialogVisible">
				<!--  form部分   -->
				<form>
					<div class="form-group row">
						 <div class="input-group col-sm-12">
							<el-button   @click="btnChange">{{ btnText }}</el-button>
							<select class="custom-select col-sm-10" id="inlineFormCustomSelect" v-model="selectAddress" v-show="!isNewAddress">
								<option v-for="(item,index) in resultAddress" >

								{{ index+1 }} | {{ item.receivename }} | {{ item.receivephone }} | {{ item.address }}
								
								</option>
								
							</select>
							
						</div>
					</div>
					<div  v-for="(val,key,index) in form" v-show="isNewAddress">
						<div class="form-group row" v-if="key!='地址号'">
							<label for="aaa" class="col-sm-2 offset-sm-1 col-form-label" v-bind:disabled="key=='地址号'">{{ key }}</label>
							<div class="col-sm-8" >
							<input type="text" class="form-control" id="aaa"  v-model:value="form[key]"  >
							</div>
						</div>
					</div>
				</form>
				<!--   按钮部分   -->

				<div slot="footer" class="dialog-footer">
					<el-button @click="dialogVisible = false">取 消</el-button>
					<el-button type="primary" @click="enterOrder">确 定</el-button>
				</div>

			</el-dialog>


		</div>
	</div>
</template>
<script type="text/javascript">
	
	export default{
		name:'goodslist',
		data(){
			return {
				pagenow:1,//当前页码
				pagelimit:10,//限制一页只能显示
				goodscount:0,//商品总数查数据库得到
				goodsALLcount:0,//商品总数查数据库得到
				pagesizes:[5,10,15,20],
				pagelist:[],
				selectgoods:[],
				resultgoods:[],
				goodsClassJO:{},

				resultAddress:[],
				nowclass:'全部',
				searchText:'',
				postjsonparam:{},

				dialogVisible:false,
				btnText:'新建地址',
				editerIndex:0,
				isNewAddress:false,
				selectAddress:'',
				newAddress:'',
				form: {	
					地址号:'',
					接收人:'',
					接收电话:'',
					收货地址:''
				},



			}
		},
		computed:{
			countMoney(){
				let sum=0;
				for(let index in this.selectgoods){
					let obj=this.selectgoods[index];
					sum+=obj.number*obj.price;
				}
				sum=sum.toFixed(2)
				return sum;
			}
		},
		methods: {
			btnsearch(){
				console.log(this.searchText);
				if(!this.searchText=="")
				{
					this.$axios.get('http://localhost:8080/SSMWW/goodsSearch.do?searchText='+this.searchText)
					.then(res=> {
						console.log(res.data);//输出返回的结果
						this.resultgoods=res.data;
						this.goodscount=res.data.length;
					});
				}else{
					this.$message({
								message: '搜索内容不能为空',
								type: 'warning'
							});
				}
				
			},
			btnChange(){
				this.isNewAddress=!this.isNewAddress;
				this.btnText=this.btnText=='选择地址'?'新建地址':'选择地址';
			},
			enterOrder(){
				console.log("this.isNewAddress:"+this.isNewAddress);
				if(!this.isNewAddress){
					let arr=this.selectAddress.split('|');
					let addressid=this.resultAddress[arr[0]-1].addressid;
					this.postjsonparam['addressid']=addressid;
					this.submitOrder();
					this.dialogVisible=false;
				}else{
					let {接收人,接收电话,收货地址} = this.form;
					let jsonparam={};
					jsonparam['receivename']=接收人;
					jsonparam['receivephone']=接收电话;
					jsonparam['address']=收货地址;
					console.log(jsonparam);
					this.$axios.post('http://localhost:8080/SSMWW/addAddress.do',jsonparam)
					.then(res=> {
						let updateres=res.data;
						console.log(updateres);
						if(updateres.code=='200'&&updateres.msg=='添加成功'){
							
							this.$axios.get('http://localhost:8080/SSMWW/addressMaxId.do')
								.then(res=> {
									console.log(res.data);
									let addressmaxid=res.data;
									this.postjsonparam['addressid']=addressmaxid;
									this.submitOrder();
									this.dialogVisible=false;
							});
							this.$message({
								message: '恭喜你，添加成功',
								type: 'success'
							});
						}else{
							this.$message({
								message: '不好意思，添加失败',
								type: 'warning'
							});
						}
					});
				}
				
			},
			editAddress(){
				this.form.地址号='';
				this.form.接收人='';
				this.form.接收电话='';
				this.form.收货地址='';
				this.resultAddress=[];
				this.dialogVisible=true;
				this.$axios.get('http://localhost:8080/SSMWW/addressALL.do')
                    .then(res=> {
                        console.log(res.data);
                        this.resultAddress=res.data;
                });
			},
			submitOrder(){

				Date.prototype.format = function(format)
				{
					var o = {
					"M+" : this.getMonth()+1, //month
					"d+" : this.getDate(),    //day
					"h+" : this.getHours(),   //hour
					"m+" : this.getMinutes(), //minute
					"s+" : this.getSeconds(), //second
					"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
					"S" : this.getMilliseconds() //millisecond
					}
					if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
					(this.getFullYear()+"").substr(4 - RegExp.$1.length));
					for(var k in o)if(new RegExp("("+ k +")").test(format))
					format = format.replace(RegExp.$1,
					RegExp.$1.length==1 ? o[k] :
					("00"+ o[k]).substr((""+ o[k]).length));
					return format;
				}
				this.postjsonparam['confirmdate']=new Date().format('yyyy-MM-dd hh:mm:ss');
				this.postjsonparam['countprice']=this.countMoney;
				let salename=localStorage.getItem('ms_username')
				this.$axios.get('http://localhost:8080/SSMWW/findSaleidBySaleName.do?salename='+salename)
					.then(res=> {
						this.postjsonparam['saleid']=res.data;
						this.postjsonparam['orderdetails']=this.selectgoods;
						this.$axios.get('http://localhost:8080/SSMWW/getToDayMaxOrderCode.do?date='+new Date().format('yyyy-MM-dd'))
						.then(res=> {
							let todyres=res.data;
							console.log('------------------------------------------------------------');
							console.log(todyres);
							let ordercode;
							ordercode=todyres.code=='200'?new Date().format('yyyyMMdd')+(parseInt(todyres.maxordercode.substring(8))+1):ordercode=new Date().format('yyyyMMdd')+"1001";
							this.postjsonparam['ordercode']=ordercode;
							console.log(this.postjsonparam);
							this.$axios.post('http://localhost:8080/SSMWW/addGoodsOrder.do',this.postjsonparam)
								.then(res=> {
									let orderres=res.data;
									console.log(orderres);
									if(orderres.code=='200')
									{
										this.$message({
											message: '商品订单已提交成功',
											type: 'success'
										});
										this.selectgoods=[];
									}else{
										this.$message({
											message: '订单提交失败',
											type: 'warning'
										});
									}
								});
						});
					});
			},
			addToBasket:function(goodsid,goodsname,price,index){
				let tempgoods={goodsid,goodsname,price,index,number:1}
				if (this.selectgoods.length>0) {
					let afterFilterArr=this.selectgoods.filter(
						function(item){
							return (item.goodsname===goodsname&&item.price===price)
						}
					)
					if (afterFilterArr!=null&&afterFilterArr.length>0) {
						afterFilterArr[0].number++;
					}else{
						this.selectgoods.push(tempgoods);
					}
					
				}else{
					this.selectgoods.push(tempgoods);
				}
				this.selectgoods.push()
			},
			reduce:function(goods){
				goods.number--;
				if (goods.number<1) {
					this.removeSelectGoods(goods);
				}
			},
			add:function(goods){
				goods.number++;
			},
			handleSizeChange(val) {					//分页  每页显示条目
				console.log(`每页 ${val} 条`);
				this.pagelimit=val;
				console.log(this.nowclass+"----------------");
				if(this.nowclass==='全部')
					this.getGoodsAll();
				else
					this.getGoodsByClass(this.nowclass);

			},
			handleCurrentChange(val) {				//分页  当前页监听事件
				console.log(`当前页: ${val}`);
				this.pagenow=val;
				if(this.nowclass==='全部')
					this.getGoodsAll();
				else
					this.getGoodsByClass(this.nowclass);
			},
			removeSelectGoods(goods){
				this.selectgoods.splice(this.selectgoods.indexOf(goods),1);
			},
			getGoodsAll(){
				this.nowclass='全部';
				this.$axios.get('http://localhost:8080/SSMWW/goodsALLByPage.do?pageNow='
				+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					console.log(res.data);//输出返回的结果
					this.resultgoods=res.data;
					this.goodscount=this.goodsALLcount;
				});
			},
			getGoodsByClass(gc){
				this.nowclass=gc;
				console.log(gc);
				this.$axios.get('http://localhost:8080/SSMWW/goodsByClassAndPage.do?goodsclass='+gc+'&pageNow='
				+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					console.log(res.data);//输出返回的结果
					this.resultgoods=res.data;
					this.goodscount=this.resultgoods.length;
				});
			}
			
		},
		created(){
			this.$axios.get('http://localhost:8080/SSMWW/goodsCount.do?')
				.then(res=> {
					this.goodsALLcount=res.data;
					this.goodscount=this.goodsALLcount;
				});
			// 获取商品分类列表
			this.$axios.get('http://localhost:8080/SSMWW/goodsClassALLMap.do')
				.then(res=> {
					console.warn(res.data);
					this.goodsClassJO=res.data;
				});
			// 获取商品列表
			this.$axios.get('http://localhost:8080/SSMWW/goodsALLByPage.do?pageNow='
			+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					this.resultgoods=res.data;
					console.log(res.data);//输出返回的结果
				});
		}

	}
	

</script>

<!-- 
6个接口

http://p0.meituan.net/codeman/33ff80dc00f832d697f3e20fc030799560495.jpg
http://p1.meituan.net/codeman/bb0abb3435a60c44d87e90ed4237b61039329.jpg
http://p0.meituan.net/codeman/a97baf515235f4c5a2b1323a741e577185048.jpg
http://p1.meituan.net/codeman/826a5ed09dab49af658c34624d75491861404.jpg
http://p0.meituan.net/codeman/daa73310c9e57454dc97f0146640fd9f69772.jpg


 -->