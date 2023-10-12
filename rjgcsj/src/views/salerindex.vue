<template>
  <el-container>
    <el-header style="background-color: #72b5ff">
      <h1 class="word" style="color: #ffffff; float: left">在线购物</h1>
      <el-button
        class="button3"
        icon="el-icon-s-tools"
        round
        style="float: right"
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
</template>
<script>
export default {
  data() {
    return {};
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
</style>
