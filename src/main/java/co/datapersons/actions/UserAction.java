package co.datapersons.actions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator; 
import org.apache.commons.lang.StringUtils;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.api.ApplicationContext;
import co.datapersons.jdbc.JdbcDatabaseService;

public class UserAction extends BaseAction
{
	
	private static Logger logger = Logger.getLogger(UserAction.class); 
	
	@Validations(
	          requiredFields = { 
	        		  @RequiredFieldValidator(fieldPath = "phonenumber", message = "@phonenumber_BLANK"),
	        		  @RequiredFieldValidator(fieldPath = "password", message = "@PASSWORD_BLANK")
			  }
//	          ,
//			  customValidators = {
//	        		  @CustomValidator(fieldPath="user.login",type="AuthorityValidator",message="@AUTH_FAILED",parameters = {@ValidationParameter(name="auth",value="t>=0")})
//	          }
	)
	//µÇÂ¼
	public void login(JSONObject input,JSONObject output){
		//1, get parameters for this logic
		String phonenumber = this.getInput(input, "phonenumber");
		String password = this.getInput(input, "password");		
		password = MD5(password);
		
		//2, create sql parameter
		String[] queryParams = new String[]{"login",phonenumber};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		
		boolean login_result = false;			
		String usertype  = StringUtils.EMPTY;
		String id  = StringUtils.EMPTY;
		String userid  = StringUtils.EMPTY;
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
//				id =  jsonResult.getString("userid");
				userid = jsonResult.getString("userid");
				String psw = jsonResult.getString("password");
				phonenumber = jsonResult.getString("phonenumber");
				usertype = jsonResult.getString("usertype");
				login_result = password.equals(psw);
			}
		}
		
		if(login_result){
			output.accumulate("status", "0000");
			queryParams = new String[]{"QueryUserInfoByUserId",userid};
			r = jdbcService.doService(queryParams);
			JSONArray result = (JSONArray)r;
			JSONObject jsonResult = result.getJSONObject(0);
			String type = jsonResult.getString("usertype");
			String curid = jsonResult.getString("userid");
			String sessionid = UUID.randomUUID().toString().replaceAll("-", "");			
			this.getSession().setAttribute("phonenumber", phonenumber);
			this.getSession().setAttribute("sessionid", sessionid);
			this.getSession().setAttribute("usertype", usertype);		
			this.getSession().setAttribute("userid", curid);	
			JSONObject userInforesult = new JSONObject();

			userInforesult.put("p", phonenumber);
			userInforesult.put("s", sessionid);
			userInforesult.put("t", type);
			userInforesult.put("i", userid);
			output.put("result", userInforesult);
			logger.info("login userid:"+ curid +" login success.");
		}else{
			output.put("status", "9997");
			JSONObject error = JSONBuilder.buildErrorByKey("LOGIN_FAILED");
			output.put("error", error);
			logger.info("login userid:"+ id +" login faild.");
		}
	}
	
	public void TurnToCode (JSONObject input,JSONObject output){
		String responsePhone =  this.getInput(input, "responsePhone");
		String phonenumber = this.getInput(input, "phonenumber");
		String verifyCode = this.getInput(input, "verifyCode");
		String responseCode = this.getInput(input, "responseCode");
		
		if((responseCode.equals(verifyCode)) && (responsePhone.equals(phonenumber))){
			String[] queryParams = new String[]{"login",phonenumber};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);			
			
			JSONArray result = (JSONArray)r;
			JSONObject jsonResult = result.getJSONObject(0);
			String type = jsonResult.getString("usertype");
			String curid = jsonResult.getString("userid");
			String sessionid = UUID.randomUUID().toString().replaceAll("-", "");			
			this.getSession().setAttribute("phonenumber", phonenumber);
			this.getSession().setAttribute("sessionid", sessionid);
			this.getSession().setAttribute("usertype", "temp");	
			this.getSession().setAttribute("userid", curid);	
			JSONObject userInforesult = new JSONObject();

			userInforesult.put("p", phonenumber);
			userInforesult.put("s", sessionid);
			userInforesult.put("t", "temp");
			userInforesult.put("i", curid);
			output.put("result", userInforesult);
			logger.info("TurnToCode :"+ phonenumber +" reset password success.");
		}
		logger.info("TurnToCode :"+ phonenumber +" reset password faild.");
	}
	

	
	public void managerlogin(JSONObject input,JSONObject output){
		//1, get parameters for this logic
		
		String userid = this.getInput(input, "userid");
		String password = this.getInput(input, "password");		
		password = MD5(password);
		
		//2, create sql parameter
		String[] queryParams = new String[]{"managerlogin",userid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		
		boolean login_result = false;
		String userID  = StringUtils.EMPTY;	
		String usertype  = StringUtils.EMPTY;
		String id  = StringUtils.EMPTY;
		String phonenumber = StringUtils.EMPTY;
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				id =  jsonResult.getString("id");
				userID = jsonResult.getString("userid");
				String psw = jsonResult.getString("password");
				phonenumber = jsonResult.getString("phonenumber");
				usertype = jsonResult.getString("usertype");
				login_result = password.equals(psw);
			}
		}
		
		if(login_result){
			output.accumulate("status", "0000");
			queryParams = new String[]{"QueryManagerInfoByUserId",userid};
			r = jdbcService.doService(queryParams);
			JSONArray result = (JSONArray)r;
			JSONObject jsonResult = result.getJSONObject(0);
			String type = jsonResult.getString("usertype");
			String curid = jsonResult.getString("id");
			String curuserid = jsonResult.getString("userid");
			String sessionid = UUID.randomUUID().toString().replaceAll("-", "");
			this.getSession().setAttribute("userid", curuserid);
			this.getSession().setAttribute("phonenumber", phonenumber);
			this.getSession().setAttribute("sessionid", sessionid);
			this.getSession().setAttribute("usertype", type);		
			this.getSession().setAttribute("id", curid);	
			JSONObject userInforesult = new JSONObject();
			logger.info("Write session userid= "+ userid);  
			userInforesult.put("u", curuserid);
			userInforesult.put("p", phonenumber);
			userInforesult.put("s", sessionid);
			userInforesult.put("t", type);
			userInforesult.put("i", id);
			output.put("result", userInforesult);
			logger.info("ManagerLogin :"+ userid +" login success.");
		}else{
			output.put("status", "9997");
			JSONObject error = JSONBuilder.buildErrorByKey("LOGIN_FAILED");
			output.put("error", error);
			logger.info("ManagerLogin :"+ userid +" login faild.");
		}
	}
	
	public void GetUserConsumeByUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetUserConsumeByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetDoingQueueConsume(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetDoingQueueConsume",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String lineupmoney =  jsonResult.getString("lineupmoney");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", lineupmoney);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetPrepareQueueConsume(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetPrepareQueueConsume",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String lineupmoney =  jsonResult.getString("lineupmoney");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", lineupmoney);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetRecommendConsumeWithUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetRecommendConsumeWithUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetRecommendUserCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetRecommendUserCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String COUNT =  jsonResult.getString("count");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", COUNT);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetRecommendShopCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetRecommendShopCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String COUNT =  jsonResult.getString("count");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", COUNT);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}	
	}
	
	public void GetUserMonthConsumeByUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetUserMonthConsumeByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
		
	}
	
	public void GetPopupQueueCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetPopupQueueCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void userperfectAdd(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();
			String weixin = this.getInput(input, "weixin");
			String zhifubao = this.getInput(input, "zhifubao");					
			String[] queryParams = new String[]{"SetUserperfectAdd",weixin,zhifubao,userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){						
				output.accumulate("status", "0000");				
			}
			
		}
	}
	
	public void GetDoingQueueCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetDoingQueueCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void GetPrepareQueueCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetPrepareQueueCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	//=================================
	//Modified function by Joseph Lee 
	//2016-04-28
	public void GetReturnFee(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			String[] queryParams = new String[]{"GetRecommendShopReturnFeeByUserid",userid};
			DecimalFormat   df = new DecimalFormat("######0.00");   
			Object r = jdbcService.doService(queryParams);
			Double returnFee = 0.0;
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String curReturnFee =  jsonResult.getString("returnfee");					
					
					returnFee += Double.parseDouble(curReturnFee);
					
				}
			}
			
			queryParams = new String[]{"GetUserReturnFeeByUserid",userid};
			
			r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String curReturnFee =  jsonResult.getString("returnfee");					
					
					returnFee += Double.parseDouble(curReturnFee);
					
				}
			}
			
			queryParams = new String[]{"GetRecommendUserReturnFeeByUserid",userid};
			r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String curReturnFee =  jsonResult.getString("returnfee");					
					
					returnFee += Double.parseDouble(curReturnFee);
					
				}
			}
			
			queryParams = new String[]{"GetProxyReturnFeeByUserid",userid};
			r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String curReturnFee =  jsonResult.getString("returnfee");					
					
					returnFee += Double.parseDouble(curReturnFee);
					
				}
			}
			
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("value", df.format(returnFee).toString());
				
			output.put("result", userInforesult);
			output.accumulate("status", "0000");
			
		}
	}
	
	public void GetRecommendCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetRecommendCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void GetProxyAreaCount(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetProxyAreaCount",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
//	GetProxyConsume
	public void GetProxyConsume(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetProxyConsume",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
//	GetProxyMonthConsume
	public void GetProxyMonthConsume(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"GetProxyMonthConsume",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumbermonth =  jsonResult.getString("paynumbermonth");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumbermonth);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	
	public void GetRecommendConsume(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetRecommendConsume",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void GetCurUserRefereeURL(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();						
					
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("userid", userid);
						
			output.put("result", userInforesult);
			output.accumulate("status", "0000");
		}
	}
	
	public void GetShopCountByUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetShopCountByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", count);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	public void GetShopIncomeByUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetShopIncomeByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String paynumber =  jsonResult.getString("paynumber");						
					
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", paynumber);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void QueryShopIncomeDetailByUserid (JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid =  this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryShopIncomeDetailByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){			
				JSONArray result = (JSONArray)r;
				if(result.size()>0){				
					String recordCount =  String.valueOf(result.size());
					
					output.put("total", recordCount);				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}else{
					output.put("total", "0");				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}
					
			}
		}
	}
	
	public void QueryShopIncomeTodayByUserid (JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid =  this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryShopIncomeTodayByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){			
				JSONArray result = (JSONArray)r;
				if(result.size()>0){				
					String recordCount =  String.valueOf(result.size());
					
					output.put("total", recordCount);				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}else{
					output.put("total", "0");				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}
					
			}
		}
	}
	
	public void QueryShopListUndone(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid =  this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryShopListUndone",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){			
				JSONArray result = (JSONArray)r;
				if(result.size()>0){				
					String recordCount =  String.valueOf(result.size());
					
					output.put("total", recordCount);				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}else{
					output.put("total", "0");				
					output.put("rows", result);
					output.accumulate("status", "0000");
				}
					
			}
		}
	}
	
	public void QueryShopSumToday(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid =  this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryShopSumToday",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){			
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String count =  jsonResult.getString("count");						
					String paynumber =  jsonResult.getString("paynumber");	
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("count", count);
					userInforesult.put("paynumber", paynumber);	
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
					
			}
		}
	}
	
	public void QueryShopInfor(JSONObject input,JSONObject output){
		String shopid =  this.getInput(input, "shopid");
					
		String[] queryParams = new String[]{"QueryShopInfor",shopid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
					
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}else{
				output.put("total", "0");				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
					
		}
		
	}
	
	
	//end Modify
	//====================================
	
	public void GetPayintegralByUserid (JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();;
			String[] queryParams = new String[]{"GetPayintegralByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String payintegral =  jsonResult.getString("payintegral");	
						
						
					JSONObject userInforesult = new JSONObject();				
					userInforesult.put("value", payintegral);
						
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}
			
		}
	}
	
	public void managerVerify(JSONObject input,JSONObject output){
		String userid  ="";
		String phonenumber = "";
		String sessionid = "";
		String type = "";
		String id = "";
		JSONObject userInforesult = new JSONObject();	
		
		if(this.getSession().getAttribute("userid") != null){
			userid = this.getSession().getAttribute("phonenumber").toString();
			phonenumber = this.getSession().getAttribute("phonenumber").toString();
			sessionid = this.getSession().getAttribute("sessionid").toString();
			type = this.getSession().getAttribute("usertype").toString();
			id = this.getSession().getAttribute("id").toString();
			
			userInforesult.put("u", userid);
			userInforesult.put("p", phonenumber);
			userInforesult.put("s", sessionid);
			userInforesult.put("t", type);
			userInforesult.put("i", id);
			logger.info("ManagerVerify :"+ userid +" session valid.");
		}else{
			userInforesult.put("u", "");
			userInforesult.put("p", "");
			userInforesult.put("s", "");
			userInforesult.put("t", "");
			userInforesult.put("i", "");
			logger.info("ManagerVerify : session invalid.");			
		}		
		
		output.put("result", userInforesult);
	}
	
	//µÃµ½µÇÂ¼ÕßÐÅÏ¢
	public void getLoginData(JSONObject input,JSONObject output){
		String phonenumber = "";
		String sessionid = "";
		String type = "";
		String userid = "";
		JSONObject userInforesult = new JSONObject();	
		
		if(this.getSession().getAttribute("phonenumber") != null){
			phonenumber = this.getSession().getAttribute("phonenumber").toString();
			sessionid = this.getSession().getAttribute("sessionid").toString();
			type = this.getSession().getAttribute("usertype").toString();
			userid = this.getSession().getAttribute("userid").toString();
				
			userInforesult.put("p", phonenumber);
			userInforesult.put("s", sessionid);
			userInforesult.put("t", type);
			userInforesult.put("i", userid);
			logger.info("ManagerVerify :"+ phonenumber +" session valid.");
		}else{
			userInforesult.put("p", "");
			userInforesult.put("s", "");
			userInforesult.put("t", "");
			userInforesult.put("i", "");
			logger.info("ManagerVerify :  session valid.");
		}		
		output.put("result", userInforesult);
		
	}
	
	public void loginCountAdd (JSONObject input,JSONObject output){
		String phonenumber = "";
		String sessionid = "";
		String type = "";
		String userid = "";
		JSONObject userInforesult = new JSONObject();	
		
		if(this.getSession().getAttribute("phonenumber") != null){
			phonenumber = this.getSession().getAttribute("phonenumber").toString();
			sessionid = this.getSession().getAttribute("sessionid").toString();
			type = this.getSession().getAttribute("usertype").toString();
			userid = this.getSession().getAttribute("userid").toString();
			
			String[] queryParams = new String[]{"LoginCountAdd",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				output.put("message", "login count added.");
				output.put("status", "0000");				
			}else {
				output.put("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
				output.put("error", error);
			}			
		}		
	}
	//µÇ³ö
	public void logout(JSONObject input,JSONObject output){	
		if(this.getSession().getAttribute("userid").toString()!=""){
			this.getSession().removeAttribute("userid");
		}
			
		this.getSession().removeAttribute("phonenumber");
		this.getSession().removeAttribute("sessionid");
		this.getSession().removeAttribute("usertype");
		this.getSession().removeAttribute("userid");
		output.put("status", "0000");
	}
	
	
//	@Validations(
//	          requiredFields = { 
//	        		  @RequiredFieldValidator(fieldPath = "phonenumber", message = "@phonenumber_BLANK")
//			  }
//	)
	//¼ìÑéÓÃ»§ID
	public void checkPhonenumber(JSONObject input,JSONObject output){
		String phonenumber = this.getInput(input, "phonenumber");
		
		String[] queryParams = new String[]{"QueryUserInfoByMobile",phonenumber};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				output.put("status", "9997");
				JSONObject error = JSONBuilder.buildErrorByKey("Phonnumber_DUPLICATION");
				output.put("error", error);								
			}else {				
				output.put("status", "0000");
			}
		}else {
			output.put("status", "9999");
			JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
			output.put("error", error);
		}
		
	}
	
	@Validations(
		          requiredFields = { 
		        		  @RequiredFieldValidator(fieldPath = "phonenumber", message = "@phonenumber_BLANK"),
		        		  @RequiredFieldValidator(fieldPath = "password", message = "@PASSWORD_BLANK")
				  }
	)
	//×¢²áÓÃ»§
	public void register(JSONObject input,JSONObject output){
		//1, get parameters
		String phonenumber = this.getInput(input, "phonenumber");
		String password = this.getInput(input, "password");
		String usertype = this.getInput(input, "usertype");
		String refereeid = this.getInput(input,"refereeid");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registertime = df.format(new Date()).toString();
		password = MD5(password);
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		//
		String[] queryParams = new String[]{"GetMaxUserid"};
		Object r = jdbcService.doService(queryParams);
		int iMaxUserid = 0;
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String maxshowid =  jsonResult.getString("maxuserid");	
				iMaxUserid = Integer.parseInt(maxshowid);
				if (iMaxUserid  == 0){
					iMaxUserid = 1100010001;
				}else{
					iMaxUserid = iMaxUserid +1;
				}
				
			}
		}
		String sMaxUserid = String.valueOf(iMaxUserid);
		if( refereeid.equals("")){
			refereeid = "0";
		} 
		//2, create sql parameter
		queryParams = new String[]{"AddUser",phonenumber,usertype,password,registertime,refereeid,sMaxUserid};
		
		r = jdbcService.doService(queryParams);
		
		if(r!=null){
			int result = Integer.parseInt(r.toString());
			if(result<=0){
				//failed
				output.put("status", "9997");
				JSONObject error = JSONBuilder.buildErrorByKey("REGISTER_FAILED");
				output.put("error", error);
				logger.info("Register:  no result , register faild.");
			}else {
				//success
				System.out.println(result);
				output.put("usertype", usertype);
				output.accumulate("status", "0000");
				logger.info("Register:  register success.");
			}
		}else {
			//failed
			output.put("status", "9997");
			JSONObject error = JSONBuilder.buildErrorByKey("REGISTER_FAILED");
			output.put("error", error);
			logger.info("Register: result is null, register faild.");
		}
		
	}
	
	public void GetReturnInfor(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String[] queryParams = new String[]{"GetReturnAll"};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			String sumreduce = "";
			String reducetonumber = "";
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					sumreduce =  jsonResult.getString("sumreduce");	
				}
			}else{
				output.put("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
				output.put("error", error);
			}
			
			queryParams = new String[]{"GetReturnUserNumber"};
			r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					reducetonumber =  jsonResult.getString("reducetonumber");	
				}else{
					output.put("status", "9999");
					JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
					output.put("error", error);
				}				
			}
			output.put("status", "0000");
			output.put("sumreduce", sumreduce);
			output.put("reducetonumber", reducetonumber);
		}
	}
	
	public void InsertShopInfor(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String sign = this.getInput(input, "sign");
			String shopName = this.getInput(input, "shopName");
			String shopAddress = this.getInput(input, "shopAddress");
			String area = this.getInput(input, "area");
			String organizeNumber = this.getInput(input, "organizeNumber");
			String incorporator = this.getInput(input, "incorporator");
			String phone = this.getInput(input, "phone");
			String orgNumber = this.getInput(input, "orgNumber");
			String shopType = this.getInput(input, "shopType");
			
			String[] queryParams = new String[]{"InsertShopInfor",userid,shopName,shopAddress,area,organizeNumber,incorporator,phone,sign,orgNumber,shopType};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				output.accumulate("status", "0000");
			}else{
				output.put("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
				output.put("error", error);
			}
		}
	}
	
	public void SetShopInfor(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String shopid = this.getInput(input, "shopid");
			String sign = this.getInput(input, "sign");
			String shopName = this.getInput(input, "shopName");
			String shopAddress = this.getInput(input, "shopAddress");
			String area = this.getInput(input, "area");
			String organizeNumber = this.getInput(input, "organizeNumber");
			String incorporator = this.getInput(input, "incorporator");
			String phone = this.getInput(input, "phone");
			
			String[] queryParams = new String[]{"SetShopInfor",shopName,shopAddress,area,organizeNumber,incorporator,phone,sign,shopid ,userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				output.accumulate("status", "0000");
			}else{
				output.put("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
				output.put("error", error);
			}
		}
	}
	
	public void QueryShopInforByShopid(JSONObject input,JSONObject output){
		String shopid = this.getInput(input, "shopid");
		
		String[] queryParams = new String[]{"QueryShopInforByShopid",shopid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
					
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}else{
				output.put("total", "0");				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
					
		}
	}
	
	public void QueryNeedVerifyShop(JSONObject input,JSONObject output){
		String shopname = this.getInput(input, "shopname");
		
		String[] queryParams = new String[]{"QueryNeedVerifyShop","%"+shopname+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
					
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}else{
				output.put("total", "0");				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
					
		}
	}
	
	//²éÑ¯ÓÃ»§
	public void queryUser(JSONObject input,JSONObject output){
		
	}
	
	
	
	//ÐÞ¸ÄÃÜÂë
	public void updatePassword(JSONObject input,JSONObject output){
		
		String password = this.getInput(input, "password");
		String confirmPwd = this.getInput(input, "confirmPwd");		
		String phonenumber =  this.getSession().getAttribute("phonenumber").toString();
		String curid = this.getSession().getAttribute("userid").toString(); 
		
		if(password.equals(confirmPwd) ){
			password = MD5(password);
			String[] queryParams = new String[]{"UpdatePassword",password,curid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			
			if(r!=null)
			{
				int result = Integer.parseInt(r.toString());
				if(result>0){
					output.accumulate("status", "0000");
				}else {
					output.put("status", "9997");
					JSONObject error = JSONBuilder.buildErrorByKey("UPDATE_PASSWORD_FAIELD");
					output.put("error", error);
				}
			}else{
				output.put("status", "9997");
				JSONObject error = JSONBuilder.buildErrorByKey("UPDATE_PASSWORD_fAIELD");
				output.put("error", error);
			}
		}else{
			output.put("status", "9997");
			JSONObject error = JSONBuilder.buildErrorByKey("UPDATE_PASSWORD_FAIELD");
			output.put("error", error);
		}
		
		
	}
	//É¾³ýÓÃ»§
	public void deleteUser(JSONObject input,JSONObject output){
		String phonenumber = this.getInput(input, "phonenumber");
		
		String[] queryParams = new String[]{"DeleteUserById",phonenumber};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			output.accumulate("status", "0000");
		}else{
			output.put("status", "9999");
			JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
			output.put("error", error);
		}
	}
	//¸ü¸ÄÓÃ»§ÐÅÏ¢
	public void updateUserInfo(JSONObject input,JSONObject output){
		String phonenumber = this.getSession().getAttribute("phonenumber").toString();
		String idCard = this.getInput(input, "idCard");
		String username = this.getInput(input, "username");
		String userArea = this.getInput(input, "userArea");
		String ICBCCard = this.getInput(input, "ICBCCard");
		String zhifubao = this.getInput(input, "zhifubao");
		String weixin = this.getInput(input, "weixin");
		String mail = this.getInput(input, "mail");
		
		
		if("".equals(phonenumber)){
			output.put("status", "9999");
			JSONObject error = JSONBuilder.buildErrorByKey("phonenumber_BLANK");
			output.put("error", error);
		}
		String[] queryParams = new String[]{"UpdateUserInfo",idCard,username,userArea,ICBCCard,mail,zhifubao,weixin,phonenumber};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			output.accumulate("status", "0000");
		}else{
			output.put("status", "9999");
			JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
			output.put("error", error);
		}
	}
	public void QueryUserInfoByRegisterday(JSONObject input,JSONObject output){
		String registertime =  this.getInput(input, "registertime");
		String area =  this.getInput(input, "area");
		String[] queryParams = new String[]{"QueryUserInfoByRegisterday",registertime,"%"+area+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
				
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}		
		}
	}
	
	public void QueryProxy(JSONObject input,JSONObject output){
//		String registertime =  this.getInput(input, "area");
		String area =  this.getInput(input, "area");
		String[] queryParams = new String[]{"QueryProxy","%"+area+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
				
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}		
		}
	}
	
	public void QueryManager(JSONObject input,JSONObject output){
		String userid =  this.getInput(input, "userid");
		String[] queryParams = new String[]{"QueryManager","%"+userid+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
				
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}else{
				output.put("total", "0");				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
				
		}
	}
	
	public void GetManagerCount(JSONObject input,JSONObject output){
		String[] queryParams = new String[]{"GetManagerCount"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("count");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void GetTodayShopRegister(JSONObject input,JSONObject output){
		String date =  this.getInput(input, "date");	 
		String[] queryParams = new String[]{"GetTodayShopRegister",date};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("count");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void GetUnauditedCount(JSONObject input,JSONObject output){
		String[] queryParams = new String[]{"GetUnauditedCount"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("count");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void GetShopCount(JSONObject input,JSONObject output){
		String[] queryParams = new String[]{"GetShopCount"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("count");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void GetShopConsume(JSONObject input,JSONObject output){
		String[] queryParams = new String[]{"GetShopConsume"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("paynumber");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	
	
	//·µ»ØÓÃ»§ÐÅÏ¢
	public void getUserInfo(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();
		
			String[] queryParams = new String[]{"QueryUserInfoByUserId",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
									
					String registime = jsonResult.getString("registime");
					String identitycard =  jsonResult.getString("identitycard");	
					String smallletter =  jsonResult.getString("smallletter");
					String paytreasurable = jsonResult.getString("paytreasurable");
					String businessbank = jsonResult.getString("businessbank");
					String name = jsonResult.getString("name");	
					String perfecttime = jsonResult.getString("perfecttime");
					String refereeid = jsonResult.getString("refereeid");
					String landtime = jsonResult.getString("landtime");
					String lastlandtime = jsonResult.getString("lastlandtime");
					String sex = jsonResult.getString("sex");
					String age = jsonResult.getString("age");
					String birthday = jsonResult.getString("birthday");
					String cardattributive = jsonResult.getString("cardattributive");
					String usertype = jsonResult.getString("usertype");
					String phonenumber = jsonResult.getString("phonenumber");
					String phoneattributive = jsonResult.getString("phoneattributive");
					String bodyattributive = jsonResult.getString("bodyattributive");
					String mailaddress = jsonResult.getString("mailaddress");
					String usershare = jsonResult.getString("usershare");
					String shopshare = jsonResult.getString("shopshare");
					String payintegral = jsonResult.getString("payintegral");
					String distribution = jsonResult.getString("distribution");
					String shopid = jsonResult.getString("shopid");
					String paytime = jsonResult.getString("paytime");
					String paytotal = jsonResult.getString("paytotal");
					String balance = jsonResult.getString("balance");
					String paycount = jsonResult.getString("paycount");
					String refereecount = jsonResult.getString("refereecount");
					String shopname = jsonResult.getString("shopname");
					String paytimecalc = jsonResult.getString("paytimecalc");
					String paytype = jsonResult.getString("paytype");
									
					
					JSONObject userInforesult = new JSONObject();
					
					userInforesult.put("userid", userid);
					userInforesult.put("registime", registime);
					userInforesult.put("phonenumber", phonenumber);
					userInforesult.put("identitycard", identitycard);
					userInforesult.put("smallletter", smallletter);
					userInforesult.put("paytreasurable", paytreasurable);
					userInforesult.put("businessbank", businessbank);
					userInforesult.put("name", name);
					userInforesult.put("perfecttime", perfecttime);
					userInforesult.put("refereeid", refereeid);
					userInforesult.put("landtime", landtime);
					userInforesult.put("lastlandtime", lastlandtime);
					userInforesult.put("sex", sex);
					userInforesult.put("age", age);
					userInforesult.put("birthday", birthday);
					userInforesult.put("usertype", usertype);
					userInforesult.put("phoneattributive", phoneattributive);
					userInforesult.put("bodyattributive", bodyattributive);
					userInforesult.put("mailaddress", mailaddress);
					userInforesult.put("usershare", usershare);
					userInforesult.put("shopshare", shopshare);
					userInforesult.put("payintegral", payintegral);
					userInforesult.put("distribution", distribution);
					userInforesult.put("shopid", shopid);
					userInforesult.put("paytime", paytime);
					userInforesult.put("paytotal", paytotal);
					userInforesult.put("balance", balance);
					userInforesult.put("paycount", paycount);
					userInforesult.put("refereecount", refereecount);
					userInforesult.put("shopname", shopname);
					userInforesult.put("paytimecalc", paytimecalc);
					userInforesult.put("paytype", paytype);
					
					output.put("result", userInforesult);
					output.accumulate("status", "0000");
				}
			}else{
				output.put("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR");
				output.put("error", error);
			}
		}
	}
	//·µ»Ø¼ÇÂ¼×ÜÊý
	public void getRecordCount(JSONObject input,JSONObject output){
		String usertype =  this.getInput(input, "usertype");			
		
		String[] queryParams = new String[]{"getRecordCountByType",usertype};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String recordCount =  jsonResult.getString("recordcount");	
				
				
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("recordCount", recordCount);
				
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	//·µ»ØËùÓÐÓÃ»§×ÜÊý
	public void getUserCount(JSONObject input,JSONObject output){		
		String[] queryParams = new String[]{"getRecordCount"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String recordCount =  jsonResult.getString("recordcount");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("recordCount", recordCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void getSumConsumeByDay(JSONObject input,JSONObject output){	
		String betweenTime =  this.getInput(input, "between");	
		String endTime =  this.getInput(input, "end");	
		String[] queryParams = new String[]{"getSumConsumeByDay",betweenTime,endTime};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String consumeamount =  jsonResult.getString("consumeamount");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("SumConsume", consumeamount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void getSumConsume(JSONObject input,JSONObject output){		
		String[] queryParams = new String[]{"getSumConsume"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String consumeamount =  jsonResult.getString("consumeamount");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("SumConsume", consumeamount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	
	public void getRegisterCountByDay(JSONObject input,JSONObject output){
		String betweenTime =  this.getInput(input, "between");	
		String endTime =  this.getInput(input, "end");	
		String[] queryParams = new String[]{"getRegisterCountByDay",betweenTime,endTime};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("usercount");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("UserCount", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}	
	
	public void  GetRegisterByArea(JSONObject input,JSONObject output){
		String area =  this.getInput(input, "area");	
			
		String[] queryParams = new String[]{"GetRegisterByArea","%"+area+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("count");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	public void  GetConsumeByArea(JSONObject input,JSONObject output){
		String area =  this.getInput(input, "area");	
			
		String[] queryParams = new String[]{"GetConsumeByArea","%"+area+"%"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String userCount =  jsonResult.getString("paynumber");	
					
					
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", userCount);
					
				output.put("result", userInforesult);
				output.accumulate("status", "0000");
			}
		}
	}
	
	//ÓÃ»§×ªÕË¼ÇÂ¼
	public void getPay(JSONObject input,JSONObject output){
		
	}
	
	//ÓÃ»§×ªÕË
	public void payToZFB(JSONObject input,JSONObject output){
		
	}
	
	//Ã¿ÈÕÊÕÈëÏêÏ¸
	public void getRand(){
			
	}
	
	//ÓÃ»§ÍÆ¼öÃûµ¥
	public void getRandSpend(JSONObject input,JSONObject output){
//		getRefereeLst
		String[] queryParams = new String[]{"getRefereeList"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
				
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
		}
	}
		
	//ÓÃ»§Ïû·Ñ¼ÇÂ¼
	public void getRecommend(JSONObject input,JSONObject output){
		String phonenumber = this.getInput(input, "phonenumber");
		String[] queryParams = new String[]{"getConsumeByUserid",phonenumber};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){				
				String recordCount =  String.valueOf(result.size());
				
				output.put("total", recordCount);				
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
		}
	}
	//ÓÃ»§ÒÑ·µÀû¼ÇÂ¼
	public void getReddle(JSONObject input,JSONObject output){
		
	}
	
	//ÓÃ»§Ïû·ÑÏêÏ¸
	public void getSpend(JSONObject input,JSONObject output){
		
	}
	//ÓÃ»§Î´½øÈëÅÅ¶Ó¼ÇÂ¼
	public void getStandby(JSONObject input,JSONObject output){
		
	}
	
	public void getTodayRegister(JSONObject input,JSONObject output){
		String today = this.getInput(input, "today");
		String[] queryParams = new String[]{"GetTodayRegister",today};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		JSONArray result = (JSONArray)r;
		if(result.size()>0){
			JSONObject jsonResult = result.getJSONObject(0);
			String userCount =  jsonResult.getString("count");
			
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("value", userCount);
			output.put("result", userInforesult);
			output.accumulate("status", "0000");			
		}
	}
	
	public void getTodayConsume(JSONObject input,JSONObject output){
		String today = this.getInput(input, "today");
		String[] queryParams = new String[]{"GetTodayConsume",today};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		JSONArray result = (JSONArray)r;
		if(result.size()>0){
			JSONObject jsonResult = result.getJSONObject(0);
			String userCount =  jsonResult.getString("paynumber");
			
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("value", userCount);
			output.put("result", userInforesult);
			output.accumulate("status", "0000");			
		}
	}
	
	public void getCountryConsume(JSONObject input,JSONObject output){		
		String[] queryParams = new String[]{"GetCountryConsume"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		JSONArray result = (JSONArray)r;
		if(result.size()>0){
			JSONObject jsonResult = result.getJSONObject(0);
			String userCount =  jsonResult.getString("paynumber");
			
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("value", userCount);
			output.put("result", userInforesult);
			output.accumulate("status", "0000");			
		}
	}
	
	public void getCountryRegister(JSONObject input,JSONObject output){
		String[] queryParams = new String[]{"GetCountryRegister"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		JSONArray result = (JSONArray)r;
		if(result.size()>0){
			JSONObject jsonResult = result.getJSONObject(0);
			String userCount =  jsonResult.getString("count");
			
			JSONObject userInforesult = new JSONObject();				
			userInforesult.put("value", userCount);
			output.put("result", userInforesult);
			output.accumulate("status", "0000");			
		}
	}
	
	
	public void QueryShopsList(JSONObject input,JSONObject output){
		String shopname =  this.getInput(input, "shopname");
		String area =  this.getInput(input, "area");
		
		String[] queryParams = new String[]{"QueryShopsList","%"+shopname+"%",area};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
				
			output.put("rows", result);
			output.accumulate("status", "0000");
			
		}		
	}
	
	//ÓÃ»§ÖÐÐÄ
	public void getSuper(JSONObject input,JSONObject output){
		String phonenumber =  this.getInput(input, "phonenumber");			
		
		String[] queryParams = new String[]{"getSuper"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){	
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
		}					
	}
	
	//ÓÃ»§´ý·µÀû¼ÇÂ¼
	public void getWait(JSONObject input,JSONObject output){
							
	}
	//ÓÃ»§ÊÕÈëÏêÏ¸
	public void getIncome(JSONObject input,JSONObject output){
		
	}
	
	
	public void UploadFile(JSONObject input,JSONObject output){
		String shopid =  this.getInput(input, "shopid");	
		String fileName = "";
		fileName = "shopid-"+shopid +".png";
	}
	
	public void checkLevante(JSONObject input,JSONObject output){		
		String[] queryParams = new String[]{"checkLevante"};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){			
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				String daydiff =  jsonResult.getString("daydiff");
				
				JSONObject userInforesult = new JSONObject();				
				userInforesult.put("value", daydiff);
				output.put("result", userInforesult);
				output.accumulate("status", "0000");			
			}			
		}
	}
	/*
	 * new function added
	 * Author @lijun
	 * 2016-05-27  
	 */
	//Query user consume list by userid  
	public void GetUserConsumeListByUserid(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){
			String userid = this.getSession().getAttribute("userid").toString();	
			String[] queryParams = new String[]{"GetUserConsumeListByUserid",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){	
					output.put("rows", result);
					output.accumulate("status", "0000");
				}
			}
		}
	}
	//Query shop income list by shopid 
	public void GetShopIncomeListByShopid(JSONObject input,JSONObject output){
		String shopid =  this.getInput(input, "shopid");	
		
		String[] queryParams = new String[]{"GetShopIncomeListByShopid",shopid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){
			JSONArray result = (JSONArray)r;
			if(result.size()>0){	
				output.put("rows", result);
				output.accumulate("status", "0000");
			}
		}
		
	}
	
	//VerifyShopByManager 
	public void VerifyShopByManager(JSONObject input,JSONObject output){
//		String shopid =  this.getInput(input, "id");	
		String request = this.getInput(input, "updated");
		JSONObject object = new JSONObject();
		object = JSONObject.fromObject(request);
		Object array = object.get("updated");
		JSONArray jsonArray = JSONArray.fromObject(array);
		for (int i = 0 ; i < jsonArray.size() ; i++ ){
			String shopid = jsonArray.getJSONObject(i).getString("id");		
			String reducepoint = jsonArray.getJSONObject(i).getString("reducepoint");
			String verifystatus =jsonArray.getJSONObject(i).getString("verifystatus");
			
			String[] queryParams = new String[]{"VerifyShopByManager",reducepoint,verifystatus,shopid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r==null){
				output.accumulate("status", "9999");
			}
			queryParams = new String[]{"SetUsertoShopByShopid",shopid};
			r = jdbcService.doService(queryParams);
			if(r==null){
				output.accumulate("status", "9999");
			}
		}
		
		output.accumulate("status", "0000");
		
	}
	
	public void UserConsumeDetail (JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"UserConsumeDetail",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
					
				output.put("rows", result);
				output.accumulate("status", "0000");
				
			}
		}
	}
	
	public void QueryProxyDetailInfor (JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryProxyDetailInfor",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
					
				output.put("rows", result);
				output.accumulate("status", "0000");
				
			}
		}
	}
	
	public void CheckProxy(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String areaCode = this.getInput(input, "area");
			String userid = this.getSession().getAttribute("userid").toString() ;
			String[] queryParams = new String[]{"QueryUserInfoByUserId",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r != null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String name =  jsonResult.getString("name");
					String phonenumber =  jsonResult.getString("phonenumber");
					String identitycard =  jsonResult.getString("identitycard");
					
					if (name.equals("") || phonenumber.equals("") || identitycard.equals("")){
						output.accumulate("status", "9999");
					}
				}
			}
			
			
			queryParams = new String[]{"CheckProxy",areaCode};
			
			r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
				if(result.size()>0){
					JSONObject jsonResult = result.getJSONObject(0);
					String number =  jsonResult.getString("number");
					output.put("value", number);
					
				}
			}
		}
	}
	
	public void  ApplyProxy (JSONObject input,JSONObject output){
		String area = this.getInput(input, "area");
		String proxyshare = "0.01";
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"ApplyProxy",userid,area,proxyshare};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
					output.accumulate("status", "0000");
				
			}
		}
	}
	
	public void  QueryAreaProxyInfor(JSONObject input,JSONObject output){
		if(this.getSession().getAttribute("userid") != null){			
			String userid = this.getSession().getAttribute("userid").toString();
			String[] queryParams = new String[]{"QueryAreaProxyInfor",userid};
			JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
			Object r = jdbcService.doService(queryParams);
			if(r!=null){
				JSONArray result = (JSONArray)r;
					
				output.put("rows", result);
				output.accumulate("status", "0000");
				
			}
		}
	}
	
	
	public void GenQueueID(JSONObject input,JSONObject output){
		String rtn ="";
	}
	
}
