package domains

import java.util

import scala.beans.BeanProperty

case class Heroes(
                   @BeanProperty var heroes: java.util.List[Hero]
                 ) {
  def this() = this(
    heroes = new util.ArrayList[Hero]()
  )
}

case class Hero(
                 @BeanProperty var id: Int,
                 @BeanProperty var name: String
               ) {
  def this() = this(
    id = 0,
    name = ""
  )
}
