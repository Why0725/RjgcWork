$(function(){
    //菜单点击
   
    $(".menuItem").on('click',function(){
    	//获取点击a链接的href
        var url = $(this).attr('href');
        //去掉其他点的ative类
        //------------a-----li-------ul---------li(s)--------去掉active类
        var lis =  $(this).parent().parent().find("li").removeClass("active");
        //给点击点的父元素li添加active类
        $(this).parent().addClass("active");
        //修改ifarme的src
        $("#iframe-main").attr('src',url);
        return false;
    });
});