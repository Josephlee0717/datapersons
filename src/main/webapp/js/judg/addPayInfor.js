$(document).ready(function(){
 
  $("#enter").click(function(){
	  var times=0;
	      times =judgInputInfor(judgValNull("#userName","必填"),"#promptUserId",times)
	      times =judgInputInfor(judgValNull("#identityCard","必填"),"#promptPayNumber",times)
	  //Click Pass
	  if(times==2){alert("支付宝交易页面")}
	  //还需要选择商店
	  //或者先选择商店进行添加
	  
	  
  })

})
   
 