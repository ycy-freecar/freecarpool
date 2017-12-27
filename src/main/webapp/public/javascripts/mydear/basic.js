
"use strict";


/**
* 消除移动端300毫秒的延迟点击事件
//  */

FastClick.attach(document.body);
/*
 * 信息弹层
 * type为0是错误，1位正确
 * text为提示信息
 * */
function Messenger(text, type) {
    var t = $('#alert-messenger');
    t.removeClass('error');
    if (type === 0) {
        t.addClass('error');
    }
    t.stop().slideDown(300).html(text);
    setTimeout(function () {
        t.stop().slideUp(300);
    }, 2000);
}
/*
 * 导航悬浮
 * */
document.addEventListener('touchmove',function(event){
    if($(window).scrollTop() > 0){
        $('header').css({'position': 'fixed'});
        $('.choice-type-wrap').css({
            'padding-top': '3.2rem'
        });
    }else{
        $('header').css({'position': 'relative'});
        $('.choice-type-wrap').css({
            'padding-top': '0.8rem'
        })
    }
},false);
/**
 * 日期插件
 * */
function dateTimePicker(obj){
    obj.datetimepicker({
        locale: "zh-cn",
        format: "YYYY-MM-DD HH:mm",
        defaultDate:"",
        minDate:new Date()
    });
}

if($('#publish-wrap').length>0){
    dateTimePicker($('#publish-date-wrap'));
}

/**
 * 刷新列表
 */




/*
* 发布确定
* */
$(document).on('click','.js-go-publish',function(){
    var fromCode = $("#fromCode").val();
    var fromName = $("#fromName").text();
    var toCode = $("#toCode").val();
    var toName = $("#toName").text();
    var infoType = $(".js-to-choice.active").attr("data-type");

    var form = $("#postForm");
    form.find("input[name='fromCode']").val(fromCode);
    form.find("input[name='fromName']").val(fromName);
    form.find("input[name='toName']").val(toName);
    form.find("input[name='toCode']").val(toCode);
    form.find("input[name='infoType']").val(infoType);
    form[0].submit();
});


function checkRequire(arr){
    for (var i=0;i<arr.length;i++) {
        var $t = $(arr[i]);
        if (!$t.val()) {
            Messenger($t.attr("data-msg"),0);
            return false;
        }
    }
    return true;
}

function checkReg(arr){
    var mobileReg = /^1[34578]\d{9}$/;
    for (var i=0;i<arr.length;i++) {
        var $t = $(arr[i]);
        if ($t.val()) {
            var dataType = $t.attr("data-type");
            switch (dataType) {
                case "mobile":
                     if(!mobileReg.test($t.val())){
                         var errorMsg = $t.attr("data-msg");
                         Messenger(errorMsg,0);
                         return false;
                     }
            }

        }
    }
    return true;
}
/**
 * 出发地、目的地切换逻辑
 */
$(document).on("click","#changeAddressIcon",function(){
    var fromName = $("#fromName").text();
    var fromCode = $("#fromCode").val();

    var toName = $("#toName").text();
    var toCode = $("#toCode").val();


    //change
    $("#fromName").text(toName);
    $("#fromCode").val(toCode);

    $("#toName").text(fromName);
    $("#toCode").val(fromCode);
    if ($("#titleFlag").val()=="infoList") {
        freshList();
    }
});

/**
 * 切换人找车逻辑
 */

$(document).on("click",".js-to-choice",function(){
    var $t = $(this);
    if ($t.hasClass("active")) {
        return;
    } else {
        $(".js-to-choice").removeClass("active");
        $t.addClass("active");
        if ($("#titleFlag").val()=="infoList") {
            freshList();
        }
    }
});

/**
 * 刷新列表页
 */
function freshList(){
    var fromCode = $("#fromCode").val();
    var toCode = $("#toCode").val();
    var infoType = $(".active.js-to-choice").attr("data-type");
    var name = infoType=="1"?"乘客":"车主";
    var ulBox = $(".info-ul-wrap");
    var notMsg = $(".blank-wrap");
    ulBox.empty();
    $.ajax({
        url:"/info/infoList",
        data:{fromCode:fromCode,toCode:toCode,infoType:infoType},
        dataType:"json",
        success:function(data){
            if (data.flag=="1") {
                var infoList = data.data.infoList;
                for (var i=0;i<infoList.length;i++) {
                    var info = infoList[i];
                    var html = "<li><a class='info-phone-wrap carpool-icon js-to-phone' href='tel:"+info.mobile+"'></a>"+
                        "<p><i>"+name+"：</i><span>"+info.userName+"</span><span class='create_time'>发布于："+info.createTimeShow+"</span></p>"+
                        "<p><i>人数：</i><span>"+info.userCount+"</span></p>"+
                        "<p><i>日期：</i><span>"+info.goTime+"</span></p>"+
                        "<p><i>备注：</i><span>"+info.remark+"</span></p>";
                    if (info.tripOver == "1") {
                        html+= "<span class='seal_over'></span>";
                    }
                    html+="</li>";
                    ulBox.append(html);
                }
                var infoLen = ulBox.children().length;
                if (infoLen>0) {
                    notMsg.addClass("hide");
                    ulBox.removeClass("hide");
                } else {
                    ulBox.addClass("hide");
                    notMsg.removeClass("hide");
                }
            }
        }
    });
}

/**
 * 计算备注字数
 */
$(document).on("input",".js-complete-words",function(){
    var currentLen = $(this).val().length;
    var countBox = $(".js-count-word");
    countBox.text(currentLen);
});

//出发地，目的地选择框
$(document).on("click",".js-city-selection",function(){
    var $t = $(this);
    var addressCode = $t.siblings("input");
    weui.picker([
        {
            label: '巨鹿',
            value: 130529,
            //disabled: true // 不可用
        },
        {
            label: '北京',
            value: 110000
        },
        {
            label: '邢台',
            value: 130500
        },
        {
            label: '石家庄',
            value: 130100,
        }
    ], {
        className: 'custom-classname',
        container: 'body',
        defaultValue: [130529],
        onConfirm: function (result) {
            $t.text(result[0].label);
            addressCode.val(result[0].value);
            if ($("#titleFlag").val()=="infoList") {
                freshList();
            }
        },
    });
});









