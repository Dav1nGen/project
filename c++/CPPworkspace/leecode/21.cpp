#include <iostream>
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
class Solution
{
public:
    ListNode *mergeTwoLists(ListNode *list1, ListNode *list2)
    {
        if (list1 == nullptr)
        {
            return list2;
        }
        if (list2 == nullptr)
        {
            return list1;
        }
        else
        {
            ListNode *head = list1;
            ListNode *ptr1 = list1;
            ListNode *ptr2 = list2;
            while (ptr2 != nullptr)
            {
                while (ptr1 != nullptr)
                {
                    if (ptr2->val >= ptr1->val && ptr2->val <= ptr1->next->val)
                    {
                        ListNode *q = ptr2;
                        list1 = ptr1;
                        q->next = list1->next;
                        list1->next = q;
                    }
                    ptr1 = ptr1->next;
                }
                ptr2 = ptr2->next;
            }
            return list1;
        }
    }
};