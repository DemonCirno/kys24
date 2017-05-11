# 种类后台API文档





## `GET`   	`/back/varieties`		展示所有种类的信息

**Parameters**

​	Name				Description

​	pageSize				每页显示多少条数据,默认15

​	pageNumber			当前页码,若不设置则返回所有页数

##### Responses

###### Example Value:		/back/varieties?pageSize=4&pageNumber=1

```
{
  "pageNumber": 1,
  "dataList": [
    {
      "varietyId": 1,
      "varietyName": "生鲜",
      "parentId": 0,
      "type": "生鲜",
      "createTime": 1493499013000,
      "updateTime": 1493499010000
    },
    {
      "varietyId": 2,
      "varietyName": "鸭货",
      "parentId": 1,
      "type": "鸭货",
      "createTime": 1493499063000,
      "updateTime": 1493499061000
    },
    {
      "varietyId": 3,
      "varietyName": "牛货",
      "parentId": 1,
      "type": "牛货",
      "createTime": 1494025223000,
      "updateTime": 1494025223000
    }
  ]
}
```

- ### `POST`	`/back/varieties`		添加种类信息

##### Parameter

###### Example Value:

```
 {
   "varietyId": 8,
   "varietyName": "冻鸡",
   "parentId": 2,
   "type": "鸡"
 }
```

##### Responses

###### Example Value:

- id存在：	

```
{
  "code": 101,
  "message": "插入失败,id已存在",
  "data": null
}
```

- 内部错误：

```
{
  "code": -1,
  "message": "出现未知异常",
  "data": null
}
```

- 成功

```
{
  "code": 100,
  "message": "操作成功",
  "data": {
    "varietyId": 8,
    "varietyName": "冻鸡",
    "parentId": 2,
    "type": "鸡",
    "createTime": 1493982128472,
    "updateTime": 1493982128472
  }
}
```

- ### `PUT`	`/back/varieties`		修改种类

##### Parameter

###### Example Value:

```
 {
   "varietyId": 8,
   "varietyName": "冻鸡爪子",
   "parentId": 2,
   "type": "鸡"
 }
```

##### Responses

###### Example Value:

- id不存在:

```
{
  "code": 102,
  "message": "更新失败,不存在指定id",
  "data": null
}
```

- 内部错误：

```
{
  "code": -1,
  "message": "出现未知异常",
  "data": null
}
```

- 成功：

```
{
  "code": 100,
  "message": "操作成功",
  "data": {
    "varietyId": 8,
    "varietyName": "冻鸡爪子",
    "parentId": 2,
    "type": "鸡",
    "createTime": 0,
    "updateTime": 0
  }
}
```

- ### `DELETE`		`/back/varieties/{varietyId}`	删除商品

  ##### Parameter			

  ​Name				Description

  varietyId				商品id号

  ##### Responses

  ###### Example Value:		/back/varieties/8

- id不存在：

```
{
  "code": 103,
  "message": "删除失败,不存在指定id",
  "data": null
}
```

- 成功：

```
{
  "code": 100,
  "message": "操作成功",
  "data": {
    "varietyId": 8,
    "varietyName": "冻鸡爪子",
    "parentId": 2,
    "type": "鸡",
    "createTime": 1494028986000,
    "updateTime": 1494028986000
  }
}
```

