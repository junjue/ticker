package model;

/**
 * Created by junjue on 11/24/2015.
 */
public class BuySignal {

    private int percentageOfShares;
    private  int offeredShares;
    private  int percentageOfWall;
    private int sharesOfWall;
    private double dollarAboveWall;
    private int percentageRemainingOfWall;

    public BuySignal(int percentageOfShares, int offeredShares, int percentageOfWall, int sharesOfWall,
                     double dollarAboveWall, int percentageRemainingOfWall){
        this.percentageOfShares = percentageOfShares;
        this.offeredShares =  offeredShares;
        this.percentageOfWall = percentageOfWall;
        this.sharesOfWall = sharesOfWall;
        this.dollarAboveWall = dollarAboveWall;
        this.percentageRemainingOfWall = percentageRemainingOfWall;
    }

    public int getPercentageOfShares() {
        return percentageOfShares;
    }

    public void setPercentageOfShares(int percentageOfShares) {
        this.percentageOfShares = percentageOfShares;
    }

    public int getOfferedShares() {
        return offeredShares;
    }

    public void setOfferedShares(int offeredShares) {
        this.offeredShares = offeredShares;
    }

    public int getPercentageOfWall() {
        return percentageOfWall;
    }

    public void setPercentageOfWall(int percentageOfWall) {
        this.percentageOfWall = percentageOfWall;
    }

    public int getSharesOfWall() {
        return sharesOfWall;
    }

    public void setSharesOfWall(int sharesOfWall) {
        this.sharesOfWall = sharesOfWall;
    }

    public double getDollarAboveWall() {
        return dollarAboveWall;
    }

    public void setDollarAboveWall(double dollarAboveWall) {
        this.dollarAboveWall = dollarAboveWall;
    }

    public int getPercentageRemainingOfWall() {
        return percentageRemainingOfWall;
    }

    public void setPercentageRemainingOfWall(int percentageRemainingOfWall) {
        this.percentageRemainingOfWall = percentageRemainingOfWall;
    }
}
