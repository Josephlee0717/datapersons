$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;
		  times =judgInputInfor(judgInputText("#possword"),"#promptPwd",times)
		  times =judgInputInfor(judgSame("#possword","#again"),"#promptAgain",times)		  
	  //Click Pass
	  if(times==2){alert("传输数据")}
  })

})
   
 