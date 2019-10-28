<template>
	
	<div>
		<center>
			<button class="btn btn-sm btn-success" style="margin-bottom:5px" @click="newAddress">新建地址</button>
		</center> 
		<el-dialog title="地址添加" :visible.sync="dialogNewAddress">
				<!--  form部分   -->
				<form>
					<div  v-for="(val,key,index) in form">
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
					<el-button @click=" dialogNewAddress = false">取 消</el-button>
					<el-button type="primary" @click="doNewAddress()">确 定</el-button>
				</div>
			</el-dialog>
		<div class="row" >
			<el-table
				:data="resultaddress"
				max-height="700"
				border
				:row-class-name="tableRowClassName"
				style="width:100%;text-align:center;font-size:15px">
				<el-table-column
				label="序号"
				width="80"
				align="center">
					<template slot-scope="scope">
						{{ scope.row.index +1 }}
					</template>
				</el-table-column>

				<el-table-column
				prop="receivename"
				label="收货人"
				width="200"
				align="center">
				</el-table-column>

				<el-table-column
				prop="receivephone"
				label="收货人电话"
				width="200"
				align="center">
				</el-table-column>

				<el-table-column
				prop="address"
				label="详细地址"
				align="center">
				</el-table-column>

				<el-table-column
				label="编辑"
				width="180"
				align="center">
				<template slot-scope="scope">
					<button @click="editer(scope.row.index)"
					class="btn btn-sm btn-outline-success">
					修改
					</button>

					<button @click="deleteAddress(scope.row.index)"
					class="btn btn-sm btn-outline-danger">
					删除
					</button>

				</template>
				</el-table-column>
			</el-table>
			<!-- 饿了么表格  -->
			<!--  编辑弹窗   -->
			<el-dialog title="地址修改" :visible.sync="dialogFormVisible">
				<!--  form部分   -->
				<form>
					<div  v-for="(val,key,index) in form">
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
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="doediter()">确 定</el-button>
				</div>

			</el-dialog>
			
		</div>
			
	</div>
</template>
<script type="text/javascript">
	export default{
		name:'addresslist',
		data(){
			return {
				pagenow:1,//当前页码
				pagelimit:10,//限制一页只能显示
				itemcount:0,//当前页有多上条
				pagecount:1,//总数除以pagelimit可得到
				itemALLcount:0,//该表共有多少个条目
				pagesizes:[5,10,15,20],
				resultaddress:[],	
				dialogNewAddress: false,
				dialogFormVisible: false,
				form: {	
					地址号:'',
					接收人:'',
					接收电话:'',
					收货地址:''
				},
				editerIndex:0
			}
		},
		methods: {
			tableRowClassName({row,rowIndex}) {
				//把每一行的索引放进row
				row.index = rowIndex;
			},

			doNewAddress(){
				let {地址号,接收人,接收电话,收货地址}=this.form;
				let jsonparam={};
				if(this.form.接收人==''||this.form.接收电话==''||this.form.收货地址=='')
                {
                    this.$message({
                        message: '编辑框内容不能为空',
                        type: 'warning'
                    });
                }else{
                    jsonparam['addressid']=地址号;
					jsonparam['receivename']=接收人;
					jsonparam['receivephone']=接收电话;
					jsonparam['address']=收货地址;
					console.log(jsonparam);
					this.$axios.post('http://localhost:8080/SSMWW/addAddress.do',jsonparam)
					.then(res=> {
						let updateres=res.data;
						console.log(updateres);
						if(updateres.code=='200'&&updateres.msg=='添加成功'){
							this.$message({message: '恭喜你，添加成功',type: 'success'});
							this.getAddressALL();
						}else{
							this.$message({message: '不好意思，添加失败',type: 'warning'});
						}
						this.dialogNewAddress=false;
					});
                }
			},
			newAddress(){
				this.dialogNewAddress=true;
				this.form.地址号='';
				this.form.接收人='';
				this.form.接收电话='';
				this.form.收货地址='';
			},
			doediter(){		//修改数据的监听
				let {地址号,接收人,接收电话,收货地址}=this.form;
				let jsonparam={};
				if(this.form.接收人==''||this.form.接收电话==''||this.form.收货地址=='')
                {
                    this.$message({message: '编辑框内容不能为空',type: 'warning'});
                }else{
                    jsonparam['addressid']=地址号;
					jsonparam['receivename']=接收人;
					jsonparam['receivephone']=接收电话;
					jsonparam['address']=收货地址;
					console.log(jsonparam);
					this.$axios.post('http://localhost:8080/SSMWW/updateAddress.do',jsonparam)
					.then(res=> {
						let updateres=res.data;
						console.log(updateres);
						if(updateres.code=='200'&&updateres.msg=='更新成功'){
							this.$message({message: '恭喜你，更改成功',type: 'success'});
							this.getAddressALL();
						}else{
							this.$message({message: '不好意思，更改失败',type: 'warning'});
						}
						this.dialogFormVisible=false;
					});
                }
			},
			editer(index){		//修改数据的监听
				this.dialogFormVisible=true;
				this.editerIndex=index;
				this.form.地址号=this.resultaddress[index].addressid;
				this.form.接收人=this.resultaddress[index].receivename;
				this.form.接收电话=this.resultaddress[index].receivephone;
				this.form.收货地址=this.resultaddress[index].address;
			},
			deleteAddress(index){
				this.$confirm('删除此地址,该地址所产生的所有订单将无法显示地址信息,是否继续?', '警告', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning',
					center: true

					}).then(() => {
						this.dodelete(index);
						//this.$message({type: 'success',message: '删除成功!'});
					}).catch(() => {
						this.$message({type: 'info',message: '已取消删除'});          
					});
				
			},
			dodelete(index){		//删除数据的监听
				console.log(this.resultaddress[index].addressid);
				this.$axios.get('http://localhost:8080/SSMWW/deleteAddressById.do?addressid='+this.resultaddress[index].addressid)
					.then(res=> {
						let addres=res.data;
						console.log(addres);
						if(addres.code=='200'&&addres.msg=='删除成功'){
							this.$message({message: '恭喜你，删除成功',type: 'success'});
							this.getAddressALL();
						}else{
							this.$message({message: '不好意思，删除失败',type: 'warning'});
						}
					});
			},
			handleSizeChange(val) {					//分页  每页显示条目
				console.log(`每页 ${val} 条`);
				this.pagelimit=val;
				this.getAddressALLByPage();
				this.getItemALLCount();

			},
			handleCurrentChange(val) {				//分页  当前页监听事件
				console.log(`当前页: ${val}`);
				this.pagenow=val;
				this.getAddressALLByPage();
				this.getItemALLCount();
			},
            getAddressALLByPage(){
                this.$axios.get('http://localhost:8080/SSMWW/addressALLByPage.do?pageNow='
                +this.pagenow+'&pageLimit='+this.pagelimit)
                    .then(res=> {
                        console.log(res.data);//返回的地址数组
                        this.resultaddress=res.data;
						
                        this.itemcount=res.data.length;
                });
            },
            getItemALLCount(){
                this.$axios.get('http://localhost:8080/SSMWW/addressCount.do')
                    .then(res=> {
                        //console.log("地址总个数"+res.data);
                        this.itemALLcount=res.data;
                    });
            },
			getAddressALL(){
				this.$axios.get('http://localhost:8080/SSMWW/addressALL.do')
                    .then(res=> {
						console.log(res.data);//返回的地址数组

                        this.resultaddress=res.data;
                        this.itemcount=res.data.length;
                    });
			}
		},
		created(){
			this.getAddressALL()
			// 获取地址列表
            // this.getItemALLCount();
            // this.getAddressALLByPage();
		}
		
	}
	
</script>


<style>
	
</style>







