# BUAA-OOP-2022-CTS

本项目为北航2022年春季学期 `面向对象程序设计（Java）` 课程的学期项目，共分为4部分进行增量开发。

### 项目说明

本仓库中包含以下内容：

- 官方需求文档：

  - [CTS-1需求文档](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-1.md)

  - [CTS-2需求文档](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-2.md)

  - [CTS-3需求文档](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-3.md)

  - [CTS-4需求文档](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-4.md)

- 每次需求发布后实现的非增量版本代码：

  - [CTS-1非增量版本](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS1-20373785-龙亿舟-202114.zip)
  - [CTS-2非增量版本](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS2-20373785-龙亿舟-202114.zip)
  - [CTS-3非增量版本](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS3-20373785-龙亿舟-202114.rar)
  - [CTS-4](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS4-20373785-龙亿舟-202114.rar)

  > 其中 `CTS-4` 版本由于对之前实现的指令进行了部分修改，因此无法通过之前的评测。

- `CTS-4` 中用以导入核酸检测的文档：

  - [cert.csv](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/cert.csv)
  - [cert2.csv](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/cert2.csv)
  - [cert3.csv](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/cert3.csv)

### 需求描述

#### CTS-1

> 具体需求文档见：[BUAA-OOP-2022-CTS/CTS-1](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-1.md)

本次实现了如下功能：

- 开关机
  - `QUIT`
- 用户注册
  - `addUser`

#### CTS-2

> 具体需求文档见：[BUAA-OOP-2022-CTS/CTS-2](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-2.md)

本次实现了如下功能：

- 超级管理员
  - `TunakTunakTun`
  - `NutKanutKanut`
- 线路管理
  - `addLine`
  - `delLine`
  - `addStation`
  - `delStation`
  - `lineInfo`
  - `listLine`
- 列车管理
  - `addTrain`
  - `delTrain`
  - `checkTicket`
  - `listTrain`

其中各功能中包括对应的输入错误处理

#### CTS-3

> 具体需求文档见：[BUAA-OOP-2022-CTS/CTS-3](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-3.md)

本次实现了如下功能：

- 登录相关
  - `login`
  - `logout`
- 购买车票
  - `buyTicket`
- 查询订单
  - `listOrder`

#### CTS-4

> 具体需求文档见：[BUAA-OOP-2022-CTS/CTS-4](https://github.com/MaxLong2001/BUAA-OOP-2022-CTS/blob/main/CTS-4.md)

本次修改或新增了如下功能：

- [修改] 添加用户
  - `addUser`
- [修改] 购买车票
  - `buyTicket`
- 电子钱包功能
  - `rechargeBalance`
  - `checkBalance`
- 核酸检测功能
  - `importCert`
- 退票功能
  - `cancelOrder`
- 结算功能
  - `payOrder`

