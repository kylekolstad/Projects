package com.campfire.aspect;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("sessionAspect")
@Aspect
public class SessionAspect {

	// attempt at session validation

	@Around("execution(* com.campfire.controllers.CF_UserController.getAllUsers(..))")
	public Object CleanSession_01(ProceedingJoinPoint pjp) throws Throwable {
		Object retval = null;
		HttpSession session = (HttpSession) pjp.getArgs()[0];
		if (session.getAttribute("loggeduser") != null) {
			retval = pjp.proceed();
			System.out.println("Proceeding with getAllUsers: " + session.getAttribute("loggeduser"));
		} else {
			System.out.println("Stopping getAllUsers: " + session.getAttribute("loggeduser"));
		}
		return retval;
	}
	
	@Around("execution(* com.campfire.controllers.CF_UserController.selectById(..))")
	public Object cleanSession_02(ProceedingJoinPoint pjp) throws Throwable {
		Object retval = null;
		HttpSession session = (HttpSession) pjp.getArgs()[0];
		if (session.getAttribute("loggeduser") != null) {
			retval = pjp.proceed();
			System.out.println("Proceeding with selectById: " + session.getAttribute("loggeduser"));
		} else {
			System.out.println("Stopping selectById: " + session.getAttribute("loggeduser"));
		}
		return retval;
	}
	
	@Around("execution(* com.campfire.controllers.CF_UserController.selectByUsername(..))")
	public Object cleanSession_03(ProceedingJoinPoint pjp) throws Throwable {
		Object retval = null;
		HttpSession session = (HttpSession) pjp.getArgs()[0];
		if (session.getAttribute("loggeduser") != null) {
			retval = pjp.proceed();
			System.out.println("Proceeding with selectByUsername: " + session.getAttribute("loggeduser"));
		} else {
			System.out.println("Stopping selectByUsername: " + session.getAttribute("loggeduser"));
		}
		return retval;
	}
	
	@Around("execution(* com.campfire.controllers.CF_UserController.selectByEmail(..))")
	public Object cleanSession_04(ProceedingJoinPoint pjp) throws Throwable {
		Object retval = null;
		HttpSession session = (HttpSession) pjp.getArgs()[0];
		if (session.getAttribute("loggeduser") != null) {
			retval = pjp.proceed();
			System.out.println("Proceeding with selectByEmail: " + session.getAttribute("loggeduser"));
		} else {
			System.out.println("Stopping selectByEmail: " + session.getAttribute("loggeduser"));
		}
		return retval;
	}
	
	@Around("execution(* com.campfire.controllers.CF_UserController.updateTitleBioProfile(..))")
	public Object cleanSession_05(ProceedingJoinPoint pjp) throws Throwable {
		Object retval = null;
		HttpSession session = (HttpSession) pjp.getArgs()[0];
		if (session.getAttribute("loggeduser") != null) {
			retval = pjp.proceed();
			System.out.println("Proceeding with updateTitleBioProfile: " + session.getAttribute("loggeduser"));
		} else {
			System.out.println("Stopping updateTitleBioProfile: " + session.getAttribute("loggeduser"));
		}
		return retval;
	}
	
}
