import http from "@/utils/request";
import axios from "axios";
//获取我的订单，基线只有一个商家，升级需加商铺id（shopid）
export const getmyorders =(token)=>{
    return http.get(`/orders/HistoryOrders/630e807bdf604941b89192af2eb7396e`, {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}

//修改订单状态
export const orderstatus=(token,status,id)=>{
    return axios({
        method:"put",
        url:"http://localhost:8080/orders/UpdateStatus",
        params:{
            status:status,
          id:id
        },headers:{
        Authorization:' Bearer '+token
        }
      })
}