package util;

public class PageInfo {
	private Integer curPage;
	private Integer pageSize;
	private Integer allPage;
	private Integer startPage;
	private Integer endPage;
	
	public PageInfo() {}
	public PageInfo(Integer curPage) {
		this.curPage=curPage;
	}
	
    public PageInfo(int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (curPage - 1) * pageSize;
    }	
	
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getEndPage() {
		return endPage;
	}
	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
	
	

}
