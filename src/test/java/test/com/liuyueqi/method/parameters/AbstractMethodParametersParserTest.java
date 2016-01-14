package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public abstract class AbstractMethodParametersParserTest {

    private Map<String, Method> methodMap = new HashMap<String, Method>();

    @BeforeClass
    public void setUp() throws Exception {

        for (Method method : serviceType().getMethods()) {
            this.methodMap.put(method.getName(), method);
        }
    }
    
    protected Method lookupMethod(String name) {
        return this.methodMap.get(name);
    }

    protected void assertTestInfoEquals(TestInfo actual, TestInfo expect) {

        Assert.assertEquals(actual.getS(), expect.getS());
        Assert.assertEquals(actual.getIi(), expect.getIi());
        Assert.assertEquals(actual.getI(), expect.getI());
        Assert.assertEquals(actual.getLl(), expect.getLl());
        Assert.assertEquals(actual.getL(), expect.getL());
        Assert.assertEquals(actual.getDd(), expect.getDd());
        Assert.assertEquals(actual.getD(), expect.getD());
        Assert.assertEquals(actual.getBb(), expect.getBb());
        Assert.assertEquals(actual.isB(), expect.isB());
    }
    
    protected abstract Class<?> serviceType();
}
