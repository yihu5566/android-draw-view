package com.we.opengl3d;

/**
 * @Author : dongfang
 * @Created Time : 2022-07-28  16:02
 * @Description:
 */
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
         this.right = right;
      }
  }

