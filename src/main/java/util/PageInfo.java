package util;

public class PageInfo {
	private Integer curPage;
	private Integer pageSize;
	private Integer allPage;
	private Integer startPage;
	private Integer endPage;
	private Integer totalCount;
	private Integer pageBlock = 10;
	
	public PageInfo() {}
	public PageInfo(Integer curPage) {
		this.curPage=curPage;
	}
	
    public PageInfo(Integer curPage, Integer pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (curPage - 1) * pageSize;
    }
    
    public PageInfo(Integer curPage, Integer pageSize, Integer totalCount) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.allPage = (int)Math.ceil((double)totalCount / pageSize);
        this.startPage = ((curPage - 1) / pageBlock) * pageBlock + 1;
        this.endPage = Math.min(startPage + pageBlock - 1, allPage);
        System.out.println("allPage=" + allPage + ", startPage=" + startPage + ", endPage=" + endPage);
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	
	
	

}
