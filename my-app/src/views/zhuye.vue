<template>
    <div>
        <el-container>
            <el-header>
                <zhuyeHeader />
            </el-header>
            <el-main>
                <el-row >
                    <el-col :span="3">
                        <el-card class="box-card">
                           <div class="sp">
                                <img src="../assets/images/xigua.jpg" alt="">
                                <div class="spinfo">
                                    <div class="info-top">
                                        <p class="spName">麒麟瓜</p>
                                        <p class="price">￥39.9</p>
                                    </div>
                                    <div class="info-bottom">
                                        <router-link to="/goods">
                                            <el-button type="primary" round>立即购买</el-button>
                                         </router-link>
                                    </div>
                                </div>
                           </div>
                        </el-card>
                    </el-col>
                </el-row>       
            </el-main>
    </el-container>
    </div>
</template>
<script>
import zhuyeHeader from '../components/zhuyeHeader.vue' 
export default {
    data() {
        return {}
    },
    components:{
        zhuyeHeader
    }
}
</script>
<style scoped>
.el-header{
    height: 120px;
    margin: 0; /* 设置margin为0 */
    padding: 0; /* 设置padding为0 */
}
</style>
<style lang="less" scoped>
.sp{
    img{
        width: 200px;
        height: 200px;
    }
}
.spinfo {
  display: flex;
  flex-direction: column;
}

.info-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.price {
  color: red;
  font-weight: bold;
  font-size: 24px;
}

.priceButton {
  margin-left: auto; /* 将按钮推到右边 */
}
</style>