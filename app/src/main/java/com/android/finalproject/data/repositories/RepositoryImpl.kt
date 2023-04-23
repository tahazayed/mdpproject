package com.android.finalproject.data.repositories

import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.model.APIError
import com.android.finalproject.data.model.APIResult
import com.android.finalproject.data.model.FailureException
import com.android.finalproject.data.model.User
import com.android.finalproject.data.offline.Offline
import com.android.finalproject.data.raw.RawDataSource
import com.android.finalproject.data.remote.RemoteDataSource
import com.android.finalproject.data.sharedpref.PrefDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val prefDataSource: PrefDataSource,
    private val offline: Offline,
    private val dbDataSource: DbDataSource,
    private val rawDataSource: RawDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    // ----------pref------------
    override fun getToken() = prefDataSource.getToken()

    override fun setToken(token: String) = prefDataSource.setToken(token)

    override fun getSharedPrefBaseAppResponse() = prefDataSource.getSharedPrefBaseAppResponse()

    override fun setSharedPrefBaseAppResponse(baseAppResponse: String) =
        prefDataSource.setSharedPrefBaseAppResponse(baseAppResponse)

    override fun logOut() = prefDataSource.logOut()


    // ---------- end pref------------

    // ----------raw------------
    override suspend fun getLocalBaseAppResponse() = withContext(Dispatchers.IO) {
        rawDataSource.getLocalBaseAppResponse()
    }

    // ----------end raw------------


    // ----------remote------------

    override suspend fun getBaseAppResponse() = safeAPIResult(remoteDataSource.getBaseAppResponse())

    // ---------- end remote------------

    // ---------- offline------------

    override fun dummyOffline() = offline.dummyOffline()

    // ---------- end offline------------

    // ---------- database------------

    override suspend fun getAllMatchedUser(email: String) = dbDataSource.getAllMatchedUser(email)

    override suspend fun addUser(user: User) = dbDataSource.addUser(user)

    // ---------- end database------------


    private fun <T> safeAPIResult(response: Response<T>): APIResult<T> {
        return when {
            response.code() in 200..299 -> {
                APIResult.Success(response.body())
            }
            response.code() == 401 -> {
                throw FailureException.InvalidUserException("", 401)
            }
            else -> {
                APIResult.Failure(APIError(response.raw().message, "", "", response.code()))
            }
        }
    }
}