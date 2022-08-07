package co.il.dmobile.recipes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecipesActivity extends AppCompatActivity {

    private int CAMERA_PERMISSION_CODE = 1;
    private int GALLERY = 2, CAMERA = 1;
    Uri contentURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // Camera permission
        if (ContextCompat.checkSelfPermission(AddRecipesActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AddRecipesActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show(); // if permission was already granted - keep on going
        } else {
            requestCameraPermission(); // if not request permission
        }

        Button addPhotoBtn = findViewById(R.id.pickPhotoBtn);
        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText recipeLevel = findViewById(R.id.Level);
                EditText recipeTime = findViewById(R.id.Time);
                EditText recipeName = findViewById(R.id.Name);
                EditText recipePrice = findViewById(R.id.price);
                EditText recipeCooking = findViewById(R.id.Cooking);
                Recipes recipes;
                recipes = new Recipes(R.drawable.pizza,recipeLevel.getText().toString(), recipeTime.getText ().toString(),recipeName.getText().toString(),recipePrice.getText().toString(),recipeCooking.getText().toString());
                if(contentURI != null){
                    String uri = contentURI.toString();
                    recipes.setUri(uri);
                }

                Intent i = new Intent();
                i.putExtra("Recipes", recipes);
                setResult(1,i);
                finish();

            }
        });

    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select image from gallery",
                "take picture from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseImageFromGallary();
                                break;
                            case 1:
                                takePictureFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseImageFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePictureFromCamera() {

        String fileName = "new-photo-name.jpg";
        // Create parameters for Intent with filename
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
        contentURI =
                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentURI);
        startActivityForResult(intent, CAMERA);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            contentURI = data.getData();

        } else if (requestCode == CAMERA) {
            System.out.println("contentUri: " + contentURI);
            System.out.println(contentURI);
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(AddRecipesActivity.this,
                                    new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }
}