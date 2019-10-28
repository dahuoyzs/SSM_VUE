<template>
	
	<div>
		<div class="row" >
			
			<!-- 商品列表 -->
			<div class="col-lg-10">
				<table class="table" style="background-color:#fff">
					<thead class="thead-default">
						<tr>
							<!-- <th>序号</th> -->
							<th>商品名称</th>
							<th>商品编号</th>
							<th>价格</th>
							<th>单位</th>
							<th>品牌</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(goods,index) in resultgoods" >
							<!-- <td>{{ index+1 }}</td> -->
							<td>{{ goods.goodsname }}</td>
							<td><small><i>{{ goods.goodsid }}</i></small></td>
							<td>{{ goods.price }}</td>
							<th>{{ goods.unit }}</th>
							<th>{{ goods.brand }}</th>
							<td>
							<button @click="editer(index)"
							class="btn btn-sm btn-outline-success">修改</button>
							<button @click="dodelete(index)"
							class="btn btn-sm btn-outline-danger">删除</button>
								</td>
						</tr>
					</tbody>
				</table>
				<div class="center offset-md-2">
					<el-pagination
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
			<div class="col-lg-2">
				<!-- 分类 -->
				<div class="list-group">
				  <button 
				  	class="list-group-item list-group-item-action"
					@click="getGoodsAll()"
				  	>
				    全部
				    <span class="badge badge-pill badge-warning">{{ goodsALLcount }}</span>
				  </button>
				  
				  <button
				  	class="list-group-item list-group-item-action"
					v-for="(value,key) in goodsClassJO" 
					@click="getGoodsByClass(key)"
				  	>
					  {{ key }}<span class="badge badge-pill badge-warning">{{ value }}</span>
				  </button>

				</div>
				<br>
			</div>
			<!--  编辑弹窗   -->
			<el-dialog title="商品修改" :visible.sync="dialogFormVisible">
				<!--  form部分   -->
				<form>
					<div class="form-group row">
						 <div class="input-group col-sm-10 offset-sm-1">
							
							<select class="custom-select col-sm-5" id="inlineFormCustomSelect" v-model="selectclass" v-show="!isNewClass">
								<option v-for="(val,key,index) in goodsClassJO" >{{ key }}</option>
							</select>
							

							<input type="text" class="form-control col-sm-5 offset-sm-1" v-show="isNewClass" v-model:value="newclass" placeholder="新分类名称">
							<!--<input type="button" class="btn btn-sm btn-outline-success  offset-sm-3" @click="btnChange">{{ btnText  }}</input>
							 <button class="btn btn-sm btn-outline-success  offset-sm-3" @click="btnChange">{{ btnText  }}</button>-->
							<span class=" col-sm-5"></span>
							<el-button   @click="btnChange">{{ btnText }}</el-button>
						</div>
					</div>
					<div class="form-group row" v-for="(val,key,index) in form">
						<label for="aaa" class="col-sm-2 offset-sm-1 col-form-label">{{ key }}</label>
						<div class="col-sm-8">
						<input type="text" class="form-control" id="aaa"  v-model:value="form[key]"  v-bind:disabled="key=='商品编号'">
						</div>
					</div>
					
				</form>
				<!--   按钮部分   -->
				<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="doediter()">确 定</el-button>
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
				pagecount:1,//商品总数除以pagelimit可得到
				goodsALLcount:0,//商品总数查数据库得到
				nowclass:'全部',			//用于保存当前分类，  当下次换页的时候根据nowclass查询
				pagesizes:[5,10,15,20],

				resultgoods:[],	//返回的商品json对象
				goodsClassJO:{},//返回的商品分类json对象
				
				dialogFormVisible: false,
				isNewClass:false,
				selectclass:'全部',
				newclass:'',
				form: {				//点击修改按钮发送的数据
					商品名称:'',
					商品编号:'',
					商品价格:'',
					商品单位:'',
					商品品牌:''
				},
				btnText:'新建分类 ',
				editerIndex:0

			}
		},
		methods: {
			btnChange(){
				this.isNewClass=!this.isNewClass;
				this.btnText=this.btnText=='选择分类'?'新建分类':'选择分类';
				
			},
			doediter(){		//修改数据的监听
				console.log(this.isNewClass?this.newclass:this.selectclass);
				let {商品名称,商品编号,商品价格,商品单位,商品品牌}=this.form;
				let jsonparam={};
				if(this.form.商品名称==''||this.form.商品编号==''||this.form.商品价格==''||this.form.商品单位==''||this.form.商品品牌=='')
                {
                    this.$message({
                        message: '编辑框内容不能为空',
                        type: 'warning'
                    });
                }else{
                    if(this.isNewClass){
                        if(this.selectclass=='全部'||this.selectclass=='')
                        {
                            this.$message({
                                message: '请先选择分类',
                                type: 'warning'
                            });
                            return;
                        }
                    }else{
                        if(this.selectclass=='全部'||this.selectclass=='')
                        {
                            this.$message({
                                message: '请先选择分类',
                                type: 'warning'
                            });
                            return;
                        }
                    }
                    jsonparam['goodsclass']=this.isNewClass?this.newclass:this.selectclass;
					jsonparam['goodsname']=商品名称;
					jsonparam['goodsid']=商品编号;
					jsonparam['price']=parseFloat(商品价格);
					jsonparam['unit']=商品单位;
					jsonparam['brand']=商品品牌;
					console.log(jsonparam);
					this.$axios.post('http://localhost:8080/SSMWW/updateGoods.do',jsonparam)
					.then(res=> {
						let updateres=res.data;
						console.log(updateres);
						if(updateres.code=='200'&&updateres.msg=='更新成功'){
							this.$message({
								message: '恭喜你，更改成功',
								type: 'success'
							});
							
							if(this.nowclass==='全部')
								this.getGoodsAll();
							else
								this.getGoodsByClass(this.nowclass);
							this.innerGetGoodsClass();
						}else{
							this.$message({
								message: '不好意思，更改失败',
								type: 'warning'
							});
						}
						this.dialogFormVisible=false;
					});
					
                }
			},
			editer(index){		//修改数据的监听
				console.log(this.nowclass);
				this.selectclass=this.nowclass;
				this.dialogFormVisible=true;
				this.editerIndex=index;
				//this.form=this.resultgoods[index];
				this.form.商品名称=this.resultgoods[index].goodsname;
				this.form.商品编号=this.resultgoods[index].goodsid;
				this.form.商品价格=this.resultgoods[index].price;
				this.form.商品单位=this.resultgoods[index].unit;
				this.form.商品品牌=this.resultgoods[index].brand;
			},
			dodelete(index){		//删除数据的监听
				let jsonparam={};
				jsonparam['goodsid']=this.resultgoods[index].goodsid;
				this.$axios.post('http://localhost:8080/SSMWW/deleteGoodsById.do',jsonparam)
					.then(res=> {
						let addres=res.data;
						console.log(addres);
						if(addres.code=='200'&&addres.msg=='删除成功'){
							this.$message({
								message: '恭喜你，删除成功',
								type: 'success'
							});
							if(this.nowclass==='全部')
								this.getGoodsAll();
							else
								this.getGoodsByClass(this.nowclass);
							this.innerGetGoodsClass();
						}else{
							this.$message({
								message: '不好意思，删除失败',
								type: 'warning'
							});
						}
						
					});
			},
			
			handleSizeChange(val) {					//分页  每页显示条目
				console.log(`每页 ${val} 条`);
				this.pagelimit=val;
				if(this.nowclass==='全部')
					this.getGoodsAll();
				else
					this.getGoodsByClass(val);

			},
			handleCurrentChange(val) {				//分页  当前页监听事件
				console.log(`当前页: ${val}`);
				this.pagenow=val;
				if(this.nowclass==='全部')
					this.getGoodsAll();
				else
					this.getGoodsByClass(val);
			},
			getGoodsAll(){
				this.nowclass='全部';
				this.$axios.get('http://localhost:8080/SSMWW/goodsALLByPage.do?pageNow='
				+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					this.resultgoods=res.data;
					this.goodscount=this.goodsALLcount;
					this.pagecount=this.goodscount%this.pagelimit==0?this.pagecount=this.goodscount/this.pagelimit:this.pagecount=parseInt(this.goodscount/this.pagelimit)+1;
				});
			}
			,
			getGoodsByClass(gc){
				this.nowclass=gc;
				this.$axios.get('http://localhost:8080/SSMWW/goodsByClassAndPage.do?goodsclass='+gc+'&pageNow='
				+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					this.resultgoods=res.data;
					this.goodscount=this.resultgoods.length;
					this.pagecount=this.goodscount%this.pagelimit==0?this.pagecount=this.goodscount/this.pagelimit:this.pagecount=parseInt(this.goodscount/this.pagelimit)+1;

				});
			},
			innerGetGoodsClass(){
				this.$axios.get('http://localhost:8080/SSMWW/goodsClassALLMap.do')
				.then(res=> {
					this.goodsClassJO=res.data;
					this.goodsALLcount=0;
					let nums=Object.values(this.goodsClassJO);//获取到值
					for (var i = 0; i < nums.length; i++) {
						this.goodsALLcount+=nums[i];		//找出每个分类的个数叠加
					}
					this.pagecount=this.goodsALLcount%this.pagelimit==0?this.pagecount=this.goodsALLcount/this.pagelimit:this.pagecount=parseInt(this.goodsALLcount/this.pagelimit)+1;
					this.goodscount=this.goodsALLcount;
				});
			}
		},
		created(){
			this.innerGetGoodsClass();

			// 获取商品列表
			this.$axios.get('http://localhost:8080/SSMWW/goodsALLByPage.do?pageNow='
			+this.pagenow+'&pageLimit='+this.pagelimit)
				.then(res=> {
					this.resultgoods=res.data;
					console.log(res.data);
				});
		}
		
	}
	
</script>


<style>
	
</style>

<!--
    本页面公用了7个接口
-->





