<template>
    <div id="maindiv" style="background-color:#F0F0F0;width:100%;height:100%;overflow:auto;">
        <button type="button" class="btn btn-success" style="float:right" @click="printPage()" >打印所选项</button>
        <div id="print" >
          <div class="col-sm-9" v-for="(order,index) in orders" style="background-color:#fff;">
              
              <center><h2 style="color:black">河南君豪建材有限商行</h2></center>
              <!--  
              <p style="color:blue">订单号:{{ order.ordercode }}</p>
              <p style="color:blue">下单时间:{{ order.confirmdate }}</p>
              -->
               <table class="table table-bordered" >
                  <tr  >
                    <th >用户：</th><th style="color:red">{{ order.receivename }}</th>
                    <th>联系电话：</th><th style="color:red">{{ order.receivephone }}</th>
                    <th>地址：</th><th style="color:red">{{ order.address }}</th>
                  </tr>
                  <tr>
                    <th>业务员：</th><th>{{ order.salename }}</th>
                    <th>付款方式：</th><th></th>
                    <th>日期：</th><th>{{ order.confirmdate }}</th>
                    
                  </tr>
                  <tr>
                    <th>备注：</th><td colspan="5"></td>
                  </tr>

               </table>
              <table class="table table-bordered" >
                <thead class="thead-default"  
                  <tr>
                    <th style="text-align:center;">序号</th>
                    <th style="text-align:center;">商品名称</th>
                    <th style="text-align:center;">单价</th>
                    <th style="text-align:center;">数量</th>
                    <th style="text-align:center;">合计</th>
                    <th style="text-align:center; width:200px">备注</th>
                    <!--
                    <div style="margin-left:1px;"></div>
                    <th style="text-align:center;">商品名称</th>
                    <th style="text-align:center;">单价</th>
                    <th style="text-align:center;">数量</th>
                    <th style="text-align:center;">合计</th>
                    <th style="text-align:center; width:200px">备注</th>
                    -->
                  </tr>
                </thead>
                <tbody > 
                  <tr v-for="(goods,innerindex) in order.detail">   <!-- danArr[index] 设置单双列  -->
                    <td style="text-align:center;" >{{ innerindex+1 }}</td>
                    <td style="text-align:center;" >{{ goods.goodsname }}</td>
                    <td style="text-align:center;" >{{ goods.price }}</td>
                    <td style="text-align:center;" >{{ goods.number }}</td>
                    <td style="text-align:center;" >{{ goods.number*goods.price }}</td>
                     <td style="text-align:center;" >  </td>
                     <!--
                    <div style="margin-left:1px; "></div>
                    <td style="text-align:center;" >{{ shuangArr[index][innerindex].goodsname }}</td>
                    <td style="text-align:center;" >{{ shuangArr[index][innerindex].price }}</td>
                    <td style="text-align:center;" >{{ shuangArr[index][innerindex].number }}</td>
                    <td style="text-align:center;" >
                    {{ 
                      (shuangArr[index][innerindex].number*shuangArr[index][innerindex].price)===0?
                      '':shuangArr[index][innerindex].number*shuangArr[index][innerindex].price
                     }}
                    </td>
                    <td style="text-align:center;" >  </td>
                     -->
                  </tr>
                </tbody>
              </table>
              <p class="text-right" style="color:red">共{{ order.count }}个商品</p>
              <p class="text-right" style="color:red">总计:{{ order.countprice }}元</p>
              <!--<p class="text-right" style="color:red">销售人员:{{ order.salename }}</p>-->
          </div>
       </div>
    </div>
</template>
<script>
export default {
    name:"print",
    data() {
      return {
        orders:[],
        danArr:[],
        shuangArr:[]
      }
    },
    methods: {
      printPage(){    
        window.print();
      },
      getPrintOrders(){
        let params=JSON.parse(localStorage.getItem('ordercodes'));
        console.log(params);
        this.$axios.post('http://localhost:8080/SSMWW/getPrintOrders.do',params).then(res =>{
            this.orders=res.data;
            this.orders.forEach((item,index,arr)=>{
              console.log(item,index,arr);
              let temArr=item.detail;
              this.danArr[index]=temArr.filter((it,innerdex,arr)=>{
                  return innerdex%2==0;
              });
              this.shuangArr[index]=temArr.filter((item,innerdex,arr)=>{
                  return innerdex%2==1;
              });
              if(this.danArr[index].length>this.shuangArr[index].length)
              {
                this.shuangArr[index].push({"number":"","price":"","goodsname":""});
              }
            })

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
    .table.table-bordered > tr >td {
      height: 10px;
      max-height: 10px;
      }

    
  </style>

  
<!--
    本页面公用了1个接口
-->




