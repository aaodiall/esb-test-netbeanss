package consumers;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author zaki
 */
@WebService(serviceName = "consumer4")
public class Consumer4 extends ConsumerBoss {

    private static final Integer ID = 4;
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
            invoker = new Thread(new Invoker4());
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