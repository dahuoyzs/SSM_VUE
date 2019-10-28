<template>
  <div id="maindiv" style="background-color:#fff">
    
        <div class="col-lg-12" >
          <div class="row" >
            <div class="col-lg-4" style="margin:5px;">
              <el-date-picker
                v-model="selectDate"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2">
              </el-date-picker>
            </div>
            <div class="col-lg-4" style="margin:2px;">
              <form  class="input-group" @submit.prevent="btnsearch">
                <input type="text" class="form-control " placeholder="输入收货人或者电话搜索" v-model="searchText" ></input>
                <input type="submit" class="btn btn-outline-success" style="margin-left:1px"  value="搜索" ></input>
              </form >
            </div>
            <button class="btn btn-sm btn-primary"  @click="getOrderALL">全部订单</button> 
        </div>
      </div>
      <div class="col-lg-12">
        <el-table
          ref="multipleTable"
          :data="resultorders"
          border
          max-height="700"
          tooltip-effect="dark"
          :cell-style="setCellStyle"
          style="width:100%;text-align:center;font-size:15px"
          @selection-change="handleSelectionChange"
          >
          <el-table-column
            align="center"
            type="selection"
            width="50"
            >
          </el-table-column>
          <el-table-column
            align="center"
            prop="ordercode"
            type=index
            label="序"
            >
          </el-table-column>
          <el-table-column
            align="center"
            prop="ordercode"
            label="订单号"

            >
          </el-table-column>
          <el-table-column
            align="center"  
            prop="confirmdate"
            label="下单时间"

           >
          </el-table-column>
          <el-table-column
            align="center"
            prop="countprice"
            label="总价"
            width="70"
            >
          </el-table-column>
          <el-table-column
            align="center"
            prop="receivename"
            label="收货人"

            >
          </el-table-column>
          <el-table-column
            align="center"
            prop="receivephone"
            label="收货电话"
            >
          </el-table-column>
          <el-table-column
            align="center"
            prop="address"
            label="收货地址"
            >
          </el-table-column>
          <el-table-column
            align="center"
            label="详情"
            width="65"
            >
            <template slot-scope="scope">
              <button @click="getDetails(scope.row)" class="btn btn-sm btn-outline-success">详细</button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="删除"
            width="65"
            >
            <template slot-scope="scope">
              <button @click="dodelete(scope.row)"class="btn btn-sm btn-outline-danger">删除</button>
            </template>
            
          </el-table-column>

        </el-table>
      </div><!-- col-lg-12 -->
      <div class="col-lg-12">
        <!-- 详细 -->
        <el-dialog :title="titleText" :visible.sync="dialogTableVisible">
          <el-table :data="orderDetails">
            <el-table-column property="goodsname" label="商品名称" width="200"></el-table-column>
            <el-table-column property="price" label="单价" width="80" style="color:red"></el-table-column>
            <el-table-column property="number" label="个数" width="80"></el-table-column>
            <el-table-column property="confirmdate" label="下单时间"></el-table-column>
          </el-table>
        </el-dialog>
        <!-- 打印按钮 
        <div id="print">
          <button type="button" style="margin-top:10px;" class="btn btn-outline-info" @click="printupdate()">打印所选项</button>
        </div>
        -->
      </div> <!-- col-lg-12 -->
  </div> <!-- maindiv -->
</template>
<script>export
default {
    name:
    "orderlist",
    data() {
      return {
        pagenow: 1,
        //当前页码
        pagelimit: 10,
        //限制一页只能显示
        ordersize: 0,
        //商品总数查数据库得到
        pagesizes: [5, 10, 15, 20],
        resultorders: [],
        selectOrders:[],
        pNOrderNum:0,
        pNRealOrderNum:0,
        isAll:false,
        orderDetails:[
          {goodsname:'青铜水龙头',price:15,number:3,goodsid:1003},
          {goodsname:'黄金水龙头',price:30,number:1,goodsid:1004}
        ],
        dialogTableVisible:false,
        titleText:'订单详情',

        pickerOptions2: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        selectDate:[],
        searchText:'',
      }
    },
    methods: {
      test(scope){
        console.log(scope);
      },
      btnsearch(){

          Date.prototype.format = function(format)
          {
            var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(),    //day
            "h+" : this.getHours(),   //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
            "S" : this.getMilliseconds() //millisecond
            }
            if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            for(var k in o)if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
            ("00"+ o[k]).substr((""+ o[k]).length));
            return format;
          }
          let startDate="";
          let endDate="";
          if(this.selectDate==null){
            startDate="";endDate="";
          }else if(this.selectDate.length!=2){
            startDate="";endDate="";
          }else{
            startDate = new Date(this.selectDate[0]).format('yyyy-MM-dd hh:mm:ss');   
            endDate = new Date(this.selectDate[1]).format('yyyy-MM-dd hh:mm:ss');
          }
          // console.log(startDate);
          // console.log(endDate);
          // console.log(this.searchText);
          if(this.searchText==""&&startDate==""){//都空判断
            this.getOrderALL();
            //this.$message({message: '请先选择搜索条件',type: 'warning'});
          }else if(this.searchText==""||this.searchText==null){//搜索为空判断
            this.$axios.get('http://localhost:8080/SSMWW/goodsOrderALLByDate.do'
                +'?startDate='+startDate
                +'&endDate='+endDate
                )
                .then(res=> {
                  console.log(res.data);//输出返回的结果
                  this.resultorders=res.data;
                  this.goodscount=res.data.length;
                });
          }else if(startDate==""||endDate==""){//时间为空判断
            this.$axios.get('http://localhost:8080/SSMWW/goodsOrderALLBySearch.do'
                +'?searchText='+this.searchText
                )
                .then(res=> {
                  console.log(res.data);//输出返回的结果
                  this.resultorders=res.data;
                  this.goodscount=res.data.length;
                });
          }else{
            this.$axios.get('http://localhost:8080/SSMWW/goodsOrderALLBySearchAndDate.do?searchText='
                +this.searchText
                +'&startDate='+startDate
                +'&endDate='+endDate
                )
                .then(res=> {
                  console.log(res.data);//输出返回的结果
                  this.resultorders=res.data;
                  this.goodscount=res.data.length;
                });
          }
      },
      setCellStyle({row, column, rowIndex, columnIndex}){
        if(columnIndex===4)
        {
          return 'color:red'
        }
      },
      handleSelectionChange(val) {//前边的选择会返回val这个数组
        console.log(val);
        this.selectOrders=val;
      },
      printupdate(){
        console.log("printupdate");
        let ordercodes=[];
        ordercodes=this.selectOrders.map(val=>val.ordercode);//重新构造出来订单号数组
        console.log(ordercodes);//选中的订单号
        if(ordercodes.length===0){
            this.$notify({
              title: '打印提示',
              message: this.$createElement('i', { style: 'color: blue'}, '请先选择要打印的订单哦'),
              duration:2000
            });
        }else{
            
            localStorage.setItem('ordercodes',JSON.stringify(ordercodes));
            let routeData = this.$router.resolve({
              name: "print",
            });
            window.open(routeData.href, '_blank');
        }
      },
      print(){
        let filtIndexArr = this.selectOrders.map((val,index)=>val?{val,index}:false).filter(item=>item.val===false?false:item).map(item=>item.index);
        //console.log(filtIndexArr);//选中的下标
        let pagecount= parseInt(this.ordersize%this.pagelimit==0?this.ordersize/this.pagelimit:(this.ordersize/this.pagelimit)+1);
        
        let ordercodes=[];
        if(pagecount==this.pagenow){
          console.log(this.pagenow,this.pagelimit,this.ordersize,this.pNOrderNum);
          for(let i=0;i<this.ordersize%this.pagelimit;i++)
          {
            ordercodes[i]= this.resultorders[filtIndexArr[i]].ordercode;
          }
        }else{
           ordercodes=filtIndexArr.map(val=>this.resultorders[val].ordercode);
           //let ordercodes=filtIndexArr.map(val=>this.resultorders[val].ordercode);
        }
        console.log(ordercodes);//选中的订单号
        if(ordercodes.length===0){
            this.$notify({
              title: '打印提示',
              message: this.$createElement('i', { style: 'color: blue'}, '请先选择要打印的订单哦'),
              duration:2000
            });
        }else{
            
            localStorage.setItem('ordercodes',JSON.stringify(ordercodes));
            let routeData = this.$router.resolve({
              name: "print",
            });
            window.open(routeData.href, '_blank');
        }
      },
      dodelete(order){
        this.$axios.get('http://localhost:8080/SSMWW/deleteOrderDetailsByOrderCode.do?ordercode='+order.ordercode)
        .then(res =>{
          console.log(res.data);//返回结果
          // this.orderDetails=res.data;
          // this.titleText=order.ordercode+"号订单";
          let result=res.data;
          if(result.code=='200'&&result.msg=='删除成功'){
							this.$message({
								message: '恭喜你，删除成功',
								type: 'success'
							});
						this.btnsearch();//点一下搜索按钮，来刷新界面
						}else{
							this.$message({
								message: '不好意思，删除失败',
								type: 'warning'
							});
						}
        });
      },
      getDetails(order) {
        //console.log(order.ordercode);
        this.dialogTableVisible=true;
        this.$axios.get('http://localhost:8080/SSMWW/orderDetailsByOrderCode.do?ordercode='+order.ordercode)
        .then(res =>{
          //console.log(res.data);//返回结果
          this.orderDetails=res.data;
          this.titleText=order.ordercode+"号订单";
        });
      },
      getOrderALL() {
        this.selectOrders=[];
        this.$axios.get('http://localhost:8080/SSMWW/goodsOrderALL.do').then(res =>{
          //console.log(res.data)
          this.resultorders = res.data;//返回结果
        });
      },
      getOrder() {
        this.selectOrders=[];
        this.$axios.get('http://localhost:8080/SSMWW/goodsOrderALLByPage.do?pageNow=' + this.pagenow + '&pageLimit=' + this.pagelimit).then(res =>{
          //console.log(res.data)
          this.resultorders = res.data;//返回结果
          this.pNOrderNum=this.ordersize>this.pagelimit?this.pagelimit:this.ordersize;//本页显示的条目数
          for(let i=0;i<this.pNOrderNum;i++)    //初始化所有的额选项
            this.selectOrders.push(this.isAll);
        });
      },
      getOrderSize() {
        this.$axios.get('http://localhost:8080/SSMWW/goodsOrderCount.do').then(res =>{
          this.ordersize = res.data;
        });
      }
    },
    created() {
      let salename=localStorage.getItem("salename");
      console.log(salename);
      if(salename==null)
			{
				
			}else{
        this.getOrderALL();
      }
      
    }
  }</script>




