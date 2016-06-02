 $(function (){
	$("input.mh_date").manhuaDate({					       	Event : "click",//可选				       
		Left : 0,//弹出时间停靠的左边位置
		Top : -16,//弹出时间停靠的顶部边位置
		fuhao : "-",//日期连接符默认为-
		isTime : false,//是否开启时间值默认为false
		beginY : 2016,//年份的开始默认为1949
		endY :2060//年份的结束默认为2049
	});
 });