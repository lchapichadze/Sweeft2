import java.util.*;

public class Main {


    public static void main(String[] args) {
        //Test1
        String s = "5+20-8+5";
        System.out.println(evaluateExpression(s));

        //Test2
        ArrayList<String> list = new ArrayList<>();
        list.add("abbcc");
        list.add("abc");
        list.add("abcabc");
        list.add("cabcbb");
        System.out.println(numberOfHappyStrings(list));

        //Test3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printLinkedList(reverseList(head));

        //Test4
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        int[] nums2 = {3, 4, 4, 5, 6, 7};
        System.out.println(Arrays.toString(findIntersection(nums1, nums2)));

        //Test5
        int[] nums3 = {6,2,2,3,4,1};
        int k = 8;
        System.out.println(lenOfLongSubarr(nums3,k));

        //Test6
        int[] array = {5, 1, 22, 25, 6, -1, 8, 10};
        int[] sequence = {1, 6, -1, 10};
        System.out.println(isValidSequence(array,sequence));
    }

    public static int evaluateExpression(String expression) {
        int total = 0;
        int num = 0;
        char operation = '+';

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (!(symbol == '+' || symbol == '-')) {
                num = num * 10 + (symbol - '0');
            }

            if (symbol == '+' || symbol == '-' || i == expression.length() - 1) {
                if (operation == '+') {
                    total += num;
                } else {
                    total -= num;
                }
                operation = symbol;
                num = 0;
            }
        }
        return total;
    }

    public static int numberOfHappyStrings(List<String> strings) {
        int count = 0;
        for (String word : strings) {
            boolean unique = true;
            for (int i = 0; i < word.length() - 1; i++) {
                if (word.charAt(i) == word.charAt(i + 1)) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                count++;
            }
        }
        return count;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode current = head, previous = null, nextNode = null;
        while (current != null) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            if (current.next != null) {
                System.out.print(current.val + "->");
            } else {
                System.out.println(current.val);
            }
            current = current.next;
        }
    }

    public static int[] findIntersection(int[] nums1, int[] nums2) {
        Set<Integer> seen = new HashSet<>();
        for (int value : nums1) {
            seen.add(value);
        }
        Set<Integer> common = new HashSet<>();
        for (int value : nums2) {
            if (seen.contains(value)) {
                common.add(value);
            }
        }
        int[] output = new int[common.size()];
        int index = 0;
        for (int value : common) {
            output[index++] = value;
        }
        return output;
    }

    public static int lenOfLongSubarr(int[] array, int k) {
        int len = array.length;
        int[][] dp = new int[len + 1][k + 1];

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= k; j++) {
                if (array[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], 1 + dp[i - 1][j - array[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return Math.max(dp[len][k], 0);
    }

    public static boolean isValidSequence(int[] array, int[] sequence) {
        int subIndex = 0;
        for (int value : array) {
            if (subIndex == sequence.length) {
                break;
            }
            if (value == sequence[subIndex]) {
                subIndex++;
            }
        }
        return subIndex == sequence.length;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        this.val = x;
    }
}
