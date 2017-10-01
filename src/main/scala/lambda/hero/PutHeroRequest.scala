package lambda.hero

import scala.beans.BeanProperty

class PutHeroRequest(
                      @BeanProperty var id: String,
                      @BeanProperty var name: String
                    ) {
  def this() = this(id = "")
}
