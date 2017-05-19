package test;

import kys24.goods.service.StorageProperties;
import kys24.user.utils.YUUtils;

/**
 * @author Duolaimon
 *         17-5-12 下午8:41
 */
public class Test {

    @org.junit.Test
    public void testMessage(){
        YUUtils.getMessageStatus("15204696480");
    }

    @org.junit.Test
    public void  testPath(){
        StorageProperties s = new StorageProperties();
        System.out.print(s.getLocation());
    }
}
