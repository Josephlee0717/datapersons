$(document).ready(function(){
 
  $("#land").click(function(){
	  var times=0;
	      times =judgInputInfor(judgValNull("#phoneNumber","用户名不得为空"),"#promptUser",times)
	      times =judgInputInfor(judgValNull("#password","密码不得为空"),"#promptPassword",times)
	  //Click Pass
	  if(times==2){alert("可以登录")}
	  //navInfor.html
  })

})
   
 