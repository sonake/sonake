package com.hc.common.result;

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
    C201(201, "CREATED"),
    /*
    Accepted[*]：表示一个请求已经进入后台排队（异步任务）
     */
    C202(202, "Accepted"),
    /*
   NO CONTENT - [DELETE]：用户删除数据成功
    */
    C204(204, "no content"),
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
    C401(401, "Unauthorized"),
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
    C405(405, "method not allowed"),
    /*
    Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
     */
    C406(406, "Not Acceptable"),
    /*

     */
    C409(409, "请求冲突"),
    /*
    Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。例如文件下载时，文件不存在。
     */
    C410(410, "Gone"),
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
    C415(415, "Error Type"),
    /*
    Unprocesable entity - [POST/PUT/PATCH]：当创建一个对象时，发生一个验证错误。请求格式正确，但语义错误。此时错误描述信息中最好有错误详情。
     */
    C422(422, "Unprocesable entity "),
    /*
    too many request - 请求过多
     */
    C429(429, "too many request"),
    /*
    INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功
     */
    C500(500, "Internal Server Error"),
    /*
    Token过期
     */
    C514(514, "TOKEN_EXPIRE"),
    /*
    错误码，根据响应信息中的msg参数展示具体错误信息。
     */
    C999(999, "Error"),
    //令牌过期或不存在
    ToKenError(4000001, "Token expire or do not exist");


    /**
     * Code 状态码
     */
    private Integer code;
    /**
     * desc 描述
     */
    private String desc;

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
