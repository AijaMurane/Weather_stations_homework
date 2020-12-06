import org.json4s.jackson.JsonMethods.{pretty, render}
import java.io.{File, PrintWriter, _}
import scala.xml.XML.loadFile
import org.json4s.Xml.{toJson, toXml}
import java.io.{File, PrintWriter}
import scala.io.Source

object XMLtoJSON extends App {
  val allStations = loadFile("./src/resources/LV_meta2.xml")

  val stations = allStations \\ "station_info"
  val stationsAlldetails = allStations \\ "station"

  for (st <- stations) {
    val jsonStation = toJson(st)
    val station_name = (st \ "station_name").text
    val station_european_code = (st \ "station_european_code").text
    val fName: String = station_name + "_" + station_european_code + "_meta.json"
    val pw = new PrintWriter(new File(fName))
    pw.write(pretty(render(jsonStation)))
    pw.close()
  }

  val country_name = (allStations \ "country" \ "country_name").text
  val fNameCountry: String = "stations_" + country_name + "_meta.json"
  val pw2 = new FileWriter(new File(fNameCountry))

  for (st <- stations) {
    val station_name = (st \ "station_name").text
    val station_european_code = (st \ "station_european_code").text
    val fName: String = station_name + "_" + station_european_code + "_meta.json"
    val jsonFile = Source.fromFile(s"./$fName").getLines.mkString
    pw2.write(jsonFile)
    pw2.write("\n")
  }

  pw2.close()
}
