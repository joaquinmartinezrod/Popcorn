package Popcorn;

import java.util.Random;
import java.util.TimerTask;

public class Popcorn {
    int popcorn = 0;
    int time;
    int pop;

    int randomTime;
    int randomPopcorn;
    int randomPop;

    public int getTimecounter() {
        return time;
    }
    public void setTimecounter(int timecounter) {
        this.time = timecounter;
    }
    public int getTime() {
        return time;
    }
    public int getPopcorn() {
        return popcorn;
    }
    public void calculateRandomTime(){
        Random r = new Random();
        int low = 90;
        int high = 130;
        randomTime = r.nextInt(high-low) + low;
        //randomTime = (int) (Math.random() * 110) + 90;
        //randomTime = Math.floor(Math.random()*(90-110+1)+110);
    }
    public int getRandomTime(){
        return randomTime;
    }
    public void calculateRandomPop(){
        Random r = new Random();
        int low = 400;
        int high = 500;
        pop = r.nextInt(high-low) + low;

    }
    public int getRandomPop(){
        return pop;
    }
}
