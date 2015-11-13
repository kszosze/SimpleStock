package uk.co.simple.stocks.repository.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.repository.StockRepository;

/**
 * The Class StockRepositoryImpl.
 */
@Component("StockRepository")
public class StockRepositoryImpl implements StockRepository{

	/** The memory map. */
	private final Map<String,Stock> memoryMap = new LinkedHashMap<>();
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.StockRepository#save(uk.co.simple.stocks.model.Stock)
	 */
	public boolean save(Stock stock) {
		final String key = stock.getSymbol().toUpperCase();
		boolean result = false;
		if (!memoryMap.containsKey(key))
		{
			memoryMap.put(key,stock);
			result = true;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.StockRepository#findBySymbol(java.lang.String)
	 */
	public Stock findBySymbol(String symbol)
	{
		Stock result= null;
		
		if (memoryMap.containsKey(symbol.toUpperCase()))
		{
			result = memoryMap.get(symbol);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.repository.StockRepository#getAll()
	 */
	@Override
	public List<Stock> getAll() {		
		return memoryMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public void clean() {
		memoryMap.clear();
	}
	
	
}
