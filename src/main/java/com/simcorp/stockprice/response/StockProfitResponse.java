package com.simcorp.stockprice.response;

import java.util.Date;

/**
 * Represents the response after calculation the Stock Profit
 */
public class StockProfitResponse {

    private Date buyDate;

    private double buyPrice;

    private Date sellDate;

    private double sellPrice;

    private double profit;

    /**
     * Constructs a new StockProfitResponse with the specified parameters.
     *
     * @param buyDate      the buy date of the stock
     * @param buyPrice     the buy price of the stock
     * @param sellDate     the sell date of the stock
     * @param sellPrice    the sell price of the stock
     * @param profit       the profit obtained by selling the stock
     */
    public StockProfitResponse(Date buyDate, double buyPrice, Date sellDate, double sellPrice, double profit) {
        this.buyDate = buyDate;
        this.buyPrice = buyPrice;
        this.sellDate = sellDate;
        this.sellPrice = sellPrice;
        this.profit = profit;
    }

    public StockProfitResponse() {
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


    /**
     * @return String representation of StockPriceResponse
     */
    @Override
    public String toString() {
        return "StockPriceResponse{" +
                "buyDate=" + buyDate +
                ", buyPrice=" + buyPrice +
                ", sellDate=" + sellDate +
                ", sellPrice=" + sellPrice +
                ", profit=" + profit +
                '}';
    }
}
