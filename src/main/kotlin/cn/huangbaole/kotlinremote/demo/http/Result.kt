package cn.huangbaole.kotlinremote.demo.http

import org.dom4j.dom.DOMNodeHelper.getData
import org.dom4j.dom.DOMNodeHelper.setData
import sun.print.ServiceDialog.getMsg
import java.io.Serializable

class Result<T> : Serializable {

  var code: Int? = null
  var msg: String? = null
  var data: T? = null

  private constructor() {}

  constructor(type: ResultTypeEnum) {
    this.code = type.code
    this.msg = type.msg
  }

  constructor(type: ResultTypeEnum, data: T) {
    this.code = type.code
    this.msg = type.msg
    this.data = data
  }

  constructor(type: ResultTypeEnum, content: String, data: T) {
    this.code = type.code
    this.msg = content
    this.data = data
  }

  companion object {

    fun success(): Result<*> {
      return Result(ResultTypeEnum.SERVICE_SUCCESS, null)
    }

    fun <T> success(data: T): Result<T> {
      return Result(ResultTypeEnum.SERVICE_SUCCESS, data)
    }

    fun <T> error(data: T): Result<T> {
      return Result(ResultTypeEnum.SERVICE_ERROR, data)
    }

    fun <T> success(content: String, data: T): Result<T> {
      return Result(ResultTypeEnum.SERVICE_SUCCESS, content, data)
    }

    fun error(): Result<*> {
      return Result(ResultTypeEnum.SERVICE_ERROR, null)
    }

    fun error(typeEnum: ResultTypeEnum): Result<*> {
      return Result(typeEnum, null)
    }

    fun error(typeEnum: ResultTypeEnum, msg: String): Result<*> {
      return Result(typeEnum, msg)
    }
  }
}

enum class ResultTypeEnum(val code: Int, val msg: String) {
  SERVICE_SUCCESS(200, "成功"),
  PARAM_ERROR(40001, "入参异常"),
  SERVICE_ERROR(500, "服务异常");
}