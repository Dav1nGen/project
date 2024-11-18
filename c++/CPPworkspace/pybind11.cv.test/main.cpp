#include<iostream>
#include<pybind11/pybind11.h>
#include<opencv2/opencv.hpp>

void CheckFuncInvoke(PyObject* Func)
{
    if(Func!=NULL)
    {
        std::cout<<"Func invock successfully"<<std::endl;
    }
}

int main()
{
    Py_Initialize();
    if( !Py_IsInitialized()){
		std::cout << "python init fail" << std::endl;
		return 0;
	}
    PyRun_SimpleString("import sys");
	PyRun_SimpleString("sys.path.append('/home/davi/CPPworkspace/pybind11.test2/scripts')");
    //PyRun_SimpleString("sys.path.append('./scripts')");//使用相对路径有问题

    PyObject* pModule = PyImport_ImportModule("detect");
	if( pModule == NULL )
    {
		std::cout <<"module not found" << std::endl;
		return 1;
	}

    PyObject* pFunc1 = PyObject_GetAttrString(pModule, "run");
    PyObject* pFunc2 = PyObject_GetAttrString(pModule, "parse_opt");
    PyObject* pFunc3 = PyObject_GetAttrString(pModule, "main");
    
    PyObject_CallObject(pFunc1, NULL );
    PyObject_CallObject(pFunc2, NULL );
    PyObject_CallObject(pFunc3, NULL );
    


	Py_Finalize();
	return 0;
}

