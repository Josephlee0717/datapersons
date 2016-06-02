co.datapersons.ui = {
		
	serverErrorHandle: function(){
		
	},
	
	systemErrorHandle: function(error){
		art.dialog({
		    content: error.message,			
			cancelVal: 'Close',
			cancel: true 
		});			
	},
	
	requestFailureHandle: function(error){
		art.dialog({
		    content: error.message,			
			cancelVal: 'Close',
			cancel: true 
		});	
	},
	
	init: function(){
		
	}
	
};


co.datapersons.manager = {
	phonenumber :"",	
	usertype : "",
	sessionid :"",
	verifyCode :"",
	verifyGraph : false,
	curPageName:"",
	init:function(){
		if (!window.$) {
			return;
		}
		
		
	},
	
	pageLoad : function(){
		co.request({
			action:"user.getLoginData",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			var id = result.result.i;
	    			var phonenumber = result.result.p;
	    			var session = result.result.s;
	    			var usertype = result.result.t;
	    			if (co.datapersons.manager.curPageName == "ureceive"){
	    				co.datapersons.manager.phonenumber = phonenumber;
	    				co.datapersons.manager.getUserInfor();	
	    			}else if(co.datapersons.manager.curPageName == "shopperfect"){
	    				
	    			}
	    			
	    			if(id == "" || phonenumber== "" ||session =="" || usertype == ""){
	    				window.location.href = "../index.html"; 
	    			}    			
	    		}
				console.log(data);
			}
		});
	},
	
	getRecordCount : function(userType){		
		co.request({
			action:"user.getRecordCount",
			body:{usertype:userType},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status  == "0000"){
	    				var recordCount = result.result.recordCount; 
	    				if(userType =="user"){
	    					$("#personCount").text(recordCount);
	    				}else{
	    					$("#bussinessCount").text(recordCount);
	    				}
	    				
	    			}
	    		}
				console.log(data);
			}
		});
	},
	
	
	
	login :function(type){
		var mobileNum = $("#mobileNum").val();		
		var password = $("#password").val();
		co.request({
			action:"User.login",
			body:{phonenumber:mobileNum,password:password,usertype:type},
			success:function(data){	    			    						
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				if(type == "user"){
	    					window.location.href = "ureceive.html";
	    				}
	    				if(type == "shop"){
	    					window.location.href = "shopperfect.html";
	    				}
	    			}
	    		}
			}
		});
	},
	
	logout:function(){
		co.request({
			action:"User.logout",
			body:{},
			success:function(data){	    			    						
				co.datapersons.manager.verifyUser();
			}
		});
	},
	
	submitPost : function(userType){
		var verifyCode = co.datapersons.manager.registerVerify();
		var password = $("#password").val();
		if(verifyCode == "0000"){
			co.request({
				action:"User.checkPhonenumber",
				body:{phonenumber:co.datapersons.manager.phonenumber},
				success:function(data){
					console.log(data);
					var result = data;
		    		if(result != undefined || result != null){
		    			if(result.status == "0000"){
		    				co.request({
		    					action:"User.register",
		    					body:{phonenumber:co.datapersons.manager.phonenumber,password:password,usertype:userType},
		    					success:function(data){
		    						console.log(data);
		    						var result = data;
		    			    		if(result != undefined || result != null){
		    			    			if(result.status == "0000"){
		    			    				co.datapersons.manager.login();
		    			    			}	
		    			    		}
		    						console.log(data);
		    					}
		    				});
		    			}
		    		}
					console.log(data);
				}
			});
		}
	},
	
	updateUser : function(){
		var username = $("#userName").val();
		var idCard = $("#identityCard").val();
		var userArea = $("#userArea").val();
		var ICBCCard = $("#bankCardICBC").val();
		var mail = $("#mail").val();
		
		co.request({
			action:"User.updateUserInfo",
			body:{phonenumber:co.datapersons.manager.phonenumber,username:username,idCard:idCard,ICBCCard:ICBCCard,userArea:userArea,mail:mail},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				art.dialog({
	    				    content: '用户信息已保存！',
	    				    ok: function () {
//	    				    	window.location.href = "userperfect.html"; 
	    				    	
	    				    }
	    				});	
	    			}	
	    		}
				console.log(data);
			}
		});
	},
	
	updateBusiness : function(){
		var username = $("#username").val();
		var idCard = $("#idCard").val();
		var ICBCCard = $("#ICBCCard").val();
		var WXNum = $("#WXNum").val();
		var ZFBNum = $("#ZFBNum").val();
		
		co.request({
			action:"User.updateUserInfo",
			body:{username:username,idCard:idCard,ICBCCard:ICBCCard,WXNum:WXNum,ZFBNum:ZFBNum},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				art.dialog({
	    				    content: '用户信息已保存！',			
	    					cancelVal: 'Close',
	    					cancel: true 
	    				});	
	    			}
	    		}
				console.log(data);
			}
		});
	},
	
	getUserInfor: function(){		
		co.request({
			action:"User.getUserInfo",
			body:{phonenumber:co.datapersons.manager.phonenumber},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				//取出返回值
	    				var registime = result.result.registime;
	    				var phonenumber =result.result.phonenumber;
	    				var identitycard = result.result.identitycard;
	    				var smallletter = result.result.smallletter;
	    				var paytreasurable = result.result.paytreasurable;	    				
	    				var businessbank = result.result.businessbank;
	    				var name = result.result.name;
	    				
	    				var perfecttime = result.result.perfecttime;
	    				var refereeid = result.result.refereeid;
	    				var landtime = result.result.landtime;
	    				var lastlandtime = result.result.lastlandtime;
	    				var sex = result.result.sex;
	    				var age = result.result.age;
	    				var birthday = result.result.birthday;
	    				var usertype = result.result.usertype;
	    				var phoneattributive = result.result.phoneattributive;
	    				var bodyattributive = result.result.bodyattributive;
	    				var mailaddress = result.result.mailaddress;
	    				var usershare = result.result.usershare;
	    				var shopshare = result.result.shopshare;
	    				
	    				var payintegral = result.result.payintegral;
	    				var distribution = result.result.distribution;
	    				
	    				var shopid = result.result.shopid;
	    				var paytime = result.result.paytime;
	    				var paytotal = result.result.paytotal;
	    				var balance = result.result.balance;
	    				var paycount = result.result.paycount;
	    				
	    				var refereecount = result.result.refereecount;
	    				var shopname = result.result.shopname;
	    				var paytimecalc = result.result.paytimecalc;
	    				var paytype = result.result.paytype;
	    				
	    				//给元素赋值
	    				$("#userName").val(name);
	    				$("#identityCard").val(identitycard);
	    				$("#userArea").val(bodyattributive);
	    				$("#bankCardICBC").val(businessbank);
	    				$("#mail").val(mailaddress);
	    				
	    			}
	    		}
				console.log(data);
			}
		});
	},
	
	getShopInfor: function(){		
		co.request({
			action:"User.getUserInfo",
			body:{phonenumber:co.datapersons.manager.phonenumber},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#username").val(result.result.FullName);
	    				$("#idCard").val(result.result.idCard);
	    				$("#ICBCCard").val(result.result.ICBCCard);
	    				$("#WXNum").val(result.result.WXNum);
	    				$("#ZFBNum").val(result.result.ZFBNum);
	    					
	    			}
	    		}
				console.log(data);
			}
		});
	},

	getTodayRegister:function(){
		var now = new Date();
		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		co.request({
			action:"User.getTodayRegister",
			body:{today:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#registerSum").text(result.result.value);
	    			}
	    		}
				console.log('TodayRegister is : '+data);
			}
		});
	},
	getTodayConsume:function(){
		var now = new Date();
		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		co.request({
			action:"User.getTodayConsume",
			body:{today:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#consumSum").text(result.result.value);
	    			}
	    		}
				console.log('TodayConsume is : '+ data);
			}
		});
	},
	getCountryRegister:function(){
		co.request({
			action:"User.getCountryRegister",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#userAllCount").text(result.result.value);
	    			}
	    		}
				console.log('CountryRegister is : '+ data);
			}
		});
	},
	getCountryConsume:function(){
		co.request({
			action:"User.getCountryConsume",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#consumeAllSum").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getUserpageConditionRegister:function(){		
		var today = $("#queryDate").datebox('getValue');
		co.request({
			action:"User.getTodayRegister",
			body:{today:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#userpage-conditionRegister").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getUserpageConditionConsume: function(){
		var today = $("#queryDate").datebox('getValue');
		co.request({
			action:"User.getTodayConsume",
			body:{today:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#userpage-conditionConsume").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
//	userpage-conditionRegisterByArea
	getUserpageConditionRegisterByArea:function(){
		var area = $("#userpage-conditionArea").val();
		co.request({
			action:"User.GetRegisterByArea",
			body:{area:area},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#userpage-conditionRegisterByArea").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	//userpage-conditionConsumeByArea
	getUserpageConditionConsumeByArea:function(){
		var area = $("#userpage-conditionArea").val();
		co.request({
			action:"User.GetConsumeByArea",
			body:{area:area},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#userpage-conditionConsumeByArea").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getTodayShopRegister : function(){
		var now = new Date();
		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		co.request({
			action:"User.GetTodayShopRegister",
			body:{date:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-todayhopCount").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getQueryNeedVerifyShop:function(){		
		var shopname = $("#shopnameInput").val();
		co.request({
			action:"User.QueryNeedVerifyShop",
			body:{shopname:shopname},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopConfirmTable").datagrid('loadData',result.rows);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getShopRegisterByDate : function(){		
		var date = $("#shopRegister-date").datebox('getValue');
		co.request({
			action:"User.GetTodayShopRegister",
			body:{date:date},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-queryCurdayRegist").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getUnauditedCount : function(){
		var now = new Date();
		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		co.request({
			action:"User.GetUnauditedCount",
			body:{date:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-unauditedCount").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	getTodayShopConsume : function(){
		var now = new Date();
		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		co.request({
			action:"User.getTodayConsume",
			body:{today:today},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-todayConsume").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	getShopConsumeByDate : function(){		
		var date = $("#shopRegister-date").datebox('getValue');
		co.request({
			action:"User.getTodayConsume",
			body:{today:date},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-queryCurdayConsume").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
		
	},
	
	getShopCount: function(){
		
		co.request({
			action:"User.GetShopCount",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-shopCount").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	}, 
	
	//shopRegister-shopConsume
	
	getShopConsume: function(){		
		co.request({
			action:"User.GetShopConsume",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#shopRegister-shopConsume").text(result.result.value);
	    			}
	    		}
				console.log('CountryConsume is : '+data);
			}
		});
	},
	
	menuControl:function(showMenuItem){
		$(".rightPanel").hide();
		$("#"+showMenuItem).show();
		
	},
	
	createUserTable:function(){		
	 	$('#userTable').datagrid({
	 		striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 520, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[
	        {  
	            field: 'id', 
	            title: 'ID',  
	            width: 50,
	            align: 'center'  
	        },  
	        {  
	            field: 'registime',  
	            title: '注册时间',  
	            width: 170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'name',  
	            title: '姓名',
	            width: 90,
	            align: 'center'
	        },  
	        {  
	            field: 'phonenumber',  
	            title: '手机号码',
	            width:  200,
	            align: 'center'	
	        },  
	        {  
	            field: 'identitycard',  
	            title: '身份证号',
	            width: 270,
	            align: 'center'
	        },  
	        {  
	            field: 'paytotal',  
	            title: '消费',
	            width: 100,
	            align: 'center'
	        },  
	        {  
	            field: 'paycount',  
	            title: '收入',
	            width: 100,
	            align: 'center'
	        },  
	        {  
	            field: 'refereecount',  
	            title: '推荐人数',
	            width: 100,
	            align: 'center'
	        }
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#userTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	
	createProxyTable: function(){
		$('#proxyTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 620, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[{  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'registime',  
	            title: '区域',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'name',  
	            title: '入驻商铺',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '区月消费',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'proxyid',  
	            title: '代理ID',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'proxyshare',  
	            title: '提成',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areausernumber',  
	            title: '代理收益',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areashopnumber',  
	            title: '代理类型',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthtransac',  
	            title: '共代理区',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '姓名',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'whetherproxy',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'verifytime',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'checkpeople',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'refereecount',  
	            title: '代理设置时间',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'undonecause',  
	            title: '设/撤代理',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'undonecause',  
	            title: '用户资料',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'undonecause',  
	            title: '审核人',
	            width:100,
	            align: 'center'
	        }
	        
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#proxyTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	
	createShopRegisterTable: function(){
		$('#shopRegisterTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 520, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[{  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'registime',  
	            title: '注册时间',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'verifytime',  
	            title: '审核时间',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'shopname',  
	            title: '商店名称',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'AREA',  
	            title: '所属区域',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'name',  
	            title: '申请人',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'phone',  
	            title: '联系方式',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areashopnumber',  
	            title: '营业额',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthtransac',  
	            title: '推荐人数',
	            width:100,
	            align: 'center'
	        },  
//	        {  
//	            field: 'monthlypayment',  
//	            title: '消费总额',
//	            width:100,
//	            align: 'center'
//	        },  
//	        {  
//	            field: 'whetherproxy',  
//	            title: '收入',
//	            width:100,
//	            align: 'center'
//	        },  
	        {  
	            field: 'verifytime',  
	            title: '商家资料',
	            width:100,
	            align: 'center'
	        }
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#shopRegisterTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	
	createShopConfirmTable: function(){
		$('#shopConfirmTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 670, 
	        fitColumns:true,	       
	        pagination:false,//分页控件 
	        columns: [[
	        { field:'ck',checkbox:true },
	        {  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'userid',  
	            title: '用户ID',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'area',  
	            title: '区域',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'shopaddress',  
	            title: '地址',
	            width:190,
	            align: 'center'  
	        },  
	        {  
	            field: 'shopbrand',  
	            title: '品牌',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'applytime',  
	            title: '申请时间',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'shopname',  
	            title: '商店名称',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'incorporator',  
	            title: '申请人',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'phone',  
	            title: '申请人电话',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'verifytime',  
	            title: '批准日期',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'checkpeople',  
	            title: '审核人',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'undoncause',  
	            title: '未通过原因',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'verifystatus',  
	            title: '审核状态',
	            width:100,
	            align: 'center',
//	            formatter:function(value,row){
//                     return row.verifyText;
//                },
	            editor: { 
	            	type: 'combobox', 
	            	options: {
	            		url:'js/verifyStatus.json',
	            		method:'get',
	            		valueField: 'varifyValue',	            		
	            		textField: 'verifyText',
	            		editable: false
	            	}
	            }
	        },  
	        {  
	            field: 'reducepoint',  
	            title: '扣点率',
	            width:100,
	            align: 'center',
	            editor : "numberbox"

	        },  
	        {  
	            field: 'image',  
	            title: '图片',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'shoptype',  
	            title: '商业类型',
	            width:100,
	            align: 'center'
	        }
	        ]],
	        toolbar:[
	        {
	        	text:"编辑",
	        	iconCls:"icon-edit",
	        	handler:function(){
	        		var row = $("#shopConfirmTable").datagrid("getSelected");
	        		if(row){
	        			var rowIdx = $("#shopConfirmTable").datagrid("getRowIndex",row);
	        			$("#shopConfirmTable").datagrid("beginEdit",rowIdx);
	        		}
	        	}
	        
	        },{
	        	text:"结束编辑",
	        	iconCls:"icon-cancel",
	        	handler:function(){
	        		var row =	$("#shopConfirmTable").datagrid("getRows");
	        		for(var i = 0; i< row.length; i ++){
	        			$("#shopConfirmTable").datagrid("endEdit",i);
	        		}
	        	}	        	
	        },{
	        	text:"保存",
	        	iconCls:"icon-save",
	        	handler:function(){
	        		var row =	$("#shopConfirmTable").datagrid("getRows");
	        		for(var i = 0; i< row.length; i ++){
	        			$("#shopConfirmTable").datagrid("endEdit",i);
	        		}
	        		
	        		if($("#shopConfirmTable").datagrid("getChanges").length){
	        			var updated = $("#shopConfirmTable").datagrid("getChanges","updated");
	        			var effectRow = new Object();
	        			if(updated.length){
	        				effectRow["updated"] = JSON.stringify(updated); 
	        			}
	        			
	        			co.request({
	        				action: "user.VerifyShopByManager",
	        				body:{
	        					updated: effectRow
	        				},
	        				success:function(data){	
	        					co.datapersons.manager.getQueryNeedVerifyShop();
	        			
//	        					$("#shopConfirmTable").datagrid("reload");
	        				}
	        			})
	        		}
	        		
	        	}
	       }]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#shopConfirmTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	//transferTable
	createTransforTable: function(){
		$('#transferTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 525, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[{  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'registime',  
	            title: '区域',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'name',  
	            title: '入驻商铺',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '区月消费',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'proxyid',  
	            title: '代理ID',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'proxyshare',  
	            title: '提成',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areausernumber',  
	            title: '代理收益',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areashopnumber',  
	            title: '代理类型',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthtransac',  
	            title: '共代理区',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '姓名',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'whetherproxy',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'verifytime',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        }
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#transferTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	createReturnTable: function(){
		$('#returnFeeTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 525, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[{  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'registime',  
	            title: '区域',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'name',  
	            title: '入驻商铺',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '区月消费',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'proxyid',  
	            title: '代理ID',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'proxyshare',  
	            title: '提成',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areausernumber',  
	            title: '代理收益',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'areashopnumber',  
	            title: '代理类型',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthtransac',  
	            title: '共代理区',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'monthlypayment',  
	            title: '姓名',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'whetherproxy',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        },  
	        {  
	            field: 'verifytime',  
	            title: '手机号码',
	            width:100,
	            align: 'center'
	        }
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#returnFeeTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	
//	accountMangerTable
	createAccountManagerTable: function(){
		$('#accountMangerTable').datagrid({
			striped: true,	         
        	url:co.datapersons.url, 
//        	queryParams: { request: params },       	
	        singleSelect:true,//是否单选	        
	 		method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	
	        height: 665, 
	        fitColumns:true,
	        pagination:true,//分页控件 
	        columns: [[{  
	            field: 'id',  
	            title: 'ID',  
	            width:50,
	            align: 'center'  
	        },  
	        {  
	            field: 'userid',  
	            title: '用户名',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'password',  
	            title: '密码',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'phonenumber',  
	            title: '联系电话',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'usertype',  
	            title: '用户类型',
	            width:270,
	            align: 'center'
	        }
	        ]]
	 	});
	 	
	 	//设置分页控件 
	    var p = $('#accountMangerTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /*onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }*/ 
	    });
	},
	
	verifyUser: function(){
		co.request({
			action:"User.managerVerify",
			body:{},
			success:function(data){	    			    						
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				if(result.result.t != "admin"){
	    					window.location.href = "manager.html";
	    				}
	    			}
	    		}
			}
		});
	},
	getManagerCount: function(){
		co.request({
			action:"User.GetManagerCount",
			body:{},
			success:function(data){	    			    						
				var result = data;
	    		$("#accountManger-ManagerCount").text(result.result.value);
			}
		});
	}
}

$(function(){
	var htmlPos = window.location.href.indexOf(".html");
	var urlName = window.location.href.slice(window.location.href.lastIndexOf('/')+1,htmlPos);
	co.datapersons.manager.curPageName = urlName;
	
//	var usertype = Util.getQueryStringByName("usertype");
	co.datapersons.manager.verifyUser();
	
	var now = new Date();
	var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
	$('#queryDate').val(today);
	$('#queryDate').datebox({
	   	required: true
	});		
	
	$('#queryDateforShop').val(today);
	$('#queryDateforShop').datebox({
	   	required: true
	});	
	
	$('#queryDateforTransfor').val(today);
	$('#queryDateforTransfor').datebox({
	   	required: true
	});	
	
	$('#queryDateforReturnFee').val(today);
	$('#queryDateforReturnFee').datebox({
	   	required: true
	});
	
	$('#shopRegister-date').val(today);
	$('#shopRegister-date').datebox({
	   	required: true
	});
	
	co.datapersons.manager.getTodayRegister();
	co.datapersons.manager.getTodayConsume();
	co.datapersons.manager.getCountryRegister();
	co.datapersons.manager.getCountryConsume();
	
	co.datapersons.manager.createUserTable();
	
	/*Menu item click*/	
	$("#userEveryDate").click(function(){ 
		co.datapersons.manager.menuControl('userPage');
		co.datapersons.manager.getTodayRegister();
		co.datapersons.manager.getTodayConsume();
		co.datapersons.manager.getCountryRegister();
		co.datapersons.manager.getCountryConsume();		
	 });
	
	$("#userAgency").click(function(){ 
		co.datapersons.manager.menuControl('proxyPage');
		co.datapersons.manager.createProxyTable();		
	});	

	$("#shopEveryDate").click(function(){ 
		co.datapersons.manager.menuControl('shopRegister');
		co.datapersons.manager.createShopRegisterTable();
		//show data 
		co.datapersons.manager.getTodayShopRegister();
		co.datapersons.manager.getUnauditedCount();
		co.datapersons.manager.getTodayShopConsume();
		co.datapersons.manager.getShopCount();
		co.datapersons.manager.getShopConsume();
		
		//===========
		$("#shopRegister-dateCondition").text($("#shopRegister-dateCondition").datebox('getValue'));		
		$("#shopRegister-areaCondition").text($("#shopRegister-area").val());
//		$("#shopRegister-queryCurdayRegist").
	 });
	 
	$("#shopAudit").click(function(){ 
		co.datapersons.manager.menuControl('shopConfirm');
		co.datapersons.manager.createShopConfirmTable();
		co.datapersons.manager.getQueryNeedVerifyShop();
	 });	
		 
	$("#transfer").click(function(){ 
		co.datapersons.manager.menuControl('transferQuery');
		co.datapersons.manager.createTransforTable();
	 })	;

	$("#lineup").click(function(){ 
		co.datapersons.manager.menuControl('returnFee');
		co.datapersons.manager.createReturnTable();
	 })	;
	 
	$("#adminList").click(function(){ 
		co.datapersons.manager.menuControl('accountManger');
		co.datapersons.manager.createAccountManagerTable();	
		$("#accountManager-conditionManager").val('');
		co.datapersons.manager.getManagerCount();
		var d = co.datapersons.buildRequestParam({ action: "User.QueryManager",body:{userid:""}});
		var params = $.toJSON(d);
		var queryParams = $('#accountMangerTable').datagrid('options').queryParams;  
        queryParams.request = params;
        
//		$('#accountMangerTable').datagrid({queryParams: { request: params }});
		$('#accountMangerTable').datagrid("load");
	 });
	 
	 $("#queryForAccountManager").click(function(){ 
	 	var userid = $("#accountManager-conditionManager").val();
	 	var d = co.datapersons.buildRequestParam({ action: "User.QueryManager",body:{userid:userid}});
		var params = $.toJSON(d);
		var queryParams = $('#accountMangerTable').datagrid('options').queryParams;  
        queryParams.request = params;
        
//		$('#userTable').datagrid({queryParams: { request: params }});
		$('#accountMangerTable').datagrid("reload");
	 });
	 
	 /*Query Button click*/
	 
	 $("#dateCand").click(function (){
//	 	$('#userHistroy').load("pages/user/usernumberlist.html");
	 	var registtime = $('#queryDate').datebox('getValue');
	 	var area = $('#userpage-Area').val();
	 	var d = co.datapersons.buildRequestParam({ action: "User.QueryUserInfoByRegisterday",body:{registertime:registtime,area:area}});
		var params = $.toJSON(d);
		var queryParams = $('#userTable').datagrid('options').queryParams;  
        queryParams.request = params;
        
		$('#userTable').datagrid({queryParams: { request: params }});
		$('#userTable').datagrid("reload");
		
		$("#userpage-conditionDate").text(registtime);
		$("#userpage-conditionArea").text(area);
		co.datapersons.manager.getUserpageConditionConsumeByArea();
		co.datapersons.manager.getUserpageConditionRegisterByArea();
		co.datapersons.manager.getUserpageConditionConsume();
		co.datapersons.manager.getUserpageConditionRegister();
	});
	
	
	$("#queryForProxy").click(function(){
		var area = $('#proxyArea').val();
	 	var d = co.datapersons.buildRequestParam({ action: "User.QueryProxy",body:{area:area}});
		var params = $.toJSON(d);
		var queryParams = $('#userTable').datagrid('options').queryParams;  
        queryParams.request = params;
        
        $('#proxyTable').datagrid({queryParams: { request: params }});
        
	 	$('#proxyTable').datagrid("reload");
	 	
	});
	
	
	/*Disturb now*/
	$('#verifyCode').click(function(){
		co.datapersons.manager.requestSMS();
	});
	
	
	
	$('#register').click(function(){		 
		 if(usertype == 'user'){
			 co.datapersons.manager.submitPost("user");
		 }else if (usertype == 'bussiness'){
			 co.datapersons.manager.submitPost("shop");
		 }else{
			 art.dialog({
			    content: "没有用户类型，请重新由首页选择用户入口！",			
				cancelVal: 'Close',
				cancel: true 
			 });
		 }		
	});
	
	
	$('#loginBtn').click(function(){		
		if(usertype =="bussiness" ){
			co.datapersons.manager.login("shop");
		}
		if(usertype =="user"){
			co.datapersons.manager.login("user");
		}
	});
	
	$('#updateUserBtn').click(function(){
		co.datapersons.manager.updateUser();
	});
	
//	if(urlName.indexOf("register")< 0  && urlName.indexOf("index")<0 && urlName.indexOf("land")< 0){
//		co.datapersons.manager.pageLoad();	
//	}
	
	$('#payBtn').click(function(){
		co.datapersons.manager.pay();
	});
	
	if(urlName == "shoppay"){
		co.datapersons.manager.loadShopPage();
	}
	
	$('#logout').click(function(){		
		co.datapersons.manager.logout();
	});
	
    
    
    
});