<template>
  <div>
    <p>hello</p>

    <MyEditor ref="myEditorRef" />
    <button @click="dian">
        111
    </button>
  </div>
</template>
<script>
import MyEditor from "../components/MyEditor.vue";

export default {
  data() {
    return {
    };
  },
  components: {
    MyEditor,
  },
  methods:{
    dian(){
        const myEditorInstance = this.$refs.myEditorRef;

        // 然后可以访问 MyEditor 组件的 data 中的数据
        alert(myEditorInstance.html);
    }
  }
};
</script>