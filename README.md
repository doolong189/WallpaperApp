# WallpaperApp
https://github.com/chthai64/SwipeRevealLayout
android:background="#CCCCCC"
private var tongTien = 10000
    private var isButton1Active = false
    private var isButton2Active = false
    private var statusClick : String = "" //false là chẵn // true là lẻ
val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnChan = findViewById<Button>(R.id.btnChan)
        val btnLe = findViewById<Button>(R.id.btnLe)
        val tvRandom = findViewById<TextView>(R.id.tvRandomNumber)
        val tvTongTien = findViewById<TextView>(R.id.tvTongTien)
        val edNhapTien = findViewById<EditText>(R.id.edNhapTien)

        tvTongTien.text = "Tổng số tiền: " + tongTien
        btnPlay.setOnClickListener {
            val handler = Handler(Looper.getMainLooper())
            if (edNhapTien.text.toString() != "" && edNhapTien.text.toString().toInt() > tongTien) {
                Toast.makeText(this, "Không nhập quá số tiền", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (edNhapTien.text.toString() == "") {
                Toast.makeText(this, "Yêu cầu nhập số tiền", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if (statusClick == "") {
                Toast.makeText(this, "Yêu cầu chọn chẵn hoặc lẻ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                for (i in 0..5) {
                    handler.postDelayed({
                        tvRandom.text = i.toString()
                        if (i == 5) {
                            val randomNumber = (0 until 100).random()
                            tvRandom.text = randomNumber.toString()
                            if (randomNumber % 2 == 0 && statusClick == "Chẵn") {
                                Toast.makeText(this, "Bạn chọn chẵn - kết quả chẵn", Toast.LENGTH_LONG)
                                    .show()
                                tongTien += edNhapTien.text.toString().toInt()
                            } else if (randomNumber % 2 == 0 && statusClick == "Lẻ") {
                                tongTien -= edNhapTien.text.toString().toInt()
                                Toast.makeText(this, "Bạn chọn chẵn - kết quả lẻ", Toast.LENGTH_LONG).show()
                            } else if (randomNumber % 1 == 0 && statusClick ==  "Lẻ") {
                                tongTien += edNhapTien.text.toString().toInt()
                                Toast.makeText(this, "Bạn chọn lẻ - kết quả lẻ", Toast.LENGTH_LONG).show()
                            } else if (randomNumber % 1 == 0 && statusClick == "Chẵn") {
                                tongTien -= edNhapTien.text.toString().toInt()
                                Toast.makeText(this, "Bạn chọn lẻ - kết quả chẵn", Toast.LENGTH_LONG).show()
                            }
                            tvTongTien.text = "Tổng số tiền: " + tongTien
                        }
                    }, i * 1000L)
                }
            }
        }

        btnChan.setOnClickListener {
            if (!isButton1Active) {
                btnChan.setBackgroundColor(Color.RED)
                btnLe.setBackgroundColor(Color.LTGRAY)
                isButton1Active = true
                isButton2Active = false
                statusClick = "Chẵn"
            }
        }

        btnLe.setOnClickListener {
            if (!isButton2Active) {
                btnLe.setBackgroundColor(Color.RED)
                btnChan.setBackgroundColor(Color.LTGRAY)
                isButton2Active = true
                isButton1Active = false
                statusClick = "Lẻ"
            }
        }
