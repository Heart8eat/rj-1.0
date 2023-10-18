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
                                        <div v-if="x === 0" class="replenish-msg">
                                        </div>
                                        <router-link v-else to="/goods">
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
        return {
            x:2, // 初始化为0，表示没有西瓜
        }
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
        width: 199px;
        height: 189px;
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
.replenish-msg {
  position: relative; /* 设置相对定位，以便子元素可以绝对定位 */
  width: 100px; /* 设置圆形的宽度 */
  height: 80px; /* 设置圆形的高度 */
  background-color: #ccc; /* 灰底色 */
  border: 2px solid #000; /* 黑边框 */
  border-radius: 50%; /* 将元素变成圆形 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  color: #000; /* 文本颜色 */
  font-weight: bold; /* 加粗字体 */
  text-align: center; /* 文本居中 */
}

.replenish-msg::before {
  content: '补货中'; /* 设置显示的文本内容 */
}
</style>