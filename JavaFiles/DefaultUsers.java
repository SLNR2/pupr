/* A class to simulate a Database by defining some default user profiles

*/

public class DefaultUsers {
   public static void main (String args[]){
   
      
      User user1 = new User ("Sloan", "Lipman", "slipman", "pupr123");
            User.userList.printList();
            System.out.println(user1.toString());
            user1.votingQueue.enqueue(1);
           System.out.println("First element in the queue is: " + user1.votingQueue.front());
   
   
      User user2 = new User ("Jimmy", "Smith", "jsmith", "pupr321");
            
         //Just running some tests here
            User.userList.printList();
             System.out.println(user2.getFirstName());
           System.out.println(user2.toString());
            user1.deleteAccount();
           System.out.println(user2.toString());
     
                    User.userList.printList();
                    
                   System.out.println(User.dog.toString());


   }
}