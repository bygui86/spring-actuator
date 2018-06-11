package com.rabbitshop.springactuatorsample.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;


@Entity
@Table(name = "PRODUCT", uniqueConstraints = @UniqueConstraint(columnNames = "itemId"))
public class Product implements Serializable {

	@Id
	private String itemId;

	private String name;

	private String description;

	private double price;

	public Product() {

	}

	public String getItemId() {

		return itemId;
	}

	public void setItemId(final String itemId) {

		this.itemId = itemId;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public double getPrice() {

		return price;
	}

	public void setPrice(final double price) {

		this.price = price;
	}

	@Override
	public String toString() {

		return "Product [itemId=" + itemId + ", name=" + name + ", price=" + price + "]";
	}
}