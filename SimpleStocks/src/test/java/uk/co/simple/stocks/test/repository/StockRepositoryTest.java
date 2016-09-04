/**
 * 
 */
package uk.co.simple.stocks.test.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static uk.co.simple.stocks.test.fixtures.TestFixtures.stock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.model.enums.StockType;
import uk.co.simple.stocks.repository.StockRepository;
import uk.co.simple.stocks.services.StockService;
import uk.co.simple.stocks.test.CommonTest;

public class StockRepositoryTest extends CommonTest {
	
	@Autowired
	private StockRepository stockRepository;
	
	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.StockRepository#save(uk.co.simple.stocks.model.Stock)}.
	 */
	@Test
	public final void testSave() {
		assertTrue(stockRepository.save(stock()));
	}

	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.StockRepository#findBySymbol(java.lang.String)}.
	 */
	@Test
	public final void testFindBySymbol() {
		assertThat(stockRepository.findBySymbol("TEA"),hasProperty("parValue", equalTo(100.0)));
	}

	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.StockRepository#getAll()}.
	 */
	@Test
	public final void testGetAll() {
		Stock stock = new Stock("TEA",StockType.Common,0.0,0.0,100.0);
		assertThat(stockRepository.getAll(),hasItem(stock));
	}
}