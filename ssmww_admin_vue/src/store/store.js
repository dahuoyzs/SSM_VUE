import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        ordercodes:[],
    },
    getters:{//获取属性状态
        getOrderCodes:state=>state.ordercodes,

    },
    mutations:{//改变属性状态
        setOrderCodes(state,ordercodes){
            
            state.ordercodes=ordercodes;
        }
    },
    actions:{//应用mutations

    }
})