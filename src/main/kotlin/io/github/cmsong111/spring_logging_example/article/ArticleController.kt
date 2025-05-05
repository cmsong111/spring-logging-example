package io.github.cmsong111.spring_logging_example.article

import java.net.URI
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping
    fun getAllArticles(
        @PageableDefault(size = 10, page = 0)
        pageable: Pageable
    ): ResponseEntity<PagedModel<Article>> {
        return ResponseEntity.ok(
            PagedModel(
                articleService.getArticles(pageable),
            )
        )
    }

    @GetMapping("/{id}")
    fun getNoteById(
        @PathVariable id: Long,
    ): ResponseEntity<Article> {
        return ResponseEntity.ok(
            articleService.getArticle(id)
        )
    }

    @PostMapping
    fun createArticle(
        @RequestBody form: ArticleForm
    ): ResponseEntity<Article> {
        val article = articleService.createArticle(
            form.title,
            form.content
        )
        return ResponseEntity.created(
            URI.create("/api/v1/articles/${article.id}")
        ).body(
            article
        )
    }

    @PatchMapping("/{id}")
    fun updateArticle(
        @PathVariable id: Long,
        @RequestBody form: ArticleForm
    ): ResponseEntity<Article> {
        return ResponseEntity.ok(
            articleService.updateArticle(
                id,
                form.title,
                form.content
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        articleService.deleteArticle(id)
        return ResponseEntity.noContent().build()
    }

    data class ArticleForm(
        val title: String,
        val content: String
    )
}
