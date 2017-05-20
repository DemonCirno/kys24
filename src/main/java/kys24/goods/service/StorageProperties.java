package kys24.goods.service;

import org.springframework.stereotype.Component;

/**
 * @author Duolaimon
 *         17-5-2 下午1:02
 */
@Component
public class StorageProperties {

    /**
     * 适用于liunx的相对路径
     */
/*
    private String location = this.getClass().getClassLoader().getResource("/").getPath()+"/image";
*/

    /**
     * 适合cirno的windows的绝对路径
     */

   private String location = "D:\\develop\\IDEA\\kys24\\src\\main\\webapp\\WEB-INF\\classes\\image";


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }
}
