<template>
    <div>
        <el-container>
            <el-header class="custom-header"> <!-- 添加一个类名以便样式设置 -->
                <booksHeader />
            </el-header>
            <el-row>
                <el-col :span="16">
                    <span class="books-text">
                        送货清单
                    </span>
                </el-col>
                <el-col :span="8">
                    <span class="books-text">
                        配送信息
                    </span>
                </el-col>    
            </el-row>
            <el-container class="form-container">
                <el-row>
                    <el-col :span="16" class="left-side"> 
                        <books-left />
                    </el-col>
                    <el-col :span="8" class="right-side"> 
                        <button @click="openBooksRightDialog" class="dzbutton">新增收货地址</button>
                        <el-dialog :visible.sync="showBooksRightDialog" title="新增收货人信息">
                            <el-form ref="rightForm" :model="formData" label-width="80px">
                                <el-form-item label="收货人">
                                    <el-input  placeholder="请输入名称" v-model="formData.receiver" />
                                </el-form-item>
                                <el-form-item label="详细地址">
                                    <el-input placeholder="请输入地址" v-model="formData.address" />
                                </el-form-item>
                                <el-form-item label="手机号码">
                                    <el-input placeholder="请输入手机号码" v-model="formData.phnumber" />
                                </el-form-item>
                                <el-form-item label="邮箱地址">
                                    <el-input placeholder="请输入邮箱" v-model="formData.email" />
                                </el-form-item>
                            </el-form>
                                
                                <span slot="footer" class="dialog-footer">
                                    <el-button type="primary" @click="saveBooksRight = false">保存</el-button>
                                </span>
                        </el-dialog>
                    </el-col>
                </el-row>
            </el-container>
            <el-container class="buttons">
                <el-row>
                    <router-link to="/zhuye">
                        <el-button class="button1">取消</el-button>
                        <el-button :plain="true" @click="open2" type="primary" class="button2">确定下单</el-button>
                    </router-link>
                </el-row>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import BooksLeft from '@/components/booksLeft.vue';
import booksHeader from '../components/booksHeader.vue'; // 导入 booksHeader 组件
export default {
    components: {
        booksHeader,
        BooksLeft
    },
    data() {
        return {
            showBooksRightDialog: false,
            formData: {
                receiver: '', // 收货人
                address: '', // 详细地址
                phnumber:'',
                email:'',
            },
        };
  },
    methods: {
        openBooksRightDialog() {
            this.showBooksRightDialog = true;
        },
        saveBooksRight() {
            // 在这里处理表单提交逻辑
            // 可以通过 this.formData.receiver 和 this.formData.address 访问表单数据
            // 例如，可以将数据保存到数据库或执行其他操作
            // 然后关闭弹窗
            this.showBooksRightDialog = false;
        },
        open2() {
            this.$message({
            message: '恭喜你，下单成功',
            type: 'success'
            });
         }
    }
}
</script>
<style scoped>
.custom-header { /* 添加一个类名以便样式设置 */
    margin: 0;
    padding: 0;
    border-bottom: 2px solid #000000; /* 添加底部边框样式 */
}
.books-text{
    font-weight: bold;
    font-size: 24px;
    padding: 20px;
}

.form-container {
    border: 2px solid #333; /* 圆角框的样式 */
    border-radius: 10px; /* 圆角边框 */
    width: 1850px; /* 设置容器的宽度 */
    height: 500px;
    margin: 30px auto;
}
.left-side{
    background-color: #72B5FF;
    width: 1250px; /* 设置容器的宽度 */
    height: 500px;
}
.right-side{
    width:600px;
    height: 500px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
.right-side button {
  position: absolute;
  bottom: 10px; /* 调整按钮与底部的距离 */
  right: 10px; /* 调整按钮与右侧的距离 */
}
.dzbutton {
  text-align: center; /* 文字居中 */
  border-radius: 20px; /* 圆角边框 */
  border: 2px solid #007BFF; /* 蓝色边框 */
  color: #007BFF; /* 白色文字颜色 */
  padding: 15px 30px; /* 增加内边距以增加按钮的大小 */
  font-size: 20px; /* 设置按钮字体大小 */
  cursor: pointer; /* 鼠标指针形状 */
  margin-right: 200px;
  margin-bottom: 20px;
}
.buttons{
    margin-top:180px ;
    margin-left: 1400px;
}
.button1{
    width: 187px;
    height: 87px;
    margin-right: 50px;
    font-size: 24px;
}
.button2{
    width: 187px;
    height: 87px;
    font-size: 24px;
}
</style>