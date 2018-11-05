package com.pupr;


    /* A class to simulate a Database by defining some default user profiles

     */

    public class DefaultUsers {
        public static void getUsers(){

            User user0 = new User ("Sloan", "Lipman", "slipman", "pupr123");
            System.out.println(user0.toString()); //Should say that user0 has an ID of 0 and that there is 1 user
            System.out.println("First element in the queue is: " + user0.votingQueue.peek()); //returns -1 because nothing is in queue
            System.out.println(user0.dog.toString()); //Sloan's dog's ownerid should match his userid of 0


            User user1 = new User ("Jimmy", "Smith", "jsmith", "pupr321");

            System.out.println(User.userList); //Will print an array of the users, using whatever toString() method is defined


            //Just running some tests here
            System.out.println(user1.getFirstName()); //returns Jimmy
            System.out.println(user1.toString()); //Jimmy Smith's ID is 1, and there are 2 users in the system

            user0.deleteAccount();
            System.out.println(user1.toString()); //Now there is only one user in the system


            System.out.println(user1.dog.toString()); //Jimmy's dog's ownerId should match his userid of 1


        }
    }

