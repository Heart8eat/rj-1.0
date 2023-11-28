<template>
  <div style="border: 1px solid #ccc">
    <Editor
      style="height: 80px;width: 800px; overflow-y: hidden"
      v-model="html"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="onCreated"
    />
  </div>
</template>
<script>
import Vue from "vue";
import { Editor } from "@wangeditor/editor-for-vue";
import axios from "axios";

export default Vue.extend({
  components: { Editor },
  data() {
    return {
      product: {
        description: "",
        // Add more properties as needed
      },
      editor: null,
      html: "<p><span style='color: rgb(216, 68, 147);'>测试</span></p>",
      toolbarConfig: {
        excludeKeys: [
          "group-image",
          "group-video",
          "insertTable",
          "todo",
          "codeBlock",
        ],
      },
      editorConfig: {
        placeholder: "请输入内容...",
        readOnly: true,
        scroll: true, //配置编辑器是否支持滚动，默认为 true 。注意，此时不要固定 editor-container 的高度，设置一个 min-height 即可。
      },
      mode: "default", // or 'simple'
    };
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor); // 一定要用 Object.seal() ，否则会报错
    },
  },
  mounted() {
    // 模拟 ajax 请求，异步渲染编辑器
    setTimeout(() => {
      console.log(this.html);
    }, 1500);
  },
  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy(); // 组件销毁时，及时销毁编辑器
  },
  created() {
    const productId = this.$route.params.productId;
    console.log("Product ID:", productId);
    // 构建完整的URL
    const url = `http://localhost:8080/goods/getGoodDetails/${productId}`;

    // 发起HTTP请求获取数据
    axios
      .get(url)
      .then((response) => {
        console.log("成功获取数据：", response.data);

        this.product.description = response.data.data.description;
        //<p><span style="color: rgb(216, 68, 147);">hello</span></p>
        // 更新其他属性以匹配您的数据结构
      })
      .catch((error) => {
        console.error("获取数据时出错：", error);
      });
  },
});
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>