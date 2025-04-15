package dto.buyer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class KockFarm {
	Long kockNum;
	Long userNum;
	Long cateNum;
	String title;
	Integer quantity;
	Integer price;
	LocalDate shipDate;
	String content;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	boolean isMatched;
	String imgUrl;
	
	String userName;
	String name;
	
	public KockFarm() {
	}
	public KockFarm(Long kockNum, Long userNum, Long cateNum, String title, Integer quantity, Integer price,
			LocalDate shipDate, String content, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isMatched, String imgUrl) {
		this.kockNum = kockNum;
		this.userNum = userNum;
		this.cateNum = cateNum;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.shipDate = shipDate;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isMatched = isMatched;
		this.imgUrl = imgUrl;
	}
	
	public KockFarm(Long userNum, Long cateNum, String title, Integer quantity, Integer price, LocalDate shipDate,
			String content, String imgUrl) {
		this.userNum = userNum;
		this.cateNum = cateNum;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.shipDate = shipDate;
		this.content = content;
		this.imgUrl = imgUrl;
	}
	
	
	public KockFarm(Long kockNum, String title, Integer quantity, Integer price, LocalDate shipDate, String content,
			String imgUrl, String userName, String name) {
		this.kockNum = kockNum;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.shipDate = shipDate;
		this.content = content;
		this.imgUrl = imgUrl;
		this.userName = userName;
		this.name = name;
	}
	public Long getKockNum() {
		return kockNum;
	}
	public void setKockNum(Long kockNum) {
		this.kockNum = kockNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Long getCateNum() {
		return cateNum;
	}
	public void setCateNum(Long cateNum) {
		this.cateNum = cateNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public LocalDate getShipDate() {
		return shipDate;
	}
	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public boolean isMatched() {
		return isMatched;
	}
	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
