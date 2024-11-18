#include <iostream>
#include <string>
#include <sstream>
// 不同的数据类型相加

// template <typename... T>
// auto add(T... t)
// {
//     return (t + ...);
// }
// template <typename... T>
// std::string concat(T... t)
// {

//     std::string str;
//     ((str += t), ...);
//     return str;
// }

// template<typename T1, typename T2,typename T3>
// auto sum(T1 x,T2 y,T3 z)
// {
//     return x+y+z;
// }

template <typename T>
std::string to_string(const T& value) 
{
    std::ostringstream oss;
    oss << value;
    return oss.str();
}

// 可变模板参数的相加和字符串拼接
template <typename... Args>
std::string addOrConcat(Args&&... args) {
    std::string result;
    ((result += to_string(std::forward<Args>(args))), ...);
    return result;
}

int main() {
    std::cout << addOrConcat(1, 2, 3) << std::endl;                      
    std::cout << addOrConcat("Hello", ", ", "world", "!") << std::endl;    

    return 0;
}