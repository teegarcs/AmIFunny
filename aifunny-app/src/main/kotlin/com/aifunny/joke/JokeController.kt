package com.aifunny.joke

import com.aifunny.model.joke.GenerateJokeRequest
import com.aifunny.model.joke.JokeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class JokeController {

    @Autowired
    lateinit var jokeService: JokeService

    @RequestMapping("/joke", method = [RequestMethod.POST])
    fun generateJoke(@RequestBody request: GenerateJokeRequest): JokeResponse {
        return jokeService.generateJoke(request) ?: run {
            throw NullPointerException("Unable to generate joke with provided parameters.")
        }
    }
}