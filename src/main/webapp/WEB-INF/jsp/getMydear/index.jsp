<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="common/head.jsp"%>
<section class="route-wrap" id="publish-wrap">
    <header>路线选择</header>

    <!-- 出发地 目的地-->
    <form action="/info/infoList">
        <div class="route-select-wrap">
            <div class="route-start-wrap">
                <p class="route-title">出发地haha：</p>
                <div class="route-list-wrap">
                    省：
                    <select id="from_province" name="from_province" class="js-city-info" data-child="from_city">
                        <option value="">请选择</option>
                        <c:forEach items="${fromProvince}" var="item">
                            <option value="${item.cityCode}">${item.cityName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="route-list-wrap">
                    市：
                    <select id="from_city" name="from_city" class="js-city-info" data-child="from_county">
                        <option value="">请选择</option>
                    </select>
                </div>


                <div class="route-list-wrap">
                    县：
                    <select id="from_county" name="from_county">
                        <option value="">请选择</option>
                    </select>
                </div>

            </div>


            <div class="route-start-wrap">
                <p class="route-title">目的地：</p>
                <div class="route-list-wrap">
                    省：
                    <select id="to_province" name="to_province" class="js-city-info" data-child="to_city">
                        <option value="">请选择</option>
                        <c:forEach items="${fromProvince}" var="item">
                            <option value="${item.cityCode}">${item.cityName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="route-list-wrap">
                    市：
                    <select id="to_city" name="to_city" class="js-city-info" data-child="to_county">
                        <option value="">请选择</option>
                    </select>
                </div>


                <div class="route-list-wrap">
                    县：
                    <select id="to_county" name="to_county">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

        </div>

        <footer class="footer-wrap text-center">
            <a class="js-to-infoList">确定</a>
        </footer>
    </form>

</section>

<jsp:include page="common/footer.jsp"/>

</body>
</html>