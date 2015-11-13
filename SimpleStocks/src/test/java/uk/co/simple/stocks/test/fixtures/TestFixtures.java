/**
 * 
 */
package uk.co.simple.stocks.test.fixtures;

import java.time.LocalDateTime;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.model.enums.StockType;

public class TestFixtures {

	public static Trade trade()
	{
		return new Trade("ALE",LocalDateTime.of(2015, 11, 12, 21, 23),1.0,true,2.3);
	}
	
	public static Stock stock()
	{
		return new Stock("CAF",StockType.Common,2.4,6.0,3.6);
	}
}
