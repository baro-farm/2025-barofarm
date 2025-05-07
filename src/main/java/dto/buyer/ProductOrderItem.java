package dto.buyer;

public class ProductOrderItem {
	Long orderItem;
	Long pdOrderNum;
	Long productNum;
	Long optionNum;
	Integer amount;
	Integer price;
	
	public ProductOrderItem() {
		
	}
	
	public ProductOrderItem(Long pdOrderNum, Long productNum, Integer amount, Integer price) {
		super();
		this.pdOrderNum = pdOrderNum;
		this.productNum = productNum;
		this.amount = amount;
		this.price = price;
	}

	public ProductOrderItem(Long orderItem, Long pdOrderNum, Long productNum, Integer amount, Integer price) {
		super();
		this.orderItem = orderItem;
		this.pdOrderNum = pdOrderNum;
		this.productNum = productNum;
		this.amount = amount;
		this.price = price;
	}

	public Long getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Long orderItem) {
		this.orderItem = orderItem;
	}

	public Long getPdOrderNum() {
		return pdOrderNum;
	}

	public void setPdOrderNum(Long pdOrderNum) {
		this.pdOrderNum = pdOrderNum;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(Long optionNum) {
		this.optionNum = optionNum;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
