package com.example.mlwithtensorflowlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView result, confidence;
    ImageView imageView;
    Button picture;
    int imageSize = 224;

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);

        picture.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        });
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                result.setText("Camera permission is required");
            }
        }
    }

    private void classifyImage(Bitmap image) {
        try {
            // Try to initialize the model using reflection to avoid compilation issues
            Object model = initializeModel();
            if (model == null) {
                result.setText("Model initialization failed");
                return;
            }

            // Creates inputs for reference
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

            ByteBuffer byteBuffer = convertBitmapToByteBuffer(image);

            // Load the input buffer
            inputFeature0.loadBuffer(byteBuffer);

            // Process the image and get results
            float[] confidences = processModel(model, inputFeature0);

            if (confidences != null) {
                processResults(confidences);
            } else {
                result.setText("Model processing failed");
            }

            // Close the model
            closeModel(model);

        } catch (Exception e) {
            Log.e(TAG, "Error in classification: " + e.getMessage());
            result.setText("Error: " + e.getMessage());
        }
    }

    private Object initializeModel() {
        try {
            // Use reflection to avoid compilation issues with the generated model
            Class<?> modelClass = Class.forName("com.example.mlwithtensorflowlite.ml.Model");
            java.lang.reflect.Method newInstanceMethod = modelClass.getMethod("newInstance", android.content.Context.class);
            return newInstanceMethod.invoke(null, getApplicationContext());
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize model: " + e.getMessage());
            return null;
        }
    }

    private float[] processModel(Object model, TensorBuffer inputFeature0) {
        try {
            // Use reflection to call process method
            java.lang.reflect.Method processMethod = model.getClass().getMethod("process", TensorBuffer.class);
            Object outputs = processMethod.invoke(model, inputFeature0);

            // Get output feature
            java.lang.reflect.Method getOutputMethod = outputs.getClass().getMethod("getOutputFeature0AsTensorBuffer");
            TensorBuffer outputFeature0 = (TensorBuffer) getOutputMethod.invoke(outputs);

            return outputFeature0.getFloatArray();
        } catch (Exception e) {
            Log.e(TAG, "Failed to process model: " + e.getMessage());
            return null;
        }
    }

    private void closeModel(Object model) {
        try {
            java.lang.reflect.Method closeMethod = model.getClass().getMethod("close");
            closeMethod.invoke(model);
        } catch (Exception e) {
            Log.e(TAG, "Failed to close model: " + e.getMessage());
        }
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
        byteBuffer.order(ByteOrder.nativeOrder());

        // Create a resized bitmap to ensure correct dimensions
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, true);

        int[] intValues = new int[imageSize * imageSize];
        resizedBitmap.getPixels(intValues, 0, resizedBitmap.getWidth(), 0, 0,
                resizedBitmap.getWidth(), resizedBitmap.getHeight());

        int pixel = 0;
        for (int i = 0; i < imageSize; i++) {
            for (int j = 0; j < imageSize; j++) {
                int val = intValues[pixel++];
                // Normalize pixel values to [0, 1]
                byteBuffer.putFloat(((val >> 16) & 0xFF) / 255.0f); // R
                byteBuffer.putFloat(((val >> 8) & 0xFF) / 255.0f);  // G
                byteBuffer.putFloat((val & 0xFF) / 255.0f);         // B
            }
        }
        return byteBuffer;
    }

    private void processResults(float[] confidences) {
        if (confidences == null || confidences.length == 0) {
            result.setText("No results");
            return;
        }

        int maxPos = 0;
        float maxConfidence = 0;

        // Find the class with highest confidence
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }

        String[] classes = {
                "Sun Chips Mix Masala",
                "Sun Chips Garlic & Chilli",
                "Bombay Sweets Potato Cracker (Free Surprise Toy)",
                "Bombay Sweets Pasta Chips Shell Chaat Flavour",
                "Bombay Sweets Korntos Chaat Masti",
                "Bombay Sweets Alooz Charcoal BBQ Chips",
                "Bombay Sweets Alooz Waves Phuchka",
                "Sun Chips Tomato Tango",
                "Cheese Puff",
                "Bombay Sweets Cheese Ball Cheezee Corn Snacks",
                "Pran Potato Cracker",
                "Bombay Sweets Ring Chips",
                "Ifad Eggy Stix Bar-B-Q Chips",
                "Kurkure American Style Cream & Onion Chips",
                "Haque Potato Chips",
                "Bombay Sweets Potato Crackers",
                "Bombay Sweets Nachoz Chutney & Lime",
                "Bombay Sweets Alooz Spanish Tomato Flavor",
                "Sun Chips Salt & Pepper",
                "Kurkure Chilli Chatka Chips",
                "Bombay Sweets Mr. Chicken Chips",
                "Ifad Eggy Pillow Bar-B-Q Chips",
                "Pringles Sour Cream & Onion Potato Chips",
                "Lay's Thai Style Spicy Chicken Potato Chips",
                "Kurkure Masala Munch Chips",
                "Ruchi BBQ Potato Crackers",
                "Bombay Sweets Alooz Waves Hot Flavour Potato Chips",
                "Bombay Sweets Alooz Red Chilli Chatka",
                "Bombay Sweets Nachoz Spicy Nacho Cheese",
                "Bombay Sweets Alooz Magic Masala",
                "Bombay Sweets Pasta Chips Shell Chutney Flavour",
                "Haque Pillow Chips",
                "Haque Tarzan Chips",
                "ACI Fun Fresh Fry Potato Pallets",
                "Pringles Original Potato Chips",
                "Meridian Real Thai Chicken Chips",
                "Detos Chicken Wings Chips",
                "Curl",
                "Mr.Twist Tasty & Crispy Potato Chips",
                "Potato Crackers",
                "Lays American Style Cream & Onion Chips",
                "Lays Spanish Tomato Tango Chips",
                "Yokozona Twisty Goodness Crackers",
                "Meridian Crispy Chicken Sticks",
                "Poppers Corn Coconut Crackers",
                "Dekko Black & Brown Chocolate Chips Cookies",
                "Ruchi BBQ Chanachur",
                "Bombay Sweets Dalmoth Chanachur",
                "Ruchi Jhal Chanachur",
                "Class 50",
                "Igloo Cup Vanilla",
                "Igloo Stick Shell & Core",
                "Igloo Premium Macho",
                "Igloo Container Mango Fresh",
                "Bisk Club Shero Biscuit",
                "Olympic Energy Plus Biscuits",
                "Olympic Lexus Vegetable Crackers Biscuits",
                "Haque Mr. Cookie Butter Coconut Biscuit",
                "Olympic Nutty Real Peanut Biscuit",
                "Pran Potata Spicy Biscuit"
        };

        // Ensure we don't go out of bounds
        if (maxPos < classes.length) {
            String className = classes[maxPos];
            result.setText(className);

            // Build confidence string for all classes
            StringBuilder confidenceBuilder = new StringBuilder();
            for (int i = 0; i < classes.length && i < confidences.length; i++) {
                confidenceBuilder.append(String.format(Locale.getDefault(),
                        "%s: %.1f%%\n", classes[i], confidences[i] * 100));
            }
            confidence.setText(confidenceBuilder.toString());
        } else {
            result.setText("Unknown Class");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap image = (Bitmap) extras.get("data");
                if (image != null) {
                    // Create square thumbnail
                    int dimension = Math.min(image.getWidth(), image.getHeight());
                    image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);

                    // Display the image
                    imageView.setImageBitmap(image);

                    // Classify the image
                    classifyImage(image);
                }
            }
        }
    }
}