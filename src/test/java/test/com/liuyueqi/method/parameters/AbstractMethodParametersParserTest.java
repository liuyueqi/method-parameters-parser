package test.com.liuyueqi.method.parameters;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
    
    protected abstract Class<?> serviceType();
}
