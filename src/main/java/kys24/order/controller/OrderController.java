package kys24.order.controller;

import kys24.order.dto.OrderResult;
import kys24.order.dto.RestResult;
import kys24.order.model.Order;
import kys24.order.model.OrderItem;
import kys24.order.service.IOrderItemService;
import kys24.order.service.IOrderService;
import kys24.user.model.User;
import kys24.user.service.IUserService;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by cirno
 * on 2017/5/21.
 */
@CrossOrigin
@RestController
@RequestMapping("/pay")
public class OrderController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    /**
     * 每页显示十条数据
     */
    private final int EVERYPAGE = 10;

    /**
     * 处理订单的业务逻辑
     */
    private final IOrderService orderService;

    /**
     * 处理订单项的业务逻辑
     */
    private final IOrderItemService orderItemService;

    /**
     * 处理用户的业务逻辑
     */
    private final IUserService userService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderController(IOrderService orderService,IOrderItemService orderItemService,IUserService userService){
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.userService = userService;
    }

    /**
     * 根据订单ID查询订单
     */
    @RequestMapping(value="/order/{orderid}",method = RequestMethod.GET)
    public RestResult showOrder(@PathVariable String orderid){
        logger.info("/pay/order?orderid={}:[GET]:(*^__^*) return the order information", orderid);
        Integer totalnum = 0;
        Float totalprice = 0.0f;

            OrderResult orderResult = new OrderResult();
            Order order = orderService.queryOrderById(orderid);
            if(order!=null) {
                List<OrderItem> list = orderItemService.queryOrderItemsById(order.getOrderId());
                for(OrderItem items:list){
                    totalnum = totalnum + items.getCount();
                    totalprice = totalprice + items.getCommodityPrice();
                }
                orderResult.setTotalCount(totalnum);
                orderResult.setTotalPrice(totalprice);
                BeanUtils.copyProperties(order, orderResult);
                orderResult.setOrderItems(list);
                return new RestResult<>(200, orderid + "号订单信息", orderResult);
            }else{
                return new RestResult<>(407,"订单号不存在",null);
            }

    }

    /**
     * 生成订单流程
     * 1.添加订单
     * 2.添加订单项
     * 3.更新商品数量
     */
    @RequestMapping(value="/order",method = RequestMethod.POST)
    public RestResult addOrder(HttpServletRequest httpServletRequest,@RequestParam("address") String address,
                               @RequestParam("totalnum") Integer totalnum,@RequestParam("totalprice") Float totalprice){
        logger.info("/pay/order?address={}&totalnum={}&totalprice={}:[POST]:(*^__^*) return all the commodities information", address,totalnum,totalprice);
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            String s = UUID.randomUUID().toString();
            String id = s.substring(0,8)+s.substring(9,13);
            Order order = new Order();
            order.setOrderId(id);
            order.setStatus(0);
            order.setOrderAddress(address);
            order.setTotalCount(totalnum);
            order.setTotalPrice(totalprice);
            order.setUserId(user.getUserId());

            //获取购物车
            HttpSession httpSession = httpServletRequest.getSession();
            HashMap<String,Integer> cart = (HashMap<String, Integer>) httpSession.getAttribute("cart");
            if(cart == null){
                return new RestResult<>(402,"购物车为空",null);
            }else {
                orderService.addOrder(order,cart);
                httpSession.removeAttribute("cart");
                return new RestResult<>(200, "添加订单成功", order.getOrderId());
            }
        }
    }

    /**
     * 取消订单
     */
    @RequestMapping(value="/order/{orderid}",method = RequestMethod.DELETE)
    public RestResult deleteOrder(HttpServletRequest httpServletRequest,@PathVariable String orderid){
        logger.info("/pay/order?orderid={}:[DELETE]:(*^__^*) return delete order information", orderid);
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            orderService.deleteOrder(orderid);
            return new RestResult<>(200,"删除订单成功",1);
        }
    }


    /**
     * 更改订单状态
     */
    @RequestMapping(value="/order",method = RequestMethod.PUT)
    public RestResult updateOrder(HttpServletRequest httpServletRequest, @Param("orderid") String orderid,@Param("status") Integer status){
        logger.info("/pay/order?orderid={}&status={}:[PUT]:(*^__^*) return update order information", orderid,status);
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Order order = orderService.queryOrderById(orderid);
            order.setStatus(status);
            int count = orderService.updateOrder(order);
            if (count > 0) {
                return new RestResult<>(200, "修改订单状态成功", order.getStatus());
            } else {
                return new RestResult<>(405, "修改订单状态失败", null);
            }
        }
    }

    //后台方法

    /**
     * 查询所有订单信息（分页）
     */
    @RequestMapping(value = "/orders/{count}",method = RequestMethod.GET)
    public List showOrders(@PathVariable Integer count){
        logger.info("/pay/orders?count={}:[GET]:(*^__^*) return order information by page", count);
        List<Object> result = new ArrayList<>();
        Page page = PageUtil.createPage(EVERYPAGE,orderService.count(),count);
        List<Order> list = orderService.queryAllOrder(page);
        result.add(list);
        result.add(page);
       return result;
    }

    /**
     * 个人中心（分页）
     */
    @RequestMapping(value = "users",method = RequestMethod.GET)
    public RestResult myorders(@RequestParam("userid") Integer userid,@RequestParam("count") Integer count){
        logger.info("/pay/users?count={}:[GET]:(*^__^*) return user's order information",count);
        User user = userService.selectById(userid);
        if(user == null){
            return new RestResult<>(400, "该用户不存在", null);
        }
        Page page = PageUtil.createPage(EVERYPAGE,orderService.countByuserId(user.getUserId()),count);
        List list = orderService.queryOrderByuserId(user.getUserId(),page);
        list.add(page);
        return new RestResult<>(200,"个人订单信息",list);
    }

    /**
     * 批量查询销量
     */
    @RequestMapping(value = "/goods",method = RequestMethod.POST)
    public List querycommoditysold(@RequestBody List<String> list){
        logger.info("/pay/goods?commodityid={}:[GET]:(*^__^*) return the order count information", list);
        HashMap<String,Object> map = new HashMap<>();
        List result = new ArrayList<>();
        for(String id:list){
            Integer commodityid = Integer.parseInt(id);
            Object count = orderItemService.querySumCount(commodityid);
            if(count == null){
                map.put("id",commodityid);
                map.put("num",0);
            }else{
                map.put("id",commodityid);
                map.put("num",count);
            }
            result.add(map);
        }
        return result;
    }
}