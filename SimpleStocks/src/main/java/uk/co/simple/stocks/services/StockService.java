/**
 * 
 */
package uk.co.simple.stocks.services;

import java.util.List;

import uk.co.simple.stocks.model.Stock;

// TODO: Auto-generated Javadoc
/**
 * The Interface StockService.
 */
public interface StockService {

	/**
	 * Save.
	 *
	 * @param trade the trade
	 * @return true, if successful
	 */
	boolean save(Stock trade);

	/**
	 * Find by symbol.
	 *
	 * @param tradeId the trade id
	 * @return the stock
	 */
	Stock findBySymbol(String tradeId);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Stock> getAll();

	void clean();

}
