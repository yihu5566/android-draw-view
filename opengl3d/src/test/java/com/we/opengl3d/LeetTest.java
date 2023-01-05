package com.we.opengl3d;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author : dongfang
 * @Created Time : 2022-07-22  15:51
 * @Description:
 */
class LeetTest {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j])
                temp[index++] = nums1[i++];
            else
                temp[index++] = nums2[j++];
        }

        System.out.println(i + "===" + j);
        System.out.println(Arrays.toString(temp));

        for (; i < m; ) {
            temp[index++] = nums1[i++];
        }

        for (; j < n; ) {
            temp[index++] = nums2[j++];
        }
        for (int k = 0; k < m + n; k++) {
            nums1[k] = temp[k];
        }

    }


    public TreeNode sortedArrayToBST(int[] num) {

        if (num.length == 0) return null;
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] num, int start, int end) {

        if (start > end) return null;
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, start, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, end);
        return root;

    }


    List<List<Integer>> main(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(subList);
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        System.out.println("");

        int indexFirst = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < indexFirst) {
                indexFirst = strs[i].length();
            }
        }
        //相同前缀
        StringBuilder stringBuilder = new StringBuilder();
        //循环次数
        int count = strs.length - 1;
        char[] chars = strs[0].toCharArray();
        for (int i = 0; i < indexFirst; i++) {
            char index = chars[i];
            for (int j = 1; j < strs.length; j++) {
                if (index != strs[j].charAt(i)) {
                    break;
                }
                count--;
            }
            if (count == 0) {
                stringBuilder.append(index);
            }
        }
        return stringBuilder.toString();


    }


}
