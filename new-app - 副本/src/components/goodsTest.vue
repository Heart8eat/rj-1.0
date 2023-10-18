<template>
    <div class="g-container">
        <div class="product-description">
            <!-- 商品描述在框的左上角 -->
            <div class="description-heading">
                商品描述:
            </div>
        </div>
        <div class="description-row">
            <div class="description-item">
                <div class="item-text">
                   <span>商品名称：</span>
                    <span class="item-text2">鲜生麒麟西瓜</span>
                </div>
            </div>
            <div class="description-item">
                <div class="item-text">
                   <span>品种：</span>
                    <span class="item-text2">麒麟西瓜</span>
                </div>
            </div>
        </div>
        <div class="description-row">
            <div class="description-item">
                <div class="item-text">
                   <span>上架日期：</span>
                    <span class="item-text2">2023.9.10</span>
                </div>
            </div>
            <div class="description-item">
                <div class="item-text">
                   <span>类别：</span>
                    <span class="item-text2">西瓜</span>
                </div>
            </div>
        </div>
        <div class="description-row">
            <div class="description-item">
                <div class="item-text">
                   <span>商品毛重：</span>
                    <span class="item-text2">2.7kg</span>
                </div>
            </div>
            <div class="description-item">
                <div class="item-text">
                   <span>储存条件：</span>
                    <span class="item-text2">控温</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return {}
      }
}
</script><style lang="less" scoped>
.g-container {
    border: 2px solid #333;
    border-radius: 10px;
    background-color: #D5D5D5;
    padding: 30px;
    width: 1180px;
    margin: 0 auto;
}

.product-description {
    text-align: left;
    font-weight: bold;
    margin-bottom: 10px;
    font-size: 24px;
}

.description-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.description-item {
    flex: 1;
    padding: 10px;
    margin-left: 200px;
    text-align: center;
    display: flex;
    flex-direction: column; /* 垂直排列子元素 */
    justify-content: center; /* 子元素垂直居中对齐 */
    margin-bottom: 10px;
    font-size: 24px;
}

.item-text {
    display: flex;
    align-items: flex-start; /* 字头对齐，内容垂直居中对齐 */
}

.item-text span {
    margin-right: 5px; /* 适当的间距 */
}

.item-text2 {
    font-weight: normal; /* 内容文本不加粗 */
}
</style>