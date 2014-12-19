/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

/**
 *
 * @author alpha
 */
public class ProviderBean {
     private Integer id;
     private Integer processingTime;
     private Integer sizeDataExchange;

    public ProviderBean(Integer id,Integer size, Integer processingTime) {
        this.id=id;
        this.sizeDataExchange=size;
        this.processingTime=processingTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    public Integer getSizeDataExchange() {
        return sizeDataExchange;
    }

    public void setSizeDataExchange(Integer sizeDataExchange) {
        this.sizeDataExchange = sizeDataExchange;
    }

    public  String fillString(char fillChar, int count){  
       // creates a string of 'x' repeating characters  
       char[] chars = new char[count];  
       java.util.Arrays.fill(chars, fillChar);  
       return new String(chars);  
   }  
     

    
    
}
