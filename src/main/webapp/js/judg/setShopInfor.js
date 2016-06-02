$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;
	  	  times =judgInputInfor(judgValNull("#sign","必填"),"#promptSign",times)
	      times =judgInputInfor(judgValNull("#shopName","必填"),"#promptShopName",times)
	      times =judgInputInfor(judgValNull("#shopAddress","必填"),"#promptAddress",times)		  
	      times =judgInputInfor(judgAera("#userSeachcity","#userSeachdistrict"),"#promptArea",times)
	      times =judgInputInfor(judgValNull("#organizeNumber","必填"),"#promptNumber",times)
	      times =judgInputInfor(judgValNull("#incorporator","必填"),"#promptImport",times)	
	      times =judgInputInfor(judgValNull("#incorporatorNumber","必填"),"#promptPhone",times)
	  //Click Pass
	  if(times==7){alert("商店修改成功")}
	  //window.location.href("navAgent.html")
  })

})
   
 