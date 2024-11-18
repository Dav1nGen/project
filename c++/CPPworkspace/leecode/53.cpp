#include <iostream>
#include <vector>
using namespace std;

//Brute force cracking
// class Solution
// {
// public:
//     int max;
//     int maxSubArray(vector<int> &nums)
//     {
//         for (int i = 0; i <= nums.size(); i++)
//         {
//             for (vector<int>::iterator it = nums.begin(); it <= nums.end() - i - 1; it++)
//             {
//                 if ((it + i) != nums.end())
//                 {
//                     int temp = *it;
//                     for (int k = 1; k <= i; k++)
//                     {
//                         temp = temp + *(it + k);
//                     }
//                     if (temp >= max)
//                     {
//                         max = temp;
//                     }
//                 }
//             }
//         }
//         return max;
//     }
// };

//Official Answer
// class Solution
// {
// public:
//     int maxSubArray(vector<int> &nums)
//     {
//         int pre = 0, maxAns = nums[0];
//         for (const auto &x : nums)
//         {
//             pre = max(pre + x, x);
//             maxAns = max(maxAns, pre);
//         }
//         return maxAns;
//     }
// };

//Greedy Algorithm
class Solution
{
    public:
    int maxSubArray(vector<int> &nums)
    {
        
        int maxsum, tempsum;
        maxsum=nums[0];
        tempsum=0;
        if(nums.size()==1)
        {
            return nums[0];
        }
        for(const auto &x:nums)
        {
            tempsum+=x;
            if(tempsum<x)
            {
                tempsum=x;
            }
            if(tempsum>=maxsum)
            {
                maxsum=tempsum;
            }
        }
        return maxsum;
    }
};
int main()
{
    vector<int> nums;
    nums.push_back(5);
    nums.push_back(4);
    nums.push_back(-1);
    nums.push_back(7);
    nums.push_back(8);
    // nums.push_back(2);
    // nums.push_back(1);
    // nums.push_back(-5);
    // nums.push_back(4);
    Solution s;
    return s.maxSubArray(nums);
}