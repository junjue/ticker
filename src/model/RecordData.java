package model;

public class RecordData {
    private MarketData marketData;
    private SymbolData symbolData;
    private String profit;
    private String mmp;

    public RecordData(MarketData marketData, SymbolData symbolData, String profit, String mmp) {
        this.marketData = marketData;
        this.symbolData = symbolData;
        this.profit = profit;
        this.mmp = mmp;
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
}
