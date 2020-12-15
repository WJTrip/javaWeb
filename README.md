# javaweb平时作业  
## 项目结构  
### 1. src包  
* bean包：存放city（城市类）、download（下载类）、page（页面类）、province（省份类）、user（用户类）对象  
* controller包：存放Login（登录）、Logout（安全退出）、Register（注册）、Download（下载）、CreateVerifyCodeImage（生成验证码）、GetDownloadList（获取下载列表）、QueryUser（查找用户）、
DeleteUser（删除用户）、checkExist（检查用户是否存在）、queryProvinceCity（根据省份ID查找城市）、ajaxLoginCheck（ajax登录）、QueryProvinceCityList（ajax根据省份ID查找城市）控制器
* dao包：存放对数据库数据的操作（CRUD），CityDao（查询并返回city列表）、CreateVCImage（绘制生成验证码）、DownloadDao（查询并返回每个下载资源的所有信息）、ProvinceDao（查询并返回province列表）、
UserDao（get查询并返回user对象、insert插入一个user对象、update修改一个user对象的信息、getByField通过id查询并返回user对象、query查询所有user对象并返回一个列表存放在page里、count统计user对象数量并返回到page里、
delete删除一个user对象）
* filter包：有两个过滤器，实现权限拦截，AutoLogin（自动登录），Permission（权限拦截）
* utils包：存放一个JdbcUtil，数据库连接和关闭的简单操作的封装
* jdbc.properties：数据库连接信息的配置
### 2.web目录
* css：各种对应jsp和html页面的样式文件
* images：页面中所需的图片
* js：login.js（changCode点击重新生成验证码，ajax登录，ajax局部更新页面信息（鼠标聚焦更换显示提示信息））、
register.js（fillProvince、fillCity填充省份城市下拉框，checkUser、checkMailFormat、checkMailExit都是检查格式以及是否存在，也有ajax局部更新页面信息（鼠标聚焦更换显示提示信息））、
userManage.js（第一步是加载数据的function、后有insert、query、delete、清空、update按钮方法、分页操作）、jquery-3.3.1.js、jquery-3.5.0.min.js
* download.jsp （下载界面）、error.jsp（出错提示页面）、login.html（登录界面）、main.jsp（主界面）、register.html（注册界面）、
userManager.jsp（用户管理分页页面）
#### WEB-INF目录
一个静态资源文件和一个web.xml配置文件
