#include<iostream>
#include<vector>
using namespace std;
class Solution 
{
vector<int> vec;
public:
    vector<int> twoSum(vector<int>& nums, int target) 
    {
        for(int i=0;i<=nums.size()-2;i++)
        {
            for(int k=i+1;k<=nums.size()-1;k++)
            {
                if(nums[i]+nums[k]==target)
                {
                    vec.push_back(i);
                    vec.push_back(k);
                    
                } 
            }
        }
        return vec;
    }
};