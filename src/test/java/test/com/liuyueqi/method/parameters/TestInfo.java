package test.com.liuyueqi.method.parameters;

import java.util.List;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TestInfo {

    private String s;

    private Integer ii;
    private int i;

    private Long ll;
    private long l;

    private Double dd;
    private double d;

    private Boolean bb;
    private boolean b;

    @SuppressWarnings("rawtypes")
    private List list;
    private List<String> sList;
    private List<TestInfo> testList;

    @SuppressWarnings("rawtypes")
    private Map map;
    private Map<String, Long> slMap;
    private Map<String, TestInfo> testMap;
}