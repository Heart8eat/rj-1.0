import http from "@/utils/request";
//卖家登录
export const login = (name, password) => {
    return http.get('/merchants/login', {
        params: {
            name: name,
            password: password
        }
    })
}
//买家登录
export const loginB = (name, password) => {
    return http.get('/buyers/login', {
        params: {
            name: name,
            password: password
        }
    })
}
//获取密码
export const getpwd = (token) => {
    return http.get('/merchants/getInfo', {
        headers: {
            Authorization:' Bearer '+token
        }
    })
}
//修改密码
export const changepwd = (token,pwd) => {
    //console.log(token)
    //console.log(pwd)  
    return http.put('/merchants/updatePassword', {
        newPassword:pwd
    },{
        headers: {
            Authorization: ' Bearer ' + token
        },
    })
}

//测试
// export const history = () => {
//     return http.get('/orders/HistoryOrders/630e807bdf604941b89192af2eb7396e', {
//     })
// }