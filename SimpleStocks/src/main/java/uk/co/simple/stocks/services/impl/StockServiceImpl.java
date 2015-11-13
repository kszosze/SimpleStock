/**
 * 
 */
package uk.co.simple.stocks.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.repository.StockRepository;
import uk.co.simple.stocks.services.StockService;

// TODO: Auto-generated Javadoc
/**
 * The Class StockServiceImpl.
 */
@Service("StockService")
public class StockServiceImpl implements StockService {

	/** The stock repository. */
	@Autowired
	private StockRepository stockRepository;
	
	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.StockService#save(uk.co.simple.stocks.model.Stock)
	 */
	public boolean save(Stock stock) {
		boolean result = true;
		if (stock != null)
		{
			result = stockRepository.save(stock);
		}else 
		{
			result = false;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.StockService#findBySymbol(java.lang.String)
	 */
	@Override
	public Stock findBySymbol(String symbol) {
		return stockRepository.findBySymbol(symbol);
	}

	/* (non-Javadoc)
	 * @see uk.co.simple.stocks.services.StockService#getAll()
	 */
	@Override
	public List<Stock> getAll() {		
		return stockRepository.getAll();
	}

	@Override
	public void clean() {
		stockRepository.clean();
	}

}
