# Домашнее задание к занятию «3.2. Generics и коллекции»

В качестве CRUD системы был создан абстрактный класс [CrudService.kt](/src/main/kotlin/CrudService.kt) с дженерик функциями и через него был реализован основной сервис [NoteService](/src/main/kotlin/NoteService.kt) для класса заметок [Note](/src/main/kotlin/Note.kt) и вспомогательный сервис для работы с классом комментариев [Comment](/src/main/kotlin/Comment.kt)  на том же астрактном классе [CommentService](/src/main/kotlin/CommentService.kt) 

Функции из [CommentService](/src/main/kotlin/CommentService.kt) вызываются через [NoteService](/src/main/kotlin/NoteService.kt) при методах работы с комментариями

Класс [Note](/src/main/kotlin/Note.kt) содержит поля Note VK кроме `read_comments` и `view_url`, а также дополнительно содержит список комментариев `commentsList`, прикрепленных именно к этому посту

Класс [Comment](/src/main/kotlin/Comment.kt)  дополнительно содержит список флаг `deleted`, для пометки объекта что он был удален (и затем может быть восстановлен)

Каждый комментарий хранится в общем списке комментариев в `CommentService` и там при удалении лишь изменяется флаг, а также копией внутри `commentsList` конкретной заметки, которая удаляется при удалении заметки.

Тесты для каждого сервиса разнесены в свои файлы [NoteServiceTest](/src/test/kotlin/NoteServiceTest.kt) и [CommentServiceTest](/src/test/kotlin/CommentServiceTest.kt) 
