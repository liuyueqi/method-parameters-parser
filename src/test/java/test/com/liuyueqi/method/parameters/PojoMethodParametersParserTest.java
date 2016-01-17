package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.DefaultMethodParametersParserFactory;
import com.liuyueqi.method.parameters.MethodParametersParser;

public class PojoMethodParametersParserTest extends AbstractMethodParametersParserTest {

    @Override
    protected Class<?> serviceType() {
        return PojoTestService.class;
    }

    @Test
    public void testSinglePojo() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singlePojo");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        TestInfo param = createTestInfo(1);

        List<String> sList = new ArrayList<String>();
        List<TestInfo> testList = new ArrayList<TestInfo>();
        Map<String, Long> slMap = new HashMap<String, Long>();
        Map<String, TestInfo> testMap = new HashMap<String, TestInfo>();

        for (int i = 0; i < 5; i++) {

            sList.add("str" + i);
            testList.add(createTestInfo(i + 2));
            slMap.put(String.valueOf(i), Integer.valueOf(i).longValue());
            testMap.put(String.valueOf(i), createTestInfo(i + 2));
        }

        param.setSList(sList);
        param.setTestList(testList);
        param.setSlMap(slMap);
        param.setTestMap(testMap);

        // 参数格式：[]
        String json = JSON.toJSONString(param);
        Object[] result = parser.parse(json);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof TestInfo);
        Assert.assertEquals(result[0], param);
        
        // 参数格式：{'testInfo': {}}
        result = parser.parse("{'testInfo': " + json + "}");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof TestInfo);
        Assert.assertEquals(result[0], param);
    }

    @Test
    public void testTwoPojos() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("twoPojos");
        MethodParametersParser parser = factory.getMethodParametersParser(method);
        
        TestInfo testInfo1 = createTestInfo(1);
        TestInfo testInfo2 = createTestInfo(2);  

        List<String> sList = new ArrayList<String>();
        List<TestInfo> testList = new ArrayList<TestInfo>();
        Map<String, Long> slMap = new HashMap<String, Long>();
        Map<String, TestInfo> testMap = new HashMap<String, TestInfo>();

        for (int i = 0; i < 5; i++) {

            sList.add("str" + i);
            testList.add(createTestInfo(i + 2));
            slMap.put(String.valueOf(i), Integer.valueOf(i).longValue());
            testMap.put(String.valueOf(i), createTestInfo(i + 2));
        }
        
        testInfo1.setSList(sList);
        testInfo2.setTestList(testList);
        testInfo1.setSlMap(slMap);
        testInfo2.setTestMap(testMap);

        // 参数格式：[]
        Object[] result = parser.parse(JSON.toJSONString(new Object[] { testInfo1, testInfo2 }));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 2);
        Assert.assertTrue(result[0] instanceof TestInfo);
        Assert.assertTrue(result[1] instanceof TestInfo);
        Assert.assertEquals(result[0], testInfo1);
        Assert.assertEquals(result[1], testInfo2);
        
        // 参数格式：{'testInfo1': {}, 'testInfo2': {}}
        result = parser.parse("{'testInfo1': " + JSON.toJSONString(testInfo1) + ", 'testInfo2': "
                + JSON.toJSONString(testInfo2) + "}");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 2);
        Assert.assertTrue(result[0] instanceof TestInfo);
        Assert.assertTrue(result[1] instanceof TestInfo);
        Assert.assertEquals(result[0], testInfo1);
        Assert.assertEquals(result[1], testInfo2);
    }

    @Test
    public void testMultiParams() {
        
        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("multiParams");
        MethodParametersParser parser = factory.getMethodParametersParser(method);
        
        List<TestInfo> testInfos = new ArrayList<TestInfo>();
        for (int i = 0; i < 5; i++) {
            
            TestInfo testInfo = createTestInfo(i + 1);
            
            List<String> sList = new ArrayList<String>();
            List<TestInfo> testList = new ArrayList<TestInfo>();
            Map<String, Long> slMap = new HashMap<String, Long>();
            Map<String, TestInfo> testMap = new HashMap<String, TestInfo>();
            
            for (int j = 0; j < i; j++) {
                
                sList.add("str" + j);
                testList.add(createTestInfo(j + i));
                slMap.put(String.valueOf(j), Integer.valueOf(j).longValue());
                testMap.put(String.valueOf(j), createTestInfo(j + i));
            }
            
            testInfo.setSList(sList);
            testInfo.setTestList(testList);
            testInfo.setSlMap(slMap);
            testInfo.setTestMap(testMap);
            
            testInfos.add(testInfo);
        }
        
        // 参数格式：[]
        
        Object[] param = new Object[] {9999L, testInfos, "OK"};
        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 3);
        
        int index = 0;
        for (Object item : result) {
            Assert.assertEquals(item, param[index++]);
        }
        
        // 参数格式：{arg1: 9999L, arg2: [], arg3: 'OK'}
        result = parser.parse("{arg1: " + 9999L + ", arg2: " + JSON.toJSONString(testInfos) + ", arg3: 'OK'}");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 3);
        Assert.assertEquals(result[0], 9999L);
        Assert.assertEquals(result[1], testInfos);
        Assert.assertEquals(result[2], "OK");
    }

    private TestInfo createTestInfo(int i) {

        TestInfo testInfo = new TestInfo();
        testInfo.setS("abc");
        testInfo.setIi(i * 11);
        testInfo.setI(i);
        testInfo.setLl(i * 1111L);
        testInfo.setL(i * 111L);
        testInfo.setDd(i * 11.11D);
        testInfo.setD(i * 1.1D);
        testInfo.setBb(false);
        testInfo.setB(true);

        return testInfo;
    }
}

class PojoTestService {

    public void singlePojo(TestInfo testInfo) {
    }

    public void twoPojos(TestInfo testInfo1, TestInfo testInfo2) {
    }

    public void multiParams(Long arg1, List<TestInfo> arg2, String arg3) {
    }
}
