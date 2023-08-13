import { fileURLToPath, URL} from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite'
import { VantResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 8080,
    host: "0.0.0.0"
  },

  plugins: [
    vue(),
    Components({ resolvers: [VantResolver()] })
  ],

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },

  // devServer: {
  //   proxy: {
  //    	// 属性名可以是自定义的一个端口目录，为了让代理服务器区分是请求本地服务器还是目标服务器
  //     '/user': { 
  //       target: 'http://localhost:8081', //这里写请求目标网站的协议+域名（IP）+端口，默认是80端口
  //       changeOrigin: true,
  //       pathRewrite: {
  //         '^/user': '' // 自动去除/prefix这个自定义的端口目录前缀，这样在不修改后端api的情况下可以做到前端的api管理
  //       }
  //     }
  //   },
  // }

})


