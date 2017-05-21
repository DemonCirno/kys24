package kys24.order.controller;

import kys24.order.dto.OrderResult;
import kys24.order.dto.RestResult;
import kys24.order.model.Order;
import kys24.order.model.OrderItem;
import kys24.order.service.IOrderItemService;
import kys24.order.service.IOrderService;
import kys24.user.model.User;
import kys24.user.utils.Page;
import kys24.user.utils.PageUtil;
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
 * Created by cirno on 2017/5/21.
 */
@CrossOrigin
@RestController
@RequestMapping("/pay")
public class OrderController {
    //每页显示十条数据
    private final int EVERYPAGE = 10;
    /**
     * 处理订单的业务逻辑
     */
    private final IOrderService orderService;

    /**
     * 处理订单项的业务逻辑
     */
    private final IOrderItemService orderItemService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderController(IOrderService orderService,IOrderItemService orderItemService){
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    /**
     * 根据订单ID查询订单
     * @param orderid
     * @return
     */
    @RequestMapping(value="/order/{orderid}",method = RequestMethod.GET)
    public RestResult showOrder(HttpServletRequest httpServletRequest,@PathVariable String orderid){
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            OrderResult orderResult = new OrderResult();
            Order order = orderService.queryOrderById(orderid);
            List<OrderItem> list = orderItemService.queryOrderItemsById(order.getOrderId());
            BeanUtils.copyProperties(orderResult, order);
            orderResult.setOrderItems(list);
            return new RestResult(200, orderid + "号订单信息", orderResult);
        }
    }

    /**
     * 生成订单流程
     * 1.添加订单
     * 2.添加订单项
     * 3.更新商品数量
     * @return
     */
    @RequestMapping(value="/order",method = RequestMethod.POST)
    public RestResult addOrder(HttpServletRequest httpServletRequest,String address,Integer totalnum,Float totalprice){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Order order = new Order();
            order.setOrderId(UUID.randomUUID()+"");
            order.setStatus(0);
            order.setOrderAddress(address);
            order.setTotalCount(totalnum);
            order.setTotalPrice(totalprice);
            order.setUserId(user.getUserId());

            //获取购物车
            HttpSession httpSession = httpServletRequest.getSession();
            HashMap<String,Integer> cart = (HashMap<String, Integer>) httpSession.getAttribute("cart");
            if(cart == null){
                return new RestResult(402,"购物车为空",null);
            }else {
                orderService.addOrder(order,cart);
                return new RestResult(200, "添加订单成功", 1);

            }
        }
    }

    /**
     * 根据订单id取消订单
     * @param orderid
     * @return
     */
    @RequestMapping(value="/order",method = RequestMethod.DELETE)
    public RestResult deleteOrder(HttpServletRequest httpServletRequest,String orderid){
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            orderService.deleteOrder(orderid);
            return new RestResult(200,"删除订单成功",1);
        }
    }

    /**
     * 更改订单状态
     * @param orderid
     * @param status
     * @return
     */
    @RequestMapping(value="/order",method = RequestMethod.PUT)
    public RestResult updateOrder(HttpServletRequest httpServletRequest,String orderid,Integer status){
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return new RestResult<>(400, "用户未登录", null);
        } else {
            Order order = orderService.queryOrderById(orderid);
            order.setStatus(status);
            int count = orderService.updateOrder(order);
            if (count > 0) {
                return new RestResult(200, "修改订单状态成功", order.getStatus());
            } else {
                return new RestResult(405, "修改订单状态失败", null);
            }
        }
    }

    //后台方法

    /**
     * 查询所有订单信息（分页）
     * @param count
     * @return
     */
    @RequestMapping(value = "/orders/{count}",method = RequestMethod.GET)
    public RestResult showOrders(@PathVariable Integer count){
        List results = new ArrayList();
        Page page = PageUtil.createPage(EVERYPAGE,orderService.count(),count);
        List<Order> list = orderService.queryAllOrder(page);
        for(Order order:list){
            List<OrderItem> orderItems = orderItemService.queryOrderItemsById(order.getOrderId());
            OrderResult result = new OrderResult();
            BeanUtils.copyProperties(result,order);
            result.setOrderItems(orderItems);
            results.add(result);
            results.add(page);
        }
        return new RestResult(200,"订单分页信息",results);
    }

    /**
     * 个人中心
     * @param userid
     * @param count
     * @return
     */
    @RequestMapping(value = "orders",method = RequestMethod.GET)
    public RestResult myorders(Integer userid,Integer count){
        Page page = PageUtil.createPage(EVERYPAGE,orderService.countByuserId(userid),count);
        List list = orderService.queryOrderByuserId(userid,page);
        list.add(page);
        return new RestResult(200,"个人订单信息",list);
    }
}
