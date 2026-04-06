# 技术栈
## 🔹 前端技术
Vue.js 2.6 - 渐进式JavaScript框架<br>
Vue Router 3.5 - 前端路由管理<br>
Vuex 3.0 - 状态管理（配合持久化插件）<br>
Element UI 2.15 - UI组件库<br>
Axios 1.13 - HTTP请求封装<br>
🔹 后端技术<br>
Spring Boot 4.0.2 - 后端框架<br>
MyBatis-Plus 3.5.15 - ORM持久层框架<br>
MySQL - 关系型数据库<br>
JWT (JJWT 0.11.5) - Token认证机制<br>
Swagger - API接口文档<br>
Lombok - 简化Java代码<br>
Maven - 项目构建工具<br>
🔹 开发环境<br>
JDK 17<br>
Node.js (前端运行环境)<br>
端口配置: 后端8090，前端默认8080<br>


# 登录页面

![image-20260404224542060](note_img/image-20260404224542060.png)

***

# 个人首页

![image-20260404224600612](note_img/image-20260404224600612.png)

***

# 管理员页面

![image-20260404224624511](note_img/image-20260404224624511.png)

***

# 物品管理页面

![image-20260404224642347](note_img/image-20260404224642347.png)

# 一些功能的展示

## 1. 首页右上角名片

![image-20260404224944592](note_img/image-20260404224944592.png)

## 2. 首页诗词-时间模块

![image-20260404225008365](note_img/image-20260404225008365.png)

## 3. 物品筛选功能

![image-20260404225044840](note_img/image-20260404225044840.png)

## 4. 侧边栏伸缩功能

![image-20260404225109699](note_img/image-20260404225109699.png)

## 5.登录功能&退出登录功能

![image-20260404225231447](note_img/image-20260404225231447.png)

![image-20260404225251103](note_img/image-20260404225251103.png)

# 2026年4月6日 增加功能



# add配置前后端JWT校验

## 1 新建 Axios 拦截器文件（唯一新增文件）

创建 src/utils/request.js：

```
import axios from 'axios'
import { Message } from 'element-ui'
import router from '@/router'

const service = axios.create({
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    const token = sessionStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const res = response.data
    
    if (res.code === 401) {
      Message.error('登录已过期，请重新登录')
      sessionStorage.clear()
      router.push('/')
      return Promise.reject(new Error('未授权'))
    }
    
    return response
  },
  error => {
    if (error.response && error.response.status === 401) {
      Message.error('未授权，请重新登录')
      sessionStorage.clear()
      router.push('/')
    } else {
      Message.error(error.message || '网络错误')
    }
    return Promise.reject(error)
  }
)

export default service

```

add

![image-20260406194010395](note_img/image-20260406194010395.png)

add

![image-20260406194156033](note_img/image-20260406194156033.png)

add

![image-20260406194417795](note_img/image-20260406194417795.png)

add

![image-20260406194506343](note_img/image-20260406194506343.png)


