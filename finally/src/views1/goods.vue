<template>
    <div>
        <el-container>
            <el-header class="custom-header"> <!-- 添加一个类名以便样式设置 -->
                <goodsHeader />
            </el-header>
            <el-row>
                <el-col :span="6">
                    <div class="GAisde">
                        <goods-aside :productId="$route.params.productId"/>
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="GMain">
                       <goods-test :productId="$route.params.productId"/>
                        <!-- <goods-test-3 /> -->
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
import GoodsTest3 from '@/components/goodsTest3.vue'
export default {
    data() {
        return {}
    },
    components:{
        goodsHeader,
        GoodsAside,
        GoodsTest,
        GoodsTest3,
    },
    created() {
        const productId = this.$route.params.productId;
        console.log('Product ID:', productId);
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
