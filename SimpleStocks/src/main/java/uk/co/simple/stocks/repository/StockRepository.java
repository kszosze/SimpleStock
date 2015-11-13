/**
 * 
 */
package uk.co.simple.stocks.repository;

import java.util.List;

import uk.co.simple.stocks.model.Stock;


// TODO: Auto-generated Javadoc
/**
 * The Interface StockRepository.
 */
public interface StockRepository {

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
	 * @param symbol the symbol
	 * @return the stock
	 */
	Stock findBySymbol(String symbol);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Stock> getAll();

	void clean();

}
