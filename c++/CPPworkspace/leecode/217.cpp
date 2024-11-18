#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
public:
    bool containsDuplicate(vector<int> &nums)
    {
        if (nums.size() > 1)
        {
            sort(nums.begin(), nums.end());
            for (vector<int>::iterator it = nums.begin(); it != nums.end()-1; it++)
            {
                if (*it == *(it + 1))
                {
                    return true;
                }
            }
            return false;
        }

        if (nums.size() == 1)
        {
            return false;
        }
        return false;
    }
};

int main()
{
    vector<int> nums;
    nums.push_back(1);
    nums.push_back(2);
    nums.push_back(3);
    nums.push_back(4);
    Solution temp;
    temp.containsDuplicate(nums);
}