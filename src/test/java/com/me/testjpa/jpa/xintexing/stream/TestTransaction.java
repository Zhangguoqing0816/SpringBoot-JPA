package com.me.testjpa.jpa.xintexing.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTransaction {

    List<Transaction> transactions = null;
    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 850)
        );
    }
    //1.找出2011年发生的所有交易，并按交易额排序，从低到高
    @Test
    public void test01(){
        List<Transaction> collect = transactions.stream().filter(e -> e.getYear() == 2011)
                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
    //2.交易员都在哪些不同的城市工作过
    @Test
    public void test02(){
        List<String> collect = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
    //3.查找所有来自剑桥的交易员，并按姓名排序   Cambridge
    @Test
    public void test03(){
        List<Trader> cambridge = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cambridge);
    }
    //4.返回所有交易员的姓名字符串，按照字母排序
    @Test
    public void test04(){
        transactions.stream()
                .map(t -> t.getTrader().getName()).sorted().forEach(System.out::println);
        System.out.println("<----->");
        String reduce = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                //.reduce("", String::concat);
                .reduce("", (x, y) -> x.concat(y));
        System.out.println(reduce);
        System.out.println("<----->");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);
    }
    public static Stream<String> filterCharacter(String str){
        List<String> list = new ArrayList<>();
        for (Character c: str.toCharArray()) {
            list.add(c.toString());
        }
        return list.stream();
    }

    //5.有没有交易员实在米兰工作的
    @Test
    public void test05(){
        boolean miLan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(miLan);

    }
    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void test06(){
        Optional<Integer> cambridge = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(cambridge.get());
    }
    //7.所有交易中，最高的交易额是多少
    @Test
    public void test07(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        System.out.println(max.get());

        Optional<Integer> collect = transactions.stream()
                .map(Transaction::getValue)
                .collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(collect.get());
    }
    //8.找到交易额最小的交易
    @Test
    public void test08(){
        Optional<Transaction> min = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(min.get());
        Optional<Transaction> collect = transactions.stream()
                .collect(Collectors.minBy((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())));
        System.out.println(collect.get());
    }

    public void main(String[] args){

    }
}
