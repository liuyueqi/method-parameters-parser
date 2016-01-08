package com.liuyueqi.method.parameters.test;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestInfo {

    private String s;
    
    private Long ll;
    private long l;
    
    private Integer ii;
    private int i;
    
    private Double dd;
    private double d;
    
    private Boolean bb;
    private boolean b;
    
    private List list;
    private List<String> sList;
    private List<TestInfo> testList;
    
    private Map map;
    private Map<String, Long> slMap;
    private Map<String, TestInfo> testMap;
}
