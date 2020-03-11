package json

import akka.http.javadsl.model.ContentType
import akka.stream.scaladsl.Source
import akka.util.ByteString
import io.circe.Json
import play.Application
import play.api.http.{MimeTypes, Writeable}
import play.api.libs.ws.{BodyWritable, SourceBody}

/**
  * Interface to abstract writes calls.
  */
trait Writeable {

  /**
    * Implicit conversion from circe json to [[play.api.libs.ws.WSClient]] body.
    *
    * @return A [[play.api.libs.ws.BodyReadable]] instance.
    */
  implicit protected def circeJsonBodyWritable: BodyWritable[Json] = BodyWritable[io.circe.Json](
    json => SourceBody(Source.single(ByteString(json.toString()))),
    MimeTypes.JSON
  )
}
