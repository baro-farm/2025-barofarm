package dto.buyer;

import java.util.List;

public class CartProductGroup {
	private String storeName;
	private Long productNum;
	private String productName;
	private String imgUrl;
	private int basePrice;
	private List<ShoppingCartItem> options; // 같은 상품의 여러 옵션들
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public List<ShoppingCartItem> getOptions() {
		return options;
	}

	public void setOptions(List<ShoppingCartItem> options) {
		this.options = options;
	}

	public CartProductGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

}
