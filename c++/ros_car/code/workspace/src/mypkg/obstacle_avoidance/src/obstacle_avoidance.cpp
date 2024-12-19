/**
 * @file obstacle_avoidance.cpp
 * @author Dav1nGen (davicheng1114@gmail.com)
 * @brief 
 * @version 0.1
 * @date 2024-05-26
 * 
 * @copyright Copyright (c) 2024
 * 
 */
#include "obstacle_avoidance.hpp"

int main(int argc, char* argv[]) {
    rclcpp::init(argc, argv);
    auto avoidance_node = std::make_shared<Avoidancer>("avoidancer");
    rclcpp::spin(avoidance_node);
    rclcpp::shutdown();
    return 0;
}
