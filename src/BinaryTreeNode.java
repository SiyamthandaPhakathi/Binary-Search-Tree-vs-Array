// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<T> 
{
   T entry;
   BinaryTreeNode<T>  left;
   BinaryTreeNode<T>  right;
   
   public BinaryTreeNode ( T entry, BinaryTreeNode<T>  l, BinaryTreeNode<T>  r )
   {
      this.entry = entry;
      left = l;
      right = r;
   }
   
   BinaryTreeNode<T> getLeft () { return left; }
   BinaryTreeNode<T> getRight () { return right; }
}
