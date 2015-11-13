/**
 * 
 */
package uk.co.simple.stocks.strategy;

import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Interface FormulaStrategy.
 */
public interface FormulaStrategy {

	/**
	 * Calc div yield.
	 *
	 * @param symbol the symbol
	 * @return the double
	 */
	Double calcDivYield(String symbol);

	/**
	 * Calc pe ratio.
	 *
	 * @param symbol the symbol
	 * @param parse the parse
	 * @return the double
	 */
	Double calcPERatio(String symbol, LocalDateTime parse);

	/**
	 * Calc price.
	 *
	 * @param symbol the symbol
	 * @param timeWindow the time window
	 * @return the double
	 */
	Double calcPrice(String symbol, LocalDateTime timeWindow);

	/**
	 * Calc geo mean.
	 *
	 * @return the double
	 */
	Double calcGeoMean();
}
