package com.interview.task.http.model

import zio.json.DeriveJsonDecoder
import zio.json.JsonDecoder

/**
 * Model for POST-request processing.
 */
case class SplitTextRequest(text: String, limit: Int)

object SplitTextRequest {
  implicit val decoder: JsonDecoder[SplitTextRequest] = DeriveJsonDecoder.gen[SplitTextRequest]
}
