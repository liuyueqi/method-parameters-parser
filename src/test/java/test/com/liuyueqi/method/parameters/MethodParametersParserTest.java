package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.DefaultMethodParametersParserFactory;
import com.liuyueqi.method.parameters.MethodParametersParser;

public class MethodParametersParserTest {

    private Map<String, Method> methodMap = new HashMap<String, Method>();

    @BeforeClass
    public void setUp() throws Exception {

        Class<?> clazz = TestService.class;
        for (Method method : clazz.getMethods()) {
            this.methodMap.put(method.getName(), method);
        }
    }

    @Test
    public void testParseSingleString() throws Exception {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleString");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        String param = "aaa";
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testParseSingleInteger() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleInteger");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Integer param = 111;
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param.toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testParseSingleShort() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleShort");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Short param = 13;
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param.toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testParseSingleLong() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleLong");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Long param = 1234L;
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param.toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testParseSingleDouble() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleDouble");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Double param = 1.23;
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param.toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testParseSingleBoolean() throws Exception {
       
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleBoolean");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Boolean param = true;
        Object[] result = parser.parse("['" + param + "']");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse(param.toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
        
        result = parser.parse("{'arg1': '" + param + "'}");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], param);
    }
    
    @Test
    public void testStringAndInteger() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("stringAndInteger");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        String arg1 = "abc";
        Integer arg2 = 123;
        Object[] result = parser.parse(JSON.toJSONString(new Object[] { arg1, arg2 }));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 2);
        Assert.assertEquals(result[0], arg1);
        Assert.assertEquals(result[1], arg2);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("arg1", arg1);
        map.put("arg2", arg2);
        result = parser.parse(JSON.toJSONString(map));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 2);
        Assert.assertEquals(result[0], arg1);
        Assert.assertEquals(result[1], arg2);
    }
    
    @Test
    public void testSingleList() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);
        
        Object[] param = new Object[] {"abc", 123, 12.34D, true};
        Object[] result = parser.parse(JSON.toJSONString(param));
        
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        
        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item.toString(), param[index].toString());
            index++;
        }
        
        List<TestInfo> testInfos = new ArrayList<TestInfo>();
        for (int i = 0; i < 5; i++) {
            TestInfo testInfo = new TestInfo();
            testInfo.setS("abc" + i);
            testInfo.setI(i);
            testInfo.setDd(i + 0.5);
            testInfos.add(testInfo);
        }
        
        result = parser.parse(JSON.toJSONString(testInfos));
        
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        
        list = (List<?>) result[0];
        index = 0;
        for (Object item : list) {
            
            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof Map);
            
            Map<String, Object> map = (Map<String, Object>) item;
            Assert.assertEquals(testInfos.get(index).getS(), map.get("s"));
            Assert.assertEquals(testInfos.get(index).getI(), map.get("i"));
            Assert.assertEquals(testInfos.get(index).getDd().toString(), map.get("dd").toString());
            index++;
        }
    }

    @Test
    public void testSingleStringList() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleStringList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);
        
        String[] param = new String[] {"abc", "def", "xyz"};
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        
        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item, param[index]);
            index++;
        }
    }

    @Test
    public void testSingleIntegerList() throws Exception {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = this.methodMap.get("singleIntegerList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);
        
        Integer[] param = new Integer[] {100, 200, 300};
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        
        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item, param[index]);
            index++;
        }
    }
}

class TestService {

    public String singleString(String arg1) {
        return arg1;
    }

    public Integer singleInteger(Integer arg1) {
        return arg1;
    }

    public Short singleShort(Short arg1) {
        return arg1;
    }
    
    public Long singleLong(Long arg1) {
        return arg1;
    }

    public Double singleDouble(Double arg1) {
        return arg1;
    }
    
    public Boolean singleBoolean(Boolean arg1) {
        return arg1;
    }

    public Object[] stringAndInteger(String arg1, Integer arg2) {
        return new Object[] { arg1, arg2 };
    }
    
    public List<?> singleList(List list) {
        return list;
    }
    
    public List<String> singleStringList(List<String> list) {
        return list;
    }
    
    public List<Integer> singleIntegerList(List<Integer> list) {
        return list;
    }
    
    public List<Double> singleDoubleList(List<Double> list) {
        return list;
    }
    
    public List<TestInfo> singleTestInfoList(List<TestInfo> list) {
        return list;
    }
    
    public Set<?> singleObjectSet(Set set) {
        return set;
    }
    
    public Set<String> singleStringSet(Set<String> set) {
        return set;
    }
    
    public Set<Integer> singleIntegerSet(Set<Integer> set) {
        return set;
    }
    
    public Set<Double> singleDoubleSet(Set<Double> set) {
        return set;
    }

    public Set<TestInfo> singleTestInfoSet(Set<TestInfo> set) {
        return set;
    }
}

@Getter
@Setter
class TestInfo {

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