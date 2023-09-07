package com.aifunny.joke

import com.aifunny.llm.LLMService
import com.aifunny.model.joke.GenerateJokeRequest
import com.aifunny.model.joke.JokeResponse
import com.aifunny.model.joke.JokeType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to generate jokes from the client joke request
 */
interface JokeService {

    /**
     * Takes in a JokeRequest object and generates a joke by reaching out to the LLM Service
     * after generating a prompt based on the request.
     */
    fun generateJoke(jokeRequest: GenerateJokeRequest): JokeResponse?
}

@Service
class JokeServiceImpl() : JokeService {

    @Autowired
    lateinit var llmService: LLMService

    override fun generateJoke(jokeRequest: GenerateJokeRequest): JokeResponse? {
        val promptTemplate = getPromptTemplate(jokeRequest.jokeType)
        val ffmPrompt = if (jokeRequest.familyFriendly) {
            JokePrompts.FF_SUFFIX
        } else {
            ""
        }
        val nouns = jokeRequest.jokeNouns.joinToString(", ")

        val fullTemplate = promptTemplate.replace(JokePrompts.NOUN_PLACE_HOLDER, nouns) + ffmPrompt

        System.out.println("Joke Prompt: $fullTemplate")
        val llmResponse = llmService.sendPrompt(fullTemplate, temperature = .7F)
        return llmResponse?.let {
            JokeResponse(joke = it)
        }
    }

    /**
     * takes a joke type and returns the prompt Template
     */
    private fun getPromptTemplate(jokeType: JokeType): String {
        return when (jokeType) {
            JokeType.Dad -> JokePrompts.DAD_PROMPT
            JokeType.Knock -> JokePrompts.KNOCK_PROMPT
            JokeType.Pun -> JokePrompts.PUN_PROMPT
            JokeType.Corny -> JokePrompts.CORNY_PROMPT
        }
    }

}