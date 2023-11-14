package network

import com.balanmaharaja.zusers.module.model.ResponseListUsers
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Only 12 users data available
    @GET("/api/users?page=1&per_page=12")
    suspend fun getAllUsers(): Response<ResponseListUsers>
}