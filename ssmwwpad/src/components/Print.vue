<template>
    <div id="maindiv" style="background-color:#F0F0F0;width:100%;height:100%;overflow:auto;">
        <button type="button" class="btn btn-success" style="float:right" @click="printPage()" >打印所选项</button>
        <div id="print" >
          <div class="col-lg-9" v-for="(order,index) in orders" style="background-color:#fff;">
              <p style="color:blue">订单号:{{ order.ordercode }}</p>
              <p style="color:blue">下单时间:{{ order.confirmdate }}</p>
              <table class="table" >
                <thead class="thead-default">
                  <tr>
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>个数</th>
                    <th>合计</th>
                  </tr>
                </thead>
                <tbody>
                            
                  <tr v-for="(goods,index) in order.detail">
                    <td>{{ index }}</td>
                    <td>{{ goods.goodsname }}</td>
                    <td>{{ goods.price }}</td>
                    <td>{{ goods.number }}</td>
                    <td>{{ goods.number*goods.price }}</td>
                  </tr>
                </tbody>
              </table>
              <p class="text-right" style="color:red">总计:{{ order.countprice }}元</p>
              <p class="text-right" style="color:red">销售人员:{{ order.salename }}</p>
          </div>
       </div>
    </div>
</template>
<script>
export default {
    name:"print",
    data() {
      return {
        orders:[]  
      }
    },
    methods: {
      printPage(){    
        window.print();
      },
      getPrintOrders(){
        let params=JSON.parse(localStorage.getItem('ordercodes'));
        console.log(params);
        this.$axios.post('http://localhost:8080/WaterWarm1.0/GetPrintOrders',params).then(res =>{
            console.log(res.data);
            this.orders=res.data;
        });
      }
    },
    created() {
      this.getPrintOrders()
    }
  }</script>
  <style scoped>
    table{
      border: 1px solid #151515;
    }
    thead{
      border: 1px solid #151515;
    }

    
  </style>
