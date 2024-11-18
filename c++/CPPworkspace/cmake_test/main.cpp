#include<iostream>
#include<opencv2/opencv.hpp>
//#include"/usr/include/Python.h"
#include<Python.h>
int main()
{
    std::cout<<"hello"<<std::endl;
    cv::namedWindow("test",1920*1080);
    cv::Mat src=cv::imread("/home/davi/1.png");
    cv::imshow("test",src);
    cv::waitKey(0);
    return 0;
}