# 购物车API文档

### demoncirno.cn/kys24

- ### `GET`   	`/cart/show`	显示购物车[先登录]
 ##### Responses 
  ###### Example Value:   /cart/show
  ```
      {
        "code": 200,
        "message": "购物车信息",
        "data": [
          {
            "commodityname": "鸡心",
            "price": 666,
            "num": 1
          }
        ]
      }
  ```
- ### `POST`  `/cart/commodities/{commodityid}`	添加商品到购物车[先登录]
  ##### Parameter
  commodityid           商品id
 ##### Responses 
  ###### Example Value:   /cart/commodities/100
  ```
        {
          "code": 200,
          "message": "购物车中商品信息",
          "data": {
            "100": 1
          }
        }
  ```
  - ### `DELETE`  `/cart/commodities/{commodityid}`	删除购物车商品[先登录]
    ##### Parameter
    commodityid           商品id
   ##### Responses 
   ###### Example Value:   /cart/commodities/100
    ```
        {
          "code": 402,
          "message": "购物车为空",
          "data": null
        }
    ```
    
 - ### `PUT`   	`/cart/commodities`	修改购物车中商品数量[先登录]
     ##### Parameter
     commodityid        商品id
     num                商品数量
     ##### Responses 
      ###### Example Value:   /cart/commodities?commodityid=100&num=5
      ```
          {
            "code": 200,
            "message": "购物车商品信息",
            "data": {
              "100": 5
            }
          }
      ```