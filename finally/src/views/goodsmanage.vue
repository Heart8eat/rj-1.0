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
        <h1 class="word" style="margin: 0">商品管理</h1>
      </div>
    </el-header>
    <el-container>
      <el-main
        style="justify-content: center; display: inline-block; padding: 0 10px"
      >
        <div>
          <el-input
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="search"
            style="margin-top: 10px; width: 300px"
          >
          </el-input>
          <el-select
            v-model="type1"
            value-key="id"
            clearable
            placeholder="水果类别"
            @change="($event) => getvariety(type1.id)"
            style="width: 180px; margin-left: 15px"
          >
            <el-option
              v-for="t in types"
              :key="t.id"
              :label="t.typeName"
              :value="t"
              style="width: 180px"
            >
            </el-option>
          </el-select>
          <el-select
            v-model="variety"
            clearable
            placeholder="水果品种"
            style="width: 180px; margin-left: 15px"
          >
            <el-option
              v-for="v in varieties"
              :key="v.id"
              :label="v.varietyName"
              :value="v"
              style="width: 180px"
            >
            </el-option>
          </el-select>
          <el-button type="primary" style="margin-left: 10px" plain
            >搜索</el-button
          >
        </div>
        <ul class="infinite-list myul" style="overflow: auto; padding: 0 5px">
          <li
            style="width: 1400px"
            v-for="(good, index) in goodslist"
            :key="good.id"
          >
            <el-container style="display: flex">
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
              >
                <input
                  type="checkbox"
                  name="item"
                  :value="good.id"
                  style="width: 20px; height: 20px"
                  v-model="isChecked[index]"
                  @change="handleCheckboxChange($event, index)"
                />
              </el-aside>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 4;
                "
              >
                <img class="img1" :src="bindsrc(good.mainImage.url)"
              /></el-aside>
              <el-container style="flex: 8">
                <el-main>
                  <el-row>
                    <el-col :span="24"
                      ><h2 style="margin-top: 5px">
                        {{ good.name }}
                      </h2></el-col
                    >
                  </el-row>
                  <el-row>
                    <el-col :span="12"
                      ><h5>商品名称:{{ good.name }}</h5></el-col
                    >
                    <el-col :span="12"
                      ><h5>品种:{{ good.variety }}</h5></el-col
                    >
                  </el-row>
                  <el-row>
                    <el-col :span="12"
                      ><h5>上架日期:{{ good.shelfDate }}</h5></el-col
                    >
                    <el-col :span="12"
                      ><h5>类别:{{ good.type }}</h5></el-col
                    >
                  </el-row>
                  <el-row>
                    <el-col :span="12"
                      ><h5>重量:{{ good.weight }}</h5></el-col
                    >
                    <el-col :span="12"
                      ><h5>贮存条件:{{ good.store }}</h5></el-col
                    >
                  </el-row>
                </el-main>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 8;
                "
              >
                <el-row>
                  <el-col :span="12">
                    <span>
                      <el-tag class="tag3" type="success" effect="dark">
                        有货
                      </el-tag>
                    </span>
                  </el-col>
                  <el-col :span="12">
                    <span v-if="good.status == '1'"
                      ><el-button class="el-button1" @click="remove(good.id)"
                        >下架商品</el-button
                      ></span
                    >
                    <span v-if="good.status == '0'"
                      ><el-button class="el-button2" @click="list(good.id)"
                        >上架商品</el-button
                      ></span
                    >
                  </el-col>
                </el-row>
              </el-aside>
            </el-container>
          </li>
        </ul>
        <div>
          <el-button
            type="primary"
            style="margin-left: 1200px"
            @click="checkall"
            plain
            >全选</el-button
          >
          <el-button type="primary" @click="listmore()">上架</el-button>
          <el-button type="primary" @click="removemore()">下架</el-button>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
        
        <script>
import Cookie from "js-cookie";
import { gethistorygoodlist } from "@/api/goods";
import { goodsstatue } from "@/api/goods";
import { getlistType } from "@/api/goods";
import { getlistVariety } from "@/api/goods";
export default {
  data() {
    return {
      count: 0,
      goodslist: [],
      search: "",
      types: [],
      varieties: [],
      type1: "",
      variety: "",
      //所有选框状态
      isChecked: [],
      //被选中商品的id
      checkedid: [],
      //所有商品id
      allid: [],
    };
  },
  methods: {
    //刷新页面
    refresh() {
      location.reload();
    },
    //图片路径
    bindsrc(img) {
      return "http://localhost:8080" + img;
    },
    //全选商品
    //选取商品监听器
    handleCheckboxChange(event, index) {
      this.isChecked[index] = event.target.checked;
      if (this.isChecked[index] === true) {
        //console.log(event.target.value, this.isChecked[index]);
        this.checkedid.push(event.target.value);
        //console.log(this.checkedid);
        //console.log(this.isChecked);
      } else {
        console.log(event.target.value, this.isChecked[index]);
        this.checkedid = this.checkedid.filter(
          (item) => item !== event.target.value
        );
        //console.log(this.checkedid);
        //console.log(this.isChecked);
      }
    },
    //全选按钮
    checkall() {
      var flag = false;
      for (let i = 0; i < this.isChecked.length; i++) {
        if (this.isChecked[i] === false) {
          this.isChecked[i] = true;
          flag = true;
        }
      }
      if (flag === false) {
        //已经全选需取消全选
        for (let i = 0; i < this.isChecked.length; i++) {
          this.isChecked[i] = false;
        }
        this.checkedid = [];
      } else {
        this.checkedid = this.allid;
      }
      // console.log(this.checkedid)
      // console.log(this.isChecked)
      this.$forceUpdate();
    },
    //单个上架
    list(id) {
      const token = Cookie.get("token");
      goodsstatue(token, [id], 1).then((res) => {
        console.log(res.data);
      });
      this.$message({
        message: "商品上架成功",
        type: "success",
      });
      setTimeout(() => this.refresh(), 1000);
    },
    //多个上架
    listmore() {
      const token = Cookie.get("token");
      if (this.checkedid.length != 0) {
        goodsstatue(token, this.checkedid, 1).then((res) => {
          console.log(res.data);
        });
        this.$message({
          message: "商品上架成功",
          type: "success",
        });
        setTimeout(() => this.refresh(), 1000);
      } else {
        this.$message({
          message: "请选择要上架的商品",
          type: "error",
        });
      }
    },
    //单个下架
    remove(id) {
      const token = Cookie.get("token");
      goodsstatue(token, [id], 0).then((res) => {
        console.log(res.data);
      });
      this.$message({
        message: "商品下架成功",
        type: "success",
      });
      setTimeout(() => this.refresh(), 1000);
    },
    //多个下架
    removemore() {
      const token = Cookie.get("token");
      if (this.checkedid.length != 0) {
        goodsstatue(token, this.checkedid, 0).then((res) => {
          console.log(res.data);
        });
        this.$message({
          message: "商品下架成功",
          type: "success",
        });
        setTimeout(() => this.refresh(), 1000);
      } else {
        this.$message({
          message: "请选择要下架的商品",
          type: "error",
        });
      }
    },
    //获取品种
    getvariety(id) {
      const token = Cookie.get("token");
      getlistVariety(token, id).then((res) => {
        this.varieties = res.data.data;
      });
      this.variety="";
    },
  },
  //获取商品
  mounted() {
    const token = Cookie.get("token");
    gethistorygoodlist(token).then((data) => {
      this.goodslist = data.data.data;
      //console.log(this.goodslist);
      for (let g of this.goodslist) {
        this.allid.push(g.id);
        this.isChecked.push(false);
      }
      //console.log(this.allid)
      //console.log(this.isChecked)
    });
    getlistType(token).then((res) => {
      this.types = res.data.data;
    });
  },
};
</script>
        
<style scoped>
.el-row {
  margin-bottom: 0;
}
.el-row :last-child {
  margin-bottom: 0;
}
.myul li {
  border-radius: 20px;
  /* height: 200px; */
  background-color: rgba(114, 181, 255, 0.66);
  list-style-type: none;
  margin-top: 20px;
  padding: 10px;
  align-items: center;
}
.img1 {
  /* margin-left: 20px; */
  max-height: 150px;
  max-width: 200px;
}
.el-button1 {
  width: 200px;
  height: 86px;
  font-size: 25px;
  border: 3px solid red;
  background-color: white;
  color: red;
  border-radius: 5px;
}
.el-button2 {
  width: 200px;
  height: 86px;
  font-size: 25px;
  border: 3px solid #268cfd;
  background-color: white;
  color: #268cfd;
  border-radius: 5px;
}
.tag3 {
  width: 150px;
  height: 86px;
  text-align: center;
  font-size: 25px;
  padding: 24px 40px;
  border: 3px solid rgba(44, 194, 172, 1);
  color: rgba(44, 194, 172, 1);
  background-color: white;
  border-radius: 5px;
}
</style>