package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
    public void testSingleList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Object[] param = new Object[] { "abc", 123, 12.34D, true };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

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
        Assert.assertTrue(result[0] instanceof List);

        list = (List<?>) result[0];
        index = 0;
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

        String[] param = new String[] { "abc", "def", "xyz" };
        String json = JSON.toJSONString(param);
        Object[] result = parser.parse(json);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item, param[index]);
            index++;
        }
    }

    @Test
    public void testSingleIntegerList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleIntegerList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Integer[] param = new Integer[] { 100, 200, 300 };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item, param[index]);
            index++;
        }
    }

    @Test
    public void testSingleDoubleList() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleDoubleList");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Double[] param = new Double[] { 12.34D, 43.21D, 8.8D };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {
            Assert.assertNotNull(item);
            Assert.assertEquals(item, param[index]);
            index++;
        }
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
        Object[] result = parser.parse(JSON.toJSONString(testInfos));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof List);

        List<?> list = (List<?>) result[0];
        int index = 0;
        for (Object item : list) {

            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof TestInfo);

            TestInfo testInfo = (TestInfo) item;
            Assert.assertEquals(testInfo.getS(), testInfos.get(index).getS());
            Assert.assertEquals(testInfo.getI(), testInfos.get(index).getI());
            Assert.assertEquals(testInfo.getDd(), testInfos.get(index).getDd());
            index++;
        }
    }

    @Test
    public void testSingleSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Object[] param = new Object[] { "abc", 123, 12.34D, true };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : param) {
            Assert.assertNotNull(item);
        }

        Set<TestInfo> testInfos = new HashSet<TestInfo>();
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
        Assert.assertTrue(result[0] instanceof Set);

        set = (Set<?>) result[0];
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

        String[] param = new String[] { "abc", "def", "xyz" };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : param) {
            Assert.assertTrue(set.contains(item));
        }
    }

    @Test
    public void testSingleIntegerSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleIntegerSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Integer[] param = new Integer[] { 100, 200, 300 };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : param) {
            Assert.assertTrue(set.contains(item));
        }
    }

    @Test
    public void testSingleDoubleSet() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleDoubleSet");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Double[] param = new Double[] { 12.34D, 43.21D, 8.8D };
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> set = (Set<?>) result[0];
        for (Object item : param) {
            Assert.assertTrue(set.contains(item));
        }
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
        Object[] result = parser.parse(JSON.toJSONString(testInfos));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof Set);

        Set<?> list = (Set<?>) result[0];
        for (Object item : list) {

            Assert.assertNotNull(item);
            Assert.assertTrue(item instanceof TestInfo);

            TestInfo testInfo = (TestInfo) item;
            Assert.assertNotNull(testInfo.getS());
            Assert.assertNotNull(testInfo.getI());
            Assert.assertNotNull(testInfo.getDd());
        }
    }
}

class CollectionTestService {

    @SuppressWarnings("rawtypes")
    public void singleList(List list) {
    }

    public void singleStringList(List<String> list) {
    }

    public void singleIntegerList(List<Integer> list) {
    }

    public void singleDoubleList(List<Double> list) {
    }

    public void singlePojoList(List<TestInfo> list) {
    }

    @SuppressWarnings("rawtypes")
    public void singleSet(Set set) {
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