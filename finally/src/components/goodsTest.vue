<template>
  <div class="g-container">
    <div class="product-description">
      <!-- 商品描述在框的左上角 -->
      <div class="description-heading">商品描述:</div>
    </div>
    <div class="description-row">
      <div class="description-item">
        <div class="item-text">
          <span>商品名称：</span>
          <span class="item-text2">{{ product.name }}</span>
        </div>
      </div>
      <div class="description-item">
        <div class="item-text">
          <span>品种：</span>
          <span class="item-text2">{{ product.variety }}</span>
        </div>
      </div>
    </div>
    <div class="description-row">
      <div class="description-item">
        <div class="item-text">
          <span>上架日期：</span>
          <span class="item-text2">2023.9.10</span>
        </div>
      </div>
      <div class="description-item">
        <div class="item-text">
          <span>类别：</span>
          <span class="item-text2">{{ product.type }}</span>
        </div>
      </div>
    </div>
    <div class="description-row">
      <div class="description-item">
        <div class="item-text">
          <span>商品毛重：</span>
          <span class="item-text2">{{ product.weight }}</span>
        </div>
      </div>
      <div class="description-item">
        <div class="item-text">
          <span>储存条件：</span>
          <span class="item-text2">{{ product.store }}</span>
        </div>
      </div>
    </div>
    <div class="description-row">
      <div class="description-item">
        <div class="item-text">
          <span>其他信息：</span>
          <MyEdForLook :productId="$route.params.productId"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import MyEdForLook from "./MyEdForLook.vue";
export default {
    data() {
        return {
            product: {
                name: "",
                price: "",
                type: "",
                variety: "",
                weight: "",
                store: "",
                description: "",
                // Add more properties as needed
            },
        };
    },
    created() {
        const productId = this.$route.params.productId;
        console.log("Product ID:", productId);
        // 构建完整的URL
        const url = `http://localhost:8080/goods/getGoodDetails/${productId}`;
        // 发起HTTP请求获取数据
        axios
            .get(url)
            .then((response) => {
            console.log("成功获取数据：", response.data);
            this.product.name = response.data.data.name;
            this.product.price = response.data.data.price;
            this.product.type = response.data.data.type;
            this.product.variety = response.data.data.variety;
            this.product.weight = response.data.data.weight;
            this.product.store = response.data.data.store;
            this.product.description = response.data.data.description;
            // 更新其他属性以匹配您的数据结构
        })
            .catch((error) => {
            console.error("获取数据时出错：", error);
        });
    },
    components: { MyEdForLook }
};
</script>
<style lang="less" scoped>
.g-container {
  border: 1px solid #333;
  border-radius: 10px;
  background-color: #d5d5d5;
  padding: 5px;
  width: 1180px;
  margin: 0 auto;
}

.product-description {
  text-align: left;
  font-weight: bold;
  margin-bottom: 10px;
  font-size: 24px;
}

.description-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 2px;
}

.description-item {
  flex: 1;
  padding: 10px;
  margin-left: 200px;
  text-align: center;
  display: flex;
  flex-direction: column; /* 垂直排列子元素 */
  justify-content: center; /* 子元素垂直居中对齐 */
  margin-bottom: 10px;
  font-size: 24px;
}

.item-text {
  display: flex;
  align-items: flex-start; /* 字头对齐，内容垂直居中对齐 */
}

.item-text span {
  margin-right: 5px; /* 适当的间距 */
}

.item-text2 {
  font-weight: normal; /* 内容文本不加粗 */
}
</style>