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
		      <label>�̸�</label>
		      <div class="readonly-text">${seller.userName }</div>
		    </div>
		    <div class="form-group">
		      <label>��й�ȣ</label>
		      <input type="password" value="${seller.pwd }" />
		    </div>
		    <div class="form-group">
			  <label>��ȭ��ȣ</label>
			  <div class="phone-group">
			    <input type="text" id="phone1" maxlength="3" value="${seller.phone.substring(0,3)}" />
			    -
			    <input type="text" id="phone2" maxlength="4" value="${seller.phone.substring(3,7)}" />
			    -
			    <input type="text" id="phone3" maxlength="4" value="${seller.phone.substring(7)}" />
			  </div>
			</div>
		    
		    <div class="form-group">
		      <label>�̸���</label>
		      <input type="email" value="${seller.email }" />
		    </div>
		    <div class="form-group">
				<c:set var="address" value="${user.addresses[0]}"/>
		      <label>�����ȣ</label>
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
			  <div class="biz-group">
			    <input type="text" id="biz1" maxlength="3" value="${seller.businessNum.substring(0,3)}" readonly />
			    -
			    <input type="text" id="biz2" maxlength="2" value="${seller.businessNum.substring(3,5)}" readonly />
			    -
			    <input type="text" id="biz3" maxlength="5" value="${seller.businessNum.substring(5)}" readonly />
			  </div>
			</div>
		    <div class="form-group bttn">
		    	<button class="submit-btn" type="button" id="updateBtn">�����ϱ�</button>
		    	<button class="del-btn" type="button" id="deleteBtn">Ż���ϱ�</button>
		    </div>
		    
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
<!-- Ż�� ��� -->
	<div id="talModal" class="modal-wrapper" style="display:none;">
		<div class="modal">
			<p>���� Ż���Ͻðڽ��ϱ�?<br>Ż�� �� ��� ������ ������ϴ�!</p>
		    <div class="buttons">
		        <button class="btn btn-subscribe">ȸ�� ����</button>
		        <button class="btn btn-cancel">Ż���ϱ�</button>
		    </div>
        </div>
	</div>
<script>
document.getElementById('deleteBtn').addEventListener('click', function () {
    document.getElementById('talModal').style.display = 'flex';
});

document.querySelector('#talModal .btn-subscribe').addEventListener('click', function () {
    document.getElementById('talModal').style.display = 'none';
});

document.querySelector('#talModal .btn-cancel').addEventListener('click', function () {
    fetch('${contextPath}/deleteUserBySeller', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userId: '${seller.userId}' })
    })
    .then(res => res.json())
    .then(result => {
        alert(result.msg);
        if (result.redirect) {
            location.href = result.redirect;
        }
    })
    .catch(err => {
        alert('���� ��� �� ������ �߻��߽��ϴ�.');
        console.error(err);
    });
});


/*  ���� */
 
document.getElementById('updateBtn').addEventListener('click', function () {
    const data = {
        userId: '${seller.userId}',
        pwd: document.querySelector('input[type="password"]').value,
        phone: document.getElementById('phone1').value +
               document.getElementById('phone2').value +
               document.getElementById('phone3').value,
        email: document.querySelector('input[type="email"]').value,
        postCode: document.getElementById('postCode').value,
        addr1: document.getElementById('addr1').value,
        addr2: document.getElementById('addr2').value,
        storeName: document.querySelector('input[value="${seller.storeName }"]').value,
        businessNum: document.getElementById('biz1').value +
                     document.getElementById('biz2').value +
                     document.getElementById('biz3').value
    };

    fetch('${contextPath}/detailStoreInfo', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        location.reload();
    })
    .catch(err => {
        alert('���� �� ������ �߻��߽��ϴ�.');
        console.error(err);
    });
});



</script>

</body>
</html>