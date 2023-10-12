<template>
    <div class="sp">
        <div class="sp-content">
            <img src="../assets/images/xigua.jpg" alt="" class="sp-image">
            <div class="sp-text">
                <p class="spName">麒麟西瓜 1粒装</p>
                <p>单果2.5KG起 生鲜水果</p>
            </div>
            <div class="info-bottom">
                <div class="small-images">
                    <!-- 三张小图片并行排列 -->
                    <img src="../assets/images/xigua1.jpg" alt="">
                    <img src="../assets/images/xigua2.jpg" alt="">
                    <img src="../assets/images/xigua3.jpg" alt="">
                </div>
            </div>
            <div class="additional-info">
                <p class="add1">优惠价:  <span style="color: red;">￥39.9</span></p>
            </div>
            <div>
                <router-link to="/books">
                    <el-button type="success" plain class="priceButton">立即购买</el-button>
                </router-link>
             </div>
            <p></p>
            
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
.sp {
    height: 100%;
  .sp-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color:rgb(59, 185, 216);
  }
  .sp-image{
    margin-top: 10px; 
    height: 300px;
    width: 400px;
  }

  .sp-text {
    font-weight: bold;
    font-size: 24px;

  }

  .small-images {
    display: flex;
    justify-content: space-between;
    margin-top: 10px; /* 添加一些上边距 */
  }

  .small-images img {
    padding: 10px;
    width: 100px; /* 调整小图片的大小 */
    height: 100px;
  }

  .additional-info {
    text-align: center;
    font-weight: bold;
    font-size: 32px;
  }

  .priceButton {
    margin-top: 20px; 
    margin-bottom: 80px;
    width: 300px;
    height: 80px;
    font-size: 18px;
    border: 5px solid red; /* 添加红色边框 */
    background-color: white; /* 设置白色背景 */
    color: red; /* 设置红色字体颜色 */
    border-radius: 5px; /* 圆角边框 */
  }
}
</style>