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

        List<Object> list = new ArrayList<Object>();
        List<String> sList = new ArrayList<String>();
        List<TestInfo> testList = new ArrayList<TestInfo>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        Map<String, Long> slMap = new HashMap<String, Long>();
        Map<String, TestInfo> testMap = new HashMap<String, TestInfo>();

        for (int i = 0; i < 5; i++) {

            if (i % 2 == 0) {
                list.add(createTestInfo(i << 2));
                map.put(i, createTestInfo(i << 3));
            } else {
                list.add("item" + i);
                map.put(i, "value" + i);
            }

            sList.add("str" + i);
            testList.add(createTestInfo(i + 2));
            slMap.put(String.valueOf(i), Integer.valueOf(i).longValue());
            testMap.put(String.valueOf(i), createTestInfo(i + 2));
        }

        param.setList(list);
        param.setSList(sList);
        param.setTestList(testList);
        param.setMap(map);
        param.setSlMap(slMap);
        param.setTestMap(testMap);

        Object[] result = parser.parse(JSON.toJSONString(param));

        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertTrue(result[0] instanceof TestInfo);

        TestInfo testInfo = (TestInfo) result[0];
        assertTestInfoEquals(testInfo, param);

        List<?> list2 = testInfo.getList();
        int index = 0;
        for (Object item : list) {

            Assert.assertNotNull(item);

            if (item instanceof TestInfo) {
                Assert.assertTrue(list2.get(index) instanceof TestInfo);
                assertTestInfoEquals((TestInfo) item, (TestInfo) list.get(index));
            } else {
                Assert.assertEquals(item, list.get(index));
            }

            index++;
        }

        List<String> sList2 = testInfo.getSList();
        index = 0;
        for (String s : sList2) {
            Assert.assertEquals(s, sList.get(index));
            index++;
        }

        List<TestInfo> testList2 = testInfo.getTestList();
        index = 0;
        for (TestInfo t : testList2) {
            assertTestInfoEquals(t, (TestInfo) testList2.get(index));
            index++;
        }
        
        Assert.assertEquals(testInfo.getMap(), map);
    }

    @Test
    public void testTwoPojos() {

    }

    @Test
    public void testMultiParams() {

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
