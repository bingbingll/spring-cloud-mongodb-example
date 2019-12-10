package com.example.demo.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @description 统一数据传输响应
 */
@Data
public class ResponseWrapper {
    /**
     * 200 请求已成功，请求所希望的响应头或数据体将随此响应返回。
     */
    private static Integer Code200 = 200;
    /**
     * 获取资源所需要的策略并没有没满足或没有找到对象。
     */
    private static Integer Code510 = 510;
    /**
     * 由于临时的服务器维护或者过载，服务器当前无法处理请求。这个状况是临时的，并且将在一段时间以后恢复。
     */
    private static Integer Code503 = 503;
    /**
     * 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。
     */
    private static Integer Code520 = 520;
    /**
     * 服务器不支持当前请求所需要的某个功能。当服务器无法识别请求的方法，并且无法支持其对任何资源的请求。
     */
    private static Integer Code501 = 501;
    /**
     * 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。一般来说，这个问题都会在服务器的程序码出错时出现。
     */
    private static Integer Code500 = 500;
    /**
     * 状态
     */
    private StatusEnum status;
    /**
     * 响应码
     */
    private Integer infoCode;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 传输数据
     */
    private Object data;


    /**
     * 无参构造器
     */
    public ResponseWrapper() {

    }

    /**
     * @param status   状态
     * @param infoCode 响应码
     * @param msg      响应信息
     * @param data     传输数据
     */
    public ResponseWrapper(StatusEnum status, Integer infoCode, String msg, Object data) {
        switch (infoCode) {
            case 200:
                this.infoCode = 200;
                this.msg = "操作成功";
                this.data=data;
                break;
            case 510:
                this.infoCode = Code510;
                this.msg = "获取资源所需要的策略并没有没满足或没有找到对象。";
                break;
            case 503:
                this.infoCode = Code503;
                this.msg = "由于临时的服务器维护或者过载，服务器当前无法处理请求。";
                break;
            case 520:
                this.infoCode = Code520;
                this.msg = "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。";
                break;
            case 501:
                this.infoCode = Code501;
                this.msg = "服务器不支持当前请求所需要的某个功能。当服务器无法识别请求的方法，并且无法支持其对任何资源的请求。";
                break;
            case 500:
                this.infoCode = Code500;
                this.msg = "请求失败！系统异常。";
                break;
            case 300:
                this.infoCode=300;
                this.msg="请求条件错误。"+msg;
                break;
            default:
                this.infoCode = infoCode;
                this.msg = msg;
        }
        this.status = status;
        //this.infoCode = infoCode;
        //this.msg = msg;
        this.data = data;
    }

    /**
     * @return
     * @author
     * @description 返回成功
     * @since 2017年3月7日 上午10:20:47
     */
    public static ResponseWrapper success() {
        return new ResponseWrapper( StatusEnum.SUCCESS, 200, null, "");
    }

    public static ResponseWrapper success(Integer infoCoder) {
        return new ResponseWrapper( StatusEnum.SUCCESS, infoCoder, null, "" );
    }

    public static ResponseWrapper success(String info) {
        return new ResponseWrapper( StatusEnum.SUCCESS, 200, info, "" );
    }

    public static ResponseWrapper success(Object data) {
        return new ResponseWrapper( StatusEnum.SUCCESS, 200, null, data );
    }

    public static ResponseWrapper success(Integer infoCode, String info) {
        return new ResponseWrapper( StatusEnum.SUCCESS, infoCode, info, null  );
    }

    public static ResponseWrapper success(Integer infoCoder, String info, Object data) {
        return new ResponseWrapper( StatusEnum.SUCCESS, infoCoder, info, data );
    }


    /**
     * @return
     * @author
     * @description 返回失败
     * @since 2017年3月7日 上午10:24:19
     */
    public static ResponseWrapper error() {
        return new ResponseWrapper( StatusEnum.ERROR, 500, null, "" );
    }

    public static ResponseWrapper error(Integer infoCode) {
        return new ResponseWrapper( StatusEnum.ERROR, infoCode, null, ""  );
    }

    public static ResponseWrapper error(String info) {
        return new ResponseWrapper( StatusEnum.ERROR, 300, info, "" );
    }

    public static ResponseWrapper error(Integer infoCode, String info) {
        return new ResponseWrapper( StatusEnum.ERROR, infoCode, info, ""  );
    }

    /**
     * 类型
     */
    public enum StatusEnum {
        /**
         * 成功
         */
        SUCCESS,
        /**
         * 错误
         */
        ERROR
    }

}