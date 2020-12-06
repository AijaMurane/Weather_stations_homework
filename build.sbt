name := "Weather stations homework"

version := "0.1"

scalaVersion := "2.12.12"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.0.1"
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"

//Library to work with XML.
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.3.0"

//for parsing XML to dataframe
//https://github.com/databricks/spark-xml
libraryDependencies += "com.databricks" %% "spark-xml" % "0.10.0"

//https://mvnrepository.com/artifact/org.json4s/json4s-xml_2.13/3.7.0-M7
//for parsing XML to JSON
libraryDependencies += "org.json4s" %% "json4s-xml" % "3.7.0-M7"


