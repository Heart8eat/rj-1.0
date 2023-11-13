<template>
  <el-form
    label-width="70px"
    :inline="true"
    class="login-container"
    :model="form"
    :rules="rules"
  >
    <el-tabs v-model="loadWho" type="card" @tab-click="handleClick" stretch>
      <el-tab-pane label="买家" name="first"></el-tab-pane>
      <el-tab-pane label="卖家" name="second"></el-tab-pane>
    </el-tabs>
    <h2 class="login_title">果购</h2>
    <el-form-item prop="username">
      <el-input
        v-model="form.username"
        placeholder="请输入用户名"
        class="input"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-row>
        <el-col :span="12">
          <el-input v-model="form.verify" placeholder="请输入验证码"></el-input>
        </el-col>
        <el-col :span="12">
          <div>
            <img
              :src="this.captchaUrl.img"
              alt="验证码"
              style="max-width: 100px; height: auto; cursor: pointer"
              @click="refreshCaptcha"
            />
          </div>
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        type="password"
        v-model="form.password"
        placeholder="请输入密码"
        class="input"
      ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input
        type="password"
        v-model="form.password2"
        placeholder="请确认密码"
        class="input"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button
        @click="regist"
        style="margin-left: 52px; margin-top: 5px"
        type="primary"
        >注册</el-button
      >
      <el-button plain @click="close" style="margin-left: 32px">取消</el-button>
    </el-form-item>
  </el-form>
</template>
  <script>
import axios from "axios";
import router from "@/router";
export default {
  data() {
    return {
      loadWho: "first",
      captchaUrl: {
        key: "",
        img: "",
      },
      form: {
        username: "",
        verify: "",
        password: "",
        password2: "",
      },
      rules: {
        username: [
          { required: true, trigger: "blur", message: "请输入用户名" },
        ],
        password: [{ required: true, trigger: "blur", message: "请输入密码" }],
        password2: [{ required: true, message: "请确认密码", trigger: "blur" }],
        captcha: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },
  mounted() {
    // 发起HTTP请求获取数据
    axios
      .get("http://localhost:8080/merchants/getCode")
      .then((response) => {
        console.log("成功获取数据：", response.data);

        this.captchaUrl.key = response.data.data.key;
        this.captchaUrl.img = response.data.data.image;
        // 更新其他属性以匹配您的数据结构
      })
      .catch((error) => {
        console.error("获取数据时出错：", error);
      });
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
    refreshCaptcha() {
      // 发起HTTP请求以获取新的验证码
      axios
        .get("http://localhost:8080/merchants/getCode")
        .then((response) => {
          console.log("成功获取数据：", response.data);

          this.captchaUrl.key = response.data.data.key;
          this.captchaUrl.img = response.data.data.image;
          // 更新其他属性以匹配您的数据结构
        })
        .catch((error) => {
          console.error("获取数据时出错：", error);
        });
    },
    async regist() {
      console.log(this.form);
      if (this.loadWho == "first") {
        try {
          // 构建HTTP POST请求
          console.log(this.form);
          const response1 = await axios.post(
            "http://localhost:8080/buyers/newBuyer",
            {
              name: this.form.username,
              verify: this.form.verify,
              key: this.captchaUrl.key,
              pwd1: this.form.password,
              pwd2: this.form.password2,
            }
          );
          console.log("成功保存到数据库:", response1.data);
          if (response1.data.data == true) {
            this.$message({
              message: "恭喜你，注册成功",
              type: "success",
            });
            this.$router.push("/loginMain");
          } else {
            this.$notify.error({
              title: "注册失败",
              message: "请检查信息输入是否正确",
            });
          }
        } catch (error) {
          // 请求失败，处理错误
          console.error("保存失败:", error);
          // 可以显示错误消息或采取其他适当的措施
        }
      } else if (this.loadWho == "second") {
        try {
          // 构建HTTP POST请求
          const response2 = await axios.post(
            "http://localhost:8080/merchants/newMerchant",
            {
              name: this.form.username,
              verify: this.form.verify,
              key: this.captchaUrl.key,
              pwd1: this.form.password,
              pwd2: this.form.password2,
            }
          );
          console.log("成功保存到数据库:", response2.data);
          if (response2.data.data == true) {
            this.$message({
              message: "恭喜你，注册成功",
              type: "success",
            });
            this.$router.push("/loginMain");
          } else {
            this.$notify.error({
              title: "注册失败",
              message: "请检查信息输入是否正确",
            });
          }
        } catch (error) {
          // 请求失败，处理错误
          console.error("保存失败:", error);
          // 可以显示错误消息或采取其他适当的措施
        }
      }
    },
    close() {
      this.$router.push("/loginMain");
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
  .input {
    width: 250px;
  }
}
</style>