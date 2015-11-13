/**
 * 
 */
package uk.co.simple.stocks;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import uk.co.simple.stocks.model.Stock;
import uk.co.simple.stocks.services.StockService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class Application.
 */
@SpringBootApplication
@Configuration
public class Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		final Resource initData = ctx.getResource("classpath:refValues.json");

		final StockService stockService = ctx.getBean(StockService.class);
		final JsonFactory f = new JsonFactory();
		JsonParser jp;
		try {
			jp = f.createParser(initData.getFile());
			jp.nextToken();
			final ObjectMapper mapper = new ObjectMapper();
			while (jp.nextToken() == JsonToken.START_OBJECT) {
				stockService.save(mapper.readValue(jp, Stock.class));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
