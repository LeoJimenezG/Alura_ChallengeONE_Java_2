# Alura_ChallengeOne_Java_2
Alura Challenge: LiterAlura. Java BackEnd

This program allows you to find books using the Gutendex API (https://gutendex.com/) and saves all the information searched in a PostgreSQL DataBase.

-- How does it work --
- Once you run the program, a total of 8 available options will be displayed:
1. Find book by title:
   - The search won't be case sensitive
   - You can write the full title of the book to search
   - Or you can write words that could be cointained in the book title
   - Once the search is completed, the result will be returned in the console
   - The return will be the request status code and the information of a single book
   - If you search the same thing several times the return could be different
   - If the search goes right, the book's information and the author's information will be saved in the DataBase
2. Find book by id:
   - If you know the book's id, use this option, it is way more precise
   - When you write the book's id the result will be returned in the console
   - The return will be the request status code and the information of a single book
   - If you search the same thing several times the return will be not be different
   - If the search goes right, the book's information and the author's information will be saved in the DataBase
3. Show all books:
   - This option will use the information saved in the DataBase
   - It will return all the books you have searched
   - It includes the books found by title or by id, but there will be no difference
   - Every book will have all its information
4. Show all authors:
   - This option will use the information saved in the DataBase
   - It will return all the authors of the books you have searched
   - Every author will hace their information
5. Show authors by year:
   - This option allows you to find authors alive in a range of time
   - The input is very specific: StartingYear-EndYear
   - An example of an input is: 1850-1910
   - A single year is not valid
   - If the EndYear is not greater that the StartingYear is not valid
   - It will return all the authors that match the following condition:
   - author.birthyear >= :StartingYear AND author.deathyear <= :EndYear
6. Show books by a language:
   - This option will use the information saved in the DataBase
   - If you want to see the books written in an specific language use this one
   - The input is based on the ISO 639-1 Standard Language Codes
   - An example of an input is: es, for spanish
   - It will return the total of books written in that language and the books
7. Show top 5 books:
   - This option will use the information saved in the DataBase
   - It will return the five books with the highest downloads number
   - The books returned will be in descending order
0. Exit:
   - Finishes the execution of the program
