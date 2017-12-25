define(function(require, exports, module) {
	var fire = require('fire'),
	pager = function(){
		this.init.call(this,arguments[0]);
	};
	$.extend(pager.prototype,fire,{
		init:function(config){
			this.pageNum = 1;
			this.config = $.extend({
				pageSize:5,
				isPageValue:true,
				isCount:true
			},config);
		},
		setRecords:function(records){
			this.records = records;
		},
		getPage:function(){
			return this.pageNum;
		},
		render:function(){
			var self = this,
				config = this.config,
				page = Math.ceil(this.records/config.pageSize),
				el = this.config.el,
				pageNum = parseInt(this.pageNum)>page?page:parseInt(this.pageNum),
				pageNum = parseInt(this.pageNum)<1?1:parseInt(this.pageNum),
				pageElm = '<div class="ui-page-num clearfix">';

				if(config.isCount){
					pageElm ='<span class="ui-page-count">共'+this.records+'条数据</span>'+pageElm;
				}

				var fix = pageNum+5<=page?0:5 - (page - pageNum);
				var startPage;
				if(pageNum-5-fix>=1){
					startPage = pageNum - fix -5;
				} else {
					startPage = 1;
				}
				var fix = pageNum - 5>=1?0:5 - (pageNum - 1);
				var endPage;
				if(pageNum+fix+5<=page){
					endPage = pageNum + fix +5;
				} else if(page != 0) {
					endPage = page;
				} else {
					endPage = 1;
				}
				pageElm += (pageNum == 1?'<span class="ui-page-prev ui-page-disable">prev</span>':'<a href="javascript:;" pageNum="'+(pageNum-1)+'" class="ui-page-prev">prev</a>');
				for(var i=startPage;i<=endPage;i++){
					pageElm +=(pageNum==i?"<span>"+i+"</span>":'<a href="javascript:;" pageNum="'+i+'">'+i+'</a>');
				}
				if(page != 0){
					pageElm += (pageNum == page?'<span class="ui-page-next ui-page-disable">Next</span>':'<a href="javascript:;" pageNum="'+(pageNum+1)+'" class="ui-page-next">Next</a>');
				} else {
					pageElm += '<span class="ui-page-next ui-page-disable">Next</span>';
				}
				
				pageElm += '</div>';
				if(config.isPageValue){
					pageElm+='<input type="text" class="span1 j_pageValue"/><button class="btn btn-primary btn-xs" type="button" style="position:relative;top:-2px;">确定</button>&nbsp;&nbsp;每页显示&nbsp;&nbsp;<select class="ui-page-pagesize"><option value="10">10</option><option value="20">20</option><option value="40">40</option><select>&nbsp;&nbsp;条数据';
				}
				pageElm += "</div>";
					
				el.html(pageElm);
				$.each($(".ui-page-pagesize",el)[0].options,function(i,v){
					if(v.value == self.config.pageSize){
						$(".ui-page-pagesize",el)[0].selectedIndex = i;
					}
				});
				$(".ui-page-pagesize",el).on("change",function(){
					self.config.pageSize = this.value;
					self.pageNum = 1;
					self.fire("pageChange");
					self.render();
				});
				$("a",el).click(function(){
					self.pageNum = $(this).attr("pageNum");
					self.fire("pageChange");
					self.render();
					return false;
				});
				$(".btn",el).click(function(){
					self.pageNum = $(".j_pageValue",el).val();
					self.fire("pageChange");
					self.render();
					return false;
				});
			}
		});
		module.exports = pager;
});


