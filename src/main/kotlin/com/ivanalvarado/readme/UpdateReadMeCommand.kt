package com.ivanalvarado.readme

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.path
import com.ivanalvarado.readme.client.AdaptToDomain
import com.ivanalvarado.readme.client.GithubClientImpl
import com.ivanalvarado.readme.domain.usecase.AdaptGithubEventsToActivityItemsImpl
import com.ivanalvarado.readme.domain.usecase.GenerateReadMeTemplateImpl
import com.ivanalvarado.readme.domain.usecase.GetGithubEventsImpl
import com.ivanalvarado.readme.network.RetrofitHelper
import kotlinx.coroutines.runBlocking
import okio.FileSystem
import okio.Path.Companion.toOkioPath
import java.nio.file.Path
import kotlin.system.exitProcess

class UpdateReadMeCommand : CliktCommand() {

    private val apiToken: String by option(help = "All requests require a Github API token.").required()

    private val login: String by option(help = "The login to fetch user events for.").required()

    private val outputFile: Path by option(help = "The README file to update.").path().required()

    override fun run() {
        val newReadMe = runBlocking {
            val service = RetrofitHelper.getInstance(apiToken)
            val adaptToDomain = AdaptToDomain()
            val client = GithubClientImpl(service, adaptToDomain)
            val githubEvents = GetGithubEventsImpl(client)(login)
            val activityItems = AdaptGithubEventsToActivityItemsImpl()(githubEvents)
            GenerateReadMeTemplateImpl()(activityItems)
        }

        echo(newReadMe)
        FileSystem.SYSTEM.write(outputFile.toOkioPath()) { writeUtf8(newReadMe) }
        exitProcess(0)
    }
}

fun main(args: Array<String>) {
    UpdateReadMeCommand().main(args)
}
