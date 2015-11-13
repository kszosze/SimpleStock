package uk.co.simple.stocks.model;

import uk.co.simple.stocks.model.enums.StockType;

/**
 * The Class Stock.
 */
public class Stock {

	/** The symbol. */
	private String symbol;
	
	/** The type. */
	private StockType type;
	
	/** The last div. */
	private Double lastDiv;
	
	/** The fix div. */
	private Double fixDiv;
	
	/** The par value. */
	private Double parValue;
	
	/**
	 * Instantiates a new stock.
	 */
	public Stock()
	{}
	
	/**
	 * Instantiates a new stock.
	 *
	 * @param symbol the symbol
	 * @param type the type
	 * @param lastDiv the last div
	 * @param fixDiv the fix div
	 * @param parValue the par value
	 */
	public Stock(String symbol, StockType type, Double lastDiv, Double fixDiv,
			Double parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDiv = lastDiv;
		this.fixDiv = fixDiv;
		this.parValue = parValue;
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Sets the symbol.
	 *
	 * @param symbol the new symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public StockType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(StockType type) {
		this.type = type;
	}
	
	/**
	 * Gets the last div.
	 *
	 * @return the last div
	 */
	public Double getLastDiv() {
		return lastDiv;
	}
	
	/**
	 * Sets the last div.
	 *
	 * @param lastDiv the new last div
	 */
	public void setLastDiv(Double lastDiv) {
		this.lastDiv = lastDiv;
	}
	
	/**
	 * Gets the fix div.
	 *
	 * @return the fix div
	 */
	public Double getFixDiv() {
		return fixDiv;
	}
	
	/**
	 * Sets the fix div.
	 *
	 * @param fixDiv the new fix div
	 */
	public void setFixDiv(Double fixDiv) {
		this.fixDiv = fixDiv;
	}
	
	/**
	 * Gets the par value.
	 *
	 * @return the par value
	 */
	public Double getParValue() {
		return parValue;
	}
	
	/**
	 * Sets the par value.
	 *
	 * @param parValue the new par value
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixDiv == null) ? 0 : fixDiv.hashCode());
		result = prime * result + ((lastDiv == null) ? 0 : lastDiv.hashCode());
		result = prime * result
				+ ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (fixDiv == null) {
			if (other.fixDiv != null)
				return false;
		} else if (!fixDiv.equals(other.fixDiv))
			return false;
		if (lastDiv == null) {
			if (other.lastDiv != null)
				return false;
		} else if (!lastDiv.equals(other.lastDiv))
			return false;
		if (parValue == null) {
			if (other.parValue != null)
				return false;
		} else if (!parValue.equals(other.parValue))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", type=" + type + ", lastDiv="
				+ lastDiv + ", fixDiv=" + fixDiv + ", parValue=" + parValue
				+ "]";
	}
	
}
