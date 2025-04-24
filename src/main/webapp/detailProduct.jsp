<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
	<link rel="stylesheet" href="${contextPath}/reset.css" />
    <link rel="stylesheet" href="${contextPath}/detailProduct.css" /> 
</head>
<body>
	<div class="container">
		<jsp:include page="/header/mainHeader.jsp" />
		<div class="wrapper">
			<jsp:include page="/header/sideMenu.jsp" />
			<div class="content">
				<!-- 상품정보 -->
			    <div class="prodInfoBox">
			            <!-- 이미지 -->
			            <div class="prodImg">
			                <img src="${contextPath}/img/fruits1.jpg" alt="상추이미지">
			            </div>
			            <div class="prodInfo">
			                <!-- 스토어상품정보 -->
			                <div class="storeName"><a href="">충남부여 강도석 &gt;</a></div>
			                <div class="prodTitle">[친환경 수정재배] 유럽 샐러드 채소 야채 1kg 유러피안 버터헤드 로메인 상추 쌈채소</div>
			                <div class="additional">
			                    <div class="reviewScore">⭐ 4.5 (212)</div>
			                    <div class="price">7,400원</div>
			                </div>
			          
			                <!-- 주문옵션전체영역 -->
			                <div class="orderOption">
			                    <select id="optionSelect" class="selectBox">
			                        <option value="">옵션 선택</option>
			                        <option value="바타비아|7400">바타비아 - 7,400원</option>
			                        <option value="로메인|7800">로메인 - 7,800원</option>
			                        <option value="버터헤드|8000">버터헤드 - 8,000원</option>
			                    </select>
			                    <div id="selectedOptions"></div>
			                </div>
			
			                 <!-- 총 가격 -->
			                <div class="totalPrice"><span id="totalPrice">0</span>원</div>
			                
			                <!-- 버튼 영역 -->
			                <div class="actionButtons">
			                    <a href="#" id="basket">장바구니</a>
			                    <a href="#" id="purchase">구매하기</a>
			                </div>
			            </div>
			        </div>

			        <!-- 상세정보 -->
			        <div class="tabs" id="details">
			            <ul class="tabList">
			                <li class="tab focus"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(21)</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        <div class="contentDetail">
			            <div>
			                <img src="../img/image 4.png" alt="">
			            </div>
			        </div>
			        <!-- 리뷰 -->
			        <div class="tabs" id="reviews">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab focus"><a href="#reviews">리뷰 보기(21)</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        <!-- 리뷰 이미지 슬라이더 -->
			        <div class="imgSlider">
			            <button class="nav-button prev" onclick="slide(-1)">←</button>
			            <div class="slider" id="slider">
			              <div class="slide"><img src="../img/fruits1.jpg" alt="1"></div>
			              <div class="slide"><img src="../img/fruits2.jpg" alt="2"></div>
			              <div class="slide"><img src="../img/fruits3.jpg" alt="3"></div>
			              <div class="slide"><img src="../img/bener1.jpg" alt="4"></div>
			              <div class="slide"><img src="../img/bener2.jpg" alt="5"></div>
			              <div class="slide"><img src="../img/bener3.jpg" alt="6"></div>
			              <div class="slide"><img src="../img/fruits1.jpg" alt="7"></div>
			              <div class="slide"><img src="../img/fruits2.jpg" alt="8"></div>
			              <div class="slide"><img src="../img/fruits3.jpg" alt="9"></div>
			              <div class="slide"><img src="../img/fruits1.jpg" alt="10"></div>
			            </div>
			            <button class="nav-button next" onclick="slide(1)">→</button>
			        </div>
			        <!-- 리뷰내용  -->
			            <table class="reviewTable">
			            </table>     
			            <div class="paging">
			                <a href="">&lt;</a>
			                <a href="" class="pagingBtn">1</a>
			                <a href="" class="pagingBtn">2</a>
			                <a href="" class="pagingBtn">3</a>
			                <a href="">&gt;</a>
			            </div>
			
			        <!-- 문의 -->
			        <div class="tabs" id="qna">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(21)</a></li>
			                <li class="tab focus"><a href="#qna">문의(11)</a></li>
			                <li class="tab"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        
			        <table id="notice_table" class="table" width="100%">
			            <thead class="">
			                <tr>
			                    <th style="font-weight: bold;">답변상태</th>
			                    <th style="font-weight: bold;">제목</th>
			                    <th style="font-weight: bold;">작성자</th>
			                    <th style="font-weight: bold;">작성일</th>
			                </tr>
			            </thead>
			            <tbody>
			                <tr>
			                    <td>답변대기</td>
			                    <td><a href="">고객센터 전화번호 변경안내</a></td>
			                    <td>id18****</td>
			                    <td>2025-03-29</td>
			                </tr>
			                <tr>
			                    <td>답변대기</td>
			                    <td><a href="">발송마감시간 변경안내</a></td>
			                    <td>id335****</td>
			                    <td>2025-03-29</td>
			                </tr>
			                <tr>
			                    <td>답변완료</td>
			                    <td><a href="" class="question">연말연시 배송안내</a></td>
			                    <td>id877****</td>
			                    <td>2025-02-21</td>
			                </tr>
			                <tr class="answerRow">
			                    <td colspan="4" class="answer">
			                      <span class="tag">답변</span>
			                      안녕하세요. 고객님~ 저희 유럽 샐러드 채소를 주문해주시면...
			                      <div class="meta">판매자 | 2025-02-21</div>
			                    </td>
			                  </tr>
			                <tr>
			                    <td>답변완료</td>
			                    <td><a href="">2019년 설 연휴 배송안내</a></td>
			                    <td>id1sssss8****</td>
			                    <td>2025-03-29</td>
			                </tr>
			                <tr>
			                    <td>답변완료</td>
			                    <td><a href="">상품을 교환/반품하고 싶어요.</a></td>
			                    <td>id335****</td>
			                    <td>2025-03-29</td>
			                </tr>
			            </tbody>
			        </table>
			
			        <div class="paging">
			            <a href="">&lt;</a>
			            <a href="" class="pagingBtn">1</a>
			            <a href="" class="pagingBtn">2</a>
			            <a href="" class="pagingBtn">3</a>
			            <a href="">&gt;</a>
			        </div>
			        <!-- 안내사항 -->
			        <div class="tabs" id="info">
			            <ul class="tabList">
			                <li class="tab"><a href="#details">상세정보</a></li>
			                <li class="tab"><a href="#reviews">리뷰 보기(21)</a></li>
			                <li class="tab"><a href="#qna">문의(11)</a></li>
			                <li class="tab focus"><a href="#info">안내사항</a></li>
			              </ul>
			        </div>
			        <div class="infoContent">
			            <div>
			                <p>📌 결제안내</p>
			                <p>전화 주문은 받지 않습니다.</p>
			                <p>온라인 홈페이지를 통해 주문 하시기를 부탁드립니다.</p>
			            </div>
			            <div>
			                <p>📦 배송안내</p>
			                <p>바로팜은은 생산자가 산지에서 직접 포장, 일반 택배사를 이용해 발송합니다.</p>
			                <p>품목마다 택배사, 발송 요일이 다릅니다. 상세페이지 뒷부분 배송 안내를 참고하세요.</p>
			                <p>주문요청 항목은 메모, 배송이 지연될 수 있으니 참고하세요.</p>
			                <p>도서산간 지역은 배송비가 추가됩니다.</p>
			                <p>포장과 상자는 생산자의 상황에 따라 달라질 수 있습니다.</p>
			                <p>물품 사진은 생산자가가 직접 찍은 사진입니다. 투박하지만 정직과 정성을 담았습니다.</p>
			                <p>물품에 대해 궁금하신 점은 중앙사무국(02-582-1416)으로 전화주세요.</p>
			            </div>
			        </div>
			    </div>
    	</div>
    	<jsp:include page="/header/footer.jsp" />
    </div>
</body>
</html>