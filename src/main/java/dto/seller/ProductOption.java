package dto.seller;

import java.time.LocalDateTime;

public class ProductOption {
	Long optionNum;
	Long productNum;
	String option;
	Integer price;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	Integer stock;

	public Long getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(Long optionNum) {
		this.optionNum = optionNum;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public ProductOption() {
		super();
	}

	public ProductOption(String option, Integer price, Integer stock) {
		super();
		this.option = option;
		this.price = price;
		this.stock = stock;
	}

	public ProductOption(Long optionNum, String option, Integer price, Integer stock) {
		super();
		this.optionNum = optionNum;
		this.option = option;
		this.price = price;
		this.stock = stock;
	}

	public ProductOption(Long productNum, String option, Integer price, LocalDateTime updatedAt, Integer stock) {
		super();
		this.productNum = productNum;
		this.option = option;
		this.price = price;
		this.updatedAt = updatedAt;
		this.stock = stock;
	}

	public ProductOption(Long optionNum, Long productNum, String option, Integer price, LocalDateTime createdAt, LocalDateTime updatedAt, Integer stock) {
		super();
		this.optionNum = optionNum;
		this.productNum = productNum;
		this.option = option;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.stock = stock;
	}

	public ProductOption(Long optionNum, String option, Integer price, String stock) {
		super();
		this.optionNum = optionNum;
		this.option = option;
		this.price = price;
		this.stock = stock;
	}
	
}
