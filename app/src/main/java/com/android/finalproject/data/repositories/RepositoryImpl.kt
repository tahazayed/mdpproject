package com.android.finalproject.data.repositories

import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.model.*
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

    override suspend fun getPopularMovies() = safeAPIResult(remoteDataSource.getPopularMovies())

    override suspend fun getPopularTvShows() = safeAPIResult(remoteDataSource.getPopularTvShows())

    override suspend fun getDiscoverMovies(sortBy: String) =
        safeAPIResult(remoteDataSource.getDiscoverMovies(sortBy))

    override suspend fun getDiscoverTvShows(sortBy: String) =
        safeAPIResult(remoteDataSource.getDiscoverTvShows(sortBy))

    override suspend fun searchMovies(query: String) =
        safeAPIResult(remoteDataSource.searchMovies(query))

    override suspend fun searchSeries(query: String) =
        safeAPIResult(remoteDataSource.searchSeries(query))

    override suspend fun getMovieVideos(movieId: Int) =
        safeAPIResult(remoteDataSource.getMovieVideos(movieId))

    override suspend fun getTvShowVideos(tvId: Int) =
        safeAPIResult(remoteDataSource.getTvShowVideos(tvId))

    // ---------- end remote------------

    // ---------- offline------------

    override fun dummyOffline() = offline.dummyOffline()

    // ---------- end offline------------

    // ---------- database------------

    override suspend fun getAllMatchedUser(email: String) = dbDataSource.getAllMatchedUser(email)

    override suspend fun addUser(user: User) = dbDataSource.addUser(user)

    override suspend fun getFavMovieList() = dbDataSource.getFavMovieList()

    override suspend fun addMovieToFav(movie: Movie) = dbDataSource.addMovieToFav(movie)

    override suspend fun removeMovieFromFav(movie: Movie) = dbDataSource.removeMovieFromFav(movie)

    override suspend fun getFavSeriesList() = dbDataSource.getFavSeriesList()

    override suspend fun addSeriesToFav(series: TvShow) = dbDataSource.addSeriesToFav(series)

    override suspend fun removeSeriesFromFav(series: TvShow) =
        dbDataSource.removeSeriesFromFav(series)

    override suspend fun deleteMyAccount(user: User) =
        dbDataSource.deleteMyAccount(user)

    override suspend fun deleteAllFav() =
        dbDataSource.deleteAllFav()

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