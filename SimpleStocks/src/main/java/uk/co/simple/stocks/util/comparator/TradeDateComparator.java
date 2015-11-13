/**
 * 
 */
package uk.co.simple.stocks.util.comparator;

import java.util.Comparator;

import uk.co.simple.stocks.model.Trade;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeDateComparator.
 */
public class TradeDateComparator implements Comparator<Trade> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Trade trade0, Trade trade1) {
		return trade1.getMoment().compareTo(trade0.getMoment());
	}

}
