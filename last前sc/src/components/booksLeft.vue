<template>
    <div class="B-contanier">
        <el-row>
             <el-col :span="24">
                <div class="head">
                    <i class="el-icon-s-shop">由“商家一”为您配送</i>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="8">
                <div class="half-left">
                    <img src="../assets/images/xigua.jpg" alt="" class="image">
                </div>
            </el-col>
            <el-col :span="16">
                <div class="half-right">
                    <el-row>
                        <el-col :span="8">
                            <span class="sp-text3">数量：</span>
                        </el-col>
                        <el-col :span="16">
                            <el-input-number v-model="num" @change="handleChange" :min="1" :max="100" label="描述文字" class="custom-input-number"></el-input-number>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" class="zffs">
                            <span class="sp-text4">支付方式：</span>
                        </el-col>
                    </el-row>
                    <el-row >
                        <div class="button-container">
                            <el-button class="custom-button" plain>货到付款</el-button>
                            <el-button class="custom-button" plain>在线支付</el-button>
                        </div>
                    </el-row>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <div class="sp-text">
                    <p class="spName">{{product.name}} 1粒装</p>
                    <p>单果2.5KG起 生鲜水果</p>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="sp-text2">
                    总计：<span class="money">￥{{ sum }}</span>
                </div>
            </el-col>
        </el-row>
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
                // Add more properties as needed
            },
            num: 1,
        };
    },
    methods: {
        handleChange() {
            this.updateSum();
        },
        updateSum() {
        // 在这里更新 sum 的值
            this.sum = this.num * this.product.price;; // 使用 num 乘以 39.9 更新 sum 的值
        }
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
          // 更新其他属性以匹配您的数据结构
        })
        .catch(error => {
          console.error('获取数据时出错：', error);
        });
  },
};
</script>
<style lang="less" scoped>
.image{
    width: 250px;
    height: 200px;
    padding: 20px;
}
.head{
    font-size: 24px;
    padding: 30px 0 5px 30px;
}
.zffs{
    margin-top:50px ;
}
.sp-text{
    margin-left: 30px;
    font-size: 24px;
    .spName{
        margin-left: 40px;
    }
}
.sp-text2{
    font-size: 32px;
    font-weight: bold;
    margin-top: 60px;
    margin-left: 180px;
    .money{
        font-size: 40px;
        color: red;
    }
}
.sp-text3{
    font-size: 32px;
    font-weight: bold;
}
.sp-text4{
    font-size: 32px;
    font-weight: bold;
    padding: 30px 0px;
}
.button-container {
  display: flex; /* 使用 Flex 布局 */
  justify-content: space-between; /* 在容器中均匀分配按钮 */
  padding: 30px 5px 20px 0px;
  width: 400px;
  height: 60px;
}

.custom-button {
  flex: 1; /* 均分容器宽度，使按钮并行 */
  /* 如果需要，您可以添加其他样式，如大小、颜色等 */
}
.custom-input-number .el-input-number__decrease{
    color: #3894FF; /* 更改加减符号的颜色 */
}
.custom-input-number .el-input-number__increase {
  color: #3894FF; /* 更改加减符号的颜色*/
}
</style>