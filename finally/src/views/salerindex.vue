<template>
  <div>
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%">
      <el-form
        ref="form"
        :model="form"
        label-width="80px"
        label-position="left"
      >
        <el-form-item label="用户名:"> 啊对对对 </el-form-item>
        <el-form-item label="原密码:">
          <el-input class="input1" v-model="form.opwd"></el-input>
        </el-form-item>
        <el-form-item label="新密码:">
          <el-input class="input1" v-model="form.npwd"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:">
          <el-input class="input1" v-model="form.cpwd"></el-input>
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer"
        style="display: flex; justify-content: center"
      >
        <el-button type="primary" @click="changepwd">确 定</el-button>
        <el-button style="margin-left: 40px" @click="dialogVisible = false"
          >取 消</el-button
        >
      </span>
    </el-dialog>
    <el-container>
      <el-header style="background-color: #72b5ff">
        <h1 class="word" style="color: #ffffff; float: left">在线购物</h1>
        <el-button
          class="button3"
          icon="el-icon-s-tools"
          round
          style="float: right"
          @click="dialogVisible = true"
          >修改密码</el-button
        >
      </el-header>
      <el-main style="display: flex; justify-content: center">
        <el-button
          @click="listgoods"
          class="large-button"
          style="color: #268cfd; margin-right: 50px"
          ><div class="size-icon"><i class="el-icon-s-goods"></i></div>
          发布商品</el-button
        >
        <el-button
          @click="myorders"
          class="large-button"
          style="color: #268cfd; margin-left: 50px"
          ><div class="size-icon"><i class="el-icon-s-order"></i></div>
          我的订单</el-button
        >
      </el-main>
      <el-footer style="display: flex; justify-content: center"
        ><el-button
          @click="historicalgoods"
          class="button2"
          type="info"
          icon="el-icon-time"
          style="color: black"
          >历史记录</el-button
        ></el-footer
      >
    </el-container>
  </div>
</template>

<script>
import Cookie from "js-cookie";
import { getpwd } from "@/api/merchants";
import { changepwd } from "@/api/merchants";
export default {
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
    //发布商品
    listgoods() {
      this.$router.push("/listgoods");
    },
    //我的订单
    myorders() {
      this.$router.push("/myorders");
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
      if (
        this.form.opwd == "" ||
        this.form.cpwd == "" ||
        this.form.npwd == ""
      ) {
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
  },
};
</script>

<style lang="less" scoped>
.large-button {
  //   left: 102px;
  //   top: 167px;
  width: 550px;
  height: 520px;
  border-radius: 30px;
  font-size: 80px;
  text-align: center;
  font-family: Roboto;
  .size-icon {
    margin-top: 0;
    font-size: 300px;
  }
}
.button2 {
  width: 1200px;
  height: 80px;
  border-radius: 30px;
  font-size: 60px;
  text-align: center;
  font-family: Roboto;
}
.button3 {
  font-size: 15px;
  font-family: Roboto;
  margin: 10px 0;
}
.word {
  font-size: 30px;
  font-family: Roboto;
  margin: 10px 0;
}
.input1 {
  width: 250px;
}
</style>
