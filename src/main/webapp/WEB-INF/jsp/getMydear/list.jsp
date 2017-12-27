<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<jsp:include page="common/head.jsp"/>
<script>
    (function(){
        if(window.navigator.userAgent.toLowerCase().indexOf("micromessenger")<0){
            //非微信客户端打开
            window.location.href="/info/error";
        }
    })();
</script>
<section class="info-list-wrap">
    <link href="/public/stylesheets/mydear/datetimepicker.css" rel="stylesheet" media="screen">
    <!--头部banner-->
    <header class="header-address-wrap">
        <div >
            <span name="fromName" id="fromName" class="js-city-selection">${fromName}</span>
            <input name="fromCode" id="fromCode" value="${fromCode}" type="hidden"/>
        </div>
        <i class="carpool-icon" id="changeAddressIcon"></i>
        <div >
            <span name="toName" id="toName" class="js-city-selection">${toName}</span>
            <input name="toCode" id="toCode" value="${toCode}" type="hidden"/>
        </div>
    </header>

    <!--人找车 车找人-->
    <div class="choice-type-wrap">
        <input type="hidden" value="infoList" id="titleFlag"/>
        <input type="hidden" value="${infoType}" id="infoType"/>
        <!-- 选中的加类名 active -->
            <div class="active js-to-choice" data-type="1">
                <span>车主 - 找人</span>
                <i class="carpool-icon"></i>
            </div>

            <div class="js-to-choice" data-type="2">
                <span>乘客 - 找车</span>
                <i class="carpool-icon"></i>
            </div>
    </div>


    <div class="placeholder-line"></div>

    <!-- 列表信息 -->
    <ul class="info-ul-wrap">
        <c:forEach items="${carpoolInfos}" var="item">
            <li>
                <div class="info-phone-wrap carpool-icon js-to-phone"></div>
                <p>
                    <i>姓名：</i>
                    <span>${item.userName}</span>
                </p>
                <p>
                    <i>日期：</i>
                    <span>${item.goTime}</span>
                </p>
                <p>
                    <i>人数：</i>
                    <span>${item.userCount}</span>
                </p>
                <p>
                    <i>备注：</i>
                    <span>${item.remark}</span>
                </p>
            </li>
        </c:forEach>
    </ul>
    <div class="blank-wrap hide">
        <img src="/public/images/blank-bg.png" />
        <p class="text-center">暂无相关信息~</p>
    </div>

    <form id="postForm" action="/info/publish">
        <input type="hidden" name="fromCode">
        <input type="hidden" name="fromName">
        <input type="hidden" name="toName">
        <input type="hidden" name="toCode">
        <input type="hidden" name="infoType">
    </form>

    <%--<form id="freshInfoList" action="/info/infoList">
        <input type="hidden" name="from_city">
        <input type="hidden" name="to_city">
        <input type="hidden" name="infoType">
    </form>--%>


    <footer class="footer-wrap text-center">
        <a href="javascript:;" class="js-go-publish">我要发布</a>
    </footer>



</section>

<jsp:include page="common/footer.jsp"/>

<script>
    var infoType = $("#infoType").val();
    if (infoType) {
        $(".js-to-choice").removeClass("active");
        $(".js-to-choice[data-type='"+infoType+"']").addClass("active");
    }
    freshList();
</script>

</body>
</html>