<template>
  <el-form
    label-width="70px"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
  >
    <el-tabs v-model="loadWho" type="card" @tab-click="handleClick">
      <el-tab-pane label="买家" name="first"></el-tab-pane>
      <el-tab-pane label="卖家" name="second"></el-tab-pane>
    </el-tabs>
    <h2 class="login_title">果购</h2>
    <el-form-item prop="username">
      <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="请输入密码"
      ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        type="password"
        v-model="form.password2"
        placeholder="请确认密码"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button
        @click="regist"
        style="margin-left: 82px; margin-top: 5px"
        type="primary"
        >注册</el-button
      >
    </el-form-item>
  </el-form>
</template>
  <script>
export default {
  data() {
    return {
      loadWho: "first",
      form: {
        username: "",
        password: "",
        password2: "",
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
        console.log(this.loadWho);
      } else if (tab.name === "second") {
        this.loadWho = "second"; // 选中卖家标签时，设置loadWho为2
        console.log(this.loadWho);
      }
    },
    regist() {
      console.log(this.form);
      if (this.loadWh === "first") {
      } else if (this.loadWho == "second") {
        const formData = new FormData();
        formData.append("name", form.username);
        formData.append("verify", form.password);
        formData.append("pwd1", form.password);
        formData.append("pwd2", form.password2);
        fetch("http://localhost:8080/merchants/newMerchant", {
          method: "POST",
          body: formData,
        })
          .then((response) => {
            if (response.ok) {
              // 请求成功
              return response.json();
            } else {
              // 请求失败
              throw new Error("请求失败");
            }
          })
          .then((data) => {
            // 成功处理返回的数据
            console.log(data);
          })
          .catch((error) => {
            // 处理错误
            console.error(error);
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
    width: 250px;
  }
}
</style>