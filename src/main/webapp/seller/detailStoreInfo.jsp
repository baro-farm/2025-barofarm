<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>���� ��û�ϱ�</title>
	<link rel="stylesheet" href="${contextPath }/seller/detailStoreInfo.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
        <h2 class="title">����� ���� ��ȸ</h2>
		  <form>
		    <div class="form-group">
		      <label>���̵�</label>
		      <input type="text" value="���̵�� ���� �Ұ�" readonly/>
		    </div>
		    <div class="form-group">
		      <label>��й�ȣ</label>
		      <input type="password" value="1234" />
		    </div>
		    <div class="form-group">
		      <label>�̸�</label>
		      <div class="readonly-text">����</div>
		    </div>
		    <div class="form-group">
		      <label>��ȭ��ȣ</label>
		      <input type="text" value="010-1234-1234" />
		    </div>
		    <div class="form-group">
		      <label>�̸���</label>
		      <input type="email" value="abc@email.com" />
		    </div>
		    <div class="form-group">
		      <label>�ּ�</label>
		      <div class="address-group">
		        <input type="text" placeholder="�����ȣ" style="width: 80px;" />
		        <button type="button">�ּҰ˻�</button>
		      </div>
		    </div>
		    <div class="form-group" style="margin-left: 100px;">
		      <input type="text" placeholder="�ּ�" style="width: 180px;" />
		      <input type="text" placeholder="���ּ�" style="width: 180px;" />
		    </div>
		    <div class="form-group">
		      <label>������</label>
		      <input type="text" value="��ϳ� �Թ�" />
		    </div>
		    <div class="form-group">
		      <label>����� ��ȣ</label>
		      <input type="text" placeholder="����� ��ȣ" />
		    </div>
		    <button class="submit-btn" type="submit">����</button>
		  </form>
    </div>
</body>
</html>