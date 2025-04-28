package util;

public class SearchDtoSoy {
	private int page = 1; //기본값 처음 접속 시 무조건 1페이지
	private final int recordSize = 10; //한페이지에 몇줄씩 보여줄 것인가
	private final int pageSize = 5; //페이지네비게이션 수
	
	private String keyword; //검색어 값
	private String searchType; //스토어명, 시작일, 어쩌고.....
	private String startDateFrom; //검색 시작일
	private String startDateTo; //검색 종료일
	
    private String status;  // 매칭 상태
	private Long userNum;
	
	public int getOffset() {
		return (page-1) * recordSize; //limit offset에 사용할 것
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
	    this.page = (page < 1) ? 1 : page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getStartDateFrom() {
		return startDateFrom;
	}

	public void setStartDateFrom(String startDateFrom) {
		this.startDateFrom = startDateFrom;
	}

	public String getStartDateTo() {
		return startDateTo;
	}

	public void setStartDateTo(String startDateTo) {
		this.startDateTo = startDateTo;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}