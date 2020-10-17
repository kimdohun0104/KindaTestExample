package dohun.kim.count

import kotlinx.coroutines.delay

interface CountRepository {

    suspend fun requestCount(): Int
}

class DefaultCountRepository : CountRepository {

    companion object {
        private const val COUNT_DATA = 100
    }

    override suspend fun requestCount(): Int {
        delay(4000)
        return COUNT_DATA
    }

}