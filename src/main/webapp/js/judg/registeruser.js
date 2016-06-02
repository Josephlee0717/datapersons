$(document).ready(function(){
 
  var InterValObj; 
  var count = 60; 
  var curCount;
  function SetRemainTime() {
    if (curCount == 0) {                
      window.clearInterval(InterValObj);
      $("#verifyCode").removeAttr("disabled");
	  $("#verifyCode").html("获取验证码");    
	  $("#verifyCode").css({"color":"#333","text-shadow":"1px 1px 1px #f3f3f3"});			
    }else {
	  curCount--;
	  $("#verifyCode").html( + curCount + "秒再获取");
    }
  }	
  $("#verifyCode").click(function(){
	var infor = judgValNull("#phoneNumber","手机号码不得为空");
	if(infor!=true){judgInputInfor(infor,"#promptNumber");return;}
    judgInputInfor(judgPhoneNumber("#phoneNumber"),"#promptNumber")
    if(judgPhoneRight("#phoneNumber")!=true){return;}
	curCount = count;
	$("#verifyCode").attr("disableds", "true");
	$("#verifyCode").html( + curCount + "秒再获取");
	$("#verifyCode").css({"color":"#a0a0a0","text-shadow":"1px 1px 1px #fff"});
	InterValObj = window.setInterval(SetRemainTime, 1000); 
  })

  //register button	
  $("#register").click(function(){
	  var times=0;
	      times =judgInputInfor(judgPhoneNumber("#phoneNumber"),"#promptNumber",times)
		  times =judgInputInfor(judgPhoneCard("#verificationCard"),"#verificationPrompt",times)	
		  times =judgInputInfor(judgInputText("#password"),"#promptPwdNumber",times)	
		  times =judgInputInfor(judgSame("#password","#confirm"),"#promptPwdContrast",times)			  		  
	  //var times=0;
	     // times =judgInputInfor(judgValNull("#historyPossword","请输入原始密码"),"#promptHis",times)
		 // times =judgInputInfor(judgInputText("#possword"),"#promptPwd",times)
		 // times =judgInputInfor(judgSame("#possword","#again"),"#promptAgain",times)		  
	  //Click Pass
	  if(times==4){alert("传输数据")}
	  //changePwd.html
  })



})
   
 