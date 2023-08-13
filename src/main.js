import { createApp } from 'vue'
import router from '@/router'
import './style.css'
import App from './App.vue'
import { Button,Icon, Form, Field, Skeleton, Swipe, SwipeItem } from 'vant'
import 'lib-flexible/flexible'
import '@/common/style/theme.css'
import './assets/iconfont/iconfont.js'
import axios from 'axios'
import { Toast } from 'vant'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


const app = createApp(App)

app.use(router)
app
    .use(Button)
    .use(Icon)
    .use(Form)
    .use(Field)
    .use(Skeleton)
    .use(Swipe)
    .use(SwipeItem)
    .use(Toast)
    .use(ElementPlus)

app.mount('#app')
