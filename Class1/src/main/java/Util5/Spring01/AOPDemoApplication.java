package Util5.Spring01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPDemoApplication {

    public  static void main(String [] args){


        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student1 = context.getBean(Student.class);
        Object obj=  context.getBean("student123");
        Student student1 = (Student) context.getBean("student123");
        System.out.println(student1.toString());

        student1.print();

        Student student100 = (Student) context.getBean("student100");
        System.out.println(student100.toString());

        student100.print();

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型："+class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school.getClass());

        ISchool school1 = context.getBean(ISchool.class);
        System.out.println(school1);
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school1.getClass());

        school1.ding();

        class1.dong();

        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));

    }

}
