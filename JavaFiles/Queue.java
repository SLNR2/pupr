public class Queue {
   private Node first, last;
   private int size;
  
   //constructor method to create a Queue object with first, last, and size. 
   public Queue()
   {
      first = null;  // pointer for the front of the queue
      last = null;  // pointer for the end of the queue
      size = 0;     // queue size
   }
  
//Add a dog to the queue
//Need to add an algorithm to connect to DB, check for dogs that the user has not voted on
   public void enqueue(int data) 
   {
      if (last == null) 
         first = last = new Node(data); //empty queue
      else 
      {
         last.next = new Node(data); //link new node as last node
         last = last.next; //make last pointer points to new last node
      }
      size++; //increase list size by one
   }

//Serve the Dog at the front of the Queue            
   public void dequeue(){
         Node temp = first; //temp points to the first node
         first = first.next; //make first point to the second node
         temp.next = null; //remove the link from the queue
         size--; //decrement size of the list
         if (first == null) last = null; //if there was only one node, list is now empty 
    }
   
   
   //Return the first dog at the front of the queue for the user to see prior to voting. Will need to display the fields in the appropriate spots of the GUI.
   public int front() {
      if (first != null)
         return this.first.data;
      else return -1; //if no elements in the queue
      }   

  
   //class to create nodes of the queue as objects
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
