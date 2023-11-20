<template>
    <div class="sp">
        <div class="sp-content">
            <img :src="bindsrc(product.img)" alt="" class="sp-image">
            <div class="sp-text">
                <p class="spName">{{product.name}} 1粒装</p>
                <p>单果2.5KG起 生鲜水果</p>
            </div>
           
            <div class="additional-info">
                <p class="add1">优惠价:  <span style="color: red;">￥{{product.price}}</span></p>
            </div>
            <div>
                <router-link  :to="'/books/'+product.id">
                    <el-button type="success" plain class="priceButton">立即购买</el-button>
                </router-link>
             </div>
            <p></p>
            
        </div>
    </div>
  </template>

  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
          product: {
          name: '',
          price: '',
          id:'',
          img:''
          // Add more properties as needed
        },
      };
    },
    methods:{
      bindsrc(img) { 
        console.log('url:', img);
      return "http://localhost:8080" + img;
    },
  },
    created() {
      const productId = this.$route.params.productId;
      console.log('Product ID:', productId);
      // 构建完整的URL
      const url = `http://localhost:8080/goods/getGoodDetails/${productId}`;
      
      // 发起HTTP请求获取数据
      axios.get(url)
        .then(response => {
          console.log('成功获取数据：', response.data);

          this.product.name = response.data.data.name;
          this.product.price = response.data.data.price;
          this.product.id = productId;
          this.product.img = response.data.data.mainImage.url;
          // 更新其他属性以匹配您的数据结构
        })
        .catch(error => {
          console.error('获取数据时出错：', error);
        });
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
    background-color:rgb(114, 181 ,255);
  }
  .sp-image{
    margin-top: 40px; 
    height: 309px;
    width: 391px;
  }
  .spName{
    margin-left: 40px; 
  }

  .sp-text {
    font-weight: bold;
    font-size: 24px;

  }


  .additional-info {
    text-align: center;
    font-weight: bold;
    font-size: 32px;
    margin-top: 30px; 
  }

  .priceButton {
    margin-top: 20px; 
    margin-bottom: 110px;
    width: 300px;
    height: 80px;
    font-size: 25px;
    border: 2px solid red; /* 添加红色边框 */
    background-color: white; /* 设置白色背景 */
    color: red; /* 设置红色字体颜色 */
    border-radius: 10px; /* 圆角边框 */
  }
}
</style>