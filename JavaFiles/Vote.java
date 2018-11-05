/* A class to define the voting algorithms


*/

public class Vote {
   /*
      1) Use Queue's .front() method to present the user with the next dog. In actual implementation, we'll need something more intricate to actually display all of the data.
      2) Apply the vote. Again, it'll be more intricate in the final implementation because it will have to wait for the user to actually click something.
      3) Perform calculations
      4) Check if leaderboard needs to be updated
      5) Add to votedOnBy
      
      
   */
   
    Scanner input = new Scanner(System.in);                                //what I used for CLI input - not needed here but I don't know how to GUI

    //This method goes through each entrant object in the VotingQueue and allows the user to vote on them (1-5)

    for (int i = 0; i < votingQueue.length; i++) {
        ballotMemeber = votingQueue[i];
        int thisVotesScore = 0;
        int dogsTotalRounds = getTotalRounds(ballotMemeber);                //NEW needs to be defined
        int dogsTotalStars = getTotalStars(ballotMemeber);                  //NEW needs to be defined
        double dogsAverageScore = getAverage(ballotMemeber);

        //Prompt user for input - cli
        System.out.println("Select rating 1 to 5");

        score = input.nextInt();

        //Same as using "cancel". Loop will go to the next dog and not count a vote.
        if (score == 0){
            continue
        }

        System.out.println("You have voted " + score);

        //Update the total number of stars 
        newTotalStars = (dogsTotalStars + score);
        setTotalStars(dogID, newTotalStars);

        newTotalRounds = (dogsTotalRounds++);
        setTotalRounds = (dogID, newTotalRounds);

        //Generate Average
        // Turn newTotalRounds and newTotalStars into doubles
        double newTotalStarsDouble = newTotalStars                           //9 would be 9.0
        double newTotalRoundsDouble = newTotalRounds                         //10 would be 10.0
        double percentage = (newTotalStarsDouble/(newTotalRoundsDouble*5))   //9 stars out of 10 would return 0.9, or 90%

        //Convert percentage into star average
        double newAverageDouble = percentage/2                               //0.9 (9%) would now be 4.5

        //Round newAverageDouble to 2 decimal points
        double newAverageDoubleRounded = Math.round(newAverageDouble * 100.0) / 100.0;   //There is no change to 4.5 here, but a number like 123.1299 would now be 123.13 - just looks cleaner

        //Update Dog object stats 
        setTotalStars(dogID, newTotalStars);
        setTotalRounds(dogID, newTotalRounds);
        setAverage(dogID, newAverageDoubleRounded)
                
      }

}