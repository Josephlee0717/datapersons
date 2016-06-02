//judg input password
function judgInputText(idone){
  var textone = $(idone).val();
  if(textone.length==0){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 密码不得为空</span></p>";}
  if(textone.length<6){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 密码长度小于6位</span></p>";}
  if(textone.length>12){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 密码长度大于12位</span></p>";}
  else{return true}
}

//judg password and again text is same
function judgSame(idone,idtwo){
  var textone = $(idone).val();
  var texttwo = $(idtwo).val();  
  if(textone.length!=0 && textone == texttwo){return true}
  else if(texttwo.length!=0){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 密码不一致</span></p>";}	
}

//judg city and area is select
function judgAera(cityId,areaId){
  var city = $(cityId);
  var area = $(areaId);
  //if(city.children().length>1 )
  if(city.val()==null || city.val() == 0){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 请选择城市</span></p>";}
  if(area.children().length>1 && area.val()==0){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 请选择区域</span></p>"}
  else{return true}
}

//judg val is null
function judgValNull(cityId,promptText){
  if($(cityId).val().length>0){return true}
  else{return "<p><span class=\"rowLeft\"></span><span class=\"red\">* "+promptText+"</span></p>";}
}

//Input infor
function judgInputInfor(returnInfor,elementId,times){
  if(returnInfor==true){times+=1;$(elementId).html("")}else{$(elementId).html(returnInfor)}  
  return times;
}

//judg phoneNumber
function judgPhoneRight(phoneId){
	if($(phoneId).val().length==11){return true}else{return false}
}
function judgPhoneNumber(phoneId){
	if($(phoneId).val().length!=11){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 请正确输入手机号</span></p>"}
	else{return true}
}

//judg PhoneCard
function judgPhoneCard(cardId){
	if($(cardId).val().length!=4){return "<p><span class=\"rowLeft\"></span><span class=\"red\">* 请输入4位验证码</span></p>"}
	else{return true}
}
