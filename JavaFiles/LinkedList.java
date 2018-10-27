public class LinkedList
{
   private Node head, tail;
   private int size;
  
   //constructor method to create a list object with head, tail, and size. 
   public LinkedList()
   {
      head = null;  // list head pointer
      tail = null;  // list tail pointer
      size = 0;     // list size
   }
  
   //method to add a node to the end of list
   public void addLastNode(int data) 
   {
      if (tail == null) 
         head = tail = new Node(data); //empty list
      else 
      {
         tail.next = new Node(data); //link new node as last node
         tail = tail.next; //make tail pointer points to new last node
      }
      size++; //increase list size by one
   }
   
      
   //method to add a new node as the first node in the list
   public void addFirstNode(int data)
   {
      //complete this method using the pesudocode discussed in class
      Node newNode = new Node(data); //create new node
      newNode.next = head; //link new node as the first node
      head = newNode; //make head reference the first node
      size++; //increment the list size
      if (tail == null) tail = head; //if list empty, make tail reference head
   }
      
   //method to add a node at specific position, namely index
   public void addAtIndex(int index, int data)
   {
      //complete this method using the pesudocode discussed in class
     
      if (index == 0) //add as first node
         addFirstNode(data);
      else if (index >= size) //add as last node
         addLastNode(data); 
         
      else { //in general case, move pointer to the appropriate node
         Node current = head; //current starts at the head of the list
         Node temp = head.next; //temp starts one node ahead of current
         for (int i = 1; i < index; i++) { //advance current and temp one node ahead
            current = current.next;
            temp = temp.next;
         }
         current.next = new Node(data); //insert new node after current
         (current.next).next = temp; //link temp after new node
         size++; //increment size of list
      }

   }
      
   //method to remove the first node from the list
   public void removeFirstNode()
   {
      //complete this method using the pesudocode discussed in class
      if (size == 0) System.out.print(""); //empty list
      else {
         head = head.next; //make head point to the second node
         size--; //decrement size of the list
         if (head == null) tail = null; //if there was only one node, list is now empty 
      }
   }
      
   //method to remove the last node from the list
   public void removeLastNode()
   {
      //complete this method using the pesudocode discussed in class
      if (size == 0) System.out.print(""); //empty list
      else if (size == 1) { //only one node
         head = tail = null; //remove the only node
         size = 0;
      }
      else { //general case
         Node current = head;
         for (int i = 0; i < size - 2; i++) //increment pointer to the next to last node
            current = current.next;
            
         tail = current; //set tail to next to last node
         tail.next = null; //no nodes after tail
         size--; //decrement size   
      }
     
   }
      
   //method to remove a node at specific position,namley index
   public void removeAtIndex(int index)
   {
      //complete this method using the pesudocode discussed in class
      if (index < 0 || index >= size) //invalid index selection
         System.out.print("(Invalid index selection... No changes made.)\n");
      else if (index == 0) removeFirstNode(); //special case
      else if (index == size - 1) removeLastNode(); //special case
         
      else { //general case
         Node previous = head; //point to head
         for (int i = 1; i < index; i++)
            previous = previous.next; //increment pointer previous
         
         Node current = previous.next; //create pointer current at the next node after previous
         previous.next = current.next; //link pointer previous to the node after current (i.e. remove the selected node)
         size--;

      }
   }
   
   //method to create a copy of the list
   public LinkedList copyList()
   {  
      //complete this method using the pesudocode discussed in class
      //This method returns a pointer to the new list, List2
      
      LinkedList List2 = new LinkedList();  //declare the list variable
      int size2 = 0;
     
      //Add code to perform the copying process
      
      Node head2 = null;  //head pointer for List2
      Node tail2 = null; //tail pointer for List2
      //special case 1: empty list
         if (head == null) List2.head = null;
         
      //special case 2: only  one node
         else if (size == 1) {
            List2.head = new Node(head.data); //create new node at the head of List2
            head2 = List2.head; //set head2 pointer to the new node
            (List2.head).next = null; //set next node to null
            tail2 = (List2.head).next; //set tail to new tail
            size2++;
         }      
         
         else {
            Node temp = head; //set pointer to original head
            List2.head = new Node(head.data); //create new node at head of List2
            head2 = tail2 = List2.head; //set head2 and tail2 pointers to the new node
            while (temp.next != null) {
               temp = temp.next; //advance temp
               tail2.next = new Node(temp.data); //create new node after current tail
               tail2 = tail2.next; //reassign tail2 pointer
               List2.tail = tail2; //reassign tail of List2 to the tail2 pointer
               size2++;   
            
            }
         }
      return List2;
   }



   //method to print out the list
   public void printList() 
   {
      if (head == null)  // if an empty list
         System.out.println("List is empty...");
      else               // if non-empty list
      {
         Node temp;
         temp = head;
         System.out.print("    ");
         while (temp != null)
         {
            System.out.print(temp.data + "    ");
            temp = temp.next;
         }
         System.out.println();
      }
   }
  
   //class to create nodes of the list as objects
   private class Node
   {
      private int data;  //data field
      private Node next; //link field
       
      public Node(int item) //constructor method
      {
         data = item;
         next = null;
      }
   }
}
