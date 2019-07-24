package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.liuyueqi.method.parameters.DefaultMethodParametersParserFactory;
import com.liuyueqi.method.parameters.MethodParametersParser;

public class BaseTypeMethodParametersParserTest extends AbstractMethodParametersParserTest {

    @Override
    protected Class<?> serviceType() {
        return BaseTypeTestService.class;
    }
    
    @Test
    public void testParseSingleString() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleString");
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
    public void testParseSingleInteger() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleInteger");
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
    public void testParseSingleShort() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleShort");
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
    public void testParseSingleLong() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleLong");
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
    public void testParseSingleDouble() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleDouble");
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
    public void testParseSingleBoolean() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singleBoolean");
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
    public void testStringAndInteger() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("stringAndInteger");
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
    public void testSinglePrimitiveInteger() {

        DefaultMethodParametersParserFactory factory = DefaultMethodParametersParserFactory.getInstance();

        Method method = lookupMethod("singlePrimitiveInteger");
        MethodParametersParser parser = factory.getMethodParametersParser(method);

        Object[] result = parser.parse(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.length, 1);
        Assert.assertEquals(result[0], 0);
    }
}

class BaseTypeTestService {

    public void singleString(String arg1) {
    }

    public void singleInteger(Integer arg1) {
    }

    public void singleShort(Short arg1) {
    }

    public void singleLong(Long arg1) {
    }

    public void singleDouble(Double arg1) {
    }

    public void singleBoolean(Boolean arg1) {
    }

    public void stringAndInteger(String arg1, Integer arg2) {
    }

    public void singlePrimitiveInteger(int arg1) {
    }
}