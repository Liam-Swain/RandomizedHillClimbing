import java.lang.Math;
import java.util.LinkedList;
import java.util.Random;


public class main {
    static int count = 0; // count variable to find local min
    public static boolean checkIfTerminate(LinkedList<Double> list, int p){ // checks immediate neighbors to see if its the lowest val
        boolean ret = true;
        double min = list.get(count);
        System.out.println("LIST SIZE: " + list.size());
        if(list.size() < count * p + p) // if we havent generated enough neighbors continue
            return false;

        if(list.size() >= p) { // see if there is a lower value in the neighbors, if so, continue climbing
            for (int j = count * p; j < count * p + p; j++) {
                if (list.get(j) < min)
                    ret = false;
            }
        }
        if(ret == false)
        {
            count++;
        }

        return ret;
    }

    public static void Task1(double StartX, double StartY, int p, double StartZ, int seed){
        LinkedList<Double> q = new LinkedList<>(); // acts as list for X and Y values
        LinkedList<Double> List = new LinkedList<>(); // acts as list for sol
        q.add(StartX);
        q.add(StartY);
        double sol;
        double x;
        double y;
        double min = 0;
        Random rand = new Random();
        rand.setSeed(seed); // set seed to what we pass in
        double z = (-1 * StartZ) + (StartZ - (-1 * StartZ)) * rand.nextDouble();
        double[] minArr = new double[2];
        int counter = 0;
        while(q.peek() != null){
            System.out.println("Counter: " + counter);
            x = q.get(counter);
            y = q.get(counter + 1);
            sol = RandomizedHillClimbing(new double[]{x, y});
            System.out.println(sol);
            List.add(sol);
            for(int i = 0; i < p; i++){ // this adds the neighbors to the X and Y list
                z = (-1 * StartZ) + (StartZ - (-1 * StartZ)) * rand.nextDouble();
                if(x + z > -512 && x + z < 512 && y + z > -512 && y + z < 512)
                {
                    q.add(x + z);
                    q.add(y + z);
                }
            }

            if(checkIfTerminate(List, p)){ // check to see if we terminate, if so, get min, x and y
                min = List.get(count);
                minArr[0] = q.get(2 * count);
                minArr[1] = q.get((2 * count + 1));
                break;
            }
            counter += 2;
        }
        System.out.println("Minx Value " + min + " Min X: " + minArr[0] + " Min Y: " + minArr[1]);
    }

    public static double RandomizedHillClimbing(double[] vecXY){
        double sol = 0;
        double x = vecXY[0];
        double y = vecXY[1]; // function we are minimizing
        sol = - 1 * (y + 47) * Math.sin(Math.sqrt(Math.abs(x / 2 + (y + 47)))) +
                (-1 * x * Math.sin(Math.sqrt(Math.abs(x - (y + 47)))));
        return sol;
    }


    public static void main(String[] args){
        Task1(404,504, 5, 3, 30); // call with start x, start y, p, start z and seed
    }
}
