[TOP] 搭建通用webview框架

* usesCleartextTraffic true 允许使用http协议  9.0限制

- common 通用模块   所有程序员均可修改
- base   通用模块   高工、架构师可修改

- autoservice 组件化

#### 在common中 接口下沉


#### 单页面形式打开webview 同时支持是否显示ActionBar

#### 单独放入一个进程   fragment不能放在独立的进程   取决于Activity是否处于独立进程
> 防止内存泄漏
> 防止oom
> 防止webView奔溃导致应用奔溃
> 提高应用可靠性

#### 待优化点
> 返回操作
> 