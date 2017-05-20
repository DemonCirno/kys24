# 品牌后台API文档



## `GET`   	`/back/brands`		展示所有品牌的信息

**Parameters**

​	Name				Description

​	pageSize				每页显示多少条数据,默认15

​	pageNumber			当前页码,若不设置则返回所有页数

##### Responses

###### Example Value:		/back/brands?pageSize=4&pageNumber=1

```
{
  "pageSize": 4,
  "pages": [
    {
      "pageNumber": 1,
      "dataList": [
        {
          "brandID": 1,
          "varietyID": 3213,
          "brandname": "正大",
          "createTime": 1493494295000,
          "updateTime": 1493494291000
        },
        {
          "brandID": 2,
          "varietyID": 5435,
          "brandname": "六合",
          "createTime": 1493494295000,
          "updateTime": 1493494293000
        }
      ]
    }
  ]
}
```

- ### `POST`	`/back/brands`		添加品牌信息

##### Parameter

###### Example Value:

```
{
	"brandId":"305",
	"brandName":"正大鸭肉",
	"varietyId":"2"
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
    "brandID": 0,
    "varietyId": 2,
    "brandName": "正大鸭肉",
    "createTime": 1493980160915,
    "updateTime": 1493980160915
  }
}
```

- ### `PUT`	`/back/brands`		修改品牌

##### Parameter

###### Example Value:

```
{
    "commodityId":11,
    "commodityName":"牛蹄",
    "commodityBrand":3234,
    "commodityVariety":4143,
    "commodityDepict":"标签",
    "commodityPrice":30,
    "commodityAmount":200,
    "commodityLeavenum":80,
    "commodityStandard":15,
    "operator":13
}
```

##### Responses

###### Example Value:

- id不存在:

```
{
  "code": 102,
  "message": "更新失败,不存在指定id",
  "data": {
    "brandID": 0,
    "varietyId": 5,
    "brandName": "六合狗肉",
    "createTime": 0,
    "updateTime": 0
  }
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
    "brandId": 1,
    "varietyId": 5,
    "brandName": "六合狗肉",
    "createTime": 0,
    "updateTime": 0
  }
}
```

- ### `DELETE`		`/back/brands/{brandId}`	删除商品

  ##### Parameter			/back/brands/1

​	Name				Description

​	**brandId**		**商品id号**

##### Responses	

###### Example Value:		

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
    "brandId": 1,
    "varietyId": 5,
    "brandName": "六合狗肉",
    "createTime": 1494027421000,
    "updateTime": 1494027421000
  }
}
```



### `POST`	`/back/pictures/brand/{brandId}`	上传品牌图片

##### Parameter

​	表单属性名			Description

​	**file					图片属性名**

​	**brandId**		**商品id**

##### Responses

###### 	Example Value:	

文件大于5M：

```
{
  "code": 112,
  "message": "文件过大,无法上传",
  "data": null
}
```

不存在id：

```
{
  "code": 102,
  "message": "更新失败,不存在指定id",
  "data": null
}
```

成功：

```
{
  "code": 100,
  "message": "操作成功",
  "data": "/image/4d3c2b1aB_11.jpg"
}
```