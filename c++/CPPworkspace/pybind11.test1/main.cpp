#include<iostream>
#include<pybind11/pybind11.h>
#include<opencv2/opencv.hpp>

int main()
{
    Py_Initialize();
    if( !Py_IsInitialized()){
		std::cout << "python init fail" << std::endl;
		return 0;
	}
    PyRun_SimpleString("import sys");
	PyRun_SimpleString("sys.path.append('/home/davi/CPPworkspace/pybind11.test1/scripts')");
    //PyRun_SimpleString("sys.path.append('./scripts')");//使用相对路径有问题

    PyObject* pModule = PyImport_ImportModule("sayHello");
	if( pModule == NULL )
    {
		std::cout <<"module not found" << std::endl;
		return 1;
	}

    PyObject* pFunc = PyObject_GetAttrString(pModule, "say");
	if( !pFunc || !PyCallable_Check(pFunc))
    {
		std::cout <<"not found function add_num" << std::endl;
		return 0;
	}

    PyObject_CallObject(pFunc, NULL );

	Py_Finalize();
	return 0;
}