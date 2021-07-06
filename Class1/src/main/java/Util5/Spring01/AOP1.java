package Util5.Spring01;

import org.aspectj.lang.ProceedingJoinPoint;

public class AOP1 {




    public  void startTransaction(){System.out.println("======>Start Begin");}

    public  void endTransaction(){System.out.println("======>end Begin");}



    //这里的参数必须是ProceedingJoinPoint
    public void    around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=======>around  begin ding");

        proceedingJoinPoint.proceed();

        System.out.println("======>around finish end");
    }
}
