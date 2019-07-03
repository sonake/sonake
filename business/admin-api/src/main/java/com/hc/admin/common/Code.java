package com.hc.admin.common;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/5/9 14:08
 * @description：状态码枚举类
 * @version: 1.0
 */
public enum Code {
    /*
    OK[GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）
     */
    C200(200, "Success"),
    /*
    CREATED[POST/PUT/PATCH]：用户新建或修改数据成功
     */
    C201(201,"CREATED"),
    /*
    Accepted[*]：表示一个请求已经进入后台排队（异步任务）
     */
    C202(202,"Accepted"),
    /*
   NO CONTENT - [DELETE]：用户删除数据成功
    */
    C204(204,"no content"),
    /*
    Not Modified：客户端使用缓存数据
     */
    C301(304, "Not Modified"),
    /*
    INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
     */
    C400(400, "INVALID REQUEST"),
    /*
    Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）
     */
    C401(401,"Unauthorized"),
    /*
    Forbidden - [*]：表示用户得到授权（与401错误相对），但是访问是被禁止的
     */
    C403(403, "Forbidden"),
    /*
    NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的
     */
    C404(404, "Not Found"),
    /*
    method not allowed：该http方法不被允许
     */
    C405(405,"method not allowed"),
    /*
    Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
     */
    C406(406,"Not Acceptable"),
    /*

     */
    C409(409,"请求冲突"),
    /*
    Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。例如文件下载时，文件不存在。
     */
    C410(410,"Gone"),
    /*
    请求体过大。比如说，服务器要求上传文件不能超过 5M，但是我们 POST 了 10M，这时候就返回 413。
     */
    C412(413, "Too Many"),
   /*
   请求的 URI 太长了。比如说，我们提供了太多的 Query 参数，以至于超过了服务器的限制，这时候可以返回 414。
    */
    C414(414, "Error Format"),
    /*
    不支持的媒体类型。例如上传文件只允许png图片，上传gif文件时，此时返回415
     */
    C415(415,"Error Type"),
    /*
    Unprocesable entity - [POST/PUT/PATCH]：当创建一个对象时，发生一个验证错误。请求格式正确，但语义错误。此时错误描述信息中最好有错误详情。
     */
    C422(422, "Unprocesable entity "),
    /*
    too many request - 请求过多
     */
    C429(429,"too many request"),
    /*
    INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功
     */
    C500(500, "Internal Server Error"),
    /*
    Token过期
     */
    C514(514,"TOKEN_EXPIRE"),
    /*
    错误码，根据响应信息中的msg参数展示具体错误信息。
     */
    C999(999,"Error"),

    /**
     * 错误码范围（暂定）策略:4001000 	策略对象:4001100 	IP类:4001300	字符串类:4001400    数值类:4001500     公共：4009000
     */
    /**
     * 请求参数opAction为enable时，policyList中isValid参数的值，只能为1(启用)
     */
    IsValidInEnableIsWrongRange(4001000,"When the request parameter opAction is enable, the value of the isValid parameter in the policyList can only be 1 (enable)."),
    /**
     *请求参数opAction为disable时，policyList中isValid参数的值，只能为0(停用)
     */
    IsValidInDisableIsWrongRange(4001001,"When the request parameter opAction is disable, the value of the isValid parameter in the policyList can only be 0 (disable)."),
    /**
     * 请求参数policyList中的isValid参数的值，只能为0(停用)和1(启用)
     */
    IsValidIsWrongRange(4001002,"The value of the isValid parameter in the request parameter policyList can only be 0 (disable) and 1 (enable)."),
    /**
     * 请求参数policyList中objectIds参数和objectList参数不能同时为空
     */
    PolicysObjectIdAndObjectListIsEmpty(4001003,"The objectIds and objectList parameters in the request parameter policyList cannot be empty at the same time."),
    /**
     * 请求参数policyList中objectIds参数不能为空
     */
    PolicysObjectIdIsEmpty(4001004,"The objectIds parameter in the request parameter policyList cannot be empty."),

    /**
     * 请求参数policyList中objectIds和objectList参数的总数不能大于8
     */
    GroupNumGtEight(4001005,"The total number of objectIds and objectList parameters in the request parameter policyList cannot be greater than 8."),
    /**
     * 请求参数policyList中compileName参数不能为空
     */
    CompileNameIsEmpty(4001006,"The compileName parameter in the request parameter policyList cannot be empty."),
    /**
     *请求参数policyList中compileName参数的长度不能超过128
     */
    CompileNameIsToLong(4001007,"The length of the compileName parameter in the request parameter policyList cannot be greater than 128."),
    /**
     * 请求参数policyList中compileDesc参数的长度不能大于1024
     */
    CompileDescIsToLong(4001008,"The length of the compileDesc parameter in the request parameter policyList cannot be greater than 1024."),
    /**
     * 请求参数policyList中action参数不能为空
     */
    ActionIsEmpty(4001009,"The action parameter in the request parameter policyList cannot be empty."),
    /**
     *请求参数policyList中action的值只能是1(Intercept)、2(Monitor)、16(Block)、48(Redirect/Replace/Hijack/Insert)、128(Bypass)
     */
    ActionInWrongRange(4001010,"The action values in the request parameter policyList can only be 1 (Intercept), 2 (Monitor), 16 (Block), 48 (Redirect/Replace/Hijack/Insert), 128 (Bypass)."),
    /**
     *请求参数policyList中doBlacklist参数的值只能是0(不需要)
     */
    DoBlacklistInWrongRange(4001011,"The value of the doBlacklist parameter in the request parameter policyList can only be 0 (no need)."),
    /**
     *请求参数policyList中doLog参数不能为空
     */
    DoLogIsEmpty(4001012,"The doLog parameter in the request parameter policyList cannot be empty."),
    /**
     *请求参数policyList中doLog参数的值只能是0(不需要)、1(需要)、2(只记录结构化日志)
     */
    DoLogInWrongRange(4001013,"The value of the doLog parameter in the request parameter policyList can only be 0 (no need), 1 (need), and 2 (record only structured logs)."),
    /**
     *请求参数policyList中,effectiveRange必须是json格式
     */
    EffectiveRangeMastJson(4001014,"In the request parameter policyList, the effectiveRange must be in JSON format."),
    /**
     *请求参数policyList中,userRegion必须是json格式
     */
    UserRangeMastJson(4001015,"In the request parameter policyList, the userRegion must be in JSON format."),
    /**
     *请求参数policyList中,compileType为PXY_CONTROL时，action的值只能是1(Monitor)、16(Block)、48(Redirect/Replace/Hijack/Insert)、128(Bypass)
     */
    PxyControlsActionInWrongRange(4001016,"In the request parameter policyList, when compileType is PXY_CONTROL, action values can only be 1 (Monitor), 16 (Block), 48 (Redirect/Replace/Hijack/Insert), 128 (Bypass)."),
    /**
     *请求参数policyList中,compileType为PXY_CONTROL时，userRegion参数不能为空
     */
    UserRegionIsEmpty(4001017,"In the request parameter policyList, when compileType is PXY_CONTROL, the userRegion parameter cannot be empty."),
    /**
     *请求参数policyList中,compileType为PXY_CONTROL时，userRegion参数格式不正确
     */
    UserRegionFormatError(4001018,"In the request parameter policyList, when compileType is PXY_CONTROL, the userRegion parameter format is wrong."),
    /**
     *请求参数policyList中,compileType为PXY_CONTROL时，userRegion参数中method不能为空
     */
    UserRegionsMethodIsEmpty(4001019,"In the request parameter policyList, when the compileType is PXY_CONTROL, the method in the userRegion parameter cannot be empty."),
    /**
     * 请求参数policyList中,compileType为PXY_CONTROL时，userRegion参数中method的值只能是redirect、replace、block、hijack、insert
     */
    PxyCtlUserRegionsMethodInWrongRange(4001020,"In the request parameter policyList, when the compileType is PXY_CONTROL, the method value in the userRegion parameter can only be redirect, replace, block, hijack, insert."),
    /**
     * 请求参数policyList中,action为48时，userRegion参数中method为redirect时，code不能为空
     */
    UserRegionsCodeIsEmpty(4001021,"In the request parameter policyList, when action is 48 and method is redirect in the userRegion parameter, code cannot be empty."),
    /**
     * 请求参数policyList中,action为48时，userRegion参数中method为redirect时，code的值只能为301(永久重定向)和302(临时重定向)
     */
    UserRegionsCodeInWrongRange(4001022,"In the request parameter policyList, when action is 48 and method is redirect in the userRegion parameter, code values can only be 301 (permanent redirection) and 302 (temporary redirection)."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为redirect时，to不能为空
     */
    UserRegionsToIsEmpty(4001023,"In the request parameter policyList, when action is 48 and method in userRegion parameter is redirect, to cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为replace时，rules不能为空
     */
    UserRegionsRulesIsEmpty(4001024,"In the request parameter policyList, rules cannot be empty when action is 48 and method is replace in the userRegion parameter."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为replace时，rules中的find不能为空
     */
    UserRegionsFindIsEmpty(4001025,"In the request parameter policyList, when action is 48 and method in userRegion parameter is replace, find in rules cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为replace时，rules中的replace_with不能为空
     */
    UserRegionsReplaceWithIsEmpty(4001026,"In the request parameter policyList, when action is 48 and method in userRegion parameter is replace, replace_with in rules cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为replace时，rules中的search_in不能为空
     */
    UserRegionsSearchInIsEmpty(4001027,"In the request parameter policyList, when action is 48 and method in userRegion parameter is replace, search_in in rules cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为hijack时，hijack_profile不能为空
     */
    UserRegionsHijackProfileIsEmpty(4001028,"In the request parameter policyList, when action is 48 and method in userRegion parameter is hijack, hijack_profile cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method为insert时，insert_profile不能为空
     */
    UserRegionsInsertProfileIsEmpty(4001029,"In the request parameter policyList, when action is 48 and method in userRegion parameter is insert, insert_profile cannot be empty."),
    /**
     *请求参数policyList中,action为48时，userRegion参数中method只能为Redirect/Replace/Hijack/Insert
     */
    UserRegionsMethodInWrongRange(4001030,"In the request parameter policyList, when action is 48, the method in the userRegion parameter can only be Redirect/Replace/Hijack/Insert."),
    /**
     *请求参数policyList中,compileType为PXY_INTERCEPT时，action的值只能是2(Intercept)、128(Bypass)
     */
    PxyInterceptActionInWrongRange(4001031,"In the request parameter policyList, when the compileType is PXY_INTERCEPT, the action value can only be 2 (Intercept), 128 (Bypass)."),
    /**
     *请求参数policyList中,action为16时，userRegion参数中method为block时，html_profile不能为空
     */
    UserRegionsHtmlProfileIsEmpty(4001032,"In the request parameter policyList, when action is 16 and method is block in userRegion parameter, html_profile cannot be empty."),
    /**
     *请求参数policyList中,userTags的格式不正确,不能出现数字和','以外的字符
     */
    UserTagsFormatError(4001033,"In the request parameter policyList, userTags are incorrectly formatted and characters other than numbers and ',' cannot appear."),
    /**
     * CompileType不能为空
     */
    CompileTypeIsEmpty(4001034,"CompileType cannot be empty."),
    /**
     *compileType的取值范围只能是PXY_INTERCEPT(proxy拦截策略)、PXY_CONTROL(proxy控制策略)、PXY_CACHE(proxy缓存策略)、FW_CONTROL(fireWall控制策略)
     */
    CompileTypeInWrongRange(4001035,"The range of compileType can only be PXY_INTERCEPT (proxy interception strategy), PXY_CONTROL (proxy control strategy), PXY_CACHE (proxy caching strategy), FW_CONTROL (firewall control strategy)."),

    /**
     *policyList参数不能为空
     */
    PolicyListIsEmpty(4001036,"The policyList parameter cannot be empty."),
    /**
     *请求参数policyList中，compileId不能为空
     */
    CompileIdIsEmpty(4001037,"In the request parameter policyList, compileId cannot be empty."),
    /**
     *指定compileIds时，请求参数compileType不能为空
     */
    DelCompileIdsTypeIsEmpty(4001038,"When specifying compileIds, the request parameter compileType cannot be empty."),

    /**
     * 请求参数objectList中objectName参数不能为空
     */
    ObjectNameIsEmpty(4001100,"The objectName parameter in the request parameter objectList cannot be empty."),
    /**
     * 请求参数objectList中objectName参数的长度不能大于128
     */
    ObjectNameIsToLong(4001101,"The length of the objectName parameter in the request parameter objectList cannot be greater than 128."),
    /**
     * 请求参数objectList中objectDesc参数长度不能超过1024
     */
    ObjectDescIsToLong(4001102,"The length of the objectDesc parameter in the request parameter objectList cannot be greater than 1024."),
    /**
     *请求参数objectList中的objectType参数不能为空
     */
    ObjectListIsEmpty(4001103,"The objectType parameter in the request parameter objectList cannot be empty."),
    /**
     *请求参数objectList中的objectType参数的值，只能为IP、URL、FQDN（域名）、Content（内容类关键字）、SubscribeID（AAA账号）、"REQ_HDR（请求头域）、RES_HDR（应答头域）、Account(邮件账号)
     */
    ObjectTypeInWrongRange(4001104,"The value of the objectType parameter in the request parameter objectList can only be IP, URL, FQDN (domain names), Content (HTTP Body Keywords), SubscribeID (Subscribe ID), REQ_HDR (HTTP Header Fields), RES_HDR (HTTP Header Fields), Account (Mail Account)."),
    /**
     * 请求参数objectList中isInitialize参数值只能为0(非内置对象)和1(内置对象)
     */
    IsInitializeInWrongRange(4001105,"The isInitialize parameter values in the request parameter objectList can only be 0 (non-built-in object) and 1 (built-in object)."),
   /**
    * 请求参数opAction为add时，objectList中的isInitialize参数值只能为0(非内置对象)
    * 内置的数据通过脚本直接生成
    */
    IsInitializeOfAddInWrongRange(4001106,"When the request parameter opAction is add, the isInitialize parameter value in the objectList can only be 0 (non-built-in object)."),
    /**
     * 请求参数objectList中isExclusion参数不能为空
     */
    IsExclusionIsEmpty(4001107,"The isExclusion parameter in the request parameter objectList cannot be empty."),
    /**
     * 请求参数objectList中isExclusion参数值只能为0(策略对象)和1(解密排除对象)
     */
    IsExclusionInWrongRange(4001108,"The isExclusion parameter values in the request parameter objectList can only be 0 (policy object) and 1 (decryption exclusion object)."),
    /**
     * 请求参数objectList中,objectId不能为空
     */
    ObjectIdIsEmpty(4001109,"In the request parameter objectList, objectId cannot be empty."),
    /**
     * 已存在解密排除对象1(解密排除对象)，类型为……
     */
    IsExclusionConfigIsExist(4001110,"Existing decryption exclusion objects (isExclusion=1) of type."),
    /**
     * 被生效的策略引用的策略对象不能被删除和停用
     */
    ReferencedObjectCannotDelOrDisable(4001111,"Policy objects referenced by effective policies cannot be deleted or disabled."),
    /**
     * 解密排除对象(isExclusion=1)，不能被删除或停用
     */
    IsExclusionConfigurationCannotDelOrDisable(4001112,"The decryption exclusion object (isExclusion = 1) cannot be delete or disable."),
    /**
     * 策略对象不能将自己设置为子策略对象
     */
    RefSelfToSubObject(4001113,"Policy objects cannot set themselves as sub-policy objects."),
    /**
     * 策略对象属于该对象的上级策略,不能设置为他的下级策略对象
     */
    RefParentToSubObject(4001114,"The policy object belongs to the superior policy of the object and cannot be set as its inferior policy object."),
    /**
     * 内置的策略对象(isInitialize=1)，不能被删除
     */
    IsInitializeConfigurationCannotDel(4001115,"The built-in policy object (isInitialize = 1) cannot be delete."),
    /**
     * opAction参数不能为空
     */
    OpActionIsEmpty(4009000,"OpAction parameter cannot be empty."),
    /**
     *新增操作opAction参数必需是add
     */
    OpActionOfAddInWrongRange(4009001,"In a save operation, the opAction parameter for the  must be add."),
    /**
     *修改操作中，请求参数opAction参数的值只能是update(修改)、enable(启用)和disable(停用)
     */
    OpActionOfUpdateInWrongRange(4009002,"In a update operation, the values of the request parameter opAction parameter can only be update, enable, and disable."),
    /**
     *请求参数orderBy中的排序字段名称不存在!
     */
    OrderByFieldNotExist(4009003,"The sorting field name in the request parameter orderBy does not exist."),
   /**
    * 不存在的配置
    */
    NotExistConfiguration(4009004,"Not exist configuration."),

    ;

    private Integer code;//code
    private String desc;//description

    Code(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
