﻿co.datapersons.ui = {

	serverErrorHandle : function() {

	},

	systemErrorHandle : function(error) {
		art.dialog({
					content : error.message,
					cancelVal : 'Close',
					cancel : true
				});
	},

	requestFailureHandle : function(error) {
		art.dialog({
					content : error.message,
					cancelVal : 'Close',
					cancel : true
				});
	},

	init : function() {

	}

};

co.datapersons.manager = {
	userid : "",
	phonenumber : "",
	identitycard:"",
	name:"",	
	usertype : "",
	sessionid : "",
	verifyCode : "",
	verifyGraph : false,
	curPageName : "",
	init : function() {
		if (!window.$) {
			return;
		}

	},

	pageLoad : function() {
		co.request({
			action : "user.getLoginData",
			body : {},
			success : function(data) {
				console.log(data);
				var result = data;
				if (result != undefined || result != null) {
					var id = result.result.i;
					var phonenumber = result.result.p;
					var session = result.result.s;
					var usertype = result.result.t;
					co.datapersons.manager.usertype = usertype;
					if ((co.datapersons.manager.curPageName == "shopperfect" && usertype == "user")
							|| (co.datapersons.manager.curPageName == "ureceive" && usertype == "shop")) {
						window.location.href = "../index.html";
						return;
					}
					co.datapersons.manager.phonenumber = phonenumber;
					co.datapersons.manager.userid = id;
					co.datapersons.manager.sessionid = session;

					if (co.datapersons.manager.curPageName == "setUserInfor") {
						co.datapersons.manager.getUserInfor();
					} else if (co.datapersons.manager.curPageName == "shopperfect") {

					} else if (co.datapersons.manager.curPageName == "basalData") {
						co.datapersons.manager.getUserInforWithBasalData();
					} else if (co.datapersons.manager.curPageName == "line") {
						co.datapersons.manager.GetUserConsumeByUserid();
						co.datapersons.manager.GetUserMonthConsumeByUserid();
						co.datapersons.manager.GetPayintegralByUserid();
						// popupQueueCount
						co.datapersons.manager.GetPopupQueueCount();
						// doingQueueCount
						co.datapersons.manager.GetDoingQueueCount();
						// prepareQueueCount
						co.datapersons.manager.GetPrepareQueueCount();
						// returnFee
						co.datapersons.manager.GetReturnFee();
					} else if (co.datapersons.manager.curPageName == "navRecommend") {
						// recommendCount
						co.datapersons.manager.GetRecommendCount();
						// recommendConsume
						co.datapersons.manager.GetRecommendConsume();
						// curUserRefereeURL
						co.datapersons.manager.GetCurUserRefereeURL();
					} else if (co.datapersons.manager.curPageName == "navShopManage") {
						co.datapersons.manager.GetShopIncomeByUserid();
						co.datapersons.manager.GetShopCountByUserid();
						co.datapersons.manager.QueryShopIncomeDetailByUserid();
					} else if (co.datapersons.manager.curPageName == "tdcode") {
						co.datapersons.manager.GenRefereeURL();
					} else if (co.datapersons.manager.curPageName == "queryShopInfor") {
						co.datapersons.manager.QueryShopInfor();
					} else if (co.datapersons.manager.curPageName == "setShopInfor") {
						co.datapersons.manager.QuerShopInforByShopid();
					} else if (co.datapersons.manager.curPageName == "navShopInfor") {
						co.datapersons.manager.QueryShopSumToday();
						co.datapersons.manager.QueryShopListUndone();
						co.datapersons.manager.QueryShopIncomeTodayByUserid();
					} else if (co.datapersons.manager.curPageName == "navUserInfor") {
						co.datapersons.manager.GetUserConsumeByUserid();
						co.datapersons.manager.GetReturnFee();
						co.datapersons.manager.GetDoingQueueConsume();
						co.datapersons.manager.GetPrepareQueueConsume();
						co.datapersons.manager.GetRecommendConsumeWithUserid();
						co.datapersons.manager.GetRecommendUserCount();
						co.datapersons.manager.GetRecommendShopCount();
						co.datapersons.manager.GetReturnInfor();
					} else if (co.datapersons.manager.curPageName == "addPayInfor") {
						// $('#userid').val();
						$('#WIDsubject').val('七度未来');
						co.datapersons.manager.QuerShopInforByShopid();
					} else if (co.datapersons.manager.curPageName == "navAgent") {
						co.datapersons.manager.GetProxyAreaCount();
						co.datapersons.manager.GetProxyConsume();
						co.datapersons.manager.GetProxyMonthConsume();
						co.datapersons.manager.QueryProxyDetailInfor();
						co.datapersons.manager.QueryAreaProxyInfor();
						
					} else if (co.datapersons.manager.curPageName == "goinShopping") {
						co.datapersons.manager.UserShopping();
					} else if (co.datapersons.manager.curPageName == "navShopsInfor") {
						co.datapersons.manager.getUserInforInNavShops();
					}else if(co.datapersons.manager.curPageName =="userConsumeDetail"){
						co.datapersons.manager.UserConsumeDetail();
					}else if (co.datapersons.manager.curPageName =="shopIncomeDetail"){
						co.datapersons.manager.ShopIncomeDetail();
					}

					if (id == "" || phonenumber == "" || session == ""
							|| usertype == "") {
						window.location.href = "../index.html";
					}

					// co.request({
					// action:"user.loginCountAdd",
					// body:{},
					// success:function(data){
					// return;
					// }
					// });
				}
				console.log(data);
			}
		});
	},

	GetUserMonthConsumeByUserid : function() {
		co.request({
					action : "user.GetUserMonthConsumeByUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#monthConsumeByUserid").text(result.result.value);
					}
				});
	},

	GetUserConsumeByUserid : function() {
		co.request({
					action : "user.GetUserConsumeByUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#consumeByUserid").text(result.result.value);
					}
				});
	},

	GetPayintegralByUserid : function() {
		co.request({
					action : "user.GetPayintegralByUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#consumePoints").text(result.result.value);
					}
				});
	},

	getRecordCount : function(userType) {
		co.request({
					action : "user.getRecordCount",
					body : {
						usertype : userType
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								var recordCount = result.result.recordCount;
								$("#personCount").text(recordCount);
								if (userType == "user") {
									$("#setUserType").text("人");
								} else {
									$("#setUserType").text("家商户");
								}

							}
						}
						console.log(data);
					}
				});
	},
	
	QueryProxyDetailInfor:function(){
		co.request({
			action:"user.QueryProxyDetailInfor",
			body:{},
			success:function(data){
				var result = data;
				var html ="";
				if(result.rows.length == 0){
					html = "<p><span> 您目前没有代理任何区域</span></p>";
				}else{
					
					for(var i= 0; i< result.rows.length; i++){
						var area = result.rows[i].area;
						var proxyshare = result.rows[i].proxyshare;
						var verifystatus = result.rows[i].verifystatus;
						if (verifystatus != 1){
							verifystatus = "尚未通过审核";
						}
						area = co.datapersons.manager.parserAreaCodeToString(area);
						html+="<p class=\"borderBottom\"> <span>代理区域：<span class=\"tll\">"+area+"</span> <span>代理额度："+proxyshare+"</span><span>状态："+verifystatus+"</span></span>"
					}
				}
				
				$("#proxyList").html(html);
			}
		});
	},
	
	QueryAreaProxyInfor:function(){
		co.request({
			action:"user.QueryAreaProxyInfor",
			body:{},
			success:function(data){
				var result = data;
				if(result.status =="0000"){
					if(result.rows.length > 0){
						$("#userid").text(result.rows[0].userid);
						$("#phonenumber")
								.text(result.rows[0].phonenumber);
					}else{
						$("#showAreaProxy").html("<span>当前区域没有代理！</span>");
					}
				}
			}
		})
	},

	GetPopupQueueCount : function() {
		co.request({
					action : "user.GetPopupQueueCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#popupQueueCount").text(result.result.value);
					}
				});
	},

	GetDoingQueueCount : function() {
		co.request({
					action : "user.GetDoingQueueCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#doingQueueCount").text(result.result.value);
					}
				});
	},
	GetPrepareQueueCount : function() {
		co.request({
					action : "user.GetPrepareQueueCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#prepareQueueCount").text(result.result.value);
					}
				});
	},

	GetReturnFee : function() {
		co.request({
					action : "user.GetReturnFee",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#returnFee").text(result.result.value);
						$("#returnFee1").text(result.result.value);
					}
				});
	},

	GetRecommendCount : function() {
		co.request({
					action : "user.GetRecommendCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#recommendCount").text(result.result.value);
					}
				});
	},

	GetRecommendConsume : function() {
		co.request({
					action : "user.GetRecommendConsume",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#recommendConsume").text(result.result.value);
					}
				});
	},

	GetDoingQueueConsume : function() {
		co.request({
					action : "user.GetDoingQueueConsume",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#queueFee").text(result.result.value);
					}
				});
	},

	GetPrepareQueueConsume : function() {
		co.request({
					action : "user.GetPrepareQueueConsume",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#prequeueFee").text(result.result.value);
					}
				});
	},
	GetRecommendConsumeWithUserid : function() {
		co.request({
					action : "user.GetRecommendConsumeWithUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#recommendIncome").text(result.result.value);
					}
				});
	},

	GetRecommendUserCount : function() {
		co.request({
					action : "user.GetRecommendUserCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#recommendUser").text(result.result.value);
					}
				});
	},

	GetRecommendShopCount : function() {
		co.request({
					action : "user.GetRecommendShopCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#recommendShop").text(result.result.value);
						
					}
				});
	},

	GetReturnInfor : function() {
		co.request({
					action : "user.GetReturnInfor",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#rewardPond").text(result.sumreduce);
						$("#rewardPeopleNumber").text(result.reducetonumber);
					}
				});
	},

	// curUserRefereeURL
	GetCurUserRefereeURL : function() {
		co.request({
					action : "user.GetCurUserRefereeURL",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#curUserRefereeURL")
								.text("www.qiduwl.com/index.html?refereeid="
										+ result.result.userid);
					}
				});
	},

	GenRefereeURL : function() {
		co.request({
					action : "user.GetCurUserRefereeURL",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#qrcodeTable").qrcode({
							render : "canvas",
							text : "http://www.qiduwl.com/index.html?refereeid="
									+ result.result.userid, // 这个地方是放置二维码地址的,传递一个地址进来,
							width : "200",
							height : "200"
						});
						// $("#qrcodeTable").qrcode("http://www.qiduwl.com?refereeid="+result.result.userid);
						// $("#curUserRefereeURL").text("www.qiduwl.com?userid="+result.result.userid);
					}
				});
	},

	GetShopCountByUserid : function() {
		co.request({
					action : "user.GetShopCountByUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#shopCount").text(result.result.value);
					}
				});
	},

	GetShopIncomeByUserid : function() {
		co.request({
					action : "user.GetShopIncomeByUserid",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						$("#shopIncome").text(result.result.value);
					}
				});
	},

	// ==========================================
	// Modified by Joseph Lee
	// 2016-04-28

	QueryShopIncomeDetailByUserid : function() {
		co.request({
			action : "user.QueryShopIncomeDetailByUserid",
			body : {},
			success : function(data) {
				console.log(data);
				var result = data;
				var html = "";
				for (var i = 0; i < result.rows.length; i++) {
					var item = result.rows[i];
					var shopid = item.id;
					var shopname = item.shopname;
					var shopaddress = item.shopaddress;
					var paynumber = item.paynumber;
					var paynumbermonth = item.paynumbermonth;
					html += "<p class=\"borderBottom\">  <span>商铺名称：<span class=\"tll\">"
							+ shopname
							+ "</span></span>  </p>"
							+ " <p class=\"borderBottom\">  <span>商铺所属区域：<span class=\"tll\">"
							+ shopaddress
							+ "</span></span> "
							+ "  <span>通过系统营业额：<span class=\"tll\">"
							+ paynumber
							+ "</span> 元</span>"
							+ "<span>通过系统月营业：<span class=\"tll\">"
							+ paynumbermonth
							+ "</span> 元</span>"
							+ " </p> "
							+ "  <p class=\"borderBottom\">"
							+ " <span><a href=\"addPayInfor.html?shopid="
							+ shopid
							+ "\">添加用户消费</a></span>     "
							+ " <span><a href=\"queryShopInfor.html?shopid="
							+ shopid
							+ "\">商铺交易记录</a></span>     "
							+ " <span><a href=\"setShopInfor.html?shopid="
							+ shopid
							+ "\">商铺信息修改申请</a></span> "
							+ "<span><a href=\"showShopQrcode.html?shopid="
							+ shopid + "\">商铺支付二维码生成</a></span>" + "</p>"
				}
				// 添加用户消费
				document.getElementById("queryShopIncomeList").innerHTML = html;
			}
		});
	},

	QueryShopListUndone : function() {
		co.request({
			action : "user.QueryShopListUndone",
			body : {},
			success : function(data) {
				console.log(data);
				var result = data;
				var html = "";
				for (var i = 0; i < result.rows.length; i++) {
					var item = result.rows[i];
					var shopname = item.shopname;
					var verifystatus = item.verifystatus;
					var undoncause = item.undonecause;
					var status = "<span>审核状态：<span class=\"tll\">正在审核</span>";
					if (verifystatus == "2") {
						status = " <span>审核状态：<span class=\"tll\">未通过&nbsp;</span><span>审核原因：<span class=\"tll\" id=\"undoncause\">"+undoncause+"</span></span>";
					}
					html += "<p class=\"borderBottom\"> "
							+ " <span>商铺名称：<span class=\"tll\">" + shopname
							+ "</span>" + status + "</p>";
				}
				// 添加用户消费
				document.getElementById("queryShopIncomeList").innerHTML = html;
			}
		});
	},

	QueryShopSumToday : function() {
		co.request({
					action : "user.QueryShopSumToday",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						var count = result.result.count;
						var paynumber = result.result.paynumber;
						$("#todayPaynumber").text(paynumber);
						$("#todayCount").text(count);
					}
				});
	},

	GetProxyAreaCount : function() {
		co.request({
					action : "user.GetProxyAreaCount",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						var count = result.result.value;

						$("#proxyAreaCount").text(count);
					}
				});
	},

	GetProxyConsume : function() {
		co.request({
					action : "user.GetProxyConsume",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						var paynumber = result.result.value;
						$("#proxyConsume").text(paynumber);
					}
				});
	},

	GetProxyMonthConsume : function() {
		co.request({
					action : "user.GetProxyMonthConsume",
					body : {},
					success : function(data) {
						console.log(data);
						var result = data;
						var count = result.result.count;
						var paynumber = result.result.value;
						$("#proxyMonthConsume").text(paynumber);
					}
				});
	},

	QueryShopIncomeTodayByUserid : function() {
		co.request({
			action : "user.QueryShopIncomeTodayByUserid",
			body : {},
			success : function(data) {
				console.log(data);
				var result = data;
				var html = "";
				for (var i = 0; i < result.rows.length; i++) {
					var item = result.rows[i];
					var shopid = item.id;
					var shopname = item.shopname;
					var shopaddress = item.shopaddress;
					var paynumbertoday = item.paynumbertoday;
					var paycount = item.paycount;
					html += "<p class=\"borderBottom\"> "
							+ " <span>商铺名称：<span class=\"tll\">" + shopname
							+ "</span>" + " <span>当日交易数：<span class=\"tll\">"
							+ paycount + "</span></span>"
							+ " <span>当日营业额：<span class=\"tll\">"
							+ paynumbertoday + "</span></span>" + "</p>";
				}
				// 添加用户消费
				document.getElementById("queryShopIncomeTodayList").innerHTML = html;
			}
		});
	},

	logout : function() {
		co.request({
					action : "user.logout",
					body : {},
					success : function(data) {
						window.location.href = "../index.html";
					}
				});
	},

	QueryShopInfor : function() {
		var shopURL = Util.getQueryString("shopid")[0];// shopid=1'
		var array = shopURL.split("=");
		var shopid = array[1];

		var shopname = "";
		var shopaddress = "";
		var area = "";
		co.request({
			action : "user.QueryShopInfor",
			body : {
				shopid : shopid
			},
			success : function(data) {
				console.log(data);
				var result = data;
				var html = "";
				for (var i = 0; i < result.rows.length; i++) {
					var item = result.rows[i];
					var shopid = item.id;
					var paynumber = item.paynumber;
					var paytime = item.paytime;
					var paytimecalc = item.paytimecalc;
					var paytype = item.paytype;
					var name = item.name;
					var phonenumber = item.phonenumber;
					shopname = item.shopname;
					shopaddress = item.shopaddress;
					area = item.area;

					var paynumbermonth = item.paynumbermonth;
					html += "<p class=\"borderBottom\">" + "<span>用户名称：<span"
							+ "	class=\"tll\">" + name + "</span>" + "</span>"
							+ "<span>用户电话：<span" + "	class=\"tll\">"
							+ phonenumber + "</span>" + "</span>"
							+ "<span>支付类型：<span class=\"tll\">" + paytype
							+ "</span>" + "</span>"
							+ "<span>交易时间：<span class=\"tll\">" + paytime
							+ "</span>" + "</span>" + "<span>交易金额：<span"
							+ "	class=\"tll\">" + paynumber + "</span> 元"
							+ "</span>" + "</p>"
				}
				$("#shopname").text(shopname);
				$("#shopaddress").text(shopaddress);
				$("#area").text(area);
				document.getElementById("queryShopIncomeList").innerHTML = html;
			}
		});
	},

	InsertShopInfor : function() {
		// get params
		var sign = $('#sign').val();
		var shopName = $('#shopName').val();
		var shopAddress = $('#shopAddress').val();
		var userSeachprov = $('#userSeachprov').val();
		var userSeachcity = $('#userSeachcity').val();
		var userSeachdistrict = $('#userSeachdistrict').val();
		var organizeNumber = "";
		var incorporator = $('#incorporator').val();
		var phone = $('#incorporatorNumber').val();
		var area = userSeachprov + "-" + userSeachcity + "-"
				+ userSeachdistrict;
		var shopType = $('#shopType').val();
		var orgNumber = $('#orgNumber').val();
		co.request({
					action : "user.InsertShopInfor",
					body : {
						sign : sign,
						shopName : shopName,
						shopAddress : shopAddress,
						area : area,
						organizeNumber : organizeNumber,
						incorporator : incorporator,
						phone : phone,
						shopType: shopType,
						orgNumber:orgNumber
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result.status == "0000") {
							art.dialog({
										content : '商铺添加成功！',
										cancelVal : 'Close',
										cancel : true
									});
						}
					}
				});
	},

	QuerShopInforByShopid : function() {
		var shopURL = Util.getQueryString("shopid")[0];// shopid=1'
		var array = shopURL.split("=");
		var shopid = array[1];

		co.request({
					action : "user.QueryShopInforByShopid",
					body : {
						shopid : shopid
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result.status == "0000") {
							for (var i = 0; i < result.rows.length; i++) {
								var item = result.rows[i];
								var id = item.id;
								var area = item.area;
								var areaArray = area.split('-');

								var shopaddress = item.shopaddress;
								var shopbrand = item.shopbrand;
								var applytime = item.applytime;
								var shopname = item.shopname;
								var orgnumber = item.orgnumber;
								var incorporator = item.incorporator;

								var phone = item.phone;
								var verifytime = item.verifytime;
								var checkpeople = item.checkpeople;
								var undonecause = item.undonecause;
								//

								$('#userid').val(id);
								$('#sign').val(shopbrand);
								$('#shopName').val(shopname);
								$('#shopNameSpan').text(shopname);
								$('#shopAddress').val(shopaddress);
								$('#userSeachprov').val(areaArray[0]);
								$('#userSeachcity').val(areaArray[1]);
								changeCity(areaArray[1], 'userSeachdistrict',
										'userSeachdistrict');
								$('#userSeachdistrict').val(areaArray[2]);

								$('#organizeNumber').val(orgnumber);
								$('#incorporator').val(incorporator);
								$('#incorporatorNumber').val(phone);

							}
						}
					}
				});
	},

	SetShopInfor : function() {
		// get params
		var shopURL = Util.getQueryString("shopid")[0];// shopid=1'
		var array = shopURL.split("=");
		var shopid = array[1];

		var sign = $('#sign').val();
		var shopName = $('#shopName').val();
		var shopAddress = $('#shopAddress').val();
		var userSeachprov = $('#userSeachprov').val();
		var userSeachcity = $('#userSeachcity').val();
		var userSeachdistrict = $('#userSeachdistrict').val();
		var organizeNumber = $('#organizeNumber').val();
		var incorporator = $('#incorporator').val();
		var phone = $('#incorporatorNumber').val();
		var area = userSeachprov + "-" + userSeachcity + "-"
				+ userSeachdistrict;
		co.request({
					action : "user.SetShopInfor",
					body : {
						sign : sign,
						shopName : shopName,
						shopAddress : shopAddress,
						area : area,
						organizeNumber : organizeNumber,
						incorporator : incorporator,
						phone : phone,
						shopid : shopid
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result.status == "0000") {
							art.dialog({
										content : '商铺添加成功！',
										cancelVal : 'Close',
										cancel : true
									});
						}
					}
				});
	},

	// end modify
	// =========================================

	requestSMS : function() {
		var mobile = $("#mobileNum").val();
		co.request({
			action : "SendSMSVerify.requestVerify",
			body : {
				mobileNum : mobile
			},
			success : function(data) {
				console.log(data);
				var result = data;
				if (result != undefined || result != null) {
					co.datapersons.manager.phonenumber = result.result.mobileNum;
					co.datapersons.manager.verifyCode = result.result.VerifyCode;
				}
				console.log(data);
			}
		});

	},

	turnToCode : function() {
		var responsePhone = co.datapersons.manager.phonenumber;
		var phonenumber = $("#mobileNum").val();
		var verifyCode = $("#verificationCard").val();
		var responseCode = co.datapersons.manager.verifyCode;
		co.request({
					action : "User.TurnToCode",
					body : {
						phonenumber : phonenumber,
						responsePhone : responsePhone,
						verifyCode : verifyCode,
						responseCode : responseCode
					},
					success : function(data) {
						console.log(data);
						var result = data;

						if (result.result.t == "temp") {
							window.location.href = "changePwd.html";
						}

						console.log(data);
					}
				});
		// window.location.href = "changePwd.html";
	},

	changePwd : function() {
		var password = $("#password").val();
		var confirmPwd = $("#confirmPwd").val();
		co.request({
					action : "User.updatePassword",
					body : {
						password : password,
						confirmPwd : confirmPwd
					},
					success : function(data) {
						console.log(data);
						var result = data;

						if (result.status == "0000") {
							art.dialog({
										content : "密码修改成功！请以新密码重新登录！",
										ok : function() {
											window.location.href = "../index.html";
										}
									});
						}

						console.log(data);
					}
				});
	},
	registerVerify : function() {
		var mobileNum = $("#mobileNum").val();
		var verifyText = $("#verifyText").val();
		var password = $("#password").val();
		var pwFirm = $("#pwFirm").val();

		// if(co.datapersons.manager.verifyGraph != true){
		// art.dialog({
		// content: '图形验证码不正确，请再次确认！',
		// cancelVal: 'Close',
		// cancel: true
		// });
		// return "9999";
		// }

		if (mobileNum == "") {
			art.dialog({
						content : '请输入手机号。',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}
		if (verifyText == "") {
			art.dialog({
						content : '请输入验证码。',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		if (password == "") {
			art.dialog({
						content : '请输入密码。',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		if (pwFirm == "") {
			art.dialog({
						content : '请输入确认密码。',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		if (pwFirm != password) {
			art.dialog({
						content : '确认密码与密码不符。',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		if (co.datapersons.manager.phonenumber != mobileNum) {
			art.dialog({
						content : '输入手机号与接受短信手机不符，请再次确认！',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		if (co.datapersons.manager.verifyCode != verifyText) {
			art.dialog({
						content : '输入验证码不正确，请再次确认！',
						cancelVal : 'Close',
						cancel : true
					});
			return "9999";
		}

		return "0000";
	},

	login : function(type) {
		var mobileNum = $("#mobileNum").val();
		var password = $("#password").val();

		co.request({
					action : "User.login",
					body : {
						phonenumber : mobileNum,
						password : password,
						usertype : type
					},
					success : function(data) {
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								if (type != result.result.t) {
									var typeStr = "";
									if(result.result.t == "shop"){
										typeStr = "商户";
									}
									if(result.result.t == "user"){
										typeStr = "用户";
									}
									art.dialog({
										content : "请从"+typeStr+"入口登录！",
										cancelVal : 'Close',
										cancel : true
									});
									return;
								}
								if (type == "user") {
									window.location.href = "navUserInfor.html";
								}
								if (type == "shop") {
									window.location.href = "navShopInfor.html";
								}
							}
						}
					}
				});
	},

	firstlogin : function(type) {
		var mobileNum = $("#mobileNum").val();
		var password = $("#password").val();

		co.request({
					action : "User.login",
					body : {
						phonenumber : mobileNum,
						password : password,
						usertype : type
					},
					success : function(data) {
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								if (type != result.result.t) {
									art.dialog({
												content : '用户类型不符！',
												cancelVal : 'Close',
												cancel : true
											});
									return;
								}
								if (type == "user") {
									window.location.href = "userperfect.html";
								}
								if (type == "shop") {
									window.location.href = "shopperfect.html";
								}
							}
						}
					}
				});
	},

	userperfectAdd : function() {
		var weixin = $("#weixin").val();
		var zhifubao = $("#zhifubao").val();

		co.request({
					action : "User.userperfectAdd",
					body : {
						weixin : weixin,
						zhifubao : zhifubao
					},
					success : function(data) {
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								art.dialog({
											content : '用户信息以保存！',
											cancelVal : 'Close',
											cancel : true
										});
								return;
							}
						}
					}
				});
	},

	managerloginBtn : function() {
		var userid = $("#mobileNum").val();
		var password = $("#password").val();

		co.request({
					action : "User.managerlogin",
					body : {
						userid : userid,
						password : password
					},
					success : function(data) {
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								var url = co.datapersons.url;
								url = url.substring(0, url
												.indexOf("HttpService"));
								if (window.location.href
										.indexOf("datapersons/") >= 0) {
									window.location.href = "after.html";
								} else {
									window.location.href = "after.html";
								}
							}
						}
					}
				});
	},

	submitPost : function(userType, refereeid) {
		var verifyCode = co.datapersons.manager.registerVerify();
		var password = $("#password").val();
		if (verifyCode == "0000") {
			co.request({
				action : "User.checkPhonenumber",
				body : {
					phonenumber : co.datapersons.manager.phonenumber
				},
				success : function(data) {
					console.log(data);
					var result = data;
					if (result != undefined || result != null) {
						if (result.status == "0000") {
							co.request({
								action : "User.register",
								body : {
									phonenumber : co.datapersons.manager.phonenumber,
									password : password,
									usertype : userType,
									refereeid : refereeid
								},
								success : function(data) {
									console.log(data);
									var result = data;

									if (result != undefined || result != null) {
										if (result.status == "0000") {
											co.datapersons.manager
													.firstlogin(result.usertype);
										}
									}
									console.log(data);
								}
							});
						}
					}
					console.log(data);
				}
			});
		}
	},
	
	SetPayInfor : function(){
		var zhifubao = $("#zhifubao").val();
		var weixin = $("#weixin").val();
		co.request({
			action : "user.userperfectAdd",
			body : {				
				zhifubao : zhifubao,
				weixin : weixin
			},
			success : function(data) {
				console.log(data);
				var result = data;
				if (result != undefined || result != null) {
					if (result.status == "0000") {
						art.dialog({
									content : '用户信息已保存！',
									ok : function() {
										window.location.href = "basalData.html";

									}
								});
					}
				}
				console.log(data);
			}
		});
	},

	updateUser : function() {
		var username = $("#userName").val();
		var idCard = $("#identityCard").val();
		var userSeachprov = $('#userSeachprov').val();
		var userSeachcity = $('#userSeachcity').val();
		var userSeachdistrict = $('#userSeachdistrict').val();
		var userArea = userSeachprov + "-" + userSeachcity + "-"
				+ userSeachdistrict;
		var ICBCCard = $("#bankCardICBC").val();
		var zhifubao = $("#zhifubao").val();
		var weixin = $("#weixin").val();
		var mail = $("#mail").val();

		co.request({
			action : "user.updateUserInfo",
			body : {
				phonenumber : co.datapersons.manager.phonenumber,
				username : username,
				idCard : idCard,
				ICBCCard : ICBCCard,
				userArea : userArea,
				zhifubao : zhifubao,
				weixin : weixin,
				mail : mail
			},
			success : function(data) {
				console.log(data);
				var result = data;
				if (result != undefined || result != null) {
					if (result.status == "0000") {
						art.dialog({
									content : '用户信息已保存！',
									ok : function() {
										window.location.href = "basalData.html";

									}
								});
					}
				}
				console.log(data);
			}
		});
	},

	InsertUserInfor : function() {
		var username = $("#userName").val();
		var idCard = $("#identityCard").val();
		var userSeachprov = $('#userSeachprov').val();
		var userSeachcity = $('#userSeachcity').val();
		var userSeachdistrict = $('#userSeachdistrict').val();
		var userArea = userSeachprov + "-" + userSeachcity + "-"
				+ userSeachdistrict;
		var ICBCCard = $("#bankCardICBC").val();
		var zhifubao = $("#zhifubao").val();
		var weixin = $("#weixin").val();
		var mail = $("#mail").val();

		co.request({
					action : "User.updateUserInfo",
					body : {
						phonenumber : co.datapersons.manager.phonenumber,
						username : username,
						idCard : idCard,
						ICBCCard : ICBCCard,
						userArea : userArea,
						zhifubao : zhifubao,
						weixin : weixin,
						mail : mail
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								art.dialog({
											content : '用户信息已保存！',
											ok : function() {
												// window.location.href =
												// "basalData.html";

											}
										});
							}
						}
						console.log(data);
					}
				});
	},

	updateBusiness : function() {
		var username = $("#username").val();
		var idCard = $("#idCard").val();
		var ICBCCard = $("#ICBCCard").val();
		var WXNum = $("#WXNum").val();
		var ZFBNum = $("#ZFBNum").val();

		co.request({
					action : "User.updateUserInfo",
					body : {
						username : username,
						idCard : idCard,
						ICBCCard : ICBCCard,
						WXNum : WXNum,
						ZFBNum : ZFBNum
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								art.dialog({
											content : '用户信息已保存！',
											cancelVal : 'Close',
											cancel : true
										});
							}
						}
						console.log(data);
					}
				});
	},

	getUserInfor : function() {
		co.request({
					action : "User.getUserInfo",
					body : {
						phonenumber : co.datapersons.manager.phonenumber
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								// 取出返回值
								var registime = result.result.registime;
								var phonenumber = result.result.phonenumber;
								var identitycard = result.result.identitycard;
								var smallletter = result.result.smallletter;
								var paytreasurable = result.result.paytreasurable;
								var businessbank = result.result.businessbank;
								var name = result.result.name;

								var perfecttime = result.result.perfecttime;
								var refereeid = result.result.refereeid;
								var landtime = result.result.landtime;
								var lastlandtime = result.result.lastlandtime;
								var sex = result.result.sex;
								var age = result.result.age;
								var birthday = result.result.birthday;
								var usertype = result.result.usertype;
								var phoneattributive = result.result.phoneattributive;
								var bodyattributive = result.result.bodyattributive;
								var mailaddress = result.result.mailaddress;
								var usershare = result.result.usershare;
								var shopshare = result.result.shopshare;

								var payintegral = result.result.payintegral;
								var distribution = result.result.distribution;

								var shopid = result.result.shopid;
								var paytime = result.result.paytime;
								var paytotal = result.result.paytotal;
								var balance = result.result.balance;
								var paycount = result.result.paycount;

								var refereecount = result.result.refereecount;
								var shopname = result.result.shopname;
								var paytimecalc = result.result.paytimecalc;
								var paytype = result.result.paytype;

								if (bodyattributive == null) {
									bodyattributive = "";
								} else {
									var areaArray = bodyattributive.split("-");
									$('#userSeachprov').val(areaArray[0]);
									$('#userSeachcity').val(areaArray[1]);
									changeCity(areaArray[1],
											'userSeachdistrict',
											'userSeachdistrict');
									$('#userSeachdistrict').val(areaArray[2]);
								}

								// 给元素赋值
								$("#userName").val(name);
								$("#identityCard").val(identitycard);

								$("#bankCardICBC").val(businessbank);
								$("#weixin").val(smallletter);
								$("#zhifubao").val(paytreasurable);
								$("#mail").val(mailaddress);

							}
						}
						console.log(data);
					}
				});
	},
	
	parserAreaCodeToString:function(code){
		var curArea = "";

		if (code == null) {
			code = "";
		} else {
			var areaArray = code.split("-");

			$('#userSeachprov').val(areaArray[0]);
			curArea = area_array[areaArray[0]];
			$('#userSeachcity').val(areaArray[1]);
			curArea += "-"
					+ sub_array[areaArray[0]][areaArray[1]];
			changeCity(areaArray[1],
					'userSeachdistrict',
					'userSeachdistrict');
			$('#userSeachdistrict').val(areaArray[2]);
			curArea += "-"
					+ sub_arr[areaArray[1]][areaArray[2]];

		}
		return curArea;
	},

	getUserInforInNavShops : function() {
		co.request({
					action : "User.getUserInfo",
					body : {
						phonenumber : co.datapersons.manager.phonenumber
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								// 取出返回值
								var bodyattributive = result.result.bodyattributive;

								var curArea = "";

								if (bodyattributive == null) {
									bodyattributive = "";
								} else {
									var areaArray = bodyattributive.split("-");

									$('#userSeachprov').val(areaArray[0]);
									curArea = area_array[areaArray[0]];
									$('#userSeachcity').val(areaArray[1]);
									curArea += "-"
											+ sub_array[areaArray[0]][areaArray[1]];
									changeCity(areaArray[1],
											'userSeachdistrict',
											'userSeachdistrict');
									$('#userSeachdistrict').val(areaArray[2]);
									curArea += "-"
											+ sub_arr[areaArray[1]][areaArray[2]];

								}

								// 给元素赋值
								$("#curArea").text(curArea);
								co.datapersons.manager.shopListSearch();
							}
						}
						console.log(data);
					}
				});
	},

	getUserInforWithBasalData : function() {
		co.request({
					action : "User.getUserInfo",
					body : {
						phonenumber : co.datapersons.manager.phonenumber
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								// 取出返回值

								var userid = result.result.userid;
								var registime = result.result.registime;
								var phonenumber = result.result.phonenumber;
								var identitycard = result.result.identitycard;
								var smallletter = result.result.smallletter;
								var paytreasurable = result.result.paytreasurable;
								var businessbank = result.result.businessbank;
								var name = result.result.name;

								var perfecttime = result.result.perfecttime;
								var refereeid = result.result.refereeid;
								var landtime = result.result.landtime;
								var lastlandtime = result.result.lastlandtime;
								var sex = result.result.sex;
								var age = result.result.age;
								var birthday = result.result.birthday;
								var usertype = result.result.usertype;
								var phoneattributive = result.result.phoneattributive;
								var bodyattributive = result.result.bodyattributive;
								var mailaddress = result.result.mailaddress;
								var usershare = result.result.usershare;
								var shopshare = result.result.shopshare;

								var payintegral = result.result.payintegral;
								var distribution = result.result.distribution;

								var shopid = result.result.shopid;
								var paytime = result.result.paytime;
								var paytotal = result.result.paytotal;
								var balance = result.result.balance;
								var paycount = result.result.paycount;

								var refereecount = result.result.refereecount;
								var shopname = result.result.shopname;
								var paytimecalc = result.result.paytimecalc;
								var paytype = result.result.paytype;
								if (identitycard == null) {
									identitycard = "";
								}

								if (name == null) {
									name = "";
								}

								if (bodyattributive == null || bodyattributive == "") {
									bodyattributive = "";
								} else {
									var b = bodyattributive.split("-");
									var provice = "";
									var city = "";
									var district = "";
									provice = area_array[b[0]];
									bodyattributive = provice;
									if (provice != "请选择") {
										city = sub_array[b[0]][b[1]];
									}

									if (city != "请选择") {
										district = sub_arr[b[1]][b[2]];
										bodyattributive += "-" + city;
										if (district != "请选择") {
											bodyattributive += "-" + district;
										}
									}

								}

								if (mailaddress == null) {
									mailaddress = "";
								}

								if (paytreasurable == null) {
									paytreasurable = "";
								}
								if (smallletter == null) {
									smallletter = "";
								}
								// 给元素赋值
								$("#userID").text(userid);
								$("#registime").text(registime);
								$("#name").text(name);
								$("#identitycard").text(identitycard);
								$("#phonenumber").text(phonenumber);

								$("#bodyattributive").text(bodyattributive);
								$("#mailaddress").text(mailaddress);
								$("#paytreasurable").text(paytreasurable);
								$("#smallletter").text(smallletter);
							}
						}
						console.log(data);
					}
				});
	},

	getShopInfor : function() {
		co.request({
					action : "User.getUserInfo",
					body : {
						phonenumber : co.datapersons.manager.phonenumber
					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								$("#username").val(result.result.FullName);
								$("#idCard").val(result.result.idCard);
								$("#ICBCCard").val(result.result.ICBCCard);
								$("#WXNum").val(result.result.WXNum);
								$("#ZFBNum").val(result.result.ZFBNum);

							}
						}
						console.log(data);
					}
				});
	},

	loadShopPage : function() {
		co.request({
			action : "User.getLoginData",
			body : {},
			success : function(data) {
				console.log(data);
				var result = data;
				if (result != undefined || result != null) {
					if (result.status == "0000") {
						co.datapersons.manager.usertype = result.result.t;
						$("#userid").val(result.result.i);
						if(result.result.t == "user"){
							$("#consumerid").val(result.result.i);
							$('#consumerid').attr("readonly",true)
						}
						co.datapersons.manager.phonenumber = result.result.p;
						co.request({
							action : "User.getUserInfo",
							body : {
								phonenumber : co.datapersons.manager.phonenumber
							},
							success : function(data) {
								var result = data;
								if (result != undefined || result != null) {
									if (result.status == "0000") {
										$("#fullname")
												.val(result.result.FullName);
									}
								}
							}
						});
						if (document.getElementById("out_trade_no")) {
							var out_trade_no = document
									.getElementById("out_trade_no");
							// 设定时间格式化函数
							Date.prototype.format = function(format) {
								var args = {
									"M+" : this.getMonth() + 1,
									"d+" : this.getDate(),
									"h+" : this.getHours(),
									"m+" : this.getMinutes(),
									"s+" : this.getSeconds()
								};
								if (/(y+)/.test(format))
									format = format
											.replace(
													RegExp.$1,
													(this.getFullYear() + "")
															.substr(4
																	- RegExp.$1.length));
								for (var i in args) {
									var n = args[i];
									if (new RegExp("(" + i + ")").test(format))
										format = format
												.replace(
														RegExp.$1,
														RegExp.$1.length == 1
																? n
																: ("00" + n)
																		.substr(("" + n).length));
								}
								return format;
							};

							out_trade_no.value = '七度未来'
									+ new Date().format("yyyyMMddhhmmss");
						}
					}
				}
				console.log(data);
			}
		});

	},

	pay : function() {
		var mobileNum = co.datapersons.manager.phonenumber;
		var subject = $("#subject").val();
		var total_fee = $("#total_fee").val();
		var consumerid = $("#consumerid").val();
		var WIDout_trade_no = $("#out_trade_no").val();
		// var curShopID = $("#curShopID").val();
		co.request({
					action : "ZFB.paying",
					body : {
						userid : co.datapersons.manager.phonenumber,
						subject : "养老消费",
						consumerid : consumerid,
						total_fee : total_fee,
						WIDout_trade_no : WIDout_trade_no,
						WIDbody : "养老消费"

					},
					success : function(data) {
						console.log(data);
						var result = data;
						if (result != undefined || result != null) {
							if (result.status == "0000") {
								// var url =
								// "https://www.alipay.com/cooperate/gateway.do?_input_charset=utf-8";
								var body = result.result.body;
								var notify_url = result.result.notify_url;
								var out_trade_no = result.result.out_trade_no;
								var partner = result.result.partner;
								var payment_type = result.result.payment_type;
								var seller_email = result.result.seller_email;
								var service = result.result.service;
								var sign_type = result.result.sign_type;
								var subject = result.result.subject;

								var total_fee = result.result.total_fee;
								var show_url = result.result.show_url;
								var return_url = result.result.return_url;
								var paymethod = result.result.paymethod;
								var defaultbank = result.result.defaultbank;

								var sign = result.result.sign;
								$("#idCard").val(result.result.idCard);
								$("#ICBCCard").val(result.result.ICBCCard);
								$("#WXNum").val(result.result.WXNum);
								$("#ZFBNum").val(result.result.ZFBNum);

								$.ajax({
											type : "post",
											url : url,
											dataType : "json",
											data : {
												body : body,
												notify_url : notify_url,
												out_trade_no : out_trade_no,
												partner : partner,
												payment_type : payment_type,
												seller_email : seller_email,
												service : service,
												sign_type : sign_type,
												subject : subject,
												total_fee : total_fee,
												sign : sign,
												show_url : show_url,
												return_url : return_url,
												paymethod : paymethod,
												defaultbank : defaultbank
											},

											success : function(data) {

											}
										});
							}
						}
						console.log(data);
					}
				});
	},
	initVCode : function() {
		// vcode
		var randstr = function(length) {
			var key = {

				str : ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
						'l', 'm', 'o', 'p', 'q', 'r', 's', 't', 'x', 'u', 'v',
						'y', 'z', 'w', 'n', '0', '1', '2', '3', '4', '5', '6',
						'7', '8', '9'],

				randint : function(n, m) {
					var c = m - n + 1;
					var num = Math.random() * c + n;
					return Math.floor(num);
				},

				randStr : function() {
					var _this = this;
					var leng = _this.str.length - 1;
					var randkey = _this.randint(0, leng);
					return _this.str[randkey];
				},

				create : function(len) {
					var _this = this;
					var l = len || 10;
					var str = '';

					for (var i = 0; i < l; i++) {
						str += _this.randStr();
					}

					return str;
				}

			};

			length = length ? length : 10;

			return key.create(length);
		};

		var randint = function(n, m) {
			var c = m - n + 1;
			var num = Math.random() * c + n;
			return Math.floor(num);
		};

		var vCode = function(dom, options) {
			this.codeDoms = [];
			this.lineDoms = [];
			this.initOptions(options);
			this.dom = dom;
			this.init();
			this.addEvent();
			this.update();
			this.mask();
		};

		vCode.prototype.init = function() {
			this.dom.style.position = "relative";
			this.dom.style.overflow = "hidden";
			this.dom.style.cursor = "pointer";
			this.dom.title = "点击更换验证码";
			this.dom.style.background = this.options.bgColor;
			this.w = this.dom.clientWidth;
			this.h = this.dom.clientHeight;
			this.uW = this.w / this.options.len;
		};

		vCode.prototype.mask = function() {
			var dom = document.createElement("div");
			dom.style.cssText = ["width: 100%", "height: 100%", "left: 0",
					"top: 0", "position: absolute", "cursor: pointer",
					"z-index: 9999999"].join(";");

			dom.title = "点击更换验证码";

			this.dom.appendChild(dom);
		};

		vCode.prototype.addEvent = function() {
			var _this = this;
			_this.dom.addEventListener("click", function() {
						_this.update.call(_this);
					});
		};

		vCode.prototype.initOptions = function(options) {

			var f = function() {
				this.len = 4;
				this.fontSizeMin = 20;
				this.fontSizeMax = 48;
				this.colors = ["green", "red", "blue", "#53da33", "#AA0000",
						"#FFBB00"];
				this.bgColor = "#FFF";
				this.fonts = ["Times New Roman", "Georgia", "Serif",
						"sans-serif", "arial", "tahoma", "Hiragino Sans GB"];
				this.lines = 8;
				this.lineColors = ["#888888", "#FF7744", "#888800", "#008888"];

				this.lineHeightMin = 1;
				this.lineHeightMax = 3;
				this.lineWidthMin = 1;
				this.lineWidthMax = 60;
			};

			this.options = new f();

			if (typeof options === "object") {
				for (i in options) {
					this.options[i] = options[i];
				}
			}
		};

		vCode.prototype.update = function() {
			for (var i = 0; i < this.codeDoms.length; i++) {
				this.dom.removeChild(this.codeDoms[i]);
			}
			for (var i = 0; i < this.lineDoms.length; i++) {
				this.dom.removeChild(this.lineDoms[i]);
			}
			this.createCode();
			this.draw();
		};

		vCode.prototype.createCode = function() {
			this.code = randstr(this.options.len);
		};

		vCode.prototype.verify = function(code) {
			return this.code === code;
		};

		vCode.prototype.draw = function() {
			this.codeDoms = [];
			for (var i = 0; i < this.code.length; i++) {
				this.codeDoms.push(this.drawCode(this.code[i], i));
			}

			this.drawLines();
		};

		vCode.prototype.drawCode = function(code, index) {
			var dom = document.createElement("span");
			fontSizeMin = 17;
			fontSizeMax = 30;
			dom.style.cssText = [
					// "font-size:" + randint(this.options.fontSizeMin,
					// this.options.fontSizeMax) + "px",
					"font-size:" + randint(fontSizeMin, fontSizeMax) + "px",
					"color:"
							+ this.options.colors[randint(0,
									this.options.colors.length - 1)],
					"position: absolute",
					"left:"
							+ randint(this.uW * index, this.uW * index
											+ this.uW - 10) + "px",
					"top:" + randint(0, this.h - 30) + "px",
					"transform:rotate(" + randint(-30, 30) + "deg)",
					"-ms-transform:rotate(" + randint(-30, 30) + "deg)",
					"-moz-transform:rotate(" + randint(-30, 30) + "deg)",
					"-webkit-transform:rotate(" + randint(-30, 30) + "deg)",
					"-o-transform:rotate(" + randint(-30, 30) + "deg)",
					"font-family:"
							+ this.options.fonts[randint(0,
									this.options.fonts.length - 1)],
					"font-weight:" + randint(400, 900)].join(";");

			dom.innerHTML = code;
			this.dom.appendChild(dom);

			return dom;
		};

		vCode.prototype.drawLines = function() {
			this.lineDoms = [];
			for (var i = 0; i < this.options.lines; i++) {
				var dom = document.createElement("div");

				dom.style.cssText = [
						"position: absolute",
						"opacity: " + randint(3, 8) / 10,
						"width:"
								+ randint(this.options.lineWidthMin,
										this.options.lineWidthMax) + "px",
						"height:"
								+ randint(this.options.lineHeightMin,
										this.options.lineHeightMax) + "px",
						"background: "
								+ this.options.lineColors[randint(0,
										this.options.lineColors.length - 1)],
						"left:" + randint(0, this.w - 20) + "px",
						"top:" + randint(0, this.h) + "px",
						"transform:rotate(" + randint(-30, 30) + "deg)",
						"-ms-transform:rotate(" + randint(-30, 30) + "deg)",
						"-moz-transform:rotate(" + randint(-30, 30) + "deg)",
						"-webkit-transform:rotate(" + randint(-30, 30) + "deg)",
						"-o-transform:rotate(" + randint(-30, 30) + "deg)",
						"font-family:"
								+ this.options.fonts[randint(0,
										this.options.fonts.length - 1)],
						"font-weight:" + randint(400, 900)].join(";");
				this.dom.appendChild(dom);

				this.lineDoms.push(dom);
			}
		};

		this.vCode = vCode;

		var container1 = document.getElementById("generalGraph");
		var code1 = new vCode(container1);

		$('#verifyGraph').keyup(function() {
			var isRight = code1.verify($("#verifyGraph").val());
			co.datapersons.manager.verifyGraph = isRight;
			$('#verifyGraph').css("border-color", "#eee");
			if (isRight) {
				$('#verifyGraph').css("backgroundImage",
						"url(../images/correct.png)");
			} else {
				$('#verifyGraph').css("backgroundImage",
						"url(../images/error.png)");
			}
		});

		$('#verifyGraph').blur(function() {
					if ($('#verifyGraph').val() == "") {
						$('#verifyGraph').css("border-color", "#EA0000");
					}
				});

		$('#verifyGraph').focus(function() {
					$('#verifyGraph').css("border-color", "#eee");
				});
	},

	qrcode : function() {

		$('#qrcodeCanvas').qrcode({
			text : "https://mapi.alipay.com/gateway.do?sign=7dc2f5f2fe7803097f7bd05d0e6d0402&_input_charset=utf-8&subject=养老消费&sign_type=MD5&service=create_direct_pay_by_user&notify_url=http://www.qiduwl.com/datapersons/notify_url.jsp&partner=2088021003577393&seller_id=2088021003577393&payment_type=1&return_url=http://www.qiduwl.com/datapersons/return_url.jsp",// 二维码代表的字符串（本页面的URL）
			width : 167,// 二维码宽度
			height : 167
				// 二维码高度
		});
	},

	returnURL : function() {
		window.history.back(-1);
	},

	toForgetPwd : function() {
		var usertype = Util.getQueryStringByName("usertype");
		window.location.href = "find.html?usertype=" + usertype;
	},

	UploadFile : function() {
		var shopid = "";
		$('#projectfile').uploadify('settings', 'scriptData', {
					'shopid' : 1
				});

		$('#projectfile').uploadify('upload', '*');
	},

	gotoShop : function(shopid) {
		co.request({
			action : "user.getLoginData",
			body : {},
			success : function(data) {

				var type = data.result.t;
				if (type == "user") {
					window.location.href = "goinShopping.html?shopid=" + shopid;
				}
				if (type == "shop") {
					window.location.href = "setShopInfor.html?shopid=" + shopid;
				}

			}
		});
	},

	shopListSearch : function() {
		var userSeachprov = $('#userSeachprov').val();
		var userSeachcity = $('#userSeachcity').val();
		var userSeachdistrict = $('#userSeachdistrict').val();

		var shopNameText = $('#shopNameText').val();
		var areaStr = area_array[userSeachprov] + "-"
				+ sub_array[userSeachprov][userSeachcity] + "-"
				+ sub_arr[userSeachcity][userSeachdistrict];
		$("#curArea").text(areaStr);
		var area = userSeachprov + "-" + userSeachcity + "-"
				+ userSeachdistrict;
		co.request({
			action : "user.QueryShopsList",
			body : {
				area : area,
				shopname : shopNameText

			},
			success : function(data) {
				console.log(data);
				var html = "";
				if (data.status = "0000") {
					for (var i = 0; i < data.rows.length; i++) {
						var shopid = data.rows[i].id;
						var shopname = data.rows[i].shopname;
						var address = data.rows[i].shopaddress;
						var shopType = data.rows[i].shoptype;
						var image = data.rows[i].image;
						var shopbrand = data.rows[i].shopbrand;
						if (shopbrand == null || shopbrand == undefined){
							shopbrand = "";
						}
						if(image){
							image = image.replaceAll("//","\\")
						}else{
							image="";
						}
						html += "<div class=\"borderBottom dHeight\" onclick='co.datapersons.manager.gotoShop("
								+ shopid
								+ ")'>"
								+ "<div class=\"divLeft\">"
								+ "<img src=\""+image+"\">"
								+ "</div>"
								+ "<div class=\"divRight\">"
								+ "<div><span class=\"shopName\">"
								+ shopbrand
								+ "</span></div>"
								+ "<div><span>"
								+ address
								+ "</span></div>"
								+ "<div><span class=\"shopLabel\">"
								+ shopType
								+ "</span></div>" + "</div>" + "</div>";
					}
				}

				$("#shopsList").html(html);
			}
		});
	},

	UserShopping : function() {
		var shopid = Util.getQueryStringByName("shopid");
		co.request({
					action : "user.QueryShopInforByShopid",
					body : {
						shopid : shopid
					},
					success : function(data) {
						console.log(data);
						var shopid = data.rows[0].id;
						var shopname = data.rows[0].shopname;
						var shoptype = data.rows[0].shoptype;
						var image = data.rows[0].image;
						if (shoptype == undefined)
							shoptype = "";
						var phone = data.rows[0].phone;
						if (phone == null)
							phone = "";
						$("#shopid").text(shopid);
						$("#shopname").text(shopname);
						$("#shoptype").text(shoptype);
						$("#phone").text(phone);
						$("#shopimage").attr("src",image);
					}
				});

	},

	uploadImage : function() {
		// 判断是否有选择上传文件
		var imgPath = $("#fileupload").val();
		if (imgPath == "") {
			alert("请选择上传图片！");
			return;
		}
		// 判断上传文件的后缀名
		var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
		if (strExtension != 'jpg' && strExtension != 'gif'
				&& strExtension != 'png' && strExtension != 'bmp') {
			alert("请选择图片文件");
			return;
		}
		var elementIds = ["flag"]; // flag为id、name属性名
		$.ajaxFileUpload({
					url : co.datapersons.url,
					type : 'post',
					data : {
						action : 'user.QueryManager',
						body : {
							file : imgPath
						}
					}, // 此参数非常严谨，写错一个引号都不行
					secureuri : false, // 一般设置为false
					fileElementId : 'file1', // 文件上传空间的id属性 <input
					// type="file" id="file"
					// name="file" />
					dataType : 'json', // 返回值类型 一般设置为json
					success : function(data, status) // 服务器成功响应处理函数
					{
						alert(data);
						$("#img1").attr("src", data.imgPath1);
						alert("你请求的Id是" + data.Id + "     " + "你请求的名字是:"
								+ data.name);
						if (typeof(data.error) != 'undefined') {
							if (data.error != '') {
								alert(data.error);
							} else {
								alert(data.msg);
							}
						}
					},
					error : function(data, status, e)// 服务器响应失败处理函数
					{
						alert(e);
					}
				});
	},
	UserConsumeDetail:function(){
		co.request({
			action:"user.UserConsumeDetail",
			body:{},
			success : function(data) {
				var rows = data.rows;
				
				var html = "";
				for(var i = 0 ; i< rows.length ; i++){
				    html += "<p class=\"borderBottom\">" + "<span>用户名称：<span"
					+ "	class=\"tll\">" + rows[i].name + "</span>" + "</span>"
					+ "<span>消费时间：<span" + "	class=\"tll\">"
					+ rows[0].paytime + "</span>" + "</span>"
					+ "<span>支付类型：<span class=\"tll\">" + rows[0].paytype
					+ "</span>" + "</span>"
					+ "<span>消费金额：<span class=\"tll\">" + rows[0].paynumber
					+ "</span>" + "</span>" + "<span>商户：<span"
					+ "	class=\"tll\">" + rows[0].shopname + "</span> "
					+ "</span>" + "</p>";
				}
				$("#DetailList").html(html);
			}
		})
	},
	ShopIncomeDetail:function(){
		var shopid = Util.getQueryStringByName("shopid");
		co.request({
			action:"user.ShopIncomeDetail",
			body:{shopid:shopid},
			success : function(data) {
				var result = data.result;
				for(var i =0 ; i< result.length ; i++){
					
				}
				$("#DetailList").html(html);
			}
		})
		
	}

}

$(function() {
	var htmlPos = window.location.href.indexOf(".html");

	var urlName = window.location.href.slice(window.location.href
					.lastIndexOf('/')
					+ 1, htmlPos);
	co.datapersons.manager.curPageName = urlName;
	

	var usertype = Util.getQueryStringByName("usertype");
	var refereeid = Util.getQueryStringByName("refereeid");

	$('#verifyCode').click(function() {
				co.request({
							action : "user.checkPhonenumber",
							body : {
								phonenumber : $("#mobileNum").val()
							},
							success : function(data) {
								console.log(data);
								var result = data;
								if (result != undefined || result != null) {
									if (result.status == "0000") {
										co.datapersons.manager.requestSMS();
									}
								}
								console.log(data);
							}
						});
			});

	$("#entryPointUser").click(function() {
				var refereeid = Util.getQueryStringByName("refereeid");
				var refereeURL = "";
				if (refereeid != null && refereeid != undefined
						&& refereeid != "") {
					refereeURL = "&refereeid=" + refereeid;
				}
				window.location.href = "consume/land.html?usertype=user"
						+ refereeURL;
			});

	$("#entryPointShop").click(function() {
				var refereeid = Util.getQueryStringByName("refereeid");
				var refereeURL = "";
				if (refereeid != null && refereeid != undefined
						&& refereeid != "") {
					refereeURL = "&refereeid=" + refereeid;
				}
				window.location.href = "consume/land.html?usertype=shop"
						+ refereeURL;
			});

	$("#registerEntry").click(function() {
		var refereeid = Util.getQueryStringByName("refereeid");
		var usertype = Util.getQueryStringByName("usertype");

		if (usertype != "user" && usertype != "shop") {
			// Show error message
			art.dialog({
						content : "没有用户类型，默认将作为个人用户注册！",
						cancelVal : 'Close',
						cancel : true
					});
			usertype = 'user';
		}

		var refereeURL = "";
		if (refereeid != null && refereeid != undefined && refereeid != "") {
			refereeURL = "&refereeid=" + refereeid;
		}
		window.location.href = "register.html?usertype=" + usertype
				+ refereeURL;
	});

	$('#register').click(function() {
		var refereeURL = "";
		var refereeid = Util.getQueryStringByName("refereeid");
		var usertype = Util.getQueryStringByName("usertype");
		if (refereeid != null && refereeid != undefined && refereeid != "") {
			refereeURL = refereeid;
		} else {
			refereeURL = "";
		}
		if (usertype == 'user') {
			var times = 0;
			times = judgInputInfor(judgPhoneNumber("#mobileNum"),
					"#promptNumber", times)
			times = judgInputInfor(judgPhoneCard("#verifyText"),
					"#verificationPrompt", times)
			times = judgInputInfor(judgInputText("#password"),
					"#promptPwdNumber", times)
			times = judgInputInfor(judgSame("#password", "#pwFirm"),
					"#promptPwdContrast", times)
			// var times=0;
			// times
			// =judgInputInfor(judgValNull("#historyPossword","请输入原始密码"),"#promptHis",times)
			// times
			// =judgInputInfor(judgInputText("#possword"),"#promptPwd",times)
			// times
			// =judgInputInfor(judgSame("#possword","#again"),"#promptAgain",times)
			// Click Pass
			if (times == 4) {
				co.datapersons.manager.submitPost("user", refereeURL);
			}

		} else if (usertype == 'shop') {
			co.datapersons.manager.submitPost("shop", refereeURL);
		} else {
			art.dialog({
						content : "没有用户类型，请重新由首页选择用户入口！",
						cancelVal : 'Close',
						cancel : true
					});
		}
	});

	$("#setShopInfor").click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#sign", "必填"), "#promptSign", times)
		times = judgInputInfor(judgValNull("#shopName", "必填"),
				"#promptShopName", times)
		times = judgInputInfor(judgValNull("#shopAddress", "必填"),
				"#promptAddress", times)
		times = judgInputInfor(
				judgAera("#userSeachcity", "#userSeachdistrict"),
				"#promptArea", times)

		times = judgInputInfor(judgValNull("#incorporator", "必填"),
				"#promptImport", times)
		times = judgInputInfor(judgValNull("#incorporatorNumber", "必填"),
				"#promptPhone", times)
		// Click Pass
		if (times == 6) {
			co.datapersons.manager.SetShopInfor();
		}

	});

	$("#changePwd").click(function() {
				co.datapersons.manager.changePwd();
			});

	$("#forget").click(function() {
				co.datapersons.manager.toForgetPwd();
			});

	$("#return").click(function() {
				co.datapersons.manager.returnURL();
			})

	$("#turnToCode").click(function() {
		var times = 0;
		times = judgInputInfor(judgPhoneNumber("#mobileNum"), "#promptNumber",
				times)
		times = judgInputInfor(judgPhoneCard("#verificationCard"),
				"#promptCard", times)
		// var times=0;
		// times
		// =judgInputInfor(judgValNull("#historyPossword","请输入原始密码"),"#promptHis",times)
		// times =judgInputInfor(judgInputText("#possword"),"#promptPwd",times)
		// times
		// =judgInputInfor(judgSame("#possword","#again"),"#promptAgain",times)
		// Click Pass
		if (times == 2) {
			co.datapersons.manager.turnToCode();
		}

	});

	$('#loginBtn').click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#mobileNum", "用户名不得为空"),
				"#promptUser", times);
		times = judgInputInfor(judgValNull("#password", "密码不得为空"),
				"#promptPassword", times);
		if (times == 2) {
			if (usertype == "shop") {
				co.datapersons.manager.login("shop");
			}
			if (usertype == "user") {
				co.datapersons.manager.login("user");
			}
		}
	});

	$('#addAgentBtn').click(function() {
		var times = 0;
		times = judgInputInfor(
				judgAera("#userSeachcity", "#userSeachdistrict"),
				"#promptArea", times)
		// Click Pass
		if (times == 1) {			
			var userSeachprov = $('#userSeachprov').val();
			var userSeachcity = $('#userSeachcity').val();
			var userSeachdistrict = $('#userSeachdistrict').val();
			var areaCode = userSeachprov + "-" + userSeachcity + "-"
			+ userSeachdistrict;
			co.request({
				action:"user.CheckProxy",
				body:{area:areaCode},
				success:function(data){
					if(data.status == "9999"){
						$("#showProxyMessage").html("<div style=\"color:#DC143C\">请完善您的个人信息再次申请代理！</div>");
					}else if(data.value > 0){
						$("#showProxyMessage").html("<div style=\"color:#DC143C\">您已提交申请！</div>");
					}else{
						$("#showProxyMessage").html("");
						co.request({
							action:"user.ApplyProxy",
							body:{area:areaCode},
							success:function(data){
								if(data.status =="0000"){
									art.dialog({
										content : "申请已提交，请等待审核！",
										cancelVal : 'Close',
										cancel : true
									})
								}
								if (data.status =="9999"){
									art.dialog({
										content : "提交失败，请稍后重试！",
										cancelVal : 'Close',
										cancel : true
									})
								}
							}
						})
					}
				}
			});
			
		}
	});

	$('#managerloginBtn').click(function() {
				co.datapersons.manager.managerloginBtn();
			});
	
	$("#SetPayInfor").click(function(){
		var times = 0;
		times = judgInputInfor(judgValNull("#zhifubao", "必填"), "#promptZhifubao",
				times)
		times = judgInputInfor(judgValNull("#weixin", "必填"),
				"#promptWeixin", times)
		if (times == 2) {
			co.datapersons.manager.SetPayInfor();
		}
	})

	$('#updateUserBtn').click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#userName", "必填"), "#promptName",
				times)
		times = judgInputInfor(judgValNull("#identityCard", "必填"),
				"#promptCard", times)
		times = judgInputInfor(
				judgAera("#userSeachcity", "#userSeachdistrict"),
				"#promptArea", times)
		times = judgInputInfor(judgValNull("#bankCardICBC", "必填"),
				"#promptICBC", times)
		times = judgInputInfor(judgValNull("#mail", "必填"), "#promptEmail",
				times)
		// Click Pass
		if (times == 5) {
			co.datapersons.manager.updateUser();
		}

	});

	$("#updateShopBtn").click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#sign", "必填"), "#promptSign", times)
		times = judgInputInfor(judgValNull("#shopName", "必填"),
				"#promptShopName", times)
		times = judgInputInfor(judgValNull("#shopAddress", "必填"),
				"#promptAddress", times)
		times = judgInputInfor(
				judgAera("#shopSeachcity", "#shopSeachdistrict"),
				"#promptShopAddress", times)
		// times
		// =judgInputInfor(judgValNull("#organizeNumber","必填"),"#promptOrgNumber",times)
		times = judgInputInfor(judgValNull("#incorporator", "必填"),
				"#promptImport", times)
		times = judgInputInfor(judgValNull("#incorporatorNumber", "必填"),
				"#promptImportNumber", times)
		times = judgInputInfor(judgValNull("#userName", "必填"),
				"#promptUserName", times)
		times = judgInputInfor(judgValNull("#identityCard", "必填"),
				"#promptIdentityCard", times)
		times = judgInputInfor(
				judgAera("#userSeachcity", "#userSeachdistrict"),
				"#promptUserAddress", times)
		times = judgInputInfor(judgValNull("#zhifubao", "必填"),
				"#promptZhifubao", times)
		times = judgInputInfor(judgValNull("#bankCardICBC", "必填"),
				"#promptICBC", times)
		times = judgInputInfor(judgValNull("#mail", "必填"), "#promptEmail",
				times)
		// Click Pass
		if (times == 12) {
			co.datapersons.manager.InsertShopInfor();
			co.datapersons.manager.InsertUserInfor();
		}

	});

	$('#insertShopInfor').click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#sign", "必填"), "#promptSign", times)
		times = judgInputInfor(judgValNull("#shopName", "必填"),
				"#promptShopName", times)
		times = judgInputInfor(judgValNull("#shopAddress", "必填"),
				"#promptAddress", times)
		times = judgInputInfor(
				judgAera("#userSeachcity", "#userSeachdistrict"),
				"#promptArea", times)

		times = judgInputInfor(judgValNull("#incorporator", "必填"),
				"#promptImport", times)
		times = judgInputInfor(judgValNull("#incorporatorNumber", "必填"),
				"#promptPhone", times)
		times = judgInputInfor(judgValNull("#orgNumber", "必填"),
				"#promptOrgNumber", times)
		times = judgInputInfor(judgValNull("#shopType", "必填"),
				"#promptShopType", times)
		// Click Pass
		if (times == 8) {
			co.datapersons.manager.InsertShopInfor();
		}
	});
	
	if (urlName.indexOf("register") < 0 && urlName.indexOf("index") < 0
			&& urlName.indexOf("land") < 0 && urlName.indexOf("login") < 0
			&& urlName.indexOf("find") < 0) {
		co.datapersons.manager.pageLoad();
	}

	if (urlName.indexOf("register") >= 0) {
		co.datapersons.manager.getRecordCount(usertype);
	}

	$('#payBtn').click(function() {
				co.datapersons.manager.pay();
			});

	if (urlName == "addPayInfor") {
		co.datapersons.manager.loadShopPage();
	}

	// $('#wxpayBtn').click(function(){
	// co.datapersons.manager.qrcode();
	// wx.chooseWXPay({
	// timestamp: 0, //
	// 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	// nonceStr: '', // 支付签名随机串，不长于 32 位
	// package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	// signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	// paySign: '', // 支付签名
	// success: function (res) {
	// // 支付成功后的回调函数
	// var s = res;
	// }
	// });

	// WeixinJSBridge.invoke(
	// 'getBrandWCPayRequest',
	// {
	// //请注意，这个地方放Demo.java中生成的package},
	// },
	// function(res){
	// //支付成功或失败前台判断
	// if(res.err_msg=='get_brand_wcpay_request:ok'){
	// alert('恭喜您，支付成功!');
	// }else{
	// alert('支付失败');
	// }
	// });
	// });

	$("#nav01").click(function() {
				var userType = co.datapersons.manager.usertype;
				if (userType == "user") {
					window.location.href = "navUserInfor.html";
				} else {
					window.location.href = "navShopInfor.html";
				}
			});

	$("#nav02").click(function() {
				window.location.href = "basalData.html";
			});
	$("#nav03").click(function() {
				window.location.href = "line.html";
			});
	$("#nav04").click(function() {
				window.location.href = "navRecommend.html";
			});
	$("#nav05").click(function() {
				window.location.href = "navAgent.html";
			});

	$("#navShop").click(function() {
				window.location.href = "navShopsInfor.html";
			});

	$("#nav06").click(function() {
				window.location.href = "navShopManage.html";
			});
	$("#nav07").click(function() {
				window.location.href = "navTransfer.html";
			});
	$("#nav08").click(function() {
				window.location.href = "newinfor.html";
			});
	$("#nav09").click(function() {
				window.location.href = "help.html";
			});
	$("#nav10").click(function() {
				window.location.href = "servicepeoson.html";
			});
	$("#nav11").click(function() {
				co.datapersons.manager.logout();
			});

	$(".dHeight").click(function() {
				window.location.href = "goinShopping.html";
			})

	$("#enterPay").click(function() {
		var times = 0;
		times = judgInputInfor(judgValNull("#consumerid", "必填"),
				"#promptUserId", times)
		times = judgInputInfor(judgValNull("#total_fee", "必填"),
				"#promptPayNumber", times)
		// Click Pass
		if (times == 2) {
			$("#postForm").submit();
		}
			// 还需要选择商店
			// 或者先选择商店进行添加

	});

	$("#changePwdBtn").click(function() {
		var times = 0;
		times = judgInputInfor(judgInputText("#possword"), "#promptPwd", times)
		times = judgInputInfor(judgSame("#possword", "#again"), "#promptAgain",
				times)
		// Click Pass
		if (times == 2) {
			alert("传输数据")
		}

	});

	$("#userperfectAdd").click(function() {
				co.datapersons.manager.userperfectAdd();
			});
	$(".endMenu .button").click();

	if ($('#projectfile').length > 0) {
		$('#projectfile').uploadify({
			'swf' : '../js/uploadify/uploadify.swf',
			'uploader' : co.datapersons.url + "?action=User.UploadFile",
			'fileTypeExts':'*.jpg;*.png',
			'fileTypeDesc':'选择图片',
			
			'formData' : {
				'shopid' : '555'
			},
			'width' : 120,
			'onQueueComplete' : function(queueData) {
				alert(queueData.uploadsSuccessful
						+ ' files were successfully uploaded.');
			}
		});
	}

	$("#searchShopList").click(function() {
				co.datapersons.manager.shopListSearch();
			});

	$("#shoppingBtn").click(function() {
				var shopid = "1";
				window.location.href = "addPayInfor.html?shopid=" + shopid;
			});
	// $("#btnUpload").click(function() {
	// co.datapersons.manager.uploadImage();
	// });
	if (urlName == "shopPicture") {
		$('#fileupload').uploadify({
			'swf' : '../js/uploadify/uploadify.swf',
			'uploader' : co.datapersons.url
					+ "?action=user.QueryManager&shopid=1",
			'buttonText' : '选择文件...',
			'fileTypeExts':'*.jpg;*.png',
			'fileSizeLimit':'500KB',
			'fileTypeDesc':'选择图片',
			'formData' : {
				'action' : '',
				'shopid' : ''
			},			
			'method' : 'get',
			'auto' : false,
			onSelect : function(file) {

			},
			onUploadSuccess : function(file, data, response) {
				art.dialog({
					content : "图片上传成功！",
					cancelVal : 'Close',
					cancel : true
				});
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				art.dialog({
					content : "图片上传失败。请稍后再试！",
					cancelVal : 'Close',
					cancel : true
				});
			}
		});
	}

	$("#uploadBtn").click(function() {
		var shopid = Util.getQueryStringByName("shopid");
		var d = co.datapersons.buildRequestParam({
				action : "file.SaveImage",
				body : {
					shopid : shopid
				}
		});
		var params = $.toJSON(d);
			$('#fileupload').uploadify('settings', 'formData', {
				request : params
			});
			$('#fileupload').uploadify('upload', '*');

		});
		
	$("#addPicture").click(function(){
		var shopid = Util.getQueryStringByName("shopid");
		window.location.href = "shopPicture.html?shopid="+shopid;
	});

});