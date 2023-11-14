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
      <el-link href="/salerindex" :underline="false"
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
        <h1 class="word" style="margin: 0">我的订单</h1>
      </div>
    </el-header>
    <el-container>
      <el-main style="justify-content: center; display: flex; padding: 0 10px">
        <ul class="infinite-list" style="overflow: auto; padding: 0 5px">
          <li
            style="width: 1400px"
            v-for="order in orderslist"
            :key="order.order_id"
          >
          <el-container style="display: flex" v-if="order.status == '0'">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" :src="bindsrc(order.image)"
              /></el-aside>
              <el-container style="flex: 3; display: inline-block">
                <h3 style="color: red">No.{{ order.order_id }}</h3>
                <h3>
                  {{ order.name }}
                </h3>
                <div style="display: flex; align-items: flex-start">
                  <h3 style="margin-left: 0; margin-top: 24px">配送信息:</h3>
                  <el-tag
                    class="tag1"
                    style="margin-left: 20px; margin-top: 12px"
                    >{{ order.buyer.name }} {{ order.buyer.phone }}
                    {{ order.buyer.address }}
                  </el-tag>
                </div>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 2;
                  flex-direction: column;
                "
              >
                <p>
                  支付方式：<span v-bind:style="{ fontWeight: 'bold' }">{{
                    order.pay
                  }}</span>
                </p>
                <p style="margin-top: 0;">总计：<span v-bind:style="{ fontWeight: 'bold' }" style="color: red;">多少钱</span></p>
                <div style="display: inline">
                  <el-button
                    class="button2"
                    type="info"
                    @click="submitorder(order.order_id, 3)"
                    >取消</el-button
                  >
                  <el-button
                    class="button1"
                    type="primary"
                    @click="submitorder(order.order_id, 1)"
                    >接受订单</el-button
                  >
                </div>
              </el-aside>
            </el-container>
            <el-container style="display: flex" v-if="order.status == '1'">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" :src="bindsrc(order.image)"
              /></el-aside>
              <el-container style="flex: 3; display: inline-block">
                <h3 style="color: red">No.{{ order.order_id }}</h3>
                <h3>
                  {{ order.name }}
                </h3>
                <div style="display: flex; align-items: flex-start">
                  <h3 style="margin-left: 0; margin-top: 24px">配送信息:</h3>
                  <el-tag
                    class="tag1"
                    style="margin-left: 20px; margin-top: 12px"
                    >{{ order.buyer.name }} {{ order.buyer.phone }}
                    {{ order.buyer.address }}
                  </el-tag>
                </div>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                  flex-direction: column;
                "
              >
                <p>
                  支付方式：<span v-bind:style="{ fontWeight: 'bold' }">{{
                    order.pay
                  }}</span>
                </p>
                <p style="margin-top: 0;">总计：<span v-bind:style="{ fontWeight: 'bold' }" style="color: red;">多少钱</span></p>
                <el-button
                  class="button1"
                  type="primary"
                  @click="submitorder(order.order_id, 2)"
                  >完成交易</el-button
                >
              </el-aside>
            </el-container>
            <el-container style="display: flex" v-if="order.status == '2'">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" :src="bindsrc(order.image)"
              /></el-aside>
              <el-container style="flex: 3; display: inline-block">
                <h3 style="color: red">No.{{ order.order_id }}</h3>
                <h3>
                  {{ order.name }}
                </h3>
                <div style="display: flex; align-items: flex-start">
                  <h3 style="margin-left: 0; margin-top: 24px">配送信息:</h3>
                  <el-tag
                    class="tag1"
                    style="margin-left: 20px; margin-top: 12px"
                    >{{ order.buyer.name }} {{ order.buyer.phone }}
                    {{ order.buyer.address }}
                  </el-tag>
                </div>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                  flex-direction: column;
                "
              >
                <p>
                  支付方式：<span v-bind:style="{ fontWeight: 'bold' }">{{
                    order.pay
                  }}</span>
                </p>
                <p style="margin-top: 0;">总计：<span v-bind:style="{ fontWeight: 'bold' }" style="color: red;">多少钱</span></p>
                <el-tag class="tag2">已完成</el-tag>
              </el-aside>
            </el-container>
            <el-container style="display: flex" v-if="order.status == '3'">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" :src="bindsrc(order.image)"
              /></el-aside>
              <el-container style="flex: 3; display: inline-block">
                <h3 style="color: red">No.{{ order.order_id }}</h3>
                <h3>
                  {{ order.name }}
                </h3>
                <div style="display: flex; align-items: flex-start">
                  <h3 style="margin-left: 0; margin-top: 24px">配送信息:</h3>
                  <el-tag
                    class="tag1"
                    style="margin-left: 20px; margin-top: 12px"
                    >{{ order.buyer.name }} {{ order.buyer.phone }}
                    {{ order.buyer.address }}
                  </el-tag>
                </div>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                  flex-direction: column;
                "
              >
                <p>
                  支付方式：<span v-bind:style="{ fontWeight: 'bold' }">{{
                    order.pay
                  }}</span>
                </p>
                <p style="margin-top: 0;">总计：<span v-bind:style="{ fontWeight: 'bold' }" style="color: red;">多少钱</span></p>
                <el-tag class="tag2">订单已取消</el-tag>
              </el-aside>
            </el-container>
          </li>
        </ul>
      </el-main>
      <el-footer>
        <h2 style="float: right;margin-right: 20px;">总收入：<span style="color: red;">{{  }}</span></h2>
      </el-footer>
    </el-container>
  </el-container>
</template>
  
  <script>
import Cookie from "js-cookie";
import { getmyorders } from "@/api/orders";
import { orderstatus } from "@/api/orders";
export default {
  data() {
    return {
      count: 0,
      orderslist: [],
    };
  },
  methods: {
    //刷新页面
    refresh() {
      location.reload();
    },
    //修改订单状态
    submitorder(id, status) {
      const token = Cookie.get("token");
      orderstatus(token, status, id).then((res) => {
        //console.log(res)
      });
      switch (status) {
        case 1:
          this.$message({
            message: "订单已接受",
            type: "success",
          });
          setTimeout(() => this.refresh(), 1000);
          break;
        case 2:
          this.$message({
            message: "交易已完成",
            type: "success",
          });
          setTimeout(() => this.refresh(), 1000);
          break;
        case 3:
          this.$message({
            message: "交易已取消",
            type: "success",
          });
          setTimeout(() => this.refresh(), 1000);
          break;
      }
    },
    bindsrc(img) {
      return require("../picture/" + img);
    },
  },
  mounted() {
    const token = Cookie.get("token");
    getmyorders(token).then((data) => {
      this.orderslist = data.data.data;
      //console.log(this.orderslist);
    });
  },
};
</script>
  
  <style scoped>
ul li {
  border-radius: 20px;
  /* height: 200px; */
  background-color: rgba(114, 181, 255, 0.66);
  list-style-type: none;
  margin-top: 20px;
  padding: 20px;
  align-items: center;
}
.img1 {
  /* margin-left: 20px; */
  max-height: 150px;
  max-width: 200px;
}
.row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.tag1 {
  display: flex;
  height: 50px;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  padding-left: 40px;
  padding-right: 40px;
  border: 3px solid #268cfd;
  color: #268cfd;
  background-color: white;
  border-radius: 5px;
}
.tag2{
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  height: 50px;
  font-size: 18px;
  border: 3px solid #268cfd;
  color: #268cfd;
  background-color: white;
  border-radius: 5px;
}
.button1 {
  width: 150px;
  height: 50px;
  font-size: 18px;
}
.button2 {
  width: 100px;
  height: 50px;
  font-size: 18px;
}
</style>