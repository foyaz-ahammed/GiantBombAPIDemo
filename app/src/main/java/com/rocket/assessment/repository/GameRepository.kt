package com.rocket.assessment.repository

import com.rocket.assessment.BuildConfig
import com.rocket.assessment.entities.DataResult
import com.rocket.assessment.entities.Response
import com.rocket.assessment.repository.apis.GameAPI
import com.rocket.assessment.utility.Constants
import java.lang.Exception

/**
 * Repository class
 */
class GameRepository(private val api: GameAPI) {

    suspend fun getGameResult(keyword: String): DataResult<List<Response.GameItem>> {
        return try {
            val gameResult = api.getGameResult(
                apiKey = BuildConfig.API_KEY,
                filter = "name:$keyword",
                format = Constants.JSON
            )
            DataResult.Success(gameResult.results)
        } catch (e: Exception) {
            DataResult.Failure(e)
        }
    }

}