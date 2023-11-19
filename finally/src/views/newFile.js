import Cookie from "js-cookie";
import { getpwd } from "@/api/merchants";
import { changepwd } from "@/api/merchants";

export default (await import('vue')).defineComponent({
data() {
return {
dialogVisible: false,
form: {
opwd: "",
npwd: "",
cpwd: "",
},
};
},
methods: {
//创建商品
creategoods() {
this.$router.push("/creategoods");
},
//发布商品
listgoods() {
this.$router.push("/listgoods");
},
//我的订单
myorders() {
this.$router.push("/myorders");
},
//价格管理
pricemanage() {
this.$router.push("/pricemanage");
},
//历史商品
historicalgoods() {
this.$router.push("/historicalgoods");
},
//刷新页面
refresh() {
location.reload();
},
//修改密码
changepwd() {
//console.log(this.form.opwd, this.form.cpwd, this.form.npwd);
if (this.form.opwd == "" ||
this.form.cpwd == "" ||
this.form.npwd == "") {
this.$message.error("密码不能为空");
//dialogVisible = false;
} else {
const token = Cookie.get("token");
getpwd(token).then((data) => {
//console.log(data.data.data)
const gpwd = data.data.data.password;
if (this.form.opwd == gpwd) {
if (this.form.cpwd == this.form.npwd) {
changepwd(token, this.form.cpwd).then((data) => {
this.$message({
message: "密码修改成功",
type: "success",
});
setTimeout(() => this.refresh(), 1000);
});
} else {
this.$message.error("新密码与确定密码不一致");
}
} else {
this.$message.error("旧密码输入错误");
}
});
}
},
async handleCommand(command) {
//修改密码
if (command === "a") {
this.dialogVisible = true;
console.log("1111");
} //退出登录
else if (command === "b") {
await Cookie.set('token', "");
await Cookie.setMaxAge(0);
await location.reload();
}
},
},
});
