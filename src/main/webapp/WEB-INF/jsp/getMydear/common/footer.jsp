<script>
    var w = document.documentElement.clientWidth;
    if(w > 640){
        w = 640;
    }
    document.documentElement.style.fontSize=20/375*w+'px';
    window.onresize=function(){
        document.documentElement.style.fontSize=20/375*w+'px';
    }
</script>
<script type="text/javascript" src="/public/javascripts/mydear/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/public/javascripts/mydear/fastclick.js"></script>
<script type="text/javascript" src="/public/javascripts/mydear/datetimepicker.js"></script>
<script type="text/javascript" src="/public/javascripts/mydear/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/public/javascripts/weiui/weui.js"></script>
<script type="text/javascript" src="/public/javascripts/mydear/basic.js"></script>

</body>
</html>
