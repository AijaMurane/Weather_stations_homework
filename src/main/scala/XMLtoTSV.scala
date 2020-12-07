import java.io.{File, FileWriter, PrintWriter}
import scala.xml.XML.loadFile
import scala.collection.mutable.Map


object XMLtoTSV extends App {
  val allStations = loadFile("./src/resources/LV_meta2.xml")
  val country = (allStations \\ "country_name").text
  val stations = allStations \\ "station_info"
  val stationsAlldetails = allStations \\ "station"

  for (st <- stationsAlldetails) {
    val station_name = (st \ "station_name").text
    val station_european_code = (st \ "station_european_code").text
    val fName: String = station_name + "_" + station_european_code + "_yearly.tsv"
    val fw = new FileWriter(new File(s"./src/resources/$fName"))
    val resultMap = Map[String, String]()

    val allMeasurements = st \\ "measurement_configuration"
    for (measurement <- allMeasurements) {
      val component_name = (measurement \ "measurement_info" \ "component_name").text
      resultMap += ("component_name" -> component_name)
      val component_caption = (measurement \ "measurement_info" \ "component_caption").text
      resultMap += ("component_caption" -> component_caption)
      val measurement_unit = (measurement \ "measurement_info" \ "measurement_unit").text
      resultMap += ("measurement_unit" -> measurement_unit)
      val measurement_technique_principle = (measurement \ "measurement_info" \ "measurement_technique_principle").text
      resultMap += ("measurement_technique_principle" -> measurement_technique_principle)
      val year = (measurement \ "@Year").text
      resultMap += ("year" -> year)
      val statistic_result = (measurement \\ "statistic_result").filter(result => result.toString.contains("P50")).head.text
      resultMap += ("statistic_result" -> statistic_result)
    }
    fw.write(s"${resultMap.keys.mkString("\t")} \n ${resultMap.values.mkString("\t")}")
    fw.close()
  }

}
