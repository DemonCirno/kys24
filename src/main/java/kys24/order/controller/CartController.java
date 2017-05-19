package kys24.order.controller;

import kys24.goods.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 商品购物车session实现
 *
 * @author cirno
 *
 */

@RequestMapping("/cart")
@RestController
public class CartController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    /**
     * 用于处理商品的业务逻辑
     */

    private CommodityService commodityService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     * 添加商品到购物车
     *
     * @return
     */

    @RequestMapping("")
    public void add(HttpSession httpSession,String commodityid){

    }
    /**
     * 显示购物车
     *
     * @return
     */

    /**
     * 购物车删除与修改操作
     *
     * @return
     */

}