package Util5.Spring01;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect  //开启一个切面类
public class AOP2 {


    @Pointcut("execution(* Util5.Spring01.Klass.*dong(..))")
    public  void point(){

    }



    @Before(value = "point()")
    public  void before(){

    }

    @AfterReturning("point()")
    public  void after(){

    }

    @Around("point()")
    public  void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=======>around  begin ding");

        proceedingJoinPoint.proceed();

        System.out.println("======>around finish end");
    }
}
