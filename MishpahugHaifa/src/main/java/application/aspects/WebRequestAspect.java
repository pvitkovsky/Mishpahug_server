package application.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import application.controllers.EventController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class WebRequestAspect {

	@Pointcut("within(application.controllers..*)")
	public void webPointCut() {
	};
	

	@Around("webPointCut() && args(httpHeaders, request)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint, HttpServletRequest request, HttpHeaders httpHeaders)
			throws Throwable {
		String targetClass =  joinPoint.getTarget().getClass().toString();
		String targetMethod =  joinPoint.getSignature().getName();
		
		System.out.println(joinPoint.getArgs()); // TODO: get ID; 
		httpHeaders.forEach((key, value) -> {
			log.info(targetClass + " -> " + targetMethod + " -> headers -> "
					+ String.format("Header '%s' = %s", key, value));
		});
		log.info(targetClass + " -> " + targetMethod + " -> Remote IP -> " + request.getRemoteAddr());
		Object proceed = joinPoint.proceed();
		return proceed;
		
	}
	
	
}
