/**
 * 
 */
package uk.co.simple.stocks.repository;

import java.time.LocalDateTime;
import java.util.List;

import uk.co.simple.stocks.model.Trade;


// TODO: Auto-generated Javadoc
/**
 * The Interface TradeRepository.
 */
public interface TradeRepository {

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
	 * @param symbol the symbol
	 * @return the list
	 */
	List<Trade> findBySymbol(String symbol);
	
	/**
	 * Find by symbol and top time.
	 *
	 * @param symbol the symbol
	 * @param topTime the top time
	 * @return the list
	 */
	List<Trade> findBySymbolAndTopTime(String symbol, LocalDateTime topTime);
	
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

	void clean();

}
