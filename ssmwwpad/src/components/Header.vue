<template>
    <header>
       <nav class="navbar navbar-expand-sm bg-light navbar-light" style="background-color: #e3f2fd;" >

          <ul class="navbar-nav">
            <li><router-link class="nav-link" to="/" style="color:blue;font-size:1em;">主页</router-link></li>
            <li><router-link class="nav-link" to="/GoodsList" style="color:blue;font-size:1em;">商品列表</router-link></li>
            <li><router-link class="nav-link" to="/GoodsOrders" style="color:blue;font-size:1em;">订单管理</router-link></li>
          </ul>
          <ul class="navbar-nav ml-auto">
            <li v-show="!IsLogin">
              <div class="btn-group">
                <button class="btn  btn-sm btn-info"  @click="btnLogin">登录</button>
                <button class="btn  btn-sm btn-info" @click="btnRegister">注册</button>
              </div>
             </li>
            <li v-show="IsLogin">
              <div class="btn-group">
                <button class="btn  btn-sm btn-info" @click="saleinfo">{{ salename }}</button>
                <button class="btn  btn-sm btn-info" @click="exitAccount">退出</button>
              </div>
             </li>
          </ul>
        </nav>
    </header> 
</template>


<script>
export default {
    data() {
      return {
        salename:'test',
        IsLogin:false,
      }
    },
    methods: {
      saleinfo(){
      },
      btnLogin(){
        this.IsLogin=this.checkLogin();
        if(this.IsLogin)
          this.$router.push({path:'/goodslist'});
        else
          this.$router.push({path:'/Login'});

      },
      btnRegister(){
        this.IsLogin=this.checkLogin();
        if(this.IsLogin)
          this.$router.push({path:'/goodslist'});
        else
          this.$router.push({path:'/Register'});
      },
      
      exitAccount(){
        localStorage.setItem('salename',"");
        this.IsLogin=false;
        console.log("退出");
        this.$router.push({path:'/Login'});
      },
      checkLogin(){
        let salename=localStorage.getItem('salename');
        this.salename=salename;
        if(salename==null||salename=="")return false;else return true;
      },

    },
    created() {
      this.IsLogin=this.checkLogin();
    }
  }</script>
