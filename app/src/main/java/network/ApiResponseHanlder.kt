package network

sealed class Result<out T>
{
    data class Success<out T>(val value: T) : Result<T>()

    data class Failure(val errorMessage: String? = null): Result<Nothing>()
}
