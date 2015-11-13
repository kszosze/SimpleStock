/**
 * 
 */
package uk.co.simple.stocks.controllers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.model.enums.StockType;
import uk.co.simple.stocks.services.StockService;
import uk.co.simple.stocks.services.TradeService;
import uk.co.simple.stocks.strategy.FormulaStrategy;

/**
 * The Class SimpleStockRest.
 */
@Controller
@RequestMapping("/simplestock")
public class SimpleStockRest {

	/** The trade service. */
	@Autowired
	private TradeService tradeService;

	/** The stock service. */
	@Autowired
	private StockService stockService;
	
	/** The formula strategy. */
	@Autowired
	private FormulaStrategy formulaStrategy;

	// Recording a trade, with timestamp, quantity of shares, buy or sell
	/**
	 * Adds the trade.
	 *
	 * @param symbol the symbol
	 * @param qShares the q shares
	 * @param isSell the is sell
	 * @param price the price
	 * @return the trade
	 */
	// indicator and price
	@RequestMapping(value = "/add/trade/{symbol}/{qShares:.+}/{isSell}/{price:.+}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Trade addTrade(@PathVariable String symbol,
			@PathVariable Double qShares, @PathVariable boolean isSell,
			@PathVariable Double price) {

		final Trade trade = new Trade(symbol, LocalDateTime.now(), qShares,
				isSell, price);

		tradeService.save(trade);

		return trade;
	}

	/**
	 * Adds the trade extended.
	 *
	 * @param symbol the symbol
	 * @param timestamp the timestamp
	 * @param qShares the q shares
	 * @param isSell the is sell
	 * @param price the price
	 * @return the trade
	 */
	@RequestMapping(value = "/add/trade/{symbol}/{timestamp}/{qShares:.+}/{isSell}/{price:.+}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Trade addTradeExtended(
			@PathVariable String symbol,
			@PathVariable String timestamp,
			@PathVariable Double qShares, 
			@PathVariable boolean isSell,
			@PathVariable Double price) {
		Trade trade = null;
		if (timestamp.matches("[\\d]{4}-[\\d\\d]-[\\d\\d]T[\\d\\d]:[\\d\\d]:[\\d\\d]")){
		trade = new Trade(symbol, LocalDateTime.parse(timestamp), qShares,
				isSell, price);

		tradeService.save(trade);
		}
		return trade;
	}
	
	/**
	 * Adds the stock.
	 *
	 * @param symbol the symbol
	 * @param type the type
	 * @param fixDiv the fix div
	 * @param lastDiv the last div
	 * @param parValue the par value
	 * @return the stock
	 */
	@RequestMapping(value="/add/stock/{symbol}/{type}/{fixDiv}/{lastDiv}/{parValue}", method=RequestMethod.POST)
	 @ResponseStatus(HttpStatus.OK)
	 public @ResponseBody Stock addStock(
			 @PathVariable String symbol,
			 @PathVariable String type,
			 @PathVariable Double fixDiv,
			 @PathVariable Double lastDiv,
			 @PathVariable Double parValue)
			 {
		 
		 final Stock stock = new Stock(symbol, StockType.valueOf(type), lastDiv, fixDiv,parValue);
		 
		 stockService.save(stock);
		 
		 return stock;
			 }

	/**
	 * Gets the all trade by symbol.
	 *
	 * @param symbol the symbol
	 * @return the all trade by symbol
	 */
	@RequestMapping(value = "/get/trade/{symbol}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Trade> getAllTradeBySymbol(
			@PathVariable String symbol) {
		return tradeService.findBySymbol(symbol);
	}

	/**
	 * Gets the div yield for symbol.
	 *
	 * @param symbol the symbol
	 * @return the div yield for symbol
	 */
	@RequestMapping(value = "/operation/stock/divYield/{symbol}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Double getDivYieldForSymbol(@PathVariable String symbol)
	{
		Double result = -1.0;
		if (!StringUtils.isEmpty(symbol)) {
			result = formulaStrategy.calcDivYield(symbol);
		}
		return result;
	}
	
	/**
	 * Gets the PE ratio for symbol.
	 *
	 * @param symbol the symbol
	 * @param timestamp the timestamp
	 * @return the PE ratio for symbol
	 */
	@RequestMapping(value = "/operation/stock/peratio/{symbol}/{timestamp}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Double getPERatioForSymbol(
			@PathVariable String symbol,
			@PathVariable String timestamp) {
		Double result = -1.0;
		if (!StringUtils.isEmpty(symbol) && !StringUtils.isEmpty(timestamp) && timestamp.matches("[\\d]{4}-[\\d\\d]-[\\d\\d]T[\\d\\d]:[\\d\\d]:[\\d\\d]"))
		{
			result = formulaStrategy.calcPERatio(symbol,LocalDateTime.parse(timestamp));
		}
		return result;
	}
	
	/**
	 * Gets the calc price for symbol.
	 *
	 * @param symbol the symbol
	 * @return the calc price for symbol
	 */
	@RequestMapping(value = "/operation/stock/calprice/{symbol}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Double getCalcPriceForSymbol(
			@PathVariable String symbol) {
		Double result = -1.0;
		if (!StringUtils.isEmpty(symbol))
		{
			final LocalDateTime timeWindow = LocalDateTime.now().minus(15, ChronoUnit.MINUTES);
			result = formulaStrategy.calcPrice(symbol,timeWindow);
		}
		return result;
	}
	
	/**
	 * Gets the calc price for symbol.
	 *
	 * @return the calc price for symbol
	 */
	@RequestMapping(value = "/operation/geomean", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Double getCalcPriceForSymbol() {
		
		return formulaStrategy.calcGeoMean();
	}
}
