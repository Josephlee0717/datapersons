$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;
	  	  times =judgInputInfor(judgValNull("#sign","必填"),"#promptSign",times)
	      times =judgInputInfor(judgValNull("#shopName","必填"),"#promptShopName",times)
	      times =judgInputInfor(judgValNull("#shopAddress","必填"),"#promptAddress",times)		  
	      times =judgInputInfor(judgAera("#shopSeachcity","#shopSeachdistrict"),"#promptShopAddress",times)
	      times =judgInputInfor(judgValNull("#organizeNumber","必填"),"#promptOrgNumber",times)
	      times =judgInputInfor(judgValNull("#incorporator","必填"),"#promptImport",times)	
	      times =judgInputInfor(judgValNull("#incorporatorNumber","必填"),"#promptImportNumber",times)
	      times =judgInputInfor(judgValNull("#userName","必填"),"#promptUserName",times)
	      times =judgInputInfor(judgValNull("#identityCard","必填"),"#promptIdentityCard",times)	  
	      times =judgInputInfor(judgAera("#userSeachcity","#userSeachdistrict"),"#promptUserAddress",times)	
		  times =judgInputInfor(judgValNull("#zhifubao","必填"),"#promptZhifubao",times)	  
		  times =judgInputInfor(judgValNull("#bankCardICBC","必填"),"#promptICBC",times)	  
		  times =judgInputInfor(judgValNull("#mail","必填"),"#promptEmail",times)	  
	  //Click Pass
	  if(times==13){alert("商店修改成功")}
	  //window.location.href("navAgent.html")
  })


})
   
 