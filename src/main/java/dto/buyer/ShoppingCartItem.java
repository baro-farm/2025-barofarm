package dto.buyer;

public class ShoppingCartItem {
	private Long cartNum;
	private Long productNum;
	private String productName;
	private String imgUrl;
	private Integer price;
	
	private Long optionNum;
	private String option;
	private Integer optionPrice;
	private Integer quantity;
	private Integer totalPrice;
	
	private String storeName;

	public Long getCartNum() {
		return cartNum;
	}

	public void setCartNum(Long cartNum) {
		this.cartNum = cartNum;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(Long optionNum) {
		this.optionNum = optionNum;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(Integer optionPrice) {
		this.optionPrice = optionPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public ShoppingCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartItem [cartNum=" + cartNum + ", productNum=" + productNum + ", productName=" + productName
				+ ", imgUrl=" + imgUrl + ", optionNum=" + optionNum + ", option=" + option + ", price=" + price
				+ ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", storeName=" + storeName + "]";
	}
	
	
}
