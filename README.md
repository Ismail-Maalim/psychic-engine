# Psychic Engine

A simple REST API for managing books, built with Java 17 and Spring Boot. The API supports listing, retrieving, adding, and deleting books. Book data is stored in memory and is cleared when the application restarts.

## Run locally

From the `demo` directory, start the application with:

```bash
./mvnw spring-boot:run
```

The API is available at `http://localhost:8080/api/books`.

## Endpoints

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/books` | List all books |
| `GET` | `/api/books/{id}` | Get a book by ID |
| `POST` | `/api/books` | Add a book |
| `DELETE` | `/api/books/{id}` | Delete a book by ID |

To add a book, send JSON with an `id`, `title`, and `author`, for example:

```json
{
  "id": 1,
  "title": "Example Book",
  "author": "A. Writer"
}

```