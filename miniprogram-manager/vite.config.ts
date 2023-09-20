import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import VueSetupExtend from 'vite-plugin-vue-setup-extend';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';
export default defineConfig({
	base: './',
	plugins: [
		vue(),
		VueSetupExtend(),
		AutoImport({
			resolvers: [ElementPlusResolver()]
		}),
		Components({
			resolvers: [ElementPlusResolver()]
		})
	],
	server: {
		https: false, // 是否开启 https
		open: false, // 是否自动在浏览器打开
		cors: true, // 允许跨域  8月更新
		port: 3000, // 端口号
		host: "127.0.0.1",
		proxy: {
		  "/api": {
			target: "http://127.0.0.1:8081/", // 后台接口
			changeOrigin: true,
			secure: false, // 如果是https接口，需要配置这个参数
			// ws: true, //websocket支持
			rewrite: (path) => path.replace(/^\/api/, ""),
		  },
		}
	  },
	optimizeDeps: {
		include: ['schart.js']
	}
});
