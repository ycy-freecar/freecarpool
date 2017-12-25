(function(){
	$.extend({
		overlay:function(elm){
			var elm = elm.modal();
			elm.on('hidden.bs.modal', function (e) {
			  elm.remove();
			});
			elm.on("submit",function(){
				//$(".j_submit",elm)[0].click();
				return false;
			});
			return {
				mode:elm,
				close:function(){
					elm.modal('hide');
				}
			};
		},
		loading:function(){
			var loading = $(['<div class="modal fade"><div class="modal-dialog"><div class="modal-content" ><div class="modal-body">',
							"<div style='height:120px;overflow:hidden; text-align:center;'><img src='/public/images/loading.gif'/></div>",
							'</div></div></div></div>'].join(""));
			overlay = $.overlay(loading);
			return overlay;
		},
		msgContainer:{},
		msg:function(txt,after){
			var self = this;
			typeof (this.msgContainer.mode) != "undefined" && this.msgContainer.mode.remove();
			var _txt = txt&&txt!=""?txt:"处理成功";
			if($("#J_overlay_msg_content")[0]){
				$("#J_overlay_msg_content").html(_txt);
				return false;
			}
			var template = $('<div class="modal fade" data-backdrop="false">\
				                   <div class="modal-dialog">\
				                       <div class="modal-content">\
				                           <div class="modal-body">\
				                               <div id="J_overlay_msg_content">'+_txt+'</div>\
				                           </div>\
				                           <div class="modal-footer">\
				                               <button type="submit" class="btn btn-primary">确认</button>\
				                           </div>\
				                       </div>\
				                   </div>\
				               </div>');
			this.msgContainer = $.overlay(template);
			$(".btn-primary",template).click(function(){
				self.msgContainer.mode.modal("hide");
				self.msgContainer = {};
				after && after();
			});
		},
		confirm:function(txt,after){
			var template = $(['<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-body">',
							txt,
							'</div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">关闭</button><button type="submit" class="btn btn-primary">确认</button></div></div></div></div>'].join(""));
			var overlay = $.overlay(template);
			$(".btn-primary",template).click(function(){
				overlay.close();
				after && after();
			});
		},
		dialog:function(config){
			var template = $('<div class="modal fade">\
			  <div class="modal-dialog" '+(config.width?"style='width:"+config.width+"px;'":"")+'>\
			    <div class="modal-content">\
			      <div class="modal-header">\
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
			        <h4 class="modal-title">'+config.title+'</h4>\
			      </div>\
			        <div class="modal-body">\
				    </div>\
			       <div class="modal-footer">\
			        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>\
			        <button type="button" class="btn btn-primary j_submit">确认</button>\
			      </div>\
				</div></div></div>');
			if(typeof config.content == "string"){
				$(".modal-body",template).html(config.content);
			}else{
				$(".modal-body",template).append(config.content);
			}
			return $.overlay(template);
		}
	});
	$.fn.extend({
		overlay:function(config){
			var _config = {
				footer:true
			};
			_config = $.extend(_config,config);
			if(config.title.indexOf("详情")!= -1){
				_config.footer = false;
			}
			
			if(config.noneFooter){
				_config.footer = false;
			}
			var tplHtml = '<div class="modal fade">\
			  <div class="modal-dialog" '+(_config.width?"style='width:"+_config.width+"px;'":"")+'>\
			    <div class="modal-content">\
			      <div class="modal-header">\
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
			        <h4 class="modal-title">'+_config.title+'</h4>\
			      </div>\
			        <div class="modal-body">\
				    </div>';
				    if(_config.footer){
				    	tplHtml+='<div class="modal-footer">\
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>\
					        <button type="button" class="btn btn-primary j_submit">确认</button>\
					      </div>';
				    }
				    tplHtml+='</div></div></div>';
			var template = $(tplHtml);
			$(".modal-body",template).append(this);
			$.overlay(template);
			var _overlay ={
				islock:false,
				lock:function(){
					var self = this;
					//$(".j_submit",template).html("<img src='/public/images/dialogLoading.gif' height='10'/>");
					//$(".j_submit",template).addClass("lock");
					$(".j_submit",template).attr("disabled", true);
					this.islock = true;
					setTimeout(function(){
						self.islock = false;
						self.unlock();
					},1000);
				},
				unlock:function(){
					var self = this;
						//$(".j_submit",template).html("确定");
						//$(".j_submit",template).removeClass("lock");
					$(".j_submit",template).removeAttr("disabled");
					self.islock = false;
				},
				init:function(){
					var self = this;
						$(".j_submit",template).click(function(){
							if(!self.islock){
									self.lock();
									self.fire("submit",self);
							}
						});
				},
				elm:template,
				close:function(){
					template.modal("hide");
				},queue:null,
				on:function(evt,func){
					
					if(this.queue == null)this.queue = {};
					if(!$.isArray(this.queue[evt])){
						this.queue[evt] = [];
					}
					this.queue[evt].push(func);
				},
				fire:function(evt,args){
					var self = this,
						args = args !== undefined?args:self;
					if(this.queue == null||!$.isArray(this.queue[evt]))return false;

					$.each(this.queue[evt],function(index,func){
						
						$.isFunction(func)&&func.call(self,args);
					});
				},set:function(key,value){
					if(this[key]!==undefined){
						this[key] = value;
					}
					this.fire("setValue");
				},get:function(key){
					if(this[key]!==undefined){
						return this[key];
					}else{
						return null;
					}
					this.fire("getValue");
				}
			}
			_overlay.init();
			return _overlay;
		}
	});
})();