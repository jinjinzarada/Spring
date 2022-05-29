package kh.spring.myweb.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Aspect
// 예를 들어 예전에 extends 같이 비스무리
// @Pointcut, @Before 인지하는 역할
public class AdviceLog {
	
//	@Pointcut("execution(접근제한자 리턴자료형 패키지명.클래스명.메소드명(매개인자자료형 매개인자 변수명, 매개인자자료형 변수명))")
	@Pointcut("execution(public * kh.spring.myweb..*Dao.*(..))")
	public void commonDaoPointCut() {}
	@Pointcut("execution(public * kh.spring.myweb..*Service.*(..))")
	public void commonServicePointCut() {}
	@Pointcut("execution(public * kh.spring.myweb..*Controller.*(..))")
	public void commonControllerPointCut() {}
	
	//@Before("commonDaoPointCut()")
	public void beforeLogMethod(JoinPoint jp) {
		System.out.println("[Before"+jp.getThis()+":"+jp.getSignature().getName()+"]");
		// 타겟메소드로 전달되는 매개인자들
		Object[] args = jp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.print("args["+i+"] "+args[i] +"\n");
		}	
	}
//	@AfterReturning("commonDaoPointCut()")
	public void afterReturningLogMethod(JoinPoint jp) {
		System.out.println("AfterReturning ["+jp.getThis()+":"+jp.getSignature().getName()+"]");
	}
//	@After("commonDaoPointCut()")
	public void afterLogMethod(JoinPoint jp) {
		System.out.println("After ["+jp.getThis()+":"+jp.getSignature().getName()+"]");
		
	}
	
	@Around("commonDaoPointCut()")
	public Object aroundLogMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object ro = null;  // 타겟메소드로부터 return 받은 값을 저장
		
		System.out.println("["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		// 타겟메소드로 전달되는 매개인자들
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.print("args["+i+"] "+args[i] +"\n");
		}	
		
		// 타겟메소드 실행
		ro = pjp.proceed();
		
		// 타겟메소드의 return 값
		System.out.println("DAO Ret : "+ ro);
		
		return ro;
	}
	
	@Around("commonControllerPointCut()")
	public Object aroundLogCtrlMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object ro = null;  // 타겟메소드로부터 return 받은 값을 저장
		
		System.out.println("["+pjp.getThis()+":"+pjp.getSignature().getName()+"]");
		// 타겟메소드로 전달되는 매개인자들
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.print("args["+i+"] "+args[i] +"\n");
		}	
		
		// 타겟메소드 실행
		ro = pjp.proceed();
		
		// 타겟메소드의 return 값
		System.out.println("CRTL Ret : "+ ro);
		
		return ro;
	}
	
	
	

}
