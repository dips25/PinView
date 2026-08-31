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

<img width="720" height="1650" alt="Screenshot_2026-08-31-16-33-26-434_com pins pinviewotp" src="https://github.com/user-attachments/assets/ae1f63da-7e1c-40be-b0ea-2b3748f9a680" />
<img width="720" height="1650" alt="Screenshot_2026-08-31-16-33-18-490_com pins pinviewotp" src="https://github.com/user-attachments/assets/bf00e4d5-8448-4f2a-b4b2-26e86c776a7b" />

Files-https://drive.google.com/drive/folders/1CQ9WjNy1jZQtKFN8IDevKNYYY3F2Q6pT?usp=sharing

