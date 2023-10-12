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
                        <!-- 左半边内容 -->
                        <books-left />
                    </el-col>
                    <el-col :span="8"> 
                        <!-- 右半边内容 -->
                    </el-col>
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
        BooksLeft,
        
    },
    data() {
        return {}
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
    background-color: aquamarine;
}
</style>