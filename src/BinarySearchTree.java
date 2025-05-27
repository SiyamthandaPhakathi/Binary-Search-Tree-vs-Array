// Hussein's Binary Search Tree
// 27 March 2017
// Hussein Suleman

public class BinarySearchTree<Entry extends Comparable<? super Entry>> extends BinaryTree<Entry> 
{
   public void insert ( Entry entry )
   {
      if (root == null)
         root = new BinaryTreeNode<Entry>  (entry, null, null);
      else
         insert (entry, root);
   }
   //This will insert based on the term
   public void insert ( Entry entry, BinaryTreeNode<Entry>  node )
   {
      if (entry.compareTo (node.entry) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<Entry>  (entry, null, null);
         else
            insert (entry, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<Entry>  (entry, null, null);
         else
            insert (entry, node.right);
      }
   }
   
   public BinaryTreeNode<Entry>  find ( Entry entry )
   {
      if (root == null)
         return null;
      else
         return find (entry, root);
         
   }

   

   public BinaryTreeNode<Entry>  find ( Entry entry, BinaryTreeNode<Entry>  node )
   {
      //if we find d return node with d
      if (entry.compareTo (node.entry) == 0) 
         return node;
      else if (entry.compareTo (node.entry) < 0)
         /*if (node.left == null)
          *    return null;
          * else
          *    return find(d, node.left);
          */
         return (node.left == null) ? null : find (entry, node.left);
      else
         return (node.right == null) ? null : find (entry, node.right);
   }
   
   public void delete ( Entry entry )
   {
      root = delete (entry, root);
   }   
   public BinaryTreeNode<Entry>  delete ( Entry entry, BinaryTreeNode<Entry>  node )
   {
      if (node == null) return null;
      if (entry.compareTo (node.entry) < 0)
         node.left = delete (entry, node.left);
      else if (entry.compareTo (node.entry) > 0)
         node.right = delete (entry, node.right);
      else if (node.left != null && node.right != null )
      {
         node.entry = findMin (node.right).entry;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   public BinaryTreeNode<Entry>  findMin ( BinaryTreeNode<Entry>  node )
   {
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   public BinaryTreeNode<Entry>  removeMin ( BinaryTreeNode<Entry>  node )
   {
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
}
