package org.fufeng.project.lamda.algro;

/**
 *
 * @ClassName: Solution
 * @author: LuoChunYun
 * @Date: 2019/4/30 11:34
 * @Description: TODO
 */
public class Solution {

    public static void main(String[] args) {
        final ListNode head = new ListNode(1);
        final ListNode one = new ListNode(2);
        final ListNode two = new ListNode(3);
        final ListNode three = new ListNode(4);
        final ListNode fir = new ListNode(5);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = fir;

        /**
         *
         */
        //final ListNode listNode = rotateRight(head, 2);
        //输出
        //printNode(listNode);

        final ListNode listNode = removeNthFromEnd(head, 2);
        //输出
        printNode(listNode);

    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //去掉只有一个节点的情况
        if(head == null || head.next == null){
            return null;
        }
        int i = 0;
        ListNode brokeNode = head;
        while(brokeNode != null){
            i++;
            if(brokeNode.next == null){
                break;
            }
            brokeNode = brokeNode.next;
        }
        //去掉连个节点时删除第一个节点
        if(i == n){
            return head.next;
        }else if(n > i){
            return head;
        }
        brokeNode = head;
        for (int move = 1; move < (i - n) ; move++){
            brokeNode = brokeNode.next;
        }
        brokeNode.next = brokeNode.next.next;
        return head;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        int i = 0;
        ListNode tail = head;
        while (tail != null) {
            i++;
            if(tail.next == null) {
                break;
            }else {
                tail = tail.next;
            }
        }
        if(k % i == 0){
            return head;
        }
        System.out.println("i="+i);
        tail.next = head;
        int move = i - (k % i);
        System.out.println("move="+move);
        ListNode brokePoint = head;
        for (int v = 0; v < move-1; v++) {
            brokePoint = brokePoint.next;
        }
        head = brokePoint.next;
        brokePoint.next = null;
        return head;
    }

    private static void printNode(ListNode listNode){
        ListNode node = listNode;
        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
