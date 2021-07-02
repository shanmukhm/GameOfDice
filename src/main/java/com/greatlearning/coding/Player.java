package com.greatlearning.coding;

public class Player {
    private String name;
    private int totalScore;
    private int rank;
    private int prevRolledVal;
    private boolean skipNext;

    public Player(String name) {
        this.name = name;
    }

    public Integer roll() {
        // Generating a random number between [1,6]
        int rolledVal = (int) Math.floor(Math.random() * 6 + 1);
        totalScore += rolledVal;
        if(rolledVal == 1 && prevRolledVal == 1) {
            skipNext = true;
            System.out.println(this.name + ", You rolled 1 twice consecutively. You will be skipped in the next round.");
        } else {
            if(rolledVal == 6) {
                System.out.println("Awesome! You rolled 6, you get to roll again.");
            } else {
                System.out.println(this.name + " rolled " + rolledVal);
            }
        }
        prevRolledVal = rolledVal;
        return rolledVal;
    }

    public void print() {
        System.out.println("| " + this.name + " |   " + this.totalScore + "   |   " + this.rank + "   |");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPrevRolledVal() {
        return prevRolledVal;
    }

    public void setPrevRolledVal(int prevRolledVal) {
        this.prevRolledVal = prevRolledVal;
    }

    public boolean getSkipNext() {
        return skipNext;
    }

    public void setSkipNext(boolean skipNext) {
        this.skipNext = skipNext;
    }
}
