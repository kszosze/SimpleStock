/**
 * 
 */
package uk.co.simple.stocks.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.repository.TradeRepository;
import uk.co.simple.stocks.services.TradeService;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeServiceImpl.
 */
@Service("TradeService")
public class TradeServiceImpl implements TradeService {

	/** The trade repository. */
	@Autowired
	private TradeRepository tradeRepository;
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.TradeService#save(uk.co.simple.stocks.model.Trade)
	 */
	public boolean save(Trade trade) {
		boolean result = true;
		if (trade != null)
		{
			result = tradeRepository.save(trade);
		}else 
		{
			result = false;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.TradeService#findBySymbol(java.lang.String)
	 */
	@Override
	public List<Trade> findBySymbol(String symbol) {
		return tradeRepository.findBySymbol(symbol);
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.TradeService#findLastBySymbol(java.lang.String)
	 */
	@Override
	public Trade findLastBySymbol(String symbol) {
		return tradeRepository.findLastBySymbol(symbol);
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.TradeService#findBySymbolAndTimestamp(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public Trade findBySymbolAndTimestamp(String symbol, LocalDateTime timestamp) {
		return tradeRepository.findBySymbolAndTimestamp(symbol,timestamp);
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.TradeService#findBySymbolAndTopTime(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public List<Trade> findBySymbolAndTopTime(String symbol,
			LocalDateTime timeWindow) {	
		return tradeRepository.findBySymbolAndTopTime(symbol, timeWindow);
	}

	@Override
	public void clean() {
		tradeRepository.clean();
	}

	
}
