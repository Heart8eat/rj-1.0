<template>
    <div class="B-contanier">
        <el-row>
             <el-col :span="24">
                <div class="head">
                    由“商家一”为您配送
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <div class="grid-content bg-purple">
                    <img src="../assets/images/xigua.jpg" alt="" class="image">
                </div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content bg-purple-light">
                    数量？
                    方式？
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
                <div class="sp-text">
                    <p class="spName">麒麟西瓜 1粒装</p>
                    <p>单果2.5KG起 生鲜水果</p>
                </div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content bg-purple-light">
                    总计：<span>￥39.9</span>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<style lang="less" scoped>
.image{
    width: 300px;
    height: 200px;
    padding: 20px;
}
</style>