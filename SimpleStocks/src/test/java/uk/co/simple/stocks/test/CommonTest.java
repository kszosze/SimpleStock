/**
 * 
 */
package uk.co.simple.stocks.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.simple.stocks.Application;
import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.services.StockService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public abstract class CommonTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private StockService stockService;
	
	@Before
    public void setup() throws Exception {
        
        final Resource initData = applicationContext.getResource("classpath:refValues.json");
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
}
