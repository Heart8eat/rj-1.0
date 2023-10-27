<template>
  <el-form
    label-width="70px"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
  >
    <h2 class="login_title">在线购物</h2>
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
        @click="submit"
        style="margin-left: 102px; margin-top: 5px"
        type="primary"
        >登录</el-button
      >
    </el-form-item>
  </el-form>
  <!-- <el-container>
        <el-header></el-header>
        <el-main>
            <h2>在线购物</h2>
            <el-input v-model="input" placeholder="用户名"></el-input>
            <el-input placeholder="密码" v-model="input" show-password></el-input>
            <el-row>
                <el-button type="primary" round>登录</el-button>
            </el-row>
        </el-main>
        <el-footer></el-footer>
    </el-container> -->
</template>
<script>
import Mock from "mockjs";
import Cookie from "js-cookie"
import { login } from "@/api/merchants";
import { history } from "@/api/merchants";
export default {
  data() {
    return {
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
  methods:{
    //登录
    submit() {
      login(this.form.username,this.form.password).then((data)=>{
        console.log(data.data)
        if(data.data.data.token!=null){
          //获取后端token信息
          const token = data.data.data.token
          //输出token值
          //console.log(token)
          //token信息传入cookie用于不同页面间通讯
          Cookie.set('token',token)
          this.$router.push('/salerindex')
        }
        else{
          this.$message.error(data.data.data);
        }
      })
        //token信息
        //const token = Mock.Random.guid()
        //token信息传入cookie用于不同页面间通讯
        //this.$router.push('/salerindex')
    }
  },
  // mounted(){
  //   history().then((data)=>{
  //         console.log(data)
  //   })
  // }
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
</style>