# 商城API文档
## duolaimon.cn


- ### `GET`   	`/shop/commodities`	展示所有商品的信息

  ##### Parameter

  Name			Description

  pageSize			每页显示多少条数据,默认15

  pageNumber		当前页码,若不设置则返回所有页数

  ##### Responses 	

  ###### Example Value:    /shop/commodities?pageSize=4&pageNumber=2

```
{
  "pageSize": 4,
  "pages": [
      {
      "pageNumber": 2,
      "dataList": [
        {
          "commodityID": 12,
          "commodityBrand": 3230,
          "commodityVariety": 4141,
          "commodityName": "猪蹄",
          "commodityPrice": 30,
          "commodityPicture": ""
        },
        {
          "commodityID": 13,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 14,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 15,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        }
      ]
     }
  ]
}
```
---
- ### `GET`	`/shop/varieties/{varietyId}`	展示指定种类的商品

  使用位置：	筛选种类商品

##### Parameter

​	Name				Description

​	varietyId				种类id号

​	pageSize				每页显示多少条数据,默认15

​	pageNumber			当前页码,若不设置则返回所有页数

##### Responses

###### 	Example Value:/shop/varieties/4141

不存在：	

```
{
  "pageSize": 15,
  "pages": []
}
```

存在：

```
{
  "pageSize": 15,
  "pages": [
    {
      "pageNumber": 1,
      "dataList": [
        {
          "commodityID": 10,
          "commodityBrand": 3230,
          "commodityVariety": 4141,
          "commodityName": "猪蹄",
          "commodityPrice": 30,
          "commodityPicture": ""
        },
        {
          "commodityID": 12,
          "commodityBrand": 3230,
          "commodityVariety": 4141,
          "commodityName": "猪蹄",
          "commodityPrice": 30,
          "commodityPicture": ""
        }
      ]
    }
  ]
}
```

---

- ### `GET`	`/shop/brands/{brandId}`	展示指定品牌的商品

  使用位置：	筛选品牌商品

##### Parameter

​	Name				Description

​	brandId				品牌id号

​	pageSize				每页显示多少条数据,默认15

​	pageNumber			当前页码,若不设置则返回所有页数

##### Responses

###### 	Example Value:	/shop/brands/3123?pageSize=3

不存在：	同上

存在：

```
{
  "pageSize": 3,
  "pages": [
    {
      "pageNumber": 1,
      "dataList": [
        {
          "commodityID": 2,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 13,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 14,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        }
      ]
    },
    {
      "pageNumber": 2,
      "dataList": [
        {
          "commodityID": 15,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 16,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 17,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        }
      ]
    },
    {
      "pageNumber": 3,
      "dataList": [
        {
          "commodityID": 18,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 19,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 20,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        }
      ]
    }
  ]
}
```
---
- ### `GET`	`/shop/commodities/{commodityId}`	展示指定id号的商品	

  使用位置：	商品单页

##### Parameter

​	Name				Description

​	commodityId			商品id号

##### Responses

###### 	Example Value:	/shop/commodities/10

不存在：				[ ]

存在：

```
{
  "commodityId": 10,
  "commodityName": "猪蹄",
  "commodityBrand": 3230,
  "commodityVariety": 4141,
  "commodityDepict": "标签",
  "commodityPrice": 30,
  "commodityAmount": 200,
  "commodityLeavenum": 80,
  "commodityStandard": 15,
  "operator": 13,
  "createTime": 1493688745000,
  "updateTime": 1493688741000,
  "commodityPicture": ""
}
```

---

### `POST`	`/shop/search`	关键字查找商品

​	使用位置：	商城搜索

##### Parameter

​	表单属性名			Description

​	searchKey			搜索关键字

​	pageSize				每页显示多少条数据,默认15

​	pageNumber			当前页码,若不设置则返回所有页数

##### Responses


##### 	Example Value:	/shop/search?pageSize=15&pageNumber=1


存在：

```
{
  "num": 2,
  "pageSize": 15,
  "pages": [
    {
      "pageNumber": 1,
      "dataList": [
        {
          "commodityID": 1,
          "commodityBrand": 1231,
          "commodityVariety": 3213,
          "commodityName": "饺子",
          "commodityPrice": 100,
          "commodityPicture": null
        },
        {
          "commodityID": 2,
          "commodityBrand": 3123,
          "commodityVariety": 432,
          "commodityName": "鸭脖子",
          "commodityPrice": 100,
          "commodityPicture": null
        }
    }
  ]
} 
```
不存在：

```
{
  "num": 0,
  "pageResult": {
    "pageSize": 15,
    "pages": []
  }
}
<<<<<<< HEAD
```
