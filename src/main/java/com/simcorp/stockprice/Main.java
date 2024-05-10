package com.simcorp.stockprice;


import com.simcorp.stockprice.response.StockProfitResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    // Initializing an empty Map to hold the stock names
    private static final Map<String, String> stockMap = new HashMap<>();

    // Adding values to an empty Map that hold the stock names
    static {
        stockMap.put("apple", "AAPL");
        stockMap.put("microsoft", "MSFT");
        stockMap.put("netflix", "NFLX");
        stockMap.put("tesla", "TSLA");
    }


    /**
     * Main method for the application take the required input parameters
     * @param args
     */
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object to read the input

        String stockName =  "";

        // 1.0 Reads the stock name and checks if it is empty or not
        while(stockName.isEmpty()){
            System.out.println("Stock Name cannot be empty");
            System.out.println("Enter the Stock Name from:" + stockMap.keySet());
            stockName =   myObj.nextLine();
        }

        // 2.0 Reads the stock name and checks it is appropriate or not
        while(!stockMap.containsKey(stockName.toLowerCase())){
            System.out.println("Enter the appropriate Stock Name from:" + stockMap.keySet());
            stockName =   myObj.nextLine();
        }

        // 3.0  Asks the user for the year to calculate the profit
        System.out.println("Enter Year to calculate:");
        String inputYear  = myObj.nextLine();
        int year = 0;
        if(!inputYear.isEmpty()){
            year = Integer.parseInt(inputYear);
        }

        //4.0 Executing the maxProfit function to calculate the profit
        System.out.println("Stock Name is: " + stockName.toUpperCase() +" And Year is : " + year);  // Output user input
        StockProfitCalculator stockPriceCalculator = new StockProfitCalculator();
        StockProfitResponse response = stockPriceCalculator.maxProfit(stockMap.get(stockName.toLowerCase()), year);
        System.out.println(response.getBuyDate() != null ? "To maximize the profit purchase stock on " + response.getBuyDate().toString() : "Buy date not available");
        System.out.println(response.getBuyPrice() != 0 ? "To maximize the profit buy the stock for " + response.getBuyPrice() : "Buy price not available");
        System.out.println(response.getSellDate() != null ? "To maximize the profit sell stock on " + response.getSellDate().toString() : "Sell date not available");
        System.out.println(response.getSellPrice() != 0 ? "To maximize the profit sell the stock for " + response.getSellPrice() : "Sell price not available");
        System.out.println(response.getProfit() != 0 ? "Maximum profit achievable is " + response.getProfit() : "No profit achievable");
    }
}