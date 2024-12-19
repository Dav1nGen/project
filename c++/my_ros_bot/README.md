# README
---
# 项目介绍
此项目参考fishros的[foshbot项目](https://github.com/fishros/fishbot)编写，用于学习ROS2，并实现一个简单的机器人仿真环境。

目前已完成机器人结构定义以及仿真世界的创建，并可以通过launch文件启动RobotDescribtion节点和RobotWorld节点

## 特性
CmakeLists.txt文件使用了[ament_cmake_auto](https://github.com/ament/ament_cmake/tree/master/ament_cmake_auto)自动查找依赖包，简化了传统ament_cmake的繁琐配置

## 项目文件树
```
my_ros_bot
├── README
├── document
└── workspace
    └── src
        ├── robot_describtion # 自定义机器人包
        │   ├── CMakeLists.txt
        │   ├── include
        │   │   └── robot_describtion
        │   │       └── robot_describtion.hpp
        │   ├── launch
        │   │   └── launch.py
        │   ├── package.xml
        │   ├── src
        │   │   └── robot_describtion.cpp
        │   └── urdf
        │       └── robot.urdf #机器人结构定义文件
        └── robot_world # 自定义世界包
            ├── CMakeLists.txt
            ├── include
            │   └── robot_world
            │       └── robot_world.hpp
            ├── launch
            │   └── launch.py
            ├── package.xml
            ├── src
            │   └── robot_world.cpp
            └── world
                ├── robot.urdf # 机器人结构定义文件
                └── world.sdf # 世界定义文件
```
