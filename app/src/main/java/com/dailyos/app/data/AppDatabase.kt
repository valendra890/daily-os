package com.dailyos.app.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// === ENTITIES ===
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val priority: Int = 1,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val amount: Long,
    val category: String,
    val note: String? = null,
    val date: Long = System.currentTimeMillis()
)

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val target: Int,
    val progress: Int = 0,
    val period: String = "MONTHLY"
)

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val date: Long = System.currentTimeMillis()
)

// === DAOS ===
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<TaskEntity>>
    @Insert suspend fun insert(t: TaskEntity)
    @Update suspend fun update(t: TaskEntity)
    @Delete suspend fun delete(t: TaskEntity)
}

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun observeAll(): Flow<List<TransactionEntity>>
    @Insert suspend fun insert(t: TransactionEntity)
    @Delete suspend fun delete(t: TransactionEntity)
}

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals ORDER BY id DESC")
    fun observeAll(): Flow<List<GoalEntity>>
    @Insert suspend fun insert(g: GoalEntity)
    @Update suspend fun update(g: GoalEntity)
    @Delete suspend fun delete(g: GoalEntity)
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun observeAll(): Flow<List<NoteEntity>>
    @Insert suspend fun insert(n: NoteEntity)
    @Delete suspend fun delete(n: NoteEntity)
}

// === DATABASE ===
@Database(
    entities = [TaskEntity::class, TransactionEntity::class, GoalEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun transactionDao(): TransactionDao
    abstract fun goalDao(): GoalDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dailyos.db"
            ).build().also { INSTANCE = it }
        }
    }
}
