<template>
    <div>
        <el-container>
            <el-header class="custom-header"> <!-- 添加一个类名以便样式设置 -->
                <goodsHeader />
            </el-header>
            <el-row>
                <el-col :span="8">
                    <div class="GAisde">
                        <goods-aside />
                    </div>
                </el-col>
                <el-col :span="16">
                    <div class="GMain">
                       <goods-test />
                        <goods-test-2 />
                    </div>
                </el-col>    
            </el-row>
        </el-container>
    </div>
</template>
<script>
import GoodsAside from '@/components/goodsAside.vue'
import goodsHeader from '../components/goodsHeader.vue'
import GoodsTest from '@/components/goodsTest.vue'
import GoodsTest2 from '@/components/goodsTest2.vue'
export default {
    data() {
        return {}
    },
    components:{
        goodsHeader,
        GoodsAside,
        GoodsTest,
        GoodsTest2,
    }
}
</script>
<style scoped>
.custom-header { /* 添加一个类名以便样式设置 */
    margin: 0;
    padding: 0;
    border-bottom: 2px solid #000000; /* 添加底部边框样式 */
}
</style>
