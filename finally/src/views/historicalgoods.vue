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
        <h1 class="word" style="margin: 0">历史商品</h1>
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
            v-model="value"
            clearable
            placeholder="水果类别"
            style="width: 180px; margin-left: 15px"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              style="width: 180px"
            >
            </el-option>
          </el-select>
          <el-select
            v-model="value"
            clearable
            placeholder="水果品种"
            style="width: 180px; margin-left: 15px"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              style="width: 180px"
            >
            </el-option>
          </el-select>
          <el-button type="primary" style="margin-left: 10px" plain
            >搜索</el-button
          >
        </div>
        <ul class="infinite-list myul" style="overflow: auto; padding: 0 5px">
          <li style="width: 1400px" v-for="good in goodslist" :key="good.id">
            <el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 1;
                "
                ><img class="img1" :src="bindsrc(good.image.url)"
              /></el-aside>
              <el-main style="flex: 2">
                <!-- <el-header
                  ><h2 style="margin-top: 5px">
                    {{ good.name }}
                  </h2></el-header
                > -->
                <!-- <el-container style="display: flex">
                  <el-main
                    style="
                      padding-top: 0;
                      padding-bottom: 0;
                      padding-left: 20px;
                      flex: 1;
                    "
                  >
                    <h2 style="margin-top: 5px">
                      {{ good.name }}
                    </h2>
                    <h5>商品名称:{{ good.name }}</h5>
                    <h5>上架日期:{{ good.shelfDate }}</h5>
                    <h5>重量:{{ good.weight }}</h5>
                  </el-main>
                  <el-aside
                    style="
                      padding-top: 0;
                      padding-bottom: 0;
                      padding-left: 20px;
                      flex: 2;
                    "
                  >
                    <h5>品种:{{ good.variety }}</h5>
                    <h5>类别:{{ good.type }}</h5>
                    <h5>贮存条件:{{ good.store }}</h5>
                  </el-aside>
                </el-container> -->
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
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 2;
                "
              >
                <el-row>
                  <el-col :span="9">
                    <span>
                      <el-tag
                        class="tag3"
                        type="success"
                        effect="dark"
                      >
                        有货
                      </el-tag>
                    </span>
                  </el-col>
                  <el-col :span="15">
                    <span v-if="good.status == '1'"
                      ><el-button class="el-button1" @click="dongjie(good.id)"
                        >下架商品</el-button
                      ></span
                    >
                    <span v-if="good.status == '0'"
                      ><el-tag class="tag2" style="margin-left: 20px"
                        >商品已下架
                      </el-tag></span
                    >
                    <span v-if="good.status == '2'"
                      ><el-tag class="tag1" style="margin-left: 20px"
                        >补货中
                      </el-tag></span
                    >
                  </el-col>
                </el-row></el-aside
              >
            </el-container>
          </li>
        </ul>
      </el-main>
    </el-container>
  </el-container>
</template>
  
  <script>
import Cookie from "js-cookie";
import { gethistorygoodlist } from "@/api/goods";
import { goodstatue } from "@/api/goods";
export default {
  data() {
    return {
      count: 0,
      goodslist: [],
      search: "",
      options: [
        {
          value: "选项1",
          label: "黄金糕",
        },
        {
          value: "选项2",
          label: "双皮奶",
        },
        {
          value: "选项3",
          label: "蚵仔煎",
        },
        {
          value: "选项4",
          label: "龙须面",
        },
        {
          value: "选项5",
          label: "北京烤鸭",
        },
      ],
      value: "",
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
    //冻结商品
    dongjie(id) {
      const token = Cookie.get("token");
      goodstatue(token, id, "2").then((data) => {
        //console.log(data.data)
      });
      this.$message({
        message: "冻结商品成功",
        type: "success",
      });
      setTimeout(() => this.refresh(), 1000);
    },
  },
  //获取历史商品
  mounted() {
    const token = Cookie.get("token");
    gethistorygoodlist(token).then((data) => {
      this.goodslist = data.data.data;
      //console.log(this.goodslist);
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
  padding: 20px;
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
  font-size: 18px;
  border: 3px solid red;
  background-color: white;
  color: red;
  border-radius: 5px;
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
.tag2 {
  height: auto;
  text-align: center;
  font-size: 20px;
  padding: 10px 40px;
  border: 3px solid #96a0aa;
  color: #96a0aa;
  background-color: white;
  border-radius: 5px;
}
.tag3{
  height: auto;
  text-align: center;
  font-size: 20px;
  padding: 10px 40px;
  border: 3px solid rgba(44, 194, 172, 1);
  color: rgba(44, 194, 172, 1);
  background-color: white;
  border-radius: 5px;
}
</style>