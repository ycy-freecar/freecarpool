window.easySelect = {};
$.fn.extend({
	easySelect:function(config){
		var self = this;
		$.each(this,function(k,s){
			var elm = $(s),
				name = elm.attr("name"),
				platType = elm.attr('platType'),
				select = $('<div class="select-container">\
							<div class="select-txt"></div>\
							<div class="select-options"></div>\
							<input type="hidden" class="select-val" name="'+name+'"/>\
						</div>'),
				html = "",
				txt = $(".select-txt",select),
				val = $(".select-val",select),
				option = $(".select-options",select),
				index = s.options[s.selectedIndex];
				if(index === undefined){
					return true;
				}
				optID = elm.attr("id")
				if(optID){
					val.attr("id",optID);
				}
				val.val(index.value?index.value:"");
				txt.html(index.innerHTML);
				select.css({
					width:config.width?config.width:120
				});
				$.each(s.options,function(i,option){
					platTypeVal = $(option).attr('platType');
html += '<span title="'+option.innerHTML+'" v="'+option.value+'"'+(platTypeVal?'plat-type="'+platTypeVal+'"':'')+'>'+option.innerHTML+'</span>';
				});
				option.html(html);
				var optLength = $('span',option).length;
				option.css({
					width:config.width?config.width:120,
					height:optLength<=22?'auto':580,
					overflow:'auto'
				})					
				txt.click(function(){
					select.addClass("select-drop");
					
				});
				$(option).click(function(e){
					if(e.target.tagName == "SPAN"){
						txt.html($(e.target).html());
						val.val($(e.target).attr("v"));
						select.removeClass("select-drop");
					}
				});
				select.on("mouseleave",function(){
					select.removeClass("select-drop");
				});
				elm.replaceWith(select);
				window.easySelect[name] = select;
				//select
		});
	}
});