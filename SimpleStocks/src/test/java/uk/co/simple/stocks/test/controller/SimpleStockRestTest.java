/**
 * 
 */
package uk.co.simple.stocks.test.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import uk.co.simple.stocks.test.CommonRestTest;

public class SimpleStockRestTest extends CommonRestTest{

	/**
	 * Test method for {@link uk.co.simple.stocks.controllers.SimpleStockRest#addStock(java.lang.String, java.sql.Timestamp, java.lang.Double, boolean, java.lang.Double)}.
	 */
	@Test
	public final void testAddStock() throws Exception {
		getMockMvc().perform(post("/simplestock/add/trade/ALE/1.00/true/5.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("5.67")));
	}
	@Test
	public final void tstGetAllStockBySymbol() throws Exception {
		getMockMvc().perform(post("/simplestock/add/trade/ALE/1.00/true/5.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("5.67")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/3.00/true/7.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("7.67")));
		getMockMvc().perform(post("/simplestock/add/trade/POT/3.00/true/1.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("POT")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/7.00/true/8.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("8.67")));
		
		getMockMvc().perform(get("/simplestock/get/trade/ALE")).andExpect(status().isOk())
		.andExpect(content().string(containsString("ALE")));
	}
	
	@Test
	public final void testGetDivYieldForSymbol() throws Exception {		
		getMockMvc().perform(post("/simplestock/add/trade/ALE/1.00/true/5.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("5.67"))).andReturn();
		getMockMvc().perform(get("/simplestock/operation/stock/divYield/ALE")).andExpect(status().isOk())
		.andExpect(content().string(containsString("4.056")));
	}
	
	@Test
	public final void testGetCalcPriceForSymol() throws Exception {
		getMockMvc().perform(post("/simplestock/add/trade/ALE/1.00/true/5.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("5.67")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/3.00/true/7.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("7.67")));
		getMockMvc().perform(post("/simplestock/add/trade/POP/3.00/true/1.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("POP")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/7.00/true/8.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("8.67")));
		
		getMockMvc().perform(get("/simplestock/operation/stock/calprice/ALE")).andExpect(status().isOk())
		.andExpect(content().string(containsString("8.1245")));
	}
	
	@Test
	public final void testGetGeometricMean() throws Exception {
		getMockMvc().perform(post("/simplestock/add/trade/ALE/1.00/true/5.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("5.67")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/3.00/true/7.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("7.67")));
		getMockMvc().perform(post("/simplestock/add/trade/POP/4.00/true/1.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("POP")));
		getMockMvc().perform(post("/simplestock/add/trade/POP/5.00/true/6.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("POP")));
		getMockMvc().perform(post("/simplestock/add/trade/POP/1.00/true/11.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("POP")));
		getMockMvc().perform(post("/simplestock/add/trade/ALE/7.00/true/8.67")).andExpect(status().isOk())
		.andExpect(content().string(containsString("8.67")));
		
		getMockMvc().perform(get("/simplestock/operation/geomean")).andExpect(status().isOk())
		.andExpect(content().string(containsString("6.481")));
	}
}
