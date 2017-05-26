# 订单API文档

### demoncirno.cn/kys24

## 用户登录

- ### `POST` `userController/login?userPhone=123456789&userPassword=123456789` 登录方法

## 前台增删改查方法

- ### `POST`   	`/pay/order`	生成订单[先登录]
 ##### Parameters

    address             收货地址
    
    totalnum            购物商品总数
    
    totalprice          购物商品总价

 ##### Responses 
  ###### Example Value:   /pay/order
  ```
   {
    "code": 200,
    "message": "添加订单成功",
    "data": 1
   }
  ```

- ### `DELETE`   	`/pay/order/{orderid}`	删除订单[先登录]
 ##### Parameters
    orderid             订单id
 ##### Responses 
 ###### Example Value:   /pay/order/6d9f0d91e22d
  ```
    {
      "code": 200,
      "message": "删除订单成功",
      "data": 1
    }
  ```
 
- ### `PUT`   	`/pay/order`	更改订单状态[先登录]
 ##### Parameters
    orderid             订单id
    status              订单状态
 ##### Responses 
 ###### Example Value:   /pay/order?orderid=6d9f0d91e22d&status=1
  ```
 {
   "code": 200,
   "message": "修改订单状态成功",
   "data": 1
 }
  ```

- ### `GET`   	`/pay/order/{orderid}`	根据订单ID查询订单
 ##### Responses 
  ###### Example Value:   /pay/order/6d9f0d91e22d
  ```
   {
     "code": 200,
     "message": "ba5e77cf964e号订单信息",
     "data": {
       "orderId": "ba5e77cf964e",
       "userId": 1,
       "orderAddress": "测试",
       "status": 0,
       "totalCount": 10,
       "totalPrice": 233,
       "createTime": 1495708469000,
       "updateTime": 1495708469000,
       "orderItems": [
         {
           "orderitemId": 8,
           "orderId": "ba5e77cf964e",
           "commodityId": 66,
           "commodityPrice": 233.2,
           "count": 2,
           "createTime": 1495708469000,
           "updateTime": 1495708469000
         },
         {
           "orderitemId": 9,
           "orderId": "ba5e77cf964e",
           "commodityId": 67,
           "commodityPrice": 233.2,
           "count": 3,
           "createTime": 1495708470000,
           "updateTime": 1495708470000
         },
         {
           "orderitemId": 10,
           "orderId": "ba5e77cf964e",
           "commodityId": 2,
           "commodityPrice": 233.2,
           "count": 1,
           "createTime": 1495708470000,
           "updateTime": 1495708470000
         },
         {
           "orderitemId": 11,
           "orderId": "ba5e77cf964e",
           "commodityId": 68,
           "commodityPrice": 233.2,
           "count": 4,
           "createTime": 1495708470000,
           "updateTime": 1495708470000
         },
         {
           "orderitemId": 12,
           "orderId": "ba5e77cf964e",
           "commodityId": 69,
           "commodityPrice": 233.2,
           "count": 5,
           "createTime": 1495708470000,
           "updateTime": 1495708470000
         }
       ]
     }
   }
  ```

## 订单后台方法 

- ### `GET`   	`/pay/orders/{count}`	查询所有订单信息（分页）
 ##### Parameters
    count             当前页
 ##### Responses 
  ###### Example Value:   /pay/orders/1
  ```
 [
   [
     {
       "orderId": "ba5e77cf964e",
       "userId": 1,
       "orderAddress": "测试",
       "status": 0,
       "totalCount": 10,
       "totalPrice": 233,
       "createTime": 1495708469000,
       "updateTime": 1495708469000
     }
   ],
   {
     "everyPage": 10,
     "totalCount": 1,
     "totalPage": 1,
     "currentPage": 1,
     "beginIndex": 0,
     "hasPrePage": false,
     "hasNextPage": false
   }
 ]
  ```
- ### `GET`   	`/pay/user`	个人中心（分页）
 ##### Parameters
    userid              用户id
    count               当前页
 ##### Responses 
  ###### Example Value:   pay/users?userid=1&count=1
  ```
 {
   "code": 200,
   "message": "个人订单信息",
   "data": [
     {
       "orderId": "ba5e77cf964e",
       "userId": 1,
       "orderAddress": "测试",
       "status": 0,
       "totalCount": 10,
       "totalPrice": 233,
       "createTime": 1495708469000,
       "updateTime": 1495708469000
     },
     {
       "everyPage": 10,
       "totalCount": 1,
       "totalPage": 1,
       "currentPage": 1,
       "beginIndex": 0,
       "hasPrePage": false,
       "hasNextPage": false
     }
   ]
 }
  ```
- ### `GET`   	`/pay/goods/{commodityid}`	查询销量
 ##### Parameters
    commodityid         商品id
 ##### Responses 
 ###### Example Value:   /pay/goods/23
  ```
    1
  ```