$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;		  
	      times =judgInputInfor(judgValNull("#userName","必填"),"#promptName",times)
	      times =judgInputInfor(judgValNull("#identityCard","必填"),"#promptCard",times)		
	      times =judgInputInfor(judgAera("#userSeachcity","#userSeachdistrict"),"#promptArea",times)
		  times =judgInputInfor(judgValNull("#zhifubao","必填"),"#promptZhifubao",times)				  
	      times =judgInputInfor(judgValNull("#bankCardICBC","必填"),"#promptICBC",times)
	      times =judgInputInfor(judgValNull("#mail","必填"),"#promptEmail",times)			  	  		  
	  //Click Pass
	  if(times==6){alert("信息修改成功")}
	  //window.location.href("navAgent.html")
  })

})
  