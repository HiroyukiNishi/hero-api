package lambda.hero

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import domains.Hero
import infrastructures.HeroDBClient

trait PutHeroComponent extends RequestHandler[PutHeroRequest, Unit] {
  val client: HeroDBClient

  override def handleRequest(input: PutHeroRequest, context: Context): Unit = client.update(Hero(input.id.toLong, input.name))
}

class PutHeroController extends PutHeroComponent {
  override val client: HeroDBClient = new HeroDBClient()
}
