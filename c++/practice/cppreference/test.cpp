#include <iostream>
#include <thread>
#include <chrono>
void PrintFunc()
{
    while(1)
    {
        std::this_thread::sleep_for(std::chrono::seconds(1));
        std::cout<<"1"<<"\n";                                
    } 
}

int main()
{
    std::thread the1(PrintFunc);
    std::thread the2(PrintFunc);

    the1.join();
    the2.join();

}
