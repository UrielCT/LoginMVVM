package com.example.loginmvvm.core

import java.util.regex.Matcher
import java.util.regex.Pattern

//fun String.happy():String = "$this :)"


//fun Activity.color(@ColorRes color: Int) = ContextCompat.getColor(this,color)


//fun Any?.isNull() = this == null


/*fun Activity.toast(text:String, length:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, length).show()
}*/

/*fun ImageView.load(url:String){
    if(url.isNotEmpty()){
        Glide.with(this.context).load(url).into(this)
    }
}*/


/*fun EditText.onTextChanged(listener:(String) -> Unit){
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("Not yet implemented")
        }

        override fun afterTextChanged(s: Editable?) {
            listener(s.toString())
        }

    })
}*/


fun isEmailValid(email: String?): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(email)
    return matcher.matches()
}
