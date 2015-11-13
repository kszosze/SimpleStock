/**
 * 
 */
package uk.co.simple.stocks.strategy.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.services.StockService;
import uk.co.simple.stocks.services.TradeService;
import uk.co.simple.stocks.strategy.FormulaStrategy;

// TODO: Auto-generated Javadoc
/**
 * The Class FormulaStrategyImpl.
 */
@Component("FormulaStrategy")
public class FormulaStrategyImpl implements FormulaStrategy {

	/** The trade service. */
	@Autowired
	private TradeService tradeService;
	
	/** The stock service. */
	@Autowired
	private StockService stockService;
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.strategy.FormulaStrategy#calcDivYield(java.lang.String)
	 */
	@Override
	public Double calcDivYield(String symbol) {
		Double result = -1.0;
		final Stock stock = stockService.findBySymbol(symbol);
		if (stock != null)
		{
			if (stock.getLastDiv() >0)
			{
				Trade trade = tradeService.findLastBySymbol(stock.getSymbol());
				if (trade != null)
				{
					if (stock.getLastDiv() > 0)
					{
						result = stock.getLastDiv()/trade.getPrice();
					}
				}
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.strategy.FormulaStrategy#calcPERatio(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public Double calcPERatio(String symbol, LocalDateTime timestamp) {
		Double result = -1.0;
		final Stock stock = stockService.findBySymbol(symbol);
		if (stock != null)
		{
			Trade trade = tradeService.findBySymbolAndTimestamp(symbol,timestamp);
			
			if (trade != null)
			{
				result = trade.getPrice()/calcEPS(stock);
			}
		}
		return result;
	}

	/**
	 * Calc eps.
	 *
	 * @param stock the stock
	 * @return the double
	 */
	private Double calcEPS(Stock stock) {
		Double result = 1.0;
		for (Trade trade : tradeService.findBySymbol(stock.getSymbol()))
		{
			if(trade.isSell()) 
			{
				result +=((trade.getPrice()*trade.getQuantity()) - (trade.getPrice()*trade.getQuantity()*stock.getFixDiv()/100.0)); 
			}else
			{
				result -=(trade.getPrice()*trade.getQuantity());
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.strategy.FormulaStrategy#calcPrice(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public Double calcPrice(String symbol,LocalDateTime timeWindow) {
		Double result = -1.0;
		final Stock stock = stockService.findBySymbol(symbol);
		if (stock != null)
		{
			result = calPriceStock(tradeService.findBySymbolAndTopTime(stock.getSymbol(),timeWindow));
		}
		return result;
	}

	/**
	 * Cal price stock.
	 *
	 * @param listTrades the list trades
	 * @return the double
	 */
	private Double calPriceStock(List<Trade> listTrades)
	{
		Double result = 0.0;
		Double tradePriceAcum = 0.0;
		Double shareQuantity = 0.0;
		for (Trade trade : listTrades)
		{
			tradePriceAcum += trade.getPrice()*trade.getQuantity();
			shareQuantity += trade.getQuantity();
		}
		if (tradePriceAcum > 0 && shareQuantity > 0)
		{
			result = tradePriceAcum / shareQuantity;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.strategy.FormulaStrategy#calcGeoMean()
	 */
	@Override
	public Double calcGeoMean() {
		final List<Stock> stockList = stockService.getAll();
		double valGeoMean = 1.0;
		int i = 0;
		for (Stock stock : stockList)
		{
			Double priceStock = calPriceStock(tradeService.findBySymbol(stock.getSymbol())).doubleValue();
			if (priceStock >0.0)
			{
				valGeoMean = valGeoMean * priceStock;
				i++;
			}
		}
		return Math.pow(valGeoMean, 1.0/i);
	}
	
	
}
