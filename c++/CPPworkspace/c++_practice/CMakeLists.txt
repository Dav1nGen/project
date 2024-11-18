cmake_minimum_required(VERSION 3.14)
project(test)
set(CMAKE_CXX_STANDARD 17)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_executable(
    ${PROJECT_NAME}
    ./Genertic.cpp
)