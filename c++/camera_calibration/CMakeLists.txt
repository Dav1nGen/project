cmake_minimum_required(VERSION 3.14)
project(test)
set(CMAKE_CXX_STANDARD 17)

find_package(OpenCV 4 REQUIRED)
find_package(Ceres REQUIRED)
find_package(Eigen3 REQUIRED)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_executable(
    ${PROJECT_NAME}
    ./camera_calibration.cpp
)

include_directories(
    ${OPENCV_INCLUDE_DIRS}
    ${EIGEN3_INCLUDE_DIRS}
)


target_link_libraries(
    ${PROJECT_NAME}
    ${OpenCV_LIBS}
    ${X11_LIBRARIES}
    ${CERES_LIBRARIES}
    gxiapi

)