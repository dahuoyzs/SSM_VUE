<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-row>
                    <el-col>
                        <el-card shadow="hover" class="mgb20">
                            <div class="user-info">
                                <img src="static/img/head.jpg" class="user-avator" alt="">
                                <div class="user-info-cont">
                                    <div class="user-info-name">{{name}}</div>
                                    <div>{{role}}</div>
                                </div>
                            </div>
                            <div class="user-info-list">登录时间：<span>{{ time }}</span></div>
                            <div class="user-info-list">登录地点：<span>郑州</span></div>
                        </el-card>
                        <el-card shadow="hover">
                            <div slot="header" class="clearfix">
                                <span>商品分类占比</span>
                            </div>
                            <div v-for="(item,index) in proportions">
                                 {{ item.goodsclassname }}
                                <el-progress :percentage="item.proportion" :color="item.randColor"></el-progress>
                            </div>
                            <!--
                            水龙头
                            <el-progress :percentage="57.2" color="#42b983"></el-progress>
                            管制品
                            <el-progress :percentage="29.8" color="#f1e05a"></el-progress>
                            塑料管
                            <el-progress :percentage="11.9"></el-progress>
                            地暖
                            <el-progress :percentage="1.1" color="#f56c6c"></el-progress>
                            -->
                        </el-card>
                    </el-col>
                </el-row>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-view grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ VisitsNumber }}</div>
                                    <div>用户访问量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-message grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ ordersize }}</div>
                                    <div>总订单数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ goodscount }}</div>
                                    <div>商品数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" :body-style="{ height: '304px'}">
                    <div slot="header" class="clearfix">
                        <span>公告</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                    <el-table :data="todoList" :show-header="false" height="304" style="width: 100%;font-size:14px;">
                        <el-table-column width="40">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="60">
                            <template slot-scope="scope">
                                <i class="el-icon-edit"></i>
                                <i class="el-icon-delete"></i>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>

            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'dashboard',
        data() {
            return {
                VisitsNumber:0,
                goodscount:0,
                ordersize:0,
                proportions:[],
                name: localStorage.getItem('ms_username'),
                todoList: [
                    {
                        title: '一个人的理想越崇高，生活越纯洁。——伏尼契',
                        status: false,
                    },
                    {
                        title: '世界上最快乐的事，莫过于为理想而奋斗。——苏格拉底',
                        status: false,
                    },
                    {
                        title: '你若要喜爱你自己的价值，你就得给世界创造价值。——歌德',
                        status: false,
                    },
                    {
                        title: '生活的情况越艰难，我越感到自己更坚强，甚而也更聪明。——高尔基',
                        status: false,
                    }
                    
                ]
            }
        },
        computed: {
            randColor(){
                return '#'+Math.floor(Math.random()*256).toString(10);
            },
            time(){
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
                return new Date().format('yyyy-MM-dd');
            },
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        },
        created() {
           this.$axios.get('http://localhost:8080/SSMWW/VisitsNumber.do')
				.then(res=> {
					this.VisitsNumber=res.data;
				});
            this.$axios.get('http://localhost:8080/SSMWW/goodsClassALLMap.do')
            .then(res=> {
                let goodsClassJO=res.data;
                let sum=0;
                for(let key in goodsClassJO){
                    sum+=goodsClassJO[key];
                }
                this.goodscount=sum;
                let colorArr=
                ['#4affce','#ff2fd5','#6af874',
                '#c1f86a','#f86ac1','#fa2edb',
                '#26b0ff','#f5c5a5','#ffe659',
                '#ff47fd'];
                
                for(let key in goodsClassJO )
                {
                    let proportion=Math.round(goodsClassJO[key] / sum * 10000) / 100.00;
                    
                    let colorIndex=Math.floor(Math.random()*10);
                    let randColor=colorArr[colorIndex];
                    console.log("颜色值："+randColor);
                    // for(let a=0;a<10;a++){
                    //     let colorIndex=Math.floor(Math.random()*10);
                    //     console.log(colorArr[colorIndex]);
                    // }
                    this.proportions.push({ goodsclassname:key,number:goodsClassJO[key],proportion,randColor});
                }
                let compare=function(o1,o2)
                {
                    return o2.number-o1.number;
                }
                this.proportions.sort(compare);
            });
            this.$axios.get('http://localhost:8080/SSMWW/goodsOrderCount.do').then(res =>{
                this.ordersize = parseInt(res.data);
                });
        },
        
    }

</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 12px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

</style>
