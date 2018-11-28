0. Завести учётную запись на github.com;
1. Установить на ПК:
- jdk1.8 https://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html#javasejdk,
- maven https://maven.apache.org/download.cgi. Распакуйте архив и добавьте путь к каталогу bin в переменную окружения path. Чтобы протестировать правильность установки Maven, запустите в командной строке:
mvn -v
Если всё было сделано правильно, то вы увидите сообщение примерно такого содержания:

Apache Maven 3.0.5 (r01de14724cdef164cd33c7c8c2fe155faf9602da; 2013-02-19 07:51:28-0600)
Maven home: /usr/share/maven
Java version: 1.7.0_09, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_09.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.8.3", arch: "x86_64", family: "mac"

Теперь у вас есть установленный Maven.

- IntelliJ Idea Community https://www.jetbrains.com/idea/download/#section=windows,
- git https://gitforwindows.org/.
2. Связать Git и учётную запись;
3. В консоли гита переходите в папочку где будет лежать проект и делаете клон git clone 'https://github.com/yunusov/NC_FF1_2018_repository.git' -b 'Student Branch Name';
4. Открываете в JIDEA проект через pom.xml, maven попросит скачать библиотеки;
5. Собираете проект [Ctrl] + [F9];
6. Запускаете App.main();
7. В браузере переходите по ссылке http://localhost:8080/. Если открылась страничка с приветствием, то всё сделали правильно.

Полезные ссылки:
Git для новичков https://www.youtube.com/watch?v=PEKN8NtBDQ0

Git установка и публикация (что-то про публичные ключи) https://www.youtube.com/watch?v=qt-QDN3MyeM

Офсайт Spring: https://spring.io/guides

Переводы статей: https://o7planning.org/
