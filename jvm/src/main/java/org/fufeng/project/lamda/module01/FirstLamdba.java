package org.fufeng.project.lamda.module01;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * @ClassName: FirstLamdba
 * @author: LuoChunYun
 * @Date: 2019/4/25 17:09
 * @Description: TODO
 */
public class FirstLamdba {

    public static void main(String[] args) {
//        ()->{
//            for (int i=0;i<10;i++){
//                System.out.println("-> "+i);
//            }
//        };

        //演示一
        final List<Integer> integerList = Arrays.asList(1,2,3);
        final Stream<int[]> intsStream = integerList.stream().map(int[]::new);
        final List<int[]> intCollect = intsStream.collect(Collectors.toList());
        for (int[] in : intCollect){
            out.println(in.length);
        }
        out.println("--------------------------");

        //演示二
        List<Integer> personList = Arrays.asList(1,2,3,4);
        final Stream<Person> personStream = personList.stream().map(Person::new);
        final Person[] peoples = personStream.toArray(Person[]::new);
        for (Person person : peoples){
            out.println(person.age);
        }
        out.println("--------------------------");
        //演示三
        int count=3;
        new Thread(()->{
            if(count == 3){ //这里说明lambda表达式本身就是一个闭包
                //count 不允许做运算，可以理解为最终变量及final
                //count+++;
            }
        }).start();
        //List<String> mutalVal = Arrays.asList("11","22");
        //Arrays.asList 获取的是java.util.Arrays.ArrayList这个对象，而不是我们常用的java.util.ArrayList
        List<String> mutalVal = new ArrayList<>();
        new Thread(()->{
           if(!mutalVal.contains("33")){
               mutalVal.add("33");
           }
        }).start();
        out.println("--------------------------");
        //演示四
        List<String> mutalForeach = new ArrayList<>();
        mutalForeach.add("luo");
        mutalForeach.add("chun");
        mutalForeach.add("yun");
        mutalForeach.forEach(out::println);
        out.println("--------------------------");
        //演示五
        //定义比较规则
        final Comparator<Person> comparing = Comparator.comparing(Person::get);
        final int comparePerson = comparing.compare(new Person(1), new Person(2));
        out.println(comparePerson);
        //定义比较规则
        final Comparator<String> stringComparator = Comparator.comparing(String::length);
        final int compareString = stringComparator.compare("567890", "1234");
        out.println(compareString);

        out.println(1L << 32);
        out.println(8192 >> 10);
        out.println(8192 >>> 10);
        out.println("--------------------------");
        //演示六
        //演示File类里面的listFile
        File file = new File("D:\\SoftWareTools\\idea201901\\boot\\thinking-in-java8");
        //一
        File[] filesLamdba = file.listFiles(fileName->fileName.isDirectory());
        if(filesLamdba != null) {
            Arrays.stream(filesLamdba).forEach(fileEach -> out.println(fileEach.getName()));
        }
        //二
        File[] files = file.listFiles(File::isDirectory);
        if(files != null) {
            Arrays.stream(files).forEach(fileEach -> out.println(fileEach.getName()));
        }

        //三 对一个File目录集合进行目录排序，然后再对目录中的元素信息按路径名称排序
        if(files != null) {
            Arrays.stream(files) //创建一个文件流
                    .map(fileSort -> fileSort.getAbsolutePath()) //计算出新的文件路径
                    .sorted() //排序处理
                    .map(filePath -> {
                        ArrayList<String> list = new ArrayList<>();
                        File filePathFile = new File(filePath);
                        if(filePathFile.listFiles() != null) {
                            for (File filepf : filePathFile.listFiles()) {
                                list.add(filepf.getAbsolutePath());
                            }
                        }
                        return list;
                    }) //计算出排序后的文件夹的内部文件的顺序
                    .forEach(soredArrayList -> {
                        for(String soredFileName : soredArrayList){
                            out.println(soredFileName);
                        }
                    });
                    //.forEach(out::println);
        }

        //线程案例
        //new Thread(uncheck(()->{}));


    }

    public static class Person{

        private Integer age;

        public Person(Integer age) {
            this.age = age;
        }

        public int get(){
            return age;
        }
    }

}
