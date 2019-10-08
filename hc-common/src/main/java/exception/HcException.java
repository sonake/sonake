package exception;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 11:31
 * @description：自定义异常
 * @version:
 */
public class HcException extends Exception{
    private static final long serialVersionUID = -6916154462432027437L;

    private Integer Code;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public HcException(String message){
        super(message);
    }
}
