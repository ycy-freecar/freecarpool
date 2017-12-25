define(function(require, exports, module) {
(function($){ 
    $.extend({ 
        /** 
        * url get parameters 
        * @public 
        * @return array() 
        */ 
        fullForm:function(form,data) 
        { 
            $.each(data,function(k,v){
                if(typeof v === "string" || typeof v === "number"){
                    if(form[k]&&form[k].tagName =="SELECT"){

                        $.each(form[k].options,function(index,option){
                            if(option.value == v){
                                form[k].selectedIndex = index;
                                return false;
                            }
                        });
                    }
                }
            });
        }, 
    }); 
    $.extend({ 
        /** 
        * url get parameters 
        * @public 
        * @return array() 
        */ 
        urlGet:function() 
        { 
        var aQuery = window.location.href.split("?");//取得Get参数 
        var aGET = new Array(); 
        if(aQuery.length > 1) 
        { 
        var aBuf = aQuery[1].split("&"); 
        for(var i=0, iLoop = aBuf.length; i<iLoop; i++) 
        { 
        var aTmp = aBuf[i].split("=");//分离key与Value 
        aGET[aTmp[0]] = aTmp[1]; 
        } 
        } 
        return aGET; 
        }, 
    }); 
    $.extend({ 
        radix:function(str,length) 
        { 
            var str = str+"";
            if(str == ""){
                return str;
            }else if(str == "0"){
                return "0";
            }else if(str.length>length){
               return str.substr(0,str.length-length)+"."+str.substr(str.length-length,str.length)
            }else{
                return str;
            }
        }
    }); 
    
    if(window.cityData){
        $.extend({ 
            /** 
            * url get parameters 
            * @public 
            * @return array() 
            */ 
            getCityCode:function(receiverProvince,receiverCity,receiverArea) 
            { 

                var cityCode = "";
                $.each(window.cityData.nodes,function(k,v){
                    if(v.value == receiverProvince){
                        cityCode = v.id;
                        if(receiverCity!=""){
                            $.each(v.children,function(kk,vv){
                               if(vv.value == receiverCity){
                                cityCode = vv.id;
                                    if(receiverArea!=""){
                                       $.each(vv.children,function(kkk,vvv){
                                         if(vvv.value == receiverArea){
                                            cityCode = vvv.id;
                                            }
                                       })
                                   }
                                }
                            });
                        }
                    }
                });
                return cityCode;
            }, 
        }); 
   }
})(jQuery);
    Date.prototype.pattern=function(fmt) {        
        var o = {        
        "M+" : this.getMonth()+1, //月份        
        "d+" : this.getDate(), //日        
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
        "H+" : this.getHours(), //小时        
        "m+" : this.getMinutes(), //分        
        "s+" : this.getSeconds(), //秒        
        "q+" : Math.floor((this.getMonth()+3)/3), //季度        
        "S" : this.getMilliseconds() //毫秒        
        };        
        var week = {        
        "0" : "\u65e5",        
        "1" : "\u4e00",        
        "2" : "\u4e8c",        
        "3" : "\u4e09",        
        "4" : "\u56db",        
        "5" : "\u4e94",        
        "6" : "\u516d"       
        };        
        if(/(y+)/.test(fmt)){        
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
        }        
        if(/(E+)/.test(fmt)){        
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
        }        
        for(var k in o){        
            if(new RegExp("("+ k +")").test(fmt)){        
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
            }        
        }        
        return fmt;        
    }      

    Date.prototype.format = function(format){ 
        var o = { 
        "M+" : this.getMonth()+1, //month 
        "d+" : this.getDate(), //day 
        "h+" : this.getHours(), //hour 
        "m+" : this.getMinutes(), //minute 
        "s+" : this.getSeconds(), //second 
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
        "S" : this.getMilliseconds() //millisecond 
        } 

        if(/(y+)/.test(format)) { 
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
        } 

        for(var k in o) { 
        if(new RegExp("("+ k +")").test(format)) { 
        format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
        } 
        } 
        return format; 
    }

    Handlebars.registerHelper('time', function(item) {
       var t = new Date(item);
       var timeStr = item == "" || item== null?"":t.format("yyyy-MM-dd hh:mm:ss"); 
       timeStr = timeStr.indexOf(" 00:00:00")!=-1?timeStr.replace(" 00:00:00",""):timeStr;
      return timeStr;
    });

});