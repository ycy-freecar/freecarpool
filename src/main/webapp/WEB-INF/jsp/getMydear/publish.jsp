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
    <section class="publish-wrap" id="publish-wrap">
        <link href="/public/stylesheets/mydear/datetimepicker.css" rel="stylesheet" media="screen">

        <form action="/info/saveInfo">
            <input type="hidden" name="fromName" value="${fromName}"/>
            <input type="hidden" name="toName" value="${toName}"/>
            <input type="hidden" name="infoType" value="${infoType}"/>

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
                <input type="hidden" value="${infoType}" id="infoType"/>
                <!-- 选中的加类名 active -->
                <div class="active js-to-choice" data-type="1">
                    <span>人找车</span>
                    <i class="carpool-icon"></i>
                </div>

                <div class="js-to-choice" data-type="2">
                    <span>车找人</span>
                    <i class="carpool-icon"></i>
                </div>
            </div>


            <div class="placeholder-line"></div>
            <!-- 填写信息 -->
            <ul class="publish-user-info-wrap">
                <li>
                    <span>姓名：</span>
                    <input type="text" value="" name="userName"
                           data-require="true" data-msg="姓名不能为空"/>
                </li>
                <li>
                    <span>手机：</span>
                    <input type="number" value="" name="mobile" data-type="mobile"
                           data-require="true" data-msg="请输入正确的手机号"/>
                </li>
                <li>
                    <span>人数：</span>
                    <input type="number" value="" name="userCount"
                           data-require="true" data-msg="人数不能为空"/>
                </li>
                <li>
                    <span>出行日期：</span>
                    <div class="admin-vote-time iDate" id="publish-date-wrap">
                        <input type="text" id="date" name="goTime" data-require="true" data-msg="请选择出行日期" />
                        <button type="button" class="addOn"></button>
                    </div>
                </li>
                <li class="publish-remark-wrap">
                    <textarea placeholder="备注，200字以内，暂不支持表情符..." name="remark" class="js-complete-words" maxlength="200"></textarea>
                    <p><span class="js-count-word">0</span>/200</p>
                </li>
            </ul>

            <footer class="list-footer-wrap">
                <a href="/info/listView">返回列表</a>
                <a class="js-to-publish">确定发布</a>
            </footer>
        </form>
    </section>

<jsp:include page="common/footer.jsp"/>
<script>

    var infoType = $("#infoType").val();
    $(".js-to-choice[data-type='"+infoType+"']").click();

    $(document).on("click",".js-to-publish",function(){
        var form = $(this).closest("form");
        var requireData = form.find("[data-require='true']");
        //校验必填
        if (checkRequire(requireData) && checkReg(requireData)) {
            var fromName = $("#fromName").text();
            var toName = $("#toName").text();
            var infoType = $(".js-to-choice.active").attr("data-type");
            form.find("input[name='fromName']").val(fromName);
            form.find("input[name='toName']").val(toName);
            form.find("input[name='infoType']").val(infoType);
            form[0].submit();
        }
    });
</script>
</body>
</html>