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
      <el-aside
        style="
          border: 1px solid #bbbbbb;
          background-color: #bfdeff;
          padding: 20px;
          display: flex;
          justify-content: center;
        "
        width="500px"
      >
        <div style="display: flex; flex-direction: column; align-items: center">
          <el-upload
            class="avatar-uploader"
            action="/goods/uoloadImg"
            :data="{ id: uid, main: 1 }"
            name="uploadFiles"
            :show-file-list="false"
            :auto-upload="false"
            :on-change="handleChange"
            :before-upload="beforeAvatarUpload"
            style="margin-top: 30px"
          >
            <img v-if="mimageUrl" :src="mimageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <h3>请上传商品主要图片</h3>
          <div style="display: flex; align-items: center">
            <div style="margin-left: 10px">
              <p>上传商品的描述图片</p>
              <p>(选填1~9张)</p>
            </div>
            <el-upload
              action="/goods/uoloadImg"
              list-type="picture-card"
              :data="{ id: uid, main: 0 }"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-change="handleChange2"
              :auto-upload="false"
              :limit="9"
            >
              <i class="el-icon-plus" style=""></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
          </div>
        </div>
      </el-aside>
      <el-main style="border: 1px solid #bbbbbb; padding: 20px 20px">
        <div>
          <div class="row">
            <h4>商品名称:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="name"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
            <h4>品种:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="variety"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>商品定价:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="price"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
            <h4>重量:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="weight"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>储存条件:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="store"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
            <h4>类别:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="type"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row" style="display: flex">
            <h4 style="align-self: flex-start">其他描述:</h4>
            <el-input
              type="textarea"
              :rows="8"
              placeholder="请输入内容"
              v-model="description"
              clearable
              style="
                width: 568px;
                margin-left: 20px;
                margin-top: 15px;
                align-self: flex-start;
              "
            ></el-input>
          </div>
          <div style="display: flex; justify-content: center">
            <el-button
              @click="submit"
              class="button1"
              type="primary"
              style="margin-right: 250px"
              >确定</el-button
            >
            <el-button class="button1" @click="Cancel">清空</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
  
<script>
import Cookie from "js-cookie";
import { create } from "@/api/goods";
import { nanoid } from "nanoid";
import { uploadImg } from "@/api/picture";
export default {
  data() {
    return {
      mimageUrl: "",
      mbaseUrl: "",
      dialogImageUrl: "",
      Urllist: [],
      Urlmain: "",
      dialogVisible: false,
      price: "",
      name: "",
      type: "",
      variety: "",
      weight: "",
      store: "",
      description: "",
      uid: "",
    };
  },
  methods: {
    //主图片
    handleChange(file, fileLists) {
      // 本地电脑路径
      this.mbaseUrl = file.name;
      //使图片显示
      this.mimageUrl = URL.createObjectURL(file.raw);
      // console.log(this.mbaseUrl);
      this.Urlmain = file;
      console.log(this.Urlmain);
    },
    //主图片
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    handleRemove(file, fileList) {
      this.Urllist = fileList;
      console.log(this.Urllist);
      //console.log(file, fileList);
    },
    handlePictureCardPreview(file, fileList) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleChange2(file, fileList) {
      this.Urllist = fileList;
      console.log(this.Urllist);
    },
    async submit() {
      //console.log("测试")
      const token = Cookie.get("token");
      if (
        this.price == "" ||
        this.name == "" ||
        this.Urlmain == null ||
        this.Urllist == null ||
        this.type == "" ||
        this.variety == "" ||
        this.weight == "" ||
        this.store == "" ||
        this.description == ""
      ) {
        this.$message.error("商品信息不能为空");
      } else {
        await uploadImg(
          token,
          this.uid,
          this.Urlmain.raw,
          this.Urllist.map((f) => f.raw)
        ).then((res) => {
          //console.log(res.data)
        });

        await create(
          token,
          this.price,
          this.name,
          this.uid,
          this.type,
          this.variety,
          this.weight,
          this.store,
          this.description
        ).then((data) => {
          //console.log(data.data);
        });

        this.$message({
          message: "发布商品成功",
          type: "success",
        });
        setTimeout(() => this.$router.push("/salerindex"), 2000);
      }
    },
    Cancel() {
      (this.mimageUrl = ""),
        (this.mbaseUrl = ""),
        (this.dialogImageUrl = ""),
        (this.Urllist = []),
        (this.dialogVisible = false),
        (this.price = ""),
        (this.name = ""),
        (this.type = ""),
        (this.variety = ""),
        (this.weight = ""),
        (this.store = ""),
        (this.description = "");
    },
  },
  mounted() {
    this.uid = nanoid();
    console.log(this.uid);
  },
};
</script>
  
<style lang="less" scoped>
.word {
  font-size: 25px;
  font-family: Roboto;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<style scoped>
.row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-left: 10px;
}

h4 {
  margin-right: 10px;
  margin-left: 100px;
}
.button1 {
  width: 105px;
  height: 60px;
  font-size: 30px;
  text-align: center;
  font-family: Roboto;
}
.el-upload--picture-card {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>