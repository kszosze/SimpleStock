# Super Simple Stock

Concept probe for a small application based in Maven + Spring-Boot + Java8 about a rest service for make easy calculations related to stock market.

The whole project runs by it's own keeping all the information in memory. No database it's used, and no persistent storage it's requiered. Data are lost when application ends.

### Version
1.0.0

### Description & Arquitecture

Application it's based in the spring-boot-started project, which provides all the necesary infraestructure to run it:

* [Jetty] - Light aplication container and embedded into the project.
* [Spring Framework] - Dependency Injection framework with web/rest capabilities.
* [Jackson] - JSon framework for data interchange. Used mainly to return data.

##### Design notes
- All the entry points where grouped in a controller due the small size of the project. Ideally Each element recorded will be in their own controller, with the normal CRUD functions, in the meantime all the operations can belong to one unique controller.
- There is a Service layer which add an abstraction level and in the future can support some bussiness logic
- Data management belongs to a repository layer which contains all the logic to store, retrive, remove data. That approach allow implement the usual DAO patter implementations or move into the spring-data approach.
- Operations implementations are under a Strategy pattern, initially implemented in one unique clase, that allow us replace it just only adding a ne clase with the same component name without need to rework all the code.
- There are a small set of initial data loaded from a JSON file hold in the classpath (resources folder in the source code)

### Install and run

This project it's selfcontained. Only needed is checkout the project with git and execute a clean install

```sh
$ mvn clean install
```

Once finish this step without errors (crossfingers) we can proceed to launch the application using maven

```sh
$ mvn mvn spring-boot:run
```

or using the generate jar in the target folder
```sh
$ java -jar target\SimpleStocks-0.0.1-SNAPSHOT.jar
```
### Instructions

Once the application it's running the following entry points are available:

- /simplestock/add/trade/{symbol}/{qShares}/{isSell}/{price} - Record a trade in the system. Return the Trade inserted in JSON Format.
  - {Symbol} the stock Symbol
  - {qShates} The number of shares involved in the trade. Decimal value a.e. 12.0
  - {isSell} Buy or Sell flag. Boolean value true if it's a sell, false if not.
  - {price} Price of the share. Decimal value.
- /simplestock/add/trade/{symbol}/{timestamp}/{qShares:.+}/{isSell}/{price:.+} - Record a trade in the system under an specific timestamp. Return the Trade inserted in JSON Format.
  - {Symbol} the stock Symbol
  - {timestamp} timestamp with the format yyyy-mm-ddTHH:MM:ss
  - {qShates} The number of shares involved in the trade. Decimal value a.e. 12.0
  - {isSell} Buy or Sell flag. Boolean value true if it's a sell, false if not.
  - {price} Price of the share. Decimal value.
- /simplestock/add/stock/{symbol}/{type}/{fixDiv}/{lastDiv}/{parValue} - Record a stock into the system. Return the Stock inserted in JSON Format.
  - {Symbol} the stock Symbol
  - {type} Type of the Stocl, one between Common or Preferred
  - {fixDiv} Fixed dividend. Integer value which represent a %
  - {lastDiv} Integer value which represent the last Dividend 
  - {parValue} Integer value which represent the PAR Value of face value
- /simplestock/operation/stock/divYield/{symbol} - Calculate the Yield Dividend for the stock; {symbol} will be the Stock Symbol, a.e.: POP, ALE, 
 Symbols must exist in the system. if not a -1 value will be returned.
- /simplestock/get/trade/{symbol} Return a list of al the recorded trades
  - {symbol} - The stock Symbol for which want retrieve all the trades.
- /simplestock/operation/stock/peratio/{symbol}/{timestamp} Calculate the P/E ratio for the specific Trade operation. -1 if hasn't been possible to calculate
  - {symbol} The stock symbol to retrieve the trade
  - {timestamp} The timestamp of the trade to calculate in the format yyyy-mm-ddTHH:MM:ss
- /simplestock/operation/stock/calprice/{symbol} Calculate the estimate price for an stock within the last 15 minutes as time window. -1 if hasn't been possible to calculate
  - {symbol} the stock symbol to calculate the price
- /simplestock/operation/geomean calculate the geometric mean for all the stocks.  -1 if hasn't been possible to calculate

### Development
Want to contribute? Great!


License
----

MIT


**Free Software, Hell Yeah!**