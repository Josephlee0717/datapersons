$(document).ready(function(){
  
  var InterValObj; //timer变量，控制时间
  var count = 60; //间隔函数，1秒执行
  var curCount;//当前剩余秒数
  function SetRemainTime() {
    if (curCount == 0) {                
      window.clearInterval(InterValObj);//停止计时器
      $("#verifyCode").removeAttr("disabled");//启用按钮
	  $("#verifyCode").html("获取验证码");    
	  $("#verifyCode").css({"color":"#333","text-shadow":"1px 1px 1px #f3f3f3"});			
    }else {
	  curCount--;
	  $("#verifyCode").html( + curCount + "秒再获取");
    }
  }	
  $("#verifyCode").click(function(){ 
    judgInputInfor(judgPhoneNumber("#phoneNumber"),"#promptNumber")
    if(judgPhoneRight("#phoneNumber")!=true){return;}
	curCount = count;
	$("#verifyCode").attr("disableds", "true");
	$("#verifyCode").html( + curCount + "秒再获取");
	$("#verifyCode").css({"color":"#a0a0a0","text-shadow":"1px 1px 1px #fff"});
	InterValObj = window.setInterval(SetRemainTime, 1000); 
  })

  $("#enter").click(function(){

	  var times=0;
	      times =judgInputInfor(judgPhoneNumber("#phoneNumber"),"#promptNumber",times)
		  times =judgInputInfor(judgPhoneCard("#verificationCard"),"#promptCard",times)	
	  //var times=0;
	     // times =judgInputInfor(judgValNull("#historyPossword","请输入原始密码"),"#promptHis",times)
		 // times =judgInputInfor(judgInputText("#possword"),"#promptPwd",times)
		 // times =judgInputInfor(judgSame("#possword","#again"),"#promptAgain",times)		  
	  //Click Pass
	  if(times==2){alert("传输数据")}
	  //changePwd.html
  })

})
   
 