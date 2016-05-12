package annotator.hash;

import junit.framework.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashFunctionTest {

    @Test
    public void testHashFunction() {
        String password = "admin";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Assert.assertEquals("Does not match!", true, BCrypt.checkpw(password, hashed));

        password = "GandalfIsTheBest1238";
        hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Assert.assertEquals("Does not match!", true, BCrypt.checkpw(password, hashed));

        password = "/*O385886024í+=áěíáěščř";
        hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Assert.assertEquals("Does not match!", true, BCrypt.checkpw(password, hashed));

        password = "/*O385886024í+=áěíáěščř";
        hashed = BCrypt.hashpw(password.substring(1), BCrypt.gensalt());
        Assert.assertEquals("Does math!", false, BCrypt.checkpw(password, hashed));
    }

}
