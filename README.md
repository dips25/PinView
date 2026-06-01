Welcome to the PinView wiki!

## Add dependency
`implementation("com.github.dips25:PinView:v1.1")`

## Add pinview to layout

```
<com.pins.pinview.PasswordView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@android:color/transparent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:id="@+id/pin_view"/>
```

## Get the pin

```
val pinView:PasswordView = findViewById(R.id.pin_view)

        val pickBtn: Button = findViewById(R.id.img_pick)
        pickBtn.setOnClickListener {

            val otp = pinView.getOtp()

            Toast.makeText(this@MainActivity,"Otp: $otp",Toast.LENGTH_SHORT).show()
            

        }
```
