<template>
  <div id="app">
    <div class="container col-lg-10">
      <appHeader></appHeader>
    </div>
    <div class="container col-lg-10">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Header from './components/Header';
export default {
  components:{
    "appHeader":Header
  },
  created(){
      
  }
}
</script>

<style>

</style>
