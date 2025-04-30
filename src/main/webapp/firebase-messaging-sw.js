importScripts('https://www.gstatic.com/firebasejs/10.8.1/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/10.8.1/firebase-messaging-compat.js');

firebase.initializeApp({
  apiKey: "AIzaSyAMTyM_wgDLIGSRbXOec448CKNRcnlggRY",
  authDomain: "kosta-1213b.firebaseapp.com",
  projectId: "kosta-1213b",
  messagingSenderId: "798921087749",
  appId: "1:798921087749:web:8e5e43ff0c566e08d1356c"
});
  
  //컨피그를 가지고 fb 메시징을 하나 만든다.

  const messaging = firebase.messaging();


//백그라운드(비활성화일 때 메세지 처리)
self.addEventListener('push', function(event) {
  if (event.data) {
    const payload = event.data.json();
    //console.log('[Service Worker] 푸시 수신:', payload);

    const title = payload.notification?.title || '알림';
    const options = {
      body: payload.notification?.body || '',
      icon: '/barofarm/img/logo.png' // 원하는 아이콘 경로
    };

    event.waitUntil(
      self.registration.showNotification(title, options)
    );
  }
});