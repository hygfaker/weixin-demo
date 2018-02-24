**简要描述：** 

- 更新应用的Android证书信息

**请求URL：** 
- ` ${schema}://${ip}:${port}/modu/api/account/apps/:appId/certs/android `
  
**请求方式：**
- POST 

**URL参数：** 

| 参数名 | 类型 | 可空 | 默认值 | 说明 |
| :-- | :-- | :-- | :-- | :-- |
| appId | number | 否 | 无 | 应用ID |

**参数：** 

| 参数名 | 类型 | 可空 | 默认值 | 说明 |
| :-- | :-- | :-- | :-- | :-- |
| appPackage | string | 否 | 无 | 应用包名 |
| alias | string | 否 | 无 | 证书别名 |
| keyStorePwd | string | 否 | 无 | 证书库密码 |
| certPwd | string | 否 | 无 | 证书密码 |
| cert | string | 否 | 无 | 证书文件地址 |

 **返回示例**

``` 
{
    "status": 200,
    "desc": "执行成功",
    "data": {
        "appPackage": "cn.com.minstone.xxx",
        "alias": "xxx",
        "keyStorePwd": "minstone",
        "certPwd": "minstone",
        "cert": "/modu/api/file/z"
    },
    "timestamp": 1509684825993
}
```

 **返回参数说明** 

参考参数信息




