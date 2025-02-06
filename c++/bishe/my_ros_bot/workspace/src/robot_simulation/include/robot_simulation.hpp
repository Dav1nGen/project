#ifndef ROBOT_WORLD_INCLUDE_ROBOT_WORLD_ROBOT_WORLD_HPP_
#define ROBOT_WORLD_INCLUDE_ROBOT_WORLD_ROBOT_WORLD_HPP_

#include "rclcpp/rclcpp.hpp"

class RobotSimulation : public rclcpp::Node
{
public:
    RobotSimulation() : Node("robot_simulation")
    {
        RCLCPP_INFO(this->get_logger(), "Robot Simulation Node has been started.");
    }
};

#endif  //  ROBOT_WORLD_INCLUDE_ROBOT_WORLD_ROBOT_WORLD_HPP_