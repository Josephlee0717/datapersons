$(document).ready(function(){
 	
	var InterValObj; //timer变量，控制时间
 	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数
	function SetRemainTime() {
		if (curCount == 0) {                
			window.clearInterval(InterValObj);//停止计时器
			$("#verifyCode").removeAttr("disabled");//启用按钮
			$("#verifyCode").html("获取验证码");    
			$("#verifyCode").css({
				"color":"#333",
				"text-shadow":"1px 1px 1px #f3f3f3"});	
		}else {
			curCount--;
			$("#verifyCode").html( + curCount + "秒再获取");
		}
	}	
	function SetRemainTime2() {
		if (curCount == 0) {                
			window.clearInterval(InterValObj);//停止计时器
			$("#getVerifyCode").removeAttr("disabled");//启用按钮
			$("#getVerifyCode").html("获取验证码");    
			$("#getVerifyCode").css({
				"color":"#333",
				"text-shadow":"1px 1px 1px #f3f3f3"});	
		}else {
			curCount--;
			$("#getVerifyCode").html( + curCount + "秒再获取");
		}
	}	
	
	$("#verifyCode").click(
	 function(){ 
		if(curCount>0){return;}
		curCount = count;
		$("#verifyCode").attr("disabled", "true");
		$("#verifyCode").html( + curCount + "秒再获取");
		$("#verifyCode").css({"color":"#a0a0a0","text-shadow":"1px 1px 1px #fff"});
		InterValObj = window.setInterval(SetRemainTime, 1000); 
	 })
	 
	 $("#getVerifyCode").click(
	 function(){ 
		if(curCount>0){return;}
		curCount = count;
		$("#getVerifyCode").attr("disabled", "true");
		$("#getVerifyCode").html( + curCount + "秒再获取");
		$("#getVerifyCode").css({"color":"#a0a0a0","text-shadow":"1px 1px 1px #fff"});
		InterValObj = window.setInterval(SetRemainTime2, 1000); 
	 })

 	

})