package kys24.order.controller;

import kys24.goods.dao.CommodityDao;
import kys24.goods.entity.Commodity;
import kys24.order.dto.CartItems;
import kys24.order.dto.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    private CommodityDao commodityDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public void setCommodityDao(CommodityDao commodityDao) {
        this.commodityDao = commodityDao;
    }

    /**
     * 显示购物车
     */

    @RequestMapping(value="/show",method = RequestMethod.GET)
    public RestResult show (HttpServletRequest httpServletRequest){
        logger.info("/cart/show");
        List list = new ArrayList<CartItems>();
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            HttpSession httpSession = httpServletRequest.getSession();
            HashMap<String,Integer> cart = (HashMap<String, Integer>) httpSession.getAttribute("cart");
            if(cart==null){
                return new RestResult(402,"购物车为空",null);
            }else{
                /**
                 * 获取entry实体对象，通过实体获取键和值
                 */
                Set<Map.Entry<String, Integer>> set = cart.entrySet();
                for(Map.Entry<String,Integer> entry:set){
                    Integer id = Integer.parseInt(entry.getKey());
                    Commodity c = commodityDao.queryCommodityByID(id);
                    if(c!=null){
                        CartItems cartItems = new CartItems();
                        cartItems.setCommodityname((c.getCommodityName()==null)?"商品名不存在":c.getCommodityName());
                        cartItems.setPrice((c.getCommodityPrice()==null)?0:c.getCommodityPrice());
                        cartItems.setNum((entry.getValue()==null)?0:entry.getValue());
                        list.add(cartItems);
                    }
                }
                return new RestResult(200,"购物车信息",list);
            }
        }
    }
    /**
     * 添加商品到购物车
     *
     * @return
     */
    @RequestMapping(value = "/commodities/{commodityid}", method = RequestMethod.POST)
    public RestResult add(HttpServletRequest httpServletRequest, @PathVariable Integer commodityid) {
        logger.info("/cart/commodities");
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Commodity commodity = commodityDao.queryCommodityByID(commodityid);
            if (commodity == null) {
                return new RestResult<>(401, "该商品不存在", null);
            } else {
                /**
                 * 获取购物车
                 */
                HttpSession session = httpServletRequest.getSession();
                Map<String, Integer> cart;
                cart = (Map<String, Integer>) session
                        .getAttribute("cart");
                if (cart == null) {
                    cart = new HashMap<>();
                }
                String id = "" + commodity.getCommodityId();

                Integer count = cart.put(id, 1);
                if (count != null) {
                    cart.put(id, count + 1);
                }

                /**
                 * 将购物车放入session
                 */
                session.setAttribute("cart", cart);
                return new RestResult<>(200, "购物车中商品信息", cart);
            }
        }
    }

    /**
     * 删除购物车商品
     *
     * @return
     */
    @RequestMapping(value = "/commodities/{commodityid}", method = RequestMethod.DELETE)
    public RestResult delete(HttpServletRequest httpServletRequest,@PathVariable Integer commodityid) {
        logger.info("/cart/commodities[DELETE]");
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Commodity commodity = commodityDao.queryCommodityByID(commodityid);
            if (commodity == null) {
                return new RestResult<>(401, "该商品不存在", null);
            } else {
                /**
                 * 获取购物车
                 */
                HttpSession session = httpServletRequest.getSession();
                Map<String, Integer> cart = (Map<String, Integer>) session
                        .getAttribute("cart");
                if(cart==null){
                    return new RestResult<>(402,"购物车为空",null);
                }else{
                    String id = commodity.getCommodityId()+"";
                    cart.remove(id);
                    if (cart.size() == 0) {
                        httpServletRequest.getSession().removeAttribute("cart");
                        return new RestResult<>(402,"购物车为空",null);
                    }
                    session.setAttribute("cart", cart);
                    return new RestResult<>(200,"购物车信息",cart);
                }
            }
        }
    }

    /**
     * 修改购物车中商品数量
     */
    @RequestMapping(value = "/commodities", method = RequestMethod.PUT)
    public RestResult update(HttpServletRequest httpServletRequest,Integer commodityid,Integer num) {
        logger.info("/cart/commodities[PUT]");
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Commodity commodity = commodityDao.queryCommodityByID(commodityid);
            if (commodity == null) {
                return new RestResult<>(401, "该商品不存在", null);
            } else {
                /**
                 * 获取购物车
                 */
                HttpSession session = httpServletRequest.getSession();
                Map<String, Integer> cart = (Map<String, Integer>) session
                        .getAttribute("cart");
                if (cart == null) {
                    return new RestResult<>(402, "购物车为空", null);
                } else {
                    String id = commodity.getCommodityId() + "";
                    // 修改商品的数量
                    if (num == 0) {
                        cart.remove(id); // 将商品从购物车中移除
                    } else {
                        cart.put(id, num);
                    }
                    httpServletRequest.getSession().setAttribute("cart", cart);
                    return new RestResult(200,"购物车商品信息",cart);
                }
            }
        }
    }
}