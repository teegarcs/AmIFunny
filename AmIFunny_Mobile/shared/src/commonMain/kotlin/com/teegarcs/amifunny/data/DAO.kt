package com.teegarcs.amifunny.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

private var dao: DAO? = null

/**
 * Manually retrieve DAO Object until we get DI set up
 */
fun getDAO(): DAO {
    if (dao == null)
        dao = DAO()

    return dao!!
}

class DAO {
    private val config by lazy {
        RealmConfiguration.create(schema = setOf(SavedJoke::class))
    }
    val realm: Realm by lazy {
        Realm.open(config)
    }

    /**
     * Abstract away the inserting into realm while ensuring its not doing the work on the main thread.
     */
    suspend fun <T : RealmObject> insert(item: T): T = withContext(Dispatchers.IO) {
        realm.writeBlocking {
            copyToRealm(item)
        }
    }

    /**
     * Abstract away the retrieval of data from realm while ensuring its not doing the work on the main thread.
     */
    suspend inline fun <reified T : RealmObject> retrieve(): RealmResults<T> =
        withContext(Dispatchers.IO) {
            realm.query<T>().find()
        }

    /**
     * Abstract away the deleting from realm while ensuring its not doing the work on the main thread.
     */
    suspend fun <T : RealmObject> delete(item: T) = withContext(Dispatchers.IO) {
        realm.writeBlocking {
            findLatest(item)?.also { delete(it) }
        }
    }
}
