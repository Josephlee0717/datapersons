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
	userid :"",	
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
	    			var userid = result.result.u;
	    			var session = result.result.s;
	    			var usertype = result.result.t;
	    			if (co.datapersons.manager.curPageName == "uperfect"){
	    				co.datapersons.manager.userid = userid;
	    				co.datapersons.manager.getUserInfor();	
	    			}
	    			
	    			if(id == "" || userid== "" ||session =="" || usertype == ""){
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
	    				if(userType =="个人"){
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
	
	registerVerify : function(){
		var mobileNum = $("#mobileNum").val();
		var verifyText = $("#verifyText").val();
		var password = $("#password").val();
		var pwFirm = $("#pwFirm").val();
		
		if(co.datapersons.manager.verifyGraph != true){
			art.dialog({
			    content: '图形验证码不正确，请再次确认！',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(mobileNum == ""){
			art.dialog({
			    content: '请输入手机号。',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		if(verifyText == ""){
			art.dialog({
			    content: '请输入验证码。',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(password == ""){
			art.dialog({
			    content: '请输入密码。',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(pwFirm == ""){
			art.dialog({
			    content: '请输入确认密码。',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(pwFirm != password){
			art.dialog({
			    content: '确认密码与密码不符。',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(co.datapersons.manager.userid != mobileNum){
			art.dialog({
			    content: '输入手机号与接受短信手机不符，请再次确认！',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}
		
		if(co.datapersons.manager.verifyCode != verifyText){
			art.dialog({
			    content: '输入验证码不正确，请再次确认！',			
				cancelVal: 'Close',
				cancel: true 
			});	
			return "9999"; 
		}	
		
		return "0000";
	},
	
	login :function(){
		var mobileNum = $("#mobileNum").val();		
		var password = $("#password").val();
		co.request({
			action:"User.login",
			body:{userid:mobileNum,password:password,usertype:"个人"},
			success:function(data){	    			    						
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				window.location.href = "uperfect.html"; 
	    			}
	    		}
			}
		});
	},
	
	submitPost : function(userType){
		var verifyCode = co.datapersons.manager.registerVerify();
		var password = $("#password").val();
		if(verifyCode == "0000"){
			co.request({
				action:"User.checkuserid",
				body:{userid:co.datapersons.manager.userid},
				success:function(data){
					console.log(data);
					var result = data;
		    		if(result != undefined || result != null){
		    			if(result.status == "0000"){
		    				co.request({
		    					action:"User.register",
		    					body:{userid:co.datapersons.manager.userid,password:password,usertype:userType},
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
		var username = $("#username").val();
		var idCard = $("#idCard").val();
		var ICBCCard = $("#ICBCCard").val();
		var WXNum = $("#WXNum").val();
		var ZFBNum = $("#ZFBNum").val();
		
		co.request({
			action:"User.updateUserInfo",
			body:{FullName:username,idCard:idCard,ICBCCard:ICBCCard,WXNum:WXNum,ZFBNum:ZFBNum},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){	    				
	    				art.dialog({
	    				    content: '用户信息已保存！',	
	    				    ok: function () {
	    				    	window.location.href = "uperfectend.html"; 
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
			body:{userid:co.datapersons.manager.userid},
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
	
	refreshUserCenter: function(){
		var d = co.datapersons.buildRequestParam({ action: "User.getSuper"});
		var params = $.toJSON(d);
		
		$("#getUserCountByRegisterTime").text();
		$("#registerUserTab").datagrid({
	    	url: co.datapersons.url,
	    	method: 'POST',
	    	fitColumns: false,	    	
	    	nowrap: false,	    	  
	        height: 400, 
	    	columns: [[{  
	            field: 'userid',  
	            title: 'UID',  
	            width:130,
	            align: 'center'  
	        },  
	        {  
	            field: 'registertime',  
	            title: '注册时间',  
	            width:170,  
	            align: 'center'  
	        },  
	        {  
	            field: 'fullname',  
	            title: '名字',
	            width:90,
	            align: 'center'  
	        },  
	        {  
	            field: 'userid',  
	            title: '手机号码',
	            width:200,
	            align: 'center'	
	        },  
	        {  
	            field: 'idcard',  
	            title: '身份证号',
	            width:270,
	            align: 'center'
	        },  
	        {  
	            field: 'consumeamount',  
	            title: '消费总额',
	            width:100,
	            align: 'center'
	        }
//	        ,  
//	        {  
//	            field: '',  
//	            title: '推荐人数'
//	        },  
//	        {  
//	            field: '',  
//	            title: '资料详细'
//	        },  
//	        {  
//	            field: '',  
//	            title: '消费详细'
//	        },  
//	        {  
//	            field: '',  
//	            title: '收入详细'
//	        }
	        ]],
	        queryParams: { request: params },
	        onLoadSuccess: function (data) {
	            
	        }
	    });
		co.request({
			action:"User.getRecordCount",
			body:{usertype:"个人"},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#getUserCount").text(result.result.recordCount+"人");	    					
	    			}
	    		}
				console.log(data);
			}
		});
		var now=new Date();
//		var today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		var today =  $("#sdt_superUserPanel").val();
		if(today == ""){
			today = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		}
		var betweenTime = today+" 00:00:00";
		var endTime = today+" 23:59:59";
		co.request({
			action:"User.getRegisterCountByDay",
			body:{between:betweenTime,end:endTime },
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#getUserCountByRegisterTime").text(result.result.UserCount+"人");	    					
	    			}
	    		}
				console.log(data);
			}
		});
		
		
		co.request({
			action:"User.getSumConsumeByDay",
			body:{between:betweenTime,end: endTime},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#getComsumeSumByDate").text(result.result.SumConsume+"元");	    					
	    			}
	    		}
				console.log(data);
			}
		});
		co.request({
			action:"User.getSumConsume",
			body:{},
			success:function(data){
				console.log(data);
				var result = data;
	    		if(result != undefined || result != null){
	    			if(result.status == "0000"){
	    				$("#getComsumeSum").text(result.result.SumConsume+"元");	    					
	    			}
	    		}
				console.log(data);
			}
		});
		
	}
}

$(function(){
	$('#verifyCode').click(function(){
		co.datapersons.manager.requestSMS();
	});
	
	$('#userregister').click(function(){
		co.datapersons.manager.submitPost("个人");
	});
	
	$('#businessregister').click(function(){
		co.datapersons.manager.submitPost("商家");
	});
	
	$('#loginBtn').click(function(){
		co.datapersons.manager.login();
	});
	
	$('#updateUserBtn').click(function(){
		co.datapersons.manager.updateUser();
	});
	
	var urlName = window.location.href.slice(window.location.href.lastIndexOf('/')+1,window.location.href.length - 5);
	co.datapersons.manager.curPageName = urlName;
	if( urlName== "bregister"){		
		co.datapersons.manager.getRecordCount("商家");		
	}
	
	if(urlName == "uregister"){		
		var count = co.datapersons.manager.getRecordCount("个人");		
	}
	if(urlName != "bregister" && urlName != "uregister" && urlName != "index" && urlName != "uland" && urlName != "bland"){
		co.datapersons.manager.pageLoad();	
	}
	var today=new Date();
    $("#curDateTime").text(today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate());
    $("#sdt_superUserPanel").val(today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate());
    
//    $("#sdt_superUserPanel").onchange(function(){
//    	co.datapersons.manager.refreshUserCenter();
//    });
    $( "#sdt_superUserPanel" ).datepicker();
    $( "#sdt_superUserPanel" ).datepicker({
    	onSelect: function() {    		
    		co.datapersons.manager.refreshUserCenter();
    	}
    });
    $( "#sdt_superUserPanel" ).datepicker('option', {dateFormat: 'yy-mm-dd'});
   
    
});