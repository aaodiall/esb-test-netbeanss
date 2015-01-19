package consumers;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author zaki
 */
@WebService(serviceName = "consumer3")
public class Consumer3 extends ConsumerBoss {

    private static final Integer ID = 3;
    private final ConsumerBean bean = new ConsumerBean(ID, 1);

    /**
     * We start calling the provider in this function
     *
     * @return "over"
     */
    @WebMethod(operationName = "invokeProvider")
    public String invokeProvider() {
        Thread[] invokers = new Thread[this.bean.getReqNumber()];
        for (Thread invoker : invokers) {
            invoker = new Thread(new Invoker3());
            invoker.start();
        }
        return "over";
    }

    /**
     * To test the consumer
     *
     * @return the configuration
     */
    @WebMethod(operationName = "test")
    public String test() throws Exception {
        setBean(bean);
        getConfig(ID);
        Thread.sleep(100);
        return invokeProvider();
    }
}