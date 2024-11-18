#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;
class Solution
{
public:
    void merge(vector<int> &nums1, int m, vector<int> &nums2, int n)
    {
        if (n != 0 && m != 0)
        {
            for (int i = 0; i <= n - 1; i++)
            {
                nums1.push_back(nums2[i]);
            }
            sort(nums1.begin(), nums1.end());
            for(int i=0;i<=nums1.size()-1;i++)
            {
                vector<int>::iterator it=nums1.begin();
                if(nums1[0]==0)
                {
                    nums1.erase(it);
                }
            }
            for(const auto &x :nums1)
            {
                cout << x;
            }
        }
        else if (n == 0)
        {
            for (const auto &x : nums1)
            {
                cout << x;
            }
        }
        else if (m == 0)
        {
            for (const auto &x : nums2)
            {
                cout << x;
            }
        }
        
    }
};
int main()
{
    vector<int> nums1;
    int m = 0;
    // nums1.push_back(0);
    // nums1.push_back(0);
    // nums1.push_back(0);
    // nums1.push_back(4);

    vector<int> nums2;
    int n = 1;
    nums2.push_back(1);
    // nums2.push_back(7);
    // nums2.push_back(5);

    Solution temp;
    temp.merge(nums1, m, nums2, n);
}