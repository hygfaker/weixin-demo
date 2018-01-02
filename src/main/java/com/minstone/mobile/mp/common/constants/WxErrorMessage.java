package com.minstone.mobile.mp.common.constants;

/**
 * Created by huangyg on 2017/8/16.
 */
public class WxErrorMessage {
    public static String errorMsg(int errorcode) {
        String errmsg = "";
        switch (errorcode) {
            case -1:
                errmsg="系统繁忙，此时请开发者稍候再试";
                break;
            case 0:
                errmsg="请求成功";
                break;
            case 40001:
                errmsg="获取access_token时AppSecret错误，或者access_token无效。";
                break;
            case 40002:
                errmsg="不合法的凭证类型";
                break;
            case 40003:
                errmsg="不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID";
                break;
            case 40004:
                errmsg="不合法的媒体文件类型";
                break;
            case 40005:
                errmsg="不合法的文件类型";
                break;
            case 40006:
                errmsg="不合法的文件大小";
                break;
            case 40007:
                errmsg="不合法的媒体文件id";
                break;
            case 40008:
                errmsg="不合法的消息类型";
                break;
            case 40009:
                errmsg="不合法的图片文件大小";
                break;
            case 40010:
                errmsg="不合法的语音文件大小";
                break;
            case 40011:
                errmsg="不合法的视频文件大小";
                break;
            case 40012:
                errmsg="不合法的缩略图文件大小";
                break;
            case 40013:
                errmsg="不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写";
                break;
            case 40014:
                errmsg="不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口";
                break;
            case 40015:
                errmsg="不合法的菜单类型";
                break;
            case 40016:
                errmsg="不合法的按钮个数";
                break;
            case 40017:
                errmsg="不合法的按钮个数";
                break;
            case 40018:
                errmsg="不合法的按钮名字长度";
                break;
            case 40019:
                errmsg="不合法的按钮KEY长度";
                break;
            case 40020:
                errmsg="不合法的按钮URL长度";
                break;
            case 40021:
                errmsg="不合法的菜单版本号";
                break;
            case 40022:
                errmsg="不合法的子菜单级数";
                break;
            case 40023:
                errmsg="不合法的子菜单按钮个数";
                break;
            case 40024:
                errmsg="不合法的子菜单按钮类型";
                break;
            case 40025:
                errmsg="不合法的子菜单按钮名字长度";
                break;
            case 40026:
                errmsg="不合法的子菜单按钮KEY长度";
                break;
            case 40027:
                errmsg="不合法的子菜单按钮URL长度";
                break;
            case 40028	:
                errmsg="不合法的自定义菜单使用用户";
                break;
            case 40029:
                errmsg="不合法的oauth_code";
                break;
            case 40030:
                errmsg="不合法的refresh_token";
                break;
            case 40031	:
                errmsg="不合法的openid列表";
                break;
            case 40032	:
                errmsg="不合法的openid列表长度";
                break;
            case 40033	:
                errmsg="不合法的请求字符，不能包含\\uxxxx格式的字符";
                break;
            case 40035	:
                errmsg="不合法的参数";
                break;
            case 40037	:
                errmsg="不合法的模板id";
                break;
            case 40038	:
                errmsg="不合法的请求格式";
                break;
            case 40039	:
                errmsg="不合法的URL长度";
                break;
            case 40050	:
                errmsg="不合法的分组id";
                break;
            case 40051	:
                errmsg="分组名字不合法";
                break;
            case 40117	:
                errmsg="分组名字不合法";
                break;
            case 40130	:
                errmsg="无效的 openids 数组，至少有两个 openid 组成";
                break;
            case 40118:
                errmsg="media_id大小不合法";
                break;
            case 40119:
                errmsg="button类型错误";
                break;
            case 40120:
                errmsg="button类型错误";
                break;
            case 40121:
                errmsg="不合法的media_id类型";
                break;
            case 40132:
                errmsg="微信号不合法";
                break;
            case 40137:
                errmsg="不支持的图片格式";
                break;
            case 41001:
                errmsg="缺少access_token参数";
                break;
            case 41002:
                errmsg="缺少appid参数";
                break;
            case 41003:
                errmsg="缺少refresh_token参数";
                break;
            case 41004:
                errmsg="缺少secret参数";
                break;
            case 41005:
                errmsg="缺少多媒体文件数据";
                break;
            case 41006:
                errmsg="缺少media_id参数";
                break;
            case 41007:
                errmsg="缺少子菜单数据";
                break;
            case 41008:
                errmsg="缺少oauth code";
                break;
            case 41009:
                errmsg="缺少openid";
                break;
            case 42001:
                errmsg="access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明";
                break;
            case 42002:
                errmsg="refresh_token超时";
                break;
            case 42003:
                errmsg="oauth_code超时";
                break;
            case 43001:
                errmsg="需要GET请求";
                break;
            case 43002:
                errmsg="需要POST请求";
                break;
            case 43003:
                errmsg="需要HTTPS请求";
                break;
            case 43004:
                errmsg="需要接收者关注";
                break;
            case 43005:
                errmsg="需要好友关系";
                break;
            case 44001:
                errmsg="多媒体文件为空";
                break;
            case 44002:
                errmsg="POST的数据包为空";
                break;
            case 44003:
                errmsg="图文消息内容为空";
                break;
            case 44004:
                errmsg="文本消息内容为空";
                break;
            case 45001:
                errmsg="多媒体文件大小超过限制";
                break;
            case 45002:
                errmsg="消息内容超过限制";
                break;
            case 45003:
                errmsg="标题字段超过限制";
                break;
            case 45004:
                errmsg="描述字段超过限制";
                break;
            case 45005:
                errmsg="链接字段超过限制";
                break;
            case 45006:
                errmsg="图片链接字段超过限制";
                break;
            case 45007:
                errmsg="语音播放时间超过限制";
                break;
            case 45008:
                errmsg="图文消息超过限制";
                break;
            case 45009:
                errmsg="接口调用超过限制";
                break;
            case 45010:
                errmsg="创建菜单个数超过限制";
                break;
            case 45015:
                errmsg="回复时间超过限制";
                break;
            case 45016:
                errmsg="系统分组，不允许修改";
                break;
            case 45017:
                errmsg="分组名字过长";
                break;
            case 45018:
                errmsg="分组数量超过上限";
                break;
            case 45028:
                errmsg="没有群发的权限";
            case 45157:
                errmsg="该标签分组已经存在";
                break;
            case 46001:
                errmsg="不存在媒体数据";
                break;
            case 46002:
                errmsg="不存在的菜单版本";
                break;
            case 46003:
                errmsg="不存在的菜单数据";
                break;
            case 46004:
                errmsg="不存在的用户";
                break;
            case 47001:
                errmsg="解析JSON/XML内容错误";
                break;
            case 48001:
                errmsg="api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限";
                break;
            case 50001:
                errmsg="用户未授权该api";
                break;
            case 50002:
                errmsg="用户受限，可能是违规后接口被封禁";
                break;
            case 61451:
                errmsg="参数错误(invalid parameter)";
                break;
            case 61452:
                errmsg="无效客服账号(invalid kf_account)";
                break;
            case 61453:
                errmsg="客服帐号已存在(kf_account exsited)";
                break;
            case 61454:
                errmsg="客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)";
                break;
            case 61455:
                errmsg="客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)";
                break;
            case 61456:
                errmsg="客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)";
                break;
            case 61457:
                errmsg="无效头像文件类型(invalid file type)";
                break;
            case 61450:
                errmsg="系统错误(system error)";
                break;
            case 61500:
                errmsg="日期格式错误";
                break;
            case 61501:
                errmsg="日期范围错误";
                break;
            case 65400:
                errmsg="API不可用，即没有开通/升级到新版客服功能";
                break;
            case 65401:
                errmsg="该客服账号不存在";
                break;
            case 65403:
                errmsg="客服昵称不合法";
                break;
            case 65404:
                errmsg="客服帐号不合法。格式为：帐号前缀@公众号微信号，帐号前缀最多10个字符，必须是英文、数字字符或者下划线，后缀为公众号微信号，长度不超过30个字符";
                break;
            case 65405:
                errmsg="帐号数目已达到上限，不能继续添加";
                break;
            case 65406:
                errmsg="已经存在的客服帐号";
                break;
            case 65407:
                errmsg="邀请对象已经是本公众号客服";
                break;
            case 65408:
                errmsg="本公众号已发送邀请给该微信号";
                break;
            case 65409:
                errmsg="无效的微信号";
                break;
            case 65410:
                errmsg="邀请对象绑定公众号客服数量达到上限（目前每个微信号最多可以绑定5个公众号客服帐号）";
                break;
            case 65411:
                errmsg="该帐号已经有一个等待确认的邀请，不能重复邀请";
                break;
            case 65412:
                errmsg="该帐号已经绑定微信号，不能进行邀请";
                break;
            case 65402:
                errmsg="该客服账号没有绑定到微信公众号，请选择其他客服账号";
                break;
            case 65415:
                errmsg="该客服账号不在线，请选择其他客服账号";
                break;
            case 9001001:
                errmsg="POST数据参数不合法";
                break;
            case 9001002:
                errmsg="远端服务不可用";
                break;
            case 9001003:
                errmsg="Ticket不合法";
                break;
            case 9001004:
                errmsg="获取摇周边用户信息失败";
                break;
            case 9001005:
                errmsg="获取商户信息失败";
                break;
            case 9001006:
                errmsg="获取OpenID失败";
                break;
            case 9001007:
                errmsg="上传文件缺失";
                break;
            case 9001008:
                errmsg="上传素材的文件类型不合法";
                break;
            case 9001009:
                errmsg="上传素材的文件尺寸不合法";
                break;
            case 9001010:
                errmsg="上传失败";
                break;
            case 9001020:
                errmsg="帐号不合法";
                break;
            case 9001021:
                errmsg="已有设备激活率低于50%，不能新增设备";
                break;
            case 9001022:
                errmsg="设备申请数不合法，必须为大于0的数字";
                break;
            case 9001023:
                errmsg="已存在审核中的设备ID申请";
                break;
            case 9001024:
                errmsg="一次查询设备ID数量不能超过50";
                break;
            case 9001025:
                errmsg="设备ID不合法";
                break;
            case 9001026:
                errmsg="页面ID不合法";
                break;
            case 9001027:
                errmsg="页面参数不合法";
                break;
            case 9001028:
                errmsg="一次删除页面ID数量不能超过10";
                break;
            case 9001029:
                errmsg="页面已应用在设备中，请先解除应用关系再删除";
                break;
            case 9001030:
                errmsg="一次查询页面ID数量不能超过50";
                break;
            case 9001031:
                errmsg="时间区间不合法";
                break;
            case 9001032:
                errmsg="保存设备与页面的绑定关系参数错误";
                break;
            case 9001033:
                errmsg="门店ID不合法";
                break;
            case 9001034:
                errmsg="设备备注信息过长";
                break;
            case 9001035:
                errmsg="设备申请参数不合法";
                break;
            case 9001036:
                errmsg="查询起始值begin不合法";
                break;
            default:
                errmsg = "unknown";
                break;
        }

        return errmsg;
    }
}
