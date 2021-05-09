package Util1;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

//继承ClassLoader对象
public class CustomClassLoader  extends  ClassLoader{

    public  static  void main(String [] args) throws Exception {
        //已知类的名字为Hello,定义为常量就直接存在本地变量中，不用进行指令交换
        final String className ="file/Hello";
        //same
        final String methodName = "hello";
        //创建类加载器
        ClassLoader classLoader = new CustomClassLoader();
        //加载Hello这个类
        Class<?> clazz = classLoader.loadClass(className);

        //遍历所有的方法，这里注释一下，clazz.getMethods()和getDeclaredMethods()是不一样的
        //getMethod()是获取所有的public的方法(包括父类),而getDeclaredMethods是获取所有的方法（包括父类）
        for (Method method:clazz.getDeclaredMethods()){
            System.out.println(clazz.getSimpleName() + "." + method.getName());
        }

        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    //重写父类的findClass方法
    @Override
    protected Class<?> findClass(String name){
        //
        String resourcePath = name.replace(".","/");

        final String suffix =".xlass";

        String steamPath  = resourcePath+suffix;
        //获得当前的类加载器，并且通过路径进行加载
        InputStream inputStream =this.getClass().getClassLoader().getResourceAsStream(steamPath);

        int length = 0;
        if (inputStream!=null){
            try {
                length = inputStream.available();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte [] bytes  =new byte[length];

            try {
                inputStream.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte [] classByte =decode(bytes);

            //这里对路径做一个处理，取出类名给下面使用
            String className = name.split("/")[name.split("/").length-1];

            // defineClass这里的第一个参数是类的名称,如果原封不动的使用路径的话会引起异常,
            return defineClass(className, classByte, 0, classByte.length);
        }else{
            try {
                throw  new IOException("inputStream Path is Null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    // 关闭
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
