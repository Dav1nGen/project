#include <cassert>
#include <iostream>
#include <string>
#include <vector>

class Solution {
  public:
    std::string longestCommonPrefix(std::vector<std::string> &strs) {
        if (!strs.size())
            return "";
        std::string result = strs[0];
        for (size_t i = 0; i < strs.size(); i++) {
            result = compare(result, strs[i]);
        }
        return result;
    }

    std::string compare(std::string &str1, std::string &str2) {
        std::string result;
        size_t i = std::min(str1.size(), str2.size()) - 1;
        while (i >= 1) {
            if (str1.substr(0, i) == str2.substr(0, i)) {
                result = str1.substr(0, i);
            }
            i--;
        }
        return result;
    }
};

// class Solution {
// public:
//     string longestCommonPrefix(vector<string>& strs) {
//         if(strs.empty()) return "";
//         const auto [str0, str1] = minmax_element(strs.begin(), strs.end());
//         for(int i = 0; i < str0->size(); ++i)
//             if(str0->at(i) != str1->at(i)) return str0->substr(0, i);
//         return *str0;
//     }
// };
