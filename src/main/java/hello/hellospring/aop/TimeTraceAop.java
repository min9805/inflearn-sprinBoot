package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

	@Around("execution(* hello.hellospring..*(..))")
	public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START = " + proceedingJoinPoint.toString());

		try {
			return proceedingJoinPoint.proceed();
		} finally {
			long end = System.currentTimeMillis();
			long timeMs = end - start;

			System.out.println("END = " + proceedingJoinPoint.toString() + " " + timeMs + "Ms");
		}
	}
}
