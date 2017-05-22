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

- ### `GET`   	`/pay/order/{orderid}`	根据订单ID查询订单[先登录]
 ##### Responses 
  ###### Example Value:   /pay/order/6d9f0d91e22d
  ```
    {
      "code": 200,
      "message": "6d9f0d91e22d号订单信息",
      "data": {
        "orderId": "6d9f0d91e22d",
        "userId": 1127,
        "orderAddress": "绥化",
        "status": 0,
        "totalCount": 1,
        "totalPrice": 666,
        "createTime": 1495444473000,
        "updateTime": 1495444473000,
        "orderItems": [
          {
            "orderitemId": 12,
            "orderId": "6d9f0d91e22d",
            "commodityId": 100,
            "commodityPrice": 666,
            "count": 1,
            "createTime": 1495444473000,
            "updateTime": 1495444473000
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
        "orderId": "8f7c2a253d59",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495455910000,
        "updateTime": 1495455910000
      },
      {
        "orderId": "a1adf1af29a8",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448497000,
        "updateTime": 1495448497000
      },
      {
        "orderId": "410ffee4303f",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448492000,
        "updateTime": 1495448492000
      },
      {
        "orderId": "411717bd50ed",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448485000,
        "updateTime": 1495448485000
      },
      {
        "orderId": "19129c6acff4",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448467000,
        "updateTime": 1495448467000
      },
      {
        "orderId": "bf03358846f0",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448461000,
        "updateTime": 1495448461000
      },
      {
        "orderId": "43756786b6b3",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448456000,
        "updateTime": 1495448456000
      },
      {
        "orderId": "1055f08d0032",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448450000,
        "updateTime": 1495448450000
      },
      {
        "orderId": "3ee899d243ae",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448443000,
        "updateTime": 1495448443000
      },
      {
        "orderId": "c1ab1b755560",
        "userId": 1127,
        "orderAddress": "随意",
        "status": 0,
        "totalCount": 233,
        "totalPrice": 233,
        "createTime": 1495448430000,
        "updateTime": 1495448430000
      }
    ],
    {
      "everyPage": 10,
      "totalCount": 12,
      "totalPage": 2,
      "currentPage": 1,
      "beginIndex": 0,
      "hasPrePage": false,
      "hasNextPage": true
    }
  ]
  ```
- ### `GET`   	`/pay/user/{count}`	个人中心（分页）[先登录]
 ##### Parameters
    count               当前页
 ##### Responses 
  ###### Example Value:   /pay/user/1
  ```
 {
   "code": 200,
   "message": "个人订单信息",
   "data": [
     {
       "orderId": "8f7c2a253d59",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495455910000,
       "updateTime": 1495455910000
     },
     {
       "orderId": "a1adf1af29a8",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448497000,
       "updateTime": 1495448497000
     },
     {
       "orderId": "410ffee4303f",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448492000,
       "updateTime": 1495448492000
     },
     {
       "orderId": "411717bd50ed",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448485000,
       "updateTime": 1495448485000
     },
     {
       "orderId": "19129c6acff4",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448467000,
       "updateTime": 1495448467000
     },
     {
       "orderId": "bf03358846f0",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448461000,
       "updateTime": 1495448461000
     },
     {
       "orderId": "43756786b6b3",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448456000,
       "updateTime": 1495448456000
     },
     {
       "orderId": "1055f08d0032",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448450000,
       "updateTime": 1495448450000
     },
     {
       "orderId": "3ee899d243ae",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448443000,
       "updateTime": 1495448443000
     },
     {
       "orderId": "c1ab1b755560",
       "userId": 1127,
       "orderAddress": "随意",
       "status": 0,
       "totalCount": 233,
       "totalPrice": 233,
       "createTime": 1495448430000,
       "updateTime": 1495448430000
     },
     {
       "everyPage": 10,
       "totalCount": 12,
       "totalPage": 2,
       "currentPage": 1,
       "beginIndex": 0,
       "hasPrePage": false,
       "hasNextPage": true
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