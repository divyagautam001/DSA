/*
O(1) Time

While currentPosition is not equal to destination:
Calculate the distance to the left as Math.min(Math.abs(destination - currentPosition), l).
Calculate the distance to the right as Math.min(Math.abs(destination - currentPosition), r).
Move the frog to the direction where the distance is lesser.
Increment steps by 1.
*/

public class FrogSteps {
    public static int minSteps(int start, int destination, int l, int r) {
        int currentPosition = start;
        int steps = 0;
        
        while (currentPosition != destination) {
            int distanceToLeft = Math.min(Math.abs(destination - currentPosition), l);
            int distanceToRight = Math.min(Math.abs(destination - currentPosition), r);
            
            if (destination > currentPosition) {
                currentPosition += distanceToRight;
            } else {
                currentPosition -= distanceToLeft;
            }
            
            steps++;
        }
        
        return steps;
    }
    
    public static void main(String[] args) {
        int start = 0;
        int destination = 10;
        int l = 3;
        int r = 5;
        
        int minSteps = minSteps(start, destination, l, r);
        System.out.println("Minimum steps required: " + minSteps);
    }
}
