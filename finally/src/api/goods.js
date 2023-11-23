import http from "@/utils/request";
//创建商品
export const create = (token, price, name, uid, type, variety, weight, store, description) => {
    return http.post('/goods/create', {
        shopId: "630e807bdf604941b89192af2eb7396e",
        price: price,
        name: name,
        fakeId: uid,
        type: type,
        variety: variety,
        weight: weight,
        store: store,//储存条件
        description: description,
    }, {
        headers: {
            Authorization: ' Bearer ' + token
        },

    })
}
//获取待发布商品
export const getpublishgoodlist =(token)=>{
    return http.get('/goods/getPublishGoodList', {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//获取历史商品
export const gethistorygoodlist =(token)=>{
    return http.get('/goods/getHistoryGoodList', {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//修改商品状态(单个)
export const goodstatue =(token,id,statue)=>{
    return http.put(`/goods/changeGoodStatue/${id}?statue=${statue}`,{
        // statue:statue,
    }, {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//修改商品状态(多个)
export const goodsstatue =(token,ids,status)=>{
    return http.put(`/goods/batchChangeGoodStatue`,{
        ids:ids,
        status:status,
    }, {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//发布商品
export const listgoods =(token,id)=>{
    return http.put(`/goods/publish`,{
        // statue:statue,
        ids:id,
    }, {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//获取一级分类(类别)
export const getlistType =(token)=>{
    return http.get('/goodType/listType', {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//获取二级分类(品种)
export const getlistVariety =(token,id)=>{
    return http.get(`/goodType/listVariety?typeId=${id}`, {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}