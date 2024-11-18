#include <opencv2/opencv.hpp>
#include <iostream>

using namespace cv;
using namespace std;

// 定义鼠标事件回调函数，用于记录鼠标位置
void mouse_callback(int event, int x, int y, int flags, void *userdata);

int main()
{
    // 定义IMM-Singer模型所需的参数和变量
    const int num_models = 2; // 子模型数量
    const int state_dim = 4;  // 状态向量维度
    const int meas_dim = 2;   // 测量向量维度

    // 定义子模型的Kalman滤波器
    vector<KalmanFilter> models(num_models);

    // 初始化每个子模型的Kalman滤波器
    for (int i = 0; i < num_models; i++)
    {
        // 初始化状态转移矩阵A和控制矩阵B
        models[i].transitionMatrix = (Mat_<float>(state_dim, state_dim) << 1, 0, 1, 0,
                                      0, 1, 0, 1,
                                      0, 0, 1, 0,
                                      0, 0, 0, 1);
        models[i].controlMatrix = (Mat_<float>(state_dim, 1) << 0, 0, 0, 0);

        // 初始化测量矩阵H
        models[i].measurementMatrix = (Mat_<float>(meas_dim, state_dim) << 1, 0, 0, 0,
                                       0, 1, 0, 0);

        // 初始化过程噪声协方差矩阵Q和观测噪声协方差矩阵R
        setIdentity(models[i].processNoiseCov, Scalar::all(1e-5));
        setIdentity(models[i].measurementNoiseCov, Scalar::all(1e-1));
    }

    // 定义IMM-Singer模型的权重和转移矩阵
    Mat model_prob = Mat::ones(1, num_models, CV_32F) / num_models;
    Mat trans_prob = (Mat_<float>(num_models, num_models) << 0.95, 0.05,
                      0.05, 0.95);

    // 定义当前鼠标位置的测量向量
    Mat measure = Mat::zeros(meas_dim, 1, CV_32F);

    // 创建窗口并设置鼠标事件回调函数
    namedWindow("IMM-Singer Mouse Tracker", WINDOW_AUTOSIZE);
    setMouseCallback("IMM-Singer Mouse Tracker", mouse_callback, &measure);
    while (true)
    {
        // 进行IMM-Singer模型的状态估计
        Mat state = Mat::zeros(state_dim, 1, CV32F);
        for (int i = 0; i < num_models; i++)
        {
            models[i].predict();
            models[i].correct(measure);
            state += model_prob.at<float>(0, i) * models[i].statePost;
        }
        // 计算当前状态的协方差矩阵
        Mat cov = Mat::zeros(state_dim, state_dim, CV_32F);
        for (int i = 0; i < num_models; i++)
        {
            Mat residual = measure - models[i].measurementMatrix * models[i].statePost;
            cov += model_prob.at<float>(0, i) * (models[i].errorCovPost + (models[i].statePost - state) * (models[i].statePost - state).t() + residual * residual.t());
        }

        // 绘制鼠标当前位置和预测位置
        Mat img = Mat::zeros(480, 640, CV_8UC3);
        circle(img, Point(measure.at<float>(0, 0), measure.at<float>(1, 0)), 5, Scalar(0, 0, 255), -1);
        circle(img, Point(state.at<float>(0, 0), state.at<float>(1, 0)), 5, Scalar(0, 255, 0), -1);
        imshow("IMM-Singer Mouse Tracker", img);

        // 更新IMM-Singer模型的状态转移矩阵和权重
        Mat model_prob_predict = model_prob * trans_prob;
        for (int i = 0; i < num_models; i++)
        {
            Mat state_predict = models[i].transitionMatrix * state;
            Mat cov_predict = models[i].transitionMatrix * cov * models[i].transitionMatrix.t() + models[i].processNoiseCov;

            Mat residual = measure - models[i].measurementMatrix * state_predict;
            float likelihood = exp(-0.5 * residual.t() * models[i].measurementNoiseCov.inv() * residual) / sqrt(pow(2 * CV_PI, meas_dim) * determinant(models[i].measurementNoiseCov));

            model_prob_predict.at<float>(0, i) *= likelihood;
            models[i].statePre = state_predict;
            models[i].errorCovPre = cov_predict;
        }
        normalize(model_prob_predict, model_prob_predict, 1, NORM_L1);
        model_prob = model_prob_predict.clone();

        // 等待用户按下ESC键退出
        if (waitKey(10) == 27)
            break;
    }
    return 0;
}
void mouse_callback(int event, int x, int y, int flags, void *userdata)
{
    // 当用户单击鼠标左键时记录鼠标位置
    if (event == EVENT_LBUTTONDOWN)
    {
        Mat *measure = (Mat *)userdata;
        measure->at<float>(0, 0) = x;
        measure->at<float>(1, 0) = y;
    }
}