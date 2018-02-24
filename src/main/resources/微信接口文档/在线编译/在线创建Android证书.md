**简要描述：** 

- 在线创建Android证书

**请求URL：** 
- ` ${schema}://${ip}:${port}/modu/api/account/certs `
  
**请求方式：**
- POST 

**参数：** 

| 参数名 | 类型 | 可空 | 默认值 | 说明 |
| :-- | :-- | :-- | :-- | :-- |
| appPackage | string | 否 | 无 | 应用包名 |
| alias | string | 否 | 无 | 证书别名 |
| keyStorePwd | string | 否 | 无 | 证书库密码 |
| certPwd | string | 否 | 无 | 证书密码 |
| validity | number | 否 | 无 | 有效期(年) |
| firstAndLastName | string | 否 | 无 | 姓名 |
| organizationalUnit | string | 否 | 无 | 组组单位名称 |
| organization | string | 否 | 无 | 组组名称 |
| countryCode | number | 否 | 无 | 国家(中国86) |
| province | string | 否 | 无 | 省份、州 |
| city | string | 否 | 无 | 城市 |

 **返回示例**

``` 
{
    "status": 200,
    "desc": "执行成功",
    "data": "/modu/api/file/z",
    "timestamp": 1509681562482
}
```

 **返回参数说明** 

| 参数名 | 类型 | 说明 |
| :-- | :-- | :-- |
| data | string | 证书文件地址 |




