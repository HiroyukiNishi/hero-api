package lambda.hero

import scala.beans.BeanProperty

class DeleteHeroRequest(
                         @BeanProperty var id: String
                       ) {
  def this() = this(id = "")
}
