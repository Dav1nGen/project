#include<iostream>
#include<vector>
#include<unordered_map>

int main()
{
    // std::vector<int> vec;
    // vec.push_back(1);
    // vec.push_back(2);
    // for(auto it=vec.begin();it!=vec.end();it++)
    // {
    //     std::cout<<*it<<std::endl;
    // }


    std::unordered_map<int,int> hash;
    hash.emplace(1,1);
    hash.emplace(2,1);
    hash.emplace(3,2);
    hash.emplace(5,2);
    std::cout<<hash.size()<<std::endl;
    for(std::unordered_map<int,int>::iterator it=hash.begin();it!=hash.end();it++)
    {
        std::cout<<it->first<<" "<<it->second<<std::endl;
    }
    return 0;

}