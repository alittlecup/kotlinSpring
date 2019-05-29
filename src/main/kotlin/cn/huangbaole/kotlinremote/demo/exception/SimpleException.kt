package cn.huangbaole.kotlinremote.demo.exception

import cn.huangbaole.kotlinremote.demo.http.Result
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

class SimpleException(string: String) : Exception(string)

@ControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(SimpleException::class)
  @ResponseBody
  fun jsonErrorHandler(request: HttpServletRequest, exception: SimpleException): Result<String?> {
    return Result.error(exception.message)
  }

  @ExceptionHandler(Exception::class)
  @ResponseBody
  fun baseException(request: HttpServletRequest, exception: Exception): Result<String?> {
    return Result.error(exception.message)
  }
}