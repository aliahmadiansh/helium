
import androidx.lifecycle.LiveData
import com.article.feature.article.data.database.dao.TagDao
import com.article.feature.article.data.database.entity.TagEntity
import com.article.feature.article.data.datasource.local.TagHomeLocalDataSource
import javax.inject.Inject

class TagHomeLocalDataSourceImpl @Inject constructor(private val tagDao: TagDao) :
    TagHomeLocalDataSource {

    override suspend fun insertTag(tags: List<TagEntity>) = tagDao.insertTag(tags)
    override  fun getTags(): LiveData<List<TagEntity>> = tagDao.getTags()

}