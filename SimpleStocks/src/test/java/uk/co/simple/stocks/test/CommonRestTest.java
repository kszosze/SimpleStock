/**
 * 
 */
package uk.co.simple.stocks.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uk.co.simple.stocks.Application;
import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.services.StockService;
import uk.co.simple.stocks.services.TradeService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public abstract class CommonRestTest {

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private StockService stockService;
    
    @Autowired
    private TradeService tradeService;
    
    @Before
    public void setup() throws Exception {
        this.setMockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext).build());
        
        final Resource initData = webApplicationContext.getResource("classpath:refValues.json");
        final JsonFactory f = new JsonFactory();
		JsonParser jp;
		try {
			jp = f.createParser(initData.getFile());
			jp.nextToken();
			final ObjectMapper mapper = new ObjectMapper();
			while (jp.nextToken() == JsonToken.START_OBJECT) {
				stockService.save(mapper.readValue(jp, Stock.class));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    @After
    public void setDown()
    {
    	stockService.clean();
    	tradeService.clean();
    }

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
    
}
