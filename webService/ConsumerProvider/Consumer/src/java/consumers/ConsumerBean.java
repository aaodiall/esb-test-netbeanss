package consumers;

/**
 *
 * @author zaki
 */
public class ConsumerBean {
     private Integer id;
     private Integer reqNumber;

    public ConsumerBean(Integer id, Integer reqNumber) {
        this.id=id;
        this.reqNumber=reqNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReqNumber() {
        return reqNumber;
    }

    public void setReqNumber(Integer reqNumber) {
        this.reqNumber = reqNumber;
    }   
    
}
