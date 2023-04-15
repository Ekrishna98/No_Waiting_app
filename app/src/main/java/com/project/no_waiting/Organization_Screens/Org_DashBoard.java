package com.project.no_waiting.Organization_Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.project.no_waiting.R;

public class Org_DashBoard extends AppCompatActivity {
    Button GenerateQR;
    EditText text;
    ImageView QRCodeImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_dash_board);

        GenerateQR = findViewById(R.id.Generate_QR);
        text = findViewById(R.id.EnterQrValue);
        QRCodeImage = findViewById(R.id.QRImage);

        GenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQRCodeGenerator();
            }
        });
    }

    private void setBarCodeGenerator(){
        String QRValue = text.getText().toString();
        if(QRValue.isEmpty()){
            text.requestFocus();
            text.setError("Please Enter Value");
            return;
        }else {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(QRValue, BarcodeFormat.CODE_128, QRCodeImage.getWidth(), QRCodeImage.getHeight());
                Bitmap bitmap = Bitmap.createBitmap(QRCodeImage.getWidth(), QRCodeImage.getHeight(), Bitmap.Config.RGB_565);
                for (int i = 0; i < QRCodeImage.getWidth(); i++) {
                    for (int j = 0; j < QRCodeImage.getHeight(); j++) {
                        bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                    }
                }
                QRCodeImage.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

    private void setQRCodeGenerator() {

        String QRValue = text.getText().toString();
        if(QRValue.isEmpty()){
            text.requestFocus();
            text.setError("Please Enter Value");
            return;
        }else {
            QRCodeWriter writer = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = writer.encode(QRValue, BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
                QRCodeImage.setImageBitmap(bmp);
                //  ((ImageView) findViewById(R.id.img_result_qr)).setImageBitmap(bmp);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}