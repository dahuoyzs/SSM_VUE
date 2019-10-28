<template>
    <div class="wrapper">

        <v-head></v-head>               <!-- 头部  -->
        <v-sidebar></v-sidebar>         <!-- 左边  -->

        <div class="content-box" :class="{'content-collapse':collapse}">        
            <v-tags></v-tags>           <!-- 顶部已打开页面标签控件  -->

            <div class="content">         <!-- 主要显示区域  -->
                <transition name="move" mode="out-in">  <!-- 渐变标签  -->
                    <keep-alive :include="tagsList">
                        <router-view></router-view>     <!-- 路由  -->
                    </keep-alive>
                </transition>
            </div>
        </div>

    </div>
</template>

<script>
    import vHead from './Header.vue';           //引入头控件
    import vSidebar from './Sidebar.vue';
    import vTags from './Tags.vue';
    import bus from './bus';
    export default {
        data(){
            return {
                tagsList: [],
                collapse: false
            }
        },
        components:{
            vHead, vSidebar, vTags
        },
        created(){

            bus.$on('collapse', msg => {
                
                //console.log(msg);
                this.collapse = msg;
            })

            // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
            bus.$on('tags', msg => {
                //console.log( msg);
                let arr = [];
                for(let i = 0, len = msg.length; i < len; i ++){
                    msg[i].name && arr.push(msg[i].name);
                }
                this.tagsList = arr;
            })
        }
    }
</script>
