# 三. Vue 2

## 1. Vue 基础

### 1) 环境准备

#### 安装脚手架

```
npm install -g @vue/cli
```

* -g 参数表示全局安装，这样在任意目录都可以使用 vue 脚本创建项目

#### 创建项目

```cmd
vue ui
```

使用图形向导来创建 vue 项目，如下图，输入项目名

![image-20220815141136895](D:\2022.js\imgs\image-20220815141136895.png)

选择手动配置项目

![image-20220815141312244](D:\2022.js\imgs\image-20220815141312244.png)

添加 vue router 和 vuex

![image-20220815141412380](D:\2022.js\imgs\image-20220815141412380.png)

选择版本，创建项目

![image-20220815141459878](D:\2022.js\imgs\image-20220815141459878.png)

#### 安装 devtools

* devtools 插件网址：https://devtools.vuejs.org/guide/installation.html

![image-20220815141648040](D:\2022.js\imgs\image-20220815141648040.png)

#### 运行项目

进入项目目录，执行

```cmd
npm run serve
```

#### 修改端口

前端服务器默认占用了 8080 端口，需要修改一下

* 文档地址：[DevServer | webpack](https://webpack.js.org/configuration/dev-server/#devserverport)

* 打开 vue.config.js 添加

  ```js
  const { defineConfig } = require('@vue/cli-service')
  module.exports = defineConfig({
    
    // ...
      
    devServer: {
      port: 7070
    }
    
  })
  ```

#### 添加代理

为了避免前后端服务器联调时， fetch、xhr 请求产生跨域问题，需要配置代理

* 文档地址同上

* 打开 vue.config.js 添加

  ```js
  const { defineConfig } = require('@vue/cli-service')
  module.exports = defineConfig({
      
    // ...
      
    devServer: {
      port: 7070,
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
          changeOrigin: true
        }
      }
    }
      
  })
  ```

  

#### Vue 项目结构

```
PS D:\2022.js\代码\第3章\client> tree src
D:\2022.JS\代码\第3章\CLIENT\SRC
├─assets
├─components
├─router
├─store
└─views
```

* assets - 静态资源
* components - 可重用组件
* router - 路由
* store - 数据共享
* views - 视图组件

以后还会添加

* api - 跟后台交互，发送 fetch、xhr 请求，接收响应
* plugins - 插件



### 2) Vue 组件

Vue 的组件文件以 .vue 结尾，每个组件由三部分组成

```vue
<template></template>

<script></script>

<style></style>
```

* template 模板部分，由它生成 html 代码
* script 代码部分，控制模板的数据来源和行为
* style 样式部分，一般不咋关心



入口组件是 App.vue

解释

* export default 导出组件对象，供 main.js 导入使用
* 这个对象有一个 data 方法，返回一个**对象**，给 template 提供数据
* `{{}}` 在 Vue 里称之为插值表达式，用来**绑定** data 方法返回的**对象**属性，**绑定**的含义是数据发生变化时，页面显示会同步变化



#### 文本插值


* `{{}}` 里只能绑定一个属性，绑定多个属性需要用多个 `{{}}` 分别绑定
* template 内只能有一个根元素
* 插值内可以进行简单的表达式计算



#### 属性绑定

* 简写方式：可以省略 v-bind 只保留冒号



#### 事件绑定

* 简写方式：可以把 v-on: 替换为 @
* 在 methods 方法中的 this 代表的是 data 函数返回的数据对象



#### 双向绑定

* 用 v-model 实现双向绑定，即 
  * javascript 数据可以同步到表单标签
  * 反过来用户在表单标签输入的新值也会同步到 javascript 这边
* 双向绑定只适用于表单这种带【输入】功能的标签，其它标签的数据绑定，单向就足够了
* 复选框这种标签，双向绑定的 javascript 数据类型一般用数组



#### 计算属性
* 普通方法调用必须加 ()，没有缓存功能
* 计算属性使用时就把它当属性来用，不加 ()，有缓存功能：
  * 一次计算后，会将结果缓存，下次再计算时，只要数据没有变化，不会重新计算，直接返回缓存结果



#### axios

axios 它的底层是用了 XMLHttpRequest（xhr）方式发送请求和接收响应，xhr 相对于之前讲过的 fetch api 来说，功能更强大，但由于是比较老的 api，不支持 Promise，axios 对 xhr 进行了封装，使之支持 Promise，并提供了对请求、响应的统一拦截功能
* axios 默认导出一个对象，这里的 import 导入的就是它默认导出的对象

方法

| 请求                               | 备注   |
| ---------------------------------- | ------ |
| axios.get(url[, config])           | :star: |
| axios.delete(url[, config])        |        |
| axios.head(url[, config])          |        |
| axios.options(url[, config])       |        |
| axios.post(url[, data[, config]])  | :star: |
| axios.put(url[, data[, config]])   |        |
| axios.patch(url[, data[, config]]) |        |

* config - 选项对象、例如查询参数、请求头...
* data - 请求体数据、最常见的是 json 格式数据
* get、head 请求无法携带请求体，这应当是浏览器的限制所致（xhr、fetch api 均有限制）
* options、delete 请求可以通过 config 中的 data 携带请求体
* axios 对象可以直接使用，但使用的是默认的设置
* 用 axios.create 创建的对象，可以覆盖默认设置，config 见下面说明

常见的 config 项有

| 名称            | 含义                                                       |
| --------------- | ---------------------------------------------------------- |
| baseURL         | 将自动加在 url 前面                                        |
| headers         | 请求头，类型为简单对象                                     |
| params          | 跟在 URL 后的请求参数，类型为简单对象或 URLSearchParams    |
| data            | 请求体，类型有简单对象、FormData、URLSearchParams、File 等 |
| withCredentials | 跨域时是否携带 Cookie 等凭证，默认为 false                 |
| responseType    | 响应类型，默认为 json                                      |

* 生产环境希望 xhr 请求不走代理，可以用 baseURL 统一修改
* 希望跨域请求携带 cookie，需要配置 withCredentials: true，服务器也要配置 allowCredentials = true，否则浏览器获取跨域返回的 cookie 时会报错

响应格式

| 名称    | 含义              |
| ------- | ----------------- |
| data    | 响应体数据 :star: |
| status  | 状态码 :star:     |
| headers | 响应头            |

* 200 表示响应成功
* 400 请求数据不正确 age=abc
* 401 身份验证没通过
* 403 没有权限
* 404 资源不存在
* 405 不支持请求方式 post
* 500 服务器内部错误

请求拦截器

```js
_axios.interceptors.request.use(
  function(config) {
    // 比如在这里添加统一的 headers
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);
```

响应拦截器

```js
_axios.interceptors.response.use(
  function(response) {
    // 2xx 范围内走这里
    return response;
  },
  function(error) {
    // 超出 2xx, 比如 4xx, 5xx 走这里
    return Promise.reject(error);
  }
);

#### 列表渲染
* v-if 和 v-for 不能用于同一个标签
* v-for 需要配合特殊的标签属性 key 一起使用，并且 key 属性要绑定到一个能起到唯一标识作用的数据上，本例绑定到了学生编号上
* options 的 mounted 属性对应一个函数，此函数会在组件挂载后（准备就绪）被调用，可以在它内部发起请求，去获取学生数据
