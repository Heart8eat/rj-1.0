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
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            style="margin-top: 30px"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <h3>请上传商品主要图片</h3>
          <div style="display: flex; align-items: center">
            <div style="margin-left: 10px">
              <p>上传商品的描述图片</p>
              <p>(选填1~9张)</p>
            </div>
            <el-upload
              action="https://jsonplaceholder.typicode.com/posts/"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :limit="9"
            >
              <i class="el-icon-plus"></i>
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
              v-model="input1"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>商品定价:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="input2"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>商品编号:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="input3"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>商品毛重:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="input4"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div class="row">
            <h4>商品产地:</h4>
            <el-input
              placeholder="请输入内容"
              v-model="input5"
              clearable
              style="width: 200px; margin-left: 20px"
            ></el-input>
          </div>
          <div style="margin-bottom: 25px;">
            <h4>商品描述:</h4>
            <el-input
              type="textarea"
              placeholder="请输入内容"
              v-model="input6"
              clearable
              style="width: 700px; margin-left: 100px"
            ></el-input>
          </div>
          <div style="display: flex; justify-content: center">
            <el-button @click="submit" class="button1" type="primary" style="margin-right: 250px">确定</el-button>
            <el-button class="button1" >取消</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
  
<script>
export default {
  data() {
    return {
      imageUrl: "",
      dialogImageUrl: "",
      dialogVisible: false,
      input1: "",
      input2: "",
      input3: "",
      input4: "",
      input5: "",
      input6: "",
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
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
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    submit(){
      this.$router.push("/salerindex");
    }
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
.row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
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
</style>