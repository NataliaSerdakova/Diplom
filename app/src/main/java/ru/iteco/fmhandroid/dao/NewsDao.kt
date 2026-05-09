package ru.iteco.fmhandroid.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.iteco.fmhandroid.dto.NewsWithCategory
import ru.iteco.fmhandroid.entity.NewsCategoryEntity
import ru.iteco.fmhandroid.entity.NewsEntity

@Dao
interface NewsDao {
    @Transaction
    @Query(
        """SELECT * FROM NewsEntity
            WHERE (:publishEnabled IS NULL OR :publishEnabled = publishEnabled)
            AND (:publishDateBefore IS NULL OR publishDate <= :publishDateBefore)
            AND (:newsCategoryId IS NULL OR :newsCategoryId = newsCategoryId)
            AND (:dateStart IS NULL OR publishDate >= :dateStart)
            AND (:dateEnd IS NULL OR publishDate <= :dateEnd)
            AND (:status IS NULL OR :status = publishEnabled)
            ORDER BY publishDate DESC
        """
    )
    fun getAllNews(
        publishEnabled: Boolean? = null,
        publishDateBefore: Long? = null,
        newsCategoryId: Int? = null,
        dateStart: Long? = null,
        dateEnd: Long? = null,
        status: Boolean? = null
    ): Flow<List<NewsWithCategory>>

    @Transaction
    @Query("SELECT * FROM NewsEntity")
    fun getAllNewsList(): List<NewsWithCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsItem: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<NewsEntity>)

    @Query("DELETE FROM NewsEntity WHERE id = :id")
    fun removeNewsItemById(id: Int)

    @Query("DELETE FROM NewsEntity WHERE id IN (:idList)")
    fun removeNewsItemsByIdList(idList: List<Int?>)
}

@Dao
interface NewsCategoryDao {
    @Query("SELECT * FROM NewsCategoryEntity ORDER BY id")
    fun getAllNewsCategories(): Flow<List<NewsCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<NewsCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: NewsCategoryEntity)

    @Query("SELECT * FROM NewsCategoryEntity")
    fun getNewsCategoryList(): List<NewsCategoryEntity>
}
