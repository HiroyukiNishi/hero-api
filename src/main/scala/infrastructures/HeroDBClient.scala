package infrastructures

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.{NameMap, ValueMap}
import com.amazonaws.services.dynamodbv2.document.{DynamoDB, Item, PrimaryKey}
import domains.{Hero, Heroes}
import infrastructures.HeroDBClient.{AttributeName, HeroConverter}

import scala.collection.JavaConversions._

class HeroDBClient {
  private val db = new DynamoDB(Regions.AP_NORTHEAST_1)
  private val table = db.getTable("heroes")

//  def findAll: Heroes = Heroes(table.scan().toList.map(x => HeroConverter.toHero(x)))
  def findAll: Heroes = Heroes(table.scan().toList.map(HeroConverter.toHero))
  def create(hero: Hero): Unit =  table.putItem(new Item().withPrimaryKey(AttributeName.Id, hero.id).withString(AttributeName.Name, hero.name))
  def delete(id: Int): Unit = table.deleteItem(new PrimaryKey("id", Int.box(id)))
  def update(hero: Hero): Unit = {
    val updateSpec = new UpdateItemSpec()
      .withPrimaryKey(AttributeName.Id, hero.id)
      .withUpdateExpression(s"SET #${ AttributeName.Name } = :v_name")
      .withNameMap(new NameMap().`with`("#" + { AttributeName.Name }, AttributeName.Name))
      .withValueMap(new ValueMap().withString(":v_name", hero.name))

    table.updateItem(updateSpec)
  }
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
