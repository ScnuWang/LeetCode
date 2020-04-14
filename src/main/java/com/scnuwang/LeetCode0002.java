package com.scnuwang;

/**
 * 题目：
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
/*
    分析:
    0、一句话理解题目：实际上就是连续的两个个位数相加,如果有进位，下一次就是三个数相加
    1、两个数相加要考虑进位
    2、要考虑null值
    3、逆序 的方式存储 实际上最先存储得就是个位数。
    4、第一想法是将数组里面得数转换成ListNode，实际上不用，自己在用得时候一个一个得加到next。
    5、单链表的所有操作只能从头部开始。
    6、两个一位数相加，最大值为9+9+1=19，其中1为进位数的值。
 */

/**
 *  收获：
 *  1、单链表的特性，所有参照都需要从头开始
 *  2、Java的引用传递
 *  3、入门LeetCode答题思路
 */
public class LeetCode0002 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static String print(ListNode listNode){
        StringBuilder stringBuilder = new StringBuilder();
        while (listNode != null) {
            stringBuilder.append(listNode.val);
            listNode = listNode.next;
        }
        return stringBuilder.toString();
    }

    // 每次计算俩个个位数相加，并返回余数
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 检查是否为null,null不代表0
        if (l1 == null || l2 == null) {
            return null;
        }
        // 进位数
        int carry = 0;
        // 保存计算结果
        ListNode result = new ListNode(0);
        // 将result的引用地址复制给head，下面操作result
        ListNode head = result;
        // 可能执行两次循环
        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 == null ? 0:l1.val;
            int y = l2 == null ? 0:l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            int remainder = sum % 10;
            head.next = new ListNode(remainder);
            // 下次再进入的时候result的next的next再同步
            head = head.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // 返回当次计算的结果
        return result.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        l1.next = new ListNode(4);
        l2.next = new ListNode(6);
        System.out.println(print(l1));
        System.out.println(print(l2));
        System.out.println(print(addTwoNumbers(l1, l2)));
    }
}
