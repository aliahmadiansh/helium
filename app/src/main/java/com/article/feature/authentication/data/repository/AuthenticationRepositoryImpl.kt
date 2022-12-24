

import com.article.core.common.ApiResultWrapper
import com.article.feature.authentication.data.database.entity.UserInfoEntity
import com.article.feature.authentication.data.datasource.local.AuthLocalDataSource
import com.article.feature.authentication.data.datasource.remote.AuthRemoteDataSource
import com.article.feature.authentication.data.network.model.*
import com.article.feature.authentication.data.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource
) : AuthenticationRepository {

    override suspend fun postUserSignIn(userInfo: UserSignInBody):
            ApiResultWrapper<UserSignInResponse> = remoteDataSource.postUserSignIn(userInfo)

    override suspend fun postUserExist(userInfo: UserExistBody):
            ApiResultWrapper<UserExistResponse> = remoteDataSource.postUserExist(userInfo)

    override suspend fun postUserSignUp(userInfo: UserSignUpBody): ApiResultWrapper<Unit> =
        remoteDataSource.postUserSignUp(userInfo)

    override suspend fun getUserInfo(): ApiResultWrapper<UserInfoResponse> =
        remoteDataSource.getUserInfo()

    override suspend fun addUserInfoToDatabase(userInfo: UserInfoEntity) =
        localDataSource.addUserInfo(userInfo)

    override suspend fun getUserInfoFromDatabase(): UserInfoEntity = localDataSource.getUserInfo()

    override fun checkExistToken(): Boolean = !localDataSource.getToken().isNullOrBlank()

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun getToken(): String? = localDataSource.getToken()

    override fun saveUserID(userID: Int) {
        localDataSource.saveUserID(userID)
    }

    override fun getUserID(): Int = localDataSource.getUserID()
}