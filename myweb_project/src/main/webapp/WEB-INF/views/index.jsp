<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <!-- 경로 중요 !!!jsp파일위치아님!!! - 이 jsp파일을 dispatcher한 서블릿url이 기준 -->
    <!-- <link rel="stylesheet" href="./resources/css/reset.css"> -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/reset.css">
    <style>
        .wrap {
            width: 100%;
        }
        .wrap.header {
            background: bisque;
            border: 1px solid black;
            box-sizing: border-box;
        }
        .wrap.content {
            background: beige;
            border: 1px solid black;
            box-sizing: border-box;
        }
        .wrap.footer {
            background: bisque;
            border: 1px solid black;
            box-sizing: border-box;
        }
        /* header */
        header{ 
            /* 1. 중앙정렬 */
            width: 80%; margin: 0 auto;
        }
        /* #content */
        #content{
            /* 1. 중앙정렬 */
            width: 80%; margin: 0 auto;
            position: relative;
            /* 2. float 배치- 한것으로 height 계산 못하므로 overflow hidden 설정 */
            overflow: hidden;
        }
        /* section */
        section{
            /* 2. float 배치 */
            width: 80%; 
            float: left;
        }
        article {
            /* 5. 테두리 !! 중요 부모요소의 크기를 최대로 가지면서 나의 content 크기가 줄어듬 */
            border: 1px solid black;
            padding: 30px 20px;
            
            margin-bottom: 10px;
        }
        /* aside */
        aside{
            /* 2. float 배치 */
            width: 18%; float: right;
            border: 1px solid black;
        }
        /* footer */
        footer{
            /* 1. 중앙정렬 */
            width: 80%; margin: 0 auto;
            /* 2. float 배치-해제하고 정확한 위치잡기*/
            clear: both;

            margin-bottom: 10px;
        }


        /* 1500 보다 클때 */
        @media (min-width:1500px) {
            body{
                font-size: 1.2rem;
            }
        }

        /* 800 보다 작을 때 */
        @media (max-width:799px) {
            body{
                font-size: 0.8rem;
            }
            /* header */
            header{ 
                /* 1. 중앙정렬 */
                width: 90%; 
            }
            /* #content */
            #content{
                /* 1. 중앙정렬 */
                width: 90%;
                position: relative;
                /* 2. float 배치- 한것으로 height 계산 못하므로 overflow hidden 설정 */
                overflow: hidden;
            }
            /* section */
            section{
                /* 2. float 배치 */
                width: 100%; 
                float: none;
            }
            article {
                /* 5. 테두리 !! 중요 부모요소의 크기를 최대로 가지면서 나의 content 크기가 줄어듬 */
                padding: 30px 20px;
            }
            /* aside */
            aside{
                /* 2. float 배치 */
                width: 80%; margin: 0 auto; 
                float: none;
            }
            /* footer */
            footer{
                /* 1. 중앙정렬 */
                width: 90%; margin: 0 auto;
                /* 2. float 배치-해제하고 정확한 위치잡기*/
                /* clear: both; */

                margin-bottom: 10px;
            }
        }
    </style>
    <!-- flex box -->
    <style>
        /* flex */
        /* block size 수업용 */
        .content .flex4content{
            display: flex;
            /* overflow: hidden; */
        }
        .content .flex4content > div{
            border: 1px solid black;
            margin: 10px 5px;
            /* margin-right: 10px; */
            /* padding: 10px; */
            /* float:left; width: 25%; */
            /* width: 23%;
            display: inline-flex; */
        }
    </style>
    <!-- modal box-->
    <style>
        .modal {
            display: none;
            width: 100%;
            height: 100%;
            z-index: 1;
            position: fixed;
            top:0;
            left: 0;
            background-color: rgba(0, 0, 128, 0.3);
        }
        .modal>.modal_content{
            position: relative;
            top: 50px;
            margin: auto;
            width: 500px;
            background-color: white;
        }
    </style>
    <!-- accordion -->
    <style>
        /* .accordion{
            width: 90%; margin: 10px auto;
        } */
        .btn_accordion{
            width: 100%;
            padding: 20px;
            transition: .7s;
        }
        .btn_accordion:hover{
            background-color: greenyellow;
        }
        .panel{
            display: none;
        }
    </style>
    <script>
        window.onload = pageLoadedHandler;
        // $(pageLoadedHandler);
        function pageLoadedHandler(){
            { // accordion
                { //js DOM
                // let eleBtns = document.getElementsByClassName("btn_accordion");
                // for(var i=0; i<eleBtns.length; i++){
                //     eleBtns[i].onclick = function(){
                //         console.log(this);
                //         console.log(this.nextElementSibling);
                //         var eleNext = this.nextElementSibling;
                //         var isDisplay = eleNext.style.display;
                //         console.log(isDisplay);
                //         if(isDisplay == "" || isDisplay == "none"){
                //             eleNext.style.display = "block";   // 1
                //             // 절대 안됨. 자료형이 중요함. isDisplay = "block";   //2
                //         } else {
                //             eleNext.style.display = "none";
                //         }
                //     }
                // }
                }
                { //jquery
                    // $(".btn_accordion").click(function(e){
                    //     $(this).next().toggle();
                    // });
                }
                { //jquery index() 사용 - 복잡한거.. this / 함수 / 변수 위치 학습용~
                    // $(".btn_accordion").click(function(e){
                    //     var idx = $(this).index();  // index() 메소드는 nth-child
                    //     // 변수에 꼭 담아서 사용
                    //     // console.log(idx);
                    //     // console.log(this);
                    //     $(".panel").each( f1 );
                    //     function f1(index, elem){
                    //         // console.log(index);
                    //         // console.log(this);
                    //         if(index == idx/2){
                    //             $(this).toggle();
                    //         }
                    //     }
                    // });
                }
                { //js DOM - 클릭한 것이 열려있다면 닫고 닫혀있다면 열고, 클릭한것 이외에서 열린것 있다면 닫기
                    // let eleBtns = document.getElementsByClassName("btn_accordion");
                    let eleBtns = document.querySelectorAll(".btn_accordion");
                    // console.log(document.querySelector(".btn_accordion"));
                    console.log(document.querySelectorAll(".btn_accordion"));
                    // eleBtns.onclick = accordionBtnClickHandler;
                    // eleBtns.addEventListener("click",accordionBtnClickHandler);
                    for(var i=0; i<eleBtns.length; i++){
                        console.log("for아래 i는 "+i);
                        eleBtns[i].onclick = accordionBtnClickHandler;
                    }

                    function accordionBtnClickHandler(){
                        // console.log("onclick아래 i는 "+i);
                    
                        console.log(this);
                        //read only //this.nextElementSibling = 35;
                        console.log(this.nextElementSibling);
                        
                        var eleNext = this.nextElementSibling;
                        // show 된 것을 닫아줘야함.
                        let elePanels = document.querySelectorAll(".panel");
                        for(var i=0;i<elePanels.length;i++){
                            var isDisplay = elePanels[i].style.display;
                            if(isDisplay != "" && isDisplay != "none"){  //show 상태
                                if(eleNext != elePanels[i]) {    // 방법 1 . 클릭된 엘리먼트의 panel(eleNext)과 현재 show되고 있는 element 가 같다면 닫지 않고 아래 코드를 이용해 닫도록 함.
                                    elePanels[i].style.display = "none";
                                    break;
                                }
                            }
                        }

                        // this를 show->hide hide->show
                        var isDisplay = eleNext.style.display;
                        console.log(isDisplay);
                        if(isDisplay == "" || isDisplay == "none"){
                            eleNext.style.display = "block";   // 1
                            // 절대 안됨. 자료형이 중요함. isDisplay = "block";   //2
                        } 
                        else {
                            eleNext.style.display = "none";
                        }
                    }
                }
            }
            {  // modal box - jQuery
            // function pageLoadedHandler(){
            //     $(".btn_modal").on("click", openModalHandler);
            //     $(".close").on("click", closeModalHandler);
            //     $(".modal").on("click", closeModalWindowHandler);
            // }
            // function openModalHandler(){
            //     $(".modal").slideDown(1000);
            // }
            // function closeModalHandler(){
            //     $(".modal").slideUp();
            // }
            // function closeModalWindowHandler(){
            //     if(event.target == $(".modal").get(0) 
            //         || event.target == $(".close").get(0)){
            //         $(".modal").slideUp(300);
            //     }
            // }
            }
            { // modal box - DOM
                // modal box event 등록
                // document.getElementsByClassName("btn_modal")[0].addEventListener("click", openModalHandler);
                document.querySelector(".btn_modal").addEventListener("click", openModalHandler);
                document.querySelector(".close").onclick = closeModalHandler;
                const eleModal = document.querySelector(".modal");   // const 재선언안되고 재할당 안됨. immutable 안됨
                eleModal.onclick = closeModalWindowHandler;
                // modal box event handler
                function openModalHandler(){
                    eleModal.style.display = "block";
                }
                function closeModalHandler(){
                    eleModal.style.display = "none";
                }
                function closeModalWindowHandler(){
                    console.log("closeModalWindowHandler~~~");
                    if(event.target == eleModal) {
                        eleModal.style.display = "none";
                    }
                }
            }
        }
    </script>
<script src="https://kit.fontawesome.com/ef09f998fc.js" crossorigin="anonymous"></script> 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
    integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>

<body>

    <div id="main_wrap">
<%@ include file="template_header.jsp" %>
        <div class="wrap content">
            <div id="content">
                <section>
                    <article id="art_1">
                        <h2>Main Article</h2>
                        <div class="flex4content">
                            <div >
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            </div>
                            <div>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            </div>
                            <div>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            </div>
                            <div>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            </div>
                        </div>
                    </article>
                    <article id="art_2">
                        <h2>Main Article</h2>
                        <button class="btn_modal">동의하기ModalBox열기</button>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
                            ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                        </p>
                    </article>
                    <article id="art-3">
                        <h2>Main Article</h2>
                        <div class="accordion">
                            <button class="btn_accordion">Section 1</button>
                            <div class="panel">
                                <div class="flex4content">
                                    <div >
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    </div>
                                    <div>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    </div>
                                    <div>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    </div>
                                    <div>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    </div>
                                </div>
                            </div>
                            <button class="btn_accordion">Section 2</button>
                            <div class="panel">
                                <p>2. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                    labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                                    voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                                    cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                            </div>
                            <button class="btn_accordion">Section 3</button>
                            <div class="panel">
                                <p>3. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                    labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                    laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                                    voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                                    cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                            </div>
                        </div>
                    </article>
                    <article id="art-4">
                        <h2>Main Article</h2>
                        <button id="btn_boardlist">게시판목록</button>
                        <button onclick="location.href='boardwriteWithFile';">게시판글쓰기+파일추가</button>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
                            ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    	<script>
                    		$("#btn_boardlist").click(function(){
                    			location.href="boardlist";
                    		});
                    	</script>
                    </article>
                    <article id="art-5">
                        <h2>Main Article</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
                            ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </article>
                    <article id="art-6">
                        <h2>Main Article</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
                            ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                            cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    </article>
                </section>
                <aside>
                    <input type="radio" name="tab" id="first" checked>
                    <input type="radio" name="tab" id="second">
                    <div class="buttons">
                        <label for="first">First</label>
                        <label for="second">Second</label>
                    </div>
                    <div class="items">
                        <div class="tab_item">
                            <ul>
                                <li class="item">
                                    <a href="#art-4">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description">
                                            <strong>HTML5 Canvas</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="#art-4">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>HTML5 Semantics Web</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="#art-4">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>HTML5 Video</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a>
                                </li>
                                <li class="item">
                                    <a href="#art-4">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>HTML5 Semantics Web</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="tab_item">
                            <ul>
                                <li class="item"><a href="#">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>CSS3 Transition</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a></li>
                                <li class="item"><a href="#">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>CSS3 Animation</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a></li>
                                <li class="item"><a href="#">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>CSS3 Border</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a></li>
                                <li class="item"><a href="#">
                                        <div class="thumbnail"><img src="https://dummyimage.com/45/ff0"></div>
                                        <div class="description"><strong>CSS3 Box</strong>
                                            <p>2020-03-08</p>
                                        </div>
                                    </a></li>
                            </ul>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
        <div class="wrap footer">
            <footer>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                    labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                    voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                    cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
                    ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                    labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                    voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                    cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            </footer>
        </div>
    </div>
    <div id="modal_wrap">
        <div class="modal">
            <!-- 여기에는 아무 내용 적지 않음. - 대체로 -->
            <div class="modal_content">
                <!-- 여기에 내용 작성 -->
                <div class="close">&#9932;</div>
                <div class="flex4content">
                    <div >
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    </div>
                    <div>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    </div>
                    <div>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    </div>
                    <div>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>