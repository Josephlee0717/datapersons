/**
 * 
 */

 //get QueryString array
var Util = {	
	getQueryString : function (){
    	var result = location.search.match(new RegExp("[\?\&][^\?\&]+=[^\?\&]+","g")); 

     	if(result == null){
        	return "";
     	}

     	for(var i = 0; i < result.length; i++){
        	result[i] = result[i].substring(1);
     	}

     	return result;
	},

	//get QueryString by name
	getQueryStringByName : function(name){
    	var result = location.search.match(new RegExp("[\?\&]" + name+ "=([^\&]+)","i"));
     	if(result == null || result.length < 1){
        	return "";
     	}

     	return result[1];

	},

	//get QueryString by index
	getQueryStringByIndex : function(index){
    	if(index == null){
        	return "";
     	}

     	queryStringList = getQueryString();
     	if (index >= queryStringList.length){
        	return "";
     	}

     	var result = queryStringList[index];
     	var startIndex = result.indexOf("=") + 1;
     	result = result.substring(startIndex);

     	return result;
	}
	
};