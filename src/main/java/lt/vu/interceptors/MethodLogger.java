package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Logger
@Interceptor
public class MethodLogger implements Serializable {

    @AroundInvoke
    public Object performLogging(InvocationContext context) throws Exception {
        System.out.println("Called method: " + context.getMethod().getName());
        return context.proceed();
    }
}