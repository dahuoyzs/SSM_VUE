<template>
    <div class="login-wrap">

        <div class="ms-title">后台管理系统</div>

        <div class="ms-login">     <!--   中间白框 登录主要界面  -->

            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">

                <el-form-item prop="username">   <!--   用户名编辑框  -->
                    <el-input v-model="ruleForm.username" placeholder="username"></el-input>
                </el-form-item>

                <el-form-item prop="password">   <!--   密码编辑框  -->
                    <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                
                <!--   登录按钮  -->
                <div class="login-btn">         
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <br>

                <!--   注册按钮  -->
                <!-- <div class="register-btn">      
                    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                </div> -->

                <p style="font-size:12px;line-height:30px;color:#999;">Tips : 默认账号密码均为:admin。</p>

            </el-form>

        </div>

    </div>
</template>

<script>
    export default {
        data: function(){
            return {                    //rules规则
                ruleForm: {
                    username: 'admin',
                    password: 'admin'
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        
                        if(this.ruleForm.username=='admin'&&this.ruleForm.password=='admin'){
                            
                            localStorage.setItem('ms_username',this.ruleForm.username);
                            this.$router.push('/dashboard');
                        }else{
                            this.$notify({
                                title: '登录失败',
                                message: this.$createElement('i', { style: 'color: red'}, '后台默认账号密码均为 admin'),
                                duration:2000
                                });
                            return false;
                        }
                    } else {
                        this.$notify({
                                title: '登录失败',
                                message: this.$createElement('i', { style: 'color: red'}, '后台默认账号密码均为 admin'),
                                duration:2000
                                });
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:300px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
    .register-btn{
        text-align: center;
    }
    .register-btn button{
        width:100%;
        height:36px;
    }
</style>