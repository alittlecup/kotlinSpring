package cn.huangbaole.kotlinremote.demo.exception

import com.alibaba.fastjson.JSON
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class WebLogAspect {
  private val logger = LoggerFactory.getLogger(WebLogAspect::class.java)
  /** 以 controller 包下定义的所有请求为切入点 */
  @Pointcut("execution(public * cn.huangbaole.kotlinremote.demo.controller..*.*(..))")
  fun webLog() {
  }

  @Before("webLog()")
  fun doBefore(joinpoint: JoinPoint) {
    var attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
    var request = attributes.request
    // 打印请求相关参数
    logger.info(
        "========================================== Start ==========================================");
    // 打印请求 url
    logger.info("URL            : {}", request.requestURI.toString())
    // 打印 Http method
    logger.info("HTTP Method    : {}", request.method)
    // 打印调用 controller 的全路径以及执行方法
    logger.info("Class Method   : {}.{}", joinpoint.signature.declaringTypeName,
        joinpoint.signature.name)
    // 打印请求的 IP
    logger.info("IP             : {}", request.remoteAddr)
    // 打印请求入参
    logger.info("Request Args   : {}", JSON.toJSON(joinpoint.args))
  }

  @After("webLog()")
  fun doAfter() {
    logger.info(
        "=========================================== End ===========================================")
    // 每个请求之间空一行
    logger.info("")
  }

  @Around("webLog()")
  fun doAround(proceedingJoinPoint: ProceedingJoinPoint): Any {
    var currentTimeMillis = System.currentTimeMillis()
    var result = proceedingJoinPoint.proceed()
    // 打印出参
    logger.info("Response Args  : {}", JSON.toJSON(result))
    // 执行耗时
    logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - currentTimeMillis)
    return result

  }


}