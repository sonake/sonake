package bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xzyuan
 */
@Data
public class HcAuthUser implements Serializable {

    private static final long serialVersionUID = -1748285640320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
