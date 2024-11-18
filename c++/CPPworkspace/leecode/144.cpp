#include <iostream>
#include <vector>
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
    void find(TreeNode *tree, std::vector<int>& vec)
    {
        if (tree == nullptr)
            return;
        vec.push_back(tree->val);
        find(tree->left, vec);
        find(tree->right, vec);
    }

    std::vector<int> preorderTraversal(TreeNode *root)
    {
        std::vector<int> result;
        find(root, result);
        return result;
    }
};