
define(function(require, exports, module) {
	var fire = {
		queue:null,
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
			if(!$.isArray(this.queue[evt]))return false;
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
	module.exports = fire;
});