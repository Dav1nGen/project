#include "../include/robot_simulation.hpp"
int main(int argc, char **argv)
{
    rclcpp::init(argc, argv);
    auto node = std::make_shared<RobotSimulation>();
    rclcpp::spin(node);
    return 0;
}