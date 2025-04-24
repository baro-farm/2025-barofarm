<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> <html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>���� ��û�ϱ�</title>
	<link rel="stylesheet" href="${contextPath }/seller/detailStoreInfo.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/header/sellerHeader.jsp" />
    <header id="header">
		<jsp:include page="/header/adminSellerTop.jsp" />
    </header>
    <div id="content">
        <h2 class="title">����� ���� ��ȸ</h2>
		  <form method="post" action="${contextPath }/detailStoreInfo">
		    <div class="form-group">
		      <label>���̵�</label>
		      <div class="readonly-text">${seller.userId }</div>
		    </div>
		    <div class="form-group">
		      <label>��й�ȣ</label>
		      <input type="password" value="${seller.pwd }" />
		    </div>
		    <div class="form-group">
		      <label>�̸�</label>
		      <div class="readonly-text">����</div>
		    </div>
		    <div class="form-group">
		      <label>��ȭ��ȣ</label>
		      <input type="text" value="${seller.phone }" />
		    </div>
		    <div class="form-group">
		      <label>�̸���</label>
		      <input type="email" value="${seller.email }" />
		    </div>
		    <div class="form-group">
				<c:set var="address" value="${user.addresses[0]}"/>
		      <label>����ȣ</label>
		      <div class="address-group">
		        <input type="text" placeholder="�����ȣ" style="width: 80px;" id="postCode"  value="${address.postCode }"/>
		        <button type="button" onclick="sample6_execDaumPostcode()">�ּҰ˻�</button>
		      </div>
		    </div>
		    <div class="form-group" >
   		      <label>�ּ�</label>
   		      <div class="address-group2">
   		      	<input type="text" placeholder="�ּ�" value="${address.addr1 }" id="addr1" />
		      	<input type="text" placeholder="���ּ�" value="${address.addr2 }"  id="addr2" />
   		      </div>
	      </div>
		    <div class="form-group">
		      <label>������</label>
		      <input type="text" value="${seller.storeName }" />
		    </div>
		    <div class="form-group">
		      <label>����� ��ȣ</label>
		      <input type="text" placeholder="����� ��ȣ" value="${seller.businessNum }" />
		    </div>
		    <button class="submit-btn" type="submit">����</button>
		  </form>
    </div>
    
    <script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

                // �� �ּ��� ���� ��Ģ�� ���� �ּҸ� �����Ѵ�.
                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
                var addr = ''; // �ּ� ����
                var extraAddr = ''; // �����׸� ����

                //����ڰ� ������ �ּ� Ÿ�Կ� ���� �ش� �ּ� ���� �����´�.
                if (data.userSelectedType === 'R') { // ����ڰ� ���θ� �ּҸ� �������� ���
                    addr = data.roadAddress;
                } else { // ����ڰ� ���� �ּҸ� �������� ���(J)
                    addr = data.jibunAddress;
                }

                // ����ڰ� ������ �ּҰ� ���θ� Ÿ���϶� �����׸��� �����Ѵ�.
                if(data.userSelectedType === 'R'){
                    // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
                    // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
                    if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // ���յ� �����׸��� �ش� �ʵ忡 �ִ´�.
                
                } 

                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // Ŀ���� ���ּ� �ʵ�� �̵��Ѵ�.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
</script>
</body>
</html>