package consumers;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author zaki
 */
@WebService(serviceName = "consumer5")
public class Consumer5 extends ConsumerBoss {

    private static final Integer ID = 5;
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
            invoker = new Thread(new Invoker5());
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
        return getConfig(ID);
    }
}
