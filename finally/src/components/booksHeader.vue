<template>
    <div class="custom-header">
        <div class="l-content">
          <span class="text">订单结算</span>
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
.custom-header {
    height: 60px;
    text-align: center;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #000000; /* 添加一个白色的2像素底部边框 */

    .text{
    color: #333;
    font-weight: bold; /* 设置字体为黑体 */
    font-size: 24px; /* 设置字体大小 */
    margin-left: 860px;
  }
}
</style>