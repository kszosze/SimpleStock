/**
 * 
 */
package uk.co.simple.stocks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The Class Trade.
 */
public class Trade implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5775282055359480754L;
	
	/** The id. */
	private UUID id;
	
	/** The symbol. */
	private String symbol;
	
	/** The moment. */
	private LocalDateTime moment;
	
	/** The quantity. */
	private Double quantity;
	
	/** The sell. */
	private boolean sell;
	
	/** The price. */
	private Double price;
	
	/**
	 * Instantiates a new trade.
	 */
	public Trade() {
	}
	
	/**
	 * Instantiates a new trade.
	 *
	 * @param symbol the symbol
	 * @param moment the moment
	 * @param quantity the quantity
	 * @param sell the sell
	 * @param price the price
	 */
	public Trade(String symbol, LocalDateTime moment, Double quantity, boolean sell, Double price) {
		super();
		this.id=UUID.randomUUID();
		this.symbol = symbol;
		this.moment = moment;
		this.setQuantity(quantity);
		this.sell = sell;
		this.price = price;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(UUID id) {
		this.id = id;
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
	 * @param name the new symbol
	 */
	public void setSymbol(String name) {
		this.symbol = name;
	}
	
	/**
	 * Gets the moment.
	 *
	 * @return the moment
	 */
	public LocalDateTime getMoment() {
		return moment;
	}
	
	/**
	 * Sets the moment.
	 *
	 * @param moment the new moment
	 */
	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Checks if is sell.
	 *
	 * @return true, if is sell
	 */
	public boolean isSell() {
		return sell;
	}
	
	/**
	 * Sets the sell.
	 *
	 * @param sell the new sell
	 */
	public void setSell(boolean sell) {
		this.sell = sell;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moment == null) ? 0 : moment.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (sell ? 1231 : 1237);
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
		Trade other = (Trade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moment == null) {
			if (other.moment != null)
				return false;
		} else if (!moment.equals(other.moment))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (sell != other.sell)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trade [id=" + id + ", name=" + symbol + ", moment=" + moment
				+ ", sell=" + sell + ", price=" + price + "]";
	}
	
	
	
}
