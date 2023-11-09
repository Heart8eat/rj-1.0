<template>
  <el-form
    label-width="70px"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
  >
    <el-tabs v-model="loadWho" type="card" @tab-click="handleClick" stretch>
      <el-tab-pane label="买家" name="first" class="custom-tab"></el-tab-pane>
      <el-tab-pane label="卖家" name="second" class="custom-tab"></el-tab-pane>
    </el-tabs>
    <h2 class="login_title">果购</h2>
    <el-form-item label="用户名" prop="username">
      <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="请输入密码"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button
        @click="login2"
        style="margin-left: 52px; margin-top: 5px"
        type="primary"
        >登录</el-button
      >
      <el-button plain @click="regist" style="margin-left: 32px"
        >注册</el-button
      >
    </el-form-item>
  </el-form>
</template>
<script>
import Mock from "mockjs";
import Cookie from "js-cookie";
import { login } from "@/api/merchants";
import { loginB } from "@/api/merchants";
import { history } from "@/api/merchants";
export default {
  data() {
    return {
      loadWho: "first",
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          { required: true, trigger: "blur", message: "请输入用户名" },
        ],
        password: [{ required: true, trigger: "blur", message: "请输入密码" }],
      },
    };
  },
  methods: {
    handleClick(tab) {
      if (tab.name === "first") {
        this.loadWho = "first"; // 选中买家标签时，设置loadWho为1
      } else if (tab.name === "second") {
        this.loadWho = "second"; // 选中卖家标签时，设置loadWho为2
      }
    },
    regist() {
      this.$router.push("/regist"); //
    },
    login2() {
      if (this.loadWho == "first") {
        console.log(this.form.username);
        loginB(this.form.username, this.form.password).then((data) => {
          console.log(data.data);
          if (data.data.data.token != null) {
            //获取后端token信息
            const token = data.data.data.token;
            //输出token值
            //console.log(token)
            //token信息传入cookie用于不同页面间通讯
            Cookie.set("token", token);
            this.$router.push("/zhuye");
          } else {
            this.$message.error(data.data.data);
          }
        });
      } else if (this.loadWho == "second") {
        console.log(this.form.password);
        login(this.form.username, this.form.password).then((data) => {
          console.log(data.data);
          if (data.data.data.token != null) {
            //获取后端token信息
            const token = data.data.data.token;
            //输出token值
            //console.log(token)
            //token信息传入cookie用于不同页面间通讯
            Cookie.set("token", token);
            this.$router.push("/salerindex");
          } else {
            this.$message.error(data.data.data);
          }
        });
      }
    },
  },
};
</script>

<style lang="less" scoped>
.login-container {
  width: 350px;
  border: 1px solid #eaeaea;
  margin: 180px auto;
  padding: 20px 35px 20px 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  box-sizing: border-box;
  .login_title {
    text-align: center;
    margin-bottom: 40px;
    color: #72b5ff;
  }
  .el-input {
    width: 198px;
  }
  
}
.full-width-tabs {
  width: 100%; /* Make the tabs fill the entire width of the container */
}
.custom-tab {
  color: #409EFF; /* 设置选中时的背景颜色 */
}
</style>