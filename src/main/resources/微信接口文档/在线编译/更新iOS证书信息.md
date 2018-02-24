**简要描述：** 

- 更新iOS证书信息

**请求URL：** 
- ` ${schema}://${ip}:${port}/modu/api/account/apps/:appId/certs/ios `
  
**请求方式：**
- POST 

**URL参数：** 

| 参数名 | 类型 | 可空 | 默认值 | 说明 |
| :-- | :-- | :-- | :-- | :-- |
| appId | number | 否 | 无 | 应用ID |

**参数：** 

| 参数名 | 类型 | 可空 | 默认值 | 说明 |
| :-- | :-- | :-- | :-- | :-- |
| bundleIdentifier  | string | 否 | 无 | 应用包名 |
| certPwd  | string | 否 | 无 | 证书密码 |
| mobileprovision  | string | 否 | 无 | 证书库文件地址 |
| cert  | string | 否 | 无 | 证书文件地址 |

 **返回示例**

``` 
{
    "status": 200,
    "desc": "执行成功",
    "data": {
        "bundleIdentifier": "cn.com.minstone.xxx",
        "certPwd": "minstone",
        "mobileprovision": "/modu/api/file/x",
        "cert": "/modu/api/file/y"
    },
    "timestamp": 1509684955677
}
```

 **返回参数说明** 

参考参数信息





