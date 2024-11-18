cmake_minimum_required(VERSION 3.10)
project(armor_detector)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_executable(
    ${PROJECT_NAME}
    ./src/detect.cpp
    ./include/armor.hpp
)

find_package(OpenCV REQUIRED)

include_directories(
    ${OpenCV_INCLUDE_DIRS}
)

target_link_libraries(
    ${PROJECT_NAME} 
    ${OpenCV_LIBS}
)