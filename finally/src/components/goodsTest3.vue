<template>
  <el-carousel :interval="4000" type="card" height="480px">
    <el-carousel-item v-for="(item, index) in image" :key="index">
      <img :src="bindsrc(item.url)" :alt="`图片 ${index + 1}`" :width="item.width" :height="item.height" />
    </el-carousel-item>
  </el-carousel>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      image:[],
    };
  },
  methods: {
    bindsrc(img) {
      console.log("url:", img);
      return "http://localhost:8080" + img;
    },
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
        this.image=response.data.data.image;
        console.log("图：", response.data.data.image);
        // 更新其他属性以匹配您的数据结构
      })
      .catch((error) => {
        console.error("获取数据时出错：", error);
      });
  },
};
</script>
  <style>

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>