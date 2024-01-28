package com.ivanalvarado.readme

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.ivanalvarado.readme.network.GithubService
import com.ivanalvarado.readme.network.RetrofitHelper
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

class ReadMeUpdaterCommand : CliktCommand() {

    private val apiToken: String by option(help = "All requests require a Github API token.").required()

    private val login: String by option(help = "The login to fetch user events for.").required()

    override fun run() {
        val retrofit = RetrofitHelper.getInstance(apiToken)
        val service = retrofit.create(GithubService::class.java)
        runBlocking {
            val response = service.userEvents(login)
            if (response.isSuccessful) {
                echo("${response.body()}")
            } else {
                echo("Failed to fetch")
            }
            exitProcess(0)
        }
    }
}

fun main(args: Array<String>) {
    ReadMeUpdaterCommand().main(args)
}
