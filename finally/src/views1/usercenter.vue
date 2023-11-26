<template>
  <el-container>
    <el-header
      style="
        border: 1px solid #bbbbbb;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 0 5px;
      "
    >
      <el-link href="/zhuye" :underline="false"
        ><i class="el-icon-arrow-left" style="font-size: 40px"></i
      ></el-link>
      <div
        style="
          flex: 1;
          display: flex;
          justify-content: center;
          align-items: center;
        "
      >
        <h1 class="word" style="margin: 0">个人中心</h1>
      </div>
    </el-header>
    <div class="user-center">
      <!-- 左侧账号信息表单 -->

      <el-card class="form-container" header="账号信息">
        <el-form ref="accountForm" :model="accountForm" label-width="80px">
          <div class="actForm">
            <el-form-item class="addinfo">
              <label style="font-size: 28px">用户名：</label>
              <el-input
                v-model="accountForm.name"
                class="custom-input"
              ></el-input>
            </el-form-item>
            <el-form-item class="addinfo">
              <label style="font-size: 28px">密码：</label>
              <el-input
                v-model="accountForm.password"
                type="password"
                class="custom-input"
              ></el-input>
            </el-form-item>
          </div>
          <el-form-item>
            <el-button
              plain
              @click="cancel"
              style="margin-left: 152px; margin-top: 40px"
              class="button1"
              >重置</el-button
            >
            <el-button
              @click="save"
              style="margin-left: 102px"
              type="primary"
              class="button1"
              >确定</el-button
            >
          </el-form-item>
          <el-form-item>
            <el-button
              @click="quit"
              type="danger"
              style="margin-left: 152px; margin-top: 100px"
              class="button2"
              >退出登录</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 右侧收货地址表单 -->
      <el-card class="form-container" header="我的收货地址">
        <el-form ref="addressForm" :model="formData" label-width="80px">
          <el-form-item>
            <!-- 新增收货地址按钮 -->
          </el-form-item>
        </el-form>
        <button @click="openBooksRightDialog" class="dzbutton">
          新增收货地址
        </button>
      </el-card>
    </div>
    <el-dialog :visible.sync="showDialog" title="新增收货人信息">
      <el-form ref="rightForm" :model="formData" label-width="80px">
        <el-form-item label="收货人">
          <el-input v-model="formData.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="详细地址">
          <el-input placeholder="请输入地址" v-model="formData.address" />
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="formData.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="邮箱地址">
          <el-input v-model="formData.email" placeholder="请输入邮箱" />
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveBooksRight">保存</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>
  
<script>
import axios from "axios";
import Cookie from "js-cookie";
export default {
  data() {
    return {
      showDialog: false,
      // 账号信息表单数据
      accountForm: {
        id: "",
        name: "",
        password: "",
      },
      formData: {
        name: "", // 收货人
        address: "", // 详细地址
        phone: "",
        email: "",
      },
      gorders: {
        status: 0,
        buyerId: "",
        shopId: "630e807bdf604941b89192af2eb7396e",
      },
      // 我的收货地址表单数据
    };
  },

  methods: {
    // 新增收货地址
    openBooksRightDialog() {
      this.showDialog = true;
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
          const response = await axios.post(
            "http://localhost:8080/buyers/create",
            {
              name: this.formData.name,
              address: this.formData.address,
              phone: this.formData.phone,
              email: this.formData.email,
            }
          );
          // 请求成功，根据需要处理响应
          console.log("成功保存到数据库:", response.data);
          this.gorders.buyerId = response.data.data.id;
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
    quit() {
      Cookie.remove("token");
      this.$router.push("/loginMain");
      this.$message({
        message: "退出成功",
        type: "success",
      });
    },
    async cancel() {
      const authToken = document.cookie
        .split("; ")
        .find((row) => row.startsWith("token="))
        .split("=")[1];

      // 发起HTTP请求获取数据
      axios
        .get("http://localhost:8080/buyers/getInfo", {
          headers: {
            Authorization: `Bearer ${authToken}`,
            Accept: "application/json", // 根据服务器要求添加其他头信息
          },
        })
        .then((response) => {
          console.log("成功获取数据：", response.data);

          this.accountForm.id = response.data.data.id;
          this.accountForm.name = response.data.data.name;
          this.accountForm.password = response.data.data.password;

          // 更新其他属性以匹配您的数据结构
        })
        .catch((error) => {
          console.error("获取数据时出错：", error);
        });
    },
    async save() {
      this.$confirm("确定是否更改密码", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          const apiUrl = "http://localhost:8080/buyers/update";
          const authToken = document.cookie
            .split("; ")
            .find((row) => row.startsWith("token="))
            .split("=")[1];

          // Replace with the data you want to send in the PUT request
          const requestData = {
            name: this.accountForm.name,
            newPassword: this.accountForm.password,
          };

          // Make the PUT request using axios
          axios
            .put(apiUrl, requestData, {
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${authToken}`,
              },
            })
            .then((response) => {
              console.log("Update successful", response.data);
              this.$message({
                type: "success",
                message: "更改成功!",
              });
            })
            .catch((error) => {
              console.error("Update failed", error);
            });
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消更改密码'
          }); 
          this.cancel()
        });
    },
  },

  mounted() {
    const authToken = document.cookie
      .split("; ")
      .find((row) => row.startsWith("token="))
      .split("=")[1];

    // 发起HTTP请求获取数据
    axios
      .get("http://localhost:8080/buyers/getInfo", {
        headers: {
          Authorization: `Bearer ${authToken}`,
          Accept: "application/json", // 根据服务器要求添加其他头信息
        },
      })
      .then((response) => {
        console.log("成功获取数据：", response.data);

        this.accountForm.id = response.data.data.id;
        this.accountForm.name = response.data.data.name;
        this.accountForm.password = response.data.data.password;

        // 更新其他属性以匹配您的数据结构
      })
      .catch((error) => {
        console.error("获取数据时出错：", error);
      });
  },
};
</script>

<style scoped>
.user-center {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0; /* 重置外部容器的默认边距 */
}
.addinfo {
  margin-top: 50px;
  margin-bottom: 40px;
}
.custom-input {
  width: 274px;
  height: 80px;
  margin-right: 10px; /* 可以根据需要添加右侧边距 */
}
.actForm {
  display: flex; /* 使用Flex布局 */
  flex-direction: column; /* 垂直方向布局 */
  align-items: center; /* 水平居中 */
}
.button1 {
  width: 150px;
  height: 74px;
  font-size: 24px;
}
.button2 {
  width: 403px;
  height: 74px;
  font-size: 24px;
  background-color: red;
}
.form-container {
  width: 50%; /* 左右两侧各占据一半的宽度 */
  padding: 20px; /* 可以根据需要添加内边距 */
  border-radius: 20px; /* 添加圆角 */
  margin: 10px; /* 控制上下边距 */
  margin-left: 20px;
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
/* 让label文本居中 */
.center-label .el-form-item__label {
  text-align: center;
}
</style>