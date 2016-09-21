
Definition for singly-linked list.
  
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
 
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return helper(lists, 0, lists.length - 1);
    }
    
    private ListNode helper(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        int mid = (end - start) / 2 + start;
        return merge(helper(lists, start, mid), helper(lists, mid + 1, end));
    }
    
    private ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null && l2 == null) return null;
        if(l1 == null || l2 == null) return (l1 == null)? l2: l1;
        ListNode head = new ListNode(0);
        ListNode headWalker = head;
        while(l1 != null || l2 != null){
            if(l1 == null){
                headWalker.next = l2;
                l2 = l2.next;
            }
            else if(l2 == null){
                headWalker.next = l1;
                l1 = l1.next;
            }
            
            else if(l1.val < l2.val){
                headWalker.next = l1;
                l1 = l1.next;
            }
            else{
                headWalker.next = l2;
                l2 = l2.next;
            }
            headWalker = headWalker.next;
        }
        return head.next;
    }
}

说明： 可以把中间的那个helper 改成： 
   private ListNode helper(ListNode[] lists, int start, int end){
        if(start == end){
            return lists[start];
        }
        int mid = (end - start) / 2 + start;
        ListNode temp1 = helper(lists, start, mid);
        ListNode temp2 = helper(lists, mid + 1, end);
        return merge(temp1, temp2);
    }