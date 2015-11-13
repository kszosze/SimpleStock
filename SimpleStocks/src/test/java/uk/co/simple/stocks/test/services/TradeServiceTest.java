/**
 * 
 */
package uk.co.simple.stocks.test.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.simple.stocks.services.TradeService;
import uk.co.simple.stocks.test.CommonTest;
import static uk.co.simple.stocks.test.fixtures.TestFixtures.trade;

public class TradeServiceTest extends CommonTest{


	@Autowired
	private TradeService tradeService;
	
	@Test
	public final void testSave() {
		assertTrue(tradeService.save(trade()));
	}

}
