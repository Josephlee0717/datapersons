/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.25
 * Generated at: 2016-04-07 02:19:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


/* *
 *功能：支付宝即时到账交易接口调试入口页面
 *版本：3.4
 *日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 **********************************************
 */

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>支付宝即时到账交易接口</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    html,body {\r\n");
      out.write("        width:100%;\r\n");
      out.write("        min-width:1200px;\r\n");
      out.write("        height:auto;\r\n");
      out.write("        padding:0;\r\n");
      out.write("        margin:0;\r\n");
      out.write("        font-family:\"微软雅黑\";\r\n");
      out.write("        background-color:#242736\r\n");
      out.write("    }\r\n");
      out.write("    .header {\r\n");
      out.write("        width:100%;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        height:230px;\r\n");
      out.write("        background-color:#fff\r\n");
      out.write("    }\r\n");
      out.write("    .container {\r\n");
      out.write("        width:100%;\r\n");
      out.write("        min-width:100px;\r\n");
      out.write("        height:auto\r\n");
      out.write("    }\r\n");
      out.write("    .black {\r\n");
      out.write("        background-color:#242736\r\n");
      out.write("    }\r\n");
      out.write("    .blue {\r\n");
      out.write("        background-color:#0ae\r\n");
      out.write("    }\r\n");
      out.write("    .qrcode {\r\n");
      out.write("        width:1200px;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        height:30px;\r\n");
      out.write("        background-color:#242736\r\n");
      out.write("    }\r\n");
      out.write("    .littlecode {\r\n");
      out.write("        width:16px;\r\n");
      out.write("        height:16px;\r\n");
      out.write("        margin-top:6px;\r\n");
      out.write("        cursor:pointer;\r\n");
      out.write("        float:right\r\n");
      out.write("    }\r\n");
      out.write("    .showqrs {\r\n");
      out.write("        top:30px;\r\n");
      out.write("        position:absolute;\r\n");
      out.write("        width:100px;\r\n");
      out.write("        margin-left:-65px;\r\n");
      out.write("        height:160px;\r\n");
      out.write("        display:none\r\n");
      out.write("    }\r\n");
      out.write("    .shtoparrow {\r\n");
      out.write("        width:0;\r\n");
      out.write("        height:0;\r\n");
      out.write("        margin-left:65px;\r\n");
      out.write("        border-left:8px solid transparent;\r\n");
      out.write("        border-right:8px solid transparent;\r\n");
      out.write("        border-bottom:8px solid #e7e8eb;\r\n");
      out.write("        margin-bottom:0;\r\n");
      out.write("        font-size:0;\r\n");
      out.write("        line-height:0\r\n");
      out.write("    }\r\n");
      out.write("    .guanzhuqr {\r\n");
      out.write("        text-align:center;\r\n");
      out.write("        background-color:#e7e8eb;\r\n");
      out.write("        border:1px solid #e7e8eb\r\n");
      out.write("    }\r\n");
      out.write("    .guanzhuqr img {\r\n");
      out.write("        margin-top:10px;\r\n");
      out.write("        width:80px\r\n");
      out.write("    }\r\n");
      out.write("    .shmsg {\r\n");
      out.write("        margin-left:10px;\r\n");
      out.write("        width:80px;\r\n");
      out.write("        height:16px;\r\n");
      out.write("        line-height:16px;\r\n");
      out.write("        font-size:12px;\r\n");
      out.write("        color:#242323;\r\n");
      out.write("        text-align:center\r\n");
      out.write("    }\r\n");
      out.write("    .nav {\r\n");
      out.write("        width:1200px;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        height:70px;\r\n");
      out.write("    }\r\n");
      out.write("    .open,.logo {\r\n");
      out.write("        display:block;\r\n");
      out.write("        float:left;\r\n");
      out.write("        height:40px;\r\n");
      out.write("        width:85px;\r\n");
      out.write("        margin-top:20px\r\n");
      out.write("    }\r\n");
      out.write("    .divier {\r\n");
      out.write("        display:block;\r\n");
      out.write("        float:left;\r\n");
      out.write("        margin-left:20px;\r\n");
      out.write("        margin-right:20px;\r\n");
      out.write("        margin-top:23px;\r\n");
      out.write("        width:1px;\r\n");
      out.write("        height:24px;\r\n");
      out.write("        background-color:#d3d3d3\r\n");
      out.write("    }\r\n");
      out.write("    .open {\r\n");
      out.write("        line-height:30px;\r\n");
      out.write("        font-size:20px;\r\n");
      out.write("        text-decoration:none;\r\n");
      out.write("        color:#1a1a1a\r\n");
      out.write("    }\r\n");
      out.write("    .navbar {\r\n");
      out.write("        float:right;\r\n");
      out.write("        width:200px;\r\n");
      out.write("        height:40px;\r\n");
      out.write("        margin-top:15px;\r\n");
      out.write("        list-style:none\r\n");
      out.write("    }\r\n");
      out.write("    .navbar li {\r\n");
      out.write("        float:left;\r\n");
      out.write("        width:100px;\r\n");
      out.write("        height:40px\r\n");
      out.write("    }\r\n");
      out.write("    .navbar li a {\r\n");
      out.write("        display:inline-block;\r\n");
      out.write("        width:100px;\r\n");
      out.write("        height:40px;\r\n");
      out.write("        line-height:40px;\r\n");
      out.write("        font-size:16px;\r\n");
      out.write("        color:#1a1a1a;\r\n");
      out.write("        text-decoration:none;\r\n");
      out.write("        text-align:center\r\n");
      out.write("    }\r\n");
      out.write("    .navbar li a:hover {\r\n");
      out.write("        color:#00AAEE\r\n");
      out.write("    }\r\n");
      out.write("    .title {\r\n");
      out.write("        width:1200px;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        height:80px;\r\n");
      out.write("        line-height:80px;\r\n");
      out.write("        font-size:20px;\r\n");
      out.write("        color:#FFF\r\n");
      out.write("    }\r\n");
      out.write("    .content {\r\n");
      out.write("        width:100%;\r\n");
      out.write("        min-width:1200px;\r\n");
      out.write("        height:660px;\r\n");
      out.write("        background-color:#fff;      \r\n");
      out.write("    }\r\n");
      out.write("    .alipayform {\r\n");
      out.write("        width:800px;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        height:600px;\r\n");
      out.write("        border:1px solid #0ae\r\n");
      out.write("    }\r\n");
      out.write("    .element {\r\n");
      out.write("        width:600px;\r\n");
      out.write("        height:80px;\r\n");
      out.write("        margin-left:100px;\r\n");
      out.write("        font-size:20px\r\n");
      out.write("    }\r\n");
      out.write("    .etitle,.einput {\r\n");
      out.write("        float:left;\r\n");
      out.write("        height:26px\r\n");
      out.write("    }\r\n");
      out.write("    .etitle {\r\n");
      out.write("        width:150px;\r\n");
      out.write("        line-height:26px;\r\n");
      out.write("        text-align:right\r\n");
      out.write("    }\r\n");
      out.write("    .einput {\r\n");
      out.write("        width:200px;\r\n");
      out.write("        margin-left:20px\r\n");
      out.write("    }\r\n");
      out.write("    .einput input {\r\n");
      out.write("        width:398px;\r\n");
      out.write("        height:24px;\r\n");
      out.write("        border:1px solid #0ae;\r\n");
      out.write("        font-size:16px\r\n");
      out.write("    }\r\n");
      out.write("    .mark {\r\n");
      out.write("        margin-top: 10px;\r\n");
      out.write("        width:500px;\r\n");
      out.write("        height:30px;\r\n");
      out.write("        margin-left:80px;\r\n");
      out.write("        line-height:30px;\r\n");
      out.write("        font-size:12px;\r\n");
      out.write("        color:#999\r\n");
      out.write("    }\r\n");
      out.write("    .legend {\r\n");
      out.write("        margin-left:100px;\r\n");
      out.write("        font-size:24px\r\n");
      out.write("    }\r\n");
      out.write("    .alisubmit {\r\n");
      out.write("        width:400px;\r\n");
      out.write("        height:40px;\r\n");
      out.write("        border:0;\r\n");
      out.write("        background-color:#0ae;\r\n");
      out.write("        font-size:16px;\r\n");
      out.write("        color:#FFF;\r\n");
      out.write("        cursor:pointer;\r\n");
      out.write("        margin-left:170px\r\n");
      out.write("    }\r\n");
      out.write("    .footer {\r\n");
      out.write("        width:100%;\r\n");
      out.write("        height:120px;\r\n");
      out.write("        background-color:#242735\r\n");
      out.write("    }\r\n");
      out.write("    .footer-sub a,span {\r\n");
      out.write("        color:#808080;\r\n");
      out.write("        font-size:12px;\r\n");
      out.write("        text-decoration:none\r\n");
      out.write("    }\r\n");
      out.write("    .footer-sub a:hover {\r\n");
      out.write("        color:#00aeee\r\n");
      out.write("    }\r\n");
      out.write("    .footer-sub span {\r\n");
      out.write("        margin:0 3px\r\n");
      out.write("    }\r\n");
      out.write("    .footer-sub {\r\n");
      out.write("        padding-top:40px;\r\n");
      out.write("        height:20px;\r\n");
      out.write("        width:600px;\r\n");
      out.write("        margin:0 auto;\r\n");
      out.write("        text-align:center\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"header\">\r\n");
      out.write("        <div class=\"container black\">\r\n");
      out.write("            <div class=\"qrcode\">\r\n");
      out.write("                <div class=\"littlecode\">\r\n");
      out.write("                    <img width=\"16px\" src=\"img/little_qrcode.jpg\" id=\"licode\">\r\n");
      out.write("                    <div class=\"showqrs\" id=\"showqrs\">\r\n");
      out.write("                        <div class=\"shtoparrow\"></div>\r\n");
      out.write("                        <div class=\"guanzhuqr\">\r\n");
      out.write("                            <img src=\"img/guanzhu_qrcode.png\" width=\"80\">\r\n");
      out.write("                            <div class=\"shmsg\" style=\"margin-top:5px;\">\r\n");
      out.write("                            请扫码关注\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"shmsg\" style=\"margin-bottom:5px;\">\r\n");
      out.write("                                接收重要信息\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>      \r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"nav\">\r\n");
      out.write("                <a href=\"https://www.alipay.com/\" class=\"logo\"><img src=\"img/alipay_logo.png\" height=\"30px\"></a>\r\n");
      out.write("                <span class=\"divier\"></span>\r\n");
      out.write("                <a href=\"http://open.alipay.com/platform/home.htm\" class=\"open\" target=\"_blank\">开放平台</a>\r\n");
      out.write("                <ul class=\"navbar\">\r\n");
      out.write("                    <li><a href=\"https://doc.open.alipay.com/doc2/detail?treeId=62&articleId=103566&docType=1\" target=\"_blank\">在线文档</a></li>\r\n");
      out.write("                    <li><a href=\"https://cschannel.alipay.com/portal.htm?sourceId=213\" target=\"_blank\">技术支持</a></li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"container blue\">\r\n");
      out.write("            <div class=\"title\">支付宝即时到账(create_direct_pay_by_user)</div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"content\">\r\n");
      out.write("        <form action=\"alipayapi.jsp\" class=\"alipayform\" method=\"POST\" target=\"_blank\">\r\n");
      out.write("            <div class=\"element\" style=\"margin-top:60px;\">\r\n");
      out.write("                <div class=\"legend\">支付宝即时到账交易接口快速通道 </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"element\">\r\n");
      out.write("                <div class=\"etitle\">商户订单号:</div>\r\n");
      out.write("                <div class=\"einput\"><input type=\"text\" name=\"WIDout_trade_no\" id=\"out_trade_no\"></div>\r\n");
      out.write("                <br>\r\n");
      out.write("                <div class=\"mark\">注意：商户订单号(out_trade_no).必填(建议是英文字母和数字,不能含有特殊字符)</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"element\">\r\n");
      out.write("                <div class=\"etitle\">商品名称:</div>\r\n");
      out.write("                <div class=\"einput\"><input type=\"text\" name=\"WIDsubject\" value=\"test商品123\"></div>\r\n");
      out.write("                <br>\r\n");
      out.write("                <div class=\"mark\">注意：产品名称(subject)，必填(建议中文，英文，数字，不能含有特殊字符)</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"element\">\r\n");
      out.write("                <div class=\"etitle\">付款金额:</div>\r\n");
      out.write("                <div class=\"einput\"><input type=\"text\" name=\"WIDtotal_fee\" value=\"0.01\"></div>\r\n");
      out.write("                <br>\r\n");
      out.write("                <div class=\"mark\">注意：付款金额(total_fee)，必填(格式如：1.00,请精确到分)</div>\r\n");
      out.write("            </div>\r\n");
      out.write("\t\t\t<div class=\"element\">\r\n");
      out.write("                <div class=\"etitle\">商品描述:</div>\r\n");
      out.write("                <div class=\"einput\"><input type=\"text\" name=\"WIDbody\" value=\"即时到账测试\"></div>\r\n");
      out.write("                <br>\r\n");
      out.write("                <div class=\"mark\">注意：商品描述(body)，选填(建议中文，英文，数字，不能含有特殊字符)</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"element\">\r\n");
      out.write("                <input type=\"submit\" class=\"alisubmit\" value =\"确认支付\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"footer\">\r\n");
      out.write("        <p class=\"footer-sub\">\r\n");
      out.write("            <a href=\"http://ab.alipay.com/i/index.htm\" target=\"_blank\">关于支付宝</a><span>|</span>\r\n");
      out.write("            <a href=\"https://e.alipay.com/index.htm\" target=\"_blank\">商家中心</a><span>|</span>\r\n");
      out.write("            <a href=\"https://job.alibaba.com/zhaopin/index.htm\" target=\"_blank\">诚征英才</a><span>|</span>\r\n");
      out.write("            <a href=\"http://ab.alipay.com/i/lianxi.htm\" target=\"_blank\">联系我们</a><span>|</span>\r\n");
      out.write("            <a href=\"#\" id=\"international\" target=\"_blank\">International&nbsp;Business</a><span>|</span>\r\n");
      out.write("            <a href=\"http://ab.alipay.com/i/jieshao.htm#en\" target=\"_blank\">About Alipay</a>\r\n");
      out.write("            <br>\r\n");
      out.write("             <span>支付宝版权所有</span>\r\n");
      out.write("            <span class=\"footer-date\">2004-2016</span>\r\n");
      out.write("            <span><a href=\"http://fun.alipay.com/certificate/jyxkz.htm\" target=\"_blank\">ICP证：沪B2-20150087</a></span>\r\n");
      out.write("        </p>\r\n");
      out.write("\r\n");
      out.write("           \r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("        var even = document.getElementById(\"licode\");   \r\n");
      out.write("        var showqrs = document.getElementById(\"showqrs\");\r\n");
      out.write("         even.onmouseover = function(){\r\n");
      out.write("            showqrs.style.display = \"block\"; \r\n");
      out.write("         }\r\n");
      out.write("         even.onmouseleave = function(){\r\n");
      out.write("            showqrs.style.display = \"none\";\r\n");
      out.write("         }\r\n");
      out.write("         \r\n");
      out.write("         var out_trade_no = document.getElementById(\"out_trade_no\");\r\n");
      out.write("\r\n");
      out.write("         //设定时间格式化函数\r\n");
      out.write("         Date.prototype.format = function (format) {\r\n");
      out.write("               var args = {\r\n");
      out.write("                   \"M+\": this.getMonth() + 1,\r\n");
      out.write("                   \"d+\": this.getDate(),\r\n");
      out.write("                   \"h+\": this.getHours(),\r\n");
      out.write("                   \"m+\": this.getMinutes(),\r\n");
      out.write("                   \"s+\": this.getSeconds(),\r\n");
      out.write("               };\r\n");
      out.write("               if (/(y+)/.test(format))\r\n");
      out.write("                   format = format.replace(RegExp.$1, (this.getFullYear() + \"\").substr(4 - RegExp.$1.length));\r\n");
      out.write("               for (var i in args) {\r\n");
      out.write("                   var n = args[i];\r\n");
      out.write("                   if (new RegExp(\"(\" + i + \")\").test(format))\r\n");
      out.write("                       format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : (\"00\" + n).substr((\"\" + n).length));\r\n");
      out.write("               }\r\n");
      out.write("               return format;\r\n");
      out.write("           };\r\n");
      out.write("           \r\n");
      out.write("         out_trade_no.value = 'test'+ new Date().format(\"yyyyMMddhhmmss\");\r\n");
      out.write(" \r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}