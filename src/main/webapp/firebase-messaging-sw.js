importScripts('https://www.gstatic.com/firebasejs/10.8.1/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/10.8.1/firebase-messaging-compat.js');

  const firebaseConfig = {
    apiKey: "AIzaSyAMTyM_wgDLIGSRbXOec448CKNRcnlggRY",
    authDomain: "kosta-1213b.firebaseapp.com",
    projectId: "kosta-1213b",
    messagingSenderId: "798921087749",
    appId: "1:798921087749:web:8e5e43ff0c566e08d1356c"
  };
  firebase.initializeApp(firebaseConfig);
  
  //컨피그를 가지고 fb 메시징을 하나 만든다.

  const messaging = firebase.messaging();

  messaging.getToken({ vapidKey: "BE3AHxHgnALTTVtwcKYkxQOqktkJQ3aDHKlG2x-N85cdNXX_NS6ePIHjZuwqvivLYPjMMYaw4ytzg4hjUeFYZWk" })
    .then((token) => {
      console.log("FCM 토큰:", token);

      fetch(contextPath + '/updateFcmToken', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ fcmToken: token })
      });
    })
    .catch(err => console.error('토큰 가져오기 실패:', err));

//백그라운드(비활성화일 때 메세지 처리)
messaging.onBackgroundMessage(function (payload) {
	//payload == 백엔드 서버에서 보낸 메세지 
	//백그라운드에서 뜨는 애는 notification의 데이터만 뜨는 것으로 약속됨
  	console.log('[firebase-messaging-sw.js] 백그라운드 메시지 수신:', payload);

    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: payload.notification.body,
    };

    // eslint-disable-next-line no-restricted-globals
    self.registration.showNotification(
        notificationTitle,
        notificationOptions
    );
});