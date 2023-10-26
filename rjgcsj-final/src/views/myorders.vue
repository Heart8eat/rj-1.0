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
          <!-- <li style="width: 1400px">
            <el-container style="display: flex">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" src="../picture/xigua1.jpg"
              /></el-aside>
              <el-container style="flex: 3">
                <el-header
                  ><h2 style="margin-top: 5px">
                    麒麟西瓜 1粒装 单果2.5kg起 生鲜水果
                  </h2></el-header
                >
                <el-main style="display: flex">
                  <div style="display: flex; align-items: flex-start">
                    <h4 style="margin-left: 0; margin-top: 0">配送信息:</h4>
                    <el-tag class="tag1" style="margin-left: 20px"
                      >张三 13327518405<br />浙江省杭州市钱塘新区浙江工商大学
                    </el-tag>
                  </div>
                </el-main>
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
                  支付方式:<span v-bind:style="{ fontWeight: 'bold' }"
                    >货到付款</span
                  >
                </p>
                <el-button class="button1" type="primary">完成交易</el-button>
              </el-aside>
            </el-container>
          </li>
          <li style="width: 1400px">
            <el-container style="display: flex">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" src="../picture/xigua.png"
              /></el-aside>
              <el-container style="flex: 2">
                <el-main style="display: flex; align-items: center"
                  ><h2 style="margin-top: 5px">
                    麒麟西瓜 1粒装 单果2.5kg起 生鲜水果
                  </h2></el-main
                >
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
                <el-tag class="tag1" style="margin-left: 20px"
                  >订单已完成，信息已隐藏
                </el-tag>
              </el-aside>
            </el-container>
          </li> -->
          <li style="width: 1400px" v-for="order in orderslist" :key="order.order_id">
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
              <el-container style="flex: 3">
                <el-header
                  ><h2 style="margin-top: 5px">
                    {{ order.name }}
                  </h2></el-header
                >
                <el-main style="display: flex">
                  <div style="display: flex; align-items: flex-start">
                    <h4 style="margin-left: 0; margin-top: 0">配送信息:</h4>
                    <el-tag class="tag1" style="margin-left: 20px"
                      >{{ order.buyer.name }} {{ order.buyer.phone }}<br />{{ order.buyer.address }}
                    </el-tag>
                  </div>
                </el-main>
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
                  支付方式:<span v-bind:style="{ fontWeight: 'bold' }"
                    >{{ order.pay }}</span
                  >
                </p>
                <el-button class="button1" type="primary" @click="submitorder(order.order_id)">完成交易</el-button>
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
              <el-container style="flex: 2">
                <el-main style="display: flex; align-items: center"
                  ><h2 style="margin-top: 5px">
                    {{ order.name }}
                  </h2></el-main
                >
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
                <el-tag class="tag1" style="margin-left: 20px"
                  >订单已完成，信息已隐藏
                </el-tag>
              </el-aside>
            </el-container>
          </li>
        </ul>
      </el-main>
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
      orderslist:[],
    };
  },
  methods:{
    //刷新页面
    refresh() {
      location.reload();
    },
    //修改订单状态
    submitorder(id){
      const token = Cookie.get("token");
      orderstatus(token,1,id).then((res)=>{
        //console.log(res)
      })
      this.$message({
        message: "交易已完成",
        type: "success",
      });
      setTimeout(() => this.refresh(), 1000);
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
  
  <style>
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
  height: auto;
  text-align: center;
  font-size: 20px;
  padding: 10px 40px;
  border: 3px solid #268cfd;
  color: #268cfd;
  background-color: white;
  border-radius: 5px;
}
.button1 {
  width: 150px;
  height: 70px;
  font-size: 18px;
}
</style>