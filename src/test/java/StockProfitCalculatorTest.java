import com.simcorp.stockprice.StockProfitCalculator;
import com.simcorp.stockprice.model.UnitPriceModel;
import com.simcorp.stockprice.response.StockProfitResponse;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Class that contains unit test methods StockProfitCalculator
 */
public class StockProfitCalculatorTest {

    private StockProfitCalculator stockProfitCalculator;



    /**
     * Injecting the StockProfitCalculator class
     */
    @Before
    public void setup() {
        stockProfitCalculator = new StockProfitCalculator();
    }


    /**
     * Unit test method to test if the buildStockPriceModel
     * returns non-empty list for valid Stock name
     */
    @Test
    public void buildStockPriceModelReturnsNonEmptyListTest() {
        List<UnitPriceModel> result = stockProfitCalculator.buildStockPriceModel("AAPL");
        assertNotNull( result);
        assert !result.isEmpty();
    }

    /**
     * Unit test method to test if the buildStockPriceModel
     * returns empty list for Invalid Stock name
     */
    @Test
    public void builduildStockPriceModelInvalidReturnsEmptyListTest() {
        List<UnitPriceModel> result = stockProfitCalculator.buildStockPriceModel("invalid");
        assertEquals(0, result.size());
    }


    /**
     * Unit test method to test if filterStockData
     * returns filtered data for valid Year
     */
    @Test
    public void filterStockDataDataForYearReturnsFilteredDataTest() {
        List<UnitPriceModel> stockDataList = new ArrayList<>();
        stockDataList.add(new UnitPriceModel(new Date(121, 0, 1), 10.0, 20.0, 5.0, 15.0, 15.0, 100));
        stockDataList.add(new UnitPriceModel(new Date(121, 5, 1), 12.0, 22.0, 6.0, 16.0, 16.0, 120));
        List<UnitPriceModel> result = stockProfitCalculator.filterStockData(stockDataList, 2021);
        assertEquals(2, result.size());
    }

    /**
     * Unit test method to test if filterStockData
     * returns empty data for Invalid Year
     */
    @Test
    public void filterStockDataNoDataForDesiredYearReturnsEmptyListTest() {
        List<UnitPriceModel> stockDataList = new ArrayList<>();
        stockDataList.add(new UnitPriceModel(new Date(120, 0, 1), 10.0, 20.0, 5.0, 15.0, 15.0, 100));
        stockDataList.add(new UnitPriceModel(new Date(122, 0, 1), 12.0, 22.0, 6.0, 16.0, 16.0, 120));

        List<UnitPriceModel> result = stockProfitCalculator.filterStockData(stockDataList, 2021);
        assertEquals(0, result.size());
    }

    /**
     * Unit test method to test if maxProfit
     * calculated the profit for Apple stock and year 2021
     */
    @Test
    public void maxProfitTest() {
        StockProfitResponse result = stockProfitCalculator.maxProfit("AAPL", 2021);
        System.out.println(result);
        assertEquals(65.92000600000001, result.getProfit(), 0.001);
        assertEquals(116.209999, result.getBuyPrice(), 0.001);
        assertEquals(182.130005, result.getSellPrice(), 0.001);
    }

    /**
     * Unit test method to test if maxProfit
     * calculated the empty profit for stock and invalid year
     */
    @Test
    public void maxProfitInvalidTest() {
        StockProfitResponse result = stockProfitCalculator.maxProfit("test", 202676);
        assertEquals(0.0, result.getProfit(), 0.001);
        assertEquals(null, result.getBuyDate());
        assertEquals(null, result.getSellDate());
        assertEquals(0.0, result.getBuyPrice(), 0.001);
        assertEquals(0.0, result.getSellPrice(), 0.001);
    }

    /**
     * Unit test method to test if maxProfit
     * calculated the empty profit for invalid stock
     */
    @Test
    public void maxProfitExceptionTest() {
        StockProfitResponse result = stockProfitCalculator.maxProfit("invalid", 2021);
        assertEquals(0.0, result.getProfit(), 0.001);
        assertEquals(null, result.getBuyDate());
        assertEquals(null, result.getSellDate());
        assertEquals(0.0, result.getBuyPrice(), 0.001);
        assertEquals(0.0, result.getSellPrice(), 0.001);
    }
}
