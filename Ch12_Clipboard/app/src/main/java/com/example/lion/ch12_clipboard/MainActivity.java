package com.example.lion.ch12_clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editCopy, editPaste;
    Button btnCopy, btnPaste;

    ClipboardManager clipboardManager;
    ClipData clipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCopy = (EditText) findViewById(R.id.editCopy);
        editPaste = (EditText) findViewById(R.id.editPaste);

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnPaste = (Button) findViewById(R.id.btnPaste);

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editCopy.getText().toString();
                clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

        btnPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData data = clipboardManager.getPrimaryClip();
                ClipData.Item item = data.getItemAt(0);

                String text = item.getText().toString();
                editPaste.setText(text);
                Toast.makeText(getApplicationContext(), "Text Pasted", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
