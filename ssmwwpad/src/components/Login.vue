<template>
	<div class="row mt-3">
		<div class="col-md-12 col-lg-12">
			<div class="card">
				<div class="card-body">
					<img class="img-fluid center-block mx-auto d-block" src="https://cn.vuejs.org/images/logo.png">
					<form @submit.prevent="onSubmit">
						<div class="form-group">
							<label  for="sn" >用户名</label>
							<input class="form-control" 
							:class="{ 'is-valid':Issuccess,'is-invalid':Isdanger}"
							@blur="checkSaleName"
							v-model="salename" 
							placeholder="请输入用户名" 
							type="text" id="sn">
							<div :class="[ classSelect ]" v-show="Isdanger||Issuccess">
					          {{ altermsg }}
					        </div>
							<label class="alert alert-warning" v-if="nIsEmpty" for="password">{{ nAlert }}</label>
						</div>

						<div class="form-group">
							<label for="ps">密码</label>
							<input class="form-control" 

							@blur="checkPassWord" 
							v-model="password" 
							placeholder="请输密码" 
							type="password" id="ps">
							<label class="alert alert-warning" v-if="psIsEmpty" for="password">{{ psAlert }}</label>
						</div>
						<button type="submit" class="btn btn-block btn-success">登录</button>
					</form> 
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	// import axios from 'axios'
	export default{
		data(){
			return {
				salename:'',
				password:'',
				nIsEmpty:false,
				psIsEmpty:false,
				nAlert:'账号不能为空哦',
				psAlert:'密码不能为空哦',
				Issuccess:false,
				Isdanger:false,
				classSelect:'',
				altermsg:''
			}
		},
		methods:{
			onSubmit(){
				if (this.Issuccess&&this.salename!=''&&this.password!='') {
					let params = new URLSearchParams();
					params.append('salename', this.salename);
					params.append('password', this.password);
					this.$axios.post('http://localhost:8080/SSMWW/login.do',params)
					.then(res=> {
						let resultjson=res.data;
						console.log(resultjson);
						if (resultjson.code==200) {

							localStorage.setItem('salename',this.salename);
							
							this.salename='';
							this.password='';
							this.nIsEmpty=false;
							this.psIsEmpty=false;
							this.Issuccess=false;
							this.Isdanger=false;
							
							this.$message({message: '登录成功！',type: 'success'});
							this.$router.push({path:'goodslist'});
							

						}else if (resultjson.code==303) {
							this.$message.error('登录失败，密码错误！');
							//this.$message({message: '登录失败，密码错误！',type: 'warning'});
							//window.alert('登录失败，密码错误！');
						}else if (resultjson.code==404) {
							this.$message.error('登录失败,编辑框内容不能为空！');
							//window.alert('登录失败,编辑框内容不能为空！');
						}else {
							this.$message.error('登录失败,网络异常！');
							//window.alert('登录失败,网络异常！');
						}
					})
				}else{
					this.$message({message: '表单数据不合法,请更正后重试！',type: 'warning'});
					//window.alert('表单数据不合法,请更正后重试！');
				}
			},
			checkSaleName(){
				this.nIsEmpty=this.salename===''?true:false;
				this.$axios.get('http://localhost:8080/SSMWW/checkname.do?salename='+this.salename)
				.then(res=> {
					let Isexist=res.data;
					console.log(Isexist);
					if(this.nIsEmpty){
						this.classSelect='invalid-feedback';
						this.Issuccess=false;
						this.Isdanger=false;
					}else {
						
						if(Isexist){
							this.Issuccess=true;
							this.Isdanger=false;
							this.classSelect='valid-feedback';
							this.altermsg="这个是本网站的老用户了哦，快输入密码登录吧";
						}else if(!Isexist){
							this.Issuccess=false;
							this.Isdanger=true;
							this.classSelect='invalid-feedback';
							this.altermsg="你输入的账号可能还不存在，赶快去注册吧";
						}
					}
				})
			},
			checkPassWord(){
				this.psIsEmpty=this.password===''?true:false;
			}
		},
		created(){
			let salename=localStorage.getItem('salename');
			if(salename==null||salename=="")console.log("还没有登录");else console.log("已经登录过了用户名："+salename);
		}

	}

</script>