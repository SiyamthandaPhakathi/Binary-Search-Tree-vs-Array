// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman



public class BinaryTree<Entry> 
{
   
   //declare root node of whatever type
   BinaryTreeNode<Entry>  root;

   //on declaration root node is null
   public BinaryTree ()
   {
      root = null;
   }
   //start checking height from the root
   public int getHeight ()
   {
      return getHeight (root);
   }
   //check height of subtree staring at input node
   public int getHeight ( BinaryTreeNode<Entry>  node )
   {
      /*
      *Recurively check for number of nodes in a subtree, if a node no longer has children we return 0
      * for that level
      * */
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft()), getHeight (node.getRight()));
   }
   
   public int getSize ()
   {
      return getSize (root);
   }   
   public int getSize ( BinaryTreeNode<Entry>  node )
   {  //this will count every node in the tree, starting from the leftmost node in the subtre going up to the root
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   //prints out data in the node
   public void visit ( BinaryTreeNode<Entry>  node )
   {
      System.out.println (node.entry);
   }
   
   public void preOrder ()
   {
      preOrder (root);
   }
   public void preOrder ( BinaryTreeNode<Entry>  node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   public void postOrder ()
   {
      postOrder (root);
   }
   public void postOrder ( BinaryTreeNode<Entry>  node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   public void inOrder ()
   {
      inOrder (root);
   }
   public void inOrder ( BinaryTreeNode<Entry>  node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }


}
