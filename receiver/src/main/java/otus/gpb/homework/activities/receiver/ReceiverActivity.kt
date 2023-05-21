package otus.gpb.homework.activities.receiver

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import otus.gpb.homework.activities.common.Payload
import otus.gpb.homework.activities.receiver.R.drawable


class ReceiverActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)

        val movie = intent.getParcelableExtra<Payload>("interstellar")

        if(movie!!.title == "Интерстеллар"){
            findViewById<ImageView>(R.id.posterImageView).setImageDrawable(getDrawable(drawable.interstellar))
        } else {
            findViewById<ImageView>(R.id.posterImageView).setImageDrawable(getDrawable(drawable.niceguys))
        }
        findViewById<TextView>(R.id.titleTextView).text = movie.title
        findViewById<TextView>(R.id.yearTextView).text = movie.year
        findViewById<TextView>(R.id.descriptionTextView).text = movie.description

    }

}