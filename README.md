# java-exam
Java实现的包含题库编辑、抽题组卷、试题分析、在线考试等模块的Web教育系统。

已经实现的主要功能有：
- 选择题、填空题、判断题的文本文件数据的Web导入
- 用户数据的Web导入
- 用户注册、登录、修改密码
- 按照一定给分策略进行抽题和组卷
- 按照内容、知识点、答案等搜索题库
- 章节知识点的分层和树状展示

目前项目的主要技术包含:
- Hibernate 5.1 
- Struts 2.5
- Spring 4.3
- JFreeChart 1.0.19
- Maven
- Materialize v0.97.6 (CSS)
- Font Awesome 5.0.13

数据库设计：
- (MySQL导出)
![image](https://raw.githubusercontent.com/mikemelon/java-exam/master/screenshots/db_design1.bmp)

- (简版)
![image](https://raw.githubusercontent.com/mikemelon/java-exam/master/screenshots/db_design2.png)

目前代码行数约为：15000行 (2017-04-20)