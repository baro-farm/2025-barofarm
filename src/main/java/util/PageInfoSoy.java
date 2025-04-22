package util;

public class PageInfoSoy {
	private int currentPage; //현재 페이지
	private int listCount; //전체 데이터 개수
	private int pageLimit; //하단 페이지네비게이션 수 (5)
	private int boardLimit; //한 페이지당 게시물 수 (10)
	
	private int maxPage; //총 페이지 수
	private int startPage; //하단 시작 페이지 번호
	private int endPage;//하단 끝 페이지 번호
	
	public PageInfoSoy(int currentPage, int listCount, int pageLimit, int boardLimit) {
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount/boardLimit); //45개명 5페이지
		this.startPage = ((currentPage-1)/pageLimit)*pageLimit+1; //6페이지면 6,7,8,9
		this.endPage = startPage+pageLimit-1; 
		if (endPage > maxPage) endPage = maxPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
