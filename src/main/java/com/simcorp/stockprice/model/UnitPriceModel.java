package com.simcorp.stockprice.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a unit price model for stock prices.
 */
public class UnitPriceModel {

    private Date date;

    private double open;

    private double high;

    private double low;

    private double close;

    private double adjClose;

    private int volume;

    /**
     * Constructs a new UnitPriceModel with the specified parameters.
     *
     * @param date     the date of the price
     * @param open     the opening price
     * @param high     the highest price
     * @param low      the lowest price
     * @param close    the closing price
     * @param adjClose the adjusted closing price
     * @param volume   the trading volume
     */
    public UnitPriceModel(Date date, double open, double high, double low, double close, double adjClose, int volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }


    /**
    *  Getters and Setters for the fields
    */
    public UnitPriceModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     *
     * @return String representation of UnitPriceModel
     */
    @Override
    public String toString() {
        return "UnitPriceModel{" +
                "date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", adjClose=" + adjClose +
                ", volume=" + volume +
                '}';
    }
}
