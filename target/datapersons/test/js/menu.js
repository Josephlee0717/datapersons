$(document).ready(function(){
	
	function unfoldMenu(pn, cn){
		var p = $('strong.menu-s'), c = $('div.menu-d'), cc = $('div.menu-d-current');
		if(c.index(cn) != c.index(cc)){
			p.removeClass('menu-s-current');
			cc.hide(200, function(){
				$(this).removeAttr('style').removeClass('menu-d-current');
			})
			pn.addClass('menu-s-current');
			cn.show(200, function(){
				$(this).removeAttr('style').addClass('menu-d-current');
			});
		}
	}
	
	function menuHandle(){
		$('strong.menu-s').click(function(){
			var pn = $(this), cn = pn.next();
			unfoldMenu(pn, cn);
		});
	}
	
	//设置默认下当前展开
	function menuCurrent(){
		var idx = $('input.menu-code-index').val(), m, pn, cn, p = $('strong.menu-s'), c = $('div.menu-d'), cc = $('div.menu-d-current');
		if(/c(\d)+/.test(idx)){ //判断c（十进制）条件
			m = $('a[data-service-index="' + idx + '"]').addClass('current');
			cn = m.parents('div.menu-d');
			pn = cn.prev();
			unfoldMenu(pn, cn);
		}
	}
	
	menuCurrent();
	menuHandle();
	
	
  	$("#nav_i li").click(
	 function(){ 
	 	var id ="#"+$(this).parent().parent().attr("id")+" li";
		$("#nav_i li").siblings().removeClass("css_color");
		$(id).eq($(this).index()).addClass("css_color"); 
	 })
	
	$("#button_load").click(
	function(){ 
		$('#loadheml').load("after-index.html");
	 })
});