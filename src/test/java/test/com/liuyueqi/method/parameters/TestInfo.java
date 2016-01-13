package test.com.liuyueqi.method.parameters;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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

    @SuppressWarnings("rawtypes")
    private List list;
    private List<String> sList;
    private List<TestInfo> testList;

    @SuppressWarnings("rawtypes")
    private Map map;
    private Map<String, Long> slMap;
    private Map<String, TestInfo> testMap;
    
    public static void main(String[] args) throws Exception {
        
        PropertyDescriptor pd = new PropertyDescriptor("s", TestInfo.class);
        Method readMethod = pd.getReadMethod();
        System.out.println(readMethod);
    }
}