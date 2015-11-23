package model;

public class RecordData {
    private MarketData marketData;
    private SymbolData symbolData;
    private String profit;
    private String mmp;
    private String mmp2;

    public RecordData(MarketData marketData, SymbolData symbolData, String profit, String mmp, String mmp2) {
        this.marketData = marketData;
        this.symbolData = symbolData;
        this.profit = profit;
        this.mmp = mmp;
        this.mmp2 = mmp2;
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

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getMmp() {
        return mmp;
    }

    public void setMmp(String mmp) {
        this.mmp = mmp;
    }
    public String getMmp2() {
        return mmp2;
    }

    public void setMmp2(String mmp2) {
        this.mmp2 = mmp2;
    }
}
