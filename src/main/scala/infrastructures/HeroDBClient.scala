package infrastructures

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.document.{DynamoDB, Item}
import domains.{Hero, Heroes}
import infrastructures.HeroDBClient.HeroConverter

import scala.collection.JavaConversions._

class HeroDBClient {
  private val db = new DynamoDB(Regions.AP_NORTHEAST_1)
  private val table = db.getTable("heroes")

  def findAll: Heroes = Heroes(table.scan().toList.map(x => HeroConverter.toHero(x)))
//  def findAll: Heroes = Heroes(table.scan().toList.map(HeroConverter.toHero)
}

object HeroDBClient {
  object AttributeName {
    val Id = "id"
    val Name = "name"
  }

  object HeroConverter {
    def toHero(item: Item): Hero =
      Hero(id = item.getInt(AttributeName.Id),
      name = item.getString(AttributeName.Name)
      )
  }
}
