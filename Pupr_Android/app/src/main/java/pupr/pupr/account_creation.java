package pupr.pupr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;
import java.sql.*;

public class account_creation extends AppCompatActivity {

//Load text from the boxes on the account creation page
    EditText firstName;
    EditText lastName;
    EditText emailAddr;
    EditText firstPass;
    EditText confirmPass;
    TextView registered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_account_creation);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddr = findViewById(R.id.email);
        firstPass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);

        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String email = emailAddr.getText().toString();
        String pass = firstPass.getText().toString();
        String confirmPassword = confirmPass.getText().toString();

        registered = findViewById(R.id.success);


        Button register =  findViewById(R.id.register); //find the button

        register.setOnClickListener(new View.OnClickListener() { //Set a click listener for the button
            @Override
            public void onClick(View view) {
                try {
                    if (pass.equals(confirmPassword)) {
                        User newUser = new User(fname, lname, email, pass); //call the user creation method
                      String welcome = String.format("Welcome to Pupr, %s", newUser.getFirstName());
                       registered.setText(welcome);
                    }

                } catch(IOException | SQLException e) { //Catch undeclared IO and SQL exceptions by throwing a new Runtime Exception. Must do this because you can't declare these with the overridden onCreate or onClick methods
                    throw new RuntimeException(e);

                  }

            }
        });
    }
}
