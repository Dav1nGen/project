# README
---
# 项目介绍
此项目参考fishros的[foshbot项目](https://github.com/fishros/fishbot)编写，用于学习ROS2，并实现一个简单的机器人仿真环境。

## 特性
使用了[ament_cmake_auto](https://github.com/ament/ament_cmake/tree/master/ament_cmake_auto)自动查找依赖包，简化了传统ament_cmake的繁琐配置

## 项目运行
运行前请运行以下代码安装项目所需依赖



``` bash
cd workspace
rosdep install --from-paths src -y -r 
```

编译以及运行
``` bash
# 编译构建
colcon build

# 运行机器人仿真
ros2 launch robot_simulation launch.py
```
## 项目文件树
``` bash

my_ros_bot
├── README.md
├── models #模型文件 放置于 ~/.gazebo/ 文件夹下
└── workspace
    └── src
        ├── robot_describtion # 机器人描述包
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
        │       └── robot.urdf
        └── robot_simulation # 机器人仿真包
            ├── CMakeLists.txt
            ├── include
            │   └── robot_simulation.hpp
            ├── launch
            │   └── launch.py
            ├── package.xml
            ├── src
            │   └── robot_simulation.cpp
            ├── urdf
            │   └── robot.urdf
            └── world
                ├── Dav1nGen_world
                └── world.world

```