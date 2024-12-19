cmake_minimum_required(VERSION 3.14)
project(BuffDetect)
set(CMAKE_CXX_STANDARD 17)


find_package(Ceres REQUIRED)
find_package(Eigen3 REQUIRED)
find_package(Python3 REQUIRED COMPONENTS Development Interpreter)
find_package(pybind11 REQUIRED)
find_package(OpenCV 4 REQUIRED)
# find_package(Python3)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_executable(
  ${PROJECT_NAME}
  ./src/main.cpp 
  ./include/BuffDetect.hpp
  # ./general/GxCamera/GxCamera.cpp
  # ./general/GxCamera/GxCamera.h
  # ./general/GxCamera/include/GxIAPI.h
  # ./general/GxCamera/include/DxImageProc.h
)

include_directories(
  ${CERES_INCLUDE_DIRS}
  ${EIGEN3_INCLUDE_DIRS}
  ${OPENCV_INCLUDE_DIRS}
)

target_link_libraries(
  ${PROJECT_NAME}
  # ${X11_LIBRARIES}
  ${OpenCV_LIBS}
  ${CERES_LIBRARIES}
  pthread
  Python3::Python
  pybind11::pybind11
)
