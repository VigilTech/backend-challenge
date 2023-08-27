# backend-challenge

## Task

After reading the Linux kernel coding style, you discover the magic of having lines of code with a maximum of 80 characters each.
So, you decide that from now on your outgoing emails will also follow a similar pattern and you decide to develop a plugin to help you with that.

Implement a function that receives:
- *any text*
- *a character limit per line*

and be able to generate a new text broken into lines with a maximum *limit of characters per line* defined.
Important note: **No words can be broken**.

**Input text example:**

"In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler."
 
**Example output 40 character:**

In 1991, while studying computer science<br>
at University of Helsinki, Linus<br>
Torvalds began a project that later<br>
became the Linux kernel. He wrote the<br>
program specifically for the hardware he<br>
was using and independent of an<br>
operating system because he wanted to<br>
use the functions of his new PC with an<br>
80386 processor. Development was done on<br>
MINIX using the GNU C Compiler.<br>

## Solution Quickstart

### Preparation

From project's directory run command:
```shell
sbt assembly
```
As a result uber jar will be generated.

### Run from command line

Run TextSplitter passing text and max number of characters in line.
To see parameters use help flag
```shell
scala -classpath "target/scala-2.13/backend-challenge-0.1.0-SNAPSHOT.jar" com.interview.task.TextSplitter --help
```
Example:
```shell
scala -classpath "target/scala-2.13/backend-challenge-0.1.0-SNAPSHOT.jar" com.interview.task.TextSplitter --limit 40 --text "QA walks into a bar and orders: 1 pint of beer, 0 pint of beer, -1 pint of beer, ..."
```

### Run as a REST service

To run this function as a REST service on 8081 localhost's port use this command:
```shell
scala -classpath "target/scala-2.13/backend-challenge-0.1.0-SNAPSHOT.jar" com.interview.task.http.SplitterServer
```
Now you can use send POST request via curl or e.g. Postman:
```
POST http://localhost:8081/split

{
  "limit": 40,
  "text": "In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler."
}
```

P.S. `scala` here interchangeable with `java` 