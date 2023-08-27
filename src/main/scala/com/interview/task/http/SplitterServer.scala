package com.interview.task.http

import com.interview.task.TextSplitter
import com.interview.task.http.model.SplitTextRequest
import zio._
import zio.ZIOAppDefault
import zio.http._
import zio.json._

/**
 * Server provides opportunity to run split text function as a REST-service
 * with opportunity to process text via POST request
 */
object SplitterServer extends ZIOAppDefault {
  private val defaultPort = 8081

  val app: App[Any] = Http.collectZIO[Request] {
    case req @ Method.POST -> Root / "split" => {
      val res = for {
        body <- req.body.asString
        _ <- ZIO.logInfo(s"POST request with data: $body")
        splitReq = body.fromJson[SplitTextRequest]
        processResult <- splitReq match {
          case Right(req) =>
            ZIO
              .succeed(TextSplitter.splitTextByLines(req.text, req.limit))
              .map(Response.text(_))
              .tap(res => ZIO.logInfo(s"Request successfully with result: $res"))
          case Left(err) =>
            ZIO
              .logErrorCause(s"Failed to parse the input", Cause.fail(err))
              .as(Response.text(err).withStatus(Status.BadRequest))
        }
      } yield processResult

      res.orElseSucceed(Response.text("Request body could not be processed").withStatus(Status.BadRequest))
    }
  }

  override def run = {
    ZIO.logInfo(s"Starting service at $defaultPort port") *>
      Server.serve(app).provide(Server.defaultWithPort(defaultPort))
  }

}
