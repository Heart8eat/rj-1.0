import http from "@/utils/request";
//保存图片
export const uploadImg = (token,id, mainfile, otherfile) => {
    return http.post(`/files/uploadImg?id=${id}`, { mainFile: mainfile, uploadFiles: otherfile }, {
        headers: {
            Authorization: ' Bearer ' + token,
            "Content-Type":"multipart/form-data"
        },

    })
}