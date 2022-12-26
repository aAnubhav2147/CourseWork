package lab8;

import java.util.*;

public class ExpressionTree {
	//Declare default elements of the tree
	public ExpressionTree right;
	public ExpressionTree left;
	public char data;

//Initialize the default elements using a constructor
	
ExpressionTree(char current) {
		//Initialize the node basis which the tree construction happens
		//MAKE SURE THE NAMING IS NOT AMBIGUOUS
		data = current;		
} 
	
	//Create a tree stack of the type ExpressionTree to add the nodes
	//from the root node origination
	//This stack restricts input type to numerics only. Refer line 30
public static ExpressionTree postFixToTree(String s) {
		Stack<ExpressionTree> stree = new Stack<>();
		//BST b = new BST();
		for(char ch : s.toCharArray()) {
			if (Character.isDigit(ch)) {
		        // leaf (literal)
		        stree.push(new ExpressionTree(ch));
		    } else {
		        // operator node
		        ExpressionTree tParent = new ExpressionTree(ch);

		        // add operands
		        tParent.right = stree.pop();
		        tParent.left = stree.pop();

		        // push result to operand stack
		        stree.push(tParent);
		    }
		}
		
		return stree.pop(); 
	} 
	
	//Method for In-Order traversal
public static void inOrder(ExpressionTree root) {
		if(root != null) {
			inOrder(root.left);
			//System.out.printf("%d ",postFixTree(root));
			//Utilize the initialized variable within the constructor for printing out
			//the tree output in a human readable form
			System.out.print(root.data + " ");
            inOrder(root.right);
            
		}
	}
	
	//Method for Pre-Order traversal
public static void preOrder(ExpressionTree root) {
		if(root != null) {
			//Utilize the initialized variable within the constructor for printing out
			//the tree output in a human readable form
			System.out.print(root.data + " ");
			preOrder(root.left);
            preOrder(root.right);
            
		}
	}
	
	//Method for Post-Order traversal
public static void postOrder(ExpressionTree root) {
		if(root != null) {			
			postOrder(root.left);
            postOrder(root.right);
          //Utilize the initialized variable within the constructor for printing out
			//the tree output in a human readable form
            System.out.print(root.data + " ");
		}
	}
	
public static void main(String[] args) {
		//Driver Method to check traversal
		ExpressionTree x = postFixToTree("123+*");
		//System.out.print(x);
		System.out.println("In-Order traversal for x \n");
		inOrder(x);
		System.out.println("\n");
		System.out.println("Pre-Order traversal for x \n");
		preOrder(x);
		System.out.println("\n");
		System.out.println("Post-Order traversal for x \n");
		postOrder(x);
		System.out.println("\n");
		
		ExpressionTree y = postFixToTree("5372-*-");
		System.out.println("In-Order traversal for y \n");
		inOrder(y);
		System.out.println("\n");
		System.out.println("Pre-Order traversal for y \n");
		preOrder(y);
		System.out.println("\n");
		System.out.println("Post-Order traversal for y \n");
		postOrder(y);
		System.out.println("\n");
		
        ExpressionTree z = postFixToTree("512+4*+3-");
        System.out.println("In-Order traversal for z \n");
		inOrder(z);
		System.out.println("\n");
		System.out.println("Pre-Order traversal for z \n");
		preOrder(z);
		System.out.println("\n");
		System.out.println("Post-Order traversal for z \n");
		postOrder(z);
		System.out.println("\n");
	}

}
