/**
 * 
 */
package uk.co.simple.stocks.test.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.simple.stocks.model.Trade;
import uk.co.simple.stocks.services.TradeService;
import uk.co.simple.stocks.test.CommonTest;
import static uk.co.simple.stocks.test.fixtures.TestFixtures.trade;

import java.time.LocalDateTime;
import java.util.List;

public class TradeServiceTest extends CommonTest{


	@Autowired
	private TradeService tradeService;
	
	@Test
	public final void testSave() {
		assertTrue(tradeService.save(trade()));
	}

	@Test
	public final void testFindBySymbol()
	{
		Trade trade = trade();
		assertTrue(tradeService.save(trade));
		List<Trade> tradeList = tradeService.findBySymbol("ALE");
		assertNotNull(tradeList);
		assertThat(tradeList,hasItem(trade));
	}
	
	@Test
	public final void testFindBySymbolAndTimestamp()
	{
		Trade trade = trade();
		trade.setQuantity(2.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(4.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 22, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(5.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 23, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(6.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 13, 01, 40));
		assertTrue(tradeService.save(trade));
		Trade tradeDB = tradeService.findBySymbolAndTimestamp("ALE",LocalDateTime.of(2015, 11, 12, 22, 40));
		assertNotNull(tradeDB);
		assertThat(tradeDB,hasProperty("quantity", equalTo(4.0)));	
	}
	
	@Test
	public final void testFindLastBySymbol()
	{
		Trade trade = trade();
		trade.setQuantity(2.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(6.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 13, 01, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(4.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 22, 40));
		assertTrue(tradeService.save(trade));
		trade = trade();
		trade.setQuantity(5.0);
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 23, 40));
		assertTrue(tradeService.save(trade));
		Trade tradeDB = tradeService.findLastBySymbol("ALE");
		assertNotNull(tradeDB);
		assertThat(tradeDB,hasProperty("quantity", equalTo(6.0)));		
	}
	/**
	 * Test method for {@link uk.co.simple.stocks.service.impl.TradeServiceImpl#findBySymbolAndTopTime(java.lang.String, java.sql.Timestamp)}.
	 */
	@Test
	public final void testFindBySymbolAndTopTime() {
		Trade trade = trade();
		trade.setMoment(LocalDateTime.of(2015, 11, 12, 21, 40));
		assertTrue(tradeService.save(trade));
		List<Trade> tradeList = tradeService.findBySymbolAndTopTime("ALE",LocalDateTime.of(2015, 11, 12, 21, 35));
		assertNotNull(tradeList);
		assertThat(tradeList,hasItem(trade));
	}

}
