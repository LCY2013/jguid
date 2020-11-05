package org.fufeng.project.lamda.module02;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toSet;

/**
 * @ClassName: CreateStream
 * @author: LuoChunYun
 * @Date: 2019/4/26 14:06
 * @Description: TODO
 */
public class CreateStream {

    public static void main(String[] args) throws IOException, URISyntaxException {
        //001
        createStream();

        //002
        out.println("-----------------------------");
        List<String> lists = new ArrayList<>();
        lists.add("11");
        lists.add("22");
        lists.add("33");
        lists.add("44");
        final Stream<String> filterStream = lists.stream();
        filter(filterStream);
        final Stream<String> mapStream = lists.stream();
        map(mapStream);
        final Stream<String> flatMapStream = lists.stream();
        flatMap(flatMapStream);

        //003
        out.println("-----------------------------");
        list();

        //004
        out.println("-----------------------------");
        skip();

        //005
        out.println("-----------------------------");
        concatStream();

        //006
        out.println("-----------------------------");
        peek();

        //007
        out.println("-----------------------------");
        distinct();

        //008
        out.println("-----------------------------");
        sorted();

        //009
        out.println("-----------------------------");
        optional();

        //010
        out.println("-----------------------------");
        reduce();

        //011 展示结果集
        out.println("-----------------------------");
        showResult();

        //012 转化成map
        out.println("-----------------------------");
        trancfMap();

        //013 分组和切片
        out.println("-----------------------------");
        sliceGroup();

        //014 原始类型流
        out.println("-----------------------------");
        originalType();

        //取余数生成器
        out.println("-----------------------------");
        long a = 25214903917L;
        long c = 11;
        long m = (long)Math.pow(2,48);
        generatorNum(a,c,m,3,10)
        .forEach(val -> out.print(val + " "));
    }

    /**
     *  (a*x0+c)%m 取余生成器
     * @param seed 起始值
     * @param limit 取多少个
     * @return Stream流
     */
    private static Stream<Long> generatorNum(long a, long c, long m, long seed, long limit){
        return Stream.iterate(seed,p -> (a*p+c)%m).limit(limit);
    }

    private static void originalType() {
        //创建一个int类型的原始数据流，原始数据类型流不包括short,char,byte,boolean,这些归入int
        //而float归入double中
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        //integerStream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6},0,4);
        out.println("-----parallel-----");
        integerStream.parallel()
                .peek(out::println)
                .map(val -> val*10)
                .forEach(out::println);
        out.println("-----parallel-----");
        //不包括0,100
        final IntStream range = IntStream.range(0, 2);
        range.forEach(out::println);
        //包括0,100
        final IntStream rangeClosed = IntStream.rangeClosed(0,2);
        rangeClosed.forEach(out::println);

        //利用CharSequence转化成IntStream
        String sentence = "\uD835\uDD46 is the set of octonions.";
        final IntStream intStream = sentence.codePoints();


    }

    private static void sliceGroup() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, List<Locale>> localesMap =
                locales.collect(Collectors.groupingBy(Locale::getCountry));
        localesMap.forEach((k,v)-> out.println(k+"->"+v));
        out.println("-------sliceGroup------");
        localesMap.get("CH").forEach(out::println);
        out.println(localesMap.size());

        //向下的流转化成Set，默认是List
        locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, Set<Locale>> localesSetMap =
                locales.collect(Collectors.groupingBy(Locale::getCountry,toSet()));
        //统计每个分组的个数
        locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, Long> countingMap =
                locales.collect(Collectors.groupingBy(Locale::getCountry, counting()));
        out.println("中文拥有的语言:"+countingMap.get("CN"));
        countingMap.forEach((k,v)->out.println(k+"->"+v));

        out.println("-------分类成中文和其他语言------");
        Stream<Locale> locale = Stream.of(Locale.getAvailableLocales());
        out.println(locale.collect(Collectors.partitioningBy(local -> local.getLanguage().equals("CN")))
                .size());
        locale = Stream.of(Locale.getAvailableLocales());
        final Map<Boolean, List<Locale>> cn =
                locale.collect(Collectors.partitioningBy(local -> local.getLanguage().equals("CN")));

        out.println("-----------city-----------");

    }

    private static void trancfMap() {
        final Map<Integer, String> integerStringMap = Stream.of(new Person(1, "luo")
                , new Person(2, "chun")
                , new Person(3, "yun"))
                .collect(Collectors.toMap(Person::getAge, Person::getName));

        final Map<Integer, Person> identityPersonMap = Stream.of(new Person(1, "luo")
                , new Person(2, "chun")
                , new Person(3, "yun")
                ,new Person(3, "yun1"))
                .collect(Collectors.toMap(Person::getAge, Function.identity(),
                        (oldVal,newVal) -> oldVal));
        identityPersonMap.values().forEach(out::println);

        //如果需要在已有的map集合中新加新增的数据
        final Map<Integer, Person> personMap = Stream.of(new Person(1, "luo")
                , new Person(2, "chun")
                , new Person(3, "yun")
                , new Person(3, "yun1"))
                .collect(Collectors.toMap(Person::getAge, Function.identity(),
                        (oldVal, newVal) -> {
                            oldVal.setName(oldVal.getName() + ":" + newVal.getName());
                            return oldVal;
                        }));
        personMap.values().forEach(out::println);

        final TreeMap<Integer, Person> treePersonMap = Stream.of(new Person(1, "luo")
                , new Person(2, "chun")
                , new Person(3, "yun")
                , new Person(3, "yun1"))
                .collect(Collectors.toMap(Person::getAge, Function.identity(),
                        (oldVal, newVal) -> {
                            oldVal.setName(oldVal.getName() + ":" + newVal.getName());
                            return oldVal;
                        },
                        TreeMap::new));

    }

    private static void showResult() {
        final Integer[] as = Stream.of(1, 2, 7, 3, 5)
                .filter(intVal -> intVal > 3) //过滤
                .map(intVal -> {
                    intVal *= 2;
                    return intVal;
                })
                .toArray(Integer[]::new);
        Arrays.stream(as)
                .forEach(out::println);

        //集合操作
        Stream.of(1, 2, 7, 3, 5)
                .map(intVal -> {
                    intVal *= 2;
                    return intVal;
                })
                //.collect(ArrayList::new,ArrayList::add,ArrayList::addAll); 等价于collect(Collectors.toList()）
                //.collect(Collectors.toList());
                //.collect(Collectors.toSet());
                .collect(Collectors.toCollection(TreeSet::new));

        //把字符串收集起来合并
        final String collectJoinStr = Stream.of("1", "2", "3")
                .collect(Collectors.joining());
        out.println(collectJoinStr);
        final String collectJoinStrSplit = Stream.of("1", "2", "3")
                .collect(Collectors.joining("-"));
        out.println(collectJoinStrSplit);
        final String collectObjectJoinStrSplit = Stream.of("1", 2, "3")
                .map(Object::toString)
                .collect(Collectors.joining("-"));
        out.println(collectObjectJoinStrSplit);

        final IntSummaryStatistics summarizingInt = Stream.of("1", "2", "3")
                .collect(Collectors.summarizingInt(Integer::parseInt));
        out.println(summarizingInt.getMax());
        out.println(summarizingInt.getAverage());
        out.println(summarizingInt.getCount());
        out.println(summarizingInt.getMin());
        out.println(summarizingInt.getSum());

        //按顺序访问流，但是这个就不能使用并行带来的作用
        Stream.of(1, 2, 7, 3, 5)
                .forEachOrdered(out::println);
    }

    private static void reduce() {
        final Optional<Integer> integerOptional = Stream.of(1, 2, 7, 3, 5) //获取流
                .reduce((x, y) -> x + y);//进行流的聚合
        out.println(integerOptional.orElse(0));

        final Optional<Integer> sumOptional = Stream.of(1, 2, 7, 3, 5) //获取流
                .reduce(Integer::sum);
        out.println(integerOptional.orElse(0));
    }

    private static void optional() {
        final Optional<Integer> max = Stream.of(1, 2, 7, 3, 5)
                .max(Integer::compareTo);
        out.println(max.get());
        final Optional<Integer> min = Stream.of(1, 2, 7, 3, 5)
                .min(Integer::compareTo);
        out.println(min.get());

        final Optional<String> first = Stream.of("Qkls", "magic", "luo", "Query", "Qu")
                .filter(str -> str.startsWith("Q"))
                .findFirst();
        out.println(first.get());

        final Optional<String> any = Stream.of("Qkls", "magic", "luo", "Query", "Qu")
                .filter(str -> str.startsWith("Q"))
                .findAny();
        out.println(any.get());
        out.println(any.orElse("other"));
        out.println(any.orElseGet(()->
            System.getProperty("user.dir")));
        out.println(any.orElseThrow(NoSuchElementException::new));

        final boolean anyMatch = Stream.of("Qkls", "magic", "luo", "Query", "Qu")
                .parallel()  //开启并行查找
                .anyMatch(str -> str.startsWith("Q"));
        out.println(anyMatch);

        final Optional<Object> objectOptional = Optional.ofNullable(null);
        //out.println(objectOptional.orElseThrow(NoSuchElementException::new));


    }

    private static void sorted() {
        Stream.of(1,2,7,3,5)
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .forEach(out::println);
    }

    private static void distinct() {
        Stream.of("1","1","1","2","3")
                .distinct()
                .forEach(out::println);
    }

    private static void peek() {
        Stream.iterate(1.0, p -> p * 2)
                .peek(p -> out.println("fetch " + p))
                .limit(5)
                .forEach(out::println);
    }

    private static void concatStream() {
        final Stream<Double> limitOne = Stream.generate(Math::random).limit(3);
        final Stream<Double> limitTwo = Stream.generate(Math::random).limit(1);
        final Stream<Double> concatStream = Stream.concat(limitOne, limitTwo);
        concatStream.forEach(out::println);
    }

    private static void skip() {
        //跳过前10个数
        //final Stream<Double> limit = Stream.generate(Math::random).skip(10);
        //limit.forEach(out::println);
    }

    private static void list() {
        //获取限定的个数
        final Stream<Double> limit = Stream.generate(Math::random).limit(10);
        limit.forEach(out::println);
    }

    private static Stream<Character> createCharacter(String str){
        List<Character> retVal = new ArrayList<>();
        for (char ch : str.toCharArray()){
            retVal.add(ch);
        }
        return retVal.stream();
    }

    private static void flatMap(Stream<String> stringStream) {
        final Stream<Character> characterStream =
                stringStream.flatMap(CreateStream::createCharacter);

    }

    private static void map(Stream<String> stringStream) {
        final Stream<Stream<Character>> streamStream = stringStream.map(CreateStream::createCharacter);
    }

    private static void filter(Stream<String> stringStream){
        final Stream<String> stringStream1 = stringStream.filter(strVal -> strVal.length() > 12);
    }

    private static void createStream() throws URISyntaxException, IOException {
        //001
        final Stream<String> stringStream = Stream.of("1", "2", "3");
        //002
        //float[] floatArray = new float[]{2,3,5,6,4f,9f};
        //double[] floatArray = new double[]{2,3,5,6,4d,9f};Arrays.stream没有支持float
        double[] floatArray = new double[]{2,3,5,6,4d,9f};
        final DoubleStream stream = Arrays.stream(floatArray);

        //003
        final Stream<String> generate = Stream.generate(() -> "magic");

        //004
        final Stream<Object> empty = Stream.empty();

        //005
        final Stream<Double> doubleStream = Stream.generate(Math::random);

        //006  从0开始迭代
        final Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(100);
        bigIntegerStream.forEach(out::print);

        //007
        final Stream<String> stringPattermStream =
                Pattern.compile("[\\P{L}]+").splitAsStream("magic is a gentleman");
        stringPattermStream.forEach(out::println);

        //008
        /*try (final Stream<String> lines = Files.lines(Paths.get(new URI("")))){
            if(lines != null){

            }
        }*/
    }

    private static class Person{
        private int age;
        private String name;

        public Person() {
        }

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
