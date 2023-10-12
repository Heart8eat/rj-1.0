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
        <div class="description-row">
            <div class="description-item">
                <div class="item-text">
                   <span>商品产地：</span>
                    <span class="item-text2">中国大陆</span>
                </div>
            </div>
            <div class="description-item">
                <div class="item-text">
                   <span>国产/进口：</span>
                    <span class="item-text2">国产</span>
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
</script>

<style lang="less" scoped>
.g-container {
    border: 2px solid #333; /* 圆角框的样式 */
    border-radius: 10px; /* 圆角边框 */
    background-color: #f1efef;
    padding: 30px; /* 内边距 */
    margin-top: 20px;
    width: 1180px; /* 设置容器的宽度 */
    margin: 0 auto; /* 居中 */
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
  text-align: center;
  font-weight: bold;
}

</style>