#include<iostream>
struct ListNode 
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };

class Solution
{
public:
    bool hasCycle(ListNode *head) 
    {
        if(head==nullptr)
        {
            return false;
        }
        ListNode *slow;
        ListNode *fast;
        slow=head;
        fast=head->next;        
        while(slow!=fast)
        {
                if(fast==nullptr ||fast->next==nullptr)
                {
                    return false;
                }
                slow=slow->next;
                fast=fast->next->next;
        }
        return true;
    }
};