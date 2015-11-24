package model;

public class RecordData {
    private MarketData marketData;
    private SymbolData symbolData;
    private int percentageOfShares;
    private int offeredShares;
    private int percentageOfWall;
    private int sharesOfWall;
    private double dollarAboveWall;
    private int percentageRemainingOfWall;
    private double mpp;
    private int mppLose;
    private int t1, t2, t3, t4;

    public RecordData(MarketData marketData, SymbolData symbolData, int percentageOfShares, int offeredShares, int percentageOfWall, int sharesOfWall,
                      double dollarAboveWall, int percentageRemainingOfWall, double mpp, int mppLose, int t1, int t2, int t3, int t4) {
        this.marketData = marketData;
        this.symbolData = symbolData;
        this.percentageOfShares = percentageOfShares;
        this.offeredShares = offeredShares;
        this.percentageOfWall = percentageOfWall;
        this.sharesOfWall = sharesOfWall;
        this.dollarAboveWall = dollarAboveWall;
        this.percentageRemainingOfWall = percentageRemainingOfWall;
        this.mpp = mpp;
        this.mppLose = mppLose;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    public MarketData getMarketData() {
        return marketData;
    }

    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
    }

    public SymbolData getSymbolData() {
        return symbolData;
    }

    public void setSymbolData(SymbolData symbolData) {
        this.symbolData = symbolData;
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

    public double getMpp() {
        return mpp;
    }

    public void setMpp(double mpp) {
        this.mpp = mpp;
    }

    public int getMppLose() {
        return mppLose;
    }

    public void setMppLose(int mppLose) {
        this.mppLose = mppLose;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }
}
