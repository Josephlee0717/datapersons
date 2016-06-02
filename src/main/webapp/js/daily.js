$(document).ready(function(){
	//弹出垂直菜单
   $(".endMenu .menu").click(function() {
    if ($(this).hasClass("cura")) {
        $(this).children(".endMenu .option").hide(); //当前菜单下的二级菜单隐藏
        $(".endMenu .menu").removeClass("cura"); //同一级的菜单项
    } else {
        $(".endMenu .menu").removeClass("cura"); //移除所有的样式
        $(this).addClass("cura"); //给当前菜单添加特定样式
        $(".endMenu .menu").children(".endMenu .option").hide(); //隐藏所有的二级菜单
        $(this).children(".endMenu .option").show(); //展示当前的二级菜单
    }
   });
   //弹出垂直菜单end
   //底部右侧弹出隐藏
   var flag3 = 1;
   $(".endMenu .button,.blankButton").click(function() {
    if (flag3 == 1) {
        $(".endMenu .nav,.blankButton").show();
        $(".endMenu .button").addClass('buttonWeight');
        flag3 = 0;
    } else {
        $(".blankButton,.endMenu .option,.endMenu .nav,.endMenu .buttonLight").hide();
        $(".endMenu .menu").removeClass('cura');
        $(".endMenu .button").removeClass('.endMenu buttonWeight');
        flag3 = 1;
    }
   })
 
 


   
})