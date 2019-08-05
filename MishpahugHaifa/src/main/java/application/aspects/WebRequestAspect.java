package application.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

@Aspect
@Component
public class WebRequestAspect {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger("application.aspects");

	@Pointcut("within(application.controllers..*)")
	public void webPointCut() {
	};

	@Around("webPointCut() && args(httpHeaders, request,..)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint, HttpServletRequest request, HttpHeaders httpHeaders)
			throws Throwable {
		String targetClass = joinPoint.getTarget().getClass().getSimpleName().toString();
		String targetMethod = joinPoint.getSignature().getName();

		Optional<? extends Object> id = Stream.of(joinPoint.getArgs())
				.filter(arg -> arg != null)
				.filter(arg -> arg.getClass().getName().equals("java.lang.Integer")).findFirst();
		String idTrace = (id.isPresent() ? "{" + id.get().toString() + "}" : "");
		httpHeaders.forEach((key, value) -> {
			log.info(targetClass + " -> " + targetMethod + idTrace + " -> headers -> "
					+ String.format("Header '%s' = %s", key, value));
		});
		log.debug(targetClass + " -> " + targetMethod + " -> Remote IP -> " + request.getRemoteAddr());
		log.debug(targetClass + " -> " + targetMethod + " -> Remote Host -> " + request.getRemoteHost());
		log.debug(targetClass + " -> " + targetMethod + " -> Local name -> " + request.getLocalName());
		log.debug(targetClass + " -> " + targetMethod + " -> Path info -> " + request.getPathInfo());
		log.debug(targetClass + " -> " + targetMethod + " -> Protocol -> " + request.getProtocol());
		log.debug(targetClass + " -> " + targetMethod + " -> Scheme -> " + request.getScheme());
		log.debug(
				targetClass + " -> " + targetMethod + " -> Requested session id -> " + request.getRequestedSessionId());
		Object proceed = joinPoint.proceed();
		return proceed;

	}

}
