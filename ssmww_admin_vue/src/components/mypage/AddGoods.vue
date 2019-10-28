
<template>
	<div>
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
            <div class="form-group row" v-for="(val,key,index) in form" >
                <label for="aaa" class="col-sm-2 offset-sm-1 col-form-label">{{ key }}</label>
                <div class="col-sm-8">
                <input type="text" class="form-control" id="aaa"  v-model:value="form[key]"  v-bind:disabled="key=='商品编号'">
                </div>
            </div>
        </form>
        <div class="center offset-sm-5">
            <el-button @click="btnreset">重置</el-button>
            <el-button type="primary" @click="doAddGoods">添加</el-button>
        </div>
    </div>
</template>

<script>
  export default{
      name:"addgoods",
		data(){
			return {
				resultgoods:[],	//返回的商品json对象
				goodsClassJO:{},//返回的商品分类json对象
				
				isNewClass:false,
				selectclass:'',
				newclass:'',
				form: {				//点击修改按钮发送的数据
					商品名称:'',
					商品编号:'',
					商品价格:'',
					商品单位:'',
					商品品牌:''
				},
				btnText:'新建分类 '
			}
		},
        methods: {
			btnChange(){
				this.isNewClass=!this.isNewClass;
				this.btnText=this.btnText=='选择分类'?'新建分类':'选择分类';
			},
            btnreset(){
                this.form.商品名称='';
                this.form.商品价格='';
                this.form.商品单位='';
                this.form.商品品牌='';
            },
            doAddGoods(){
                console.log(this.isNewClass?this.newclass:this.selectclass);
                let {商品名称,商品编号,商品价格,商品单位,商品品牌}=this.form;
                let jsonparam={};
                if(this.form.商品名称==''||this.form.商品编号==''||this.form.商品价格==''||this.form.商品单位=='')
                {
                    this.$message({
                        message: '编辑框内容不能为空',
                        type: 'warning'
                    });
                }else{
                    if(this.isNewClass){
                        if(this.newclass=='全部'||this.newclass=='')
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

                    this.$axios.post('http://localhost:8080/SSMWW/addGoods.do',jsonparam)
					.then(res=> {
						let addres=res.data;
						console.log(addres);
						if(addres.code=='200'&&addres.msg=='添加成功'){
							this.$message({
								message: '恭喜你，添加成功',
								type: 'success'
							});
							this.btnreset();
                            this.getMaxid();
						}else{
							this.$message({
								message: '不好意思，添加失败',
								type: 'warning'
							});
						}
						
					});

                }
               
            },
            getMaxid(){
                this.$axios.get('http://localhost:8080/SSMWW/goodsMaxId.do')
                .then(res=> {
                    let result = res.data;
                    this.form.商品编号=result+1;
                });
            }
        },
        created(){
			// 获取商品分类列表
			this.$axios.get('http://localhost:8080/SSMWW/goodsClassALLMap.do')
				.then(res=> {
					this.goodsClassJO=res.data;
					 console.log(res.data);//输出返回的结果
					this.goodsALLcount=0;
					let nums=Object.values(this.goodsClassJO);//获取到值
					for (var i = 0; i < nums.length; i++) {
						this.goodsALLcount+=nums[i];		//找出每个分类的个数叠加
					}
					this.pagecount=this.goodsALLcount%this.pagelimit==0?this.pagecount=this.goodsALLcount/this.pagelimit:this.pagecount=parseInt(this.goodsALLcount/this.pagelimit)+1;
					this.goodscount=this.goodsALLcount;
				});
            this.getMaxid();
			
		}
  }
</script>

<!--

    本页面公用了4个接口




-->










