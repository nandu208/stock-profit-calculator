package com.simcorp.stockprice;

import com.simcorp.stockprice.model.UnitPriceModel;
import com.simcorp.stockprice.response.StockProfitResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 * Represents the profit calculator for stock prices
 */
public class StockProfitCalculator  {

    private static final Logger log = Logger.getLogger(StockProfitCalculator.class.getName());

    /**
     * Function that calculates the maximum profit for a stock by year
     * @param name name of the stock
     * @param year year for which the profit is calculated
     * @return StockProfitResponse that holds the maximum profit stock data
     */
    public StockProfitResponse maxProfit(String name, int year) {
        List<UnitPriceModel> filteredStockData = new ArrayList<>();
        StockProfitResponse response = new StockProfitResponse();
        List<UnitPriceModel> stockPriceModel = new ArrayList<>();
        int maxProfitHigh = 0;
        int maxProfitLow = 0;
        double maxProfit = 0;
        int currentIndex = 0;
        try {

            //1.0 Building the Stock Price model from csv for particular stock
            stockPriceModel = buildStockPriceModel(name);

            //2.0 If a year is specified filter out the data from StockPriceModel
            if(year != 0){
                filteredStockData = filterStockData(stockPriceModel, year);
            }else{
                filteredStockData = stockPriceModel;
            }

            // 3.0 if filteredStockData size is greater than 2 iterate over filteredStockData to find the max profit.
            if(filteredStockData.size()>=2) {
                for (int i = 1; i < filteredStockData.size(); i++) {
                    if (filteredStockData.get(i).getHigh() - filteredStockData.get(currentIndex).getLow() > maxProfit) {
                        maxProfitHigh = i;
                        maxProfitLow = currentIndex;
                        maxProfit = filteredStockData.get(i).getHigh() - filteredStockData.get(currentIndex).getLow();
                    }
                    if (filteredStockData.get(i).getLow() < filteredStockData.get(currentIndex).getLow()) {
                        currentIndex = i;
                    }
                }
                //4.0 set the values ton the response
                if(maxProfit > 0) {
                    response.setBuyDate(filteredStockData.get(maxProfitLow).getDate());
                    response.setBuyPrice(filteredStockData.get(maxProfitLow).getLow());
                    response.setSellDate(filteredStockData.get(maxProfitHigh).getDate());
                    response.setSellPrice(filteredStockData.get(maxProfitHigh).getHigh());
                    response.setProfit(maxProfit);
                }
                // If the stock is continously decreasing
                // in the year return object consists of buy date and
                // buy price which is lowest available price to buy the stock and sell
                // price is 0 because the price is continously decreasing and
                // sell date is null since we cannot sell without profit.
                else{
                    response.setBuyDate(filteredStockData.get(currentIndex).getDate());
                    response.setBuyPrice(filteredStockData.get(currentIndex).getLow());
                    response.setSellDate(null);
                    response.setSellPrice(0.0);
                    response.setProfit(maxProfit);
                }
            }

        } catch (Exception ex) {
            log.warning("Error While calculating maximum profit for Stock "+name+" and year "+ year + " with exception :" +ex.toString());
            return new StockProfitResponse();
        }
        return response;
    }

    /**
     * Builds the List of UnitPriceModel for a stock by name by parsing over csv
     * @param name name of the stock
     * @return List<UnitPriceModel> holds all the data of Stock Data
     */
    public List<UnitPriceModel> buildStockPriceModel(String name) {
        String csvFilePath = "src/main/resources/assets/" + name.toUpperCase() + ".csv";
        String line;
        List<UnitPriceModel> stockPriceModel = new ArrayList<>();
        try {
            //1.0 Reads data from file path
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            br.readLine();
            while((line= br.readLine()) != null){
                String[] fields = line.split(",");
                UnitPriceModel unitPriceModel = new UnitPriceModel();
                unitPriceModel.setDate(fields[0]);
                unitPriceModel.setOpen(Double.parseDouble(fields[1]));
                unitPriceModel.setHigh(Double.parseDouble(fields[2]));
                unitPriceModel.setLow(Double.parseDouble(fields[3]));
                unitPriceModel.setClose(Double.parseDouble(fields[4]));
                unitPriceModel.setAdjClose(Double.parseDouble(fields[5]));
                unitPriceModel.setVolume(Integer.parseInt(fields[6]));
                stockPriceModel.add(unitPriceModel);
            }
        } catch (Exception ex) {
            log.warning("Error While building Stock Price Model for "+name+" with exception :" +ex.toString());
            return new ArrayList<>();
        }
        return stockPriceModel;
    }


    /**
     * Filters of the stock data by the year given the user
     * @param stockDataList list that contains UnitPriceModel
     * @param year year to filter out the data
     * @return List<UnitPriceModel> that contains data only for the particular year
     */
    public List<UnitPriceModel> filterStockData(List<UnitPriceModel> stockDataList, int year){
        List<UnitPriceModel> filteredData = new ArrayList<>();
        if(!stockDataList.isEmpty()){
            for (UnitPriceModel data : stockDataList) {
                if (data.getDate().getYear()+1900 == year) {//In the java.util.Date class, the getYear() method returns the year minus 1900.
                    filteredData.add(data);
                }
            }
        }
        return  filteredData;
    }
}