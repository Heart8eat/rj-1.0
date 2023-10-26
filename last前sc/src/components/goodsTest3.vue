<template>
    <div class="info-bottom">
        <div class="middle-images">
            <!-- 三张小图片并行排列 -->
            <img src="../assets/images/西瓜详情3.jpg" alt="" class="image">
            <img src="../assets/images/西瓜详情1.jpg" alt="" class="image">
            <img src="../assets/images/西瓜详情4.jpg" alt="" class="image">
        </div>
    </div>
</template>
<style lang="less" scoped>
.middle-images {
    display: flex;
    justify-content: space-between;
    margin-top: 5px;
}

.image {
    width: 445px; /* 设置图片的宽度 */
    height: 643px; /* 设置图片的高度 */
}
</style>