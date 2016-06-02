$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;
	      times =judgInputInfor(judgAera("#userSeachcity","#userSeachdistrict"),"#promptArea",times)
	  //Click Pass
	  if(times==1){alert("申请递交成功")}
  })

})
   
 