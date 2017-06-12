package com.bmbm.weatherdemobykotlin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        List<String> strList = new ArrayList<>();
        strList.add("1");
        strList.add("a");
        strList.add("2");
        strList.add("3");
        List<? super String> objList = strList;

//        objList.add();


        System.out.println("success=" + objList.get(1));
    }
}