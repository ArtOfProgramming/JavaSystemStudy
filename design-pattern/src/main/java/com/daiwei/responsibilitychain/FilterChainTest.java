package com.daiwei.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

public class FilterChainTest implements FilterTest {

    List<FilterTest> filters = new ArrayList<>();
    private int index = 0;

    @Override
    public void doFilter(Req req, Res res, FilterChainTest filterChainTest) {
        if (index != filters.size()) {
            index++;
            filters.get(index-1).doFilter(req, res, filterChainTest);
        }
    }

    public FilterChainTest add(FilterTest filterTest) {
        filters.add(filterTest);
        return this;
    }

    public static void main(String[] args) {
        FilterChainTest filterChainTest = new FilterChainTest();
        filterChainTest.add(new Filter1()).add(new Filter2());
        Req req = new Req("nihao");
        Res res = new Res("daiwei");
        filterChainTest.doFilter(req, res, filterChainTest);
        System.out.println(req.getStr());
        System.out.println(res.getStr());
    }
}

class Req {
    private String str;

    public Req(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

class Res {
    private String str;

    public Res(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

interface FilterTest {
    void doFilter(Req req, Res res, FilterChainTest filterChainTest);
}

class Filter1 implements FilterTest {

    @Override
    public void doFilter(Req req, Res res, FilterChainTest filterChainTest) {
        req.setStr(req.getStr() + "+1");
        filterChainTest.doFilter(req, res, filterChainTest);
        res.setStr(res.getStr() + "+1");
    }
}

class Filter2 implements FilterTest {

    @Override
    public void doFilter(Req req, Res res, FilterChainTest filterChainTest) {
        req.setStr(req.getStr() + "+2");
        filterChainTest.doFilter(req, res, filterChainTest);
        res.setStr(res.getStr() + "+2");
    }
}
