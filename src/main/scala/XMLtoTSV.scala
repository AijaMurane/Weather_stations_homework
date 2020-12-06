import org.apache.hadoop.io.nativeio.NativeIO.renameTo
import org.apache.spark.sql.SparkSession

import java.io.{File, PrintWriter}
import scala.xml.XML.loadFile
import java.io.File
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.{asc, desc}


object XMLtoTSV extends App {
  val allStations = loadFile("./src/resources/LV_meta2.xml")

  val stations = allStations \\ "station_info"
  val stationsAlldetails = allStations \\ "station"

  for (st <- stationsAlldetails) {
    val station_name = (st \ "station_name").text
    val station_european_code = (st \ "station_european_code").text
    val fName: String = station_name + "_" + station_european_code + "_yearly.tsv"

    val allMeasurements = st \\ "measurement_configuration"
    for (measurement <- allMeasurements) {
      val component_name = (measurement \ "measurement_info" \ "component_name").text
      val component_caption = (measurement \ "measurement_info" \ "component_caption").text
      val measurement_unit = (measurement \ "measurement_info" \ "measurement_unit").text
      val measurement_technique_principle = (measurement \ "measurement_info" \ "measurement_technique_principle").text
      val measurement_details = (measurement \ "statistics" \ "statistics_average_group" \ "statistic_set" \ "statistic_result" \ "statistic_value").text


    }
  }

  /*
  val session = SparkSession.builder().appName("test").master("local").getOrCreate()
  println(s"Session started on Spark version ${session.version}")

  val df = session.read
    .format("com.databricks.spark.xml")
    .option("rootTag", "station")
    .option("rowTag", "measurement_configuration")
    .load("./src/resources/LV_meta2.xml")
  df.printSchema()
*/


  /*
  val fPath = "./src/resources/test.tsv"
  val tmpPath = "./src/resources/tempTSV"
  df
    .coalesce(1)
    .write
    .option("header","true")
    .option("delimiter", "\t")
    .format("csv")
    .mode("overwrite")
    .save(tmpPath)
*/
  /*
  val dir = new File(tmpPath)
  val tmpTsfFile = dir.listFiles.filter(_.toPath.toString.endsWith(".csv"))(0).toString
  new File(tmpTsfFile).renameTo(new File(fPath))
 //   dir.listFiles.foreach( f => f.delete )
 // dir.delete
*/


  //df.write.option("delimiter", "\t").csv("testingAija.tsv")


  /*
  val XMLfile = loadFile("./src/resources/LV_meta2.xml")
  val stations = XMLfile \\ "station"

  for (st <- stations) {
    val station_name = (st \ "station_name").text
    val station_european_code = (st \ "station_european_code").text
    val fName: String = station_name + "_" + station_european_code + "_yearly.tsv"

    val measurement_configuration = st \ "measurement_configuration"

    val session = SparkSession.builder().appName("test").master("local").getOrCreate()

    val df = session.read
    .format("com.databricks.spark.xml")
    .option("rootTag", "station")
    .option("rowTag", "measurement_configuration")
    .load("./src/resources/LV_meta2.xml")



    //val pw = new PrintWriter(new File(fName))
  }
*/



}
