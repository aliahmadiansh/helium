

import androidx.room.Database
import androidx.room.RoomDatabase
import com.article.feature.article.data.database.dao.ArticleDao
import com.article.feature.article.data.database.dao.BookmarkDao
import com.article.feature.article.data.database.dao.TagDao
import com.article.feature.article.data.database.entity.ArticleEntity
import com.article.feature.article.data.database.entity.BookmarkEntity
import com.article.feature.article.data.database.entity.TagEntity
import com.article.feature.authentication.data.database.dao.AuthenticationDao
import com.article.feature.authentication.data.database.entity.UserInfoEntity
import com.article.feature.profile.data.database.dao.MyArticleDao
import com.article.feature.profile.data.database.entity.MyArticleEntity


@Database(
    entities = [
        UserInfoEntity::class,
        ArticleEntity::class,
        TagEntity::class,
        BookmarkEntity::class,
        MyArticleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun authenticationDao(): AuthenticationDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun articleDao(): ArticleDao
    abstract fun tagDao(): TagDao
    abstract fun myArticleDao(): MyArticleDao
}