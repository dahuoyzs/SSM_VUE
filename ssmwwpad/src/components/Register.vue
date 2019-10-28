<template>
	<div class="row mt-3">
		<div class="col-md-12 col-lg-12">
			<div class="card">
				<div class="card-body">
					<img class="img-fluid center-block mx-auto d-block" src="https://cn.vuejs.org/images/logo.png">
					<form @submit.prevent="onSubmit">

						<div class="form-group">
							<label for="sn" >用户名</label>
							<input class="form-control" 
							:class="{ 'is-valid':Issuccess,'is-invalid':Isdanger}"
							@blur="checkSaleName" 
							v-model="salename" 
							placeholder="请输入用户名" 
							type="text" id="sn">
							
							<div :class="[ classSelect ]" v-show="Isdanger||Issuccess" >
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

						<div class="form-group">
							<label for="nps">确认密码</label>
							<input class="form-control" 
							@blur="checkEnterPassWord" 
							v-model="enterpassword" 
							placeholder="请输密码" 
							type="password" id="nps">
							<label class="alert alert-warning" v-if="epsIsEmpty" for="password">{{ epsAlert }}</label>
						</div>

						<button type="submit" class="btn btn-block btn-success">注册</button>
					</form> 
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	export default{
		data(){
			return {
				salename:'',
				password:'',
				enterpassword:'',
				nIsEmpty:false,
				psIsEmpty:false,
				epsIsEmpty:false,
				nAlert:'账号不能为空哦',
				psAlert:'密码不能为空哦',
				epsAlert:'密码不能为空哦',
				Issuccess:false,
				Isdanger:false,
				classSelect:'',
				altermsg:''
			}
		},
		methods:{
			onSubmit(){
				let params = new URLSearchParams();
				params.append('salename', this.salename);
				params.append('password', this.password);
				if (this.password===this.enterpassword&&this.Issuccess&&this.password!=''&&this.enterpassword!='') {
					this.$axios.post('http://localhost:8080/SSMWW/saleRegister.do',params)
					.then(res=> {
						let resultjson=res.data;
						console.log(resultjson);
						if (resultjson.code==200) {
							this.salename='';
							this.password='';
							this.nIsEmpty=false;
							this.psIsEmpty=false;
							this.Issuccess=false;
							this.Isdanger=false;
							this.$message({message: '注册成功,赶快登陆吧！',type: 'success'});
							//window.alert('注册成功,赶快登陆吧！');
							this.$router.push({path:'/Login'});
						}else{
							//this.$message({message: '注册失败！',type: 'success'});
							this.$message.error('注册失败,网络异常！');
							//window.alert('注册失败,服务器错误,请重试！');
						}
					})
				}else if (this.password!=this.enterpassword) {
					this.$message({message: '两次密码不一样哦,请重新确认密码！',type: 'warning'});
					//window.alert('两次密码不一样哦,请重新确认密码');
				}else{
					this.$message({message: '表单数据不合法,请修正后再提交！',type: 'warning'});
					//window.alert('表单数据不合法,请修正后再提交');
				}
			},
			checkSaleName(){
				this.nIsEmpty=this.salename===''?true:false;
				this.$axios.get('http://localhost:8080/SSMWW/checkname.do?salename='+this.salename)
				.then(res=> {
					let Isexist=res.data;
					console.log(Isexist);
					if(this.nIsEmpty){
						console.log("走名字为空");
						this.classSelect='invalid-feedback';
						this.Issuccess=false;
						this.Isdanger=false;
					}else {
						
						if(Isexist){
							
							this.Issuccess=false;
							this.Isdanger=true;
							this.classSelect='invalid-feedback';
							console.log("名字不为空   账号已存在"+this.Issuccess,this.Isdanger);
							this.altermsg="这个是本网站的老用户了哦，快输入密码登录吧";
						}else if(!Isexist){
							
							this.Issuccess=true;
							this.Isdanger=false;
							this.classSelect='valid-feedback';
							console.log("名字不为空   账号不存在"+this.Issuccess,this.Isdanger);
							this.altermsg="你输入的账号可能还不存在，赶快去注册吧";
						}
					}
				})
			},
			checkPassWord(){
				this.psIsEmpty=this.password===''?true:false;
				
			},
			checkEnterPassWord(){
				if (this.enterpassword!=''&&this.enterpassword===this.password) {
					this.epsIsEmpty=false;
				}else{
					this.epsAlert=this.enterpassword!=this.password?'两次密码不一样哦':'密码不能为空哦';
					this.epsIsEmpty=true;
				}
			}
		}
	}

</script>