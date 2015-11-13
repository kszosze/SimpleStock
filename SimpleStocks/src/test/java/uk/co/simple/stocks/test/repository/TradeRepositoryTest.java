/**
 * 
 */
package uk.co.simple.stocks.test.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static uk.co.simple.stocks.test.fixtures.TestFixtures.trade;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.repository.TradeRepository;
import uk.co.simple.stocks.test.CommonTest;


public class TradeRepositoryTest extends CommonTest{

	@Autowired
	private TradeRepository tradeRepository;
	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.TradeRepositoryImpl#save(uk.co.simple.stocks.model.Trade)}.
	 */
	@Test
	public final void testSave() {
		tradeRepository.save(trade());
		assertNotNull(tradeRepository.findBySymbol(trade().getSymbol()));
	}

	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.TradeRepositoryImpl#findBySymbol(java.lang.String)}.
	 */
	@Test
	public final void testFindBySymbol() {
		tradeRepository.save(trade());
		assertNotNull(tradeRepository.findBySymbol(trade().getSymbol()));
	}

	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.TradeRepositoryImpl#findBySymbolAndTopTime(java.lang.String, java.sql.Timestamp)}.
	 */
	@Test
	public final void testFindBySymbolAndTopTime() {
		Trade trade = trade();
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		tradeRepository.save(trade);
		assertNotNull(tradeRepository.findBySymbolAndTopTime("ALE",LocalDateTime.of(2015, 11, 12, 21, 35)));
		assertThat(tradeRepository.findBySymbolAndTopTime("ALE",LocalDateTime.of(2015, 11, 12, 21, 35)),hasItem(trade));
	}

	/**
	 * Test method for {@link uk.co.simple.stocks.repository.impl.TradeRepositoryImpl#findBySymbolAndTimestamp(java.lang.String, java.sql.Timestamp)}.
	 */
	@Test
	public final void testFindBySymbolAndTimestamp()
	{
		Trade trade = trade();
		trade.setQuantity(2.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(4.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 22, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(5.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 23, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(6.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 13, 01, 40));
		tradeRepository.save(trade);
		assertNotNull(tradeRepository.findBySymbolAndTimestamp("ALE",LocalDateTime.of(2015, 11, 12, 22, 40)));
		assertThat(tradeRepository.findBySymbolAndTimestamp("ALE",LocalDateTime.of(2015, 11, 12, 22, 40)),hasProperty("quantity", equalTo(4.0)));	
	}
	
	@Test
	public final void testFindLastBySymbol()
	{
		Trade trade = trade();
		trade.setQuantity(2.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(6.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 13, 01, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(4.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 22, 40));
		tradeRepository.save(trade);
		trade = trade();
		trade.setQuantity(5.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 23, 40));
		tradeRepository.save(trade);		
		assertNotNull(tradeRepository.findLastBySymbol("ALE"));
		assertThat(tradeRepository.findLastBySymbol("ALE"),hasProperty("quantity", equalTo(6.0)));		
	}
}
