package model;

/**
 * Created by junjue on 11/24/2015.
 */
public class MPP {
    private double mPP;
    private int mPPLose;

    public MPP(double mPP, int mPPLose) {
        this.mPP = mPP;
        this.mPPLose = mPPLose;
    }

    public double getmPP() {
        return mPP;
    }

    public void setmPP(double mPP) {
        this.mPP = mPP;
    }

    public int getmPPLose() {
        return mPPLose;
    }

    public void setmPPLose(int mPPLose) {
        this.mPPLose = mPPLose;
    }
}
