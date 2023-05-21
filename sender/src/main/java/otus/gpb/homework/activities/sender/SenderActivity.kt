package otus.gpb.homework.activities.sender

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import otus.gpb.homework.activities.common.Payload

class SenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sender_activity)
    }


    override fun onStart() {
        super.onStart()

        val buttonGoMap = findViewById<Button>(R.id.buttonGoMap)
        buttonGoMap.setOnClickListener {
            try {
                val uriMap = Uri.parse("geo:0,0?q=рестораны")                               //универсальный идентификатор ресурса Uri. geo: говорит о работе с координатами. 0,0?q= местность рядом. далее что ищем
                val intent = Intent(Intent.ACTION_VIEW, uriMap)                                     //не явный интент вызова карт с проброской туда значения uriMap
                intent.setPackage("com.google.android.apps.maps")                                   //делает intent явным
                startActivity(intent)                                                               //вызов намерения
            } catch (exception: ActivityNotFoundException) {                                        //исключение
                Toast.makeText(this, "карт нет, еды нет", Toast.LENGTH_LONG)
                    .show()                                                                          //всплывашка с сообщением при исключении
            }
        }

        val buttonSendEmail = findViewById<Button>(R.id.buttonSendEmail)
        buttonSendEmail.setOnClickListener {

            val uriMail = Uri.parse("mailto:android@otus.ru")                           //передает адресь письма. Uri.parse неким образом кодирует информацию
            val intent = Intent(Intent.ACTION_SENDTO, uriMail)
            intent.putExtra(Intent.EXTRA_SUBJECT, "тема письма")                          //передает тему письма если необходимо
            intent.putExtra(Intent.EXTRA_TEXT, "betta, work in progress")                 //текст сообщения
            try {
                startActivity(intent)
            } catch (exception: ActivityNotFoundException) {
                Toast.makeText(this, "нет подходящего приложения", Toast.LENGTH_LONG).show()
            }
        }

        val buttonOpenReceive = findViewById<Button>(R.id.buttonOpenReceive)

        val movie = Payload(
            "Интерстеллар",
            "2014",
            "Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному " +
                    "кризису, коллектив исследователей и учёных отправляется сквозь червоточину " +
                    "(которая предположительно соединяет области пространства-времени через большое расстояние) " +
                    "в путешествие, чтобы превзойти прежние ограничения для космических путешествий человека и найти " +
                    "планету с подходящими для человечества условиями."
        )

        val movie1 = Payload(
            "Славные парни",
            "2016",
            "Что бывает, когда напарником брутального костолома становится субтильный лопух? " +
                    "Наемный охранник Джексон Хили и частный детектив Холланд Марч вынуждены работать в паре, " +
                    "чтобы распутать плевое дело о пропавшей девушке, которое оборачивается преступлением века."
        )

        buttonOpenReceive.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)                                                 //неявное намерение для передачи данных
            intent.type = "text/plain"                                                              //филтор соответствующий неявному intent ACTION_SEND. принимающее прилоджение должно быть на него настроено!
            intent.addCategory(Intent.CATEGORY_DEFAULT)                                             //оно предназначено для использования в фильтрах намерений, указанных в пакетах. wtf???
            intent.putExtra("interstellar", movie)                                            //передаем объект с помощью Parcelable
            try {
                startActivity(intent)
            } catch (exception: ActivityNotFoundException) {
                Toast.makeText(this, "нет подходящего приложения", Toast.LENGTH_LONG).show()
            }
        }
    }

}

/*
setPackage("com.google.android.apps.maps")гарантирует, что приложение Google Maps для Android обработает Intent.
Если пакет не установлен, система определит, какие приложения могут обрабатывать файлы Intent.
Если доступно несколько приложений, пользователя могут спросить, какое приложение он хотел бы использовать.
https://developers.google.com/maps/documentation/urls/android-intents?hl=en#kotlin_2
intent.resolveActivity(packageManager)?.let {    //проверка на возможность выполнения намерения
    startActivity(intent)
 */