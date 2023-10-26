<template>
    <div class="image-with-text">
      <div class="image-row">
        <div class="image-column">
          <div class="image-container">
            <img src="../assets/images/xigua4.jpg" alt="Image 1" />
            <div class="text-overlay">
              <div class="red-circle">
                <p class="centered-text">香甜多汁</p>
              </div>
            </div>
          </div>
          <p class="bottom-text">外皮切开后色泽动人，红艳艳的果肉，让人认不准想吃上一口。</p>
        </div>
        <div class="image-column">
          <div class="image-container">
            <img src="../assets/images/xigua5.jpg" alt="Image 2" />
            <div class="text-overlay">
              <div class="red-circle">
                <p class="centered-text">果肉细腻</p>
              </div>
            </div>
          </div>
          <p class="bottom-text">肉质细腻，香甜爽口，脆甜中微带沙沙感，美味鲜嫩，口口爆汁。</p>
        </div>
      </div>
    </div>
  </template>
  
  <style scoped>
  .image-with-text {
    text-align: center;
  }
  
  .image-row {
    display: flex;
    justify-content: center;
  }
  
  .image-column {
    text-align: center;
    margin: 10px 5px; /* 添加一些间距 */
  }
  
  .image-container {
    position: relative;
    display: inline-block;
  }
  
  img {
    height: 500px;
    width: 600px;
  }
  
  .text-overlay {
    position: absolute;
    bottom: 50%;
    left: 50%;
    transform: translate(-50%, 50%);
  }
  
.text-overlay {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
}

.red-circle {
  background-color: red;
  border-radius: 50%;
  display: inline-block;
  padding: 5px;
}
.centered-text{
    font-weight: bold; /* 设置字体为黑体 */
    color:aliceblue;
}
.bottom-text {
    margin-top: 10px;
}
</style>
  