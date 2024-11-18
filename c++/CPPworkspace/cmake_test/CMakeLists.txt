# #将路径下的所有.cpp文件打包成一个静态库
# file(GLOB_RECURSE SRC_DIR_LIST "./*.cpp" )
# add_library(
#     ${PROJECT_NAME} STATIC
#     ${SRC_DIR_LIST}
# )
# message(STATUS "SRC_DIR_LIST=${SRC_DIR_LIST}")

# #自动查找头文件路径函数(没有去重)
# macro(FIND_INCLUDE_DIR result curdir)  #定义函数,2个参数:存放结果result；指定路径curdir；
#     file(GLOB_RECURSE children "${curdir}/*.hpp" "${curdir}/*.h" )	#遍历获取{curdir}中*.hpp和*.h文件列表
#     message(STATUS "children= ${children}")								#打印*.hpp和*.h的文件列表
#     set(dirlist "")														#定义dirlist中间变量，并初始化
#     foreach(child ${children})											#for循环
#         string(REGEX REPLACE "(.*)/.*" "\\1" LIB_NAME ${child})			#字符串替换,用/前的字符替换/*h
#         if(IS_DIRECTORY ${LIB_NAME})									#判断是否为路径
#             LIST(APPEND dirlist ${LIB_NAME})							#将合法的路径加入dirlist变量中
#         endif()															#结束判断
#     endforeach()														#结束for循环
#     set(${result} ${dirlist})											#dirlist结果放入result变量中
# endmacro()																#函数结束
# #查找include目录下的所有*.hpp,*.h头文件,并路径列表保存到 INCLUDE_DIR_LIST 变量中
# FIND_INCLUDE_DIR(INCLUDE_DIR_LIST ${PROJECT_SOURCE_DIR})			#调用函数，指定参数
# #将INCLUDE_DIR_LIST中路径列表加入工程		
# include_directories(   													#INCLUDE_DIR_LIST路径列表加入工程
#         ${INCLUDE_DIR_LIST}
# )
# message(STATUS "INCLUDE_DIR_LIST = ${INCLUDE_DIR_LIST}")				#打印INCLUDE_DIR_LIST中列表信息
cmake_minimum_required(VERSION 2.9)
project(cmake_test)

find_package(OpenCV 4 REQUIRED)
find_package(pybind11 REQUIRED)
find_package(Python3 3.10 EXACT REQUIRED COMPONENTS Development Interpreter)

add_executable(
    ${PROJECT_NAME}
    main.cpp
)

include_directories(
    ${OPENCV_INCLUDE_DIRS}
)
target_link_libraries(
    ${PROJECT_NAME}
    ${OpenCV_LIBS}
)
target_link_libraries(
    ${PROJECT_NAME}
    Python3::Python
    pybind11::pybind11
)