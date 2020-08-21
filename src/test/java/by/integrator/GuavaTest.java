package by.integrator;

import by.integrator.bean.User;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.*;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;

public class GuavaTest {

    public static void print(Object... args) {
        Arrays.stream(args).forEach(System.out::println);
    }

    @Test
    public void testForPreconditions() {
        //  Preconditions.checkArgument(); проверка аргумента
        //  Preconditions.checkState(); проверка состояния
        //  Preconditions.checkNotNull(); проверка на null
        User nullUser = null;
        Preconditions.checkArgument(!StringUtils.isEmpty(nullUser), "null User");
        Preconditions.checkNotNull(!StringUtils.isEmpty(nullUser), "null User");
    }

    @Test
    public void testForImmutableCollections() {
        //list
        List<String> list = ImmutableList.of("fisrt", "second");

        ImmutableList.Builder<Object> builder = ImmutableList.builder();
        for (int i = 0; i < 10; i++) {
            builder.add("new element");
        }
        Iterable<Object> iterable = builder.build();

        //map
        Map<Object, Object> map = ImmutableMap.of("1", "first", "2", "second");

        print(list, iterable, map);
    }

    @Test
    public void testForString() {
        print(Strings.commonSuffix("Mike", "Coke")); //вывод общего суффикса 'ke'
        print(Strings.padEnd("pad sad string", 20, '_')); //досоздает строку до определенного
        //кол-тва символов специальным символом. Проверь тестом
        print(Strings.repeat("_", 20));//повтор определенной строки, определенного кол-тва раз
        ImmutableList.of("Jeremy", "John", "Mike")
                .stream()
                .map(name -> Strings.padStart(name, 15, '.'))
                .forEach(GuavaTest::print);

    }

    @Test
    public void testForJoinerAndSplitter(){
        String s = "long text, very long text, nothing, but text";

        Iterable<String> strings =  Splitter.on("text").trimResults().split(s); //+ пробелы
       //Iterable<String> strings =  Splitter.on("text").split(s); убирается то что 'text'
        print(strings);

        String video = Joiner.on("video").join(strings);
        //добавляется везде 'video'
        print(video); //

    }

    @Test
    public void testForSimpleCollections(){
        ArrayList<String> stringsList = Lists.newArrayList("1", "2", "3");
        HashSet<String> stringsSet = Sets.newHashSet("4", "5", "6");

        Iterable<String> concat = Iterables.concat(stringsList, stringsSet);
        //сложение коллекций во единое

        Iterable<String> skip = Iterables.skip(concat, 2);
        //пропуск первых n элементов

        Iterable<String> limit = Iterables.limit(skip, 2);
        //вывод только n кол-тва числа элементов

        print(limit);
    }

    @Test
    public void testForSets(){
        HashSet<Integer> strings1 = Sets.newHashSet(1,2,3,4);
        HashSet<Integer> strings = Sets.newHashSet(3,4,5,6,7,8);

        Sets.SetView<Integer> difference1 = Sets.difference(strings1, strings); //отличие первого от второго
        Sets.SetView<Integer> difference2 = Sets.difference(strings, strings1); // -||- второго от первого
        //разница между сетами

        Sets.SetView<Integer> intersection = Sets.intersection(strings1, strings);
        //одинаковые элементы

        print(difference1, difference2, intersection);


    }


    @Test
    public void testForCustomCollections(){
        //MultiSet
        //hashMap со счётчиком кол-тва элементов

        HashMultiset<Object> objects = HashMultiset.create();
        objects.add("element");
        objects.add("no", 3);
        objects.add("radio");
        objects.add("radio");

        print(objects);

        //MultiMap
        HashMultimap<Object, Object> multimap = HashMultimap.create();
        multimap.put("lets", "dru");
        multimap.put("razbor", "li brus");
        multimap.put("razbor", "brus li");
        multimap.putAll("google", Lists.newArrayList("1", "2", "3"));
        //так же коллекция со счетчиком + запуск теста

        print(multimap);

        //BiMap

        HashBiMap<Object, Object> biMap = HashBiMap.create();
        biMap.put("один шорох", 1);
        biMap.put("два ответа", 2);
        biMap.put("три привета", 3);

        print(biMap);
        print(biMap.inverse());
        print(biMap.inverse().get(2));
        print(biMap.get("два ответа"));

        //Table
        HashBasedTable<Object, Object, Object> table = HashBasedTable.create();
        table.put("mike", "12-05-2017", 25.30);
        table.put("mike", "12-06-2017", 50.30);
        table.put("jot", "12-05-2017", 60.30);
        table.put("jot", "12-06-2017", 10.40);
        table.put("sam", "12-05-2017", 70.49);
        table.put("sam", "12-06-2017", 30.40);

        print(
                table,
                table.get("mike", "12-05-2017"),
                table.row("mike"),
                table.row("12-05-2017"),
                Tables.transpose(table)
        );
    }


    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green");
    //она не изменяемая


}
