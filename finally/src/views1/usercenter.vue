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
        <h1 class="word" style="margin: 0">历史订单</h1>
      </div>
    </el-header>
    <div class="user-center">
      <!-- 左侧账号信息表单 -->

      <el-card class="form-container" header="账号信息">
        <el-form ref="accountForm" :model="accountForm" label-width="80px">
          <div class="actForm">
            <el-form-item class="addinfo">
              <label style="font-size: 28px;">用户名：</label>
              <el-input
                v-model="accountForm.username"
                class="custom-input"
              ></el-input>
            </el-form-item>
            <el-form-item class="addinfo">
              <label style="font-size: 28px;">密码：</label>
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
              @click="取消"
              style="margin-left: 152px; margin-top: 40px"
              class="button1"
              >取消</el-button
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
        <el-form ref="addressForm" :model="addressForm" label-width="80px">
          <el-form-item label="我的收货地址">
            <!-- 显示历史收货地址信息 -->
            <el-table :data="addressForm.addressList" style="width: 100%">
              <!-- 列定义 -->
              <el-table-column prop="address" label="地址"></el-table-column>
              <el-table-column prop="city" label="城市"></el-table-column>
              <!-- 其他收货地址字段可以根据需要添加 -->
            </el-table>
          </el-form-item>
          <el-form-item>
            <!-- 新增收货地址按钮 -->
            <el-button type="primary" @click="addAddress"
              >新增收货地址</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </el-container>
</template>
  
<script>
export default {
  data() {
    return {
      // 账号信息表单数据
      accountForm: {
        username: "啊对对对", // 你可以从后端获取用户信息填充这些字段
        password: "123456",
        // 其他账号信息字段可以根据需要添加
      },

      // 我的收货地址表单数据
      addressForm: {
        addressList: [
          { address: "地址1", city: "城市1" },
          { address: "地址2", city: "城市2" },
          // 其他收货地址字段可以根据需要添加
        ],
      },
    };
  },

  methods: {
    // 新增收货地址
    addAddress() {
      // 处理新增收货地址逻辑，可以弹出一个对话框等
    },
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
.button1{
  width: 150px;
  height: 74px;
  font-size: 24px;
}
.button2{
  width: 403px;
  height: 74px;
  font-size: 24px;
  background-color: red
}
.form-container {
  width: 50%; /* 左右两侧各占据一半的宽度 */
  padding: 20px; /* 可以根据需要添加内边距 */
  border-radius: 20px; /* 添加圆角 */
  margin: 10px; /* 控制上下边距 */
  margin-left: 20px;
}

/* 让label文本居中 */
.center-label .el-form-item__label {
  text-align: center;
}
</style>