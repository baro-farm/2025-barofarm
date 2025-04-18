package dto.seller;

import java.time.LocalDateTime;

public class ProductOption {
	Long optionNum;
	Long productNum;
	String option;
	Integer price;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

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

	public ProductOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductOption(Long productNum, String option, Integer price) {
		super();
		this.productNum = productNum;
		this.option = option;
		this.price = price;
	}

	public ProductOption(Long productNum, String option, Integer price, LocalDateTime updatedAt) {
		super();
		this.productNum = productNum;
		this.option = option;
		this.price = price;
		this.updatedAt = updatedAt;
	}
}
