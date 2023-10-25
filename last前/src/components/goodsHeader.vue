<template>
    <div class="header-container">
        <div class="l-content">
            <router-link to="/zhuye">
              <el-button icon="el-icon-back" size="medium"></el-button>
            </router-link>
          <span class="text">商品详情</span>
        </div>
        <div class="r-content"></div>
    </div>
</template>

<script>
  export default {
      data(){
        return {}
      }
  }
</script>
<style lang="less" scoped>
.header-container {
  padding: 0 20px;
  background-color: #f5f2f2;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid #000000; /* Add a white bottom border with 2px width */
  .text {
    color: rgb(2, 2, 2);
    font-weight: bold;
    font-size: 24px;
    margin-left: 860px;
  }
}
</style>