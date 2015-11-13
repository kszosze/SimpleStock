package uk.co.simple.stocks.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.repository.TradeRepository;
import uk.co.simple.stocks.util.comparator.TradeDateComparator;

/**
 * The Class TradeRepositoryImpl.
 */
@Component("TradeRepository")
public class TradeRepositoryImpl implements TradeRepository{

	/** The memory map. */
	private final Map<String,List<Trade>> memoryMap = new LinkedHashMap<>();
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.TradeRepository#save(uk.co.simple.stocks.model.Trade)
	 */
	public boolean save(Trade trade) {
		final String key = trade.getSymbol().toUpperCase();
		if (!memoryMap.containsKey(key))
		{
			memoryMap.put(key, new ArrayList<Trade>());
		}
		return memoryMap.get(key).add(trade);
	}
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.TradeRepository#findBySymbol(java.lang.String)
	 */
	public List<Trade> findBySymbol(String symbol)
	{
		List<Trade> result= new ArrayList<>();
		
		if (memoryMap.containsKey(symbol.toUpperCase()))
		{
			result = memoryMap.get(symbol);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.TradeRepository#findBySymbolAndTopTime(java.lang.String, java.time.LocalDateTime)
	 */
	public List<Trade> findBySymbolAndTopTime(String symbol, LocalDateTime topTime)
	{
		List<Trade> result= new ArrayList<>();
		
		if (memoryMap.containsKey(symbol.toUpperCase()))
		{
			result = memoryMap.get(symbol).parallelStream().filter(p -> p.getMoment().isAfter(topTime)).collect(Collectors.toList());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.TradeRepository#findLastBySymbol(java.lang.String)
	 */
	@Override
	public Trade findLastBySymbol(String symbol) {
		Trade result = null;
		if (memoryMap.containsKey(symbol.toUpperCase()))
		{
			result = memoryMap.get(symbol).parallelStream().sorted(new TradeDateComparator()).findFirst().get();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.TradeRepository#findBySymbolAndTimestamp(java.lang.String, java.time.LocalDateTime)
	 */
	@Override
	public Trade findBySymbolAndTimestamp(String symbol, LocalDateTime timestamp) {
		Trade result = null;
		if (memoryMap.containsKey(symbol.toUpperCase()))
		{
			result = memoryMap.get(symbol).parallelStream().filter(p -> p.getMoment().isEqual(timestamp)).findFirst().get();
		}
		return result;
	}

	@Override
	public void clean() {
		memoryMap.clear();
	}
	
}
