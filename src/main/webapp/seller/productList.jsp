<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>판매자|상품관리</title>
    <link rel="stylesheet" href="${contextPath }/seller/productList.css" />

    <link href="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.css" rel="stylesheet"
        integrity="sha384-jFvlDSY24z+oXMByOoX2Z1gM+M5NMd0uG7sDa4skv2mHXPuS0/RYXwPGLK0+Mgdc" crossorigin="anonymous" />

    <script src="https://cdn.datatables.net/v/ju/jq-3.7.0/dt-2.2.2/datatables.min.js"
        integrity="sha384-FcKnveOKVsyQDhaxWTmHPNxY0wtv3QwEmOUwRZ5g+QqTQvSKKmnkT0NiFcDCCIvg"
        crossorigin="anonymous"></script>
    
</head>

<body>
    <div class="inner_body">
        <div class="sidebar">
			<jsp:include page="/header/sellerHeader.jsp" />
        </div>
    </div>
    <header id="header">
        <div id="info">
            <span id="email">kosta@kosta.com</span>
            <span>내 정보</span>
            <span>로그아웃</span>
        </div>
    </header>
    <div id="content">
        <div class="notice-header">
            <span id="title">상품 관리</span>
        </div>
		
		
		<table id="notice_table" class="table">
		        <thead>
		          <tr>
		            <th><input type="checkbox"></th>
		          
		            <th style="font-weight: bold;">상품번호</th>
		            <th style="font-weight: bold;">상품명</th>
		            <th style="font-weight: bold;">카테고리</th>
		            <th style="font-weight: bold;">상품상태</th>
		            <th style="font-weight: bold;">가격</th>
		            <th style="font-weight: bold;">썸네일</th>
		            <th style="font-weight: bold;">등록일자</th>
		            <th style="font-weight: bold;">상품재고</th>
		            <th style="font-weight: bold;">판매건수</th>
		            <th style="font-weight: bold;">리뷰건수</th>
		          	<th style="font-weight: bold;">리뷰평균</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach var="product" items="${productList}">
	                <tr>
	                    <td><div class="uiGridCell"><input type="checkbox"></div></td>
	                    <td><div class="uiGridCell productNum"><a href="#">${product.productNum }</a></div></td>
	                    <td><div class="uiGridCell"><a href="#">${product.productName }</a></div></td>
	                    <td><div class="uiGridCell">${product.name }</div></td>
	                    <td>
	                    	<c:choose>
	                    		<c:when test="${product.status == true }">
	                    			<div class="uiGridCell"><span class="status on-sale">판매중</span></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="uiGridCell"><span class="status stopped">판매중단</span></div>
	                    		</c:otherwise>
	                    	</c:choose>
	                    	
	                    </td>
	                    <td><div class="uiGridCell">${product.price }원</div></td>
	                    <td><div class="uiGridCell"><img src="${product.imgUrl}" width="50"></div></td>
	                    <td><div class="uiGridCell">${product.createdAt }</div></td>
	                    <td><div class="uiGridCell"><button>-</button> <span>${product.stock }</span> <button>+</button> <button>저장</button></div></td>
	                    <td><div class="uiGridCell">${product.salesVolume }건</div></td>
	                    <td><div class="uiGridCell">${product.productNum }건</div></td>
	                    <td><div class="uiGridCell">⭐⭐⭐⭐ 4</div></td>
	                </tr>
		         </c:forEach>
		        </tbody>
		      </table>
		

      
        <div class="actions">
            <button class="btn delete">선택 삭제</button>
            <button class="btn edit">수정</button>
            <button class="btn add">새 상품등록</button>
        </div>
    </div>
</body>
</html>