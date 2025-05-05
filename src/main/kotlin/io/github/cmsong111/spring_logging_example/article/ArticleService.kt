package io.github.cmsong111.spring_logging_example.article

import io.github.cmsong111.spring_logging_example.common.NotFoundException
import java.time.Instant
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    @Transactional
    fun createArticle(title: String, content: String): Article {
        return articleRepository.save(
            Article(
                title = title,
                content = content
            )
        )
    }

    @Transactional(readOnly = true)
    fun getArticle(id: Long): Article {
        return articleRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Article with id $id not found")
    }

    @Transactional(readOnly = true)
    fun getArticles(pageable: Pageable): Page<Article> {
        return articleRepository.findAll(pageable)
    }

    @Transactional
    fun updateArticle(
        id: Long,
        title: String,
        content: String,
    ): Article {
        val article = articleRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Article with id $id not found")

        article.title = title
        article.content = content
        article.updatedAt = Instant.now()

        return articleRepository.save(article)
    }

    @Transactional
    fun deleteArticle(id: Long) {
        val article = articleRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Article with id $id not found")

        articleRepository.delete(article)
    }
}
