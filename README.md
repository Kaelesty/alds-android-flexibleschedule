<h1>Проект: сервис адаптивного расписания</h1>
<h2>Основной функционал:</h2>
<ul>
  <li>Старосты/студенты могут сами составлять расписание, ведь оно не всегда совпадает с официальным или есть несколько подгрупп</li>
  <li>Вместе с расписанием, которое создаётся вручную создаётся группа, по которой другие студенты могут подключится к созданному расписанию</li>
  <li>Сервис будет адаптирован: как мобильное приложение</li>
</ul>
<h2>Стек технологий:</h2>
<ul>
  <li>Backend – C#, Asp.net, mySql, EntityFramwork</li>
  <li>Android – Kotlin, Retrofit, Room </li>
</ul>
<p>Человек, заходя на сайт регистрируется, его данные сохраняются в базе данных, после он сможет по номеру своей группы найти своё расписание. Так же пользователь может создать своё расписание, с определённым именем, по которому другие студенты смогут к нему получить доступ.</p>
<p>Основное расписание отображается на главной странице сайта, в соответсвии с неделей</p>

<h2>Состав команды</h2>
<ul>
  <li>Бунделев Илья Алексеевич <b>4215</b></li>
</ul>

<h2>Сборка и запуск проекта</h2>
<ul>
  <li>0. Скачать код проекта с Github</li>
</ul>
<h3>Веб-приложение</h3>
<ol>
  <li>
    Скачать и установить <a href="https://visualstudio.microsoft.com/ru/vs/">Visual Studio 2022</a> (Версия 17.8.3 или более новая) и установить компонент "ASP.NET и разработка веб-приложений"<br>
    <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/a168b4d4-f77a-490d-966e-70f4634522e5">

  </li>
  <li>
    Скачать и установить <a href="https://nodejs.org/en">NodeJS</a> (Версия LTS 20.11.0)
  </li>
  <li>
    Скачать и установить <a href="https://dev.mysql.com/downloads/">MySQL Community</a> (Версия 8.0.34 или более новая)
  </li>
  <li>
    Создать пользователя MySQL и внести его данные в файл FlexibleSchedule/appsettings.json<br>

        "AllowedHosts": "*",
          "ConnectionStrings": {
            "user": "server=localhost;port=3306;user=/user/;password=/password/;database=FlexibleSchedule",
          }
        }
Заменив /user/ и /password/ на имя пользователя и пароль MYSQL соответственно.
  </li>
  <li>
    Открыть файл FlexibleSchedule.sln как решение VisualStudio и запустить сборку проекта
  </li>
</ol>
<h3>Android-приложение</h3>
<ol>
  <li>
    Скачать и установить <a href="https://developer.android.com/studio">Android Studio</a> (Версия Giraffe | 2022.3.1 или более новая)
  </li>
  <li>
    Открыть директорию /FlexibleSchedule/AndroidApp как проект AndroidStudio
  </li>
  <li>
    Открыть файл build.gradle (Модуля app) и выполнить синхронизацию проекта (Sync Now)<br>
    <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/53aad504-ad17-4af5-bfd0-6fdbe804a13f">
  </li>
  <li>
    (В директории проекта) В директории app/src/main/java/com/kaelesty/flexibleschedule/data/remote внести изменения в файлы GroupServiceFactory.kt и AuthServiceFactory.kt:<br><br>
   
    private const val URL = "/URL/" + "..."


  Заменив /URL/ на адрес, по которому доступно веб-приложение
  </li>
  <li>
    Выпонить сборку проекта ("Make Project")
    <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/d17a550a-aeae-4a51-9d13-35c2a445ec1f">

  </li>
</ol>
<h4>Для запуска в режиме отладки:</h4>
<ol>
  <li>
      В менеджере устройств выбрать подключенное устройство в режиме отладки или эмулятор<br>
    <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/6c2bb661-f569-4a7f-9733-7cb33e103b1e">

  </li>
  <li>
    Запустить ("Run 'app'")<br>
    <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/98525f05-cc4d-4334-a8ab-a4ece86649a3">

  </li>
</ol>
<h4>Для сборки файла APK:</h4>
<ol>
  <li>
      Собрать файл ("Build APK(s)")<br>
   <img src="https://github.com/Kaelesty/alds-android-flexibleschedule/assets/74826130/e72eac8c-8b62-48c6-9873-b53db9b51201">
  </li>
</ol>


