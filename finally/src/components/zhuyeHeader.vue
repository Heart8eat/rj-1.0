<template>
  <div class="header-container">
    <div class="l-content">
      <span class="text">在线购物</span>
    </div>
    <div class="r-content">
      <el-dropdown>
        <span class="el-dropdown-link">
          <img class="userimage" src="../assets/user.jpg" alt="">
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item>历史记录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
};
</script>
<style lang="less" scoped>
.header-container {
  background-color: #72b5ff;
  color: white;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .text {
    color: white;
    font-weight: bold; /* 设置字体为黑体 */
    font-size: 24px; /* 设置字体大小 */
    margin-left: 20px;
  }
  .r-content{
    margin-right: 10px;
  }
  .userimage{
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
}
</style>
