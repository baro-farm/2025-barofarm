<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="reset.css" />
    <style>
      footer {
        width: 1920px;
        height: 170px;
        background-color: #fbf6ee;
        display: flex;
        align-items: center;
        color: #8a8a8a;
      }
      #inner {
        width: 1280px;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      #contents {
        display: flex;
        flex-direction: column;
        gap: 8px;
      }
    </style>
  </head>
  <body>
    <footer>
      <div id="inner">
        <img src="img/logo.png" alt="" />
        <div id="contents">
          <div>
            <span>공지사항</span>
            <span>고객센터</span>
          </div>
          <span
            >주소 : 05699 서울특별시 송파구 양재대로 932 (가락동) 가락몰 지하
            1층 청과 3번 69호</span
          >
        </div>
      </div>
    </footer>
  </body>
</html>