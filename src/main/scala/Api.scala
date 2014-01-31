package macondoventures

import com.typesafe.config._

object Main extends App {

  val conf = ConfigFactory.load()

  try {
    val helper = new SignedRequestsHelper(conf.getString("aws.accessKeyId"), conf.getString("aws.secretKey"))
    var params = Map(
      "AssociateTag" -> conf.getString("aws.associateTag"),
      "Service" -> "AWSECommerceService"
    )

    if (args.length > 0) {
      val extraParams = args(0).split('&') foreach { str =>
        val pair = str.split('=')
        params = params + (pair(0) -> pair(1))
      }
    }

    val url = helper.sign(params)
    val response = scala.io.Source.fromURL(url).mkString
    println(response)
  } catch {
    case e: ConfigException.Missing =>
      println("Copy the file application.conf.template to application.conf and fill-in the appropriate configuration values.")
      exit(1)
  }
}
