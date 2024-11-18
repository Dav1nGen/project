#include <iostream>
#include<pybind11/pybind11.h>
#include<opencv2/opencv.hpp>
#include<numpy/arrayobject.h>
int initNumpy()
{
    //该宏Debug下编译不通过，python3.7需要将python-include路径下"object.h"文件中第56行"#define Py_TRACE_REFS"注释掉；
    //python3.9需要将python-include路径下"pyconfig.h"文件中第316行"#define Py_DEBUG"注释掉
	import_array();
	return 1;
}

int main()
{
    Py_SetPythonHome(L"/usr/bin/python3.10");//安装的Python路径
    Py_Initialize();
	PyRun_SimpleString("import sys");
	PyRun_SimpleString("sys.path.append('F:/Yolov5/')");//要执行的Python文件路径 
	initNumpy();
    //检查初始化是否成功
	if (!Py_IsInitialized())
    {
        return 0;
    }
    //直接执行Python脚本文件
    wchar_t *argv[2];
    argv[0] = L"python";
    argv[1] = L"/home/davi/yolov5test/detect.py";
    Py_Main(2, argv);
    
    PyObject * pModule = PyImport_ImportModule("detect001");      //detect001:要执行的Python文件名  
	PyObject * pFunc = PyObject_GetAttrString(pModule, "detect"); //detect:Python文件中的函数名
    PyObject *pReturn = NULL;

}

// int main()
// {
//    pybind11::scoped_interpreter guard{};
//    pybind11::exec(R"(
//     import sys
//     sys.path.append("./detect.py")
//     import detect
//     )", pybind11::globals());
//     pybind11::module script = pybind11::module::import("detect");
//     pybind11::object result = script.attr("main")();
// }
