package cn.huangbaole.kotlinremote.demo.repository

import cn.huangbaole.kotlinremote.demo.entiy.Course
import cn.huangbaole.kotlinremote.demo.entiy.CourseType
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository:JpaRepository<Course,Long>{
}
interface CourseTypeRepository:JpaRepository<CourseType,Long>{

}