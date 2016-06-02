if (!String.prototype.ltrim) {
	String.prototype.ltrim = function() {
		return this.replace(/^\s+/, "");
	}
}

String.prototype.trimn = function() {
	return this.trim().replace(/^[\n|\r\n]*|[\n|\r\n]*$/g, '');
}

String.prototype.replaceAll = function(s1, s2) {
	var r = new RegExp(s1.replace(/([\(\)\[\]\{\}\^\$\+\-\*\?\.\"\'\|\/\\])/g, "\\$1"), "ig");
	return this.replace(r, s2);
}

function isNull(obj) {
	if (obj == null || obj == undefined || obj.length == 0)
		return true;
	return false;
}

co = {
	requestTplStr:'{"action":"","body":{}}',	
	
	request:function(ops){
		if (!ops) {
			return;
		}
		
		ops.async = !(false === ops.async);
		
		if (!ops.params) {
			ops.params = {};
		}
		
		var d = co.datapersons.buildRequestParam(ops);
		var params = $.toJSON(d);
		$.ajax({
			type:  ops.method ? ops.method : 'POST',
			async: ops.async,
            url: co.datapersons.url,
            cache: false,
            
            data: {
				request : params
			},
			
            dataType: "json",
            
            success: function(data){
            	var status = data.status;
            	if(status == '0000'){
    				if (ops.success) {
    					ops.success(data);
    				}
            	}else if(status == '9999'){
            		if (!ops.systemErrorHandle) {
            			if(co.datapersons.ui.systemErrorHandle){
            				co.datapersons.ui.systemErrorHandle(data.error);
            			}
    					return;
    				}
    				ops.systemErrorHandle(data.error);
            	}else {
            		if (!ops.requestFailureHandle) {
            			if(co.datapersons.ui.requestFailureHandle){
            				co.datapersons.ui.requestFailureHandle(data.error);
            			}
    					return;
    				}
    				ops.requestFailureHandle(data.error);
            	}
            },
            
            error: function(XMLHttpRequest, textStatus, errorThrown){
            	if (!ops.serverErrorHandle) {
            		if(co.datapersons.ui.serverErrorHandle){
            			co.datapersons.ui.serverErrorHandle();
            		}
					return;
				}
				ops.serverErrorHandle();
            }
            
         });
	}
};

co.datapersons = {
		url :  location.href.slice(0, location.href.lastIndexOf('datapersons/'))+'datapersons/HttpService',
		
		buildRequestParam : function(ops){
			var r = $.evalJSON(co.requestTplStr);
			var requestParams = $.extend({},r);
			
			requestParams.action = ops.action;
			
			$.extend(requestParams.body,ops.body);
			
			if(co.datapersons.manager && co.datapersons.manager.sessionid && !isNull(co.datapersons.manager.sessionid)){
				requestParams.sessionid = co.datapersons.manager.sessionid;
			}
			
			return requestParams;
		},
		
		createRequestParam : function(action){
			var requestParams = $.extend({},co.requestParamTpl);
			requestParams.action = action;
			return requestParams;
		}
};
