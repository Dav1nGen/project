#include <iostream>
#include <vector>
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode *father; //
    int deepth = 0;   //
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

TreeNode *FindDeepest(TreeNode *root)
{
    int max = 0;
    TreeNode *p = root;
    if (root->left != nullptr)
    {
        root->left->father = root;
        root->left->deepth = root->left->deepth + 1;
        if (root->left->deepth >= max)
        {
            max = root->left->deepth;
            p = root->left;
        }
        FindDeepest(root->left);
    }

    if (root->right != nullptr)
    {
        root->right->father = root;
        root->right->deepth = root->right->deepth + 1;
        if (root->right->deepth >= max)
        {
            max = root->right->deepth;
            p = root->right;
        }
        FindDeepest(root->right);
    }
    return p;
}

int deepestLeavesSum(TreeNode *q, TreeNode *root)
{
    TreeNode *temp;
    temp = FindDeepest(q);
    int sum = root->val;
    while (temp != root)
    {
        sum += temp->val;
        temp = temp->father;
    }
    return sum;
}

int main()
{
    TreeNode *root;
    root->left = new TreeNode();
    root->right = new TreeNode();
    root->val = 1;
    root->left->val = 2;
    root->right->val = 3;
    TreeNode *p = FindDeepest(root);
    std::cout << deepestLeavesSum(p, root) << std::endl;
    return 0;
}