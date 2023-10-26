const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false //关闭eslint校验

})

// module.exports = {
//   devServer: {
//     proxy: { //配置跨域
//       ws: true,
//     }
//   }
// }
