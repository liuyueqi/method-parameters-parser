package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.DefaultMethodParametersParserFactory;
import com.liuyueqi.method.parameters.MethodParametersParser;

public class CollectionMethodParametersParserTest extends AbstractMethodParametersParserTest {

    @Override
    protected Class<?> serviceType() {
        return CollectionTestService.class;
    }

    @Test
    public void testSingleObjectList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleObjectList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        // 基本类型List
        testSingleListParam(parser, Arrays.asList(new Object[] { "abc", 123, true }));

        // 对象List
        List<TestInfo> testInfos = new ArrayList<TestInfo>();
        for (int i = 0; i < 5; i++) {
            TestInfo testInfo = new TestInfo();
            testInfo.setS("abc" + i);
            testInfo.setI(i);
            testInfo.setDd(i + 0.5);
            testInfos.add(testInfo);
        }

        Object[] result = parser.parse(JSON.toJSONString(testInfos));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {

            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof Map);

            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) item;
            Assert.assertEquals(map.get("s"), testInfos.get(index).getS());
            Assert.assertEquals(map.get("i"), testInfos.get(index).getI());
            Assert.assertEquals(map.get("dd").toString(), testInfos.get(index).getDd().toString());
            index++;
        }
    }

    @Test
    public void testSingleStringList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleStringList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleListParam(parser, Arrays.asList(new String[] { "abc", "def", "xyz" }));
    }

    @Test
    public void testSingleIntegerList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleIntegerList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleListParam(parser, Arrays.asList(new Integer[] { 100, 200, 300 }));
    }

    @Test
    public void testSingleDoubleList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleDoubleList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleListParam(parser, Arrays.asList(new Double[] { 12.34D, 43.21D, 8.8D }));
    }

    @Test
    public void testSinglePojoList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singlePojoList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        List<TestInfo> testInfos = new ArrayList<TestInfo>();
        for (int i = 0; i < 5; i++) {
            TestInfo testInfo = new TestInfo();
            testInfo.setS("abc" + i);
            testInfo.setI(i);
            testInfo.setDd(i + 0.5);
            testInfos.add(testInfo);
        }

        testSingleListParam(parser, testInfos);
    }
    
    private void testSingleListParam(MethodParametersParser parser, List<?> param) {
        
        String json = JSON.toJSONString(param);

        // 参数格式：[]
        Object[] result = parser.parse(json);
        assertListResult(result, param);
        
        // 参数格式：{'list': []}
        result = parser.parse("{'list': " + json + "}");
        assertListResult(result, param);
    }
        
    private void assertListResult(Object[] result, List<?> param) {
        
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);
        Assert.assertEquals(result[0], param);
    }

    @Test
    public void testSingleObjectSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleObjectSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        // 基本类型Set
        testSingleSetParam(parser, new HashSet<>(Arrays.asList(new Object[] { "abc", 123, true })));

        // 对象Set
        Set<TestInfo> testInfos = new HashSet<TestInfo>();
        for (int i = 0; i < 5; i++) {
            TestInfo testInfo = new TestInfo();
            testInfo.setS("abc" + i);
            testInfo.setI(i);
            testInfo.setDd(i + 0.5);
            testInfos.add(testInfo);
        }

        Object[] result = parser.parse(JSON.toJSONString(testInfos));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : set) {

            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof Map);

            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) item;
            Assert.assertNotNull(map.get("s"));
            Assert.assertNotNull(map.get("i"));
            Assert.assertNotNull(map.get("dd"));
        }
    }

    @Test
    public void testSingleStringSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleStringSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleSetParam(parser, new HashSet<>(Arrays.asList(new String[] { "abc", "def", "xyz" })));
    }

    @Test
    public void testSingleIntegerSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleIntegerSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleSetParam(parser, new HashSet<>(Arrays.asList(new Integer[] { 100, 200, 300 })));
    }

    @Test
    public void testSingleDoubleSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleDoubleSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        testSingleSetParam(parser, new HashSet<>(Arrays.asList(new Double[] { 12.34D, 43.21D, 8.8D })));
    }

    @Test
    public void testSinglePojoSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singlePojoSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Set<TestInfo> testInfos = new HashSet<TestInfo>();
        for (int i = 0; i < 5; i++) {
            TestInfo testInfo = new TestInfo();
            testInfo.setS("abc" + i);
            testInfo.setI(i);
            testInfo.setDd(i + 0.5);
            testInfos.add(testInfo);
        }
        
        testSingleSetParam(parser, testInfos);
    }
    
    private void testSingleSetParam(MethodParametersParser parser, Set<?> param) {
        
        String json = JSON.toJSONString(param);

        // 参数格式：[]
        Object[] result = parser.parse(json);
        assertSetResult(result, param);
        
        // 参数格式：{'list': []}
        result = parser.parse("{'set': " + json + "}");
        assertSetResult(result, param);
    }
    
    private void assertSetResult(Object[] result, Set<?> param) {

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : param) {
            Assert.assertTrue(set.contains(item));
        }
    }
}

class CollectionTestService {

    public void singleObjectList(List<Object> list) {
    }

    public void singleStringList(List<String> list) {
    }

    public void singleIntegerList(List<Integer> list) {
    }

    public void singleDoubleList(List<Double> list) {
    }

    public void singlePojoList(List<TestInfo> list) {
    }

    public void singleObjectSet(Set<Object> set) {
    }

    public void singleStringSet(Set<String> set) {
    }

    public void singleIntegerSet(Set<Integer> set) {
    }

    public void singleDoubleSet(Set<Double> set) {
    }

    public void singlePojoSet(Set<TestInfo> set) {
    }
}