package com.github.fnunezkanut

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.serverless.ApiGatewayV1LambdaFunction


val http4kApp = routes(
  "/echo/{message:.*}" bind GET to {
    Response(OK).body(
      it.path("message") ?: "(nothing to echo, use /echo/<message>)"
    )
  },
  "/" bind GET to { Response(OK).body("ok") }
)

//graal
//fun main() {
//  ApiGatewayV1FnLoader(http4kApp).asServer(AwsLambdaRuntime()).start()
//}

//nongraal
class HelloServerlessHttp4k : ApiGatewayV1LambdaFunction(http4kApp)