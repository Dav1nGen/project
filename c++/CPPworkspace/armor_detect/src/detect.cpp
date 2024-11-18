#include <iostream>
#include <opencv2/core.hpp>
int main()
{
    int x=1;
    int y=4;
    int lenth =cv::norm(y-x);
    std::cout<<lenth;
    return 0;
}