package pupr.pupr;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class account_creation extends AppCompatActivity {

//Load text from the boxes on the account creation page
    EditText firstName = (EditText)findViewById(R.id.firstName);
    EditText lastName = (EditText)findViewById(R.id.lastName);
    EditText emailAddr = (EditText)findViewById(R.id.email);
    EditText firstPass = (EditText)findViewById(R.id.password);
    EditText confirmPass = (EditText)findViewById(R.id.confirmPassword);

    String fname = firstName.getText().toString();
    String lname = lastName.getText().toString();
    String email = emailAddr.getText().toString();
    String pass = firstPass.getText().toString();
    String confirmPassword = confirmPass.getText().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button register =  findViewById(R.id.register); //find the button

        register.setOnClickListener(new View.OnClickListener() { //Set a click listener for the button
            @Override
            public void onClick(View view) {
                try {
                    if (pass.equals(confirmPassword)) {
                        User newUser = new User(fname, lname, email, pass); //call the user creation method
                    }
                } catch(IOException | SQLException e) { //Catch undeclared IO and SQL exceptions by throwing a new Runtime Exception. Must do this because you can't declare these with the overridden onCreate or onClick methods
                    throw new RuntimeException(e);

                  }

            }
        });
    }
}
