package co.datapersons.validation.validators;

import java.util.ResourceBundle;

import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import co.datapersons.validation.ValidationException;
import co.datapersons.actions.Action;
import co.datapersons.api.ApplicationContext;

public class AuthorityValidator extends CustomValidatorSupport
{

	protected JexlContext context = JexlHelper.createContext();  
	
	@SuppressWarnings("unchecked")
	public void validate(Action targetAction,Object paramObject) throws ValidationException
	{
		if(!this.getParams().containsKey("auth")){
			return;
		}
		//TODO get user type from httpsession
		Object obj = targetAction.getSession().getAttribute("userType");
		if(obj==null||StringUtils.isEmpty(obj.toString())){
			String msg = ApplicationContext.getInstance().getMsg("AUTH_ERROR");
			throw new ValidationException(msg);
		}
		
		try
		{
			context.getVars().put("t", obj.toString());  
			String auth = this.getParams().get("auth").toString();
			Expression e = ExpressionFactory.createExpression(auth);  
			obj = e.evaluate(context);
			boolean result = BooleanUtils.toBoolean(obj.toString(),"true","false");
			if(!result){
				throw new ValidationException(this.getMessage());
			}
		}
		catch (Exception e1)
		{
			String msg = ApplicationContext.getInstance().getMsg("AUTH_ERROR");
			throw new ValidationException(msg);
		}  
		
	}

}
