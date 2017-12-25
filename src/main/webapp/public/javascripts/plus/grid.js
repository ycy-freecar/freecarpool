define(function(require, exports, module) {
	  	  var fire = require('fire'),
	      mygrid = function(){
	      	this.init.call(this,arguments[0]);
	      };
	      $.extend(mygrid.prototype,fire,{
				init:function(config){
					this.config = config;
					this.config.template = Handlebars.compile(config.template);
					this.reload();
				},
				render:function(data){	
					var config = this.config,
						el = config.el,
						self = this,
						data = config.format?config.format(data):data;
						el.animate({
							opacity:0
						},"fast","swing",function(){
							el.html(config.template(data));
							el.animate({
								opacity:1
							},"fast");	
							self.fire("beforeRender",el);
							self.event();
						});	

					
				},
				event:function(){
					var config = this.config,
						el = config.el,
						self = this;
					$(".checkbox-all",el).click(function(){
						if(this.checked){
							$("table tbody input[type=checkbox]",el).each(function(i,v){
								this.checked = true;
								if(self.Data.results[i])
								self.Data.results[i]._sed = true;
							})
						}else{
							$("table tbody input[type=checkbox]",el).each(function(i,v){
								$(this).removeAttr("checked");
								if(self.Data.results[i])
								self.Data.results[i]._sed = false;
							});
						}
					});
					$("table tbody input[type=checkbox]",el).click(function(){
						self.Data.results[this.value]._sed = this.checked?true:false;
					});
				},
				getSed:function(){
					var list = [],
						args = arguments[0];
					$.each(this.Data.results,function(i,li){
						if(li._sed){
							if(args !== undefined && li[args]!== undefined){
								list.push(li[args]);
							}else{
								list.push(li);
							}
						}
					});			
					return list;	
				},
				setAttr:function(params){
					this.params = params;
				},
				getData:function(){
					return this.Data;
				},
				reload:function(){
					var self = this,
						config = self.config,
						attr = config.attr?config.attr:{},
						t = Date.parse(new Date);
						attr = $.extend(attr,this.params);
					$.ajax({
						url:config.url,
						data:attr,
						type:"post",
						complete:function(d){
							try{
								if(d.responseText!=null){
									var result =  eval("("+d.responseText+")");
								}
							}catch(e){
								var result = null;
							}
							
							self.Data = result;
							self.fire("dataChange",self.Data);
							self.render(self.Data);
						}
					});
				}
		  });
		 module.exports = mygrid;
});
