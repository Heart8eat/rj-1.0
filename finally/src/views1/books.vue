<template>
  <div>
    <el-container>
      <el-header class="custom-header">
        <!-- 添加一个类名以便样式设置 -->
        <booksHeader />
      </el-header>
      <el-row>
        <el-col :span="16">
          <span class="books-text"> 送货清单 </span>
        </el-col>
        <el-col :span="8">
          <span class="books-text"> 配送信息 </span>
        </el-col>
      </el-row>
      <el-container class="form-container">
        <el-row>
          <el-col :span="16" class="left-side">
            <books-left />
          </el-col>
          <el-col :span="8" class="right-side">
            <div class="right-side-zhu">
              <div
                v-if="savedData.name || savedData.address || savedData.phone"
                class="Xx-container"
              >
                <span class="Xinxi"
                  >收货人: {{ savedData.name }}，地址:
                  {{ savedData.address }}，手机号: {{ savedData.phone }}</span
                >
              </div>
              <button @click="openBooksRightDialog" class="dzbutton">
                新增收货地址
              </button>
            </div>
            <el-dialog
              :visible.sync="showBooksRightDialog"
              title="新增收货人信息"
            >
              <el-form ref="rightForm" :model="formData" label-width="80px">
                <el-form-item label="收货人">
                  <el-input v-model="formData.name" placeholder="请输入名称" />
                </el-form-item>
                <el-form-item label="详细地址">
                  <el-input
                    placeholder="请输入地址"
                    v-model="formData.address"
                  />
                </el-form-item>
                <el-form-item label="手机号码">
                  <el-input
                    v-model="formData.phone"
                    placeholder="请输入手机号码"
                  />
                </el-form-item>
                <el-form-item label="邮箱地址">
                  <el-input v-model="formData.email" placeholder="请输入邮箱" />
                </el-form-item>
              </el-form>

              <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveBooksRight"
                  >保存</el-button
                >
              </span>
            </el-dialog>
          </el-col>
        </el-row>
      </el-container>
      <el-container class="buttons">
        <el-row>
          <router-link to="/zhuye">
            <el-button class="button1">取消</el-button>
          </router-link>
          <el-button :plain="true" @click="open2" type="primary" class="button2"
            >确定下单</el-button
          >
        </el-row>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import BooksLeft from "@/components/booksLeft.vue";
import booksHeader from "../components/booksHeader.vue"; // 导入 booksHeader 组件
import axios from "axios";
export default {
  components: {
    booksHeader,
    BooksLeft,
  },
  data() {
    return {
      showBooksRightDialog: false,
      formData: {
        name: "", // 收货人
        address: "", // 详细地址
        phone: "",
        email: "",
      },
      savedData: {
        name: "",
        address: "",
        phone: "",
      },
      gorders: {
        status: 0,
        buyerId: "",
        shopId: "630e807bdf604941b89192af2eb7396e",
      },
    };
  },
  methods: {
    openBooksRightDialog() {
      this.showBooksRightDialog = true;
    },
    async saveBooksRight() {
      if (this.formData.name === "") {
        this.$message.error("姓名不能为空");
      } else if (this.formData.phone === "") {
        this.$message.error("手机号码不能为空");
      } else if (!/^1\d{10}$/.test(this.formData.phone)) {
        this.$message.error("手机号码格式不正确");
      } else if (this.formData.email === "") {
        this.$message.error("邮箱地址不能为空");
      } else if (
        !/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(
          this.formData.email
        )
      ) {
        this.$message.error("邮箱格式不正确");
      } else {
        try {
          // 构建HTTP POST请求
          const authToken = document.cookie
            .split("; ")
            .find((row) => row.startsWith("token="))
            .split("=")[1];
          const response = await axios.post(
            "http://localhost:8080/buyers/saveAddress",
            {
              receiver: this.formData.name,
              address: this.formData.address,
              phone: this.formData.phone,
              email: this.formData.email,
              defaultAddress: this.formData.defaultAddress,
            },
            {
              headers: {
                Authorization: `Bearer ${authToken}`,
              },
            }
          );
          // 请求成功，根据需要处理响应
          console.log("成功保存到数据库:", response.data);
          this.gorders.buyerId = response.data.data.id;
          console.log("成功保存到数据库:", response.data);
          this.savedData.name = this.formData.name;
          this.savedData.address = this.formData.address;
          this.savedData.phone = this.formData.phone;
          // 关闭对话框
          this.$message({
            message: "恭喜你，保存成功",
            type: "success",
          });
          this.showBooksRightDialog = false;
        } catch (error) {
          // 请求失败，处理错误
          console.error("保存失败:", error);
          // 可以显示错误消息或采取其他适当的措施
        }
      }
    },
    async open2() {
      if (this.gorders.buyerId === "") {
        this.$message.error("地址信息不能为空");
      } else {
        try {
          console.log(this.gorders);
          const response = await axios.post(
            "http://localhost:8080/orders/newOrder",
            {
              shopId: this.gorders.shopId,
              buyerId: this.gorders.buyerId,
              goodId: this.$route.params.productId,
              quantity: 1,
              sum: 20,
            }
          );
          console.log("成功保存到数据库:", response.data);

          this.$router.push({ name: "zhuye" });
        } catch (error) {
          // 请求失败，处理错误
          console.error("保存失败:", error);
          // 可以显示错误消息或采取其他适当的措施
        }

        this.$message({
          message: "恭喜你，下单成功",
          type: "success",
        });
      }
    },
  },
};
</script>
<style scoped>
.custom-header {
  /* 添加一个类名以便样式设置 */
  margin: 0;
  padding: 0;
  border-bottom: 1px solid #000000; /* 添加底部边框样式 */
}
.books-text {
  font-weight: bold;
  font-size: 24px;
  padding: 20px;
}

.form-container {
  border: 1px solid #333; /* 圆角框的样式 */
  border-radius: 10px; /* 圆角边框 */
  width: 1850px; /* 设置容器的宽度 */
  height: 500px;
  margin: 30px auto;
}
.left-side {
  background-color: #72b5ff;
  width: 1250px; /* 设置容器的宽度 */
  height: 500px;
}
.right-side {
  width: 600px;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.Xx-container {
  width: 574px;
  height: 155px;
  border: 2px solid #000; /* 边框样式，可以根据需要更改颜色和宽度 */
  box-sizing: border-box; /* 让边框大小包括在元素的宽度和高度内 */
}
.Xinxi {
  position: absolute;
  top: 10px; /* 调整按钮与底部的距离 */
  left: 1260px; /* 调整按钮与右侧的距离 */
  border: 2px solid brown; /* 设置棕色边框 */
  border-radius: 10px; /* 添加圆角效果 */
  padding: 10px; /* 可选，添加内边距以分离文本内容和边框 */
  width: 520px;
  height: auto;
}
.right-side button {
  position: absolute;
  bottom: 10px; /* 调整按钮与底部的距离 */
  right: 10px; /* 调整按钮与右侧的距离 */
}
.dzbutton {
  text-align: center; /* 文字居中 */
  border-radius: 20px; /* 圆角边框 */
  border: 2px solid #007bff; /* 蓝色边框 */
  color: #007bff; /* 白色文字颜色 */
  padding: 15px 30px; /* 增加内边距以增加按钮的大小 */
  font-size: 20px; /* 设置按钮字体大小 */
  cursor: pointer; /* 鼠标指针形状 */
  margin-right: 200px;
  margin-bottom: 20px;
}
.buttons {
  margin-top: 180px;
  margin-left: 1400px;
}
.button1 {
  width: 187px;
  height: 87px;
  margin-right: 50px;
  font-size: 24px;
}
.button2 {
  width: 187px;
  height: 87px;
  font-size: 24px;
}
</style>