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
   public void addLastNode(Object e) 
   {
      if (tail == null) 
         head = tail = new Node(e); //empty list
      else 
      {
         tail.next = new Node(e); //link new node as last node
         tail = tail.next; //make tail pointer points to new last node
      }
      size++; //increase list size by one
   }
   
      
//method to add a new node as the first node in the list
   public void addFirstNode(Object e)
   {
      //complete this method using the pesudocode discussed in class
      Node newNode = new Node(e); //create new node
      newNode.next = head; //link new node as the first node
      head = newNode; //make head reference the first node
      size++; //increment the list size
      if (tail == null) tail = head; //if list empty, make tail reference head
   }
      
//method to add a node at specific index
   public void addAtIndex(int index, Object e)
   {
     
      if (index == 0) //add as first node
         addFirstNode(e);
      else if (index >= size) //add as last node
         addLastNode(e); 
         
      else { //in general case, move pointer to the appropriate node
         Node current = head; //current starts at the head of the list
         Node temp = head.next; //temp starts one node ahead of current
         for (int i = 1; i < index; i++) { //advance current and temp one node ahead
            current = current.next;
            temp = temp.next;
         }
         current.next = new Node(e); //insert new node after current
         (current.next).next = temp; //link temp after new node
         size++; //increment size of list
      }

   }
      
   //method to remove the first node from the list
   public void removeFirstNode() {
      //complete this method using the pesudocode discussed in class
      if (size == 0) return; //empty list
      else {
         head = head.next; //make head point to the second node
         size--; //decrement size of the list
         if (head == null) tail = null; //if there was only one node, list is now empty 
      }
   }
      
//method to remove the last node from the list
   public void removeLastNode() {
      if (size == 0) return;
      else if (size == 1) { 
         head = tail = null; 
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
      if (index < 0 || index >= size) //invalid index selection
         return;
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
   

         
   
   
    public void removeId(int id) {
         Node current = head;
         int index = 0;
           if (current.e instanceof Integer) { 
            while (current != null) {
               if ((Integer)current.e == id) {
                  this.removeAtIndex(index);
                  return;
               }
            index++;
            current = current.next;
                  
            }
         }      
         return;         
   } 

//Method to transform the LinkedList into an array so that we can easily search it
   public Object[] toArray() {
      
      Object[] objArray = new Object[size]; //Declare an array of objects
      Node current = head; //pointer
      int position = 0; //array index
      while (current != null) { //go until the end 
         current = current.next;
         objArray[position] = current.e; //update the array
         position++; //increment index
      }
      return objArray;
      
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
            System.out.print(temp.e + "    ");
            temp = temp.next;
         }
         System.out.println();
      }
   }
  
   //class to create nodes of the list as objects
   private class Node
   {
      private Object e;  //e field
      private Node next; //link field
       
      public Node(Object item) //constructor method
      {
         e = item;
         next = null;
      }
   }
}
