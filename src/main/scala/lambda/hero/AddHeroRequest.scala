package lambda.hero

import scala.beans.BeanProperty

class AddHeroRequest(
                      @BeanProperty var name: String
                    ) {

  def this() = this("")
}
