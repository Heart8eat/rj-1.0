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
        <h1 class="word" style="margin: 0">发布商品</h1>
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
            style="width: 180px;margin-left: 15px;"
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
            style="width: 180px;margin-left: 15px;"
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
                <img class="img1" :src="bindsrc(good.image.url)"
              /></el-aside>
              <el-container style="flex: 12">
                <el-header
                  ><h2 style="margin-top: 5px">
                    {{ good.name }}
                  </h2></el-header
                >
                <el-container style="display: flex">
                  <el-main
                    style="
                      padding-top: 0;
                      padding-bottom: 0;
                      padding-left: 20px;
                      flex: 1;
                    "
                  >
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
                </el-container>
              </el-container>
              <el-aside
                style="
                  align-items: center;
                  justify-content: center;
                  display: flex;
                  flex: 4;
                "
              >
                <el-button class="el-button1" type="danger" plain
                  >发布商品</el-button
                >
              </el-aside>
            </el-container>
          </li>
        </ul>
        <div>
          <el-button
            type="primary"
            style="margin-left: 1300px"
            @click="checkall"
            plain
            >全选</el-button
          >
          <el-button type="primary">发布</el-button>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
      
      <script>
import Cookie from "js-cookie";
import { getpublishgoodlist } from "@/api/goods";

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
      isChecked: [],
      checkedid: [],
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
        console.log(this.checkedid);
        console.log(this.isChecked);
      } else {
        console.log(event.target.value, this.isChecked[index]);
        this.checkedid = this.checkedid.filter(
          (item) => item !== event.target.value
        );
        console.log(this.checkedid);
        console.log(this.isChecked);
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
        this.checkedid=[];
      }
      else{
        this.checkedid = this.allid;
      }
      // console.log(this.checkedid)
      // console.log(this.isChecked)
      this.$forceUpdate();
    },
  },
  //获取历史商品
  mounted() {
    const token = Cookie.get("token");
    getpublishgoodlist(token).then((data) => {
      this.goodslist = data.data.data;
      console.log(this.goodslist);
      for (let g of this.goodslist) {
        this.allid.push(g.id);
        this.isChecked.push(false);
      }
      //console.log(this.allid)
      //console.log(this.isChecked)
    });
  },
};
</script>
      
      <style scoped>
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
</style>