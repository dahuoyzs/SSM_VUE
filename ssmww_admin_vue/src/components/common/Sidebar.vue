<template>
    <div class="sidebar">      <!--  外部div  -->
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router><!--  el-menu菜单  -->

            <template v-for="item in items">  <!--  遍历items来显示到底有一个条目  -->

                <template v-if="item.subs">  <!--  如果条目中有子条目  模板如下  -->

                    <el-submenu :index="item.index" :key="item.index"> 
                    <!-- 点击可跳转 element中 此标签的index就是路由的作用   v-bind简写:   v-on简写@  -->

                        <template slot="title"> <!--  别忘了slot是干啥用的，父子组件传值  -->
                            <i :class="item.icon"></i>              <!--  左侧图标  -->
                            <span slot="title">{{ item.title }}</span>  <!--  列表文本  -->
                        </template>
                
                         <!--  列表二级路由  -->
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">
                         <!-- 点击可跳转 element中 此标签的index就是路由的作用   v-bind简写:   v-on简写@  -->

                             <i :class="item.icon"></i> <!--  可以跟父级图标保持一致   -->
                            {{ subItem.title }}
                        </el-menu-item>

                    </el-submenu>

                </template>
                <template v-else>       <!--  如果条目中没有子条目  模板如下  -->

                    <el-menu-item :index="item.index" :key="item.index">
                    <!-- 点击可跳转 element中 此标签的index就是路由的作用   v-bind简写:   v-on简写@  -->
                    
                        <i :class="item.icon"></i>          <!--  左侧图标  -->
                        <span slot="title">{{ item.title }}</span>  <!--  列表文本  -->
                    </el-menu-item>

                </template>

            </template>

        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items: [ //这个跟着el-menu集合使用路由     subs可以做二级路由  也可多层只需每层都写subs即可
                    {
                        icon: 'el-icon-setting',    //左侧列表中显示的icon的class
                        index: 'dashboard',        //对应的哪个界面
                        title: '系统首页'      //左侧列表中显示的文本
                    },
                    {
                        icon: 'el-icon-tickets',
                        index: 'goodslist',
                        title: '商品列表'
                    },
                    {
                        icon: 'el-icon-message',
                        index: 'goodsmanger',
                        title: '商品管理'
                    },
                    {
                        icon: 'el-icon-date',
                        index: 'addgoods',
                        title: '添加商品'
                    },
                    {
                        icon: 'el-icon-star-on',
                        index: 'orderlist',
                        title: '所有订单'
                    },
                    {
                        icon: 'el-icon-tickets',
                        index: 'addresslist',
                        title: '常用地址'
                    }
                    // {
                    //     icon: 'el-icon-rank',
                    //     index: 'drag',
                    //     title: '拖拽列表'
                    // },
                    // {
                    //     icon: 'el-icon-warning',
                    //     index: 'permission',
                    //     title: '权限测试'
                    // },
                    // {
                    //     icon: 'el-icon-error',
                    //     index: '404',
                    //     title: '404页面'
                    // }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 250px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
