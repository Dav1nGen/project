cmake_minimum_required(VERSION 2.9)
project(TEST)

find_package(OpenCV 4 REQUIRED)
find_package(pybind11 REQUIRED)
find_package(Python3 3.10 EXACT REQUIRED COMPONENTS Development Interpreter)

add_executable(
    ${PROJECT_NAME}
    ./main.cpp
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