<template>
    <div class="header-container">
        <div class="l-content">
          <span class="text">在线购物</span>
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
.header-container{
    background-color:rgb(48, 139, 199);
    color: white;
    height: 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  .text{
    color: white;
    font-weight: bold; /* 设置字体为黑体 */
    font-size: 24px; /* 设置字体大小 */
    margin-left: 20px;
  }
}

</style>
