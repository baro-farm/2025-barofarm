<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page import="dto.buyer.Address"%>
<%@page import="java.util.List"%>

<%
	List<Address> addressList = (List<Address>)request.getAttribute("addressList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>배송지 주소 관리</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${contextPath}/buyer/addressList.css">
<script type="text/javascript">
$(function() {
	$(".deleteBtn").click(function(e) {
		e.preventDefault();
		const addrNum = $(this).data("addrnum");
		console.log("삭제할 주소 번호:", addrNum);

		if(confirm("정말 이 주소를 삭제하시겠습니까?")){
			$.ajax({
				url:'deleteAddress',
				type:'post',
				async:true,
				dataType:'text',
				data:{addrNum: addrNum},
				success:function(result) {
					if(result=='true') {
						alert("주소가 삭제 되었습니다");
						$("#row-"+addrNum).remove();
					} else {
						alert("삭제 실패")
					}
				},
				error:function(err) {
					console.log(err)
				}
			})
		}

	})
	
	$(".updateBtn").click(function(){
		const addrNum = $(this).data("addrnum");
		window.location.href = "${contextPath}/updateAddress?addrNum=" + addrNum;
		
	})
	
	
	
})	
</script>

</head>
<body>
	<jsp:include page="/header/mainHeader.jsp"/>
	<div class="container">
		<div class="wrapper">
			<div class="sideMenu">
				<jsp:include page="/header/buyerMenu.jsp" />
			</div>
						
			<div class="content">
				<!-- 헤더 -->
				<div class="header">배송지 주소관리</div>
				
								<!-- 주소 추가 버튼 -->
				<div class="addAddress">
					<a href="${contextPath}/insertAddress">
						<button class="btnGreen">주소지 추가</button>
					</a>
				</div>
				<!-- 주소 목록 테이블 -->
				<table class="addressTable">
					<thead>
						<tr>
							<th>배송지</th>
							<th>수령인</th>
							<th>연락처</th>
							<th>우편번호</th>
							<th>주소</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="address" items="${addressList }">
							<tr id="row-${address.addrNum}">
								<td><c:if test="${address.isDefault}">
										<span class="defaultAddress">(기본)</span>
									</c:if> ${address.nickname }</td>
								<td>${address.name }</td>
								<td>${address.phone }</td>
								<td>${address.postCode }</td>
								<td>${address.addr1 }${address.addr2 }</td>
								
								<td>
									<button class="btnGreen updateBtn" data-addrnum = "${address.addrNum }">수정</button>
									<c:if test="${!address.isDefault }">
										<button class="btnRed deleteBtn" data-addrnum = "${address.addrNum }">삭제</button>
									</c:if></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		

	</div>

</body>
</html>