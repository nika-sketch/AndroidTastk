package ge.nlatsabidze.firsttask

import android.os.*
import android.util.*
import android.widget.*
import androidx.appcompat.app.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.enterInput)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            val stringToNumber = editText.text.toString().toInt()
            val textVieResult:String = convert(stringToNumber)

            Toast.makeText(applicationContext, "Word is generated.", Toast.LENGTH_SHORT).show()
            textView.text = textVieResult
        }
    }

    private fun convert(n: Int): String {

        val toString = n.toString()
        val numberBeforeLastIndex = toString[toString.length - 2].toInt()

        val units = arrayOf("", "ერთი", "ორ", "სამ", "ოთხ", "ხუთ", "ექვს", "შვიდ",
                "რვა", "ცხრა", "ათი", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი",
                "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი")

        val secondUnits = arrayOf("", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი",
                "თვრამეტი", "ცხრამეტი")

        val tens = arrayOf( "", "", "ოცდა", "ოცდა", "ორმოცდა", "ორმოცდა", "სამოცდა", "სამოცდა",
                            "ოთხმოცდა", "ოთხმოცდა")

        when (n) {
            2 -> return "ორი"
            3 -> return "სამი"
            4 -> return "ოთხი"
            5 -> return "ხუთი"
            6 -> return "ექვსი"
            7 -> return "შვიდი"
            8 -> return "რვა"
            9 -> return "ცხრა"
            20 -> return "ოცი"
            30 -> return "ოცდაათი"
            40 -> return "ორმოცი"
            50 -> return "ორმოცდაათი"
            60 -> return "სამოცი"
            70 -> return "სამოცდაათი"
            80 -> return "ოთხმოცი"
            90 -> return "ოთხმოცდაათი"
        }

        if (n < 20) {
            return units[n]
        }

        if (n < 100) {
            var result = ""
            if (numberBeforeLastIndex % 2 == 0) {
                result = tens[n / 10] + units[n % 10]
                if (result[result.length - 1] != 'ი') {
                    result += 'ი'
                }
            } else {
                result = tens[n / 10] + secondUnits[n % 10]
            }
            return result
        }

        if (n < 1000) {
            var answer = units[n / 100] + "ას" + convert(n % 100)
            if (answer[answer.length - 1] != 'ი') {
                answer += 'ი'
            }

            return answer
        }

        if (n < 1000000) {
            return convert(n / 1000) + "ათას" + (if (n % 1000 != 0) "" else "") + convert(
                    n % 1000
            )
        }

        return if (n < 1000000000) {
            convert(n / 1000000) + " მილიონ" + (if (n % 1000000 != 0) " " else "") + convert(
                    n % 1000000
            )
        } else convert(n / 1000000000) + " მილიარდ" + (if (n % 1000000000 != 0) " " else "") + convert(
                n % 1000000000
        )

    }

}