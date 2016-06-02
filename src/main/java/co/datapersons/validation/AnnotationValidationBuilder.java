/**
 ** Created on 31 Mar 2014 TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package co.datapersons.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import co.datapersons.validation.annotation.CustomValidator;
import co.datapersons.validation.annotation.RegexFieldValidator;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.ValidationParameter;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.validation.validators.CustomValidatorSupport;
import co.datapersons.validation.validators.ValidatorSupport;


public final class AnnotationValidationBuilder
{
	private Map<String, ValidatorSupport[]> validatorsMapping = new HashMap<String, ValidatorSupport[]>();

	private static class AnnotationValidationBuilderHolder
	{
		static AnnotationValidationBuilder builer = new AnnotationValidationBuilder();
	}

	private AnnotationValidationBuilder()
	{

	}

	public static AnnotationValidationBuilder getInstance()
	{
		return AnnotationValidationBuilderHolder.builer;
	}
	
	public ValidatorSupport[] getValidatorSupoort(Method method)
	{
		String methodName = method.getDeclaringClass().getName();
		methodName = methodName.concat("."+method.getName());
		if (validatorsMapping.get(methodName) != null)
		{
			return validatorsMapping.get(methodName);
		}
		Annotation[] annotations = method.getAnnotations();
		if (annotations != null)
		{
			List<ValidatorSupport> validators = new ArrayList<ValidatorSupport>();
			for (int i = 0; i < annotations.length; i++)
			{
				Annotation annotation = annotations[i];

				if (annotation instanceof CustomValidator)
				{
					CustomValidator v = (CustomValidator) annotation;
					processCustomValidatorAnnotation(v, validators);
				}
				else if (annotation instanceof RegexFieldValidator)
				{
					RegexFieldValidator v = (RegexFieldValidator) annotation;
					processRegexFieldValidatorAnnotation(v, validators);
				}
				else if (annotation instanceof RequiredFieldValidator)
				{
					RequiredFieldValidator v = (RequiredFieldValidator) annotation;
					processRequiredFieldValidatorAnnotation(v, validators);
				}
				else if (annotation instanceof Validations)
				{
					processValidators((Validations) annotation, validators);
				}
			}
			if (validators.size() > 0)
			{
				ValidatorSupport[] validatorArray = new ValidatorSupport[validators.size()];
				this.validatorsMapping.put(methodName, validators.toArray(validatorArray));
				return validatorsMapping.get(methodName);
			}
		}
		return null;
	}

	private void processValidators(Validations annotation, List<ValidatorSupport> validators)
	{
		CustomValidator[] customs = annotation.customValidators();
		for(CustomValidator custom : customs){
			this.processCustomValidatorAnnotation(custom, validators);
		}
		
		RequiredFieldValidator[] requireds = annotation.requiredFields();
		for(RequiredFieldValidator required :requireds){
			this.processRequiredFieldValidatorAnnotation(required, validators);
		}
		
		RegexFieldValidator[] regexFields = annotation.regexFields();
		for(RegexFieldValidator regesField:regexFields){
			this.processRegexFieldValidatorAnnotation(regesField, validators);
		}
	}

	private void processCustomValidatorAnnotation(CustomValidator v, List<ValidatorSupport> validators)
	{
		String type = v.type();
		if (StringUtils.isEmpty(type))
		{
			return;
		}
		
	    type = "co.datapersons.validation.validators.".concat(type);
		
		try
		{
			Object o= Class.forName(type).newInstance();
			if(o instanceof CustomValidatorSupport){
				String  filePath = v.fieldPath();
				if(StringUtils.isEmpty(filePath)){
					return;
				}
				CustomValidatorSupport customeValidator = (CustomValidatorSupport)o;
				customeValidator.setMessage(v.message());
				customeValidator.setMessageParameters(v.messageParams());
				ValidationParameter[] params = v.parameters();
				Map<String,Object> paramsMap = new HashMap<String, Object>();
				for(ValidationParameter param : params){
					if(StringUtils.isEmpty(param.name())){
						continue;
					}
					paramsMap.put(param.name(), param.value());
				}
				customeValidator.setParams(paramsMap);
				
			}else {
//				LogService.error("customValidator class is not allow class!");
				return;
			}
		}
		catch (ClassNotFoundException e)
		{
//			LogService.error(e);
			return;
		}
		catch (InstantiationException e)
		{
//			LogService.error(e);
			return;
		}
		catch (IllegalAccessException e)
		{
//			LogService.error(e);
			return;
		}
	}


	private void processRequiredFieldValidatorAnnotation(RequiredFieldValidator v, List<ValidatorSupport> validators)
	{
		String filePath = v.fieldPath();
		if (StringUtils.isEmpty(filePath))
		{
			return;
		}
		String message = v.message();
		String[] messageparams = v.messageParams();
		co.datapersons.validation.validators.RequiredFieldValidator requiredFieldValidator = new co.datapersons.validation.validators.RequiredFieldValidator();
		requiredFieldValidator.setMessage(message);
		requiredFieldValidator.setMessageParameters(messageparams);
		requiredFieldValidator.setFieldPath(filePath);
		validators.add(requiredFieldValidator);
	}

	private void processRegexFieldValidatorAnnotation(RegexFieldValidator v, List<ValidatorSupport> validators)
	{
		String filePath = v.fieldPath();
		if (StringUtils.isEmpty(filePath))
		{
			return;
		}
		String message = v.message();
		String[] messageparams = v.messageParams();
		String regex = v.regex();
		if (StringUtils.isEmpty(regex))
		{
			return;
		}
		boolean isTrim = v.trim();
		boolean caseSensitive = v.caseSensitive();

		co.datapersons.validation.validators.RegexFieldValidator regexFieldValidator = new co.datapersons.validation.validators.RegexFieldValidator();
		regexFieldValidator.setMessage(message);
		regexFieldValidator.setMessageParameters(messageparams);
		regexFieldValidator.setFieldPath(filePath);
		regexFieldValidator.setTrim(isTrim);
		regexFieldValidator.setCaseSensitive(caseSensitive);
		regexFieldValidator.setRegex(regex);
		validators.add(regexFieldValidator);
	}
}
