/**
 * 
 */
package uk.co.simple.stocks.services;

import java.time.LocalDateTime;
import java.util.List;

import uk.co.simple.stocks.model.Trade;

// TODO: Auto-generated Javadoc
/**
 * The Interface TradeService.
 */
public interface TradeService {

	/**
	 * Save.
	 *
	 * @param trade the trade
	 * @return true, if successful
	 */
	boolean save(Trade trade);

	/**
	 * Find by symbol.
	 *
	 * @param tradeId the trade id
	 * @return the list
	 */
	List<Trade> findBySymbol(String tradeId);

	/**
	 * Find last by symbol.
	 *
	 * @param symbol the symbol
	 * @return the trade
	 */
	Trade findLastBySymbol(String symbol);

	/**
	 * Find by symbol and timestamp.
	 *
	 * @param symbol the symbol
	 * @param timestamp the timestamp
	 * @return the trade
	 */
	Trade findBySymbolAndTimestamp(String symbol, LocalDateTime timestamp);

	/**
	 * Find by symbol and top time.
	 *
	 * @param symbol the symbol
	 * @param timeWindow the time window
	 * @return the list
	 */
	List<Trade> findBySymbolAndTopTime(String symbol, LocalDateTime timeWindow);

	void clean();

}
